app.service('fornecedorService',['$http',function($http){
    const URL = "http://localhost:8080/fornecedor/";

    this.post = function(fornecedor){
        return $http.post(URL, fornecedor)
    }
    this.delete = function(id_fornecedor){
        return $http.delete(URL + id_fornecedor)
    };
    
    this.getAll = function(){
        return $http.get(URL)
    };
}]);
