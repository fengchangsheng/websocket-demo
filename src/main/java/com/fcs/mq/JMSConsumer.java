package com.fcs.mq;

import com.fcs.common.SessionUtils;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

/**
 * Created by Lucare.Feng on 2016/9/9.
 */
public class JMSConsumer {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;// 默认连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;// 默认连接密码
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;// 默认连接地址

    private static MessageConsumer messageConsumer = null;

    public static void start() {
        try {
            // 实例化连接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(JMSConsumer.USERNAME,
                    JMSConsumer.PASSWORD, JMSConsumer.BROKEURL);

            // 通过连接工厂获取连接
            Connection connection = connectionFactory.createConnection();

            // 启动连接
            connection.start();

            // 创建session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 创建一个连接HelloWorld的消息队列
            Destination destination = session.createQueue("DMessage");

            // 创建消息消费者
            messageConsumer = session.createConsumer(destination);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        MapMessage mapMessage;
                        try {
                            mapMessage = (MapMessage) messageConsumer.receive();
                            if (mapMessage != null) {
                                @SuppressWarnings("rawtypes")
                                Enumeration a = mapMessage.getMapNames();
                                while (a.hasMoreElements()) {
                                    String key = (String) a.nextElement();
                                    broadcast(Integer.parseInt(key), mapMessage.getString(key));
                                }
                            }
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "Dmessage consumer Thread").start();

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public static void broadcast(int userId, String message) {
        if (SessionUtils.hasConnection(userId)) {
            SessionUtils.get(userId).getAsyncRemote().sendText(message);
        } else {
            System.out.println(SessionUtils.getKey(userId) + " Connection does not exist");
        }
    }
}
