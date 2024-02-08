package com.hk.motong.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hk.motong.dtos.MessageDto;
import com.hk.motong.dtos.UserDto;

public class WebChatHandler extends TextWebSocketHandler{
   // roomNo는 채팅방을 구별하는 값 , websocketId는 참여자를 구별하는 값, websocketSession은 참여자
   // { roomNo:{websocketId:websocketSession,websocketId:websocketSession}
   //  ,roomNo:{websocketId:websocketSession,websocketId:websocketSession}....}
   private HashMap<Integer, HashMap<String, WebSocketSession>> map = new HashMap<>();
   private int roomNo;
   
   //채팅이 오픈되면 실행: 참여자의 방번호와 id를 확인하고 map에 저장하는 작업
   @Override
   public void afterConnectionEstablished(WebSocketSession session) throws Exception {
      String path = session.getUri().getPath();
      System.out.println("채팅참가자요청path:"+path);
      UserDto ldto=(UserDto)session.getAttributes().get("ldto");
      System.out.println("채팅참가자ID:"+ldto.getName());
      //path에서 roomNO 추출
//      roomNo = Integer.parseInt(path.charAt(path.length()-1) + ""); 
      roomNo=Integer.parseInt(path.substring(path.lastIndexOf("/")+1));
      session.getAttributes().put("roomNo", roomNo);   // /chat/55  123456789
      if (map.get(roomNo) == null) {//채팅방에 없는 경우 채팅방에 추가한다.
         var room = new HashMap<String, WebSocketSession>();
         room.put(session.getId(), session);//id이름으로 session 저장
         map.put(roomNo, room);//방번호로 사용자 session저장
      } else {
         //기존에 채팅 접속자는 방에 추가한다.
         map.get(roomNo).put(session.getId(), session);
      }
      
   }

//   @Autowired
//   ObjectMapper objectMapper=new ObjectMapper();
   //클라이언트에서 전달받은 메시지를 각각의 채팅참여자들에게 전송한다.
   @Override
   protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
       //클라이언트에서 전달된 값들을 MessageDto에 담는다.
       ObjectMapper objectMapper=new ObjectMapper();//message-->mDto에 담아줄 객체
       MessageDto mDto=objectMapper.readValue(message.getPayload(), MessageDto.class);//담아주는 작업
       System.out.println("room번호:"+mDto.getRoomNo());//클라이언트에서 전달된 방번호를 구한다.
       roomNo=Integer.parseInt(mDto.getRoomNo());//맴버필드에 방번호 저장
       HashMap<String, WebSocketSession> room = map.get(roomNo);//방번호에 해당하는 hashMap<id,websocketSession>객체 구한다.
       for (var v : room.values()) {//참여자들에게 각각 메시지를 전달하기 위해 session 값들을 구한다.
          if (v.isOpen()) {//참여자들이 채팅연결이 되어 있다면
             System.out.println(message.getPayload());//메시지 콘솔에 출력
             v.sendMessage(message);// 클라이언트로 메시지를 전송한다. 
          }
       }
   }
   
   //연결이 종료되면 사용자 세션 삭제
   @Override
   public void afterConnectionClosed(WebSocketSession session,CloseStatus status) throws Exception {
      
      //종료시 퇴장메시지를 보내줄 방번호 구하기
      String path=session.getUri().getPath();
      System.out.println("종료시path:"+session.getUri().getPath());
      roomNo=Integer.parseInt(path.substring(path.lastIndexOf("/")+1));
      System.out.println("방번호:"+roomNo);
      
      //사용자 아이디 구하기
      
//      String userId=(String)session.getAttributes().get("userId");
//      System.out.println("채팅종료하는 로그인 아이디:"+userId);
      UserDto ldto=(UserDto)session.getAttributes().get("ldto");
      System.out.println("채팅종료하는 로그인 아이디:"+ldto.getName());
      String name=ldto.getName();
      //퇴장하는 방번호에 해당하는 참여자 session을 구해서 메시지 전달하기
      HashMap<String, WebSocketSession> room = map.get(roomNo);
      for (var v : room.values()) {//참여자들에게 각 각 메시지를 전달하기 위해 session 값들을 구한다.
          if (v.isOpen()) {//참여자들이 채팅연결이 되어 있다면
             v.sendMessage(new TextMessage(
                   "{\"type\":\"bye\",\"userId\":\""+name+"\",\"roomNo\":\"1\",\"msg\":\""+name+"님이 퇴장하셨습니다.\"}"));
             System.out.println( new TextMessage(
                   "{\"type\":\"bye\",\"userId\":\""+name+"\",\"roomNo\":\"1\",\"msg\":\""+name+"님이 퇴장하셨습니다.\"}").getPayload());
          }
       }
   }
   
}