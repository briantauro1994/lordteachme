'use strict';

app.controller('addressController', function($scope, $http, $timeout) {

	$("#saved_billing_addresses").hide();

	$scope.openBilling = function() {
		$("#saved_billing_addresses").slideToggle();
	}

	$scope.closeForm = function() {
		$("#add_address_form_div").slideUp();
	}
	$scope.setShippingForm = function() {
		$scope.t = 0;
		$scope.mess = "Shipping Address Form";
		$("#add_address_form_div").slideDown();
	};

	$scope.setBillingForm = function() {
		$scope.t = 1;
		$scope.mess = "Billing Address Form";
		$("#add_address_form_div").slideDown();
	};

	$scope.setShippingForm();

	$scope.sendShipping = function() {
		$scope.oba = {
			type : 0
		};
		$http({
			method : 'POST',
			url : 'v1/address/sendaddress',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : $scope.oba
		}).success(function(response) {
			if (JSON.stringify(response).length > 4) {
				$("#add_address_form_div").slideUp();
				$scope.shippingAddress = response;
			}
		});
	}

	$scope.sendBilling = function() {
		$scope.obb = {
			type : 1
		};
		$http({
			method : 'POST',
			url : 'v1/address/sendaddress',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : $scope.obb
		}).success(function(response) {
			if (JSON.stringify(response).length > 4) {
				$scope.billingAddress = response;
			}
		});
	}

	$scope.sendShipping();
	$scope.sendBilling();

	$scope.setAddress = function() {
		if ($scope.t == 0) {
			angular.extend($scope.shippingDetails, {
				type : 0
			});
		} else {
			angular.extend($scope.shippingDetails, {
				type : 1
			});
		}
		$http({
			method : 'POST',
			url : 'v1/address/saveadd',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : $scope.shippingDetails
		}).success(function(data) {
			if (data.error == 0) {
				// if (data.type == 0) {
				// $scope.sendShipping();
				// } else {
				// $scope.sendBilling();
				// }
				$scope.sendShipping();
				$scope.sendBilling();
				$("#add_address_form_div").slideUp();
				$("#add_shipping_address").val("Add Address");
			} else {
				$scope.opty = data;
			}
		});
	};

	$scope.setSBDetails = function() {
		if ($scope.checkbox == true) {
			$scope.Objecta = {
				type : 0
			};
			angular.extend($scope.Objecta, $scope.Shipping);
			$scope.Objectb = {
				type : 1
			};
			angular.extend($scope.Objectb, $scope.Billing);
			$scope.Object = [];
			$scope.Object.push($scope.Objecta);
			$scope.Object.push($scope.Objectb);
		} else {
			$scope.Objecta = {
				type : 0
			};
			angular.extend($scope.Objecta, $scope.Shipping);
			$scope.Object = [];
			$scope.Object.push($scope.Objecta);
		}
		$scope.reply = 'Waiting for Server Response';
		$("#info").slideDown();
		$http({
			method : 'POST',
			url : 'v1/address/passtoapi',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : $scope.Object
		}).success(function(data) {
			console.log(data);
			$("#payU").attr("action", data.action);
			$scope.Payu = data;
			$timeout(function() {
				$("#payU").attr("action", data.action);
				document.forms.payuForm.submit()
			}, 100);
		});
	};
});

app.controller('filterController', function($scope, $http, $stateParams) {
	$scope.Ss = [ {
		'name' : 'Red'
	}, {
		'name' : 'Blue'
	}, {
		'name' : 'Black'
	}, {
		'name' : 'Yellow'
	}, {
		'name' : 'Pink'
	}, {
		'name' : 'Green'
	}, {
		'name' : 'White'
	}, {
		'name' : 'Brown'
	}, {
		'name' : 'Gray'
	} ];

	$scope.getProducts = function(catid, subcatid) {
		$http({
			method : 'GET',
			url : 'deals?categoryid=' + catid + '&subcategoryid=' + subcatid,
		}).success(function(data) {
			$scope.filterProducts = data;
		});
	}

	$scope.addToCart = function() {
		// $("#cart-icon-fixed").finish().fadeIn('fast');
		var cart = $('#cart-icon-fixed');
		var imgtodrag = $(this).parent('.itema').find("img").eq(0);
		if (imgtodrag) {
			var imgclone = imgtodrag.clone().css({
				top : imgtodrag.offset().top,
				left : imgtodrag.offset().left
			}).appendTo($('body')).addClass("move-to-cart");
			setTimeout(function() {
				imgclone.addClass("animate");
				imgclone.css({
					"top" : cart.offset().top + 10
				});
				imgclone.css({
					"left" : cart.offset().left
				});
				imgclone.one(transitionEnd, function(event) {
					imgclone.off(transitionEnd);
					$(this).addClass("hide-img");
					setTimeout(function() {
						imgclone.one(transitionEnd, function(event) {
							imgclone.off(transitionEnd);
							$(this).detach();
						});
					}, 1);
				});
			}, 1);
			$("#cart-icon-fixed").fadeOut(2000);
		}
	};
	$scope.getProducts($stateParams.catid, $stateParams.subcatid);
});

app.controller('indexBodyController', function($scope, $rootScope) {
});

app.controller('receiptController', function($scope, $rootScope) {
});

app.controller('cartController', function($scope, $http) {
	$scope.getCart = function() {
		$http.get("cart/cview").success(function(response) {
			if (JSON.stringify(response).length > 4) {
				$scope.cartDetails = response;
			}
		});
	};

	$scope.setCart = function() {
		$http({
			method : 'POST',
			url : 'cart/mod',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : $scope.sendDetails
		}).success(function(data) {
			if (JSON.stringify(response).length > 4) {
				$scope.cartDetails = response;
			}
		});
	};
	$scope.getCart();
});

app.controller('productController', function($scope, $http, $stateParams, $state) {
	$http({
		method : 'GET',
		url : 'v1/products/getid?id=' + $stateParams.dealId,
	}).success(function(data) {
		$scope.product = data;
	});

	$scope.addToCartReturn = null;

	$scope.addToCart = function() {
		$scope.addToCartReturn = false;
		$scope.cartProduct = {};
		angular.extend($scope.cartProduct, {
			"id" : $scope.product.dealDtoId
		}, {
			"optionsDto" : $scope.product.optionsDto
		}, {
			"qty" : 1
		});
		$http({
			method : 'POST',
			url : 'cart/addtocart',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : $scope.cartProduct
		}).success(function(data) {
			if (data.string == "sucess") {
				$scope.alertFunction("Sucessfully Added to Cart", "success", 5000);
				$scope.addToCartReturn = true;
			}
		});
	};

	$scope.buyNow = function() {
		$scope.addToCart();
		if ($scope.addToCartReturn = true) {
			$state.go("App.Cart");
		} else {
			$scope.alertFunction("Error Please Wait", "danger", 5000);
		}
	};
});

app.controller('headerController', function($scope, $http, $rootScope, AuthFactory) {

	$scope.logout = function() {
		AuthFactory.logOut();
	};
	
	$scope.signinUser = function() {
		AuthFactory.signInUser($scope.Signin);
	};

	$scope.signupUser = function() {
		$http({
			method : 'POST',
			url : 'Customer/Register',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : $scope.Signup
		}).success(function(data) {
			if (data.string == "Success") {
				$scope.alertFunction("Signup Sucessful", "success", 5000);
				$('#signupModal').modal('hide');
			} else {
				$scope.alertFunction("Please Verify Your Details", "danger", 5000);
			}
		});
	};
});