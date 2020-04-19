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
		.when('/model1s', {
			templateUrl : '/app/html/model1s.html'
		})
		.when('/model1s/edit/:id', {
			templateUrl : '/app/html/edit-model1.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);