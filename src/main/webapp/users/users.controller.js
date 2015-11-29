(function(){
	'use strict';
	
	angular.module('Console')
		.controller('UserController',
				UserController);
	
	UserController.$inject = ['UserService', '$scope', '$location',
	                          '$log', 'FlashService'];
	
	function UserController(UserService, $scope, $location, $log, FlashService){
		UserService.getUserNames().then(function(response){
				console.log(response.names);
				$scope.usernames = response.names;
			});
	};
})();