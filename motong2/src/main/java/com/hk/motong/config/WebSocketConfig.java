package com.hk.motong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.hk.motong.handler.WebChatHandler;
import com.hk.motong.handler.WebSocketInterceptor;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

   @Autowired
   public WebSocketInterceptor webSocketInterceptor;
   
   @Override 
   public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
      registry.addHandler(new WebChatHandler(), "/chatting/**")
            .addInterceptors(webSocketInterceptor)
            .setAllowedOriginPatterns("*");
      
   }

}