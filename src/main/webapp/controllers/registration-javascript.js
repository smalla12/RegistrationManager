(function() {
	'use strict';

	var app = angular.module('myApp', []);
	
	app.controller('RegistrationController', ['$scope', '$http', '$log', function($scope, $http, $log) {
				$scope.submit = function(){
					$log.debug('submitted');
					var formData = {
							"username": $scope.username,
							"password": $scope.password
					}
					var response = $http.post('api/SaveCredential', formData);
					response.success(function(data, status, headers, config){
						$scope.responseData = data;
						if(data.type=="errors"){
							$log.debug(data.type);
							document.getElementById("responseMsg").style.color="red";
						}
						else{
							$log.debug(data.type);
							document.getElementById("responseMsg").style.color="blue";
						}
					});
					response.error(function(data, status, headers, config){
						alert("Some terribly went wrong.");
					});
				};
			}]);

})();