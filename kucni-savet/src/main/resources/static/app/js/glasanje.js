var testApp = angular.module("testApp");


testApp.controller("glasanjeCtrl", function ($scope, $http, $routeParams, $location) {

	var porukeUrl = "/api/poruke/" + $routeParams.id;
	var zgradeUrl = "/api/zgrade";

	$scope.zgrade = [];

	$scope.editEntity = {};
	$scope.editEntity.naslov = "";
	$scope.editEntity.tip = "";
	$scope.editEntity.opis = "";
	$scope.editEntity.potrebanProcenat = "";

	$scope.glas = {};
	$scope.glas.komentar = "";
	$scope.glas.predlogPodrzan = "";

	$scope.zgradaId = "";

	var getzgrade = function () {
		$http.get(zgradeUrl).then(
			function success(res) {
				$scope.zgrade = res.data;
				getModel1();
			},
			function error() {
				alert("Neuspešno dobavljanje zgrade.");
			}
		);
	}

	getzgrade();


	var getModel1 = function () {
		$http.get(porukeUrl).then(
			function success(res) {
				$scope.editEntity = res.data;
			},
			function error() {
				alert("Neuspešno dobavljanje poruke.");
			}
		);
	}

	$scope.doVote = function (za) {
		if (za) {
			$scope.glas.predlogPodrzan = "za";
		} else {
			$scope.glas.predlogPodrzan = "protiv";
		}
		$http.post(porukeUrl, $scope.glas).then(
			function success() {
				$location.path("/poruke");
			},
			function error() {
				alert("Neuspešno glasanje.");
			}
		);
	}
});
