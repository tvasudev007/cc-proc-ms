var myApp = angular.module('app', ['ui.bootstrap','ngResource']);

myApp.config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.timeout = 4000;
}])
.factory('CreditCard',['$resource', function($resource) {
	
	const API_URL = '/v1/api/creditCards/:id';
	const PARAMS  = {};
	const ACTIONS = null;
	
  return $resource(API_URL, PARAMS, {
	    'query': {
	        method:  'GET',
	        isArray: true
	      },
	      'create': {
	          method: 'POST'
	      }
  });
}])
.filter('ccNumberFormatter', function() {
	  return function (creditCardNumber) {		    
		  return creditCardNumber.toString().replace(/[^0-9]/g, "").replace(/\W/gi, '').replace(/(.{4})/g, '$1 ');
	  };
})
.controller('mainCtrl',['$scope', '$http', '$httpParamSerializer','CreditCard','$timeout',  function($scope, $http, $httpParamSerializer,CreditCard,$timeout) {
	
	const DEFAULT_BAL = 0;
	
	const SUCCESS_MESSAGE = "Card was created successfully!!";
	
	$scope.regExpPatternForName = new RegExp("^[a-zA-Z0-9 _#.-]*$");
	$scope.regExpPatternForCardNumber = new RegExp("^[0-9]{10,19}$");
	$scope.regExpPatternForLimit = new RegExp("^-?\\d{1,19}$");
	
	var init =  function(){
		
		$scope.alerts = [];
		
		CreditCard.query({}, (result) => {
			$scope.cards = result;	        
	    	});
		};
	
		  $scope.closeAlert = function(index) {
		    $scope.alerts.splice(index, 1);
		  };

	$scope.addCard = function(){
		
		$scope.alerts = [];
		
		$scope.cc.balance = DEFAULT_BAL;
		$scope.cc.cardNumber = $scope.cc.cardNumber.toString();
		
		var cardObj = angular.copy($scope.cc);
		
		$scope.cc={};
		
		CreditCard.create(cardObj).$promise.then(function(res) {
			$scope.alerts.push({ type:'success', msg: SUCCESS_MESSAGE});
			$scope.cards.push(cardObj);
		}, function(err) {
			$scope.alerts.push({ type:'danger', msg: err.data.message});
		});	
	}	
	
	init();
	

}]);

