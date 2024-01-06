app.controller('ValidationController', function ($scope, $route, $location, fornecedorService, $document) {
    var self = this;

    self.clearValidation = function(fieldName) {
        if(self.formulario[fieldName]){
        self.formulario[fieldName].$setValidity('required', true);
    }
    };
});