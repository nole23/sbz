'use strict';

angular.module('appSBZ')
	.controller('CartAdminCtrl', ['$scope', 'CartAdminResource',
	function($scope, CartAdminResource) { 
		
		$scope.cart = [];
		CartAdminResource.getAllCart().then(function(data) {
			$scope.cart = data;
		})
		
		$scope.stanje = {};
		$scope.cartSearc = [];
		$scope.search = function() {
			console.log($scope.stanje);
			var saljem = '';
			if($scope.stanje.kreira == true){
				saljem = 'KREIRA';
			} else if($scope.stanje.porucivanje == true) {
				saljem = 'PORUCIVANJE';
			}
			else if($scope.stanje.kupljeno == true) {
				saljem = 'KUPLJENO';
			}
			else if($scope.stanje.otkazano == true) {
				saljem = 'OTKAZANO';
			}
			CartAdminResource.search(saljem).then(function(data) {
				$scope.cartSearc = data;
				console.log(data)
			})
		};
		
		$scope.prihvati = function(a) {
			console.log(a);
			CartAdminResource.kupi(a).then(function(data) {
				console.log(data);
			})
		}
	}])