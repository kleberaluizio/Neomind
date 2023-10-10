package com.neomind.api_jakarta.controllers;

import com.neomind.api_jakarta.models.Supplier;
import com.neomind.api_jakarta.services.SupplierService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.print.attribute.standard.Media;
import java.awt.image.RescaleOp;
import java.util.List;

@Path("suppliers")
public class SupplierResource {

    private SupplierService supplierService;

    @Inject
    public SupplierResource(SupplierService supplierService){
        this.supplierService = supplierService;
    }

    // CREATE
    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSupplier(@PathParam("id") Long id,@Valid Supplier supplier){
        return supplierService.create(supplier);
    }

    // READ
    @GET
    public Response getAllSuppliers(){
        List<Supplier> suppliers = supplierService.getAll();
        return Response
                .status(Response.Status.OK)
                .entity(suppliers)
                .build();
    }

}
