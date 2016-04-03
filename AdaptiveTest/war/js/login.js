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
	console.log(email);
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