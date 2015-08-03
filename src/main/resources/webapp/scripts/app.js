angular
  .module('StoreFruits', ['ui.router'])
  
  .config(
    function($stateProvider, $urlRouterProvider){
      
      $urlRouterProvider.otherwise('/');
      
      $stateProvider
        .state('home', {
          url: '/',
          templateUrl: 'views/home-template.html',
          controller: 'HomeController'
        })
        .state('signup', {
          url: '/signup',
          templateUrl: 'views/sign-up-template.html',
          controller: 'SignupController'
        });
    }
  )