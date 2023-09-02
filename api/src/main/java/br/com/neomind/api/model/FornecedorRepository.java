package br.com.neomind.api.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FornecedorRepository {
    private final static HashMap<Integer, Fornecedor> fornecedores = new HashMap<>();

    public List<Fornecedor> getAll(){
        return new ArrayList<Fornecedor>(fornecedores.values());
    }

    public Fornecedor get(final int id) {
        return fornecedores.get(id);
    }

    public void add(final Fornecedor fornecedor) {
        if(fornecedor.getId() == 0 )
            fornecedor.setId(generateId(fornecedores.size() + 1));
        fornecedores.put(fornecedor.getId(), fornecedor);
    }

    public void edit(final Fornecedor fornecedor) {
        fornecedores.remove(fornecedor.getId());
        fornecedores.put(fornecedor.getId(), fornecedor);
    }

    public void delete(final int id) {
        fornecedores.remove(id);
    }

    private int generateId(final int possible)
    {
        if(fornecedores.containsKey(possible))
            return generateId(possible + 1);
        return possible;
    }

}
