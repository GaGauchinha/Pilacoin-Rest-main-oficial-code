package br.ufsm.politecnico.csi.tapw.pila.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;


@Entity
@Builder
@AllArgsConstructor
@ToString
public class PilacoinModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //    @Column(name = "username", nullable = false, unique = true)
    //    private String username;

    private String idCriador;
    private Date dataCriacao;
    private byte[] chaveCriador;
    private byte[] assinaturaMestre;
    private BigInteger nonce;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCriador() {
        return idCriador;
    }

    public void setIdCriador(String idCriador) {
        this.idCriador = idCriador;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }
    private String status;

    public void setDataCriacao(Date dataMineracao) {
        this.dataCriacao = dataCriacao;
    }

    public byte[] getChaveCriador() {
        return chaveCriador;
    }

    public void setChaveCriador(byte[] chaveCriador) {
        this.chaveCriador = chaveCriador;
    }

    public byte[] getAssinaturaMestre() {
        return assinaturaMestre;
    }

    public void setAssinaturaMestre(byte[] assinaturaMestre) {
        this.assinaturaMestre = assinaturaMestre;
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public void setNonce(BigInteger nonce) {
        this.nonce = nonce;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static final String VALIDACAO = "VALIDACAO";
    //public static final String AG_BLOCO = "AG_BLOCO";
    public static final String BLOCO_AVALIANDO = "PILA_AVALIANDO";
    public static final String VÁLIDO = "VÁLIDO";
    public static final String INVÁLIDO = "INVÁLIDO";
    public PilacoinModel(String idCriador, Date dataCriacao, byte[] chaveCriador,
                         byte[] assinaturaMestre, BigInteger nonce, String status) {
      this.idCriador = idCriador;
        this.dataCriacao = dataCriacao;
        this.chaveCriador = chaveCriador;
        this.assinaturaMestre = assinaturaMestre;
       this.nonce = nonce;
    }


}
