package br.ufsm.politecnico.csi.tapw.pila.servidor.repository;

import br.ufsm.politecnico.csi.tapw.pila.model.BlocoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlocoRepository extends JpaRepository<BlocoModel, Long>, JpaSpecificationExecutor<BlocoModel> {
}