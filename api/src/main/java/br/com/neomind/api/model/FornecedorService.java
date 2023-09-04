package br.com.neomind.api.model;

import br.com.neomind.api.util.JPAUtil;
import jakarta.ws.rs.core.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class FornecedorService {


    private FornecedorDAO fornecedorDAO= new FornecedorDAO(JPAUtil.getEntityManager());

    public List<Fornecedor> getAllFornecedores() {
        return fornecedorDAO.findAll();
    }

    public Response getFornecedorById(int id) {

        Fornecedor f = fornecedorDAO.findById(id);
        if(f == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(f).build();
    }

    public Response createFornecedor(Fornecedor fornecedor) {

        //
        Fornecedor f = fornecedorDAO.findByCnpj(fornecedor.getCnpj());

        if(f != null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("O cnpj informado já existe em nosso banco de dados")
                    .build();
        }
        if (fornecedor == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Operação inválida, o cnpj informado já existe!")
                    .build();
        }

        try {
            fornecedorDAO.create(fornecedor);
            return Response.status(Response.Status.CREATED).entity(fornecedor).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    public Response updateFornecedor(int id, Fornecedor fornecedor) {
        Fornecedor f = fornecedorDAO.findById(id);

        if(f == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            fornecedor.setId(id);
            fornecedorDAO.update(fornecedor);
            return Response.status(Response.Status.OK).entity(fornecedor).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    //test
    public Response updateFornecedor(Fornecedor fornecedor) {
        Fornecedor f = fornecedorDAO.findByCnpj(fornecedor.getCnpj());

        if(f == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            fornecedor.setId(f.getId());
            fornecedorDAO.update(fornecedor);
            return Response.status(Response.Status.OK).entity(fornecedor).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    public Response deleteFornecedorById(int id) {
        Fornecedor f = fornecedorDAO.findById(id);

        if (f == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            fornecedorDAO.delete(f);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
