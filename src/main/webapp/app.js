(function () {
    'use strict';
 
    angular
        .module('Console', ['ui.router'])
        .config(config);
    
    config.$inject = ['$urlRouterProvider', '$stateProvider'];
    function config($urlRouterProvider, $stateProvider){
    	$urlRouterProvider
    		.otherwise('/users');
    	
    	$stateProvider
    		.state('register', {
    			url: '/register',
				controller: 'RegisterController',
				templateUrl: 'register/register.view.html',
				controllerAs: 'vm'
    		})
    		.state('users', {
    			url: '/users',
    			controller: 'UserController',
    			templateUrl: 'users/users.view.html',
    		});
		
    }
        
        
})();