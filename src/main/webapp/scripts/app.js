var myApp = angular.module('app', ['ngResource']);

myApp.config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.timeout = 4000;
}])
.factory('CreditCard',['$resource', function($resource) {
  return $resource('/equipment/:eqID'); // Note the full endpoint address
}])
.filter('ccNumberFormatter', function() {
	  return function (creditCardNumber) {		    
		  return creditCardNumber.toString().replace(/[^0-9]/g, "").replace(/\W/gi, '').replace(/(.{4})/g, '$1 ');
	  };
})
.controller('mainCtrl',['$scope', '$http', '$httpParamSerializer','CreditCard','$timeout',  function($scope, $http, $httpParamSerializer,CreditCard,$timeout) {
	
	var recordsSize=5;	
	
	$scope.cards = [
		{ "name":"Ron",
		  "number":1234567890123,
		  "balance": 1450.50,
		  "limit": 2000
		}
	];
		
	$scope.createEquipment = function(equipmentObj){
		console.log(equipmentObj);
		Equipment.save(equipmentObj).$promise.then(function(res) {
		   // success
			$scope.eq = null;
			showAlert('alert-success','Successfully created new equipment');
		}, function(err) {
			$scope.eq = null;
			showAlert('alert-danger',err.data);
		});	
	}	
	
				
	$scope.getEquipmentList = function(recordsSize){
		
		if(recordsSize && recordsSize>0){
			
			var api = '/vasudev-assignment-kone/equipment/search?';		
			var qs = $httpParamSerializer({limit:recordsSize});		
			var url= api.concat(qs);
			
			$http({
				  method: 'GET',
				  url: url
			}).then(function successCallback(response) {
					
					$scope.tableData = response.data;
					console.log(JSON.stringify($scope.tableData[0].doc));
			}, function errorCallback(err) {
					$scope.tableData = null;
					$scope.hideAlert=false;
			});
		} else {			
			showAlert('alert-danger','Input must be greater than zero');				
		}			

		$scope.recordSize = null;
	}	

	$scope.getEquipmentList(recordsSize);

}]);

