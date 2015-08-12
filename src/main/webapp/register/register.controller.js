(function() {
	'use strict';

	angular.module('Console')
			.controller('RegisterController',
						RegisterController);
	
	RegisterController.$inject = [ 'RegisterService', '$scope', '$location',
			'$log', 'FlashService' ];
	function RegisterController(RegisterService, $scope, $location, $log,
			FlashService) {
		$scope.vm = {};
		
		var vm = this;

		vm.register = register;

		function register() {
			var response;
			vm.dataloading = true;
			RegisterService.Create(vm.user).then(function(data) {
				if(data.success){
					$scope.vm = {};
					FlashService.Success(data.message, true);
				}else{
					FlashService.Error(data.message);
					vm.dataloading = false;
				}

			});

		}

	}
	;

})();