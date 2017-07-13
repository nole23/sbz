angular.module('appSBZ')
	.factory('KategorijaAdminResource', ['Restangular', '_', function(Restangular, _) {
		
		
		var retVal = {};
		
		var message = [];
		var messageCat = [];
		var caktegorija = [];
		
		retVal.setPodCategory = function(podCategory) {
			return Restangular.all('category/add/pod/category').post(podCategory).then(function(entris) {
				message = entris;
				return message;
			})
		}
		
		retVal.setCategory = function(category) {
			return Restangular.all('category/add/category').post(category).then(function(data) {
				messageCat = data;
				return messageCat;
			})
		}
		
		retVal.getCategoryById = function(id) {
			return Restangular.one('category/'+id).get().then(function(data) {
				caktegorija = data;
				return caktegorija;
			})
		}
		
		retVal.update = function(kategorija) {
			return Restangular.one('category/update/category').post(kategorija).then(function(data) {
				message = data;
				return message;
			})
		}
		
		return retVal;
	}])