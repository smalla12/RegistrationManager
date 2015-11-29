(function(){
	'use strict';
	
	angular.module('Console').factory('UserService', UserService);
	
	UserService.$inject=['$http', '$log'];
	
	function UserService($http, $log){
		var service = {};
		service.getUserNames = getUserNames;
		return service;
		
		function getUserNames(){
			return $http.get('api/FetchUsers').then(function(response){
				console.log(response.data);
				return response.data;
			});
		}
	}
})();