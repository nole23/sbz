angular.module('appSBZ')
	.factory('CartResource', ['Restangular', '_', function(Restangular, _) {
		
		
		var retVal = {};
		var message = [];
		var cart = [];
		var poruka = [];
		var bodovi = [];
		var kupiti = [];
		var popust = [];
		
		
		retVal.otkazati = function(id) {
			console.log(id);
			return Restangular.one('cart/otkazati/popuste/'+id).get().then(function(data) {
				message = data;
				return message
			})
		}
		
		retVal.getFinis = function(id) {
			return Restangular.one('cart/finis/'+id).get().then(function(entries) {
				cart = entries;
				return cart;
			})
		}
		
		retVal.popust = function(id) {
			console.log(id);
			return Restangular.one('cart/popust/'+id).get().then(function(data) {
				popust = data;
				return popust;
			})
		}
		
		retVal.kupiti = function(id, zetoni) {
			return Restangular.one('cart/kupiti/'+id+'/'+zetoni).get().then(function(entries) {
				kupiti = entries;
				return kupiti;
			})
		}
		
		retVal.addCart = function(id) {
			return Restangular.all('cart/add').post(id).then(function(entries) {
				message = entries;
				return message;
			})
		}
		
		retVal.getAll = function(id) {
			return Restangular.one('cart/all/'+id).get().then(function(entries) {
				cart = entries;
				return cart;
			})
		}
		
		retVal.getAllKupljene = function() {
			return Restangular.one('cart/all').get().then(function(entries) {
				cart = entries;
				return cart;
			})
		}
		
		retVal.getPlas = function(id, idAccount) {
			console.log(id);
			return Restangular.one('cart/minus/plus/'+id+'/'+idAccount).get().then(function(nesto) {
				poruka = nesto;
				return poruka;
			})
		}
		
		retVal.getMinus = function(id, idAccount) {
			console.log(id);
			return Restangular.one('cart/minus/minus/'+id+'/'+idAccount).get().then(function(nesto) {
				poruka = nesto;
				return poruka;
			})
		}
		
		retVal.deleteItem = function(id, idAccount) {
			return Restangular.one('cart/delete/'+id+'/'+idAccount).get().then(function(nesto) {
				piruka = nesto;
				return poruka;
			})
		}
		
		retVal.nagradni = function() {
			return Restangular.one('user/rewared/points').get().then(function(nesto) {
				bodovi = nesto;
				return bodovi;
			})
		}
		
		return retVal;
	}])