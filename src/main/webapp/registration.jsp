<!DOCTYPE html>
<html lang="en">
<head>
<title>Registration</title>
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
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular.min.js"></script>
</head>
<body data-ng-app="myApp">

	<div class="container">
		<div class="col-md-4"></div>

		<div class="col-md-4">
			<form name="form"
				data-ng-controller="RegistrationController" role="form">

				<!-- header -->
				<div class="modal-header">
					<h3>Registration</h3>
				</div>

				<!-- body -->
				<div class="modal-body">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Username"
							name="user_field" data-ng-model="username" data-ng-minlength="5"
							data-ng-pattern="/^[a-zA-Z0-9]*$/" required>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="Password"
							name="pwd_field" data-ng-model="password" data-ng-minlength="8"
							data-ng-pattern="/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/"
							required>
					</div>
				</div>

				<!-- footer -->
				<div class="modal-footer">
					<div class="form-actions">
						<button type="submit" id="submit" data-ng-click="submit()" data-ng-disabled="form.$invalid"
						class="btn btn-primary btn-block">Register</button>
					</div>
					
					<div class="validate-message"
						data-ng-show="form.user_field.$dirty && form.user_field.$error.required">User
						Name is required.</div>
					<div class="validate-message"
						data-ng-show="form.user_field.$dirty && form.user_field.$error.minlength">User
						Name should be no less than 5 characters.</div>
					<div class="validate-message"
						data-ng-show="form.user_field.$dirty && form.user_field.$error.pattern">User
						Name should contain alpha numeric characters only.</div>
					<div class="validate-message"
						data-ng-show="form.pwd_field.$dirty && form.pwd_field.$error.required">Password
						is required.</div>
					<div class="validate-message"
						data-ng-show="form.pwd_field.$dirty && form.pwd_field.$error.minlength">Password
						should be no less than 8 characters.</div>
					<div class="validate-message"
						data-ng-show="form.pwd_field.$dirty && form.pwd_field.$error.pattern">Password
						should contain at least 1 number, 1 uppercase, and 1 lowercase
						character.</div>
					<div style="text-align: left;" id="responseMsg">{{responseData.message}}</div>

				</div>
			</form>
		</div>

		<div class="col-md-4"></div>

	</div>
	<script type="text/javascript"
		src="controllers/registration-javascript.js">
		
	</script>
</body>
</html>