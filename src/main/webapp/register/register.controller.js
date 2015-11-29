(function() {
	'use strict';

	angular.module('Console')
			.controller('RegisterController',
						RegisterController);
	
	RegisterController.$inject = [ 'RegisterService', '$scope', '$location',
			'$log', 'FlashService' ];
	function RegisterController(RegisterService, $scope, $location, $log,
			FlashService) {
		
		var vm = this;

		vm.register = register;

		function register() {
			vm.dataloading = true;
			RegisterService.Create(vm.user).then(function(data) {
				if(data.success){
					$scope.vm = {};
					FlashService.Success(data.message, true);
					$location.path('/users');
				}else{
					FlashService.Error(data.message);
					vm.dataloading = false;
				}

			});

		}

	}
	;

})();