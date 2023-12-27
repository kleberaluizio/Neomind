
app.controller('fornecedoresCtrl', function ($scope, $http,fornecedorService) {

    $scope.tempFornecedor = [];
    $scope.buttonTitle = 'Cadastrar';
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
            fornecedorService.create(fornecedor).then(function (response) {
                $scope.getAllFornecedores();
                swal("Fornecedor cadastrado com sucesso!", "", "success");   
            })
        } else { //UPDATE 
            fornecedorService.update(fornecedor,Fid).then(function (response) {
                $scope.getAllFornecedores();
                swal("Fornecedor atualizado com sucesso!", "", "success");
                
            })
        }

        $scope.cleanData();
        document.getElementById("insertFornecedor").setAttribute("value","submit");
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
    $scope.prepareToUpdate = function(fornecedor){
        $scope.Fname = fornecedor.name;
        $scope.Femail= fornecedor.email;
        $scope.Fcomment= fornecedor.comment;
        $scope.Fcnpj= fornecedor.cnpj;
        $scope.Fid= fornecedor.id;
        $scope.buttonTitle = 'Atualizar';
        document.getElementById("insertFornecedor").setAttribute("value","Update");
    }

    // REFATORADO ABAIXO

    // READ
    $scope.getAllFornecedores = function () {
        fornecedorService.readAll().then(function (response) {
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
            $scope.cleanData();
        })
    }
    
    $scope.cleanData = function (){
        $scope.Fname = " ";
        $scope.Femail= " ";
        $scope.Fcomment= " ";
        $scope.Fcnpj= " ";
        $scope.buttonTitle = 'Enviar';
    }

  
});

