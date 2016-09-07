package com.fcs;

import org.mission.jedis.proxy.RedisProxyFactory;
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
            logger.info("====>[file-webapp] listener has running.<===");
            RedisProxyFactory.start();
            logger.info("====>[file-webapp] listener has running successfully<====");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("====>[file-webapp] listener started with errors<======",e);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        RedisProxyFactory.releaseProxy();
    }
}
