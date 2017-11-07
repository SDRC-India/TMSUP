<!-- 
@author Laxman (laxman@sdrc.co.in)
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html ng-app="dashboardApp">
<head>

<title>TMS-UP | Dashboard</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/style.css">
<spring:url value="/webjars/jquery/2.0.3/jquery.min.js" var="jQuery" />
	<script src="${jQuery}"></script>
<!-- <script src="resources/js/angular.min.js"></script> -->
<style type="text/css">
/* @media (max-width: 767px){
		div#mymain{
			margin-bottom: 0px;
		}
	} */
	.enroll_footer{
		padding-left: 0;
	}
	svg#chart g.arc path.Left.Out{
		transform: scale(1.06);
		
	}
	svg#chart g.arc path.Left.Out, svg#chart g.arc path.Admitted{
	stroke: #000;
	}
</style>
</head>

<body ng-controller="DashboardController" ng-cloak>
	<style type="text/css">
div#mymain {
	margin-bottom: 0px !important;
}
</style>
	<jsp:include page="fragments/header.jsp"></jsp:include>
	<div id="mymain">
		<div class="container ">
			<div class="page-margin">
				<div class="userLocation">
					{{userLocation}} <!-- <i class="fa fa-chevron-right"
						aria-hidden="true"></i> {{user.distName}} <i
						class="fa fa-chevron-right" aria-hidden="true"></i>
					{{user.blockName}} -->
				</div>
				<div class="selection-section dashboard-selection text-center">
					<!-- <div class="col-md-3 select-container dist-list text-center">
						<div class="input-group" style="margin: auto;">
							<input type="text" placeholder="School Type" id="schoolType"
								class="form-control not-visible-input" name="schoolType" readonly=""
								ng-model="selectedSchoolType.value">
							<div class="input-group-btn" style="position: relative;">
								<button data-toggle="dropdown"
									class="btn btn-color dropdown-toggle" type="button">
									<i class="fa fa-list"></i>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li ng-repeat="schoolType in dropdownJson.schoolType" ng-click="selectSchoolType(schoolType);"><a href="">{{schoolType.value}}</a></li>
									
								</ul>
							</div>
						</div>
					</div> -->
					<div class="dashboard-selection-inputs select-container dist-list text-center" ng-show="allDivisions.length">
						<h4 class="text-left" style="margin-bottom: 5px;color: #737373;">Division :</h4>
						<div class="input-group" style="margin: auto;">
							<input type="text" placeholder="Select Division" id="division"
								class="form-control not-visible-input" name="division" readonly=""
								ng-model="selectedDivision.areaName">
							<div class="input-group-btn" style="position: relative;">
								<button data-toggle="dropdown" ng-disabled="!allDivisions.length"
									class="btn btn-color dropdown-toggle" type="button">
									<i class="fa fa-list"></i>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li ng-repeat="division in allDivisions" ng-click="selectDivision(division);"><a href="">{{division.areaName}}</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="dashboard-selection-inputs select-container dist-list text-center" ng-show="(allDivisions.length || allDistricts.length) && selectedDivision.areaId != 0">
						<h4 class="text-left" style="margin-bottom: 5px;color: #737373;">District :</h4>
						<div class="input-group" style="margin: auto;">
							<input type="text" placeholder="Select District" id="district"
								class="form-control not-visible-input" name="district" readonly=""
								ng-model="selectedDistrict.areaName">
							<div class="input-group-btn" style="position: relative;">
								<button data-toggle="dropdown" ng-disabled="!allDistricts.length"
									class="btn btn-color dropdown-toggle" type="button">
									<i class="fa fa-list"></i>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li ng-repeat="district in allDistricts" ng-click="selectDistrict(district);"><a href="">{{district.areaName}}</a></li>
								</ul>
							</div>
						</div>
					</div>
					
					<div class="dashboard-selection-inputs select-container dist-list text-center" ng-show="(allDivisions.length || allDistricts.length || allBlocks.length) && selectedDivision.areaId != 0">
						<h4 class="text-left" style="margin-bottom: 5px;color: #737373;">Block :</h4>
						<div class="input-group" style="margin: auto;">
							<input type="text" placeholder="Select Block" id="block"
								class="form-control not-visible-input" name="block" readonly=""
								ng-model="selectedBlock.areaName">
							<div class="input-group-btn" style="position: relative;">
								<button data-toggle="dropdown" ng-disabled="!allBlocks.length"
									class="btn btn-color dropdown-toggle" type="button">
									<i class="fa fa-list"></i>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li ng-repeat="block in allBlocks" ng-click="selectBlock(block);"><a href="">{{block.areaName}}</a></li>
								</ul>
							</div>
						</div>
					</div>
					<button ng-show="(allDivisions.length && selectedDivision.areaName) || (allDistricts.length && selectedDistrict) || (allBlocks.length && selectedBlock)" data-toggle="dropdown" data-toggle="tooltip" title="Reset selections" ng-click="resetSelection()"
									class="btn btn-color dropdown-toggle tooltip-toggle back-button" type="button">
									<i class="fa fa-undo"></i>
								</button>
				</div>
				<div class="statistics-section">
					<div class="row">
						<div class="col-md-6 col-sm-8" style="padding: 45px 15px;">
							<h4 class="text-center">
								<b>Number of Students</b>
							</h4>
							<div class="stat-legend-section">
								<div class="row">
								<div class="col-md-4 col-sm-4 text-center">
									<h4>Total</h4>
									<h2>{{totalData["Total "]}}</h2>
									<div class="stat-legend total"></div>
								</div>
								<div class="col-md-4 col-sm-4 text-center">
									<h4>Enrolled</h4>
									<h2>{{totalData["Admitted "]}}</h2>
									<div class="stat-legend admitted"></div>
								</div>
								<div class="col-md-4 col-sm-4 text-center ">
									<h4>Left Out</h4>
									<h2>{{totalData["Left Out"]}}</h2>
									<div class="stat-legend left-out "></div>
								</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-4" style="padding: 45px 15px;" ng-show="pieChartData.length">
							<div class="pieChartContainer chart-containers text-center"
								id="pie-chart-container">
								<pie-chart dataprovider="pieChartData"></pie-chart>
								
							</div>
							<div class="pie-chart-legend" >
										<div style="position: relative;width: 150px;;display: inline-block">
											<div class="rectLegend enrolled"></div><div class="legendText">Enrolled Students</div><br>
											<div class="rectLegend left"></div><div class="legendText">Left Out Students</div>
										</div>
									</div>
						</div>
					</div>
					<div class="row" style="margin-bottom: 20px" >
						<div class="col-md-12" ng-show="genderDoughnutChartData.length">
							<div class="mainContainerDoughnutchart">
								<div class="piechart-box-heading">By Gender</div>
								<div class="row">
									<div class="chart-list col-md-4"  ng-repeat="data in genderDoughnutChartData">
										<sdrc-doughnut-chart dataprovider="data"></sdrc-doughnut-chart>
									</div>
								<!-- 	<div class="doughnut-chart-legend col-md-12" >
										<div style="position: relative;width: 108px;margin: auto;">
											<div class="rectLegend boy"></div><div class="legendText">Boys</div><br>
											<div class="rectLegend girl"></div><div class="legendText">Girls</div><br>
											<div class="rectLegend third-gender"></div><div class="legendText">Third Gender</div>
										</div>
									</div> -->
								</div>
							</div>
						</div>
						</div>
						<div class="row" style="margin-bottom: 20px">
						<div class="col-md-12" ng-if="castDoughnutChartData.length">
							<div class="mainContainerDoughnutchart">
								<div class="piechart-box-heading">By Social Category</div>
								<div class="row">
									<div class="chart-list col-md-12"  >
										<sdrc-stacked-bar-chart dataprovider="castDoughnutChartData"></sdrc-stacked-bar-chart>
									</div>
									
									
								<!-- 	<div class="doughnut-chart-legend col-md-4" >
										<div style="position: relative;width: 80px;margin: auto;">
											<div class="rectLegend general"></div><div class="legendText">General</div><br>
											<div class="rectLegend obc"></div><div class="legendText">OBC</div><br>
											<div class="rectLegend sc"></div><div class="legendText">SC</div><br>
											<div class="rectLegend st"></div><div class="legendText">ST</div><br>
											<div class="rectLegend minority"></div><div class="legendText">Minority</div>
										</div>
									</div> -->
								</div>
							</div>
						</div>
						
					</div>
				</div>
				<div
						class="download-container"
						ng-class="">
						<button type="button" id="excelDownloadBtn"
							 class="btn excelDownloadBtn"
							title="Download Excel" ng-click="exportTableData('dataTable', 'Uttar Pradesh', selectedDivision.areaName, selectedDistrict.areaName, selectedBlock.areaName, selectedSchool['School Name '])">
							<i class="fa fa-file-excel-o fa-lg" aria-hidden="true"></i> &nbsp;
							Download Excel
						</button>
						<button type="button" id="pdfDownloadBtn"
							 class="btn pdfDownloadBtn"
							title="Download PDF" ng-click="sdrc_export()">
							<i class="fa fa-file-pdf-o fa-lg" aria-hidden="true"></i> &nbsp;
							Download PDF
						</button>
					</div>
				<div class="main-table-container-div" style="overflow: hidden;">	
				<div class="static-header-container"></div>	
				<div class="table-responsive table-container-margin table-header-fixed"
					 style="width: 100%">
					<table items="tableData" show-filter="true" cellpadding="0"
						cellspacing="0" border="0" ng-class="{studentTable: isStudentTable}"
						class="dataTable table table-striped schoolTable custom-table dashboard-school-table"
						id="dataTable">
						<thead>
							<tr>
								<th ng-repeat="columnData in dataTableColumns | filter:removeExtraColumn" ng-class="$index == 0?'first-column':$index == 1 && !isStudentTable?'totalDashboardTable':$index == 2 && !isStudentTable?'admittedDashboardTable':($index == 3 || $index==4) && !isStudentTable?'leftDashboardTable':''"
									nowrap><div>
										{{columnData}}
										<div class="sorting1" ng-click="order(columnData)">
											<i class="fa fa-long-arrow-up asc" aria-hidden="true"
												ng-class="{'hiding': sortType == columnData &&  sortReverse == true}"></i>
											<i class="fa fa-long-arrow-down hiding dsc"
												aria-hidden="true"
												ng-class="{'hiding': (sortType == columnData &&  sortReverse == false) || sortType != columnData}"></i>
										</div>
									</div></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-class="schoolListTable && rowData[dataTableColumns[1]] != 0?'schoolListDash':''"
								ng-click="schoolListTable && rowData[dataTableColumns[1]] != 0?getStudentTable(rowData):''"
								ng-repeat="rowData in dataTableData | orderBy:filterType:sortReverse">
								<td sortable="'{{rowData.column}}'" ng-repeat="columnData in dataTableColumns | filter:removeExtraColumn" ng-class="{'status-column':columnData == 'Status', 'text-center': $index != 0}">{{columnData != 'Status'?rowData[columnData]:''}}<div ng-if="columnData == 'Status'" ng-class="rowData[columnData]"></div></td>
								
							</tr>
						</tbody>
					</table>
				</div>
				<div style="position: relative">
				<p class="enroll_footer footer-right" ng-show="(selectedBlock || userLevel == 5) && !showMap && !isStudentTable">* School names are as per UDISE Database</p>
				<p class="enroll_footer ">Last updated date : {{allDashboardData.lastUpdatedDate}}</p>
				</div>
				</div>
			
			</div>
			<div ng-show="(selectedBlock || userLevel == 5) && !showMap && !isStudentTable" class="text-right" style="margin-bottom: 10px">
				<button class="custom-submit-button" ng-click="getPushpinData(selectedDistrict.areaId, selectedBlock.areaId, true)">Show Map</button>
			</div>
			<div ng-show="(selectedBlock || userLevel == 5) && showMap && !isStudentTable" class="text-right" style="margin-bottom: 10px">
				<button class="custom-submit-button" ng-click="showMap = false">Hide Map</button>
			</div>
			<div class="mapSection" style="position: relative;" ng-if="selectedBlock && showMap && !isStudentTable">
									<section class="legends">
											<div class="legend-head">Left Out(%)</div>
											<ul>
												<!-- <li class="legend_list">
													<h4>{{selectedSector.label}}</h4>
												</li> -->
												<li class="legend_list "><span
													class="legend_key ng-binding">0 to 20</span> <span
													class="fourthslices legnedblock"> </span> </li>
												<li class="legend_list "><span class="legend_key ">20.1
														to 50</span> <span class="secondslices legnedblock"> </span> </li>
												<li class="legend_list ng-scope"><span
													class="legend_key ">Above 50</span> <span
													class="firstslices legnedblock"> </span> </li>
												<li class="legend_list ng-scope"><span
													class="legend_key ">Not Available</span> <span
													class="fifthslices legnedblock"> </span> </li>
												
											</ul>
										</section>
			
			<google-map center="map.center" zoom="map.zoom"
											draggable="true"> <markers class="pushpin"
											models="map.markers" coords="'self'" icon="'icon'"
											events="map.events"> <windows show="'showWindow'" style="margin-top: -30px"
											closeClick="'closeClick'" options='pixelOffset'>
										<p ng-non-bindable
											style="width: 130px; height: 30px; font-size: 15px; color: #313e4d; display: inline;">
											<strong>{{schoolName}}</strong><br> <strong>Left Out:{{leftOutPercent}}</strong>
										</p>
										</windows> </markers> <polygon static="true"
											ng-repeat="p in polygons track by p.id" path="p.path"
											stroke="p.stroke" visible="p.visible" geodesic="p.geodesic"
											fill="p.fill" fit="false" editable="p.editable"
											draggable="p.draggable"></polygon> </google-map>
											
		</div>
		</div>
		
	</div>
	

	<!-- All Modals  -->
	

	<!--footer section  -->
	<jsp:include page="fragments/footer.jsp"></jsp:include>

	<spring:url value="/webjars/bootstrap/3.1.1/js/bootstrap.min.js"
		var="bootstrapjs" />
	<script src="${bootstrapjs}"></script>
	<spring:url value="/webjars/angularjs/1.5.5/angular.min.js"
		var="angularmin" />
	<script src="${angularmin}" type="text/javascript"></script>
	<spring:url value="/webjars/d3js/3.4.6/d3.js" var="jQuery" />
	<script src="${jQuery}"></script>
	<!-- <script src="resources/js/jquery-ui.js" ></script> -->
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBedTd_YjXAiOM8I34K2MRUzqso2wu0wlA"
		type="text/javascript"></script>
	<spring:url value="/webjars/angularjs/1.5.5/angular-animate.min.js"
		var="angularaAnimatemin" />
	<script src="${angularaAnimatemin}" type="text/javascript"></script>
	<script src="resources/js/lodash.min.js" type="text/javascript"></script>
	<script src="resources/js/angular-google-maps.min.js"
		type="text/javascript"></script>	
	<script type="text/javascript"
		src="resources/js/angularController/dashboardController.js"></script>
	<script type="text/javascript"
		src="resources/js/sdrc.export.js"></script>
	<script type="text/javascript"
		src="resources/js/angularService/dashboardService.js"></script>
	<script type="text/javascript">
		var app = angular.module("dashboardApp", ['ngAnimate', 'google-maps']);
		var myAppConstructor = angular.module("dashboardApp");
		myAppConstructor.controller("DashboardController", dashboardController);
		myAppConstructor.service('dashboardServices', dashboardServices);
	</script>
	<script type="text/javascript"
		src="resources/js/angularDirective/directive.js"></script>
		
<script type="text/javascript">
$(document).ready(function(){
    $('.tooltip-toggle').tooltip();   
    $(".page-margin").css("min-height", $(window).height());
});
/* $(".table-header-fixed").scroll(function(){
	if($(".table-header-fixed").scrollTop() > 40){
		$("table#dataTable").css("margin-top", 0);
	}
	else{
		$("table#dataTable").css("margin-top", "25px");
	}
}); */


</script>
<script type="text/javascript"> 

$(document).ready(function() {
	sdrc_export.export_pdf("", "pdfDownloadBtn");
	/* sdrc_export.export_excel("", "excelDownloadBtn"); */
});

</script>
</body>

</html>
