package br.ufsm.politecnico.csi.tapw.pila.servidor.repository;

import br.ufsm.politecnico.csi.tapw.pila.model.PilacoinModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Repository
public interface PilaRepository extends JpaRepository<PilacoinModel, Long>, JpaSpecificationExecutor<PilacoinModel> {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pilacoinFactory");
    EntityManager entityManager = factory.createEntityManager();
//    TypedQuery<PilacoinModel> pilacoinModel = entityManager.createQuery("SELECT * FROM pilacoinModel pi", PilacoinModel.class);

//    List<PilacoinModel> pilacoinModels = pilacoinModel.getResultList();
}
