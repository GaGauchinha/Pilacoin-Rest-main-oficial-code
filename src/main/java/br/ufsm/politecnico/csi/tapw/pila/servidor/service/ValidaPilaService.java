package br.ufsm.politecnico.csi.tapw.pila.servidor.service;

import br.ufsm.politecnico.csi.tapw.pila.model.PilacoinModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Date;

@Service
public class ValidaPilaService {
    public static BigInteger dificuldade = BigInteger.ZERO;
    public static KeyPair keyPair;

    @SneakyThrows
    public static boolean validarPilaColega(PilacoinModel pilacoinModelColega) {

        UsuarioService UsuarioService = new UsuarioService();
        KeyPair keyPair = UsuarioService.leKeyPair();


        dificuldade = WebSocketService.sessionHandler.getDificuldade();

        if (dificuldade != null) {

            SecureRandom sr = new SecureRandom();
            BigInteger mNumber = new BigInteger(128, sr);

            PilacoinModel pilacoinModel = PilacoinModel.builder()
                    .dataCriacao(pilacoinModelColega.getDataCriacao())
                    .chaveCriador(pilacoinModelColega.getChaveCriador())
                    .idCriador(pilacoinModelColega.getIdCriador())
                    .nonce(pilacoinModelColega.getNonce())
                    .status(pilacoinModelColega.getStatus())
                    .build();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);


            String pilaJson = objectMapper.writeValueAsString(pilacoinModel);


            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(pilaJson.getBytes("UTF-8"));

            BigInteger numHash = new BigInteger(hash).abs();


            if (numHash.compareTo(dificuldade) < 0) {
                System.out.println("Pila validado");


            } else {
                System.out.println("Pila inválido");
            }


        }

        return false;
    }
}
