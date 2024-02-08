package com.hk.motong.chatting.handler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SocketTextHandler extends TextWebSocketHandler{

   private HashMap<Integer, HashMap<String, WebSocketSession>> map = new HashMap<>();
   private int roomNo;
    
   //채팅이 오픈되면 실행: 참여자의 방번호와 id를 확인하고 map에 저장하는 작업
   @Override
   public void afterConnectionEstablished(WebSocketSession session) throws Exception {
      String path = session.getUri().getPath();
      System.out.println(path+ "님이 입장하셨습니다.");
      //path에서 roomNO 추출(1~9)
      roomNo = Integer.parseInt(path.charAt(path.length()-1) + "");
      if (map.get(roomNo) == null) {//채팅방에 없는 경우 채팅방에 추가한다.
         var room = new HashMap<String, WebSocketSession>();
         room.put(session.getId(), session);//id이름으로 session 저장
         map.put(roomNo, room);//방번호로 사용자 session저장
      } else {
         //기존에 채팅 접속자는 방에 추가한다.
         map.get(roomNo).put(session.getId(), session);
      }
      
   }
   
   //클라이언트에서 전달받은 메시지를 각각의 채팅참여자들에게 전송한다.
   @Override
   protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//       String msg = message.getPayload();
//       JSONObject json = (JSONObject)new JSONParser().parse(msg);
       HashMap<String, WebSocketSession> room = map.get(roomNo);
       for (var v : room.values()) {//참여자들에게 각각 메시지를 전달하기 위해 session 값들을 구한다.
          if (v.isOpen()) {//참여자들이 채팅연결이 되어 있다면
             System.out.println(message.getPayload());//메시지 콘솔에 출력
             v.sendMessage(message);// 클라이언트로 메시지를 전송한다.
          }
       }
   }
   
   //연결이 종료되면 사용자 세션 삭제
   @Override
   public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
      
      String path = session.getUri().getPath();
       map.get(roomNo).remove(session.getId());
       System.out.println(path+ "님이 퇴장하셨습니다.");
      
   
   }
}