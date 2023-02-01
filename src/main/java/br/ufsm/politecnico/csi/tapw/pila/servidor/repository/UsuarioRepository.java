package br.ufsm.politecnico.csi.tapw.pila.servidor.repository;

import br.ufsm.politecnico.csi.tapw.pila.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
//
//    @Query(value = "SELECT *  FROM  usuario WHERE username = ?1", nativeQuery = true)
//    UsuarioModel findByUsername(String username);
}