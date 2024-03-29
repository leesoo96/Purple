package com.purple.demo.handler;

import com.purple.demo.common.Utils;
import com.purple.demo.config.AlarmSocketService;
import com.purple.demo.mapper.AlarmMapper;
import com.purple.demo.mapper.ChatMapper;
import com.purple.demo.model.DTO.AlarmDTO;
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
    final AlarmMapper alarmMapper;
    final Utils utils;
    final AlarmSocketService socketService; // 로그인한 사용자들 

    // 클라이언트가 접속했을 때 호출
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        
    }

    // 데이터 전송 시 호출
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
            int sendto = utils.getUserPkFromId((String)json.get("send_to"));
            int from = utils.getUserPkFromId((String)json.get("from"));

            dto.setMessage_sendto(sendto);
            dto.setMessage_from(from);
            dto.setMessage_date((String)json.get("chat_time"));
            dto.setMessage_ctnt((String)json.get("chat_ctnt"));
            dto.setMessage_chatroomid((String)json.get("room_id")); 

            chatMapper.insMessage(dto);

            String send_to = (String)json.get("send_to");
            WebSocketSession wss = socketService.getSession(send_to);
            try{
                wss.sendMessage(new TextMessage(json.toJSONString()));
            }catch(Exception e) {
                e.printStackTrace();
            }
        }else if(json.get("type").equals("ALARM")){
            String send_to = String.valueOf(json.get("alarm_sendto"));
            
            if(utils.CheckNumber(send_to)){
                int send_to_pk = Integer.parseInt(send_to);
                send_to = utils.getUserIdFromPk(send_to_pk);
            }

            int send_to_pk = utils.getUserPkFromId(send_to);
            if(Integer.parseInt(String.valueOf(json.get("alarm_from"))) == send_to_pk) {
                return;
            }
            String from = utils.getUserIdFromPk(Integer.parseInt(String.valueOf(json.get("alarm_from"))));
            AlarmDTO alarmDTO = new AlarmDTO();

            alarmDTO.setAlarm_category(Integer.parseInt(String.valueOf(json.get("alarm_category"))));
            alarmDTO.setAlarm_from(Integer.parseInt(String.valueOf(json.get("alarm_from"))));
            alarmDTO.setAlarm_sendto(send_to_pk);
            alarmDTO.setAlarm_valuepk(Integer.parseInt(String.valueOf(json.get("alarm_valuepk"))));
            
            alarmMapper.insAlarm(alarmDTO);

            json.replace("alarm_from", from);
            WebSocketSession wss = socketService.getSession(send_to);
            try{
            wss.sendMessage(new TextMessage(json.toJSONString()));
            }catch(Exception e) {   
                e.printStackTrace();
            }
        }
    }

    // 클라이언트 접속이 종료되었을 때 호출
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        socketService.removeSession(session);
        
        super.afterConnectionClosed(session, status);
    }

    // 에러 발생시
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println((session.getId() + " 에러 발생: " + exception.getMessage()));
	}
}