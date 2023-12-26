
app.controller('fornecedoresCtrl', function ($scope, $http,fornecedorService) {

    $scope.tempFornecedor = 1;
    $scope.buttonTitle = 'Enviar';
    //CREATE
    $scope.InsertFornecedor = function (Fname, Femail, Fcomment, Fcnpj,Fid) {
        var type = document.getElementById("insertFornecedor").getAttribute("value");

        var fornecedor = { 
            name: Fname, 
            email: Femail, 
            comment: Fcomment, 
            cnpj: Fcnpj};

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
                document.getElementById("insertFornecedor").setAttribute("value","submit");
                cleanData();
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
                document.getElementById("insertFornecedor").setAttribute("value","submit");
                cleanData();
            })
        }
    }


    
   
    //UPDATE 
    $scope.UpdateFornecedor = function(fornecedor){

        $scope.Fname = fornecedor.name;
        $scope.Femail= fornecedor.email;
        $scope.Fcomment= fornecedor.comment;
        $scope.Fcnpj= fornecedor.cnpj;
        $scope.Fid= fornecedor.id;
        console.log($scope.Fid);
        document.getElementById("insertFornecedor").setAttribute("value","Update");
    }
    $scope.prepareToUpdate = function(){
        
    }

    // REFATORADO ABAIXO

    // READ
    $scope.getAllFornecedores = function () {
        fornecedorService.getAll().then(function (response) {
            $scope.fornecedores = response.data;
        }, function () {
            swal("Não foi possível coletar os dados dos fornecedores, verifique sua conexão!", "", "warning");
        })
    };

    // DELETE
    $scope.deleteFornecedor = function(id_fornecedor){
        fornecedorService.delete(id_fornecedor).then(function (response) {
            $scope.getAllFornecedores();
            swal("Exclusão realizada com sucesso!", "", "success");
            cleanData();
        })
    }
    
    function cleanData(){
        $scope.Fname = " ";
        $scope.Femail= " ";
        $scope.Fcomment= " ";
        $scope.Fcnpj= " ";
    }

  
});

