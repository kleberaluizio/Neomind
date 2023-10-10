package com.neomind.api_jakarta.repositories;

import com.neomind.api_jakarta.models.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class SupplierDao {

    private EntityManager em;

    public SupplierDao(EntityManager em){
        this.em = em;
    }

    // CREATE
    public void create(Supplier supplier){
        openConnection();
        this.em.persist(supplier);
        closeConnection();
    }

    // READ
    public Supplier findById(Long id){
        return this.em.find(Supplier.class, id);
    }

    public Supplier findByCnpj(String cnpj){
        String JPQL = "SELECT s FROM Supplier s WHERE s.cnpj = :cnpj";
        try {
            return em.createQuery(JPQL, Supplier.class)
                    .setParameter("cnpj", cnpj)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    public List<Supplier> findAll(){
        String JPQL = "SELECT s FROM Supplier s";
        return em.createQuery(JPQL).getResultList();
    }

    // UPDATE
    public void update(Supplier supplier){
        openConnection();
        this.em.merge(supplier);
        closeConnection();
    }

    // DELETE

    public void delete(Supplier supplier){
        openConnection();
        supplier = this.em.merge(supplier); // Managed
        this.em.remove(supplier);
        closeConnection();
    }

    public void openConnection(){
        em.getTransaction().begin();
    }
    public void closeConnection(){
        em.getTransaction().commit();
        em.close();
    }
}
