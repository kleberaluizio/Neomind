
app.controller('SupplierController', function ($scope, $http, $location, fornecedorService) {

    //Initialization Variables
    var self = this;
    $scope.tempFornecedor = [];
    $scope.buttonTitle = 'Cadastrar';
    
    
    $scope.createOrUpdateFornecedor = function () {
        var type = document.getElementById("insertFornecedor").getAttribute("value");

        if(!$scope.formulario.$valid){
            return;
        }
        
        if (type == "submit") {
            fornecedorService.createSupplier($scope.tempFornecedor).then(function (response) {
                $scope.getAllFornecedores();
                swal("Fornecedor cadastrado com sucesso!", "", "success");   
            })
        } else { 
            fornecedorService.updateSupplier($scope.tempFornecedor,$scope.tempFornecedor.id).then(function (response) {
                $scope.getAllFornecedores();
                swal("Fornecedor atualizado com sucesso!", "", "success"); 
            })
        }

        self.cleanData();
        document.getElementById("insertFornecedor").setAttribute("value","submit");
    }

    self.prepareToUpdate = function(fornecedor){
        debugger;
        $scope.tempFornecedor = angular.copy(fornecedor);
        $scope.buttonTitle = 'Atualizar';
        document.getElementById("insertFornecedor").setAttribute("value","Update");
    }

    $scope.getAllFornecedores = function () {
        fornecedorService.getAllSuppliers().then(function (response) {
            $scope.fornecedores = response.data;
        }, function () {
            swal("Não foi possível coletar os dados dos fornecedores, verifique sua conexão!", "", "warning");
        })
    };

    self.deleteFornecedor = function(id_fornecedor){
        fornecedorService.deleteSupplier(id_fornecedor).then(function (response) {
            $scope.getAllFornecedores();
            swal("Exclusão realizada com sucesso!", "", "success");
            self.cleanData();
        })
    }
    
    self.cleanData = function (){
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

