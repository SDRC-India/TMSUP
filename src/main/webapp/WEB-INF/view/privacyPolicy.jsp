<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="serror" uri="/WEB-INF/ErrorDescripter.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="loginApp">
<head>

<title>TMS-UP | Privacy Policy</title>
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
							<h3 class="headerofterms">Privacy Policy</h3>
						</div>
					</div>
				</div>
			</div>

			<form class=" reportsection" name="selctionForm">
				<div class="col-md-12 col-sm-12 col-xs-12 termsSectionBar">
					<div style="margin-top: 20px;"></div>
					<p class="termsdata">This privacy policy sets out how TMSUP uses and protects
					 any information that you give while you use this website.</p>
					<p class="termsdata">TMSUP is committed to ensuring that your privacy is protected.
					 When we ask you to provide certain information by which you can be identified when using 
					 this website, then you can be assured that it will only be used in accordance with this
					  privacy statement.</p>
					<p class="termsdata">TMSUP may change this policy from time to time by updating this page.
					 You should check this page from time to time to ensure that you are happy with any changes. </p>
					<h3 class="headerinfoPr">What we collect</h3>
					<p class="termsdata">While using our Site, we may ask you to provide us with certain Personal Information 
					(information that can be used to contact or identify you) and Non-Personal Information.
					</p>
					<h3 class="headerinfoPr">What we do with the information we gather</h3>
					<p class="termsdata">Except as otherwise stated in this privacy policy,
					 we do not sell, trade, rent or otherwise share for marketing 
					 purposes your personal information with third parties without your consent.
					  In general, the Personal Information you provide to us is used to help us communicate with you.
					    For example, we use Personal Information to contact users in response to questions,
					     solicit feedback from users, provide technical support, and inform users about promotional offers.
</p>
					<h3 class="headerinfoPr">Security</h3>
					<p class="termsdata">We are committed to ensuring that your
						information is secure. In order to prevent unauthorized access or
						disclosure, we have put in place suitable physical, electronic and
						managerial procedures to safeguard and secure the information we
						collect online.</p>
					<h3 class="headerinfoPr">Why we use cookies</h3>
					<p class="termsdata">The site may use cookies to enhance users'
						experience. Cookies help us provide you with a better website, by
						enabling us to monitor which pages you find useful and which you
						do not. A cookie in no way gives us access to your computer or any
						information about you, other than the data you choose to share
						with us. The user may choose to set their web browser to refuse
						Cookies or alert the user when the Cookies are being sent.
						However, this may prevent you from taking full advantage of the
						website.</p>
					<h3 class="headerinfoPr">Links to other websites</h3>
					<p class="termsdata">At many places in this website, you will find links to other websites/ portals. 
					These links have been placed for your convenience. TMSUP has no control over the nature, content and 
					availability of those sites. The inclusion of any links does not necessarily
					 imply a recommendation or endorse the views expressed within them.</p>
					<h3 class="headerinfoPr">Copyright Policy</h3>
					<p class="termsdata">This website and its content is copyright
						of TMSUP - &copy; TMSUP 2017. All rights reserved. Any
						redistribution or reproduction of part or all of the contents in
						any form is prohibited other than the following:</p>
					<ul class="termsData">
						<li>You may reproduce the content partially or fully, with
							duly & prominently acknowledging the source.</li>
					</ul>
					<p class="termsdata">However, the permission to reproduce any
						material that is copyright of any third party has to be obtained
						from the copyright holders concerned. The contents of this website
						cannot be used in any misleading or objectionable context or
						derogatory manner.</p><br><br><br><br><br>&nbsp;<br><br><br><br><br>
				</div>
			</form>
		</div>
	</div>
	<!--end of thematic and chklist  -->

	<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
<!-- <script src="resources/js/angularController/loginController.js"></script> -->

</html>