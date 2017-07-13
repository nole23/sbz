angular.module('appSBZ')
	.factory('SpedingResource', ['Restangular', '_', function(Restangular, _) {
		
		
		var retVal = {};
		var prag = [];
		var message = [];
		
		retVal.getAllPrag = function() {
			return Restangular.all('prag/all').getList().then(function(data) {
				prag = data;
				return prag;
			})
		}
		
		retVal.onePrag = function(id) {
			return Restangular.one('prag/one/'+id).get().then(function(data) {
				prag = data;
				return prag;
			})
		}
		
		retVal.update = function(prag) {
			return Restangular.all('prag/update').post(prag).then(function(data) {
				message = data;
				return message;
			})
		}
		
		
		
		return retVal;
	}])