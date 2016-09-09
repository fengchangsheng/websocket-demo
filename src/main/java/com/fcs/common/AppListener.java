package com.fcs.common;

import com.fcs.mq.JMSConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Lucare.Feng on 2016/6/7.
 */
public class AppListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(AppListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            long begin = System.currentTimeMillis();
            long now = 0, current = 0;

            logger.info("====>[websocket-demo] listener has running.<===");
            // 1. active MQ 启动
            now = begin;
            JMSConsumer.start();
            current = System.currentTimeMillis();
            logger.info("[LOAD(MQ)] cost={}ms.", current - now);
            logger.info("====>[websocket-demo] listener has running successfully<====");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("====>[websocket-demo] listener started with errors<======",e);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
