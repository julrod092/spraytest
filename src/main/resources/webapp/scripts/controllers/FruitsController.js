angular
  .module('StoreFruits')

  .controller('FruitsController', [ '$scope', 'fruitService', '$http',
    function($scope, fruitService, $http){

      $scope.fruits = []

      var promise = fruitService.getAllFruits("/fruit/getAllFruits");

      promise.then(
        function(success){
            $scope.fruits = success
            console.log($scope.fruits)
        },
        function(error){
            $scope.alert = "Problems with server"
            $scope.alertmsg = true
        }
      );
    }
  ]);