package br.ufsm.politecnico.csi.tapw.pila.servidor.client;

import br.ufsm.politecnico.csi.tapw.pila.model.BlocoModel;
import br.ufsm.politecnico.csi.tapw.pila.model.PilacoinModel;
import br.ufsm.politecnico.csi.tapw.pila.servidor.service.ValidaPilaService;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.Objects;
@Service
public class WebSocketClient {

    @Getter
    private final MyStompSessionHandler sessionHandler = new MyStompSessionHandler();
    @Value(value = "${endereco.server}")
    private String enderecoServer;

    @PostConstruct
    private void init() {
        System.out.println("iniciou");
        StandardWebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.connect("ws://" + enderecoServer + "/websocket/websocket", sessionHandler);
        System.out.println("conectou");
    }

    @Scheduled(fixedRate = 3000)
    private void printDificuldade() {
        if (sessionHandler.dificuldade != null) {
            System.out.println("Dificuldade Atual: " + sessionHandler.dificuldade);
        }
    }

    private static class MyStompSessionHandler implements StompSessionHandler {
        @Getter
        private BigInteger dificuldade;

        @Override
        public void afterConnected(StompSession stompSession,
                                   StompHeaders stompHeaders)
        {
            stompSession.subscribe("/topic/dificuldade", this);
            stompSession.subscribe("/topic/validaMineracao", this);
            stompSession.subscribe("/topic/descobrirNovoBloco", this);
        }

        @Override
        public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {
        }

        @Override
        public void handleTransportError(StompSession stompSession, Throwable throwable) {
        }

        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            //System.out.println(stompHeaders);
            if (Objects.equals(stompHeaders.getDestination(), "/topic/dificuldade")) {
                return DificuldadeRet.class;
            }else if (Objects.equals(stompHeaders.getDestination(), "/topic/validaMineracao")) {
                System.out.println("tem pila pra minerar");
                return PilacoinModel.class;
            }else if (Objects.equals(stompHeaders.getDestination(), "/topic/descobrirNovoBloco")) {
                System.out.println("tem bloco pra minerar");
                return BlocoModel.class;
            }
            return null;
        }

        @Override
        public void handleFrame(StompHeaders stompHeaders, Object a) {
            assert a != null;
            if (a.getClass().equals(DificuldadeRet.class)) {
                dificuldade = new BigInteger(((DificuldadeRet) a).getDificuldade(), 16);
            }else if (a.getClass().equals(PilacoinModel.class)) {
                PilacoinModel pilacoinModel = (PilacoinModel) a;
                ValidaPilaService.validarPilaColega(pilacoinModel);
                //System.out.println(pila.getNonce());
            } else if (a.getClass().equals(BlocoModel.class)) {
                BlocoModel blocoModel = (BlocoModel) a;
                ValidadaBlocoService.confirmaBloco(blocoModel);
                System.out.println(blocoModel.getNonce());
            }
        }
    }


    public BigInteger getDificuldade() {
        System.out.println(sessionHandler.dificuldade);
        return sessionHandler.dificuldade;
    }
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DificuldadeRet {
        private String dificuldade;
    }
}
