<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="serror" uri="/WEB-INF/ErrorDescripter.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="loginApp">
<head>

<title>TMS-UP | Disclaimer</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/jquery-ui.css">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/style1.css">
<spring:url value="/webjars/jquery/2.0.3/jquery.min.js" var="jQuery" />
<script src="${jQuery}"></script>
<spring:url value="/webjars/bootstrap/3.1.1/js/bootstrap.min.js"
	var="bootstrapjs" />
<script src="${bootstrapjs}"></script>
<!-- <script src="resources/js/angular.min.js"></script> -->
<!-- <script src="resources/js/jquery.min.js"></script> -->
<!-- <script src="resources/js/bootstrap.min.js"></script> -->
<!-- <script src="resources/js/angular.min.js"></script> -->

</head>

<body>
	<jsp:include page="fragments/header.jsp"></jsp:include>
	<div id="errMsg" class="text-center" style="margin-top: 5px;">
		<serror:Error id="msgBox" errorList="${formError}"
			cssInfClass="${className}">
		</serror:Error>
	</div>
	<div id="wrapper">
		<div class="container">
			<div class="row mar-bot-10" style="margin: 9px auto;">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-6 col-sm-5 pull-left">
							<h3 class="headerofterms">Disclaimer</h3>
						</div>
					</div>
				</div>
			</div>

			<form class=" reportsection" name="selctionForm">
				<div class="col-md-12 col-sm-12 col-xs-12 termsSectionBar">
					<div style="margin-top: 20px;"></div>
					<p class="termsdata">The information contained in this website is for general 
					information purposes only. The information is provided by TMSUP and
					 while we endeavour to keep the information up to date and correct,
					  we make no representations or warranties of any kind, express or implied,
					   about the completeness, accuracy, reliability, suitability or availability 
					   with respect to the website or the information, products, services, or related 
					   graphics contained on the website for any purpose. Any reliance you place on such 
					   information is therefore strictly at your own risk. Users are advised to verify/ 
					   check any information, and to obtain any appropriate professional advice before acting
					    on the information provided on the website.</p>
					<p class="termsdata">In no event will we be liable for any 
					loss or damage including without limitation, indirect or consequential 
					loss or damage, or any loss or damage whatsoever arising from loss of data or 
					profits arising out of, or in connection with, the use of this website.</p>
					<p class="termsdata">Every effort is made to keep the website up and running
					 smoothly. However, TMSUP takes no responsibility for, and will not be liable for 
					 the website being temporarily unavailable due to technical issues beyond our control.<br><br><br><br><br>&nbsp;<br><br><br><br></p>
				</div>
			</form>
		</div>
	</div>
	<!--end of thematic and chklist  -->

	<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
<!-- <script src="resources/js/angularController/loginController.js"></script> -->
<script type="text/javascript">
	$("#msgBox").show().delay(2000).fadeOut(400);
</script>
</html>