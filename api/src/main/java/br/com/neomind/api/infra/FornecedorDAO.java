package br.com.neomind.api.infra;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FornecedorDAO {

    private EntityManager em;
    private EntityManagerFactory factory;

    public FornecedorDAO(String persistenceUnitName){
        this.factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        this.em = factory.createEntityManager();
    }

    

//        em.getTransaction().begin();
//        em.persist(f);
//        em.getTransaction().commit();
//        em.close();
}
