angular
  .module('StoreFruits')
  .controller('LoginController', [ '$scope', '$state', 'userService', '$http',
    function($scope, $state, userService, $http){
      
      $scope.success = ""
      
      $scope.login = function(){
        var json = angular.toJson($scope.user)
        console.log(json)
        var promise = userService.login("/user", json);
        
        promise.then(
          function(success){
            $state.go('fruits');
            $scope.success = success
            $scope.alertsuc = true
          },
          function(error){
            $scope.success = "Datos malos"
            $scope.alerterr = true
          }
        );
      }
    }
  ]);