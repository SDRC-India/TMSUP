<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="serror" uri="/WEB-INF/ErrorDescripter.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="reportApp">
<head>

<title>TMS-UP | Sitemap</title>
<link rel="shortcut icon" href="resources/images/icons/slr_favicon.png" type="image/x-icon">
<link rel="icon" href="resources/images/icons/slr_favicon.png" type="image/x-icon">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Questrial"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/customLoader.css">
<link rel="stylesheet" href="resources/css/style.css">

<spring:url value="/webjars/jquery/2.0.3/jquery.min.js" var="jQuery" />
<script src="${jQuery}"></script>
<spring:url value="/webjars/bootstrap/3.1.1/js/bootstrap.min.js"
	var="bootstrapjs" />
<script src="${bootstrapjs}"></script>


</head>

<body>
	<jsp:include page="fragments/header.jsp"></jsp:include>
	
	<div id="mymain" class="container report-height">
	<div class="report-height">					
				
			
			<form class="sitesection" name="siteForm">
				<div class="col-md-12 col-sm-12 col-xs-12" style="margin-top:60px;">
				<div class="terms-margin">
				<div data-height="30"></div>
	                        <h2 class="abt_us" style="margin-bottom:25px;" >SiteMap</h2>
				</div>
					<div style="margin-top: 20px;"></div>
					<ul class="siteMapData">
						<li>Home</li>
						<li>About Us</li>
						<li>Media</li>
							<ul class="siteMapData">
							<li>Resources</li>
							<li>Photo Gallery</li>
						</ul>						
						<li>Key Contacts</li>
						<li>Contact Us</li>

					</ul>
				</div>
			</form>
		</div>
	</div>
	
	
	
	<jsp:include page="fragments/footer.jsp"></jsp:include>
	
<!-- 	<script type="text/javascript"> -->
<!-- // 		$("#msgBox").show().delay(2000).fadeOut(400); -->
<!-- 	</script> -->
	
</body>

</html>