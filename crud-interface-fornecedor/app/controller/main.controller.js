
app.controller('fornecedoresCtrl', function ($scope, $http, $location, fornecedorService) {

    $scope.tempFornecedor = [];
    $scope.buttonTitle = 'Cadastrar';
    //CREATE
    $scope.createOrUpdateFornecedor = function () {
        var type = document.getElementById("insertFornecedor").getAttribute("value");

        if(!$scope.formulario.$valid){
            return;
        }
        
        if (type == "submit") {
            fornecedorService.create($scope.tempFornecedor).then(function (response) {
                $scope.getAllFornecedores();
                swal("Fornecedor cadastrado com sucesso!", "", "success");   
            })
        } else { 
            fornecedorService.update($scope.tempFornecedor,$scope.tempFornecedor.id).then(function (response) {
                $scope.getAllFornecedores();
                swal("Fornecedor atualizado com sucesso!", "", "success"); 
            })
        }

        $scope.cleanData();
        document.getElementById("insertFornecedor").setAttribute("value","submit");
    }

    // REFATORADO ABAIXO
    $scope.prepareToUpdate = function(fornecedor){
        
        $scope.tempFornecedor = angular.copy(fornecedor);
        console.log('preparing to update')
        console.log($scope.tempFornecedor)
        $scope.buttonTitle = 'Atualizar';
        console.log($scope.buttonTitle)
        document.getElementById("insertFornecedor").setAttribute("value","Update");
    }


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
        $scope.tempFornecedor = [];
        $scope.buttonTitle = 'Enviar';
    }


    $scope.init = function(){
        new Cleave('.cnpj', {
            blocks: [2, 3, 4, 2],
            delimiters: ['.', '/', '-'],
            numericOnly: true
        });
    };
    $scope.init();
    
});

