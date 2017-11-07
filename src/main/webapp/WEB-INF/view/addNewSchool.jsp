<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>TMS-UP | AddNewSchool</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/jquery-ui.css">
<link rel="stylesheet" href="resources/css/style.css">
<spring:url value="/webjars/jquery/2.0.3/jquery.min.js" var="jQuery" />


  <link rel="stylesheet" href="/resources/demos/style.css">
<script src="${jQuery}"></script>
<spring:url value="/webjars/bootstrap/3.1.1/js/bootstrap.min.js"
	var="bootstrapjs" />
<script src="${bootstrapjs}"></script>
</head>
<body>
<jsp:include page="fragments/header.jsp"></jsp:include>
<div class="container">
<form class="form-horizontal form_student"
			onsubmit="/action.php" style="margin: 22px 0 0 0;">

			<div class="school-details student_detail">
				<div class="row">
					<div
						class="school-name-heading student_name_add student_name_add1 col-md-6">Add
						New School</div>
					<div class="back-school-list col-md-6 text-right padd_right">
						<a href="javascript:void(0);"> <i class="fa fa-arrow-left"
							aria-hidden="true"> <span style="font-family: Questrial;">Back
									to School list</span>
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
						>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-4" for="s1">U-DISE Code:</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="sname"
						placeholder="U-DISE Code" name="sname" >
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-4" for="gender">District:</label>
				<div class="col-sm-4">
					<select class="form-control select_fld " >
						<option>District 1</option>
						<option>District 2</option>
						<option>District 3</option>

					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-4" for="gender">Block:</label>
				<div class="col-sm-4">
					<select class="form-control select_fld " >
						<option>Block 1</option>
						<option>Block 2</option>
						<option>Block 3</option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-4" for="s1">Latitude:</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="sname"
						placeholder="Enter Latitude" name="sname">
				</div>
			</div>



			
			<div class="form-group">
				<label class="control-label col-sm-4" for="pwd">Longitude:</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="pwd"
						placeholder="Enter Longitude" name="fname">
				</div>
			</div>

					

			<div class="form-group ">
				<div class="col-sm-offset-4 col-sm-5 submt_frm_margin">
					<button type="submit" class="btn btn-default btn_submit"
						>Submit</button>
				</div>
			</div>

		</form>
		</div>
<!-- 	<section class="section-padding"> -->
<!-- 		<div class="container"> -->
<!-- 			<div class="row"> -->
<!-- 				<div class="col-md-12 resrce_back" > -->
<!-- 					<div class="inner-page-title"> -->
					

<!-- 						<table -->
<!-- 							class="table table-responsive table-striped factsheet bg_grey"> -->
<!-- 							<h3 class="resource_undrlne"> -->
<!-- 							Resources -->
<!-- 						</h3> -->
<!-- 							<thead> -->
<!-- 								<tr> -->
<!-- 									<th>Acts & Rules,Schemes, Circular & Guidelines</th> -->
<!-- 									<th class="algn_right">Download</th> -->
<!-- 								</tr> -->
<!-- 							</thead> -->
<!-- 							<tbody> -->
<!-- 								<tr> -->
<!-- 									<td>file 1</td> -->
<!-- 									<td class="algn_right"><a href="javascript.void(0);"> -->
<!-- 											<i class="fa fa-2x fa-file-pdf-o"></i> -->
<!-- 									</a></td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td>file 1</td> -->
<!-- 									<td class="algn_right"><a href="javascript.void(0);"> -->
<!-- 											<i class="fa fa-2x fa-file-pdf-o"></i> -->
<!-- 									</a></td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td>file 1</td> -->
<!-- 									<td class="algn_right"><a href="javascript.void(0);"> -->
<!-- 											<i class="fa fa-2x fa-file-pdf-o"></i> -->
<!-- 									</a></td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td>file 1</td> -->
<!-- 									<td class="algn_right"><a href="javascript.void(0);"> -->
<!-- 											<i class="fa fa-2x fa-file-pdf-o"></i> -->
<!-- 									</a></td> -->
<!-- 								</tr> -->
<!-- 							</tbody> -->

<!-- 						</table> -->

<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</section> -->
	<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>