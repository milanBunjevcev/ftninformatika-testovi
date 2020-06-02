var testApp = angular.module("testApp");


testApp.controller("editCtrl", function ($scope, $http, $routeParams, $location) {

	var zadaciUrl = "/api/zadaci/" + $routeParams.id;
	var sprintoviUrl = "/api/sprintovi";
	var stanjaUrl = "/api/stanja";

	$scope.sprintovi = [];
	$scope.stanja = [];

	$scope.editEntity = {};
	$scope.editEntity.ime = "";
	$scope.editEntity.zaduzeni = "";
	$scope.editEntity.bodovi = "";

	$scope.editEntity.sprintId = "";
	$scope.editEntity.stanjeId = "";

	var getsprintovi = function () {
		$http.get(sprintoviUrl).then(
			function success(res) {
				$scope.sprintovi = res.data;
				getModel1();
			},
			function error() {
				alert("Neuspešno dobavljanje sprintovi.");
			}
		);
	}	

	var getstanja = function () {
		$http.get(stanjaUrl).then(
			function success(res) {
				$scope.stanja = res.data;
				getsprintovi();
			},
			function error() {
				alert("Neuspešno dobavljanje sprintovi.");
			}
		);
	}

	getstanja();

	var getModel1 = function () {
		$http.get(zadaciUrl).then(
			function success(res) {
				$scope.editEntity = res.data;
			},
			function error() {
				alert("Neuspešno dobavljanje zadaci.");
			}
		);
	}

	$scope.doEdit = function () {
		$http.put(zadaciUrl, $scope.editEntity).then(
			function success() {
				$location.path("/zadaci");
			},
			function error() {
				alert("Neuspešno čuvanje zadaci.");
			}
		);
	}
});
