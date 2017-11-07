<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TMS-UP | Key Contacts</title>
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
<div class="container contact_detl" style="padding-right:0px;">
  <div data-height="30"></div>
	   <h2 class="abt_us" style="margin-bottom:25px;" >Key Contacts</h2>
  

  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" class="clr_contact" href="#home">AD Basic</a></li>
    <li><a data-toggle="tab" class="clr_contact" href="#menu1">BSA</a></li>
    <li><a data-toggle="tab" class="clr_contact" href="#menu2">DC-com</a></li>
    <li><a data-toggle="tab" class="clr_contact" href="#menu3">EMIS</a></li>
    <li><a data-toggle="tab" class="clr_contact" href="#menu4">RMSA</a></li>
    <li><a data-toggle="tab" class="clr_contact" href="#menu5">JD</a></li>
  </ul>

  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
      <div class="container">
  <h3>AD Basic Contact Details</h3>
          <table class="table table-striped">
    <thead>
      <tr>
        <th>SL.No.</th>
        <th>Division</th>
        <th>Designation</th>
        <th>Contact No.</th>
      </tr>
    </thead>
    <tbody>
      <tr>
      <td>1</td>
        <td>ADB Moradabad</td>
        <td>AD Basic</td>
        <td>9453004041</td>
      </tr>
      <tr>
      <td>2</td>
        <td>ADB Barreilly</td>
        <td>AD Basic</td>
        <td>9453004035</td>
      </tr>
      <tr>
      <td>3</td>
        <td>ADB Devipatan</td>
        <td>AD Basic</td>
        <td>9453004059</td>
      </tr>
      <tr>
      <td>4</td>
        <td>ADB Gorakhpur</td>
        <td>AD Basic</td>
        <td>9453004147</td>
      </tr>
    </tbody>
  </table>    
 
</div>
    </div>
    <div id="menu1" class="tab-pane fade">
      <div class="container">
  <h3>BSA Contact Details</h3>
         <table class="table table-striped">
    <thead>
      <tr>
        <th>SL.No.</th>
        <th>District</th>
        <th>Contact No.</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>1</td>
        <td>Badaun</td>
        <td>9453004111</td>
      </tr>
      <tr>
        <td>2</td>
        <td>Bahraich</td>
        <td>9453004135</td>
      </tr>
      <tr>
        <td>3</td>
        <td>Balrampur</td>
        <td>9453004133</td>
      </tr>
      <tr>
        <td>4</td>
        <td>Bareilly</td>
        <td>9453004109</td>
      </tr>
      <tr>
        <td>5</td>
        <td>Bijnor</td>
        <td>9453004183</td>
      </tr>
      <tr>
        <td>6</td>
        <td>Deoria</td>
        <td>9453004151</td>
      </tr>
      <tr>
        <td>7</td>
        <td>Gonda</td>
        <td>9453004131</td>
      </tr>
      <tr>
        <td>8</td>
        <td>Gorakhpur</td>
        <td>9453004147</td>
      </tr>
      <tr>
        <td>9</td>
        <td>Jyotiba Phule Nagar</td>
        <td>9453004181</td>
      </tr>
      <tr>
        <td>10</td>
        <td>Kushinagar</td>
        <td>9453004153</td>
      </tr>
      <tr>
        <td>11</td>
        <td>Maharajganj</td>
        <td>9453004149</td>
      </tr>
      <tr>
        <td>12</td>
        <td>Moradabad</td>
        <td>9453004179</td>
      </tr>
      <tr>
        <td>13</td>
        <td>Pilibhit</td>
        <td>9453004113</td>
      </tr>
      <tr>
        <td>14</td>
        <td>Rampur</td>
        <td>9453004185</td>
      </tr>
      <tr>
        <td>15</td>
        <td>Sambhal</td>
        <td>9453004019</td>
      </tr>
      <tr>
        <td>16</td>
        <td>Shahjahanpur</td>
        <td>9453004115</td>
      </tr>
      <tr>
        <td>17</td>
        <td>Srawasti</td>
        <td>9453004137</td>
      </tr>
    </tbody>
  </table>      
  
