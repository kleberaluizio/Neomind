package br.com.neomind.api.infra;

import br.com.neomind.api.model.Fornecedor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO<E>{
    private static EntityManagerFactory factory;
    private EntityManager em;
    private Class<E> entity;

    static {
        try {
            factory = Persistence.createEntityManagerFactory("fornecedor-neomind");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //CREATE
    public DAO<E> create(E entidade) {
        em.persist(entidade);
        return this;
    }
    //READ
    public E findById(Object id){
        return em.find(entity,id);
    }
    //UPDATE
    public Fornecedor update(int id, String name, String email, String comment, String cnpj) {
        DAO<E> dao = new DAO<E>(entity);
        dao.open();

        Fornecedor fornecedor = (fornecedor) dao.findById(id);
        fornecedor.setName(name);
        fornecedor.setEmail(email);
        fornecedor.setComment(comment);
        fornecedor.setCnpj(cnpj);
        em.merge(fornecedor);

        dao.close();

        return fornecedor;
    }

    //DELETE
    public DAO<E> delete(int id) {
        DAO<E> dao = new DAO<E>(entity);
        E fornecedorFounded = dao.findById(id);
        em.remove(em.contains(fornecedorFounded) ? fornecedorFounded : em.merge(fornecedorFounded));
        return this;
    }
    public DAO(Class<E> entity) {
        this.entity = entity;
        em = factory.createEntityManager();
    }


    public DAO<E> open(){
        em.getTransaction().begin();
        return this;
    }
    public DAO<E> close(){
        em.getTransaction().commit();
        return this;
    }

}
