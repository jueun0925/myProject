package com.hk.motong.handler;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.hk.motong.dtos.UserDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//WebsocketSession과 HttpSession은 따로 관리되기 때문에 
//로그인시 HttpSession에 저장된 id와 연동할 수 없다. --> 로그인 세션과 채팅세션은 다른 저장영역임
//그래서 서로 연결시켜주는 작업을 구현해야 하는데 이작업을 HandshakeInterceptor가 가능하게 해줌

//websocket이 실행되면 중간에서 handshakeInterceptor가 가로채서 연결해주는 작업을 진행하게 됨

@Component
public class WebSocketInterceptor implements HandshakeInterceptor{

   public WebSocketInterceptor() {
      // TODO Auto-generated constructor stub
   }
   
   //채팅이 실행하기 전에 httpSession에 id를 attributes에 저장하게 되면 
   //websocketSession에서 가져올 수 있음 
   // websocketSession session;
   // session.getAttributes().get("userId"); // "userId"는 httpSession에 저장된 값의 이름임
   @Override
   public boolean beforeHandshake(ServerHttpRequest request, 
                           ServerHttpResponse response, 
                           WebSocketHandler wsHandler,
                           Map<String, Object> attributes) throws Exception {
      HttpServletRequest req = 
            ((ServletServerHttpRequest)request).getServletRequest();
      
      //getSession(),getSession(true): 세션이 존재하면 반환하고 존재하지 않으면 새로 생성
      //getSession(false): 세션이 존재하면 반환하고, 존재하지 않으면 null반환
      HttpSession session = req.getSession(false);
      System.out.println(getClass()+" session:"+(session==null?"session없음":(String)session.getAttribute("userId")));
      if(session!=null) {
         //HttpSession --> WebsocketSession 으로 값전달
         UserDto ldto=(UserDto)session.getAttribute("ldto");//세션에서 id값 가져오기
         attributes.put("ldto", ldto);//WebsocketSession에 저장하기
         return true;
      }
      
      return false;
   }
   
   @Override
   public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
         Exception exception) {
      // TODO Auto-generated method stub
      
   }
}