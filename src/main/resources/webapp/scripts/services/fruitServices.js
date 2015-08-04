angular
  .module('StoreFruits')

  .factory('fruitService', function($http, $q, $location){
  
    var userServiceFactory = this;
  
    userServiceFactory.createFruit= function (url, user){
      var deferred = $q.defer();
      $http.put(url, user).
      success(function(data, status, headers, config) {
        deferred.resolve(data);
      }).
      error(function(data, status, headers, config) {
        deferred.reject(data);
      });
      return deferred.promise;
    };
    return userServiceFactory;
  })