package com.fcs;

import org.mission.jedis.proxy.RedisProxy;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Lucare.Feng on 2016/9/7.
 */
public class SessionUtils {

    public static Map<String, Session> clients = new ConcurrentHashMap<>();

    public static void put(String relationId, int userCode, Session session) {
        clients.put(getKey(relationId, userCode), session);
    }

    public static void putRedis(String key, Session session) {
        RedisProxy proxy = RedisAccessor.getDefaultClient();
        proxy.putObjectExpire(key,session,1800);
    }

    public static Session getFromRedis(String key) {
        RedisProxy proxy = RedisAccessor.getDefaultClient();
        Session session = proxy.getObject(key, Session.class);
        return session;
    }

    public static Session get(String relationId, int userCode){

        return clients.get(getKey(relationId, userCode));
    }

    public static void remove(String relationId, int userCode){
        clients.remove(getKey(relationId, userCode));
    }

    /**
     * 判断是否有连接
     * @param relationId
     * @param userCode
     * @return
     */
    public static boolean hasConnection(String relationId, int userCode) {

        return clients.containsKey(getKey(relationId, userCode));
    }

    public static boolean hasConnection(String key) {
        RedisProxy proxy = RedisAccessor.getDefaultClient();
        Session session = proxy.getObject(key, Session.class);
        return session != null;
    }

    /**
     * 组装唯一识别的key
     * @param relationId
     * @param userCode
     * @return
     */
    public static String getKey(String relationId, int userCode) {
        return relationId + "_" + userCode;
    }
}
