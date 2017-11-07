<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="reportApp">
<head>

<title>TMS-UP | Report</title>

<meta content="width=device-width, initial-scale=1.0" name="viewport">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<!-- <link href="https://fonts.googleapis.com/css?family=Questrial" -->
<!-- 	rel="stylesheet"> -->
<link rel="stylesheet" href="resources/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/customLoader.css">
<link rel="stylesheet" href="resources/css/style.css">
<!-- <link rel="stylesheet" href="resources/css/style1.css"> -->
<spring:url value="/webjars/jquery/2.0.3/jquery.min.js" var="jQuery" />
<script src="${jQuery}"></script>

<style>
ul.dropdown-menu {
	max-width: 256px;
	max-height: 219px !important;
}
#dataTable th, #dataTable td{
	text-align: center !important;
}
#dataTable td:first-child {
    text-align: left !important;
}
</style>
</head>

<body ng-controller="ReportController" ng-cloak>
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
			<h2 class="abt_us" style="margin-bottom: 25px;">Report</h2>
			<div class="col-md-12 advnc-srch">
				<div class="form-container">

					<div class="row">
						<!-- <div class="col-md-6">
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
						</div> -->
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
												ng-click="selectSearchType(searchType);" ng-if="searchType.id!=1"><a href="">{{searchType.name}}</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
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
					</div>
					<div class="row">
						<div class="col-md-4" ng-show="selectedSearchType.id != 1 && selectedSearchType">
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
							ng-show="selectedSearchType.id != 1 && selectedSearchType.id != 2 && selectedSearchType">
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
							ng-show="selectedSearchType.id != 1 && selectedSearchType.id != 2 && selectedSearchType.id != 3 && selectedSearchType">
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

					</div>

					<div class="row">
						<div class="col-md-12">
							<button class="viewData" ng-click="downloadReport()">Download Report</button>
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
		
	</div>
	<!-- Modal for division table -->
	
	<!-- Modal for error message -->
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
	
	
		<div id="successModal" class="modal custom-modal modal-center fade" role="dialog"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<!-- Modal content -->
			<div class="modal-content">
				<div class="modal-body text-center">
					<div class="infohead">Success</div>
					<div class="warnbody">{{successMessage}}</div>
					<button type="button" class="btn errorOk" data-dismiss="modal">OK</button>
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
		src="resources/js/angularController/reportController.js"></script>
	<script type="text/javascript">
		var app = angular.module("reportApp", []);
		var myAppConstructor = angular.module("reportApp");
		myAppConstructor.controller("ReportController", reportController);
		myAppConstructor.service('httpServices', httpServices);
	</script>
	<script type="text/javascript">
		$("#msgBox").show().delay(2000).fadeOut(400);
	</script>
	<script type="text/javascript">
$(document).ready(function () {
    $(document).click(function (event) {
        var clickover = $(event.target);
        var _opened = $(".navbar-collapse").hasClass("navbar-collapse in");
        if (_opened === true && !clickover.hasClass("navbar-toggle")) {
            $("button.navbar-toggle").click();
        }
    });
});
</script>
</body>

</html>