<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="advanceSearchApp">
<head>

<title>TMS-UP | Search</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Questrial"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/customLoader.css">
<link rel="stylesheet" href="resources/css/style.css">
<!-- <link rel="stylesheet" href="resources/css/style1.css"> -->
<spring:url value="/webjars/jquery/2.0.3/jquery.min.js" var="jQuery" />
<script src="${jQuery}"></script>
</head>

<body ng-controller="AdvanceSearchController" ng-cloak>
	<jsp:include page="fragments/header.jsp"></jsp:include>
	<div id="errMsg" class="text-center">
		<serror:Error id="msgBox" errorList="${formError}"
			cssInfClass="${className}">
		</serror:Error>
	</div>
	<div id="mymain" class="container report-height">

		<section id="form-section">

		<div class="row mar-bot-10" style="margin-top: 60px;">
			<div data-height="30"></div>
			<h2 class="abt_us" style="margin-bottom: 25px;">Advance Search</h2>
			<div class="col-md-12 advnc-srch">
				<div class="form-container">

					<div class="row">
						<div class="col-md-6">
							<div class="select-container dist-list text-center">
								<div class="input-group" style="margin: auto;">
									<input type="text" placeholder="Select indicator *" id="level"
										class="form-control not-visible-input" name="level"
										readonly="" ng-model="selectedIndicator.name">
									<div class="input-group-btn" style="position: relative;">
										<button data-toggle="dropdown"
											class="btn btn-color dropdown-toggle" type="button">
											<i class="fa fa-list"></i>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li ng-repeat="indicator in allIndicators"
												ng-click="selectIndicator(indicator);"><a href="">{{indicator.name}}</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="select-container dist-list text-center">
								<div class="input-group" style="margin: auto;">
									<input type="text" placeholder="Select search level *"
										id="criteria" class="form-control not-visible-input"
										name="criteria" readonly="" ng-model="selectedSearchType.name">
									<div class="input-group-btn" style="position: relative;">
										<button data-toggle="dropdown"
											class="btn btn-color dropdown-toggle" type="button">
											<i class="fa fa-list"></i>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li ng-repeat="searchType in searchTypes"
												ng-click="selectSearchType(searchType);"><a href="">{{searchType.name}}</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4" ng-show="selectedSearchType && selectedSearchType.id != 1">
							<div class="select-container dist-list text-center">
								<div class="input-group" style="float: right;">
									<input type="text" placeholder="Division *" id="pattern"
										class="form-control not-visible-input" name="pattern"
										readonly="" ng-model="selectedDivision.areaName">
									<div class="input-group-btn" style="position: relative;">
										<button data-toggle="dropdown"
											class="btn btn-color dropdown-toggle" type="button">
											<i class="fa fa-list"></i>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li ng-repeat="division in allDivisions"
												ng-click="selectDivision(division)"><a href="">{{division.areaName}}</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4"
							ng-show="selectedSearchType && selectedSearchType.id != 1 && selectedSearchType.id != 2">
							<div class="select-container dist-list text-center">
								<div class="input-group" style="float: right;">
									<input type="text" placeholder="District *" id="pattern"
										class="form-control not-visible-input" name="pattern"
										readonly="" ng-model="selectedDistrict.areaName">
									<div class="input-group-btn" style="position: relative;">
										<button data-toggle="dropdown"
											class="btn btn-color dropdown-toggle" type="button">
											<i class="fa fa-list"></i>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li ng-repeat="district in selectedDivision.children"
												ng-click="selectDistrict(district)"><a href="">{{district.areaName}}</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4"
							ng-show="selectedSearchType && selectedSearchType.id != 1 && selectedSearchType.id != 2 && selectedSearchType.id != 3">
							<div class="select-container dist-list text-center">
								<div class="input-group" style="float: right;">
									<input type="text" placeholder="Block *" id="pattern"
										class="form-control not-visible-input" name="pattern"
										readonly="" ng-model="selectedBlock.areaName">
									<div class="input-group-btn" style="position: relative;">
										<button data-toggle="dropdown"
											class="btn btn-color dropdown-toggle" type="button">
											<i class="fa fa-list"></i>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li ng-repeat="block in selectedDistrict.children"
												ng-click="selectBlock(block)"><a href="">{{block.areaName}}</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<!-- <div class="col-md-4">
								<div class="select-container dist-list text-center">
									<div class="input-group" style="float: right;">
										<input type="text" placeholder="Timeperiod *" id="pattern"
											class="form-control not-visible-input" name="pattern"
											readonly="" ng-model="selectedTimePeriod.timeperiod">
										<div class="input-group-btn" style="position: relative;">
											<button data-toggle="dropdown"
												class="btn btn-color dropdown-toggle" type="button">
												<i class="fa fa-list"></i>
											</button>
											<ul class="dropdown-menu" role="menu">
												<li ng-repeat="district in districts"
													ng-click="selectDistrict(district)"><a href="">{{district.areaName}}</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div> -->

					</div>
					<div class="row">
						<div class="col-md-4">
							<div class="select-container dist-list text-center">
								<div class="input-group" style="float: right;">
									<input type="text" placeholder="Time Period *" id="pattern"
										class="form-control not-visible-input" name="pattern"
										readonly="" ng-model="selectedTimePeriod.name">
									<div class="input-group-btn" style="position: relative;">
										<button data-toggle="dropdown"
											class="btn btn-color dropdown-toggle" type="button">
											<i class="fa fa-calendar"></i>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li ng-repeat="timeperiod in allTimePeriods"
												ng-click="selectTimePeriod(timeperiod)"><a href="">{{timeperiod.name}}</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="select-container dist-list text-center">
								<div class="input-group" style="float: right;">
									<input type="text" placeholder="Rule *" id="pattern"
										class="form-control not-visible-input" name="pattern"
										readonly="" ng-model="selectedRule.ruleName">
									<div class="input-group-btn" style="position: relative;">
										<button data-toggle="dropdown"
											class="btn btn-color dropdown-toggle" type="button">
											<i class="fa fa-list"></i>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li ng-repeat="rule in rules" ng-click="selectRule(rule)"><a
												href="">{{rule.ruleName}}</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="select-container dist-list text-center"
								style="width: 100%">
								<div class="input-group" style="float: right; width: 100%;">
									<input type="text" style="width: 100%; max-width: 100%;"
										placeholder="Percentage Value *" id="percentValue"
										class="form-control not-visible-input" name="Dist"
										onkeyup="this.value = minmax(this.value, 0, 100)"
										ng-model="percentValue">
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12">
							<a id="view-down" ><button class="viewData" ng-click="viewData()">Search Data</button></a>
						</div>
					</div>
					<div></div>
				</div>
			</div>
		</div>
		<br>
		<br>
		<br>
		&nbsp;&nbsp;<br>
		<br>
		</section>
		<section  class="tableSection" ng-if="tableData.length">
		<div class="col-md-12 col-xs-12 searchedDataForTable">
		<div class="row">
			<div class="col-md-8 col-xs-8" style="padding: 0">
				<h5 style="font-size: 18px;">Data Search
					Result</h5>
			</div>
			<!-- 				<div class="col-md-4 col-xs-4 totaldataCenter"><h5>Total no. of Facilities : -->
			<!-- 					{{tableData.length}}</h5></div> -->
			<div class="col-md-4 excelPosition" style="padding: 0" ng-if="tableData.length">
				<img ng-click="exportTableData('dataTable')" class="excelbtn"
					data-toggle="tooltip" title="Download Excel" data-placement="top"
					alt="" src="resources/images/icons/icon_download_excel.svg">

			</div>
		</div>
		</div>
		<div id="target-table"  class="">
			<div>
				<!-- <div class="static-header-container"></div>	 -->
				<div
					class="table-responsive table-container-margin table-header-fixed"
					style="width: 100%">
					<table items="tableData" show-filter="true" cellpadding="0"
						cellspacing="0" border="0"
						class="dataTable table table-striped schoolTable custom-table dashboard-school-table"
						id="dataTable" style="overflow:scroll;">
						<thead>
							<tr>
								<th
									ng-repeat="column in columns | filter:removeExtraColumn"
									nowrap><div>
										{{column}}
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
							<tr
								ng-repeat="rowData in tableData | orderBy:filterType:sortReverse">
								<td sortable="'{{rowData.column}}'"
									ng-repeat="column in columns | filter:removeExtraColumn"
									ng-class="{'status-column':columnData == 'Status', 'text-center': $index != 0}">
									{{rowData[column]}}
								</td>

							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		</section>
	</div>
	<div id="errorMessage" class="modal modal-center fade" role="dialog"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<!-- Modal content -->
			<div class="modal-content">
				<div class="modal-body text-center">
					<div class="infohead">Error</div>
					<div class="warnbody">{{errorMsg}}</div>
					<button type="button" class="btn errorOk" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal for error message -->
	<!-- <div id="errorMessage" data-keyboard="false" data-backdrop="static" class="modal fade" role="dialog">
		<div class="modal-dialog">
			Modal content
			<div class="modal-content">
				<div class="modal-body text-center">
					<h3>{{errorMsg}}</span></h3>
					<button type="button" class="btn errorOk" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>	 -->
	<div id="errorModal" class="modal modal-center fade" role="dialog"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<!-- Modal content -->
			<div class="modal-content">
				<div class="modal-body text-center">
					<div class="infohead">Error</div>
					<div class="warnbody">{{errorMsg}}</div>
					<button type="button" class="btn errorOk" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!--end of thematic and chklist  -->
	<jsp:include page="fragments/footer.jsp"></jsp:include>

	<spring:url value="/webjars/bootstrap/3.1.1/js/bootstrap.min.js"
		var="bootstrapjs" />
	<script src="${bootstrapjs}"></script>
	<spring:url value="/webjars/angularjs/1.5.5/angular.min.js"
		var="angularmin" />
	<script src="${angularmin}" type="text/javascript"></script>

	<script type="text/javascript"
		src="resources/js/angularService/service.js"></script>
	<script type="text/javascript"
		src="resources/js/angularController/advanceSearchController.js"></script>
	<script type="text/javascript">
		var app = angular.module("advanceSearchApp", []);
		var myAppConstructor = angular.module("advanceSearchApp");
		myAppConstructor.controller("AdvanceSearchController",
				advanceSearchController);
		myAppConstructor.service('httpServices', httpServices);
	</script>
	<script type="text/javascript">
		$("#msgBox").show().delay(2000).fadeOut(400);
	</script>
	<script>
		$(document).ready(function() {
			$('[data-toggle="tooltip"]').tooltip();
		});
	</script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$(document)
									.click(
											function(event) {
												var clickover = $(event.target);
												var _opened = $(
														".navbar-collapse")
														.hasClass(
																"navbar-collapse in");
												if (_opened === true
														&& !clickover
																.hasClass("navbar-toggle")) {
													$("button.navbar-toggle")
															.click();
												}
											});
						});
	</script>
	<!-- <script type="text/javascript">	
 	$(document).ready(function(){ 
 		$("#view-down").click(function() {alert(1);
 			$('html, body').animate({
 			scrollTop : $("#target-table").offset().top +250
 			}, slow);
// 	$("#view-down").click(fu/*  */nction(){alert(1); 
// 		  $('html, body').animate({ 
//              scrollBottom: $("#target-table").offset().bottom 
//           }, 2000);
//  	});	
 	})
	</script>  -->
</body>

</html>