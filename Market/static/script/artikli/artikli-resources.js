angular.module('appSBZ')
	.factory('ArticleResource', ['Restangular', '_', function(Restangular, _) {
		
		
		var retVal = {};
		
		var article = [];
		var articleKat = [];
		var length = [];
		var articleOne = [];
		
		retVal.getNewArticle = function() {
			return Restangular.all("article/new").getList().then(function(entries) {
				article = entries;
				return article;
			})
		}
		
		retVal.getAllArticleByCategory = function(id) {
			return Restangular.all("article/category/"+id).getList().then(function(entries) {
				article = entries;
				return article;
			})
		}
		
		retVal.getSize = function() {
			return Restangular.all("article/length").get().then(function(data) {
				length = data;
				return length;
			})
		}
		
		retVal.getByCategory1 = function(id) {
			return Restangular.all('article/category/'+id).getList().then(function(data) {
				articleKat = data;
				return articleKat;
			})
		}
		
		retVal.getArticle = function(id) {
			return Restangular.one('article/new/'+id).get().then(function(data) {
				articleOne = data;
				return articleOne;
			})
		}
		
		retVal.getArticleDiscount = function(id) {
//			return Restangular.all('article/discount').getList().then(function(data) {
//				articleKat = data;
//				return articleKat;
//			})
			return articleKat;
		}
		
		return retVal;
	}])