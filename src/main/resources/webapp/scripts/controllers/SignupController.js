angular
  .module('StoreFruits')
  .controller('SignupController', [ '$scope', 'userService', '$http',
  '$timeout',
    function($scope, userService, $http, $timeout){
      
      $scope.success = ""
      
      $scope.register = function(){
        var json = angular.toJson($scope.user)
        console.log(json)
        var promise = userService.createUser("/user", json);
        
        promise.then(
          function(success){
            $scope.success = success
            $scope.alertsuc = true
          },
          function(error){
            $scope.error = error
            $scope.alerterr = true
          }
        );
        $timeout(function () {
          $scope.alertsuc = false;
          $scope.alerterr = false;
        }, 6000);
      }
    }
  ]);