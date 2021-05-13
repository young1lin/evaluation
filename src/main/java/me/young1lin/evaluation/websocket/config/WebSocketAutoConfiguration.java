package me.young1lin.evaluation.websocket.config;

import me.young1lin.evaluation.websocket.server.EvaluationServer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com"></a>young1lin
 * @version 1.0
 * @since 2020/11/16 10:03 上午
 */
@Configuration
public class WebSocketAutoConfiguration {

	/**
	 * 添加 WebSocket 支持
	 *
	 * @return org.springframework.web.socket.server.standard.ServerEndpointExporter
	 * @author 杨逸林
	 * @date 2019/7/3 9:20
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	@Bean
	public EvaluationServer evaluationServer() {
		return new EvaluationServer();
	}

}
