(function () {
	angular
		.module('appSBZ')
		.factory('LoginResources', Service);
	
	function Service($http, $localStorage, $log, $window) {
		
		var service = {};
		
		service.login = login;
		service.getCurrentUser = getCurrentUser;
		service.logout = logout;
		service.registration = registration;
		service.profile = profile;
		
		return service;
		
		function login(user, callBack) {
			$http.post('http://localhost:8080/api/user/login', user)
			.success(function (response) {
				
				var currentUser = {
						username: user.username,
						token: response.jwt,
						rola: response.role,
						name: response.name,
						surname: response.surname
				};
				
				$localStorage.currentUser = currentUser;
				 $http.defaults.headers.common.Authorization = response.jwt;
				
				console.log(currentUser)
				callBack(response);
			})
		}
		
		function registration(user) {
			$http.post('http://localhost:8080/api/user/registration', user)
			.success(function (response) {
				
				console.log(response);
			})
		}
		
		function profile(username, callBack) {
			$http.get('http://localhost:8080/api/user/profile')
			.success(function(response) {
				callBack(response);
			})
		}
		
		function logout() {
            // uklonimo korisnika iz lokalnog skladišta
            delete $localStorage.currentUser;
            $http.defaults.headers.common.Authorization = '';
            $window.location = '#/login';
        }
		
		function getCurrentUser() {
            return $localStorage.currentUser;
        }
	}
})();