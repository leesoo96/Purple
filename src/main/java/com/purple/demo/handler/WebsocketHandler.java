package com.purple.demo.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.purple.demo.model.UserPrincipal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Component
public class WebsocketHandler extends TextWebSocketHandler {    // 웹 소켓 서버 생성하는 곳.
    
    // 로그인한 사용자들 
    private Map<String, WebSocketSession> socket = new ConcurrentHashMap<>();

    // 클라이언트가 접속했을 때 호출된다
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("웹소켓 연결 성공");
        
        socket.put(session.getId(), session);
    }

<<<<<<< HEAD
    // 데이터 전송 시 호출된다 
=======
    // 클라이언트가 메시지를 보냈을 때 호출된다 
>>>>>>> d127268cd7626447a3bdfd97c528c46a9b2c5fc7
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
<<<<<<< HEAD
		for(String key : socket.keySet()) {
			WebSocketSession wss = socket.get(key);
			try {
				wss.sendMessage(new TextMessage(msg));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
=======
        for(WebSocketSession sock : list) {
            try{
                sock.sendMessage(new TextMessage(msg));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
>>>>>>> d127268cd7626447a3bdfd97c528c46a9b2c5fc7
    }

    // 클라이언트 접속이 종료되었을 때 호출된다 
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("웹소켓 종료");
        socket.remove(session.getId()); 
        super.afterConnectionClosed(session, status);
    }

    // 에러 발생시
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println((session.getId() + " 에러 발생: " + exception.getMessage()));

	}

    // 로그인한 유저들의 아이디
    private String getUserId(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // HttpSession에 저장된 아이디
        String user_id = (String) httpSession.get(principal.getUser_id()); 
        System.out.println("id = " + user_id);
		return user_id == null ? null: user_id;
	}
}