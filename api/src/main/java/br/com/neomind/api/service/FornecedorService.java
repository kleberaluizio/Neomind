package br.com.neomind.api.service;

import br.com.neomind.api.Entity.Fornecedor;
import br.com.neomind.api.dao.FornecedorDAO;
import br.com.neomind.api.Entity.SupplierDTO;
import br.com.neomind.api.util.JPAUtil;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class FornecedorService {

    private FornecedorDAO supplierDAO = new FornecedorDAO(JPAUtil.getEntityManager());

    public Response createSupplier(SupplierDTO supplierDTO) {

        Fornecedor f = supplierDAO.findByCnpj(supplierDTO.getCnpj());
        if(f != null){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: Cpnj supplier already register in our system.")
                    .build();
        }
        Fornecedor fornecedor = convertDTOtoEntity(supplierDTO);
        try {
            supplierDAO.create(fornecedor);
            return Response.status(Response.Status.CREATED).entity(fornecedor).build();
        } catch (Exception e){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    public List<Fornecedor> getAllSuppliers() {
        return supplierDAO.findAll();
    }

    public Response getSupplierById(int id) {

        Fornecedor f = supplierDAO.findById(id);
        if(f == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(f).build();
    }

    public Response updateSupplier(int id, SupplierDTO supplierDTO) {

        Fornecedor supplier = supplierDAO.findById(id);

        if(supplier == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Fornecedor updatedSupplier = convertDTOtoEntity(supplierDTO);
        updatedSupplier.setId(id);

        if(updatedSupplier.equals(supplier)){
            return Response.status(Response.Status.OK).entity(updatedSupplier).build();
        }

        try {
            supplierDAO.update(updatedSupplier);
            return Response.status(Response.Status.OK).entity(updatedSupplier).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    public Response deleteSupplierById(int id) {
        Fornecedor f = supplierDAO.findById(id);

        if (f == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            supplierDAO.delete(f);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    private Fornecedor convertDTOtoEntity(SupplierDTO supplierDTO) {

        Fornecedor supplier = new Fornecedor();

        supplier.setName(supplierDTO.getName());
        supplier.setEmail(supplierDTO.getEmail());
        supplier.setComment(supplierDTO.getComment());
        supplier.setCnpj(supplierDTO.getCnpj());

        return supplier;
    }
}
