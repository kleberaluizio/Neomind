
var uId =0;

angular.module("fornecedor-neomind", [])
        .controller("fornecedoresCtrl",["$scope", fornecedoresCtrl]);

function fornecedoresCtrl ($scope) {
    $scope.novoFornecedor = null;
    $scope.fornecedores = [];

    $scope.saveFornecedor = function(){
        if ($scope.novoFornecedor.id == null){
            $scope.fornecedores.pus
        }
    }
}


var app = angular.module('fornecedor-neomind', []);

app.controller('fornecedoresCtrl', function ($scope, $http) {

    //Fazendo a requisição
    $http.get("http://localhost:8080/fornecedor").then(function (response) {
        //console.log(response.data);
        $scope.fornecedores = response.data;
    });


