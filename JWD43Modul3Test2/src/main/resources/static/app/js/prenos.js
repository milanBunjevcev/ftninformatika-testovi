var testApp = angular.module("testApp");


testApp.controller("prenosCtrl", function ($scope, $http, $routeParams, $location) {

	$scope.editEntity = {};
	$scope.editEntity.brojRacuna1 = ""
	$scope.editEntity.brojRacuna2 = "";
	$scope.editEntity.iznos = "";

	$scope.doPrenos = function () {

		var config = { params: {} };

        if ($scope.editEntity.brojRacuna1 != "") {
            config.params.brojRacuna1 = $scope.editEntity.brojRacuna1;
        }
        if ($scope.editEntity.brojRacuna2 != "") {
            config.params.brojRacuna2 = $scope.editEntity.brojRacuna2;
		}
		if ($scope.editEntity.iznos != "") {
            config.params.iznos = $scope.editEntity.iznos;
        }

		$http.get("/api/racuni/prenos", config).then(
			function success() {
				alert("Uspesan prenos.");
				$location.path("/racuni");
			},
			function error() {
				alert("Neuspešan prenos.");
			}
		);
	}
});
