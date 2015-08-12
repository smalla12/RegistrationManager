(function () {
    'use strict';
 
    angular
        .module('Console', ['ngRoute'])
        .config(config);
    
    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider){
    	$routeProvider
    		.when('/register', {
    			controller: 'RegisterController',
    			templateUrl: 'register/register.view.html',
    			controllerAs: 'vm'
    		})
    		.otherwise({redirectTo: '/register'});
    }
        
        
})();