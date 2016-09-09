package com.fcs;


import com.fcs.common.SessionUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Lucare.Feng on 2016/9/6.
 */
@ServerEndpoint("/websocket/{userId}")
public class WebsocketTest{

    @OnMessage
    public String hello(String message) {
        System.out.println("Received : "+ message);
        return "ok,thank u";
    }

    @OnOpen
    public void myOnOpen(@PathParam("userId") int userId, Session session) {
        System.out.println("WebSocket opened: " + session.getId());
        SessionUtils.put(userId,session);
    }

    @OnClose
    public void myOnClose(@PathParam("userId") int userId, CloseReason reason) {
        System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
        SessionUtils.remove(userId);
    }


//    /**
//     * 打开连接时触发
//     *
//     * @param userId
//     * @param session
//     */
//    @OnOpen
//    public void onOpen(@PathParam("userId") int userId, Session session) {
//        System.out.println("Websocket Start Connecting:" + SessionUtils.getKey(userId));
//        SessionUtils.put(userId, session);
//    }
//
//    /**
//     * 收到客户端消息时触发
//     *
//     * @param userId
//     * @param message
//     * @return
//     */
//    @OnMessage
//    public String onMessage(@PathParam("userId") int userId, String message) {
//        return "Got your message (" + message + ").Thanks !";
//    }
//
//    /**
//     * 异常时触发
//     *
//     * @param userId
//     * @param session
//     */
//    @OnError
//    public void onError(@PathParam("userId") int userId, Throwable throwable, Session session) {
//        System.out.println("Websocket Connection Exception:" + SessionUtils.getKey(userId));
//        SessionUtils.remove(userId);
//    }
//
//    /**
//     * 关闭连接时触发
//     *
//     * @param userId
//     * @param session
//     */
//    @OnClose
//    public void onClose(@PathParam("userId") int userId, Session session) {
//        System.out.println("Websocket Close Connection:" + SessionUtils.getKey(userId));
//        SessionUtils.remove(userId);
//    }

}
