var minutes = 10;
var seconds = 0;
var flag
var color = true;
var testid = null;
var counter = 0;
var limit = 0;
var score = 0;
var complete = true;

function updateClock() {
	if (seconds == 0 && minutes == 0) {
		if (complete) {
			Materialize.toast('Test Completed. Generating your Result', 2000,
					'', function() {
						window.open("/user", "_self");
					});
			complete = false;
		}
		$('input[name="group1"]:checked', '#testOption').removeAttr("checked");
		$('input[type="submit"]').prop('disabled', true);
		jQuery("input[name='group1']").each(function(i) {
			jQuery(this).attr('disabled', 'disabled');
		});
	} else {
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
	}

	var time = "Time Remaining: " + minutes + ":" + seconds;

	$("#timer").html(time);

}

function initiate(response) {
	var rs = jQuery.parseJSON(response);
	minutes = rs[0].time;
	testid = rs[1].testid;
	limit = rs[2].no;
	$('#scoreCard').html("Score: " + score);
	getQuestion();
}

function getQuestion() {
	if (counter >= limit) {
		if (complete) {
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
								console.log("Test Completed");
							},
							error : function(response) {
								Materialize
										.toast(
												'Something Went Wrong. Please Try Again',
												4000);
							}
						});

				Materialize.toast('Test Completed. Generating your Result',
						2000, '', function() {
							window.open("/user", "_self");
						});
				complete = false;
				$('input[name="group1"]:checked', '#testOption').removeAttr(
						"checked");
				$('input[type="submit"]').prop('disabled', true);
				jQuery("input[name='group1']").each(function(i) {
					jQuery(this).attr('disabled', 'disabled');
				});
			}
		}
	} else if (counter != 0) {
		var answer = $('input[name="group1"]:checked', '#testOption').val();
		if (answer == null) {
			Materialize.toast('Please Select a Valid Answer', 4000);
		} else {
			$.ajax({
				url : "/myTest",
				data : {
					'test' : testid,
					'answer' : answer
				},
				success : function(response) {
					loadQuestion(response);
				},
				error : function(response) {
					Materialize.toast('Something Went Wrong. Please Try Again',
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
				loadQuestion(response);
			},
			error : function(response) {
				Materialize.toast('Something Went Wrong. Please Try Again',
						4000);
			}
		});
	}
	return false;
}

function loadQuestion(response) {
	var rs = jQuery.parseJSON(response);
	$('input[name="group1"]:checked', '#testOption').removeAttr("checked");
	score = rs[5].score;
	$('#scoreCard').html("Score: " + score);
	counter++;
	$("#question").html(counter + ": " + rs[0].question);
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