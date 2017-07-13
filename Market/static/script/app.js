'use strict';

angular
	.module('appSBZ', [
	    'ngResource',
	    'ngRoute',
	    'ngCookies',
	    'ngStorage',
	    'restangular',
	    'ui.bootstrap',
	    'lodash'
    ])
    .config(['$routeProvider', function($routeProvider) {
    	$routeProvider
    		.when('/', {
    			templateUrl: 'view/pocetna.html',
    			controller: 'ArtileKontroler'
    		})
    		.when('/private', {
    			templateUrl: 'view/proba.html',
    			controller: 'ProcentCtrl'
    		})
    		.when('/private/list/complement', {
    			templateUrl: 'view/listComlement.html',
    			controller: 'ArticlePorucivanjeCtrl'
    		})
    		.when('/private/list/cart', {
    			templateUrl: 'view/listAdminCart.html',
    			controller: 'CartAdminCtrl'
    		})
    		.when('/private/add/article', {
    			templateUrl: 'view/dodavanjeArtikla.html',
    			controller: 'ArtikliAddCtrl'
    		})
    		.when('/private/add/category', {
    			templateUrl: 'view/dodavanjeKategorije.html',
    			controller: 'KategorijaAddCtrl'
    		})
    		.when('/private/add/podcategory', {
    			templateUrl: 'view/dodatiPodkategoriju.html',
    			controller: 'PodKategorijaAddCtrl'
    		})
    		.when('/private/action/event', {
    			templateUrl: 'view/cisto.html',
    			controller: 'EventCtrl'
    		})
    		.when('/private/all/article', {
    			templateUrl: 'view/sviArtikli.html',
    			controller: 'ArtiklAdminCtrl'
    		})
    		.when('/private/all/category', {
    			templateUrl: 'view/sveKategorije.html',
    			controller: 'PodKategorijaAddCtrl'
    		})
    		.when('/private/spending', {
    			templateUrl: 'view/speding.html',
    			constoller: 'SperdingCtrl'
    		})
    		.when('/profile/:username', {
    			templateUrl: 'view/mojProfil.html',
    			controller: 'UserProfilCtrl'
    		})
    		.when('/user/:id/:kategorija', {
    			templateUrl: 'view/kategorijaUser.html',
    			controller: 'ArtikKategorijaCtrl'
    		})
    		.when('/login', {
    			templateUrl: 'view/login.html',
    		})
    		.when('/article/:id', {
    			templateUrl: 'view/article.html',
    			controller: 'JedanArtiklCtrl'
    		})
    		.when('/cart', {
    			templateUrl: 'view/cart.html',
    			controller: 'ShopCartCtrl'
    		})
    		.when('/finis/account', {
    			templateUrl: 'view/finis.html',
    			controller: 'FinishAccountCtrl'
    		})
    		.when('/search/:parametar', {
    			templateUrl: 'view/search.html',
    			controller: 'ProbaCtrl'
    		})
    		.when('/user/category', {
    			templateUrl: 'view/allCategory.html',
    			controller: 'AllCategoryCtrl'
    		})
    		.when('/finis/account/:id', {
    			templateUrl: 'view/finisList.html',
    			controller: 'FinisAccountListCtrl'
    		})
    }])
    
    .run(['Restangular', '$log', '$rootScope', '$http', '$location', '$localStorage', 'LoginResources', function(Restangular, $log, $rootScope, $http, $location, $localStorage, LoginResources) {
        Restangular.setBaseUrl("api");
        Restangular.setErrorInterceptor(function(response) {
            if (response.status === 500) {
                $log.info("internal server error");
                return true; // greska je obradjena
            }
            return true; // greska nije obradjena
        });
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = $localStorage.currentUser.token;
        }
        $rootScope.logout = function () {
        	LoginResources.logout();
        }
        $rootScope.getCurrentUserRole = function () {
            if (!LoginResources.getCurrentUser()){
              return "prazno";
            }
            else{
            	return LoginResources.getCurrentUser().rola;
            }
        }
        
        $rootScope.getCurrentUserName = function () {
            if (!LoginResources.getCurrentUser()){
              return "prazno";
            }
            else{
            	return LoginResources.getCurrentUser();
            }
        }
        
        $rootScope.isLoggedIn = function () {
            if (LoginResources.getCurrentUser()){
              return true;
            }
            else{
              return false;
            }
        }
        $rootScope.getCurrentState = function () {
          return $state.current.name;
        }
        
        
    }])


	
	    