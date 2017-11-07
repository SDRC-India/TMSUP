<!-- 
@author Laxman (laxman@sdrc.co.in)
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html ng-app="homeApp">
<head>

<title>TMS-UP | Home</title>
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
	/* @media (max-width: 767px){
		div#mymain{
			margin-bottom: 0px;
		}
	} */
	.img-container{
	margin:74px;
	}
	.place-desc-large{
	display:none !important;
	}
</style>

</head>

<body ng-controller="HomeController">
<style type="text/css">
div#mymain {margin-bottom: 0px !important;}</style>
	<jsp:include page="fragments/header.jsp"></jsp:include>
	<div id="mymain">
		<section id="homeslide">
			<div>
				<div>
					<div id="errMsg" class="text-center">
						<serror:Error id="msgBox" errorList="${formError}"
							cssInfClass="${className}">
						</serror:Error>
					</div>
					<div id="myCarousel" class="carousel slide" data-ride="carousel">
						<div class="carousel-inner" role="listbox">
							<div class="item img-height active">
								<img src="resources/images/banner/1.jpg"
									alt="UDISE" width="100%;">
							</div>
							<div class="item img-height">
								<img src="resources/images/banner/2.jpg"
									alt="UDISE" width="100%;">
							</div>
							<div class="item img-height">
								<img src="resources/images/banner/3.jpg"
									alt="UDISE" width="100%;">
							</div>
							
						</div>
						
						
						
						
						
							<!-- Left and right controls -->
						<!--<a class="left carousel-control" href="#myCarousel" role="button"
							data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a> <a class="right carousel-control" href="#myCarousel"
							role="button" data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>-->
					</div>
				</div>
			</div>

		</section>
		<div class="section panel-3 content-block3 vertical-top text-right">
		<div class="container-fluid">
		<div class="row">
		<div class="col-md-12 col-lg-12 col-xs-12" style="background-image:url()")">
		
		</div>
		</div>
		</div>
			<div class="container"  >
	        	<div id="about" class="col-md-12 about_mrgn" >
	            
	            	<!--Image Left-->
	            	<div class="col-md-4 classabout">
	            	<div data-height="30"></div>
	                        <h2 class="abt_us" style="margin-bottom:25px;" >About Us</h2>
						<figure class="abimage" class="img-container"  align="right">
							<img class="img img-responsive" src="resources/images/about.png" alt="image"  />
						</figure>
	                </div>
	                <!--End-->
	                
	                <!--Text Right-->
	                <div class="col-md-8 classabout" >
	                	<div>
	                    	<div data-height="30"></div>
	                        <h2 class="uppr_h"  style="margin-bottom:25px;" >&nbsp;</h2>
	                        <div>
<!-- 	                            <p>Secondary education serves as a link between the elementary and higher education, and plays a very important role in this respect. This stage of education serves to move on higher secondary stage as well as to provide generic competencies that cut across various domains of knowledge as well as skills. Unlike achievements regarding universal coverage in enrollment at the elementary level, transition to secondary level possess a big challenge in Uttar Pradesh.</p> -->
<!-- 	     	                       <p>There are a little over 22,000 government and government aided secondary schools in the state. Overall the GER at secondary level is 47.17 and for the girls GER is as low as 45.7. Availability of teachers, especially subject teachers and quality education are major concerns for secondary education.</p> -->
	                       		<p>Secondary education serves as a link between the elementary and higher education and plays a very important role in this respect. Unlike achievements regarding universal coverage in enrollment at the elementary level, transition to secondary level possess a big challenge in Uttar Pradesh. Recent survey presents a very gloomy picture, especially regarding transition of girls from upper primary school to secondary school. This is worse for children coming from certain vulnerable and minority communities, particularly girl students from Muslim community. </p>
	                       		<p> In order to achieve 100% transition of all students to secondary level and to implement targeted programmes to increase the transition rate, Rashtriya Madhyamik Shiksha Abhiyaan (RMSA) with support from UNICEF is making an effort to accelerate the entire process in 17 districts of UP. This website is one part of that effort which will enable the users to track the transition rate of children from upper primary school to secondary level in these 17 districts. </p>
	                       

	                      
	                        </div>
	                    </div>
	                </div>
	                <!--End-->
	                </div>
	             <div class="col-md-12" style="margin-bottom:50px;">
	                <!--End-->
	                <!--Text left-->
	                <div class="about col-md-4 classabout" id="contactUs" >
	                <div data-height="30"></div>
	                        <h2 class="abt_us" style="margin-bottom:25px;" >Contact Us</h2>
	                	<div>
	                    	
	                        <div class="pad_contact" >
	                        <div data-height="30"></div>
	                       
	                          <p>UNICEF</p>
	                          <p>B-3/258, Vishal Khand,</p>
	                           <p>Gomti Nagar</p>
	                           
	                           <p>Lucknow, Uttar Pradesh</p>
	                           <p>Pin code: 226010</p>
	                           <p><a href="keyContacts"><h4>View Key Contacts</h4></a></p>
	                        </div>
	                    </div>
	                </div>
	                <!-- right -->
	          <div class="col-md-8 classabout" >
	          <div data-height="30"></div>
	                        <h2 id="about" class="abt" >&nbsp;</h2>
						<figure class="abimage" class="img-container" >
						<div style="width: 100%; overflow: hidden; height: 300px;">
     <iframe 
     src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3559.8150526574286!2d80.9869378146345!3d26.845834183156374!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x399bfd2e0c4254f5%3A0xb31cdc69af72690d!2sUNICEF!5e0!3m2!1sen!2sin!4v1498197599333"
     width="100%"
     height="600"
     frameborder="0"
     style="border:0; margin-top: -150px;">
  </iframe>
</div>
							<!-- <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3559.8150526574286!2d80.9869378146345!3d26.845834183156374!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x399bfd2e0c4254f5%3A0xb31cdc69af72690d!2sUNICEF!5e0!3m2!1sen!2sin!4v1498197599333" width="100%" height="300" frameborder="0" style="border:0" allowfullscreen></iframe> -->
						</figure>
	                </div>
	        <!-- right -->
	          </div>
	 
	        </div>

	<!--end of thematic and chklist  -->
	<jsp:include page="fragments/footer.jsp"></jsp:include>
	<!-- <script src="resources/js/angularController/loginController.js"></script> -->
	<!-- <script type="text/javascript">
		var app = angular.module("homeApp", []);
		var myAppConstructor = angular.module("homeApp");
		myAppConstructor.controller("HomeController",
				engagementScoreController);
		myAppConstructor.service('allServices', allServices);
	</script>
	<script type="text/javascript" src="resources/js/angularDirective/directive.js"></script> -->
	<script type="text/javascript">
		$(document).ready(function(){
			$(".usernameShow").html(userName);
		});
	</script>
	<script type="text/javascript">
	$(document).ready(function(){
		  
		  $("#about_us,#contact_us").on('click', function(event) {	  
		    if (this.hash !== "") {	  
		      event.preventDefault();	  
		      var hash = this.hash;
		      $('html, body').animate({
		        scrollTop: $(hash).offset().top - 45
		      }, 800, function(){	   
		  
		        window.location.hash = hash;
		      });
		    } 
		  });
		});

	</script>
</body>

</html>