<!-- 
@author Laxman (laxman@sdrc.co.in)
 -->
<%@ page import="org.sdrc.udise.util.Constants"%>
<%@ page import="org.sdrc.udise.model.UserModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page
	import="org.sdrc.udise.model.UserRoleFeaturePermissionMappingModel"%>
<!--logo part end-->
<!-- spinner -->
<%
Integer role = 0,areaId=0,parentAreaId=0, userLevel=0, divisionId=0, districtId=0, blockId=0;
String areaName=null,parentAreaName=null,userName=null,divisionName=null, districtName=null, blockName=null;
UserModel user = null;
List<String> features = new ArrayList<String>();
List<String> permissions = new ArrayList<String>();

if (request.getSession().getAttribute(Constants.Web.USER_PRINCIPAL) != null) {
	user = (UserModel) request.getSession().getAttribute(Constants.Web.USER_PRINCIPAL);
	
	role = user.getUserAreaModels().get(0).
			getUserRoleFeaturePermissionMappings().get(0).getRoleFeaturePermissionSchemeModel().
			getRole().getRoleId();
	userLevel = user.getAreaLevel();
	if(userLevel > 2){
		divisionId = user.getDivisionId();
		divisionName = user.getDivisionName();
	}
	if(userLevel > 3){
		districtId = user.getDistrictId();
		districtName = user.getDistrictName();
	}
	if(userLevel > 4){
		blockId = user.getBlockId();
		blockName = user.getBlockName();
	}
	areaName = user.getAreaName();
	areaId=user.getAreaId();
	userName=user.getName();
	parentAreaId=user.getParentAreaId();
	parentAreaName=user.getParentAreaName();
	if (request.getSession().getAttribute(Constants.Web.USER_PRINCIPAL) == null) {
	} else if (request.getSession().getAttribute(Constants.Web.USER_PRINCIPAL) != null) {
		user = (UserModel) request.getSession().getAttribute(Constants.Web.USER_PRINCIPAL);

		List<UserRoleFeaturePermissionMappingModel> ursMappings = new ArrayList<UserRoleFeaturePermissionMappingModel>();
 		ursMappings = user != null ? user.getUserAreaModels().get(0).getUserRoleFeaturePermissionMappings() : null; 
		if (ursMappings != null && !ursMappings.isEmpty()) {
			for (UserRoleFeaturePermissionMappingModel ursm : ursMappings) {

				features.add(ursm.getRoleFeaturePermissionSchemeModel().getFeaturePermissionMapping()
						.getFeature().getFeatureName());
				permissions.add(ursm.getRoleFeaturePermissionSchemeModel().getFeaturePermissionMapping()
						.getPermission().getPermissionName());
			}
		}
	}
}
%>
<script>
    var blockName = "<%=areaName%>";
	var blockId = <%=areaId%>;
	var distId = <%=parentAreaId%>;
	var districtName="<%=parentAreaName%>";	
	var userLevel=<%=userLevel%>;
	if(userLevel > 2){
		var dashboardDivisionId=<%=divisionId%>;
		var dashboardDivisionName="<%=divisionName%>";
	}
	if(userLevel > 3){
		var dashboardDistrictId=<%=districtId%>;
		var dashboardDistrictName="<%=districtName%>";
	}
	if(userLevel > 4){
		var dashboardBlockId=<%=blockId%>;
		var dashboardBlockName="<%=blockName%>";
	}
	
</script>
<script type="text/javascript">
function changemysize(myvalue) {
$('#mymain *').css('font-size', myvalue + "px");
$('.abt_us').css('font-size', 30 + "px");

}
</script>


<!-- for the header fix while scroll -->



<script type="text/javascript">

$(window).scroll(function(){
	if($(window).width()>992){
	  var sticky = $('.stick_top'),
	      scroll = $(window).scrollTop();

	  if (scroll >= 80) sticky.addClass('fix');
	  else sticky.removeClass('fix');
	}
	});
</script>
<!-- for the header fix while scroll -->
<script type="text/javascript">
	var userName="<%=userName%>";
</script>

