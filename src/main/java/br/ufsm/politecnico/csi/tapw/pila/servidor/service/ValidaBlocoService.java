package br.ufsm.politecnico.csi.tapw.pila.servidor.service;

import br.ufsm.politecnico.csi.tapw.pila.model.BlocoModel;
import br.ufsm.politecnico.csi.tapw.pila.model.BlocoV;
import br.ufsm.politecnico.csi.tapw.pila.model.PilacoinModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.net.URL;
import java.net.http.HttpHeaders;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class ValidaBlocoService {

    public static BigInteger dificuldade = BigInteger.ZERO;
    public static KeyPair keyPair;

    @SneakyThrows
    public static boolean ValidaBloco(BlocoModel blocoModel) {

        UsuarioService UsuarioService = new UsuarioService();
        KeyPair keyPair = UsuarioService.leKeyPair();


        BlocoV blocoValidado = new BlocoV();
        blocoValidado.setHashBlocoAnterior(blocoModel.getHashBlocoAnterior());
        blocoValidado.setId(blocoModel.getId());
        blocoValidado.setTransacoes(blocoModel.getTransacoes());
        blocoValidado.setChaveMinerador(keyPair.getPublic().getEncoded());
        ObjectMapper objectMapper = new ObjectMapper();

        dificuldade = WebSocketService.sessionHandler.getDificuldade();

        while (true){

            SecureRandom sr = new SecureRandom();
            BigInteger mNumber = new BigInteger(128, sr);

            blocoValidado.setNonce(mNumber.toString());

            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            String blocoJson = objectMapper.writeValueAsString(blocoModel);


            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(blocoJson.getBytes("UTF-8"));

            BigInteger numHash = new BigInteger(hash).abs();


            if (numHash.compareTo(dificuldade) < 0) {
                System.out.println("Bloco Minerado e validado");
            } else {
                System.out.println("Bloco inválido");
            }
        }

        HttpHeaders headers;
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = null;
        String blocoJson = objectMapper.writeValueAsString(blocoModel);

        try{
            RequestEntity<String> requestEntity = RequestEntity.post(new URL(
                            "http://"+ "srv-ceesp.proj.ufsm.br:8097" + "/bloco/").toURI())
                    .contentType(MediaType.APPLICATION_JSON).body(blocoJson);
            resp = restTemplate.exchange(requestEntity, String.class);

            if (resp.getStatusCode() == HttpStatus.OK){
                System.out.println("POSTOU COM SUCESSO");
            }
        }
        catch(Exception e){
            System.out.println("ERRO AO VALIDAR " + e.getMessage());
            e.printStackTrace();
        }
}
}
