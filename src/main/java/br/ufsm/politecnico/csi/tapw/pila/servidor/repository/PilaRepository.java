package br.ufsm.politecnico.csi.tapw.pila.servidor.repository;

import br.ufsm.politecnico.csi.tapw.pila.model.PilacoinModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface PilaRepository extends JpaRepository<PilacoinModel, Long>, JpaSpecificationExecutor<PilacoinModel> {

}
