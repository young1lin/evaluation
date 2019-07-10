package cn.luckyray.evaluation.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import cn.luckyray.evaluation.util.ApiReturnUtil;
import cn.luckyray.evaluation.util.DateUtil;
import cn.luckyray.evaluation.util.SpringBeanUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 评价器服务端
 * @author 杨逸林
 * @date 2019-07-08 14:31
*/
@ServerEndpoint("/im/{winNum}")
@Component
@Slf4j
public class EvaluationServer {

    /**
     *  静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     * @date 2019/7/3 9:25
    */
    private static volatile int onlineCount = 0;
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     * @date 2019/7/3 9:26
    */
    private Session session;
    /**
     * 使用map对象，便于根据winNum来获取对应的WebSocket
     * @date 2019/7/3 9:26
    */
    private static ConcurrentHashMap<String,EvaluationServer> websocketList = new ConcurrentHashMap<>();
    /**
     *  接收winNum
     * @date 2019/7/3 9:27
    */
    private String winNum="";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("winNum") String fromWinNum) throws IOException {
        this.session = session;
        if(StringUtils.isEmpty(fromWinNum)){
            log.error("请输入窗口号！！！！！！！！！！！！！！！！");
            return;
        }else{
            Lock lock = new ReentrantLock();
            try {
                if(websocketList.get(fromWinNum) == null){
                    this.winNum = fromWinNum;
                    websocketList.put(fromWinNum,this);
                    lock.lock();
                    addOnlineCount();           //在线数加1
                    log.info("有新窗口开始监听:{},当前窗口数为{}",fromWinNum,getOnlineCount());
                }else{
                    session.getBasicRemote().sendText("已有相同窗口，请重新输入不同窗口号");
                    CloseReason closeReason = new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE,"相同窗口");
                    session.close(closeReason);
                }
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
        if(session.isOpen()){
            String jo = JSON.toJSONString(ApiReturnUtil.success());
            session.getBasicRemote().sendText(jo);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        Lock lock = new ReentrantLock();
        lock.lock();
        if(websocketList.get(this.winNum)!=null){
            websocketList.remove(this.winNum);
            subOnlineCount();           //在线数减1
            log.info("有一连接关闭！当前在线窗口为：{}",getOnlineCount());
        }
        lock.unlock();
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口{}的信息:{},会话ID:",winNum,message,session.getId());
        if(StringUtils.isNotBlank(message)){
            //解析发送的报文
            Map<String,Object> map = JSON.parseObject(message, Map.class);
            Integer pjjg = (Integer) map.get("evaluation");
            String lsh = (String) map.get("serialNum");
            String requestTime = DateUtil.getCurrentDate();
            JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringBeanUtil.getBean("jdbcTemplate");
            //String sql = "insert into xxxx";
            //jdbcTemplate.execute(sql);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 服务器指定推送至某个客户端
     * @param message
     * @author 杨逸林
     * @date 2019/7/3 10:02
     * @return void
    */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送给指定 浏览器
     * @ param message
     * @param winNum
     * @author 杨逸林
     * @date 2019/7/3 9:58
     * @return void
    */
    public static void sendInfo(String message,@PathParam("winNum") String winNum) throws IOException {
        if(websocketList.get(winNum) == null){
            log.error("没有窗口号！！！！！！！！！");
            return;
        }
        websocketList.forEach((k,v)->{
            try {
                //这里可以设定只推送给这个winNum的，为null则全部推送
                if(winNum==null) {
                    v.sendMessage(message);
                }else if(k.equals(winNum)){
                    log.info("推送消息到窗口:{}，推送内容: {}",winNum,message);
                    v.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.info("找不到指定的 WebSocket 客户端：{}",winNum);
            }
        });
    }

    private synchronized int getOnlineCount() {
        return onlineCount;
    }

    private synchronized void addOnlineCount() {
        onlineCount++;
    }

    private synchronized void subOnlineCount() {
        onlineCount--;
    }

    public static synchronized ConcurrentHashMap<String,EvaluationServer> getWebSocketList(){
        return websocketList;
    }
}

