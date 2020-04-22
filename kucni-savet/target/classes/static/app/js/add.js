var testApp = angular.module("testApp");

testApp.controller("addCtrl", function ($scope, $http, $location) {

    $scope.poruke = [];
    $scope.zgrade = [];

    $scope.newEntity = {};
    $scope.newEntity.naslov = "";
    $scope.newEntity.tip = "obaveštenje";
    $scope.newEntity.opis = "";
    $scope.newEntity.potrebanProcenat = "";

    $scope.newEntity.zgradaId = "";

    $scope.searchParams = {};
    $scope.searchParams.searchPar1 = "";
    $scope.searchParams.searchPar2 = "";
    $scope.searchParams.searchPar3 = "";

    $scope.pageNum = 0;
    $scope.totalPages = 1;
    $scope.rowOptions = ["5", "10", "20"];
    $scope.rowsPerPage = $scope.rowOptions[0];

    var porukeUrl = "/api/poruke";
    var zgradeUrl = "/api/zgrade";

    var getporuke = function () {

        var config = { params: {} };

        if ($scope.searchParams.searchPar1 != "") {
            config.params.zgradaId = $scope.searchParams.searchPar1;
        }
        if ($scope.searchParams.searchPar2 != "") {
            config.params.naslov = $scope.searchParams.searchPar2;
        }
        if ($scope.searchParams.searchPar3 != "") {
            config.params.tip = $scope.searchParams.searchPar3;
        }

        config.params.pageNum = $scope.pageNum;
        config.params.rowsPerPage = $scope.rowsPerPage;

        $http.get(porukeUrl, config).then(
            function success(res) {
                $scope.poruke = res.data;
                $scope.totalPages = res.headers("totalPages");
                var n = $scope.poruke.length;
                if (n > 0) {
                    for (i = 1; i <= $scope.rowsPerPage - n; i++) {
                        $scope.poruke.push({});
                    }
                }
            },
            function error() {
                alert("Neupešno dobavljanje poruke.");
            }
        );
    }

    getporuke();


    var getzgrade = function () {
        $http.get(zgradeUrl).then(
            function success(res) {
                $scope.zgrade = res.data;
            },
            function error() {
                alert("Neuspešno dobavljanje zgrade.");
            }
        );
    }

    getzgrade();


    $scope.doAdd = function () {

        $http.post(porukeUrl, $scope.newEntity).then(
            function success() {
                getporuke();

                $scope.newEntity = {};
                $scope.newEntity.naslov = "";
                $scope.newEntity.tip = "";
                $scope.newEntity.opis = "";
                $scope.newEntity.potrebanProcenat = "";

                $scope.zgradaId = "";
            },
            function error() {
                alert("Neuspešno čuvanje poruke!");
            }
        );
    }

    $scope.doDelete = function (id) {
        var promise = $http.delete(porukeUrl + "/" + id);
        promise.then(
            function success() {
                getporuke();
            },
            function error() {
                alert("Neuspešno brisanje poruke.");
            }
        );
    }

    $scope.goToEdit = function (id) {
        $location.path("/poruke/edit/" + id);
    }

    $scope.changePage = function (direction) {
        $scope.pageNum = $scope.pageNum + direction;
        getporuke();
    }

    $scope.doSearch = function () {
        $scope.pageNum = 0;
        getporuke();
    }

    $scope.doInteract = function (id) {
        $location.path("/poruke/glasanje/" + id);
    }

    // $scope.doInteract = function (id) {
    //     var promise = $http.post(porukeUrl + "/" + id);
    //     promise.then(
    //         function success() {
    //             alert("Uspešna interakcija.")
    //             getporuke();
    //         },
    //         function error() {
    //             alert("Neuspešna interakcija.");
    //             getporuke();
    //         }
    //     );
    // }

});