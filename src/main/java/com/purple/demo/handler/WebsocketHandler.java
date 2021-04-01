package com.purple.demo.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.purple.demo.common.Utils;
import com.purple.demo.config.AlarmSocketService;
import com.purple.demo.mapper.ChatMapper;
import com.purple.demo.mapper.LayoutMapper;
import com.purple.demo.model.DTO.MessageDTO;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebsocketHandler extends TextWebSocketHandler { 

    final ChatMapper chatMapper;
    final LayoutMapper layoutMapper;
    final Utils util;
    final AlarmSocketService socketService; // 로그인한 사용자들 

    // 로그인한 사용자들 
    private Map<String, WebSocketSession> users = new ConcurrentHashMap<>();

    // 클라이언트가 접속했을 때 호출된다
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        users.put(session.getId(),session);
    }

    // 데이터 전송 시 호출된다
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JSONParser parser = new JSONParser();
        String msg = message.getPayload();
        JSONObject json = (JSONObject)parser.parse(msg);
        
        if(json.get("type").equals("CREATE")) {
            String user_id = (String)json.get("user_id");
            socketService.putSession(user_id, session);
        }else if(json.get("type").equals("CHAT")) {
            // message를 DB에 저장
            MessageDTO dto = new MessageDTO();
            int sendto = util.getUserPkFromId((String)json.get("send_to"));
            int from = util.getUserPkFromId((String)json.get("from"));
            dto.setMessage_state(1);
            dto.setMessage_readstate(1);
            dto.setMessage_sendto(sendto);
            dto.setMessage_from(from);
            dto.setMessage_date((String)json.get("chat_time"));
            dto.setMessage_ctnt((String)json.get("chat_ctnt"));
            dto.setMessage_chatroomid((String)json.get("room_id"));
            
            chatMapper.insMessage(dto);
            
            json.put("noread", layoutMapper.getNoReadAllMessage(util.getUserPkFromId((String)json.get("send_to"))));

            String send_to = (String)json.get("send_to"); //테스트용으로 자기자신한테
            WebSocketSession wss = socketService.getSession(send_to);
            try{
                wss.sendMessage(new TextMessage(json.toJSONString()));
            }catch(Exception e) {
                e.printStackTrace();
                System.out.println("비로그인");
            }finally {
            }
        }else if(json.get("type").equals("DELETE")) {
            String user_id = (String)json.get("user_id");
            socketService.removeSession(user_id);
            System.out.println("종료");
        }else if(json.get("type").equals("CHAT")){
            
        }
       
        //보내는 메세지
        //로그인 한 websocketsession 검사
		// for(String key : users.keySet()) {
        //     System.out.println(key);
		// 	WebSocketSession wss = users.get(key);
        //     System.out.println(wss);
		// 	try {
		// 		wss.sendMessage(new TextMessage(msg));
		// 	}catch(Exception e) {
		// 		e.printStackTrace();
		// 	}
		// }
    }

    // 클라이언트 접속이 종료되었을 때 호출된다 
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        users.remove(session.getId());
        socketService.removeSession(session);
        super.afterConnectionClosed(session, status);
    }

    // 에러 발생시
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println((session.getId() + " 에러 발생: " + exception.getMessage()));
	}

    // // 로그인한 유저들의 아이디
    // private String getUserId(WebSocketSession session) {
	// 	Map<String, Object> httpSession = session.getAttributes();

	// 	UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //     // HttpSession에 저장된 아이디
    //     System.out.println("3");

    //     String user_id = (String) httpSession.get(principal.getUser_id()); 
    //     System.out.println("4");
	// 	return user_id == null ? null: user_id;
	// }
}