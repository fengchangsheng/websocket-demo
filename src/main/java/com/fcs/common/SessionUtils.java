package com.fcs.common;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Lucare.Feng on 2016/9/7.
 */
public class SessionUtils {

    public static Map<String, Session> clients = new ConcurrentHashMap<>();

    public static void put(int userCode, Session session) {
        clients.put(getKey(userCode), session);
    }

    public static Session get(int userCode){

        return clients.get(getKey(userCode));
    }

    public static void remove(int userCode){
        clients.remove(getKey(userCode));
    }

    /**
     * 判断是否有连接
     * @param userCode
     * @return
     */
    public static boolean hasConnection(int userCode) {

        return clients.containsKey(getKey(userCode));
    }

    /**
     * 组装唯一识别的key
     * @param userCode
     * @return
     */
    public static String getKey(int userCode) {
        return "userid_" + userCode;
    }
}
