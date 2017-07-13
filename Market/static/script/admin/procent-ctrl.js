'use strict';

angular.module('appSBZ')
	.controller('ProcentCtrl', ['$scope', 'ProcenatResource',
	    function($scope, ProcenatResource) {
		
		/*
		 * Ovde treba pogoditi kontroler koji ce nam vratiti broj svih 
		 * korisnika aplicakcije
		 * 
		 * $scope.size = {};
		 * ProcenatResource.getSizeAllUsere().then(function(data){
		 * 		$scope.size = data;
		 * })
		 */
		$scope.size = {};
		//ProcenatResource.getSizeUser().then(function(data) {
		//	$scope.size = data;
		//})
		
		/*
		 * Ovde treba pogoditi kontroler koji ce nam vretiti broj svih 
		 * artikala koji su aktivni, to znaci da ako se neki artikl ponavlja
		 * vise puta racuna se samo jednom
		 * 
		 * $scope.sizeArtikl = {};
		 * ProcenatResource.getSiyeAllArtikl().then(function(data){
		 * 		$scope.siyeArtikl = data;
		 * })
		 */
		$scope.siyeArtikl = {};
		ProcenatResource.getSiyeAllArtikl().then(function(data) {
			$scope.siyeArtikl = data;
		})

		
		/*
		 * Ovde treba da pogodi kontrole koji ce nam vratiti procenat posecenosti
		 * u toku dana koji se dobija podelom svih korisnika sa brojem onih koji su
		 * dosli bez obzira da li su se prijavili ili nisu
		 * 
		 * $scope.sizeView = {};
		 * ProcenatResource.getPosecenost().then(function(data){
		 * 		$scope.sizeView = data;
		 * })
		 */
		$scope.sizeView = 35;
		
		/*
		 * Ovde trebamo da pogodimo kontroler koji ce nam vratiti broj artikala
		 * koji su na snizenju
		 * 
		 * $scope.snizenje = {};
		 * ProcenatResource.getSnizenje().then(function(data){
		 * 		$scope.sinzemke = data;
		 * })
		 */
		
		$scope.snizenje = 10000;
	}])