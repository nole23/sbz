angular.module('appSBZ')
	.factory('CartAdminResource', ['Restangular', '_', function(Restangular, _) {
		
		
		var retVal = {};
		
		var cart = [];
		var message = [];
		
		retVal.getAllCart = function() {
			return Restangular.all('').getList().then(function(data) {
				cart = data;
				return cart;
			})
		}
		
		retVal.search = function(search) {
			
			console.log(search)
			return Restangular.all('cart/admin/all/'+search).getList().then(function(data) {
				cart = data;
				return cart;
			})
		}
		
		retVal.kupi = function(a) {
			return Restangular.all('cart/admin/prihvati').post(a).then(function(data) {
				message = data;
				return message;
			})
		}
		
		return retVal;
	}])