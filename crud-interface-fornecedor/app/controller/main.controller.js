app.controller('SupplierController', function ($scope, $route, $location, fornecedorService, $document) {

    var self = this;

    self.createSupplier = function (form) {
        form.$submitted = true;
        if(!form.$valid){return;}
        fornecedorService.createSupplier(self.tempFornecedor).then(function (response) {
            swal("Fornecedor cadastrado com sucesso!", "", "success");
            executeAfterHttpRequest();
        })
        window.location.href = '#!/list-suppliers';
    }

    self.getAllSuppliers = function () {
        fornecedorService.getAllSuppliers().then(function (response) {
            self.fornecedores = response.data;
        }, function () {
            swal("Não foi possível coletar os dados dos fornecedores, verifique sua conexão!", "", "warning");
        })
    };

    self.updateSupplier = function (form) {
        form.$submitted = true;
        if(!form.$valid){return;}
        fornecedorService.updateSupplier(self.tempFornecedor, self.tempFornecedor.id).then(function (response) {
            swal("Fornecedor atualizado com sucesso!", "", "success");
            executeAfterHttpRequest();
        })
        window.location.href = '#!/list-suppliers';
    }

    self.deleteFornecedor = function (id_fornecedor) {
        fornecedorService.deleteSupplier(id_fornecedor).then(function (response) {
            swal("Exclusão realizada com sucesso!", "", "success");
            executeAfterHttpRequest();
        })
    }

    self.prepareToUpdate = function (fornecedor) {
        self.tempFornecedor = angular.copy(fornecedor);
        self.isSupplierToBeUpdated = true;
    }


    self.executeWhenButtonClicked = function () {
        self.cleanData();
        disableSupplierToBeUpdated();
    }

    function disableSupplierToBeUpdated() {
        self.isSupplierToBeUpdated = false;
    }

    self.cleanData = function () {
        self.tempFornecedor = {};
    }

    function executeAfterHttpRequest() {
        self.cleanData();
        self.getAllSuppliers();
    }


    self.initializeCleave = function () {
        $document.ready(function () {// Wait for the DOM to be ready
            new Cleave('.cnpj', {
                blocks: [2, 3, 4, 2],
                delimiters: ['.', '/', '-'],
                numericOnly: true
            });
        });
    };


    $scope.$on('$routeChangeSuccess', function () {
        let currentRoute = $route.current ? $route.current.$$route.originalPath : '';
        if (currentRoute === '/register') {
            self.initializeCleave();
        }
    });

    $scope.init = function () {
        disableSupplierToBeUpdated();
        self.tempFornecedor = {};
        self.getAllSuppliers();
    };
    $scope.init();
});

