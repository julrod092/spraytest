angular
  .module('StoreFruits')
  .controller('AddFruitController', [ '$scope', 'fruitService', '$http',
    function($scope, fruitService, $http){
      
      $scope.success = ""
      
      $scope.addfruit = function(){
        var json = angular.toJson($scope.fruit)
        console.log(json)
        var promise = fruitService.addFruit("/fruit", json);
        
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