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
		.when('/racuni', {
			templateUrl : '/app/html/racuni.html'
		})
		.when('/racuni/edit/:id', {
			templateUrl : '/app/html/edit-racuni.html'
		})
		.when('/racuni/prenos/', {
			templateUrl : '/app/html/prenos.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);