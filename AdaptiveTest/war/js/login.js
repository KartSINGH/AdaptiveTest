$(document).ready(function() {
	$('.slider').slider({
		indicators : false,
		full_width : true,
		interval : 2000
	});
	$('select').material_select();
});

function login() {
	var email = $("#id").val();
	var pass = $("#pass").val();
	$
			.ajax({
				type : "POST",
				url : "/login",
				data : {
					'email' : email,
					'pass' : pass
				},
				success : function(response) {
					window.open("/user", "_self");
				},
				statusCode : {
					403 : function() {
						$("#pass").removeClass("active");
						$("#pass").val("");
						Materialize.toast('Inavlid Password. Please Try Again',
								4000);
					},
					404 : function() {
						$('#loginform')[0].reset();
						$("#id").removeClass("active");
						$("#pass").removeClass("active");
						Materialize
								.toast(
										'Email ID does not Exist. Please Register to Continue.',
										4000);
					}
				}
			});
	return false;
}

function register() {
	var email = $("#email").val();
	var pass = $("#pass").val();
	var name = $("#name").val();
	var branch = $("#branch").find(":selected").text();
	$.ajax({
		type : "POST",
		url : "/register",
		data : {
			'email' : email,
			'pass' : pass,
			'name' : name,
			'branch' : branch
		},
		success : function(response) {
			Materialize.toast('Please Login to Continue.', 2000, '',
					function() {
						window.open("/loginPage", "_self")
					});
		},
		statusCode : {
			403 : function() {
				$('#regform')[0].reset();
				$("#email").removeClass("active");
				$("#pass").removeClass("active");
				$("#name").removeClass("active");
				Materialize.toast('Email ID already in Use.', 4000);
			}
		}
	});
	return false;
}

function forgot() {
	var email = $("#id").val();
	$
			.ajax({
				type : "POST",
				url : "/forgot",
				data : {
					'email' : email
				},
				success : function(response) {
					$('#forgotform')[0].reset();
					$("#id").removeClass("active");
					Materialize
							.toast(
									'Email sent to your registered email-id. Please check your mail for further instructions',
									4000, '', function() {
										window.open("/loginPage", "_self")
									});
				},
				statusCode : {
					403 : function() {
						$("#pass").removeClass("active");
						$("#pass").val("");
						Materialize
								.toast(
										'Oops! Something went wrong. Please try again.',
										4000);
					},
					404 : function() {
						$('#forgotform')[0].reset();
						$("#id").removeClass("active");
						Materialize
								.toast(
										'Email ID does not Exist. Please Register to Continue.',
										4000, '', function() {
											window.open("/registerPage",
													"_self")
										});
					}
				}
			});
	return false;
}

function update() {
	var email = $("#id").val();
	var pass = $("#pass").val();
	console.log(email + pass);
	$.ajax({
		type : "POST",
		url : "/updatePassword",
		data : {
			'email' : email,
			'pass' : pass
		},
		success : function(response) {
			Materialize.toast('Password Updated. Please Login to Continue.',
					4000, '', function() {
						window.open("/loginPage", "_self")
					});
		},
		error : function(response) {
			Materialize.toast('Oops! Something went wrong. Please try again.',
					4000);
		}
	});
	return false;
}