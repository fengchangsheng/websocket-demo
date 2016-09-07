package com.fcs;


import javax.websocket.Session;

/**
 * Created by Lucare.Feng on 2016/9/7.
 */
public class PushTest {

    public void broadcast(String relationId, int userCode, String message) {

        if (SessionUtils.hasConnection(relationId, userCode)) {
            SessionUtils.get(relationId, userCode).getAsyncRemote().sendText(message);
        } else {
            throw new NullPointerException(SessionUtils.getKey(relationId, userCode) + " Connection does not exist");
        }

    }

    public void broadcast(String key, String message) {
        if (SessionUtils.hasConnection(key)) {
            SessionUtils.getFromRedis(key).getAsyncRemote().sendText(message);
        } else {
            throw new NullPointerException(key + " Connection does not exist");
        }
    }

    public static void main(String[] args) {
        String prefix = "websocket";
        int useCode = 18;
//        new PushTest().broadcast(prefix,useCode,"好好好");
        new PushTest().broadcast("websocket_18","行行行");
    }
}
