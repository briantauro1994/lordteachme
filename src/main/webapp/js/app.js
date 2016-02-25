'use strict';

var app = angular.module('myApp', [ 'ngAnimate', 'ui.router' ]);

app.factory('AuthFactory', function($rootScope, $http, $state) {

	var userAuthentication = {
		status : false,
		userName : "wait"
	};

	setUserCredentials(false, "wait");

	userAuthentication.signInUser = function(credentials) {
		$http({
			method : 'POST',
			url : 'Customer/Login',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : credentials
		}).success(function(data) {
			if (data.password == "Access Denied") {
				setUserCredentials(false, null);
				$rootScope.alertFunction("Invalid Credentials! Please Try Again...", "danger", 5000);
			} else {
				$rootScope.alertFunction("Signin Successfull", "success", 5000);
				setUserCredentials(true, data.username);
				$('#signinModal').modal('hide');
			}
		}).error(function() {
			$rootScope.signinmessage = "Error! Plese Try Later...";
		});

		return userAuthentication;
	}

	userAuthentication.logOut = function() {
		$http.get("Customer/Logout").success(function(data) {
			setUserCredentials(false, null);
			$rootScope.alertFunction("Logout Successfull", "success", 5000);
			$state.go("App");
		}).error(function() {
			$rootScope.alertFunction("Error", "danger", 5000);
			$state.go("App");
		});
	}

	userAuthentication.setUserCredentials = function() {
		return userAuthentication;
	};

	userAuthentication.setUserData = function(statusin, userNamein) {
		setUserCredentials(statusin, userNamein);
	};

	userAuthentication.isUserLoggedin = function() {
		return userAuthentication.status;
	};

	userAuthentication.returnUserName = function() {
		return userAuthentication.userName;
	};

	function setUserCredentials(statusin, userNamein) {

		$rootScope.User = {
			status : statusin,
			userName : userNamein
		};
		userAuthentication = {
			status : statusin,
			userName : userNamein
		};
		console.log($rootScope.User);
	}

	return userAuthentication;

});

app.run(function($rootScope, $http, $timeout, AuthFactory, $state, $location) {
	var p = 0;
	$http.get("Customer/CheckLogin").success(function(response) {
		p = 1;
		if (response.password == "Access Granted") {
			AuthFactory.setUserData(true, response.username);
		} else {
			AuthFactory.setUserData(false, null);
		}
		console.log("asd");
	});
	$http.get("catlist").success(function(response) {
		// alert(JSON.stringify(response));
		$rootScope.rootCategories = response;
	});

	$rootScope.$on('$stateChangeSuccess', function() {
		document.body.scrollTop = document.documentElement.scrollTop = 0;
	});

	var promise;

	$rootScope.alertFunction = function(message, type, period) {
		$timeout.cancel(promise);
		$rootScope.Alert = {};
		$rootScope.Alert.alertDisplay = true;
		$rootScope.Alert.alertMessage = message;
		$rootScope.Alert.alertType = 'alert-' + type;
		promise = $timeout(function() {
			$rootScope.Alert.alertDisplay = false;
		}, period);
	}

	$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
		if (p == 0) {
			$timeout(function() {
				magic();
			}, 500);
		} else {
			magic();
		}
		function magic() {
			console.log(fromState);
			if (toState.authenticate && !AuthFactory.isUserLoggedin()) {
				console.log("asdasd");
				event.preventDefault();
				if (fromState.authenticate == true || fromState.name == "") {
					$state.go("App");
					$('#signinModal').modal('show');
				} else {
					$('#signinModal').modal('show');
				}
			}
		}
	});

	$rootScope.$watch('User', function() {
		console.log($state);
	});
});

app.directive('addToCartButton', function() {
	function link(scope, element, attributes) {
		element.on('click', function(event) {
			var cartElem = angular.element(document.getElementsByClassName("shopping-cart"));
			console.log(cartElem);
			var offsetTopCart = cartElem.prop('offsetTop');
			var offsetLeftCart = cartElem.prop('offsetLeft');
			var widthCart = cartElem.prop('offsetWidth');
			var heightCart = cartElem.prop('offsetHeight');
			var imgElem = angular.element(event.target.parentNode.parentNode.childNodes[1]);
			var parentElem = angular.element(event.target.parentNode.parentNode);
			var offsetLeft = imgElem.prop("offsetLeft");
			var offsetTop = imgElem.prop("offsetTop");
			var imgSrc = imgElem.prop("currentSrc");
			console.log(offsetLeft + ' ' + offsetTop + ' ' + imgSrc);
			var imgClone = angular.element('<img src="' + imgSrc + '"/>');
			imgClone.css({
				'height' : '150px',
				'position' : 'absolute',
				'top' : offsetTop + 'px',
				'left' : offsetLeft + 'px',
				'opacity' : 0.5
			});
			imgClone.addClass('itemaddedanimate');
			parentElem.append(imgClone);
			setTimeout(function() {
				imgClone.css({
					'height' : '75px',
					'top' : (offsetTopCart + heightCart / 2) + 'px',
					'left' : (offsetLeftCart + widthCart / 2) + 'px',
					'opacity' : 0.5
				});
			}, 100);
			setTimeout(function() {
				imgClone.css({
					'height' : 0,
					'opacity' : 0.5

				});
				cartElem.addClass('shakeit');
			}, 1000);
			setTimeout(function() {
				cartElem.removeClass('shakeit');
				imgClone.remove();
			}, 1500);
		});
	}
	;

	return {
		restrict : 'E',
		link : link,
		transclude : true,
		replace : true,
		scope : {},
		template : '<button class="add-to-cart" ng-transclude></button>'
	};
});

app.config([ '$stateProvider', '$urlRouterProvider', "$locationProvider", function($stateProvider, $urlRouterProvider, $locationProvider) {
	$urlRouterProvider.otherwise('/');

	$stateProvider.state('App', {
		url : '/',
		views : {
			'header' : {
				templateUrl : 'partials/header.html',
				controller : 'headerController'
			},
			'body' : {
				templateUrl : 'partials/indexbody.html',
				controller : 'indexBodyController'
			},
			'footer' : {
				templateUrl : 'partials/footer.html'
			}
		}
	}).state('App.Cart', {
		url : 'Cart',
		views : {
			'body@' : {
				templateUrl : 'partials/cart.html',
				controller : 'cartController'
			}
		}
	}).state('App.Address', {
		url : 'Address',
		views : {
			'body@' : {
				templateUrl : 'partials/address.html',
				controller : 'addressController'
			}
		},
		authenticate : true
	}).state('App.filter', {
		url : 'filter/:catid/:subcatid',
		views : {
			'body@' : {
				templateUrl : 'partials/filter.html',
				controller : 'filterController'
			}
		}
	}).state('App.Product', {
		url : 'Product/:dealId/:dealName',
		views : {
			'body@' : {
				templateUrl : 'partials/product.html',
				controller : 'productController'
			}
		}
	}).state('App.Receipt', {
		url : 'Receipt',
		views : {
			'body@' : {
				templateUrl : 'partials/receipt.html',
				controller : 'receiptController'
			}
		},
		authenticate : true
	});

	// $locationProvider.html5Mode(true);
} ]);

app.filter('nospace', function() {
	return function(value) {
		return (!value) ? '' : value.replace(/ /g, '');
	};
});

app.filter('range', function() {
	return function(val, range) {
		range = parseInt(range);
		for (var i = 1; i <= range; i++)
			val.push(i);
		return val;
	};
});
