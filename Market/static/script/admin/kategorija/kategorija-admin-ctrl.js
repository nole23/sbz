'use strict';

angular.module('appSBZ')
	.controller('KategorijaAddCtrl', ['$scope', 'KategorijaAdminResource',
	function($scope, KategorijaAdminResource) { 
		
		/*
		 * Sacuvamo kategoriju
		 * 
		 */
		$scope.kategorija = {};
		$scope.addCategory = function() {
			KategorijaAdminResource.setCategory($scope.kategorija).then(function(data) {
				console.log(data);
			})
		}
		
		
		
		
		
	}])
	.controller('PodKategorijaAddCtrl', ['$scope', 'KategorijaResource', 'KategorijaAdminResource', '$uibModal',
	function($scope, KategorijaResource, KategorijaAdminResource, $uibModal) {
		
		/*
		 * Sacuvamo podkategoriju
		 * 
		 */
		
		$scope.category = []
		KategorijaResource.getAllCategory().then(function(data) {
			$scope.category = data;
			console.log($scope.category);
		})
		
		$scope.podKategorija= {};
		$scope.addPodCategory = function() {
			KategorijaAdminResource.setPodCategory($scope.podKategorija).then(function(data) {
				console.log($scope.podKategorija)
			})
		}
		
		
		$scope.setingCategory = function(resource) {
			
			var modalInstance = $uibModal.open({
				templateUrl: 'view/modals/setingCategory.html',
				controller: 'SettingKategoryCtrl',
				scope: $scope,
				windowClass: 'app-modal-window',
				resolve: {
					resource: function() {
						return resource;
					}
				}
			});
		}
	}])
	.controller('SettingKategoryCtrl', ['$scope', 'KategorijaAdminResource', 'resource',
     	function($scope, KategorijaAdminResource, resource) {
     		
			$scope.idCat = resource;
			$scope.category = {};
			KategorijaAdminResource.getCategoryById($scope.idCat).then(function(data) {
				$scope.category = data;
			})
			
			$scope.save = function() {
				KategorijaAdminResource.update($scope.category).then(function(data) {
					console.log(data);
				})
			}
     }])