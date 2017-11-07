<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>TMS-UP | Resources</title>
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
	<section class="section-padding" style="min-height: 90%;">
		<div class="container">
			<div class="row">
				<div class="col-md-12 resrce_back" >
					<div class="inner-page-title">
					

						<table
							class="table table-responsive table-striped factsheet bg_grey">
							<h2 class="resource_undrlne">
							Resources
						</h2>
							<thead>
								<tr>
									<th style="font-weight:bold">User-manual</th>
									<th class="algn_right" style="font-weight:bold">Download</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>TMS User Manual-For Dashboard and Reporting-English</td>
									<td class="algn_right"><a href="resources/files/TMS_UserManual_Dashboard_English.pdf" target="_blank">
											<i class="fa fa-2x fa-file-pdf-o"></i>
									</a></td>
								</tr>
								<tr>
									<td>TMS User Manual-For Dashboard and Reporting-Hindi</td>
									<td class="algn_right"><a href="resources/files/TMS_UserManual_Dashboard_Hindi.pdf" target="_blank">
											<i class="fa fa-2x fa-file-pdf-o"></i>
									</a></td>
								</tr>
								<tr>
									<td>TMS User Manual-For Data Entry-English</td>
									<td class="algn_right"><a href="resources/files/TMS_UserManual_Eng.pdf" target="_blank">
											<i class="fa fa-2x fa-file-pdf-o"></i>
									</a></td>
								</tr>
								<tr>
									<td>TMS User Manual-For Data Entry-Hindi</td>
									<td class="algn_right"><a href="resources/files/TMS_UserManual_Hindi.pdf" target="_blank">
											<i class="fa fa-2x fa-file-pdf-o"></i>
									</a></td>
								</tr>
								
							</tbody>

						</table>

					</div>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="fragments/footer.jsp"></jsp:include>
	<script type="text/javascript">
	$(document).ready(function(){
		$(".media-menu").addClass("active");
	})
	</script>
</body>
</html>