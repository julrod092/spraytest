angular
  .module('StoreFruits')
  .controller('SignupController', [ '$scope', 'userService', '$http',
    function($scope, userService, $http){
      
      $scope.success = ""
      
      $scope.register = function(){
        var json = angular.toJson($scope.user)
        console.log(json)
        var promise = userService.createUser("/user", json);
        
        promise.then(
          function(success){
            $scope.success = success
            $scope.alertsuc = true
            $scope.alerterr = false
          },
          function(error){
            $scope.error = error
            $scope.alerterr = true
            $scope.alertsuc = false
          }
        );
      }
    }
  ]);