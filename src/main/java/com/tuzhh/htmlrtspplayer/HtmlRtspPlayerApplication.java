package com.tuzhh.htmlrtspplayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@EnableWebSocket
@SpringBootApplication
public class HtmlRtspPlayerApplication implements WebSocketConfigurer {

    @Bean
    public WebSockerServerRtspHandler handlerRtsp() {
        return new WebSockerServerRtspHandler();
    }
    @Bean
    public WebSockerServerRtpHandler handlerRtp() {
        return new WebSockerServerRtpHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // ctrlChannel
        registry.addHandler(handlerRtsp(), "/player/control").setAllowedOrigins("*").addInterceptors(new WebSocketHandshakeInterceptor());

        // dataChannel
        registry.addHandler(handlerRtp(), "/player/data").setAllowedOrigins("*").addInterceptors(new WebSocketHandshakeInterceptor());
    }

    public static void main(String[] args) {
        SpringApplication.run(HtmlRtspPlayerApplication.class, args);
    }
}
