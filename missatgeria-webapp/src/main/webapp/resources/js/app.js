/*
 * AngularJS - degloba
 */
(function() {


    //definim el module angular
    var app = angular.module('gemStore', ["ngRoute","firebase"]);

    // ******************** 
    // configure our routes
    // ********************
app.config(['$routeProvider', function($routeProvider) {
      $routeProvider 
      .when('/XHTML', {        
        templateUrl : "/app/landing"
    })      
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

app.config(function($provide) {
            $provide.provider('MathService', function() {
               this.$get = function() {
                  var factory = {};
                  
                  factory.multiply = function(a, b) {
                     return a * b;
                  }
                  return factory;
               };
            });
         });

         app.value("defaultInput", 5);


//create a factory "MathService" which provides a method multiply to return multiplication of two number
  app.factory('serviceId', ['MathService', function(depService) {
  var factory = {};
   
   factory.multiply = function(a, b) {
      return a * b
   }
   return factory;
}])
.directive('directiveName', ['depService', function(depService) {
  // ...
}])
//inject the factory "MathService" in a service to utilize the multiply method of factory.
.service('CalcService', function(MathService) {
   this.square = function(a) {
       alert("hola2 createuser");
      return MathService.multiply(a,a);
   }
   })
.filter('filterName', ['depService', function(depService) {
  // ...
}]);




//inject the service "CalcService" into the controller
app.controller('CalcController', function($scope, CalcService, defaultInput) {
            $scope.number = defaultInput;
            $scope.result = CalcService.square($scope.number);

            $scope.square = function() {
               $scope.result = CalcService.square($scope.number);
            }
         });
        

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



ap
//inject the factory "MathService" in a service to utilize the multiply method of factory.
.service('AuthService', function(AuthService) {
   this.square = function(a) {
       alert("hola2 createuser");
      return MathService.multiply(a,a);
   }
   })
.filter('filterName', ['depService', function(depService) {
  // ...
}]);

})();;
