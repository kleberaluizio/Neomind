var app = angular.module('fornecedor-neomind', ['ngRoute', 'ngMessages']);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/list-suppliers', {
        templateUrl: 'app/components/fornecedores.template.html',
      })
      .when('/register', {
        templateUrl: 'app/components/cadastrar.template.html',
      })
      .otherwise({ redirectTo: '/list-suppliers' });
  }]);

