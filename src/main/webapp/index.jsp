<!DOCTYPE html>
<html lang="en" ng-app="Console">
<head>
<title>Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="css/registration-css.css">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.2.15/angular-ui-router.min.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div
					ng-class="{'alert':flash, 'alert-success': flash.type==='success', 'alert-danger': flash.type==='error'}"
					ng-if="flash" ng-bind="flash.message"></div>
				<div ui-view></div>
			</div>
		</div>

	</div>
	
	<script type="text/javascript" src="app.js"></script>
	<script type="text/javascript" src="register/register.service.js"></script>
	<script type="text/javascript" src="flash.service.js"></script>
	<script type="text/javascript" src="register/register.controller.js"></script>
</body>
</html>