</div>
    </div>
    <div id="menu2" class="tab-pane fade">
      <div class="container">
  <h3>DC-Com Contact Details</h3>
              
  <table class="table table-striped">
    <thead>
      <tr>
        <th>SL.No.</th>
        <th>District</th>
         <th>Name</th>
        <th>Designation</th>
         <th>Contact No.</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>1</td>
        <td>Gorakhpur</td>
        <td>SK Chaubey</td>
        <td>DC- Com</td>
        <td>9415904311</td>
      </tr>
      <tr>
        <td>2</td>
        <td>Gonda</td>
        <td>Rajesh Kumar Singh</td>
        <td>DC</td>
        <td>9415904024</td>
      </tr>
      <tr>
        <td>3</td>
        <td>Bahraich</td>
        <td>RK Singh</td>
        <td>DC- Com</td>
        <td>9415904311</td>
      </tr>
      <tr>
        <td>4</td>
        <td>Kushinagar</td>
        <td>Satyajeet Dwiedi</td>
        <td>DC</td>
        <td>9450455533</td>
      </tr>
      <tr>
        <td>5</td>
        <td>Balrampur</td>
        <td>Miss. Aabha Tripathi</td>
        <td>DC(ID)</td>
        <td>9415904025</td>
      </tr>
      <tr>
        <td>6</td>
        <td>Maharajganj</td>
        <td>Krishana Kumar Singh</td>
        <td>DC-Com</td>
        <td>9415266077</td>
      </tr>
      <tr>
        <td>7</td>
        <td>Shrawsati</td>
        <td>Ajit Kumar Upadhya</td>
        <td>DC- Com</td>
        <td>9415242945</td>
      </tr>
      <tr>
        <td>8</td>
        <td>Deoriya</td>
        <td>Gyanendra Singh</td>
        <td>DC</td>
        <td>9415904056</td>
      </tr>
      <tr>
        <td>9</td>
        <td>Moradabad</td>
        <td>Somdutt Singh</td>
        <td>DC- Com</td>
        <td>9410283831</td>
      </tr>
      <tr>
        <td>10</td>
        <td>JP nagar (Amroha)</td>
        <td>Madan Pal singh</td>
        <td>DC- Com</td>
        <td>9411811682</td>
      </tr>
      <tr>
        <td>11</td>
        <td>Bijnor</td>
        <td>Mitra Lal Gautam</td>
        <td>DC- Com</td>
        <td>9411289117</td>
      </tr>
      <tr>
        <td>12</td>
        <td>Sambhal</td>
        <td>Deen Dayal Sharma</td>
        <td>DC-MDM</td>
        <td>9453004020</td>
      </tr>
      <tr>
        <td>13</td>
        <td>Rampur</td>
        <td>Satendra  Sharma</td>
        <td>DC-IED</td>
        <td>9415904029</td>
      </tr>
      <tr>
        <td>14</td>
        <td>Bareilly</td>
        <td>Rakesh Babu Mathur</td>
        <td>DC</td>
        <td>9415904171</td>
      </tr>
      <tr>
        <td>15</td>
        <td>Badaun</td>
        <td>PC Srivastav</td>
        <td>DC-Com</td>
        <td>9415904088</td>
      </tr>
      <tr>
        <td>16</td>
        <td>Pilibhit</td>
        <td>Rakesh Kumar Patel</td>
        <td>DC- Com</td>
        <td>9415904018</td>
      </tr>
       <tr>
        <td>17</td>
        <td>Shahjhanpur</td>
        <td>Alok Chandra Mishra</td>
        <td>DC- Com</td>
        <td>9451103457</td>
      </tr>
      
    </tbody>
  </table>
