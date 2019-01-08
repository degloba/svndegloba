/*
 * AngularJS - degloba
 */
(function() {
  var app = angular.module('gemStore', ["ngRoute"]);

  // configure our routes
app.config(['$routeProvider', function($routeProvider) {
      $routeProvider      
    .when('/red', {        
        templateUrl : "red.html"
    })
    .when("/green", {
        templateUrl : "green.html"
    })
    .when("/blue", {
        templateUrl : "blue.html"
    });
  }]);

  // create the controller and inject Angular's $scope
  app.controller('mainController', function($scope) {
      // create a message to display in our view
      
      
      this.products = gems;
  });

message = 'Everyone come and see how good I look!';

  var gems = [
    { name: 'Azurite', price: 110.50 },
    { name: 'Bloodstone', price: 22.90 },
    { name: 'Zircon', price: 1100 },
  ];
})();
