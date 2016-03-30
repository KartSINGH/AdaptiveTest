var minutes = 100;
var seconds = 20;
var flag
var color = true;
var testid = null;

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
		window.open("/user", "_self");
	}

	var time = "Time Remaining: " + minutes + ":" + seconds;

	$("#timer").html(time);

}

function initiate(response) {
	var rs = jQuery.parseJSON(response);
	testid = rs;
	getQuestion();
}

function getQuestion() {
	
	$.ajax({
		url : "/myTest",
		data : {
			'test' : testid
		},
		success : function(response) {
			loadQuestion(response);
		}
	});
}

function loadQuestion(response) {
	var rs = jQuery.parseJSON(response);
	$("#question").html(rs[0].question);
	for (var i = 1; i < response.length; i++) {
		$("#op" + i).html(rs[i].option);
	}
}

$(document).ready(function() {
	setInterval('updateClock()', 1000);
	$.ajax({
		url : "/myTest",
		success : function(response) {
			initiate(response);
		}
	});
});