</div>
    </div>
    <div id="menu3" class="tab-pane fade">
      <div class="container">
  <h3>EMIS Contact Details</h3>
              
  <table class="table table-striped">
    <thead>
      <tr>
        <th>SL. No.</th>
        <th>District</th>
        <th>Name</th>
        <th>Designation</th>
        <th>Contact No.</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>1</td>
        <td>Gorakhpur</td>
        <td>Ashish Pandey</td>
        <td>EMIS</td>
        <td>9889595762</td>
      </tr>
       <tr>
        <td>2</td>
        <td>Gonda</td>
        <td>Jagdish Sharan Gupta</td>
        <td>MIS</td>
        <td>8765959720</td>
      </tr>
       <tr>
        <td>3</td>
        <td>Bahraich</td>
        <td>Israr Ali</td>
        <td>EMIS</td>
        <td>8765959655</td>
      </tr>
       <tr>
        <td>4</td>
        <td>Kushinagar</td>
        <td>Neeraj pandey</td>
        <td>CO</td>
        <td>9598183089</td>
      </tr>
       <tr>
        <td>5</td>
        <td>Balrampur</td>
        <td>Sri Ajay Kumar Srivastava</td>
        <td>CO</td>
        <td>8765959662</td>
      </tr>
       <tr>
        <td>6</td>
        <td>Maharajganj</td>
        <td>Dinesh Kumar Mishra</td>
        <td>EMIS</td>
        <td>8009407978</td>
      </tr>
       <tr>
        <td>7</td>
        <td>Shrawsati</td>
        <td>Farhan Sidhiqui</td>
        <td>MIS</td>
        <td>9918528727</td>
      </tr>
       <tr>
        <td>8</td>
        <td>Deoriya</td>
        <td>Alok Kumar Mishra</td>
        <td>EMIS</td>
        <td>8765959687</td>
      </tr>
       <tr>
        <td>9</td>
        <td>Moradabad</td>
        <td>Chandra Shekhar</td>
        <td>CO</td>
        <td>9927941819</td>
      </tr>
       <tr>
        <td>10</td>
        <td>JP nagar (Amroha)</td>
        <td>Shumaiyala Javed</td>
        <td>CO</td>
        <td>8381838165</td>
      </tr>
       <tr>
        <td>11</td>
        <td>Bijnor</td>
        <td>Manoj Kumar</td>
        <td>EMIS</td>
        <td>8765959682</td>
      </tr>
       <tr>
        <td>12</td>
        <td>Sambhal</td>
        <td>Deepak Kumar</td>
        <td>CO</td>
        <td>7534071161</td>
      </tr>
       <tr>
        <td>13</td>
        <td>Gorakhpur</td>
        <td>Ashish Pandey</td>
        <td>EMIS</td>
        <td>9889595762</td>
      </tr>
       <tr>
        <td>14</td>
        <td>Rampur</td>
        <td>Tabish Rasool Khan </td>
        <td>CO</td>
        <td>8765959723</td>
      </tr>
       <tr>
        <td>15</td>
        <td>Badaun</td>
        <td>Harendra Kumar</td>
        <td>CO</td>
        <td>9889595762</td>
      </tr>
       <tr>
        <td>16</td>
        <td>Pilibhit</td>
        <td>Pawan Agarwal</td>
        <td>EMIS</td>
        <td>8765959663</td>
      </tr>
       <tr>
        <td>17</td>
        <td>Shahjhanpur</td>
        <td>Nikhil Saxena</td>
        <td>CO</td>
        <td>9235383424</td>
      </tr>
    </tbody>
  </table>
