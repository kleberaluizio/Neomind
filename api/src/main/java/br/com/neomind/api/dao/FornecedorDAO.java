package br.com.neomind.api.dao;

import br.com.neomind.api.Entity.Fornecedor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class FornecedorDAO {

    private EntityManager em;

    public FornecedorDAO(EntityManager em) {
        this.em = em;
    }

    //CREATE
    public void create(Fornecedor fornecedor){
        open();
        this.em.persist(fornecedor);
        close();
    }
    //READ
    public Fornecedor findById(int id){
        return this.em.find(Fornecedor.class,id);
    }

    public Fornecedor findByCnpj(String cnpj){
        String JPQL = "SELECT p FROM Fornecedor p WHERE p.cnpj = :cnpj";
        try {
            return em.createQuery(JPQL, Fornecedor.class)
                    .setParameter("cnpj", cnpj)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    public List<Fornecedor> findAll(){
        String JPQL = "SELECT p FROM Fornecedor p";
        return em.createQuery(JPQL).getResultList();
    }
    //UPDATE
    public void update(Fornecedor fornecedor){
        open();
        this.em.merge(fornecedor);
        close();
    }

    //DELETE
    public void delete(Fornecedor fornecedor){
        open();
        fornecedor = em.merge(fornecedor);
        this.em.remove(fornecedor);
        close();
    }

    public void open(){
        em.getTransaction().begin();
    }
    public void close(){
        em.getTransaction().commit();
        em.close();
    }


}
