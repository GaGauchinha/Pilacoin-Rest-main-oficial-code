package br.ufsm.politecnico.csi.tapw.pila.servidor.service;

import br.ufsm.politecnico.csi.tapw.pila.servidor.interfaces.PilaServiceInterface;
import br.ufsm.politecnico.csi.tapw.pila.model.PilacoinModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PilaService implements PilaServiceInterface {

    private static final String LISTA_PILAS
            = "http://srv-ceesp.proj.ufsm.br:8097/pilacoin/all";

    @Override
    public List<PilacoinModel> findAll() {
        List<PilacoinModel> pilas = null;
        List<PilacoinModel> pilaMeu = new ArrayList<PilacoinModel>();
        UsuarioService UsuarioService = new UsuarioService();
        KeyPair keyPair = UsuarioService.leKeyPair();

        ResponseEntity<String> resp = null;
        RestTemplate restTemplate = new RestTemplate();

        resp = restTemplate.getForEntity(LISTA_PILAS, String.class);

        if (resp.getStatusCode() == HttpStatus.OK) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                pilas = Arrays.asList(mapper.readValue(resp.getBody(),
                        PilacoinModel[].class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            for (PilacoinModel pilacoinModel : pilas) {
                String minhaChave = Base64.encodeBase64String(keyPair
                        .getPublic().getEncoded());
                String base64 = Base64.encodeBase64String(pilacoinModel
                        .getChaveCriador());
                if (base64.equals(minhaChave)) {
                    pilaMeu.add(pilacoinModel);
                }
            }
            System.out.println("Todos os pilas: " + pilas.size());
            System.out.println("Meus pilas: " + pilaMeu.size());
        }
        return pilaMeu;
    }
}