</div>
    </div>
    <div id="menu4" class="tab-pane fade">
    <div class="container">
     <h3>RMSA Contact Details</h3>
     <table class="table table-striped">
     <thead>
      <tr>
      	<th>SL No.</th>
        <th>District</th>
        <th>Name</th>
        <th>Designation</th>
        <th>Contact No.</th>
        <th>Email ID</th>
      </tr>
    </thead>
    <tbody>

    <tr>
    	<td>1</td>
        <td>Amroha</td>
        <td>Sri Ramagya Kumar</td>
        <td>DIOS</td>
        <td>9454457307</td>
        <td>jpnagardios@gmail.com</td>
      </tr>
      <tr>
      	<td>2</td>
        <td>Amroha</td>
        <td>Bhupendra Kumar</td>
        <td>Computer Operator</td>
        <td>8057836867</td>
        <td>rmsa.jpnagar@gmail.com</td>
      </tr>
        <tr>
        <td>3</td>
        <td>Badaun</td>
        <td>Sri Munne Ali</td>
        <td>DIOS</td>
        <td>9454457298</td>
        <td>budaundios@gmail.com</td>
      </tr>
       <tr>
       	<td>4</td>
        <td>Badaun</td>
        <td>Mr. Rajeev Kumar Bharti</td>
        <td>Computer Operator</td>
        <td>8859370307</td>
        <td>rmsa.badaun@gmail.com</td>
      </tr>
      <tr>
      	<td>5</td>
        <td>Bahraich</td>
        <td>Sri Rajendra Kumar</td>
        <td>DIOS</td>
        <td>9454457340</td>
        <td>diosbahraich@gmail.com</td>
      </tr>
      <tr>
      <td>6</td>
      <td>Bahraich</td>
      <td>Mr. Arvind Kumar Yadav</td>
      <td>Computer Operator</td>
      <td>9935332822</td>
      <td>rmsa.bahraich@gmail.com</td>
      </tr>
       <tr>
       <td>7</td>
        <td>Balrampur</td>
        <td>Sri Hriday Narayan Tripathi</td>
        <td>DIOS</td>
        <td>9454457335</td>
        <td>diosbalrampur@gmail.com</td>
      </tr>
      <tr>
      	<td>8</td>
      	<td>Balrampur</td>
      	<td>Mr. Ashok Chandra Gupta</td>
      	<td>Computer Operator</td>
      	<td>9451670279</td>
      	<td>rmsa.balrampur@gmail.com</td>
      </tr>
      <tr>
      	<td>9</td>
        <td>Bareilly</td>
        <td>Sri Achal Mishra</td>
        <td>DIOS</td>
        <td>9454457296</td>
        <td>bareillydios@gmail.com</td>
      </tr>
      <tr>
      	<td>10</td>
        <td>Bareilly</td>
        <td>Mr. Sonu Kumar Yadav</td>
        <td>Computer Operator</td>
        <td>9456061739</td>
        <td>rmsa.bareilly@gmail.com</td>
      </tr>
       <tr>
       	<td>11</td>
        <td>Bijnour</td>
        <td>Sri Rajesh Kumar</td>
        <td>DIOS</td>
        <td>9454457311</td>
        <td>bijnordios@gmail.com</td>
      </tr>
      <tr>
      	<td>12</td>
      	<td>Bijnour</td>
      	<td>Mr. Pallav</td>
      	<td>Clerk</td>
      	<td>9520475771</td>
      	<td>rmsa.bijnor@gmail.com</td>
      </tr>
      <tr>
      	<td>13</td>
        <td>Deoria</td>
        <td>Sri Shiv Chand Ram</td>
        <td>DIOS</td>
        <td>9454457346</td>
        <td>diosdeoria@gmail.com</td>
      </tr>
      <tr>
      	<td>14</td>
        <td>Deoria</td>
        <td>Sri Uday Prakash Singh</td>
        <td>DIOS</td>
        <td>9454457348</td>
        <td>dionkushinagar@gmail.com</td>
      </tr>
      <tr>
      	<td>15</td>
      	<td>Deoria</td>
      	<td>Mr. Satish Pandey</td>
      	<td>Computer Operator</td>
      	<td>9651384992</td>
      	<td>rmsa.deoria@gmail.com</td>
      </tr>
      <tr>
      	<td>16</td>
      	<td>Deoria</td>
      	<td>Mr. Asif Ali</td>
      	<td>Computer Operator</td>
      	<td>8546079135</td>
      	<td>rmsa.kushinagar@gmail.com</td>      
      </tr>
       <tr>
       <td>17</td>
        <td>Gonda</td>
        <td>Sri Ram Khelawan Verma</td>
        <td>DIOS</td>
        <td>9454457333</td>
        <td>diosgonda@gmail.com</td>
      </tr>
      <tr>
      	<td>18</td>
      	<td>Gonda</td>
      	<td>Mr. Ajeet Kumar</td>
      	<td>Computer Operator</td>
      	<td>9936136656</td>
      	<td>rmsa.gonda@gmail.com </td>      	
      </tr>
      <tr>
      	<td>19</td>
        <td>Gorakhpur</td>
        <td>Sri Gyanendra Pratap Singh</td>
        <td>DIOS</td>
        <td>9454457342</td>
        <td>diosgkp@gmail.com</td>
      </tr>
      <tr>
      	<td>20</td>
      	<td>Gorakhpur</td>
      	<td>Mr. Ambikeshwar Singh</td>
      	<td>Computer Operator</td>
      	<td>9889138337</td>
      	<td>rmsagorakhpur1@gmail.com </td>
      </tr>
      <tr>
      	<td>21</td>
        <td>Mahrajganj</td>
        <td>Smt Mridula Anand</td>
        <td>DIOS</td>
        <td>9454457344</td>
        <td>diosmaharajganj123@gmail.com</td>
      </tr>
      <tr>
      	<td>22</td>
      	<td>Mahrajganj</td>
      	<td>Mr. Awadh Kumar Srivastava</td>
      	<td>Accountant</td>
      	<td>9125590288</td>
      	<td>rmsa.maharajganj@gmail.com</td>
      </tr>
      <tr>
      	<td>23</td>
        <td>Moradabad</td>
        <td>Sri Pradeep Kr Dwivedi</td>
        <td>DIOS</td>
        <td>9454457305</td>
        <td>moradabaddios@gmail.com</td>
      </tr>
      <tr>
      	<td>24</td>
        <td>Moradabad</td>
        <td>Mr. Gaurav Vishnoi</td>
        <td>Computer Operator</td>
        <td>7409021897</td>
        <td>rmsa.moradabad@gmail.com</td>
      </tr>
       <tr>
       	<td>25</td>
        <td>Pilibhit</td>
        <td>Sri Rajesh Kumar Verma</td>
        <td>DIOS</td>
        <td>9454457301</td>
        <td>pilibhitdios@gmail.com</td>
      </tr>
      <tr>
      	<td>26</td>
        <td>Pilibhit</td>
        <td>Mr. Prabhakar Yadav</td>
        <td>Computer Operator</td>
        <td>8799493329</td>
        <td>rmsa.pilibhit@gmail.com</td>
      </tr>
      <tr>
      	<td>27</td>
        <td>Rampur</td>
        <td>Sri Jai Karan Lal Verma</td>
        <td>DIOS</td>
        <td>9454457309</td>
        <td>rampurdios@gmail.com</td>
      </tr>
      <tr>
      	<td>28</td>
        <td>Rampur</td>
        <td>Mr. Manish Kumar</td>
        <td>Computer Operator</td>
        <td>9639229572</td>
        <td>rmsa.rampur@gmail.com</td>
      </tr>
       <tr>
       	<td>29</td>
        <td>Sambhal</td>
        <td>Sri Vinod Kumar</td>
        <td>DIOS</td>
        <td>9454457570</td>
        <td>diossambhal@gmail.com</td>
      </tr>
      <tr>
      	<td>30</td>
        <td>Sambhal</td>
        <td>Mr. Zuber Ahmad</td>
        <td>Computer Operator</td>
        <td>9456912388</td>
        <td>diossambhal@gmail.com</td>
      </tr>
       <tr>
       	<td>31</td>
        <td>Shahjahanpur</td>
        <td>Sri K.L Verma</td>
        <td>DIOS</td>
        <td>9454457303</td>
        <td>diosshahjahanpur@gmail.com</td>
      </tr>
      <tr>
      	<td>32</td>
        <td>Shahjahanpur</td>
        <td>Mr. Gaurav Saxena</td>
        <td>Computer Operator</td>
        <td>9889812555</td>
        <td>rmsa.shahjahanpur@gmail.com</td>
      </tr>
      <tr>
     	<td>33</td>
        <td>Shrawasti</td>
        <td>Sri Anoop Kumar</td>
        <td>DIOS</td>
        <td>9454457338</td>
        <td>diosshravasti@gmail.com</td>
      </tr>
      <tr>
      	<td>34</td>
      	<td>Shrawasti</td>
      	<td>Mr. Pankaj Kumar Tripathi</td>
      	<td>Computer Operator</td>
      	<td>9415607604</td>
      	<td>rmsa.shrawasti@gmail.com</td>
      </tr>      
    </tbody>
     </table>     
    </div>
    </div>
     <div id="menu5" class="tab-pane fade">
      <div class="container">
       <h3>JD Contact Details</h3>
     <table class="table table-striped">
       <thead>
       <tr>
      	<th>SL No.</th>
        <th>District</th>
        <th>Name</th>       
        <th>Contact No.</th>
        <th>Email ID</th>
      </tr>
     </thead>  
     <tbody>
     <tr>
     	<td>1</td>
     	<td>Devipatan</td>
     	<td>Mr.Om Prakash Dwivedi</td>
     	<td>9454457519</td>
     	<td>jdedudevipatan@gmail.com</td>
     </tr>
      <tr>
     	<td>2</td>
     	<td>Gorakhpur</td>
     	<td>Mr.Yogendra Nath Singh</td>
     	<td>9454457521</td>
     	<td>jdgorakhpur1@gmail.com</td>
     </tr>
      <tr>
     	<td>3</td>
     	<td>Moradabad</td>
     	<td>Mr.Mahendra Dev</td>
     	<td>9454457516</td>
     	<td>jdmoradabad123@gmail.com</td>
     </tr>
      <tr>
     	<td>4</td>
     	<td>Bareilly</td>
     	<td>Smt Anjana Goel</td>
     	<td>9454457517</td>
     	<td>jdbareilly@gmail.com</td>
     </tr>
     </tbody>
        
     </table>
      </div>
      </div>
  </div>
</div>
<jsp:include page="fragments/footer.jsp"></jsp:include>

<script type="text/javascript">
$(document).ready(function(){
	$("#key-contact").parent().addClass("active");
})
</script>
</body>
</html>