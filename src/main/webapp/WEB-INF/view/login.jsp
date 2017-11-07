<!-- 
@author Laxman (laxman@sdrc.co.in)
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="org.sdrc.udise.util.Constants"%>
<%@taglib prefix="serror" uri="/WEB-INF/ErrorDescripter.tld"%>
<!DOCTYPE html>

<html>
<head>
<%
if (request.getSession().getAttribute(Constants.Web.USER_PRINCIPAL) != null)
{
	response.sendRedirect("home");
}

%>
<title>TMS-UP | Login</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/jquery-ui.css">
<link rel="stylesheet" href="resources/css/style.css">
<spring:url value="/webjars/jquery/2.0.3/jquery.min.js" var="jQuery" />
<script src="${jQuery}"></script>
<spring:url value="/webjars/bootstrap/3.1.1/js/bootstrap.min.js"
	var="bootstrapjs" />
<script src="${bootstrapjs}"></script>
<!-- <script src="resources/js/angular.min.js"></script> -->
<style type="text/css">
body .img-bg {
	width: 100%;
	height: 100%; background :
	url("resources/images/UDISE_svg_login_pattern_bg.svg") no-repeat fixed
	center;
	background-size: cover;
}
#foot{
margin-top:0px !important;
}
</style>
</head>

<body>
<jsp:include page="fragments/header.jsp"></jsp:include>
	<div id="errMsg" class="text-center">
						<serror:Error id="msgBox" errorList="${formError}"
							cssInfClass="${className}">
						</serror:Error>
					</div>
	<div class="img-bg">
		<div class="form-section">
			<img alt="" src="resources/images/UDISE_svg_login_School.svg" style="width: 100%;margin-bottom: 15px;">
			<form class="webLogin" action="webLogin" method="post">
					<input type="text" class="form-control input-field" id="username"
						name="username" placeholder="Username" required
						oninvalid="this.setCustomValidity('Please input your username')"
						oninput="setCustomValidity('')">
					<input type="password" class="form-control input-field"
						id="password" name="password" placeholder="Password" required
						oninvalid="this.setCustomValidity('Please input your password')"
						oninput="setCustomValidity('')">
				<button type="submit" id="submit" class="btn btn-default submit">LOGIN</button>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("body .img-bg").css("min-height", $(window).height());
		})
	</script>
	<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>

</html>