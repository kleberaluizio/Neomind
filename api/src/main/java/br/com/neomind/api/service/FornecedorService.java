package br.com.neomind.api.service;

import br.com.neomind.api.model.Fornecedor;
import br.com.neomind.api.dao.FornecedorDAO;
import br.com.neomind.api.model.FornecedorDTO;
import br.com.neomind.api.util.JPAUtil;
import jakarta.ws.rs.core.Response;

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

    public Response createFornecedor(FornecedorDTO fornecedorDTO) {

        Fornecedor f = fornecedorDAO.findByCnpj(fornecedorDTO.getCnpj());

        if(f != null || fornecedorDTO.getCnpj() == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Operação inválida, o cnpj informado já existe ou é nulo!")
                    .build();
        }
        if (fornecedorDTO == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Operação inválida, campos em branco")
                    .build();
        }

        System.out.println(fornecedorDTO);
        Fornecedor novoFornecedor = new Fornecedor(fornecedorDTO);
        System.out.println(novoFornecedor);

        try {
            fornecedorDAO.create(novoFornecedor);
            return Response.status(Response.Status.CREATED).entity(novoFornecedor).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    public Response updateFornecedor(int id, Fornecedor fornecedor) {
        Fornecedor f = fornecedorDAO.findById(id);
        fornecedor.setId(id);

        if(f == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            fornecedorDAO.update(fornecedor);
            return Response.status(Response.Status.OK).entity(fornecedor).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

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
