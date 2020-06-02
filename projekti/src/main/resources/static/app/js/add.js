var testApp = angular.module("testApp");

testApp.controller("addCtrl", function ($scope, $http, $location) {

    $scope.zadaci = [];
    $scope.sprintovi = [];
    $scope.stanja = [];

    $scope.newEntity = {};
    $scope.newEntity.ime = "";
    $scope.newEntity.zaduzeni = "";
    $scope.newEntity.bodovi = "";
    
    $scope.newEntity.stanjeId = "";
    $scope.newEntity.sprintId = "";

    $scope.searchParams = {};
    $scope.searchParams.searchPar1 = "";
    $scope.searchParams.searchPar2 = "";

    $scope.pageNum = 0;
    $scope.totalPages = 1;
    $scope.rowOptions = ["5", "10", "20"];
    $scope.rowsPerPage = $scope.rowOptions[0];

    var zadaciUrl = "/api/zadaci";
    var sprintoviUrl = "/api/sprintovi";
    var stanjaUrl = "/api/stanja"

    var getzadaci = function () {

        var config = { params: {} };

        if ($scope.searchParams.searchPar1 != "") {
            config.params.ime = $scope.searchParams.searchPar1;
        }
        if ($scope.searchParams.searchPar2 != "") {
            config.params.sprintId = $scope.searchParams.searchPar2;
        }
        

        config.params.pageNum = $scope.pageNum;
        config.params.rowsPerPage = $scope.rowsPerPage;

        $http.get(zadaciUrl, config).then(
            function success(res) {
                $scope.zadaci = res.data;
                $scope.totalPages = res.headers("totalPages");
            },
            function error() {
                alert("Neupešno dobavljanje zadaci.");
            }
        );
    }

    getzadaci();


    var getsprintovi = function () {
        $http.get(sprintoviUrl).then(
            function success(res) {
                $scope.sprintovi = res.data;
            },
            function error() {
                alert("Neuspešno dobavljanje sprintovi.");
            }
        );
    }

    getsprintovi();

    var getstanja = function () {
        $http.get(stanjaUrl).then(
            function success(res) {
                $scope.stanja = res.data;
            },
            function error() {
                alert("Neuspešno dobavljanje stanja.");
            }
        );
    }

    getstanja();


    $scope.doAdd = function () {

        $http.post(zadaciUrl, $scope.newEntity).then(
            function success() {
                getzadaci();

                $scope.newEntity = {};
                $scope.newEntity.ime = "";
                $scope.newEntity.zaduzeni = "";
                $scope.newEntity.bodovi = "";
               
                $scope.newEntity.stanjeId = "";
                $scope.newEntity.sprintId = "";
            },
            function error() {
                alert("Neuspešno čuvanje zadaci!");
            }
        );
    }

    $scope.doDelete = function (id) {
        var promise = $http.delete(zadaciUrl + "/" + id);
        promise.then(
            function success() {
                getzadaci();
            },
            function error() {
                alert("Neuspešno brisanje zadaci.");
            }
        );
    }

    $scope.goToEdit = function (id) {
        $location.path("/zadaci/edit/" + id);
    }

    $scope.changePage = function (direction) {
        $scope.pageNum = $scope.pageNum + direction;
        getzadaci();
    }

    $scope.doSearch = function () {
        $scope.pageNum = 0;
        getzadaci();
    }

    $scope.doInteract = function (id) {
        var promise = $http.post(zadaciUrl + "/" + id);
        promise.then(
            function success() {
                alert("Uspešna interakcija.")
                getzadaci();
            },
            function error() {
                alert("Neuspešna interakcija.");
                getzadaci();
            }
        );
    }

});