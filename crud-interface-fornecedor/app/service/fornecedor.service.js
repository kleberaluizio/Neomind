app.service('fornecedorService',['$http',function($http){
    const URL = "http://localhost:8080/fornecedor/";

    this.createSupplier = function(supplier){
        return $http.post(URL, returnSupplierObject(supplier))
    };

    this.getAllSuppliers = function(){
        return $http.get(URL)
    };

    this.updateSupplier = function(supplier, id){
        return $http.put(URL+id, supplier)
    };

    this.deleteSupplier = function(id_supplier){
        return $http.delete(URL + id_supplier)
    };
    
    function returnSupplierObject(supplier) {
        return {   
            name: supplier.name,
            email: supplier.email,
            comment: supplier?.comment,
            cnpj: supplier.cnpj
        };
    }
    
}]);
