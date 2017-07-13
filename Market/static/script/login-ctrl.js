'use strict';

angular.module('appSBZ')
	.controller('LoginCtrl', ['$scope', '$localStorage', '$http', '$location', '$log', '_', '$rootScope', '$routeParams', 'LoginResources',
	    function($scope, $localStorage, $http, $location, $log, _, $rootScope, $routeParams, LoginResources) {
		
		var users = [];
		
		
		$scope.login = function() {
			LoginResources.login($scope.user, callBack);
			//console.log($scope.users);
		}
		
		$scope.registration = function() {
			console.log($scope.users);
			LoginResources.registration($scope.users);
		}
		
		
		
		function callBack(success) {
			console.log(success)
			if(success.role != 'USER') {
				$location.path( "/private" );
				
			} else if(success.role == 'USER'){
				$location.path( "/" );
			}
		}
		
		
		
		
	}])
	.controller('UserProfilCtrl', ['$scope', 'LoginResources', '$routeParams', '$uibModal',
	    function($scope, LoginResources, $routeParams, $uibModal) {
		
		var username = $routeParams.username;
		
		LoginResources.profile(username, callBackUser);

		$scope.profil = {};
		function callBackUser(success){
			$scope.profil = success;
		}
		
		$scope.setting = function() {
			var modalInstance = $uibModal.open({
				templateUrl: 'view/modals/settignProfil.html',
				controller: 'SetingProfilCtrl',
				scope: $scope,
				resolve: {
					resource: function() {
						return $scope.profil;
					}
				}
				
			});
		}
		
		$scope.address = function() {
			var modalInstance = $uibModal.open({
				templateUrl: 'view/modals/settignAdres.html',
				controller: 'SetingAddressCtrl',
				scope: $scope,
				resolve: {
					resource: function() {
						return $scope.profil;
					}
				}
				
			});
		}
		
	}])
	.controller('SetingProfilCtrl', ['$scope', 'LoginResources', '$routeParams', 'resource',
	    function($scope, LoginResources, $routeParams, resource) {
		
		$scope.user = resource;
		
		$scope.update = function() {
			console.log($scope.user);
			LoginResources.updateAddres($scope.user).then(function(data) {
				console.log(data);
			});
		}
		console.log("upao " +resource.username)
		
	}])
	.controller('SetingAddressCtrl', ['$scope', 'LoginResources', '$routeParams', 'resource',
	    function($scope, LoginResources, $routeParams, resource) {
		
		$scope.user = resource;
		
		$scope.update = function() {
			console.log($scope.profil.profilUserDTO)
			LoginResources.updateAddres($scope.profil.profilUserDTO).then(function(data) {
				console.log(data);
			});
		}
		console.log("upao " +resource.username)
		
	}]);