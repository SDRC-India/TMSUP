<!-- 
@author Laxman (laxman@sdrc.co.in)
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html ng-app="linkApp">
<head>

<title>TMS-UP | Link</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/style.css">
<spring:url value="/webjars/jquery/2.0.3/jquery.min.js" var="jQuery" />
	<script src="${jQuery}"></script>
<!-- <script src="resources/js/angular.min.js"></script> -->
<style type="text/css">
/* @media (max-width: 767px){
		div#mymain{
			margin-bottom: 0px;
		}
	} */
	#sel1 {
	width: 30%;
}
</style>
</head>

<body ng-controller="LinkController" ng-cloak>
	<style type="text/css">
div#mymain {
	margin-bottom: 0px !important;
}
</style>
	<jsp:include page="fragments/header.jsp"></jsp:include>
	<div id="mymain">
		<div class="container ">
			<div class="page-margin">
				<div class="userLocation">
					{{user.stateName}} <i class="fa fa-chevron-right"
						aria-hidden="true"></i> {{user.distName}} <i
						class="fa fa-chevron-right" aria-hidden="true"></i>
					{{user.blockName}}
				</div>
				<div class="filter-main" style="position: relative; margin-top: 66px;">
				<div class="filter-section-container" style="text-align: right; position: absolute; right: 0px;">
				<div class="filter-section" ng-show="!showStudentTable  && !addNewStudentSectionOpened" style="float: none;">
							<input type="text" ng-change="isTableDataAvailable(searchSchoolByBlock, allSchoolList)" ng-model="searchSchoolByBlock" placeholder="Search School" style="margin-bottom: 10px;border: 1px solid #333a3b;"><i
								class="fa fa-search" aria-hidden="true" style="background-color: #333a3b;color: #FFF;"></i>
						</div>
						</div>
						</div>
				<div style="overflow: hidden;">	
				<div class="static-header-container static-header-school-container" ng-show="!showStudentTable  && !addNewStudentSectionOpened"></div>	
				<div class="table-responsive table-container-margin table-header-link-fixed school-table-response"
					ng-show="!showStudentTable  && !addNewStudentSectionOpened" style="width: 100%">
					
					<table items="tableData" show-filter="true" ng-show="!showStudentTable  && !addNewStudentSectionOpened" cellpadding="0"
						cellspacing="0" border="0"
						class="dataTable table table-striped schoolTable custom-table "
						id="dataTable">
						<thead >
							<tr>
								<th
									style="position: relative; background-color: rgba(0, 0, 0, 0.85); color: #FFF"
									nowrap><div>
										School Name
										<div class="sorting1" ng-click="order('School Name')">
											<i class="fa fa-long-arrow-up asc" aria-hidden="true"
												ng-class="{'hiding': sortType == 'School Name' &&  sortReverse == true}"></i>
											<i class="fa fa-long-arrow-down hiding dsc"
												aria-hidden="true"
												ng-class="{'hiding': (sortType == 'School Name' &&  sortReverse == false) || sortType != 'School Name'}"></i>
										</div>
									</div></th>
								<th
									style="position: relative; min-width: 100px; background-color: #BABABA; color: #000"
									nowrap><div>
										Total
										<div class="sorting1" ng-click="order('Total')">
											<i class="fa fa-long-arrow-up asc" aria-hidden="true"
												ng-class="{'hiding': sortType == 'Total' &&  sortReverse == true}"></i>
											<i class="fa fa-long-arrow-down hiding dsc"
												aria-hidden="true"
												ng-class="{'hiding': (sortType == 'Total' &&  sortReverse == false) || sortType != 'Total'}"></i>
										</div>
									</div></th>
								<th
									style="position: relative; min-width: 100px; background-color: #bae174; color: #000"
									nowrap>Enrolled
									<div class="sorting1" ng-click="order('Admitted')">
										<i class="fa fa-long-arrow-up asc" aria-hidden="true"
											ng-class="{'hiding': sortType == 'Admitted' &&  sortReverse == true}"></i>
										<i class="fa fa-long-arrow-down hiding dsc" aria-hidden="true"
											ng-class="{'hiding': (sortType == 'Admitted' &&  sortReverse == false) || sortType != 'Admitted'}"></i>
									</div>
								</th>
								<th
									style="position: relative; min-width: 100px; background-color: rgba(240, 114, 88, 0.85); color: #000"
									nowrap>Left Out
									<div class="sorting1" ng-click="order('Left Out')">
										<i class="fa fa-long-arrow-up asc" aria-hidden="true"
											ng-class="{'hiding': sortType == 'Left Out' &&  sortReverse == true}"></i>
										<i class="fa fa-long-arrow-down hiding dsc" aria-hidden="true"
											ng-class="{'hiding': (sortType == 'Left Out' &&  sortReverse == false) || sortType != 'Left Out'}"></i>
									</div>
								</th>
								<th
									style="position: relative; min-width: 100px; background-color: rgba(240, 114, 88, 0.85); color: #000"
									nowrap>Left Out (%)
									<div class="sorting1" ng-click="order('Left Out Percent')">
										<i class="fa fa-long-arrow-up asc" aria-hidden="true"
											ng-class="{'hiding': sortType == 'Left Out Percent' &&  sortReverse == true}"></i>
										<i class="fa fa-long-arrow-down hiding dsc" aria-hidden="true"
											ng-class="{'hiding': (sortType == 'Left Out Percent' &&  sortReverse == false) || sortType != 'Left Out Percent'}"></i>
									</div>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr class="rowSchool" ng-click="getStudentsList(rowData)" style="cursor: pointer;"
								ng-repeat="rowData in allSchoolList | filter:searchSchoolByBlock | orderBy:filterType:sortReverse">
								<td sortable="'{{rowData.column}}'">{{rowData['School Name']}} ({{rowData['uidse_code']}})</td>
								<td sortable="'{{rowData.column}}'" style="text-align: center;">{{rowData['Total']}}</td>
								<td sortable="'{{rowData.column}}'" style="text-align: center;">{{rowData['Admitted']}}</td>
								<td sortable="'{{rowData.column}}'" style="text-align: center;">{{rowData['Left Out']}}</td>
								<td sortable="'{{rowData.column}}'" style="text-align: center;">{{rowData['Left Out Percent']}}</td>
							</tr>
						</tbody>
					</table>
					<h4 class="text-center noFilteredAvailable" ng-show="!(allSchoolList | filter:searchSchoolByBlock).length && getSchoolListSuccessful">No school available according to Search</h4>
					<!-- <div ng-show="isNoTableDataAvailable">
						<h3>No school available in this name</h3>
					</div> -->
				</div>
				</div>
				<div class="student-list" ng-show="showStudentTable && !addNewStudentSectionOpened">
					<div class="school-details">
						<div class="row">
							<div class="school-name-heading col-md-6">{{selectedSchool['School Name']}} ({{selectedSchool['uidse_code']}})</div>
							<div class="back-school-list col-md-6 text-right">
								<a href="#" ng-click="backToSchoolList()"><i
									class="fa fa-arrow-left" aria-hidden="true"></i> Back to school
									list</a>
							</div>
						</div>
					</div>
					<div class="add-student">
						<a href="#" class="add-new" ng-click="addNewStudentOpen()">Add New
							Student</a>
						<div class="filter-section" ng-show="allStudents.length" >
							<input type="text" ng-model="searchStudent" placeholder="Search Student"><i
								class="fa fa-search" aria-hidden="true"></i>
						</div>
					</div>
					<div class="static-header-container static-header-student-container" ng-show="showStudentTable  && !addNewStudentSectionOpened"></div>
					<div class="table-responsive table-container-margin max-page-height table-header-link-fixed student-table-response"
						style="width: 100%" ng-show="allStudents.length">
						<table items="tableData" show-filter="true" cellpadding="0"
							cellspacing="0" border="0"
							class="dataTable table table-striped studentTable custom-table"
							id="dataTable">
							<thead>
								<tr>
									<th
										style="position: relative; background-color: rgba(0, 0, 0, 0.85); color: #FFF;min-width: 197px;"
										nowrap><div>
											Student's name
											<div class="sorting1" ng-click="order(studentName)">
												<i class="fa fa-long-arrow-up asc" aria-hidden="true"
													ng-class="{'hiding': sortType == studentName &&  sortReverse == true}"></i>
												<i class="fa fa-long-arrow-down hiding dsc"
													aria-hidden="true"
													ng-class="{'hiding': (sortType == studentName &&  sortReverse == false) || sortType != studentName}"></i>
											</div>
										</div></th>
									<th
										style="position: relative; background-color: #a4a1c2; color: #000"
										nowrap><div>
											SR No.
											<div class="sorting1" ng-click="order(srNo)">
												<i class="fa fa-long-arrow-up asc" aria-hidden="true"
													ng-class="{'hiding': sortType == srNo &&  sortReverse == true}"></i>
												<i class="fa fa-long-arrow-down hiding dsc"
													aria-hidden="true"
													ng-class="{'hiding': (sortType == srNo &&  sortReverse == false) || sortType != srNo}"></i>
											</div>
										</div></th>	
									<!-- <th
										style="position: relative; background-color: #a4a1c2; color: #000"
										nowrap><div>
											Aadhar number
											<div class="sorting1" ng-click="order('Aadhar number')">
												<i class="fa fa-long-arrow-up asc" aria-hidden="true"
													ng-class="{'hiding': sortType == 'Aadhar number' &&  sortReverse == true}"></i>
												<i class="fa fa-long-arrow-down hiding dsc"
													aria-hidden="true"
													ng-class="{'hiding': (sortType == 'Aadhar number' &&  sortReverse == false) || sortType != 'Aadhar number'}"></i>
											</div>
										</div></th> -->
									<th
										style="position: relative; min-width: 150px; background-color: #a4a1c2; color: #000"
										nowrap>Father's name
										<div class="sorting1" ng-click="order(fatherName)">
											<i class="fa fa-long-arrow-up asc" aria-hidden="true"
												ng-class="{'hiding': sortType == fatherName &&  sortReverse == true}"></i>
											<i class="fa fa-long-arrow-down hiding dsc"
												aria-hidden="true"
												ng-class="{'hiding': (sortType == fatherName &&  sortReverse == false) || sortType != fatherName}"></i>
										</div>
									</th>
									<th
										style="position: relative; min-width: 150px; background-color: #a4a1c2; color: #000"
										nowrap>Mother's name
										<div class="sorting1" ng-click="order(motherName)">
											<i class="fa fa-long-arrow-up asc" aria-hidden="true"
												ng-class="{'hiding': sortType == motherName &&  sortReverse == true}"></i>
											<i class="fa fa-long-arrow-down hiding dsc"
												aria-hidden="true"
												ng-class="{'hiding': (sortType == motherName &&  sortReverse == false) || sortType != motherName}"></i>
										</div>
									</th>
									<th
										style="position: relative; min-width: 115px; background-color: #a4a1c2; color: #000"
										nowrap>Gender
										<div class="sorting1" ng-click="order('Gender')">
											<i class="fa fa-long-arrow-up asc" aria-hidden="true"
												ng-class="{'hiding': sortType == 'Gender' &&  sortReverse == true}"></i>
											<i class="fa fa-long-arrow-down hiding dsc"
												aria-hidden="true"
												ng-class="{'hiding': (sortType == 'Gender' &&  sortReverse == false) || sortType != 'Gender'}"></i>
										</div>
									</th>
									<th
										style="position: relative; min-width: 134px; background-color: #a4a1c2; color: #000"
										nowrap>Social&nbsp;Category
										<div class="sorting1" ng-click="order('Caste')">
											<i class="fa fa-long-arrow-up asc" aria-hidden="true"
												ng-class="{'hiding': sortType == 'Caste' &&  sortReverse == true}"></i>
											<i class="fa fa-long-arrow-down hiding dsc"
												aria-hidden="true"
												ng-class="{'hiding': (sortType == 'Caste' &&  sortReverse == false) || sortType != 'Caste'}"></i>
										</div>
									</th>
									
									<th
										style="position: relative; min-width: 200px; background-color: #a4a1c2; color: #000"
										nowrap>Enrolled to
										<div class="sorting1" ng-click="order('school Name')">
											<i class="fa fa-long-arrow-up asc" aria-hidden="true"
												ng-class="{'hiding': sortType == 'school Name' &&  sortReverse == true}"></i>
											<i class="fa fa-long-arrow-down hiding dsc"
												aria-hidden="true" ng-class="{'hiding': (sortType == 'school Name' &&  sortReverse == false) || sortType != 'school Name'}"></i>
										</div>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-click="getChartView(rowData)"
									ng-repeat="rowData in allStudents | filter:searchStudent | orderBy:filterType:sortReverse">
									<td sortable="'{{rowData.column}}'"><div
											class="control-group">
											<label class="control control--checkbox">{{rowData["Student's Name"]}}<input type="checkbox" ng-checked="rowData.Status == 'true'" ng-disabled="rowData.Status == 'true'" ng-model="rowData.checked" />
												<div class="control__indicator"></div>
											</label>
										</div></td>
										<td sortable="'{{rowData.column}}'" style="text-align: center;">{{rowData["SR No."]}}</td>
									<td sortable="'{{rowData.column}}'" style="text-align: center;">{{rowData["Father's Name"]}}</td>
									
									<td sortable="'{{rowData.column}}'" style="text-align: center;">{{rowData["Mother's Name"]}}</td>
									<td sortable="'{{rowData.column}}'" style="text-align: center;">{{rowData["Gender"]}}</td>
									<td sortable="'{{rowData.column}}'" style="text-align: center;">{{rowData["Caste"]}}</td>
									<td sortable="'{{rowData.column}}'" style="text-align: center;"
										ng-class="{'notAdmitted':rowData.Status == 'false'}">{{rowData['school Name'] ? rowData['school Name'] + (rowData['udise_code'] != "" ? " (" + rowData['udise_code'] + ")": ""):"Not enrolled"}}</td>
								</tr>
							</tbody>
						</table>
						<h4 class="text-center noFilteredAvailable" ng-show="!(allStudents | filter:searchStudent).length">No student found according to Search</h4>
					</div>
					<div class="link-submit" ng-show="allStudents.length && isNotAdmittedStudentAvailable">
						<button ng-click="linkStudentModal()">Enrol Student</button>
					</div>
				</div>
				<div ng-show="addNewStudentSectionOpened">
				
				<form class="form-horizontal form_student" style="margin: 22px 0 0 0;">

					<div class="school-details student_detail">
						<div class="row">
							<div
								class="school-name-heading student_name_add student_name_add1 col-md-6">Add
								New Student</div>
							<div class="back-school-list col-md-6 text-right padd_right">
								<a href="javascript:void(0);" ng-click="backToStudentList()"> <i class="fa fa-arrow-left"
									aria-hidden="true"> <span style="font-family: Questrial;">Back
											to Student list</span>
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
								ng-model="addStudentschoolName" disabled>
						</div>
					</div>
		
					<div class="form-group">
						<label class="control-label col-sm-4" for="s1">U-DISE Code:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="sname"
								placeholder="U-DISE Code" name="sname" ng-model="addStudentUdiseCode"
								disabled>
						</div>
					</div>
		
					<div class="form-group">
						<label class="control-label col-sm-4" for="s1">District:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="sname"
								placeholder="Enter the District" name="sname"
								ng-model="user.distName" disabled>
						</div>
					</div>
		
					<div class="form-group">
						<label class="control-label col-sm-4" for="s1">Block:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="sname"
								placeholder="Enter the Block" name="sname"
								ng-model="user.blockName" disabled>
						</div>
					</div>
		
		
		
					<div class="form-group">
						<label class="control-label col-sm-4" for="s1">* Student's name:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" fifty-characters-validation id="sname"
								placeholder="Enter Student's name" name="sname"
								ng-model="addStudentStudentName">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" >* Father's
							name:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="fatherName"
								placeholder="Enter Father's name" name="fname"
								ng-model="addStudentFathersName"  fifty-characters-validation>
						</div>
					</div>
		
					<div class="form-group">
						<label class="control-label col-sm-4">* Mother's
							name:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="motherName"
								placeholder="Enter Mother's name" name="mname"
								ng-model="addStudentMothersName"  fifty-characters-validation>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4">* SR (Registration) No.:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="srNo"
								placeholder="Enter SR (Registration) No." name="srNo"
								ng-model="studentRegistrationNo"  twenty-alpha-numeric-validation>
						</div>
					</div>
					<!-- <div class="form-group">
		      <label class="control-label col-sm-2" for="dob">Date of Birth:</label>
		      <div class="col-sm-5">          
		        <input type="text" id="datepicker" class="form-control"  placeholder="Enter dob" name="dob">
		      </div>
		    </div> -->
		
					<div class="form-group">
						<label class="control-label col-sm-4" for="gender">* Gender:</label>
						<div class="col-sm-4">
							<select class="form-control select_fld " ng-model="selectedGender">
								<option value="">Select</option>
								<option>Boy</option>
								<option>Girl</option>
								<option>Third Gender</option>
		
							</select>
						</div>
					</div>
		
		
					<div class="form-group">
						<label class="control-label col-sm-4" for="adhar">* Social
							Categories:</label>
						<div class="col-sm-4">
							<select class="form-control select_fld" ng-model="selectedCaste">
								<option value="">Select</option>
								<option ng-repeat="studentCaste in studentCastes"
									value="{{studentCaste.key}}">{{studentCaste.value}}</option>
							</select>
						</div>
					</div>
		
		
					<div class="form-group ">
						<div class="col-sm-offset-4 col-sm-5 submt_frm_margin">
							<button type="submit" class="btn btn-default btn_submit"
								ng-click="submitData()">Submit</button>
						</div>
					</div>

		</form>
		</div>
			</div>
		</div>
	</div>


	<!-- All Modals  -->
	<div id="link" class="modal fade modal-center custom-modal"
		role="dialog">
		<div class="modal-dialog">
			<!-- Modal content -->
			<div class="modal-content">
				<button type="button" class="custom-close close"
					data-dismiss="modal"
					style="margin-top: 10px; color: #000; right: 10px; position: absolute; z-index: 9;">&times;</button>
				<div class="modal-body text-center">
					<div class="linkhead">Enrol Student</div>
					<div class="linkbody">
						<div class="select-container dist-list text-center">
							<div class="input-group" style="margin: auto;">
								<input type="text" placeholder="Radius" id="radius"
									class="form-control not-visible-input" name="radius"
									readonly="" ng-model="selectedSchoolDistance">
								<div class="input-group-btn" style="position: relative;">
									<button data-toggle="dropdown"
										class="btn btn-color dropdown-toggle" type="button">
										<i class="fa fa-list"></i>
									</button>
									<ul class="dropdown-menu" role="menu">
										<li ng-click="selectDistance('Within 5km distance');"><a href="">Within 5 km distance</a></li>
										<li ng-click="selectDistance('Within 7km distance');"><a href="">Within 7 km distance</a></li>
										<li ng-click="selectDistance('Within 10km distance');"><a href="">Within 10 km distance</a></li>
										<li ng-click="selectDistance('From Existing');"><a
											href="">From Existing</a></li>
										<li ng-click="selectDistance('Other School in UP');"><a href="">Other School in UP</a></li>
										<li ng-click="selectDistance('Other State');"><a href="">Other State</a></li>
										<li ng-click="selectDistance('Migration');"><a href="">Migration</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div class="row text-center"
							ng-show="selectedSchoolDistance == 'From Existing'"
							style="margin: 15px 0 20px">
							<div
								class="select-container dist-list text-center col-md-3 col-md-offset-2">
								<div class="input-group" style="margin: auto;">
									<input type="text" placeholder="District" id="dist"
										class="form-control not-visible-input" name="radius"
										readonly="" ng-model="selectedDistrict.name">
									<div class="input-group-btn" style="position: relative;">
										<button data-toggle="dropdown"
											class="btn btn-color dropdown-toggle" type="button">
											<i class="fa fa-list"></i>
										</button>
										<ul class="dropdown-menu" role="menu"
											style="max-height: 350px; overflow: auto;">
											<li ng-repeat="district in allDistricts"
												ng-click="selectDistrict(district);"><a href="">{{district.name}}</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="select-container dist-list text-center col-md-3">
								<div class="input-group" style="margin: auto;">
									<input type="text" placeholder="Block" id="block"
										class="form-control not-visible-input" name="radius"
										readonly="" ng-model="selectedBlock.name">
									<div class="input-group-btn" style="position: relative;">
										<button data-toggle="dropdown"
											class="btn btn-color dropdown-toggle" type="button">
											<i class="fa fa-list"></i>
										</button>
										<ul class="dropdown-menu" role="menu"
											style="max-height: 350px; overflow: auto;">
											<li ng-repeat="block in allBlocks"
												ng-click="selectBlock(block);"><a href="">{{block.name}}</a></li>

										</ul>
									</div>
								</div>
							</div>
							<div class="filter-section-modal col-md-3">
								<input type="text" ng-model="searchSchool" style="margin-bottom: 10px;border: 1px solid #9ba3a5" placeholder="Search School"><i
								class="fa fa-search" aria-hidden="true" style="background-color: #333a3b;color: #FFF;"></i>
						</div>
						</div>
						<div style="color: #f07258;" ng-show="selectedSchoolDistance == 'From Existing' && !selectedBlock.name">Please select Block to see school list</div>
						<div ng-show="selectedSchoolDistance != 'Other School in UP' && selectedSchoolDistance != 'Other State' && selectedSchoolDistance != 'Migration' && selectedBlock.name"
							class="table-responsive table-container-margin modal-school-list"
							style="width: 100%">
							<table items="tableData" show-filter="true" cellpadding="0"
								cellspacing="0" border="0"
								class="dataTable table table-striped custom-table"
								id="dataTable">
								<thead>
									<tr>
										<th
											style="position: relative; background-color: rgba(0, 0, 0, 0.85); color: #FFF; min-width: 135px;"
											nowrap><div>
												School
												<div class="sorting1" ng-click="orderModalTable('School Name')">
													<i class="fa fa-long-arrow-up asc" aria-hidden="true"
														ng-class="{'hiding': sortType == 'School Name' &&  sortReverseModal == true}"></i>
													<i class="fa fa-long-arrow-down hiding dsc"
														aria-hidden="true"
														ng-class="{'hiding': (sortType == 'School Name' &&  sortReverseModal == false) || sortType != 'School Name'}"></i>
												</div>
											</div></th>
										<th
											style="position: relative; background-color: #a4a1c2; color: #000; min-width: 135px;"
											nowrap><div>
												U-DISE Code
												<div class="sorting1" ng-click="orderModalTable('Udise Code')">
													<i class="fa fa-long-arrow-up asc" aria-hidden="true"
														ng-class="{'hiding': sortType == 'Udise Code' &&  sortReverseModal == true}"></i>
													<i class="fa fa-long-arrow-down hiding dsc"
														aria-hidden="true"
														ng-class="{'hiding': (sortType == 'Udise Code' &&  sortReverseModal == false) || sortType != 'Udise Code'}"></i>
												</div>
											</div></th>
										<th
											style="position: relative; background-color: #a4a1c2; color: #000; min-width: 135px;"
											nowrap><div>
												District
												<div class="sorting1" ng-click="orderModalTable('District')">
													<i class="fa fa-long-arrow-up asc" aria-hidden="true"
														ng-class="{'hiding': sortType == 'District' &&  sortReverseModal == true}"></i>
													<i class="fa fa-long-arrow-down hiding dsc"
														aria-hidden="true"
														ng-class="{'hiding': (sortType == 'District' &&  sortReverseModal == false) || sortType != 'District'}"></i>
												</div>
											</div></th>

										<th
											style="position: relative; min-width: 130px; background-color: #a4a1c2; color: #000"
											nowrap>Block
											<div class="sorting1" ng-click="orderModalTable('Block')">
												<i class="fa fa-long-arrow-up asc" aria-hidden="true"
													ng-class="{'hiding': sortType == 'Block' &&  sortReverseModal == true}"></i>
												<i class="fa fa-long-arrow-down hiding dsc"
													aria-hidden="true"
													ng-class="{'hiding': (sortType == 'Block' &&  sortReverseModal == false) || sortType != 'Block'}"></i>
											</div>
										</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-click="confirmLinkModalOpen(rowData)" style="cursor: pointer;"
										ng-repeat="rowData in allSchools | filter:searchSchool | orderBy:filterType:sortReverseModal">
										<td sortable="'{{rowData.column}}'" class="text-left">{{rowData['School Name']}}</td>
										<td sortable="'{{rowData.column}}'" style="text-align: center;">{{rowData['Udise Code']}}</td>
										<td sortable="'{{rowData.column}}'" class="text-center">{{rowData['District Name']}}</td>
										<td sortable="'{{rowData.column}}'" style="text-align: center;">{{rowData['Block Name']}}</td>
									</tr>
								</tbody>
							</table>
							<div ng-show="!(allSchools | filter:searchSchool).length">No schools found</div>
							
						</div>
						
						<div ng-show="selectedSchoolDistance == 'Other School in UP' || selectedSchoolDistance == 'Other State' || selectedSchoolDistance == 'Migration' ">
							<form class="form-horizontal add-new-school">
								
								<div ng-show="selectedSchoolDistance == 'Other State' || selectedSchoolDistance == 'Migration'" class="select-container dist-list text-center">
									<div class="input-group" style="margin: auto;">
										<input type="text" placeholder="State *" id="State"
											class="form-control not-visible-input" name="state"
											readonly="" ng-model="selectedStateOtherForm.value">
											
										<div class="input-group-btn" style="position: relative;">
											<button data-toggle="dropdown"
												class="btn btn-color dropdown-toggle" type="button">
												<i class="fa fa-list"></i>
											</button>
											<ul class="dropdown-menu" role="menu"
												style="max-height: 250px; overflow: auto;">
												<li ng-repeat="state in allStatesNewSchool"
													ng-click="selectStateOtherForm(state);"><a href="">{{state.value}}</a></li>

											</ul>
										</div>
									</div>
								</div>
								<div
									class="select-container dist-list text-center list text-center">
									<div ng-show="selectedSchoolDistance != 'Other School in UP'" class="input-group" style="margin: auto;">
										<input type="text" placeholder="District *" id="dist"
											class="form-control not-visible-input" name="radius"
											readonly="" ng-model="selectedDistrictOtherForm.value">
										<div class="input-group-btn" style="position: relative;">
											<button data-toggle="dropdown"
												class="btn btn-color dropdown-toggle" type="button">
												<i class="fa fa-list"></i>
											</button>
											<ul class="dropdown-menu" role="menu"
												style="max-height: 250px; overflow: auto;">
												<li ng-repeat="district in allDistrictsNewSchool"
													ng-click="selectDistrictOtherForm(district);"><a href="">{{district.value}}</a></li>
											</ul>
										</div>
									</div>
									<div ng-show="selectedSchoolDistance == 'Other School in UP'" class="input-group" style="margin: auto;">
										<input type="text" placeholder="District *" id="dist"
											class="form-control not-visible-input" name="radius"
											readonly="" ng-model="selectedDistrictOtherForm.name">
										<div class="input-group-btn" style="position: relative;">
											<button data-toggle="dropdown"
												class="btn btn-color dropdown-toggle" type="button">
												<i class="fa fa-list"></i>
											</button>
											<ul class="dropdown-menu" role="menu"
												style="max-height: 250px; overflow: auto;">
												<li ng-repeat="district in allDistricts"
													ng-click="selectDistrictOtherForm(district);"><a href="">{{district.name}}</a></li>
											</ul>
										</div>
									</div>
								</div>
								<div class="select-container dist-list text-center" ng-show="selectedSchoolDistance == 'Other School in UP'">
									<div class="input-group" style="margin: auto;">
										<input type="text" placeholder="Block *" id="block"
											class="form-control not-visible-input" name="radius"
											readonly="" ng-model="selectedBlockOtherForm.name">
											
										<div class="input-group-btn" style="position: relative;">
											<button data-toggle="dropdown"
												class="btn btn-color dropdown-toggle" type="button">
												<i class="fa fa-list"></i>
											</button>
											<ul class="dropdown-menu" role="menu"
												style="max-height: 250px; overflow: auto;">
												<li ng-repeat="block in allBlocksNewSchool"
													ng-click="selectBlockOtherForm(block);"><a href="">{{block.name}}</a></li>

											</ul>
										</div>
									</div>
								</div>
								<input ng-show="selectedSchoolDistance != 'Migration'" type="text" placeholder="{{selectedSchoolDistance == 'Other School in UP' ? 'Name of School (max 200 char length) *':'Name of School (max 200 char length)'}}"
									class="form-control" ng-model="newSchoolName" id="schoolName">
								<input ng-show="selectedSchoolDistance == 'Other School in UP'" type="text" placeholder="U-DISE Code" only-eleven-digits
									class="form-control" ng-model="newSchoolUdiseCode" id="udiseCode">
								<!-- <div
									class="select-container dist-list text-center list text-center">
									<div class="input-group" style="margin: auto;">
										<input type="text" placeholder="School Type *" id="schoolType"
											class="form-control not-visible-input" name="radius"
											readonly="" ng-model="selectedSchoolTypeOtherForm.name">
										<div class="input-group-btn" style="position: relative;">
											<button data-toggle="dropdown"
												class="btn btn-color dropdown-toggle" type="button">
												<i class="fa fa-list"></i>
											</button>
											<ul class="dropdown-menu" role="menu"
												style="max-height: 250px; overflow: auto;">
												<li ng-repeat="schoolType in allSchoolTypes"
													ng-click="selectSchoolTypeOtherForm(schoolType);"><a href="">{{schoolType.name}}</a></li>
											</ul>
										</div>
									</div>
								</div> -->
								<button type="submit" class="btn btn-default" ng-click="saveSchool()">Save</button>
							</form>
						</div>
					</div>
				</div>
				<p class="enroll_footer" ng-show="selectedSchoolDistance != 'Other School in UP' && selectedSchoolDistance != 'Other State' && selectedSchoolDistance != 'Migration' && (allSchools | filter:searchSchool).length">* School name and UDISE code are as per UDISE Database</p>
			</div>
		</div>		
	</div>
	<div id="confirmAdmit" class="modal modal-center fade warn-modal" role="dialog"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<!-- Modal content -->
			<div class="modal-content">
				<div class="modal-body text-center">
					<div class="infohead">Confirm</div>
					<div class="warnbody">
						<!-- <ul class="admittingStudent text-left">
							<li ng-repeat="student in selectedStudents">{{student["Student's Name"]}}</li>
						</ul> -->
						<div class="confirm-msg">
							Are you sure you want to enrol the student(s) in "{{confirmAdmitSchool['School Name']}}"
						</div>	
					</div>
					<button type="button" class="btn errorOk" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn errorOk" ng-click="linkStudents(confirmAdmitSchool)">Confirm</button>
				</div>
			</div>
		</div>
	</div>
	<div id="errorModal" class="modal modal-center fade" role="dialog"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<!-- Modal content -->
			<div class="modal-content">
				<div class="modal-body text-center">
					<div class="infohead">Error</div>
					<div class="warnbody">{{errorMessage}}</div>
					<button type="button" class="btn errorOk" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<div id="successModal" class="modal custom-modal modal-center fade" role="dialog"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<!-- Modal content -->
			<div class="modal-content">
				<div class="modal-body text-center">
					<div class="infohead">Success</div>
					<div class="warnbody">{{successMessage}}</div>
					<button type="button" class="btn errorOk" data-dismiss="modal">OK</button>
				</div>
			</div>
		</div>
	</div>
	<!--  -->
	<!--footer section  -->
	<jsp:include page="fragments/footer.jsp"></jsp:include>

	
	<spring:url value="/webjars/bootstrap/3.1.1/js/bootstrap.min.js"
		var="bootstrapjs" />
	<script src="${bootstrapjs}"></script>
	<spring:url value="/webjars/angularjs/1.5.5/angular.min.js"
		var="angularmin" />
	<script src="${angularmin}" type="text/javascript"></script>
	
	<script type="text/javascript"
		src="resources/js/angularController/linkController.js"></script>
	<script type="text/javascript"
		src="resources/js/angularService/service.js"></script>

	<script type="text/javascript">
		var app = angular.module("linkApp", []);
		var myAppConstructor = angular.module("linkApp");
		myAppConstructor.controller("LinkController", linkController);
		myAppConstructor.service('httpServices', httpServices);
	</script>
	<script type="text/javascript"
		src="resources/js/angularDirective/directive.js"></script>
</body>

</html>
