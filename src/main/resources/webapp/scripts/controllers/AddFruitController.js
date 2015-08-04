angular
  .module('StoreFruits')
  .controller('AddFruitController', [ '$scope', 'userService', '$http',
    function($scope, fruitService, $http){
      
      $scope.success = ""
      
      $scope.addfruit = function(){
        var json = angular.toJson($scope.user)
        console.log(json)
        var promise = userService.createUser("/fruit", json);
        
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