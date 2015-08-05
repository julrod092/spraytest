angular
  .module('StoreFruits')

  .factory('fruitService', function($http, $q, $location){
  
    var fruitServiceFactory = this;
  
    fruitServiceFactory.addFruit= function (url, fruit){
      var deferred = $q.defer();
      $http.put(url, fruit).
      success(function(data, status, headers, config) {
        deferred.resolve(data);
      }).
      error(function(data, status, headers, config) {
        deferred.reject(data);
      });
      return deferred.promise;
    };
    return fruitServiceFactory;
  })