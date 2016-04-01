var minutes = 2;
var seconds = 0;
var flag
var color = true;
var testid = null;
var counter = 0;
var limit = 0;

function updateClock() {
	if (seconds == 0) {
		minutes--;
		seconds = 60;
	}
	seconds--;
	if (seconds <= 11 && minutes == 0) {
		if (flag) {
			$("#timer").addClass("red-text");
			flag = false;
		} else {
			$("#timer").removeClass("red-text");
			flag = true;
		}
	}
	if (seconds == 0 && minutes == 0) {
		alert("Test Completed");
		window.open("/user", "_self");
	}

	var time = "Time Remaining: " + minutes + ":" + seconds;

	$("#timer").html(time);

}

function initiate(response) {
	var rs = jQuery.parseJSON(response);
	time = rs[0].time;
	testid = rs[1].testid;
	limit = rs[2].no;
	getQuestion();
}

function getQuestion() {
	if (counter > limit) {
		alert("Test Completed");
		window.open("/user", "_self");
	} else {
		if (counter != 0) {
			var answer = $('input[name="group1"]:checked', '#testOption').val();
			if (answer == null) {
				Materialize.toast('Please Select a Valid Answer', 4000);
			} else {
				$
						.ajax({
							url : "/myTest",
							data : {
								'test' : testid,
								'answer' : answer
							},
							success : function(response) {
								loadQuestion(response);
							},
							error : function(response) {
								Materialize
										.toast(
												'Something Went Wrong. Please Try Again',
												4000);
							}
						});
			}
		} else {
			$.ajax({
				url : "/myTest",
				data : {
					'test' : testid
				},
				success : function(response) {
					counter++;
					loadQuestion(response);
				},
				error : function(response) {
					Materialize.toast('Something Went Wrong. Please Try Again',
							4000);
				}
			});
		}
	}
	return false;
}

function loadQuestion(response) {
	var rs = jQuery.parseJSON(response);
	$('input[name="group1"]:checked', '#testOption').removeAttr("checked");
	$("#question").html(counter + ": " + rs[0].question);
	counter++;
	for (var i = 1; i < response.length; i++) {
		$("#op" + i).html(rs[i].option);
		$("#test" + i).val(rs[i].option);
	}
}

function disableF5(e) {
	if ((e.which || e.keyCode) == 116 || (e.which || e.keyCode) == 82)
		e.preventDefault();
};

function disableclick(e) {
	if (event.button == 2) {
		alert("RightClick Not Allowed");
		return false;
	}
}

$(document).ready(function() {
	setInterval('updateClock()', 1000);
	$(document).on("keydown", disableF5);
	document.onmousedown = disableclick;
	$.ajax({
		url : "/myTest",
		success : function(response) {
			initiate(response);
		},
		error : function(response) {
			Materialize.toast('Something Went Wrong. Please Try Again', 4000);
		}
	});
});