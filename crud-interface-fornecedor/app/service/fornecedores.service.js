app.service('fornecedorService',['$http',function($http){
    const URL = "http://localhost:8080/fornecedor/";

    this.create = function(fornecedor){
        return $http.post(URL, returnObject(fornecedor))
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
    
    function returnObject(fornecedor) {
        return {   
            name: fornecedor.name,
            email: fornecedor.email,
            comment: fornecedor?.comment,
            cnpj: fornecedor.cnpj
        };
    }
    
}]);