<!--logo part end-->
<!-- spinner -->
<!-- /spinner -->
<div class="navbar-static-top bar" style="background-color:#F7F7F7;">
	<div class="container">
	<div class="row" >
			<div class="col-md-12">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12  text-left">
                   <ul class=" navbar-left header-links">
                   		<!-- <label style="margin:0 0 0 0;">Font Size:&nbsp;</label> -->
						<li><a href="javascript:void(0);" onClick="changemysize(12);"><small>-A</small></a></li>
						<li><a href="javascript:void(0);" onClick="changemysize(15);">A</a></li>
						<li><a href="javascript:void(0);" onClick="changemysize(16);"><big>A+</big></a></li>
                   </ul>
					<ul class=" navbar-right  header-links">
						
						
						<%if (user==null)
							{%>
						<li style="color: #999;">&nbsp; | &nbsp;<i class="fa fa-sign-in logintxt" aria-hidden="true"></i></li>
						<li class="logintxt"><a href="login">Log in</a></li>
						
						<% }else {%>
						<li class="usernameShow"></li>
						<li style="color: #999;">&nbsp; | &nbsp;<i class="fa fa-sign-out logintxt" aria-hidden="true"></i></li>
						<li class="logintxt"><a href="webLogout">Logout</a></li>
						<%} %>
					</ul>
				</div>
				
			</div>
			</div>
	</div>
	</div>
	
	<header class="navbar-static-top bar header_bg hdr_tp" id="head1">

	<div class="container-fluid">


		<div class="col-md-12">
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="col-md-12">
						<div class="logo_holder">
							<a href="./">
							<!-- <div class="logo-text">
								<img alt="tms_logo" src="resources/images/RMSA-Logo.jpg">
							</div> -->
							<div class="logo-text">
							<span class="fnt_logo">Transition Monitoring System </span>
							<p class="fnt_logo" style="font-size:17px">Uttar Pradesh</p>
							</div>
								</a> 
							
						</div>
					</div>
					</div>
				<div class="col-md-6 col-sm-6 col-xs-12 rght_img" >
				<div class="image-right">
				<a href="http://unicef.in/" target="_blank" >
						<img class="imgclass unicef-logo" src="resources/images/UNICEF_ForEveryChild_SCREEN_Horizontal_RGB_ENG.PNG" alt="unicef"
							style="float:right"></a>
				<a href="http://rmsaindia.gov.in" target="_blank" >
						<img class="imgclass" src="resources/images/RMSA-Logo.jpg" alt="Government of Uttar Pradesh"
							style="height:80px;float:right"></a>
				<a href="http://ssa.nic.in/" target="_blank" >
						<img class="imgclass" src="resources/images/ssa_logo.jpg" alt="SSA"
							style="     margin-right: 30px;"></a>
							
				</div>
				</div> 
				
			</div>
		</div>

	</div>

</header>
<!-- END Top Bar	 -->
<!-- extra -->
<nav  class="navbar nav-menu-container navbar-fixed-top stick_top class1">
	<button class="navbar-toggle custom-navbar-mobile" style="z-index: 777; background-color: #f0a997;"
		data-toggle="collapse" data-target=".navbar-menu-collapse">
		<span class="icon-bar" style="background-color:#333a3b";></span> <span class="icon-bar" style="background-color:#333a3b";></span> <span
			class="icon-bar" style="background-color:#333a3b";></span>
	</button>
	<div class="container nav-section classmd">
		
		<div
			class="col-md-12 navHeaderCollapse2 navbar-menu-collapse collapse navbar-collapse menu-gap" data-hover="dropdown">
			<ul class="nav navbar-nav navbar-right nav-submenu nav-place-right" >

				<li ng-class="{'active' : activeMenu == 'home'}"><a href="home" id="home-menu"><div>&nbsp;Home</div></a></li>
				<li ng-class="{'active' : activeMenu == 'about'}"><a id="about_us" 
					href="home#about"><div>&nbsp;About Us</div></a></li>
				<%if (features.contains("dashboard")){ %>	
				<li ng-class="{'active' : activeMenu == 'report'}"><a href="report" id="home-menu"><div>&nbsp;Report</div></a></li>
				<%} %>
				<li class="dropdown media-menu">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#" class="navclass">Media
        <span class="caret"></span></a>
        <ul class="dropdown-menu bg_drk">
          <li class="drp_1"><a href="resource">Resources</a></li>
          <li class="drp_1"><a href="gallery">Photo Gallery</a></li>
          
        </ul></li>
				
				
				
					
		<%if (features.contains("dashboard")){ %>
        <li ng-class="{'active' : activeMenu == 'advance'}"><a href="advance" id="home-menu"><div>&nbsp;Advance Search</div></a></li>
				
			<%} %>	
