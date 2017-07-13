
'use strict';

angular.module('appSBZ')
	.controller('EventCtrl', ['$scope', 'EventResource',
	function($scope, EventResource) { 
		
		/*
		 * Dodavanje novog dogadjaja 
		 * 
		 */
		$scope.addEvent = function() {
			EventResource.addEvent($scope.event).then(function(message) {
				console.log(message);
			})
		}
		
	}])