var testApp = angular.module("testApp");

testApp.controller("addCtrl", function ($scope, $http, $location) {

    $scope.model1s = [];
    $scope.model2s = [];

    $scope.newEntity = {};
    $scope.newEntity.polje1 = "";
    $scope.newEntity.polje2 = "";
    $scope.newEntity.polje3 = "";
    $scope.newEntity.polje4 = "";

    $scope.newEntity.model2Id = "";

    $scope.searchParams = {};
    $scope.searchParams.searchPar1 = "";
    $scope.searchParams.searchPar2 = "";
    $scope.searchParams.searchPar3 = "";

    $scope.pageNum = 0;
    $scope.totalPages = 1;
    $scope.rowOptions = ["5", "10", "20"];
    $scope.rowsPerPage = $scope.rowOptions[0];

    var model1sUrl = "/api/model1s";
    var model2sUrl = "/api/model2s";

    var getmodel1s = function () {

        var config = { params: {} };

        if ($scope.searchParams.searchPar1 != "") {
            config.params.searchPar1 = $scope.searchParams.searchPar1;
        }
        if ($scope.searchParams.searchPar2 != "") {
            config.params.searchPar2 = $scope.searchParams.searchPar2;
        }
        if ($scope.searchParams.searchPar3 != "") {
            config.params.searchPar3 = $scope.searchParams.searchPar3;
        }

        config.params.pageNum = $scope.pageNum;
        config.params.rowsPerPage = $scope.rowsPerPage;

        $http.get(model1sUrl, config).then(
            function success(res) {
                $scope.model1s = res.data;
                $scope.totalPages = res.headers("totalPages");
            },
            function error() {
                alert("Neupešno dobavljanje model1s.");
            }
        );
        var n = $scope.model1s.length;
        if (n > 0) {
            for (i = 1; i <= $scope.rowsPerPage - n; i++) {
                $scope.model1s.push({});
            }
        }
    }

    getmodel1s();


    var getmodel2s = function () {
        $http.get(model2sUrl).then(
            function success(res) {
                $scope.model2s = res.data;
            },
            function error() {
                alert("Neuspešno dobavljanje model2s.");
            }
        );
    }

    getmodel2s();


    $scope.doAdd = function () {

        $http.post(model1sUrl, $scope.newEntity).then(
            function success() {
                getmodel1s();

                $scope.newEntity = {};
                $scope.newEntity.polje1 = "";
                $scope.newEntity.polje2 = "";
                $scope.newEntity.polje3 = "";
                $scope.newEntity.polje4 = "";

                $scope.model2Id = "";
            },
            function error() {
                alert("Neuspešno čuvanje model1s!");
            }
        );
    }

    $scope.doDelete = function (id) {
        var promise = $http.delete(model1sUrl + "/" + id);
        promise.then(
            function success() {
                getmodel1s();
            },
            function error() {
                alert("Neuspešno brisanje model1s.");
            }
        );
    }

    $scope.goToEdit = function (id) {
        $location.path("/model1s/edit/" + id);
    }

    $scope.changePage = function (direction) {
        $scope.pageNum = $scope.pageNum + direction;
        getmodel1s();
    }

    $scope.doSearch = function () {
        $scope.pageNum = 0;
        getmodel1s();
    }

    $scope.doInteract = function (id) {
        var promise = $http.post(model1sUrl + "/" + id);
        promise.then(
            function success() {
                alert("Uspešna interakcija.")
                getmodel1s();
            },
            function error() {
                alert("Neuspešna interakcija.");
                getmodel1s();
            }
        );
    }

});