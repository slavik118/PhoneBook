<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Phone Book Example</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.0/angular.min.js"></script>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angular.js"></script>
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" />
</head>
<body>
	<div class="container" ng-app="app">
		<h1>Phone Book Demo</h1>

		<div class="row">
			<div ng-controller="postController" class="col-md-3">
				<form name="contactForm" ng-submit="submitForm()">
					<label>First Name:</label>
					<input type="text" name="firstname"	class="form-control" ng-model="firstname" />
					<label>Last Name:</label>
					<input type="text" name="lastname" class="form-control" ng-model="lastname" />
					<label>Phone:</label>
					<input type="text" name="phone" class="form-control" ng-model="phone" ui-mask="(999) 999-9999" />
				    <br />
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
				<p>{{postResultMessage}}</p>
			</div>
		</div>
		<div class="row">
			<div ng-controller="getallcontactsController" class="col-md-3">
				<h3>All Contacts</h3>

				<button ng-click="getAllContacts()">Get All Contacts</button>

				<div ng-show="showAllContacts">
					<ul class="list-group">
						<li ng-repeat="contact in allcontacts.data"><h4 class="list-group-item">
								<strong>Contact {{$index}}</strong><br />
								Id: {{contact.id}}<br />
								First Name: {{contact.firstName}}<br />
								Last Name: {{contact.lastName}}<br />
								Phone: {{contact.phone}}
						</h4></li>
					</ul>
				</div>
				<p>{{getResultMessage}}</p>
			</div>

			<div ng-controller="getcontactController" class="col-md-3">
				<h3>Contact by ID</h3>

				<input type="text" class="form-control" style="width: 100px;"
					ng-model="contactId" /> <br />
				<button ng-click="getContact()">Get Contact</button>

				<div ng-show="showContact">
					Id: {{contact.data.id}}<br />
					First Name: {{contact.data.firstName}}<br />
					Last Name: {{contact.data.lastName}}<br />
					Phone: {{contact.data.phone}}
				</div>
				<p>{{getResultMessage}}</p>
			</div>

			<div ng-controller="getcontactsbylastnameController" class="col-md-4">
				<h3>Contact by Last Name</h3>

				<input type="text" class="form-control" style="width: 100px;" ng-model="contactLastName" /><br />
				<button ng-click="getContactsByLastName()">Get Contacts</button>

				<div ng-show="showContactsByLastName">

					<ul class="list-group">
						<li ng-repeat="contact in allcontactsbylastname.data"><h4	class="list-group-item">
								<strong>Contact {{$index}}</strong><br />
								Id: {{contact.id}}<br />
								First Name: {{contact.firstName}}<br />
								Last Name: {{contact.lastName}}<br />
								Phone: {{contact.phone}}
						</h4></li>
					</ul>
				</div>
				<p>{{getResultMessage}}</p>
			</div>

		</div>
	</div>
</body>
</html>