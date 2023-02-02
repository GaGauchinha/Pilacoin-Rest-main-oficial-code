package br.ufsm.politecnico.csi.tapw.pila.servidor.repository;

import br.ufsm.politecnico.csi.tapw.pila.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer>, JpaSpecificationExecutor<UsuarioModel> {
}