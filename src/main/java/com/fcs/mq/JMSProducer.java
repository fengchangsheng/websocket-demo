package com.fcs.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSProducer {

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;// 默认连接用户名
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;// 默认连接密码
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;// 默认连接地址

	private static Session session = null;
	private static MessageProducer messageProducer = null;

	public static void start() {
		Connection connection = null;
		try {
			// 实例化连接工厂
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(JMSProducer.USERNAME,
					JMSProducer.PASSWORD, JMSProducer.BROKEURL);

			// 通过连接工厂获取连接
			connection = connectionFactory.createConnection();

			// 启动连接
			connection.start();

			// 创建session
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

			// 创建一个名称为HelloWorld的消息队列
			Destination destination = session.createQueue("DMessage");

			// 创建消息生产者
			messageProducer = session.createProducer(destination);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void sendMessage(int userId, String title, String content) {
		try {
			// 创建一条文本消息
			//TextMessage message = session.createTextMessage(dmsg);
			
			MapMessage message = session.createMapMessage();
			
			message.setString(String.valueOf(userId), title + "##" + content);
			
			// 通过消息生产者发出消息
			messageProducer.send(message);

			session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
