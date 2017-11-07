
<html ng-app="linkApp">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>

<meta content="width=device-width, initial-scale=1.0" name="viewport">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/jquery-ui.css">
<link rel="stylesheet" href="resources/css/style.css">
<spring:url value="/webjars/jquery/2.0.3/jquery.min.js" var="jQuery" />

<!--  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="${jQuery}"></script>
<spring:url value="/webjars/bootstrap/3.1.1/js/bootstrap.min.js"
	var="bootstrapjs" />
<script src="${bootstrapjs}"></script>
<!-- <script src="resources/js/angular.min.js"></script> -->
<!-- <script type="text/javascript">
$( function() {
    $( "#datepicker" ).datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd/mm/yy",
        maxDate:'31/03/2010'
    });
  } );
  </script> -->
<script>
	function validateName(name) {
		if (/^[a-z0-9][a-z0-9._\-]*$/.exec(name)) {

			return true;
		}
		return false;
	}
</script>
<style>
#sel1 {
	width: 30%;
}
</style>
</head>
<body ng-controller="LinkController">
	<jsp:include page="fragments/header.jsp"></jsp:include>
	<div class="container">

		<form class="form-horizontal form_student"
			onsubmit="return validateName(name)" style="margin: 22px 0 0 0;">

			<div class="school-details student_detail">
				<div class="row">
					<div
						class="school-name-heading student_name_add student_name_add1 col-md-6">Add
						New Student</div>
					<div class="back-school-list col-md-6 text-right padd_right">
						<a href="javascript:void(0);"> <i class="fa fa-arrow-left"
							aria-hidden="true"> <span style="font-family: Questrial;">Back
									to Student list</span>
						</i>
						</a>
					</div>

				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-4" for="s1">Name of the
					school:</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="sname"
						placeholder="Name of the school" name="sname"
						ng-model="schoolName" disabled>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-4" for="s1">U-DISE Code:</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="sname"
						placeholder="U-DISE Code" name="sname" ng-model="udiseCode"
						disabled>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-4" for="s1">District:</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="sname"
						placeholder="Enter the District" name="sname"
						ng-model="user.distName" disabled>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-4" for="s1">Block:</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="sname"
						placeholder="Enter the Block" name="sname"
						ng-model="user.blockName" disabled>
				</div>
			</div>



			<div class="form-group">
				<label class="control-label col-sm-4" for="s1">Student name:</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="sname"
						placeholder="Enter Student name" name="sname"
						ng-model="studentName">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="pwd">Fathers
					name:</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter Fathers name" name="fname"
						ng-model="fathersName">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-4" for="mname">Mothers
					name:</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter Mothers name name" name="mname"
						ng-model="mothersName">
				</div>
			</div>

			<!-- <div class="form-group">
      <label class="control-label col-sm-2" for="dob">Date of Birth:</label>
      <div class="col-sm-5">          
        <input type="text" id="datepicker" class="form-control"  placeholder="Enter dob" name="dob">
      </div>
    </div> -->

			<div class="form-group">
				<label class="control-label col-sm-4" for="gender">Gender:</label>
				<div class="col-sm-4">
					<select class="form-control select_fld " ng-model="selectedGender">
						<option>Boy</option>
						<option>Girl</option>
						<option>Third Gender</option>

					</select>
				</div>
			</div>


			<div class="form-group">
				<label class="control-label col-sm-4" for="adhar">Social
					Categories:</label>
				<div class="col-sm-4">
					<select class="form-control select_fld" ng-model="selectedCaste">
						<option ng-repeat="studentCaste in studentCastes"
							value="{{studentCaste.key}}">{{studentCaste.value}}</option>
					</select>
				</div>
			</div>


			<div class="form-group ">
				<div class="col-sm-offset-4 col-sm-5 submt_frm_margin">
					<button type="submit" class="btn btn-default btn_submit"
						ng-click="submitData()">Submit</button>
				</div>
			</div>

		</form>
	</div>

	</div>
	</div>
	<jsp:include page="fragments/footer.jsp"></jsp:include>
	<spring:url value="/webjars/angularjs/1.5.5/angular.min.js"
		var="angularmin" />
	<script src="${angularmin}" type="text/javascript"></script>
	<script type="text/javascript"
		src="resources/js/angularController/linkController.js"></script>
	<script type="text/javascript"
		src="resources/js/angularService/service.js"></script>

	<script type="text/javascript">
		var app = angular.module("linkApp", []);
		var myAppConstructor = angular.module("linkApp");
		myAppConstructor.controller("LinkController", linkController);
		myAppConstructor.service('httpServices', httpServices);
	</script>
	<script type="text/javascript"
		src="resources/js/angularDirective/directive.js"></script>
</body>
</html>