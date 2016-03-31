google.charts.load('current', {
	'packages' : [ 'corechart' ]
});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
	var jsonData = JSON.parse($.ajax({
		url : "/graph",
		dataType : "json",
		async : false
	}).responseText);

	var data = new google.visualization.DataTable();
	data.addColumn('string', 'TestID');
	data.addColumn('number', 'Score');
	var i, l = jsonData.length;
	for (i = 0; i < l; i++) {
		var temp = [ jsonData[i].date, jsonData[i].score ];
		data.addRow(temp);
	}
	var options = {
		title : 'Progress',
		curveType : 'function',
		legend : {
			position : 'bottom'
		}
	};

	var chart = new google.visualization.LineChart(document
			.getElementById('chart'));

	resize();
	function resize() {
		// change dimensions if necessary
		chart.draw(data, options);
	}
	if (window.addEventListener) {
		window.addEventListener('resize', resize);
	} else {
		window.attachEvent('onresize', resize);
	}
}

function showReport() {
	window.open("/report", "_self");
}

function takeTest() {
	window.open("/test", "_self");
}