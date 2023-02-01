package br.ufsm.politecnico.csi.tapw.pila.controller;

import br.ufsm.politecnico.csi.tapw.pila.servidor.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.math.BigInteger;

@Controller
public class MineracaoController {

    @Autowired
    private WebSocketClient webSocketClient;
    @MessageMapping("/dificuldade")
    public BigInteger getDificuldade() {
        return webSocketClient.getDificuldade();
    }
}
