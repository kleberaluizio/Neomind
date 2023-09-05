var app = angular.module('fornecedor-neomind', []);

app.controller('fornecedoresCtrl', function ($scope, $http) {

    //CREATE
    $scope.InsertFornecedor = function (Fname, Femail, Fcomment, Fcnpj) {
        var type = document.getElementById("insertFornecedor").getAttribute("value");

        var fornecedor = { name: Fname, email: Femail, comment: Fcomment, cnpj: Fcnpj};

        $scope.VerificarCnpj(fornecedor);
        
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
                url: "http://localhost:8080/fornecedor/",
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
        document.getElementById("insertFornecedor").setAttribute("value","Update");
    }

    // DELETE
    $scope.DeleteFornecedor = function(idvalue){

        // $scope.ConfirmarExclusao();

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

    $scope.VerificarCnpj = function(fornecedor){

        if(fornecedor.cnpj == null){
            swal("", "O cpnj deve ser informado!", "warning");
            $scope.Fname = fornecedor.name;
            $scope.Femail= fornecedor.email;
            $scope.Fcomment= fornecedor.comment;
            $scope.Fcnpj= fornecedor.cnpj;
            return;
        }
    }

    $scope.ConfirmarExclusao = function(){

        swal({
            title: "Tem certeza?",
            text: "Não será possível reaver as informações apagadas!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
          })
          .then((willDelete) => {
            if (willDelete) {
              swal("Exclusão realizada com sucesso!", {
                icon: "success",
              });
            } else {
              return;
            }
          });
    }
  
});

