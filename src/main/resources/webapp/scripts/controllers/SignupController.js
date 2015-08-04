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
          },
          function(error){
            $scope.success = error
            $scope.alerterr = true
          }
        );
      }
    }
  ]);