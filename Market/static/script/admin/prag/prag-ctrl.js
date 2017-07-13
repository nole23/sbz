'use strict';

angular.module('appSBZ')
	.controller('SperdingCtrl', ['$scope', 'SpedingResource', '$uibModal',
	    function($scope, SpedingResource, $uibModal) {
		
		console.log("Upao")
		$scope.prag = [];
		SpedingResource.getAllPrag().then(function(date) {
			$scope.prag = date;
			console.log(date)
		})
		
		$scope.edit = function(resource) {
			
			var modalInstance = $uibModal.open({
				templateUrl: 'view/modals/pragPotrosnje.html',
				controller: 'SetignPragCtrl',
				scope: $scope,
				resolve: {
					resource: function() {
						return resource;
					}
				}
			});
		}
	}])
	.controller('SetignPragCtrl', ['$scope', 'SpedingResource', '$window', '$uibModal', 'resource', '$uibModalInstance',
 	    function($scope, SpedingResource, $window, $uibModal, resource, $uibModalInstance) {
 		
		$scope.speding = resource;
		$scope.prag = {};
		SpedingResource.onePrag($scope.speding).then(function(data) {
			$scope.prag = data;

		})
		
		$scope.save = function() {
			SpedingResource.update($scope.prag).then(function(data) {
				$window.alert("radi")
			})
		}
 		
 	}])