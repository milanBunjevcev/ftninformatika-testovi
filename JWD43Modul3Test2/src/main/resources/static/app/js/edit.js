var testApp = angular.module("testApp");


testApp.controller("editCtrl", function ($scope, $http, $routeParams, $location) {

	var racuniUrl = "/api/racuni/" + $routeParams.id;
	var bankeUrl = "/api/banke";

	$scope.banke = [];

	$scope.editEntity = {};
	$scope.editEntity.imePrezime = "";
    $scope.editEntity.jmbg = "";
    $scope.editEntity.brojRacuna = "";

	$scope.editEntity.stanjeRacuna = "";

	var getBanke = function () {
		$http.get(bankeUrl).then(
			function success(res) {
				$scope.banke = res.data;
				getRacun();
			},
			function error() {
				alert("Neuspešno dobavljanje banke.");
			}
		);
	}

	//getBanke();

	

	var getRacun = function () {
		$http.get(racuniUrl).then(
			function success(res) {
				$scope.editEntity = res.data;
			},
			function error() {
				alert("Neuspešno dobavljanje racuni.");
			}
		);
	}

	getRacun();

	$scope.doEdit = function () {
		$http.put(racuniUrl, $scope.editEntity).then(
			function success() {
				$location.path("/racuni");
			},
			function error() {
				alert("Neuspešno čuvanje racuni.");
			}
		);
	}
});
