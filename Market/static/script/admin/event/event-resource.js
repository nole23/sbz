
angular.module('appSBZ')
	.factory('EventResource', ['Restangular', '_', function(Restangular, _) {
	
	
	var retVal = {};
	var message = [];
	
	retVal.addEvent = function(event) {
		
		return Restangular.all('admin/event').post(event).then(function(data) {
			message = data;
			return message;
		})
	}
	
	return retVal;
}])