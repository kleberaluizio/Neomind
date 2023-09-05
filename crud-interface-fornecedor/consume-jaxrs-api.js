var app = angular.module('fornecedor-neomind', []);

app.controller('fornecedoresCtrl', function ($scope, $http) {

    //CREATE
    $scope.InsertFornecedor = function (Fname, Femail, Fcomment, Fcnpj,Fid) {
        var type = document.getElementById("insertFornecedor").getAttribute("value");

        var fornecedor = { name: Fname, email: Femail, comment: Fcomment, cnpj: Fcnpj};

        if(!$scope.formulario.$valid){
            return;
        }
        
        if (type == "submit") {
            $http({
                method: "post",
                url: "http://localhost:8080/fornecedor",
                datatype: "json",
                data: JSON.stringify(fornecedor)
            }).then(function (response) {
                swal("Fornecedor cadastrado com sucesso!", "", "success");
                $scope.getAllFornecedores();
                $scope.CleanData();
                document.getElementById("insertFornecedor").setAttribute("value","submit");
            })
        } else { //UPDATE 
            document.getElementById("insertFornecedor").setAttribute("value","submit");
            $http({
                method: "put",
                url: "http://localhost:8080/fornecedor/" + Fid,
                datatype: "json",
                data: JSON.stringify(fornecedor)
            }).then(function (response) {
                swal("Fornecedor atualizado com sucesso!", "", "success");
                $scope.getAllFornecedores();
                $scope.CleanData();
            })
        }
    }


    // READ
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

    //UPDATE 
    $scope.UpdateFornecedor = function(fornecedor){
        debugger;

        $scope.Fname = fornecedor.name;
        $scope.Femail= fornecedor.email;
        $scope.Fcomment= fornecedor.comment;
        $scope.Fcnpj= fornecedor.cnpj;
        $scope.Fid= fornecedor.id;
        console.log($scope.Fid);
        document.getElementById("insertFornecedor").setAttribute("value","Update");
    }

    // DELETE
    $scope.DeleteFornecedor = function(idvalue){

        var apiUrL = "http://localhost:8080/fornecedor/";
        $http({
            method: "delete",
            url: apiUrL + idvalue,
            datatype: "json",
            data: JSON.stringify($scope.fornecedor)
        }).then(function (response) {
            $scope.getAllFornecedores();
            swal("Exclusão realizada com sucesso!", "", "success");
            $scope.reloadRoute();
            $scope.Fname = " ";
            $scope.Femail= " ";
            $scope.Fcomment= " ";
            $scope.Fcnpj= " ";
            document.getElementById("deleteFornecedor").setAttribute("value","Delete")
        })
    }



    $scope.reloadRoute = function() {
        $window.location.reload();
     }


    $scope.CleanData = function(){
        document.getElementById("insertFornecedor").setAttribute("value","submit");
        $scope.Fname = " ";
        $scope.Femail= " ";
        $scope.Fcomment= " ";
        $scope.Fcnpj= " ";
        $scope.reloadRoute();
    }
  
});

