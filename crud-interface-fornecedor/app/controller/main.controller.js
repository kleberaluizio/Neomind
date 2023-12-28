
app.controller('fornecedoresCtrl', function ($scope, $http, $location, fornecedorService) {

    //Initialization Variables
    $scope.tempFornecedor = [];
    $scope.buttonTitle = 'Cadastrar';
    
    
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

    $scope.prepareToUpdate = function(fornecedor){
        $scope.tempFornecedor = angular.copy(fornecedor);
        $scope.buttonTitle = 'Atualizar';
        document.getElementById("insertFornecedor").setAttribute("value","Update");
    }

    $scope.getAllFornecedores = function () {
        fornecedorService.readAll().then(function (response) {
            $scope.fornecedores = response.data;
        }, function () {
            swal("Não foi possível coletar os dados dos fornecedores, verifique sua conexão!", "", "warning");
        })
    };

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
        $scope.getAllFornecedores();
        new Cleave('.cnpj', {
            blocks: [2, 3, 4, 2],
            delimiters: ['.', '/', '-'],
            numericOnly: true
        });
    };
    $scope.init();
    
});

