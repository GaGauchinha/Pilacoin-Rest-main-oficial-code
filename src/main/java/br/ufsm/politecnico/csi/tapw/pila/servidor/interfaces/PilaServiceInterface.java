package br.ufsm.politecnico.csi.tapw.pila.servidor.interfaces;

import br.ufsm.politecnico.csi.tapw.pila.model.PilacoinModel;

import java.util.List;

public interface PilaServiceInterface {
    List<PilacoinModel> findAll();

}
