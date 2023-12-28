app.service('fornecedorService',['$http',function($http){
    const URL = "http://localhost:8080/fornecedor/";

    this.create = function(fornecedor){
        return $http.post(URL, returnFornecedorObject(fornecedor))
    };

    this.readAll = function(){
        return $http.get(URL)
    };

    this.update = function(fornecedor, id){
        return $http.put(URL+id, fornecedor)
    };

    this.delete = function(id_fornecedor){
        return $http.delete(URL + id_fornecedor)
    };
    
    function returnFornecedorObject(fornecedor) {
        return {   
            name: fornecedor.name,
            email: fornecedor.email,
            comment: fornecedor?.comment,
            cnpj: fornecedor.cnpj
        };
    }
    
}]);