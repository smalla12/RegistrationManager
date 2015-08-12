(function() {
	'use strict';

	angular.module('Console').factory('RegisterService', RegisterService);

	RegisterService.$inject = [ '$http', '$log' ];
	function RegisterService($http, $log) {
		var service = {};

		service.Create = Create;

		return service;

		function Create(user) {
			return $http.post('api/SaveCredential', user).then(function(data) {
				return data.data;
			});
		}

	}

})();