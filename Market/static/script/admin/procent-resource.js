angular.module('appSBZ')
	.factory('ProcenatResource', ['Restangular', '_', function(Restangular, _) {
		
		
		var retVal = {};
		
		var sizeUser = [];
		var sizeArticle = [];
		
		retVal.getSizeUser = function() {
			return Restangular.one('user/size').get().then(function(data) {
				sizeUser = data;
				return sizeUser;
			})
		}
		
		retVal.getSiyeAllArtikl = function() {
			return Restangular.one('article/length').get().then(function(data) {
				sizeArticle = data;
				return sizeArticle;
			})
		}
		
		return retVal;
	}])