<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
	<title>TMS-UP | Gallery</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/jquery-ui.css">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/css/lightbox.css">
<script src="resources/js/lightbox-plus-jquery.min.js"></script>
<spring:url value="/webjars/jquery/2.0.3/jquery.min.js" var="jQuery" />


  <link rel="stylesheet" href="/resources/demos/style.css">
<script src="${jQuery}"></script>
<spring:url value="/webjars/bootstrap/3.1.1/js/bootstrap.min.js"
	var="bootstrapjs" />
<script src="${bootstrapjs}"></script>
</head>

<body>
<jsp:include page="fragments/header.jsp"></jsp:include>

<div class="container contain-box row-slide">
  <div class="row ">
   
    <div class="col-md-12" >
     <h2 class="page_title abt_us pge_gllry">Photo Gallery</h2>
     
   	   <div class="content">
			    <div class="row content gallery_mrgn_row">
			    <div class="col-md-3 ">
			     <a class="example-image-link " href="resources/images/gallery/1.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive gallery-img" src="resources/images/gallery/1.jpg"   alt=""/></a>
			      </div
			      ><div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/2.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive " src="resources/images/gallery/2.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/3.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/3.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/4.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/4.jpg"  alt="" /></a>
			    </div>
			    </div>
			    <div class="row structure-image gallery_mrgn_row">
			    <div class="col-md-3 ">
			     <a class="example-image-link " href="resources/images/gallery/5.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/5.jpg"  alt=""/></a>
			      </div
			      ><div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/6.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/6.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/7.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/7.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/8.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/8.jpg"  alt="" /></a>
			    </div>
			    </div>
			    <div class="row structure-image gallery_mrgn_row">
			    <div class="col-md-3 ">
			     <a class="example-image-link " href="resources/images/gallery/9.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/9.jpg"  alt=""/></a>
			      </div
			      ><div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/10.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/10.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/11.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/11.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/12.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/12.jpg"  alt="" /></a>
			    </div>
			    </div>
			    <div class="row structure-image gallery_mrgn_row">
			    <div class="col-md-3 ">
			     <a class="example-image-link " href="resources/images/gallery/13.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/13.jpg"  alt=""/></a>
			      </div
			      ><div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/14.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/14.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/15.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/15.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/16.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/16.jpg"  alt="" /></a>
			    </div>
			    </div>
			    <div class="row structure-image gallery_mrgn_row">
			    <div class="col-md-3 ">
			     <a class="example-image-link " href="resources/images/gallery/17.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/17.jpg"  alt=""/></a>
			      </div
			      ><div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/18.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/18.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/19.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/19.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/20.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/20.jpg"  alt="" /></a>
			    </div>
			    </div>
			    <div class="row structure-image gallery_mrgn_row">
			    <div class="col-md-3 ">
			     <a class="example-image-link " href="resources/images/gallery/21.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/21.jpg"  alt=""/></a>
			      </div
			      ><div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/22.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/22.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/23.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/23.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/24.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/24.jpg"  alt="" /></a>
			    </div>
			    </div>
			    <div class="row structure-image gallery_mrgn_row">
			    <div class="col-md-3 ">
			     <a class="example-image-link " href="resources/images/gallery/25.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/25.jpg"  alt=""/></a>
			      </div
			      ><div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/26.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/26.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/27.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/27.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/28.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/28.jpg"  alt="" /></a>
			    </div>
			    </div>
			    <div class="row structure-image gallery_mrgn_row">
			    <div class="col-md-3 ">
			     <a class="example-image-link " href="resources/images/gallery/29.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/29.jpg"  alt=""/></a>
			      </div
			      ><div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/30.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/30.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/31.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/31.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/32.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/32.jpg"  alt="" /></a>
			    </div>
			    </div>
			    <div class="row structure-image gallery_mrgn_row">
			    <div class="col-md-3 ">
			     <a class="example-image-link " href="resources/images/gallery/33.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/33.jpg"  alt=""/></a>
			      </div
			      ><div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/34.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/34.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/35.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/35.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/36.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/36.jpg"  alt="" /></a>
			    </div>
			    </div>
			    <div class="row structure-image gallery_mrgn_row">
			    <div class="col-md-3 ">
			     <a class="example-image-link " href="resources/images/gallery/37.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/37.jpg"  alt=""/></a>
			      </div
			      ><div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/38.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/38.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/39.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/39.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/40.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/40.jpg"  alt="" /></a>
			    </div>
			    </div>
			    <div class="row structure-image gallery_mrgn_row">
			    <div class="col-md-3 ">
			     <a class="example-image-link " href="resources/images/gallery/41.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/41.jpg"  alt=""/></a>
			      </div
			      ><div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/42.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/42.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/43.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/43.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/44.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/44.jpg"  alt="" /></a>
			    </div>
			    </div>
			    <div class="row structure-image gallery_mrgn_row">
			    <div class="col-md-3 ">
			     <a class="example-image-link " href="resources/images/gallery/45.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/45.jpg"  alt=""/></a>
			      </div
			      ><div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/46.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/46.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/47.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/47.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/48.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/48.jpg"  alt="" /></a>
			    </div>
			    </div>
			    <div class="row structure-image gallery_mrgn_row">
			    <div class="col-md-3 ">
			     <a class="example-image-link " href="resources/images/gallery/49.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/49.jpg"  alt=""/></a>
			      </div
			      ><div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/50.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/50.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/51.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/51.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/52.jpg" data-lightbox="example-set" ><img class="example-image img img-responsive" src="resources/images/gallery/52.jpg"  alt="" /></a>
			    </div>
			    </div>
			    <div class="row structure-image gallery_mrgn_row">
			    <div class="col-md-3 ">
			     <a class="example-image-link " href="resources/images/gallery/53.jpg" data-lightbox="example-set" >
			     <img class="example-image img img-responsive" src="resources/images/gallery/53.jpg"  alt=""/></a>
			      </div
			      ><div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/54.jpg" data-lightbox="example-set" >
			      <img class="example-image img img-responsive" src="resources/images/gallery/54.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/55.jpg" data-lightbox="example-set" >
			      <img class="example-image img img-responsive" src="resources/images/gallery/55.jpg"  alt="" /></a>
			      </div>
			      <div class="col-md-3 ">
			      <a class="example-image-link" href="resources/images/gallery/56.jpg" data-lightbox="example-set" >
			      <img class="example-image img img-responsive" src="resources/images/gallery/56.jpg"  alt="" /></a>
			    </div>
			    </div>
			    
			    
	   </div>
   	</div>
    
    
  
  </div>
</div>

<jsp:include page="fragments/footer.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function(){
		$(".media-menu").addClass("active");
	})
	</script>
</body>
</html>