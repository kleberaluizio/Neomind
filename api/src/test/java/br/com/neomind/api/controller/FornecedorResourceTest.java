package br.com.neomind.api.controller;

import br.com.neomind.api.Entity.SupplierDTO;
import br.com.neomind.api.service.FornecedorService;
import jakarta.ws.rs.core.Response;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;


class FornecedorResourceTest {

    private FornecedorService fornecedorService;
    private FornecedorResource fornecedorResource;

    @BeforeEach
    void setUp() {
        fornecedorService = mock(FornecedorService.class);
        fornecedorResource = new FornecedorResource();
        fornecedorResource.setFornecedorService(fornecedorService);
    }

    @Test
    void create() {
        SupplierDTO supplierDTO = new SupplierDTO("Supplier Tester", "sup@gmail.com", "", "99.999/9999-00");
        when(fornecedorService.createSupplier(supplierDTO)).thenReturn(Response.status(Response.Status.CREATED).build());

        // Simula o comportamento do método create da classe FornecedorResource
        when(fornecedorResource.create(supplierDTO)).thenCallRealMethod();

        Response response = fornecedorResource.create(supplierDTO);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteFornecedor() {
    }
}