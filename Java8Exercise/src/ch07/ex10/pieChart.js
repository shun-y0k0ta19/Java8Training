#!/usr/bin/jjs -fx

function pieChartData(name, data) {
	return new javafx.scene.chart.PieChart.Data(name, data)
}	

var observableDataList = javafx.collections.FXCollections.observableArrayList(
    pieChartData('A', 40),
    pieChartData('B', 25),
    pieChartData('C', 15),
    pieChartData('D', 20),
    pieChartData('E', 5),
    pieChartData('other', 5))
var chart = new javafx.scene.chart.PieChart(observableDataList);
$STAGE.scene = new javafx.scene.Scene(chart);
$STAGE.title = "Population of the Continents";
