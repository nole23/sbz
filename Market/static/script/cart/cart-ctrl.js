'use strict';

angular.module('appSBZ')
	.controller('AddToCart', ['$scope', 'CartResource', '$window', '$uibModal', '$localStorage',
	function($scope, CartResource, $window, $uibModal, $localStorage) {
		
		
		/*
		 * Funkcija koja nam omogucava da sacuvamo artikl u korpu
		 * za kupovinu pre nego napravimo racun
		 * 
		 * 
		 */
		$scope.loading = false;
		$scope.login = false;
		$scope.addCart = function(idArtikla) {
			var cart = {
					articleDTO: {
						id: idArtikla
					},
					count: 1
			};
			
			if(!$localStorage.currentUser){
				
				var modalInstance = $uibModal.open({
					templateUrl: 'view/modals/login.html',
					controller: 'LoginCtrl',
					scope: $scope
					
				});
				
			} else {
				$scope.loading = true;
				console.log("Ulogovan si ")
				CartResource.addCart(cart).then(function(message) {
					$scope.loading = false;
					if(message.error != null){
						$window.alert("Artikl postoji u bazi, idite u korpu ako zelite da povecata kolicinu!");
					}
				})
			}	
		}
		
	}])
	.controller('ShopCartCtrl', ['$scope', 'CartResource', '$route', '$uibModal', '$interval', '$localStorage',
	function($scope, CartResource, $route, $uibModal, $interval, $localStorage) {
		
		
		/*
		 * Ova funkcija nam vraca sve artikle koje zemo da kupimo da vidimo
		 * koliko kosta i pregled racuna pre nego odlucimo da kupimo
		 * 
		 * 
		 */
		$scope.loading = false;
		$scope.allCart = [];
		$scope.allCartIndex = [];
		CartResource.getAll("KREIRA").then(function(data) {
			$scope.allCart = data;
			
		})
		
		$scope.allCartIndex = {};
		if ($localStorage.currentUser != null) {
			$interval(function () {
				CartResource.getAll("KREIRA").then(function(data) {
					if(data != null){
						$scope.allCartIndex = data;
					} else {
						console.log("Uloguj se");
						$scope.allCartIndex = data;
					}
					
					
				})	 
			}, 3600);
		}
		
		
		
		$scope.plas = function(id, idAccount) {
			$scope.loading = true;
			CartResource.getPlas(id, idAccount).then(function(data) {
				$scope.loading = false;
				$route.reload();
			})
		}
		
		$scope.minus = function(id, idAccount) {
			$scope.loading = true;
			CartResource.getMinus(id, idAccount).then(function(data) {
				$scope.loading = false;
				$route.reload();
			})
		}
	
		$scope.deleteA = function(id, idAccount){
			$scope.loading = true;
			CartResource.deleteItem(id, idAccount).then(function(data) {
				$scope.loading = false;
				$route.reload();
			})
		}
		CartResource.nagradni().then(function(data) {
			if(data.message != null){
				$scope.bodovi = 0;
			} else {
				$scope.bodovi = data.size;
			}
		})
		

		
		$scope.kupiti = function(resource) {
			
			var modalInstance = $uibModal.open({
				templateUrl: 'view/modals/test.html',
				controller: 'KupovinaCtrl',
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
	.controller('KupovinaCtrl', ['$scope', 'CartResource', '$window', 'resource', '$uibModalInstance', '$location',
      	function($scope, CartResource, $window, resource, $uibModalInstance, $location) {
      		
			$scope.loo = true;
      		$scope.resource = resource;
      		CartResource.nagradni().then(function(data) {
    			if(data.message != null){
    				$scope.bodovi = 0;
    			} else {
    				$scope.bodovi = data.size;
    			}
    		})
    		
    		
    		$scope.popusti = {};
      		$scope.zbir = 0;
      		
    		CartResource.popust($scope.resource).then(function(data) {
				$scope.popusti = data;
				for(var i=0; i<data.length; i++){
					$scope.zbir = $scope.zbir +data[i].procenat;
				}
				$scope.loo = false;
			})
			
			$scope.otkazati = function(id) {
    			CartResource.otkazati(id).then(function(data) {
					console.log(data);
				});
    			$uibModalInstance.dismiss('cancel');
			}
    		
    		$scope.placanje = false;
    		$scope.plati = function() {
    			$scope.placanje = true;
			}
			
			$scope.narucitiKonacno = function(id, nesto) {
				$scope.loading = true;
				var zetoni = "";
				if(nesto){
					zetoni = "da"
				} else {
					zetoni = "ne";
				}
				
				CartResource.kupiti(id, zetoni).then(function(data) {
					console.log(data);
					$location.path( "/finis/account/"+id );
				})			
			}

      		
      		
      }])
	.controller('FinishAccountCtrl', ['$scope', 'CartResource', '$window', '$uibModal',
		function($scope, CartResource, $window, $uibModal) {
			
			
			$scope.allCart = [];
			CartResource.getAllKupljene().then(function(data) {
				$scope.allCart = data;
				console.log($scope.allCart)
			})
			
			$scope.stavke = function(resource) {
				
				var modalInstance = $uibModal.open({
					templateUrl: 'view/modals/lista.html',
					controller: 'ListaArtikalaCtrl',
					scope: $scope,
					windowClass: 'app-modal-window',
					resolve: {
						resource: function() {
							return resource;
						}
					}
				});
			}
			//treba ispisati racun
			
	}])
	.controller('ListaArtikalaCtrl', ['$scope', 'CartResource', '$window', '$uibModal', 'resource',
		function($scope, CartResource, $window, $uibModal, resource) {
			
			
		$scope.resource = resource;
		$scope.podaci = $scope.resource;
		console.log($scope.resource);
			
	}])
	.controller('FinisAccountListCtrl', ['$scope', 'CartResource', '$window', '$routeParams',
		function($scope, CartResource, $window, $routeParams ) {
			
		var id = $routeParams.id;
	
		$scope.account = {};
		$scope.popustProcenti = {};
		CartResource.getFinis(id).then(function(data) {
			$scope.account = data;
			
			var popustArtikala = 0;
			for(var i=0; i<data.listItemsAccountDTO.length; i++){
				popustArtikala += data.listItemsAccountDTO[i].itemsAccountDTO.discountPrecentage;
				//console.log(data.listItemsAccountDTO[i].itemsAccountDTO.discountPrecentage)
			}
			
			var popustRacuna = 0;
			for(var i=0; i<data.popustiDTO.length; i++){
				popustRacuna += data.popustiDTO[i].procenat;
			}
			
			$scope.popustProcenti = popustArtikala + popustRacuna;
		})
		
		
		
		
			
	}])
	
	