package com.purple.demo.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebsocketHandler extends TextWebSocketHandler {
    
    // 접속한 클라이언트 세션들을 저장하는 list 
    private static List<WebSocketSession> list = new ArrayList<>();

    // 클라이언트가 접속했을때 호출된다
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        list.add(session);

        System.out.println("연결성공");
    }

    // 클라이언트가 메시지를 보냈을때 호출된다 
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 전송된 메시지를 list의 모든 세션에 전송
        String msg = message.getPayload();
        for(WebSocketSession sock : list) {
            try{
                sock.sendMessage(new TextMessage(msg));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    // 클라이언트 접속이 종료되었을 때 호출된다 
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("종료");

        list.remove(session);
    }
}