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
        })
        .state('login', {
          url: '/login',
          templateUrl: 'views/login-template.html',
          controller: 'LoginController'
        })
        .state('fruits', {
          url: '/fruits',
          templateUrl: 'views/fruits-template.html',
          controller: 'FruitsController'
        })
        .state('addfruit', {
          url: '/addfruit',
          templateUrl: 'views/add-fruit-template.html',
          controller: 'AddFruitController'
        });
    }
  )