var app = angular.module('fornecedor-neomind', []);

app.controller('fornecedoresCtrl', function ($scope, $http) {

    //Fazendo a requisição
    $http.get("http://localhost:8080/fornecedor").then(function (response) {
        //console.log(response.data);
        $scope.fornecedores = response.data;
    });

    //Fazendo a requisição dos dados da entidade fornecedor
    // $scope.getAllFornecedores = function () {
    //     $http({
    //         method: "get",
    //         url: "http://localhost:8080/fornecedor"
    //     }).then(function (response) {
    //         $scope.fornecedores = response.data;
    //     }, function () {
    //         alert("Ocorreu um erro no GET!");
    //     })
    // };

    // //Criando novo fornecedor
    // $scope.InsertFornecedor = function () {
    //     var type = document.getElementById("insertFornecedor").getAttribute("value")
    //     if (type == "submit") {
    //         $scope.fornecedor = [];
    //         $scope.fornecedor.name = $scope.novoFornecedor.name;
    //         $scope.fornecedor.email = [];
    //         $scope.fornecedor.comment = [];
    //         $scope.fornecedor.cpnj = [];
    //         $http({
    //             method: "post",
    //             url: "http://localhost:8080/fornecedor",
    //             datatype: "json",
    //             data: JSON.stringify($scope.fornecedor)
    //         }).then(function (response)) {
    //             alert(response.data);
    //             $scope.getAllFornecedores();
    //         }
    //     }
    // }


});