<!-- 					<li ><a href="contact" id="home-menu"><div>&nbsp;Contact Details</div></a></li> -->
				<!-- <li ng-class="{'active' : activeMenu == 'report'}"><a href="login"><div>&nbsp;Login</div></a></li> -->
				<!-- <li ng-class="{'active' : activeMenu == 'advanceSearch'}"><a
					href="login">Log in</a></li> -->
					<%if (features.contains("dashboard")){ %>
				<li class="c1" ng-class="{'active' : activeMenu == 'dashboard'}"><a
					href="dashboard">Dashboard</a></li>
					<%}
					if (features.contains("link")){
					%>
					<li class="c1" ng-class="{'active' : activeMenu == 'link'}"><a
					href="manageStudents">Manage Students</a></li>
					<%} %>
					<li ng-class="{'active' : activeMenu == 'keyContact'}"><a  id="key-contact"
					href="keyContacts"><div>&nbsp;Key Contacts</div></a></li>
					<li ng-class="{'active' : activeMenu == 'contact'}"><a  id="contact_us"
					href="home#contactUs"><div>&nbsp;Contact Us</div></a></li>
			</ul>
		</div>
	</div>
</nav>
<div class="loaderMask" id="loader-mask" >
	<div class="windows8">
		<div class="wBall" id="wBall_1">
			<div class="wInnerBall"></div>
		</div>
		<div class="wBall" id="wBall_2">
			<div class="wInnerBall"></div>
		</div>
		<div class="wBall" id="wBall_3">
			<div class="wInnerBall"></div>
		</div>
		<div class="wBall" id="wBall_4">
			<div class="wInnerBall"></div>
		</div>
		<div class="wBall" id="wBall_5">
			<div class="wInnerBall"></div>
		</div>
	</div>
	
</div>
<div class="loaderMask" id="loader-mask-wait" >
	<div class="cssload-loader">Downloading... Please wait</div>
</div>
<style>
.cssload-loader {
	width: 181px;
	height: 36px;
	line-height: 36px;
	text-align: center;
	position: absolute;
	left: 50%;
	transform: translate(-50%, -50%);
		-o-transform: translate(-50%, -50%);
		-ms-transform: translate(-50%, -50%);
		-webkit-transform: translate(-50%, -50%);
		-moz-transform: translate(-50%, -50%);
	font-family: helvetica, arial, sans-serif;
	text-transform: uppercase;
	font-weight: 900;
	font-size:13px;
	color: rgb(206,66,51);
	letter-spacing: 0.2em;
}
.cssload-loader::before, .cssload-loader::after {
	content: "";
	display: block;
	width: 11px;
	height: 11px;
	background: rgb(206,66,51);
	position: absolute;
	animation: cssload-load 1.12s infinite alternate ease-in-out;
		-o-animation: cssload-load 1.12s infinite alternate ease-in-out;
		-ms-animation: cssload-load 1.12s infinite alternate ease-in-out;
		-webkit-animation: cssload-load 1.12s infinite alternate ease-in-out;
		-moz-animation: cssload-load 1.12s infinite alternate ease-in-out;
}
.cssload-loader::before {
	top: 0;
}
.cssload-loader::after {
	bottom: 0;
}



@keyframes cssload-load {
	0% {
		left: 0;
		height: 22px;
		width: 11px;
	}
	50% {
		height: 6px;
		width: 29px;
	}
	100% {
		left: 170px;
		height: 22px;
		width: 11px;
	}
}

@-o-keyframes cssload-load {
	0% {
		left: 0;
		height: 22px;
		width: 11px;
	}
	50% {
		height: 6px;
		width: 29px;
	}
	100% {
		left: 170px;
		height: 22px;
		width: 11px;
	}
}

@-ms-keyframes cssload-load {
	0% {
		left: 0;
		height: 22px;
		width: 11px;
	}
	50% {
		height: 6px;
		width: 29px;
	}
	100% {
		left: 170px;
		height: 22px;
		width: 11px;
	}
}

@-webkit-keyframes cssload-load {
	0% {
		left: 0;
		height: 22px;
		width: 11px;
	}
	50% {
		height: 6px;
		width: 29px;
	}
	100% {
		left: 170px;
		height: 22px;
		width: 11px;
	}
}

@-moz-keyframes cssload-load {
	0% {
		left: 0;
		height: 22px;
		width: 11px;
	}
	50% {
		height: 6px;
		width: 29px;
	}
	100% {
		left: 170px;
		height: 22px;
		width: 11px;
	}
}
</style>
