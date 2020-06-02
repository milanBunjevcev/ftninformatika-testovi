var testApp = angular.module("testApp", ['ngRoute']);

testApp.controller("homeCtrl", function($scope){
	$scope.message = "Hello JWD!";
});


testApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html',
			controller: 'homeCtrl'
		})
		.when('/zadaci', {
			templateUrl : '/app/html/zadaci.html'
		})
		.when('/zadaci/edit/:id', {
			templateUrl : '/app/html/edit-zadaci.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);