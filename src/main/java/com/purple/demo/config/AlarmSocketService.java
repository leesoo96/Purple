package com.purple.demo.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class AlarmSocketService {
    private Map<String, WebSocketSession> sessionList  = new ConcurrentHashMap();

    public WebSocketSession getSession(String user_id) {
        return sessionList.get(user_id);
    }

    public void putSession(String user_id, WebSocketSession session) {
        sessionList.put(user_id, session);
    }

    public void removeSession(String user_id) {
        sessionList.remove(user_id);
    }

    public void removeSession(WebSocketSession session) {
        String user_id = getId(session);
        sessionList.remove(user_id);
    }

    public String getId(WebSocketSession session) {
        for(String user_id: sessionList.keySet()) {
            if(sessionList.get(user_id).equals(session)) {
                return user_id;
            }
        }
        return null;
    }
    public void sendMessage(String getId, String msg) {
        WebSocketSession ws = sessionList.get(getId);
       /// ws.sendMessage(new TextMessage(msg));
    }
}
