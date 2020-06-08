var testApp = angular.module("testApp");

testApp.controller("addCtrl", function ($scope, $http, $location) {

    $scope.racuni = [];
    $scope.banke = [];
    $scope.tipoviRacuna = [];

    $scope.newEntity = {};
    $scope.newEntity.imePrezime = "";
    $scope.newEntity.jmbg = "";
    $scope.newEntity.brojRacuna = "";

    $scope.newEntity.bankaId = null;
    $scope.newEntity.tipRacunaId = "";

    $scope.searchParams = {};
    $scope.searchParams.searchPar1 = "";
    $scope.searchParams.searchPar2 = "";
    $scope.searchParams.searchPar3 = "";

    $scope.pageNum = 0;
    $scope.totalPages = 1;
    $scope.rowOptions = ["5", "10", "20"];
    $scope.rowsPerPage = $scope.rowOptions[0];

    var racuniUrl = "/api/racuni";
    var bankeUrl = "/api/banke";

    var getRacuni = function () {

        var config = { params: {} };

        if ($scope.searchParams.searchPar1 != "") {
            config.params.jmbg = $scope.searchParams.searchPar1;
        }
        if ($scope.searchParams.searchPar2 != "") {
            config.params.bankaId = $scope.searchParams.searchPar2;
        }


        config.params.pageNum = $scope.pageNum;
        config.params.rowsPerPage = $scope.rowsPerPage;

        $http.get(racuniUrl, config).then(
            function success(res) {
                $scope.racuni = res.data;
                $scope.totalPages = res.headers("totalPages");
            },
            function error() {
                alert("Neupešno dobavljanje racuni.");
            }
        );
    }

    getRacuni();


    var getBanke = function () {
        $http.get(bankeUrl).then(
            function success(res) {
                $scope.banke = res.data;
            },
            function error() {
                alert("Neuspešno dobavljanje banke.");
            }
        );
    }

    $scope.getTipoviRacuna = function () {
        $http.get(bankeUrl + "/" + $scope.newEntity.bankaId + "/tipovi-racuna").then(
            function success(res) {
                $scope.tipoviRacuna = res.data;
            },
            function error() {
                alert("Neuspešno dobavljanje tipova racuna.");
            }
        );
    }

    getBanke();


    $scope.doAdd = function () {

        $http.post(racuniUrl, $scope.newEntity).then(
            function success() {
                getRacuni();

                $scope.newEntity = {};
                $scope.newEntity.imePrezime = "";
                $scope.newEntity.jmbg = "";
                $scope.newEntity.brojRacuna = "";

                $scope.newEntity.bankaId = null;
                $scope.newEntity.tipRacunaId = "";
            },
            function error() {
                alert("Neuspešno čuvanje racuni!");
            }
        );
    }

    $scope.doDelete = function (id) {
        var promise = $http.delete(racuniUrl + "/" + id);
        promise.then(
            function success() {
                getRacuni();
            },
            function error() {
                alert("Neuspešno brisanje racuni.");
            }
        );
    }

    $scope.goToEdit = function (id) {
        $location.path("/racuni/edit/" + id);
    }

    $scope.goToPrenos = function (id) {
        $location.path("/racuni/prenos/");
    }

    $scope.changePage = function (direction) {
        $scope.pageNum = $scope.pageNum + direction;
        getRacuni();
    }

    $scope.doSearch = function () {
        $scope.pageNum = 0;
        getRacuni();
    }

    $scope.doInteract = function (id) {
        var promise = $http.post(racuniUrl + "/" + id);
        promise.then(
            function success() {
                alert("Uspešna interakcija.")
                getRacuni();
            },
            function error() {
                alert("Neuspešna interakcija.");
                getRacuni();
            }
        );
    }

});