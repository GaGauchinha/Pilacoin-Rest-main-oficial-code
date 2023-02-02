package br.ufsm.politecnico.csi.tapw.pila.servidor.service;

import br.ufsm.politecnico.csi.tapw.pila.servidor.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Service
public class WebSocketService {

    public static WebSocketClient sessionHandler;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedRate = 30000)
    private void enviaMsgDificuldade() {
        simpMessagingTemplate.convertAndSend("/topic/dificuldade", 10000);
    }


    @EventListener
    public void handleSessionConnected(SessionConnectEvent event) {
        System.out.println(event);
    }

    @EventListener
    public void handleSubscribe(SessionSubscribeEvent event) {
        Message<byte[]> message = event.getMessage();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        if (command.equals(StompCommand.SUBSCRIBE)) {
            String sessionId = accessor.getSessionId();
            String stompSubscriptionId = accessor.getSubscriptionId();
            String destination = accessor.getDestination();
            System.out.println("sessionid= " + sessionId + ", stomSubsID= " + stompSubscriptionId + ", destination= " + destination);
        }
    }
}
