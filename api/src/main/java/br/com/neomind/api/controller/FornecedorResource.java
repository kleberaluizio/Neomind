package br.com.neomind.api.controller;

import br.com.neomind.api.model.Fornecedor;
import br.com.neomind.api.model.FornecedorRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("fornecedor")
public class FornecedorResource {

    private FornecedorRepository fornecedorRepository= new FornecedorRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){

        List<Fornecedor> fornecedores = fornecedorRepository.getAll();
        return Response.status(Response.Status.OK).entity(fornecedores).build();

    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id){

        Fornecedor f = fornecedorRepository.get(id);
        if(f == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(f).build();
    }

    // CRIAÇÃO DOS DADOS
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFornecedor(Fornecedor fornecedor){

        try {
            fornecedorRepository.add(fornecedor);
            return Response.status(Response.Status.CREATED).entity(fornecedor).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    // ATUALIZAÇÃO DOS DADOS
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFornecedor(@PathParam("id") int id, Fornecedor fornecedor){

        Fornecedor f = fornecedorRepository.get(id);

        if(f == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            fornecedor.setId(id);
            fornecedorRepository.edit(fornecedor);
            return Response.status(Response.Status.OK).entity(fornecedor).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFornecedor(@PathParam("id") int id) {

        Fornecedor f = fornecedorRepository.get(id);

        if (f == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            fornecedorRepository.delete(id);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
