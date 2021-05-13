package me.young1lin.evaluation.websocket.server;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import me.young1lin.evaluation.api.domain.Active;
import me.young1lin.evaluation.common.exception.WindowIsNotFoundException;
import me.young1lin.evaluation.common.util.ApiReturnUtil;
import me.young1lin.evaluation.common.util.DateUtil;
import me.young1lin.evaluation.common.util.SpringBeanUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 评价器服务端
 *
 * @author 杨逸林
 * @date 2019-07-08 14:31
 */
@ServerEndpoint("/im/{winNum}")
@Component
@Slf4j
public class EvaluationServer {

	/**
	 * static 变量被该类class对象持有，在类初始化时一起初始化。final保证对象引用不可变
	 */
	private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);
	/**
	 * 与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	private Session session;
	/**
	 * 使用map对象，便于根据winNum来获取对应的WebSocket,当然也可以用CopyOnWriteArrayList
	 */
	private static ConcurrentHashMap<String, EvaluationServer> webSocketList = new ConcurrentHashMap<>();
	/**
	 * 接收winNum
	 */
	private String winNum = "";

	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("winNum") String fromWinNum) throws IOException {
		this.session = session;
		if (!StringUtils.isEmpty(fromWinNum)) {
			log.error("请输入窗口号！！！！！！！！！！！！！！！！");
			try {
				if (webSocketList.get(fromWinNum) == null) {
					this.winNum = fromWinNum;
					webSocketList.put(fromWinNum, this);
					//在线数加1
					log.info("有新窗口开始监听:{},当前窗口数为{}", fromWinNum, ONLINE_COUNT.incrementAndGet());
				}
				else {
					session.getBasicRemote().sendText("已有相同窗口，请重新输入不同窗口号");
					CloseReason closeReason = new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "相同窗口");
					session.close(closeReason);
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			if (session.isOpen()) {
				String jo = JSON.toJSONString(ApiReturnUtil.success());
				session.getBasicRemote().sendText(jo);
			}
		}
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		if (webSocketList.get(this.winNum) != null) {
			webSocketList.remove(this.winNum);
			//在线数减1
			log.info("有一连接关闭！当前在线窗口为：[{}]", ONLINE_COUNT.decrementAndGet());
		}
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message 客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("收到来自窗口[{}]的信息:[{}],会话ID:[{}]", winNum, message, session.getId());
		try {
			if (StringUtils.isNotBlank(message)) {
				//解析发送的报文p
				Map map = JSON.parseObject(message, Map.class);
				Integer level = (Integer) map.get("evaluation");
				String serialNum = (String) map.get("serialNum");
				String requestTime = DateUtil.getCurrentDateTime();
				JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringBeanUtil.getBean("jdbcTemplate");
				String sql = "insert into evaluation (serial_num,level,deleted,creator,create_time,updater,update_time)values(?,?,?,?,?,?,?)";
				jdbcTemplate.update(sql, serialNum, level, false, "admin", requestTime, "admin", requestTime);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@OnError
	public void onError(Session session, Throwable error) {
		log.error("发生错误");
		error.printStackTrace();
	}

	/**
	 * 服务器指定推送至某个客户端
	 *
	 * @param message message to clients
	 * @author 杨逸林
	 * @date 2019/7/3 10:02
	 */
	private void sendMessage(String message) throws IOException {
		session.getBasicRemote().sendText(message);
	}

	/**
	 * 发送给指定 浏览器
	 *
	 * @param message 消息
	 * @param winNum 窗口号
	 * @author 杨逸林
	 * @date 2019/7/3 9:58
	 */
	public static void sendInfo(String message, @PathParam("winNum") String winNum) throws WindowIsNotFoundException {
		if (webSocketList.get(winNum) == null) {
			log.error("没有窗口号！！！！！！！！！");
			throw new WindowIsNotFoundException("没有窗口号");
		}
		webSocketList.forEach((k, v) -> {
			try {
				//这里可以设定只推送给这个winNum的
				if (k.equals(winNum)) {
					log.info("推送消息到窗口:[{}]，推送内容: [{}]", winNum, message);
					v.sendMessage(message);
				}
			}
			catch (IOException e) {
				e.printStackTrace();
				log.info("找不到指定的 WebSocket 客户端：[{}]", winNum);
			}
		});
	}

	/**
	 * 向OCX发送动作
	 *
	 * @param active 动作
	 * @param winNum 窗口号
	 * @author 杨逸林
	 * @date 2019-09-25 21:03
	 */
	public static void sendInfoToOCX(Active active, @PathParam("winNum") String winNum) {
		String activeJson = JSON.toJSONString(active);
		if (webSocketList.get(winNum) == null) {
			return;
		}
		webSocketList.forEach((k, v) -> {
			try {
				if (k.equals(winNum)) {
					log.info("推送消息到窗口：[{}]，推送内容：[{}]", winNum, active);
					v.sendMessage(activeJson);
				}
			}
			catch (IOException e) {
				e.printStackTrace();
				log.info("系统异常[{}]", e.getMessage());
			}
		});
	}

	public static int getOnlineCount() {
		return ONLINE_COUNT.get();
	}

	public static synchronized ConcurrentHashMap<String, EvaluationServer> getWebSocketList() {
		return webSocketList;
	}

}

