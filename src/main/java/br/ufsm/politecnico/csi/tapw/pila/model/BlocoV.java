package br.ufsm.politecnico.csi.tapw.pila.model;

import java.util.List;

public class BlocoV {


    private Long id;

    private byte[] chaveMinerador;
    private String nonce;
    private byte[] hashBlocoAnterior;

    private List<TransacaoModel> transacoes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getChaveMinerador() {
        return chaveMinerador;
    }

    public void setChaveMinerador(byte[] chaveMinerador) {
        this.chaveMinerador = chaveMinerador;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public byte[] getHashBlocoAnterior() {
        return hashBlocoAnterior;
    }

    public void setHashBlocoAnterior(byte[] hashBlocoAnterior) {
        this.hashBlocoAnterior = hashBlocoAnterior;
    }

    public List<TransacaoModel> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<TransacaoModel> transacoes) {
        this.transacoes = transacoes;
    }
}
