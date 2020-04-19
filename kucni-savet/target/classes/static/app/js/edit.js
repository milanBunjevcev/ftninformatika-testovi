var testApp = angular.module("testApp");


testApp.controller("editCtrl", function ($scope, $http, $routeParams, $location) {

	var model1sUrl = "/api/model1s/" + $routeParams.id;
	var model2sUrl = "/api/model2s";

	$scope.model2s = [];

	$scope.editEntity = {};
	$scope.editEntity.polj1 = "";
	$scope.editEntity.polj2 = "";
	$scope.editEntity.polj3 = "";
	$scope.editEntity.polj4 = "";

	$scope.model2Id = "";

	var getmodel2s = function () {
		$http.get(model2sUrl).then(
			function success(res) {
				$scope.model2s = res.data;
				getModel1();
			},
			function error() {
				alert("Neuspešno dobavljanje model2s.");
			}
		);
	}

	getmodel2s();


	var getModel1 = function () {
		$http.get(model1sUrl).then(
			function success(res) {
				$scope.editEntity = res.data;
			},
			function error() {
				alert("Neuspešno dobavljanje model1s.");
			}
		);
	}

	$scope.doEdit = function () {
		$http.put(model1sUrl, $scope.editEntity).then(
			function success() {
				$location.path("/model1s");
			},
			function error() {
				alert("Neuspešno čuvanje model1s.");
			}
		);
	}
});
