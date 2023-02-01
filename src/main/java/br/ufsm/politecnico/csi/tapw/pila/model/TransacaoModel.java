package br.ufsm.politecnico.csi.tapw.pila.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;

@Entity
public class TransacaoModel {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_pila")
    private PilacoinModel pilacoinModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public PilacoinModel getPilacoinModel() {
        return pilacoinModel;
    }

    public void setPilacoinModel(PilacoinModel pilacoinModel) {
        this.pilacoinModel = pilacoinModel;
    }
}
