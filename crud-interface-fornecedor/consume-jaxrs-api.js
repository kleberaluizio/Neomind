var app = angular.module('fornecedor-neomind', []);

app.controller('fornecedoresCtrl', function ($scope, $http) {

    //Fazendo a requisição
    // $http.get("http://localhost:8080/fornecedor").then(function (response) {
    //     //console.log(response.data);
    //     $scope.fornecedores = response.data;
    // });

    //Fazendo a requisição dos dados da entidade fornecedor
    $scope.getAllFornecedores = function () {
        $http({
            method: "get",
            url: "http://localhost:8080/fornecedor"
        }).then(function (response) {
            $scope.fornecedores = response.data;
        }, function () {
            alert("Ocorreu um erro no GET!");
        })
    };
    // Excluindo Fornecedor
    $scope.DeleteFornecedor = function(value){
        var apiUrL = "http://localhost:8080/fornecedor/";
        $http({
            method: "delete",
            url: apiUrL + value,
            datatype: "json",
            data: JSON.stringify($scope.fornecedor)
        }).then(function (response) {
            alert("Exclusão realizada com sucesso!");
            $scope.getAllFornecedores();
            $scope.reloadRoute();
            $scope.Fname = " ";
            $scope.Femail= " ";
            $scope.Fcomment= " ";
            $scope.Fcnpj= " ";
            document.getElementById("deleteFornecedor").setAttribute("value","Delete")
        })
    }
    // Criando novo fornecedor 



    $scope.reloadRoute = function() {
        $window.location.reload();
     }








    //Criando novo fornecedor e atualizando existente
    $scope.InsertFornecedor = function (Fname, Femail, Fcomment, Fcnpj) {
        var type = document.getElementById("insertFornecedor").getAttribute("value");
        if (type == "submit") {
            var newfornecedor = {
                name: Fname,
                email: Femail,
                comment: Fcomment,
                cnpj: Fcnpj
            }
            // $scope.fornecedor.name = Fname;
            // $scope.fornecedor.email = Femail;
            // $scope.fornecedor.comment = Fcomment;
            // $scope.fornecedor.cpnj = Fcnpj;
            $http({
                method: "post",
                url: "http://localhost:8080/fornecedor",
                datatype: "json",
                data: JSON.stringify(newfornecedor)
            }).then(function (response) {
                alert(response.data);
                $scope.getAllFornecedores();
                $scope.Fname = " ";
                $scope.Femail= " ";
                $scope.Fcomment= " ";
                $scope.Fcnpj= " ";
                document.getElementById("insertFornecedor").setAttribute("value","Submit");
            })
        } else {
            $scope.fornecedor = [];
            $scope.fornecedor.name = $scope.Fname;
            $scope.fornecedor.email = $scope.Femail;
            $scope.fornecedor.comment = $scope.Fcomment;
            $scope.fornecedor.cnpj = $scope.Fcnpj;
            $http({
                method: "put",
                url: "http://localhost:8080/fornecedor/"+$scope.fornecedor.id,
                datatype: "json",
                data: JSON.stringify($scope.fornecedor)
            }).then(function (response) {
                alert(response.data);
                $scope.getAllFornecedores();
                $scope.Fname = " ";
                $scope.Femail= " ";
                $scope.Fcomment= " ";
                $scope.Fcnpj= " ";
                document.getElementById("insertFornecedor").setAttribute("value","Submit");
            })
        }
    }

    // $scope.CleanData = function(){
    //     $scope.Fname = " ";
    //     $scope.Femail= " ";
    //     $scope.Fcomment= " ";
    //     $scope.Fcnpj= " ";
    //     $scope.getAllFornecedores();

    // }

    // $scope.UpdateFornecedor() = function(fornecedor){
    //     debugger;

    //     $scope.Fname = $scope.fornecedor.name;
    //     $scope.Femail= $scope.fornecedor.email;
    //     $scope.Fcomment= $scope.fornecedor.comment;
    //     $scope.Fcnpj= $scope.fornecedor.cpnj;
    //     document.getElementById("insertFornecedor").setAttribute("value","Update");
    // }




});

