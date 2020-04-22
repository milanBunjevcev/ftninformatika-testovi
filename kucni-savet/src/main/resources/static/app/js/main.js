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
		.when('/poruke', {
			templateUrl : '/app/html/poruke.html'
		})
		.when('/poruke/edit/:id', {
			templateUrl : '/app/html/edit-poruke.html'
		})
		.when('/poruke/glasanje/:id', {
			templateUrl : '/app/html/glasanje.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);