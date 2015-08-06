angular
  .module('StoreFruits')
  .controller('AddFruitController', [ '$scope', '$state','fruitService', '$http',
    function($scope, $state, fruitService, $http){
      
      $scope.success = ""
      
      $scope.addfruit = function(){
        var json = angular.toJson($scope.fruit)
        console.log(json)
        var promise = fruitService.addFruit("/fruit", json);
        
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