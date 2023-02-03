package br.ufsm.politecnico.csi.tapw.pila.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EntityManagerController {
    private static final EntityManagerController INSTANCE = new EntityManagerController();

    private final EntityManagerFactory factory;

    private EntityManagerController() {
        this.factory = Persistence.createEntityManagerFactory("pilacoin");
    }

    public static EntityManagerController getInstance() {
        return INSTANCE;
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }

    public EntityManager createManager() {
        return factory.createEntityManager();
    }
}
