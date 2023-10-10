package com.neomind.api_jakarta.services;

import com.neomind.api_jakarta.models.Supplier;
import com.neomind.api_jakarta.repositories.SupplierDao;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.regex.Pattern;

public class SupplierService {

    private SupplierDao supplierDao;

    public SupplierService(SupplierDao dao){
        this.supplierDao = dao;
    }
    public List<Supplier> getAll() {
        return supplierDao.findAll();
    }

    public Response create(Supplier supplier) {  // USAR DTO

        Supplier f = supplierDao.findByCnpj(supplier.getCnpj());

        if(f != null || supplier.getCnpj() == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Operação inválida, o cnpj informado já existe ou é nulo!")
                    .build();
        }
        if (supplier == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Operação inválida, campos em branco")
                    .build();
        }

        Supplier novoFornecedor = supplier;

        Boolean emailIsValid = Pattern.compile("^(.+)@(\\S+)$").matcher(novoFornecedor.getEmail()).matches();
        if(!emailIsValid){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Operação inválida, formato de e-mail inadequado!")
                    .build();
        }

        try {
            supplierDao.create(novoFornecedor);
            return Response.status(Response.Status.CREATED).entity(novoFornecedor).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
