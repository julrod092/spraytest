angular
  .module('StoreFruits')

  .factory('userService', function($http, $q, $location){
  
    var userServiceFactory = this;
  
    userServiceFactory.createUser= function (url, user){
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
  
    userServiceFactory.login= function (url, user){
      var deferred = $q.defer();
      $http.post(url, user).
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