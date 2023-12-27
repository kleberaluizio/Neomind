var app = angular.module('fornecedor-neomind', ['ngRoute']);

app.config(['$routeProvider',function($routeProvider){//Config method is a function that will fire before your application runs.
    
    $routeProvider
    .when('/home', {
        templateUrl: 'views/lista-fornecedores.view.html',
        controller: 'fornecedoresCtrl'
    })
    .when('/cadastrar',{
        templateUrl: 'views/cadastrar.view.html',
        controller: 'fornecedoresCtrl'
    })
    .otherwise({
        redirectTo: '/home'
    });
}]);

