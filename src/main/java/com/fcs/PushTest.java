package com.fcs;


import com.fcs.model.DMessage;
import com.fcs.mq.JMSProducer;
import java.util.Date;

/**
 * Created by Lucare.Feng on 2016/9/7.
 */
public class PushTest {

    public static void main(String[] args) {
//        DBClientFactory.start();
        JMSProducer.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    try {
//                        DMsgService service = new DMsgServiceImpl();

                        DMessage msg = new DMessage();
                        msg.setAccount_id(18);
                        msg.setAddtime(new Date());
                        msg.setAddip("127.0.0.1");
                        msg.setContent("测试内容" + i);
                        msg.setName("测试标题" + i);

                        JMSProducer.sendMessage(msg.getAccount_id(), msg.getName(), msg.getContent());
                        i++;

                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }, "Dmessage producer Thread").start();
    }
}
