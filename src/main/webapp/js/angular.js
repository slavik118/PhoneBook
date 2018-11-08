var app = angular.module('app', []);

app.controller('postController', function($scope, $http, $location) {
	$scope.submitForm = function(){
		var url = $location.absUrl() + "postcontact";
		
		var config = {
                headers : {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
        }
		
		var data = {
            firstName: $scope.firstname,
            lastName: $scope.lastname,
            phone: $scope.phone
        };
		
		$scope.postResultMessage = "";		
		
		$http.post(url, data, config).then(function (response) {
			$scope.postResultMessage = "Sucessful!";
		}, function (response) {
			$scope.postResultMessage = "Fail!";
		});
		
		$scope.firstname = "";
		$scope.lastname = "";
		$scope.phone = "";
	}
});

app.controller('getallcontactsController', function($scope, $http, $location) {
	
	$scope.showAllContacts = false;

	$scope.getAllContacts = function() {
		var url = $location.absUrl() + "findall";

		var config = {
			headers : {
				'Content-Type' : 'application/json;charset=utf-8;'
			}
		}

		$http.get(url, config).then(function(response) {

			if (response.data.status == "Done") {
				$scope.allcontacts = response.data;
				$scope.showAllContacts = true;

			} else {
				$scope.getResultMessage = "get All Contacts Data Error!";
			}

		}, function(response) {
			$scope.getResultMessage = "Fail!";
		});

	}
});

app.controller('getcontactController', function($scope, $http, $location) {
	
	$scope.showContact = false;
	
	$scope.getContact = function() {
		var url = $location.absUrl() + "contact/" + $scope.contactId;

		var config = {
			headers : {
				'Content-Type' : 'application/json;charset=utf-8;'
			}
		}

		$http.get(url, config).then(function(response) {

			if (response.data.status == "Done") {
				$scope.contact = response.data;
				$scope.showContact = true;

			} else {
				$scope.getResultMessage = "Contact Data Error!";
			}

		}, function(response) {
			$scope.getResultMessage = "Fail!";
		});

	}
});

app.controller('getcontactsbylastnameController', function($scope, $http, $location) {
	
	$scope.showContactsByLastName = false;
	
	$scope.getContactsByLastName = function() {
		var url = $location.absUrl() + "findbylastname";

		var config = {
			headers : {	'Content-Type' : 'application/json;charset=utf-8;' },
		
			params: { 'lastName' : $scope.contactLastName }
		}

		$http.get(url, config).then(function(response) {

			if (response.data.status == "Done") {
				$scope.allcontactsbylastname = response.data;
				$scope.showContactsByLastName = true;

			} else {
				$scope.getResultMessage = "Contact Data Error!";
			}

		}, function(response) {
			$scope.getResultMessage = "Fail!";
		});

	}
});
