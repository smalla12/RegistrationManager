(function () {
    'use strict';
 
    angular
        .module('Console', ['ui.router'])
        .config(config);
    
    config.$inject = ['$urlRouterProvider', '$stateProvider'];
    function config($urlRouterProvider, $stateProvider){
    	console.log('app.js');
    	$urlRouterProvider
    		.otherwise('/register');
    	
    	$stateProvider
    		.state('register', {
    			url: '/register',
				controller: 'RegisterController',
				templateUrl: 'register/register.view.html',
				controllerAs: 'vm'
    		});
		
    }
        
        
})();