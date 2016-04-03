function verify() {
	var pass = $('#pass').val();
	$.ajax({
		type : 'POST',
		url : '/verifyPassword',
		data : {
			'pass' : pass
		},
		success : function(response) {
			window.open('/setting', '_self');
		},
		statusCode : {
			403 : function() {
				$('#pass').removeClass('active');
				$('#pass').val('');
				Materialize.toast('Inavlid Password. Please Try Again', 4000);
			}
		}
	});
	return false;
}

function update(){
	var name = $('#name').val();
	var pass = $('#pass').val();
	$.ajax({
		type : 'POST',
		url : '/update',
		data:{
			'name':name,
			'pass':pass
		},
		success : function(res){
			Materialize.toast('Profile Updated',
					4000);
		},
		error : function(res){
			Materialize.toast('Oops! Something went wrong. Please Try Again',
					4000);
		}
	});
	return false;
}