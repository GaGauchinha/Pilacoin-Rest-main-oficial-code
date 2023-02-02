package br.ufsm.politecnico.csi.tapw.pila.servidor.service;

import br.ufsm.politecnico.csi.tapw.pila.model.PilacoinModel;
import br.ufsm.politecnico.csi.tapw.pila.servidor.repository.PilaRepository;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.security.*;
import java.util.Date;

@Service
public class MineracaoService  {

    @Autowired(required = false)
    PilaRepository pilaRepository;

    public static BigInteger dificuldade = BigInteger.ZERO;
    private static final String REGISTRA_MEU_PILA
            = "http://srv-ceesp.proj.ufsm.br:8097/pilacoin/";

    private static final String VALIDA_MEU_PILA
            = "http://srv-ceesp.proj.ufsm.br:8097/pilacoin/?nonce=";

//    @Value("${endereco.server}")
//    private static String enderecoServer;

    @SneakyThrows
    public void initPilacoin (boolean minerar) {
       UsuarioService UsuarioService = new UsuarioService();
        KeyPair keyPair =UsuarioService.leKeyPair();
        while (minerar){
            dificuldade = WebSocketService.sessionHandler.getDificuldade();

            dificuldade = WebSocketService.sessionHandler.getDificuldade();
            PublicKey publicKey = UsuarioService.getPublicKey();

            if(dificuldade != null){

                SecureRandom sr = new SecureRandom();
                BigInteger mNumber = new BigInteger(128, sr);

                PilacoinModel pilacoinModel = PilacoinModel.builder()
                        .dataCriacao(new Date())
                        .chaveCriador(keyPair.getPublic().getEncoded())
                        .idCriador("Gabi")
                        .nonce(new BigInteger(128, sr).abs())
                        .status(PilacoinModel.VALIDACAO)
                        .build();

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);


                String pilaJson = objectMapper.writeValueAsString(pilacoinModel);


                MessageDigest md = MessageDigest.getInstance("SHA-256");

                byte[] hash = md.digest(pilaJson.getBytes("UTF-8"));

                BigInteger numHash = new BigInteger(hash).abs();


                if (numHash.compareTo(dificuldade) < 0) {
                    System.out.println(Base64.encodeBase64String(keyPair
                            .getPublic().getEncoded()));
                    System.out.println("Minerou");
                    System.out.println("Numhash:" +numHash);
                    System.out.println("Dificuldade:" +dificuldade);

                    registrarPila(pilaJson , pilacoinModel.getNonce());

                }
            }
        }

        }

    @SneakyThrows
    private void registrarPila(String pilaJson, BigInteger nonce){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PilacoinModel> resp = null;


        try {
            RequestEntity<String> requestEntity = RequestEntity.post(new URL(
                            REGISTRA_MEU_PILA).toURI())
                    .contentType(MediaType.APPLICATION_JSON).body(pilaJson);
            resp = restTemplate.exchange(requestEntity, PilacoinModel.class);
            if (resp.getStatusCode() == HttpStatus.OK){
                System.out.println("ENVIOU PRO SERVER");
                validarPila(String.valueOf(nonce), resp.getBody());
            }
        } catch (Exception e) {
            System.out.println("Erro ao registrar pila: "+e.getMessage());
        }
}


    @SneakyThrows
    public void validarPila(String nonce, PilacoinModel pilacoinModel){
        ResponseEntity<String> resp = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            //get nos meus pilas
            resp= restTemplate.getForEntity(VALIDA_MEU_PILA+
                    nonce, String.class);

            if (resp.getStatusCode() == HttpStatus.OK){
                System.out.println("Pila validado com sucesso!!! (●'◡'●)");
                File arquivo;
                FileWriter escritorArquivo;
                BufferedWriter escritorBuffer;

                try {

                    this.pilaRepository.save(pilacoinModel);

                    arquivo = new File("meus_pilas.txt");
                    //gera o log do meu pila
                    escritorArquivo = new FileWriter(arquivo, true);
                    escritorBuffer = new BufferedWriter(escritorArquivo);

                    escritorBuffer.write(resp.getBody().toString() + "\n");
                    escritorBuffer.newLine();

                    escritorBuffer.flush();
                    escritorArquivo.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            System.out.println("Pila invalido: "+e.getMessage());
        }
    }


}
