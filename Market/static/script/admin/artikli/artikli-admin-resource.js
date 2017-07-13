angular.module('appSBZ')
	.factory('ArticleAdminResource', ['Restangular', '_', function(Restangular, _) {
		
		
		var retVal = {};
		var message = [];
		var discount = [];
		var article = [];
		
		
		retVal.updatComplement = function(id, amount) {
			return Restangular.one('article/complement/update/'+id+'/'+amount).get().then(function(data) {
				article = data;
				return article;
			})
		}
		
		
		retVal.getAllComplement = function() {
			return Restangular.all('article/complement/all').getList().then(function(data) {
				article = data;
				return article;
			})
		}
		
		retVal.saveArticle = function(article) {
			return Restangular.all('article/add/article').post(article).then(function(data) {
				message = data;
				return message;
			})
		}
		
		retVal.getAllDiscount = function() {
			return Restangular.all('article/discount').getList().then(function(data) {
				discount = data;
				return discount;
			})
		}
		
		retVal.getAllArticle = function() {
			return Restangular.all('article/new').getList().then(function(data) {
				article = data;
				return article;
			})
		}
		
		return retVal;
	}])