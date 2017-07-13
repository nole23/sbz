'use strict';

angular.module('appSBZ')
	.controller('ArtileKontroler', ['$scope', 'ArticleResource',
	    function($scope, ArticleResource) {
		
		
		/*
		 * Prvo iscitavamo najnovije artikle, i ne vise od 6komada
		 * To se nalazi u prvom planu,
		 * pocetna.html je stranica koja obradjuje ovaj deo
		 * 
		 * $scope.artikli = [];
		 * ArtiklResource.getNewArticle().then(function(data) {
		 *     $scope.artikli = data;
		 * }
		 */
		$scope.artikli = []
		ArticleResource.getNewArticle().then(function(data) {
			$scope.artikli = data;
		})

		
		
		
		
		
		
		/*
		 * Iscitati po tri za svaku kategoriju dole navedenu
		 * Te kategorije prestavljaju kategorije koje su najcesce posecene
		 * Ne treba praviti da sistem sam to odrajduje, nema se vremen
		 * pocetna.html
		 * 
		 * Kategorije su (Kategorija1, Kategorija2, Kategorija3, Kategorija4)
		 * 
		 * Ovo odraditi za svaku kategoriju gore naveednu
		 * 
		 * $scope.artiklKategorije = [;
		 * ArticleResource.getArticleByCategory(idKategory).then(function(data) {
		 * 		$scope.artiklKategorije = data;
		 * })
		 */
		$scope.artiklKategorije1 = [];
		ArticleResource.getByCategory1(1).then(function(data) {
			$scope.artiklKategorije1 = data;
		})
		
		$scope.artiklKategorije2 = [];
		ArticleResource.getByCategory1(2).then(function(data) {
			$scope.artiklKategorije2 = data;
		})
		
		$scope.artiklKategorije3 = [];
		ArticleResource.getByCategory1(3).then(function(data) {
			$scope.artiklKategorije3 = data;
		})
		
		$scope.artiklKategorije4 = [];
		ArticleResource.getByCategory1(4).then(function(data) {
			$scope.artiklKategorije4 = data;
		})

		
		/*
		 * Iscitavanje proizvoda koji su na popustu datog dana
		 * To se prikazuje u 
		 * pocetna.html ~161 stranica
		 * 
		 * $scope.artikliPopust = [;
		 * ArticleResource.getArticleDiscount().then(function(data){
		 * 		$scope.artikliPopust = data;
		 * })
		 */
		$scope.artikliPopust = [];
//		ArticleResource.getArticleDiscount(4).then(function(data) {
//			$scope.artikliPopust = data;
//		})
		 		
	}])
	.controller('ArtikKategorijaCtrl', ['$scope', '$routeParams', 'ArticleResource',
	    function($scope, $routeParams, ArticleResource) {
			var id = $routeParams.id;
			var kat = $routeParams.kategorija;
			console.log(id);
			/*
			 * Ovde iscitavamo sve artikle date kategorije, 
			 * kao parametar za pretragu kategorije koristimo naziv kategorije
			 * i to kao 
			 * 
			 * var kat = $routeParams.id;
			 * $scope.artiklKategorije = [;
			 * ArticleResource.getArticleByKategory(kat).then(function(data){
			 * 		$scope.artiklKategorije = data;
			 * 		$scope.kategorija = kat;
			 * })
			 */
			$scope.artiklKategorije = [];
			ArticleResource.getAllArticleByCategory(id).then(function(data) {
				$scope.artiklKategorije = data;
				console.log(data);
			})
			$scope.kategorija = kat;
			
			
			
			/*
			 * Kada iscitavamo jedan artikli trebamo paziti sta sve pribavljamo od podataka
			 * Te podatke prosledjujemo stranici koja i
			 */
			$scope.jedanArtikl = [
			    {nameArticle: 'Naziv Artikla1', price: 35400.00, amount: 350, dataAddArticle: 'Objavljeno tad',
			    articleDiscount: { articleDiscount: 5, statusDiscount: 'Letnja rasprodaja'},
				categoryArticle: {nameCategory: 'Naziv Kategorije', podCategory: {namePodCateogry: 'naziv podkategorje'}}}
					
			];
	}])
		.controller('JedanArtiklCtrl', ['$scope', '$routeParams', 'ArticleResource',
	    function($scope, $routeParams, ArticleResource) {
			var id = $routeParams.id;
			
			
			
			/*
			 * Kada zelimo da prikazemo samo jedan artikl prosledjujemo
			 * id tog artikla pomocu rest vracamo taj artikl i kacimo za scope
			 * 
			 * Takodje na osnovu id trebamo da vratimo sve slike dataog
			 * artikla, kako bi se prikayivale kako treba
			 * 
			 * Takodje treba na osnovu id artikla iscitati zadnjih 10 komentara
			 * sto su ostavili na dati artikli
			 * 
			 * prikaz jednog artikla se vrsi u 
			 * article.html
			 * 
			 * $scope.artikl = {};
			 * ArticleResource.getArticleOne(id).then(function(data) {
			 * 		$scope.artikl = data;
			 * })
			 */
			$scope.artikl = {};
			ArticleResource.getArticle(id).then(function(data) {
				$scope.artikl = data;
				console.log(data);
			})
			
			
			$scope.ariklMessage = [{user: {id: 1, fName: "Stefan"}, message: 'Ovaj artikli je veoma kvalitetan, jako mi se svidja dobar je', dateTime:'17:16', dateDay: '30.6.2017'},
			                       {user: {id: 2, fName: "Novica"}, message: 'Sranje u bojama', dateTime:'17:16', dateDay: '30.6.2017'},
			                       {user: {id: 3, fName: "Dragan"}, message: 'Super je', dateTime:'17:16', dateDay: '30.6.2017'}]
	}])
	