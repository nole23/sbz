'use strict';

angular.module('appSBZ')
	.controller('ArtikliAddCtrl', ['$scope', '$window', 'ArticleAdminResource', 'KategorijaResource',
	    function($scope, $window, ArticleAdminResource, KategorijaResource){
		
		
		/*
		 * Ovde iscitajemo sve kategorije kako bi mogli da spojimo artikl sa datom kategorijom
		 * 
		 * $scope.category = [];
		 * ArtikliAdminResource.getAllCategory().then(function(data){
		 * 		$scope.category = data;
		 * })
		 */
		$scope.artikl;
		$scope.dopuna = function(id) {
			ArtikliAdminResource.updatComplement(id, $scope.artikl.amount).then(function(data) {
				console.log(data);
			})
		}
		
		
		$scope.category = [];
		KategorijaResource.getAllCategory().then(function(data) {
			$scope.category = data;
			console.log(data);
		})
		
		
		
		
		$scope.article = {};
		$scope.message = {};
		$scope.addArticle = function() {
			ArticleAdminResource.saveArticle($scope.article).then(function(data) {
				$scope.message = data.message;
				$window.alert('Sacuvano');
			})
		}
		
		$scope.podcategory = []
		
		$scope.radi = function() {
			console.log();
			
			KategorijaResource.getAllPodByCategory($scope.id).then(function(data) {
				$scope.podcategory = data;
			})
		}
	}])
	.controller('ArtiklAdminCtrl', ['$scope', 'ArticleAdminResource',
	    function($scope, ArticleAdminResource) {
			
			/*
			 * Treba da iscitamo sve artikle na primer prvih od 0-10
			 * pa klikom na drugme napred idemo od 10-20 i sve tako dok ne iscitamo sve
			 * aktivne artikle i nekativne
			 * 
			 * Treba istraziti kako da prolazi kroz deo artikala
			 */
			$scope.article = [];
			ArticleAdminResource.getAllArticle().then(function(data) {
				$scope.article = data;
				
			})


			
	}])
	.controller('ArtiklNedostajeCtrl', ['$scope', 'ArticleAdminResource', '$interval',
	    function($scope, ArticleAdminResource, $interval) {

			$scope.article = [];
			//$interval(function () {
				ArticleAdminResource.getAllComplement().then(function(data) {
					$scope.article = data;
					
				})
			//}, 60000);
			
	
	}])
	.controller('ArticlePorucivanjeCtrl', ['$scope', 'ArticleAdminResource', '$interval',
	    function($scope, ArticleAdminResource, $interval) {

			$scope.article = [];
			//$interval(function () {
				ArticleAdminResource.getAllComplement().then(function(data) {
					$scope.article = data;
					console.log(data);
				})
			//}, 60000);
			
			$scope.articl = {};
			$scope.loading = false;
			$scope.addAmount = function(id) {
				$scope.loading = true;
				ArticleAdminResource.updatComplement(id, $scope.articl.amount).then(function(data) {
					$scope.loading = false;
				})
			}
			
			$scope.otkazati = function(id) {
				
				ArticleAdminResource.otkazatArtikl(id).then(function(data) {
					console.log(data)
				})
			}
	
	}])
	