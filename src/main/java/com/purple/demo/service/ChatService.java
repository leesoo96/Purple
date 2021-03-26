package com.purple.demo.service;

import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatService extends TextWebSocketHandler{  // 웹 소켓 서버 생성하는 곳.
    
    private HashMap<String, WebSocketSession> sessionMap = new HashMap<String, WebSocketSession>(); // 웹 소켓 세션을 담아둘 맵.

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{  // 웹 소켓 연결 시, 동작(클라이언트가 서버로 연결 시...)
        super.afterConnectionEstablished(session);
        sessionMap.put(session.getId(), session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message){   // 텍스트 메세지를 받았을 때, 실행(클라이언트가 데이터 전송 시...)
        String msg = message.getPayload();  // 메세지에 담긴 텍스트 값을 얻을 수 있다.

        for(String key : sessionMap.keySet()){
            WebSocketSession wss = sessionMap.get(key);

            try{
                wss.sendMessage(new TextMessage(msg));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{   // 웹 소켓 종료 시, 동작.
        sessionMap.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }
}
