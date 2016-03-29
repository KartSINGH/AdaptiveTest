var currentMinutes = 0;
var currentSeconds = 10;

function updateClock() {
	if (currentSeconds == 0) {
		currentMinutes--;
		currentSeconds = 60;
	}
	currentSeconds--;
	if (currentSeconds <= 5 && currentMinutes == 0) {
		$("#timer").addClass("red-text");
	}
	if (currentSeconds == 0 && currentMinutes == 0) {
		window.open("/user","_self");
	}

	var currentTimeString = "Time Remaining: " + currentMinutes + ":"
			+ currentSeconds;

	$("#timer").html(currentTimeString);

}

$(document).ready(function() {
	setInterval('updateClock()', 1000);
});