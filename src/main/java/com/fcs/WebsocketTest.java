package com.fcs;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Lucare.Feng on 2016/9/6.
 */
@ServerEndpoint("/hello")
public class WebsocketTest{

    @OnMessage
    public String hello(String message) {
        System.out.println("Received : "+ message);
        return "ok,thank u";
    }

    @OnOpen
    public void myOnOpen(Session session) {
        System.out.println("WebSocket opened: " + session.getId());
        String prefix = "websocket";
        int useCode = 18;
        SessionUtils.put(prefix,useCode,session);
        SessionUtils.putRedis("websocket_18",session);

    }

    @OnClose
    public void myOnClose(CloseReason reason) {
        System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
    }

}
