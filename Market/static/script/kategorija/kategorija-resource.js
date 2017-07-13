angular.module('appSBZ')
	.factory('KategorijaResource', ['Restangular', '_', function(Restangular, _) {
		
		
		var retVal = {};
		
		var category = [];
		var podcategory = [];
		
		var pretraga = [];
		
		retVal.getSearch = function (pretraga) {
			return Restangular.all('category/category/search').post(pretraga).then(function(rezultat) {
				pretraga = rezultat;
				return pretraga;
			})
		}
		
		retVal.getCategory = function () {
			return Restangular.all('category/').getList().then(function(rezultat) {
				category = rezultat;
				return category;
			})
		}
		
		retVal.getAllCategory = function() {
			return Restangular.all("category/category").getList().then(function(entries) {
				category = entries;
				return category;
			})
		}
		
		retVal.getAllPodCategory = function() {
			return Restangular.all("category/podcategory").getList().then(function(entries) {
				podcategory = entries;
				return podcategory;
			})
		}
		
		retVal.getForCategory = function() {
			return Restangular.all("category/category").getList().then(function(entries) {
				category = entries;
				return category;
			})
		}
		
		
		retVal.getAllPodByCategory = function(id) {
			return Restangular.all("category/podcategory/"+id).getList().then(function(entries) {
				podcategory = entries;
				return podcategory;
			})
		}
		
		
		return retVal;
	}])