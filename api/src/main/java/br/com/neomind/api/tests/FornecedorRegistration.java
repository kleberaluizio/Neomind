package br.com.neomind.api.tests;

import br.com.neomind.api.model.Fornecedor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FornecedorRegistration {

    public static void main(String[] args) {

        Fornecedor f = new Fornecedor();
        f.setName("fornec lorimospm");
        f.setEmail("fornec@loripsom");
        f.setComment("loreipsum");
        f.setCnpj("00.000/0000-00");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("neomind-fornecedor");

        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
        em.close();
    }
}
