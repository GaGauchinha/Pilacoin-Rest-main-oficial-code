package br.ufsm.politecnico.csi.tapw.pila.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class BlocoModel {

    @Id
    @GeneratedValue
    private Long id;
    private Long nonce;
    private byte[] hashBlocoAnterior;
    @OneToMany(mappedBy = "bloco")
    private List<TransacaoModel> transacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNonce() {
        return nonce;
    }

    public void setNonce(Long nonce) {
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
