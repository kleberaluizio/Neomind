package br.com.neomind.api.controller;

import br.com.neomind.api.Entity.Fornecedor;
import br.com.neomind.api.Entity.SupplierDTO;
import br.com.neomind.api.service.FornecedorService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("fornecedor")
public class FornecedorResource {

    private FornecedorService fornecedorService = new FornecedorService();

    //CREATE
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid SupplierDTO SupplierDTO){
        return fornecedorService.createSupplier(SupplierDTO);
    }

    //READ
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<Fornecedor> fornecedores = fornecedorService.getAllSuppliers();
        return Response.status(Response.Status.OK).entity(fornecedores).build();
    }
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id){
        return fornecedorService.getSupplierById(id);
    }

    //UPDATE
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, SupplierDTO supplierDTO){
        return fornecedorService.updateSupplier(id, supplierDTO);
    }

    //DELETE
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFornecedor(@PathParam("id") int id) {
        return fornecedorService.deleteSupplierById(id);
    }

}
