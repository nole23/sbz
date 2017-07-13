'use strict';

angular.module('appSBZ')
	.controller('KontrolerCtrl', ['$scope', 'KategorijaResource', 'ArticleResource',
	    function($scope, KategorijaResource, ArticleResource) {
		
		/*
		 * Treba pozvati KategorjuResource
		 * iscitati sve kategorije i spojiti
		 * 
		 * KategorijaResource.getAllKategorija(data);
		 * $scope.kategorja = data;
		 */
		$scope.kategorije = [];
		KategorijaResource.getAllCategory().then(function(data) {
			$scope.kategorije = data;
			
		})
		
		
		
		/*
		 * Treba pozvati KategorjuResource
		 * iscitati sve kategorije i spojiti
		 * 
		 * KategorijaResource.getAllKategorija(data);
		 * $scope.kategorja = data;
		 */
		$scope.kat = [];
		KategorijaResource.getForCategory().then(function(data) {
			$scope.kat = data;
		})
		
		
		
		
		/*
		 * Treba pozvatei KategorijaResource
		 * Iscitati iz java.controller sve brendove kako bi pozvali
		 * iz u index.html
		 * Paziti da podaci koji dolaze budu ovo formata koji je zadat
		 * inace treba vrsiti blage promene u html a kod koji se menja nalazi
		 * se u index.html linija ~186
		 * 
		 * KategorijaResource.getAllBrends(data);
		 * $scope.brend = data;
		 */
		$scope.brend = [{name: "Asus", size: 50}, {name: "HP", size: 35}, {name: "Apple", size: 200}, {name: "Microsoft", size: 20}, {name: "...", size: 5}]
		
		
	}])
	.controller('SearchCtrl', ['$scope', 'KategorijaResource', '$location',
  	    function($scope, KategorijaResource, $location) {
  		
		$scope.search = {};
		$scope.pretraga = function() {
			
			var name = {};
			var pod = {};
			var min = {};
			var max = {};
			
			if($scope.search.nameArticle == undefined){
				name = null;
			} else {
				name = $scope.search.nameArticle;
			}
			
			if($scope.search.podcategory == undefined){
				pod = null;
			} else {
				pod = $scope.search.podcategory;
			}
			
			if($scope.search.minCena == undefined){
				min = null;
			} else {
				min = $scope.search.minCena;
			}
			
			if($scope.search.maxCena == undefined){
				max = null;
			} else {
				max = $scope.search.maxCena;
			}
			
  			$location.path( "/search/"+name+'$'+pod+'$'+min+'$'+max );
  			
		}
  		
  	}])
  	.controller('ProbaCtrl', ['$scope', 'KategorijaResource', '$routeParams',
  	    function($scope, KategorijaResource, $routeParams) {
  		
  		$scope.id = $routeParams.parametar;
  		$scope.search = $scope.id.split('$');
  		
  		$scope.nameArticle = $scope.search[0];
  		$scope.podcategory = $scope.search[1];
  		$scope.min = $scope.search[2];
  		$scope.max = $scope.search[3];
  		
	
  		var search1 = {
  				nameArticle: $scope.nameArticle,
				podCategory: $scope.podcategory,
				minCena: $scope.min,
				maxCena: $scope.max
		};
  		
  		KategorijaResource.getSearch(search1).then(function(odg) {
			$scope.artiklKategorije = odg;
		})
  	}])
  	.controller('AllCategoryCtrl', ['$scope', 'KategorijaResource', '$routeParams',
  	    function($scope, KategorijaResource, $routeParams) {
  		
  		$scope.allKategory = [];
  		KategorijaResource.getCategory().then(function(data) {
			$scope.allKategory = data;
		})
  		
  	}])