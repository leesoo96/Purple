package com.purple.demo.config;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class BroadSocket {

    @OnOpen
    public void handleOpen() {
        System.out.println("웹소켓 연결");
    }

    @OnClose
    public void handleCose() {
        System.out.println("웹소켓 종료");
    }

    @OnError
    public void handleError(Throwable t) {
        System.out.println("에러 발생");
        t.printStackTrace();
    }
}
