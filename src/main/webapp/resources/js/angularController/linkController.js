
function linkController($scope, $http, $timeout, $filter, httpServices){
//	hard code for json section
	$scope.activeMenu = 'link';
	$scope.motherName="Mother's Name";
	$scope.fatherName="Father's Name";
	$scope.studentName="Student's Name";
	$scope.srNo = "SR No.";
	$scope.user = {
			stateName: "Uttar Pradesh",
			distName: districtName,
			blockName: blockName
	};
	$scope.isDataLoadSucess=false;
	$scope.showStudentTable = false;
	$("#loader-mask").show();
	$scope.addNewStudentSectionOpened = false;
	//all JSON calls when page load
	$scope.findSchoolByBlock = function(){
		httpServices.findSchoolByBlock().then(function(data){
			$scope.allSchoolList = data;
			$scope.searchSchoolByBlock = "";
			$scope.isDataLoadSucess=true;
			$("#loader-mask").fadeOut();
			$timeout(function(){
				if($(".table-header-link-fixed.school-table-response")[0].offsetWidth > $(".table-header-link-fixed.school-table-response")[0].clientWidth){
					var i=0;rowWidthList=[];
					$(".static-header-container.static-header-school-container").html("<div class='static-header static-school-header'>"+$(".table-header-link-fixed.school-table-response").html()+"</div>");
					$(".table-header-link-fixed.school-table-response thead tr").find("th").each(function(){
						rowWidthList.push($(this)[0].offsetWidth);
					});
					$(".static-header.static-school-header thead tr").find("th").each(function(){
						$(this).width(rowWidthList[i++]);
					});
				}
				
			}, 200);
			
		},function(){
			
		});
	};
	$scope.findSchoolByBlock();
	
	
	httpServices.getSchoolTypes().then(function(data){
		$scope.allSchoolTypes = data;
	},function(){
		
	});
	
	$scope.order = function (sortType) {  
        $scope.sortReverse = ($scope.sortType === sortType) ? !$scope.sortReverse : false;  
        $scope.sortType = sortType;  
      };
    $scope.orderModalTable = function (sortType) {  
          $scope.sortReverseModal = ($scope.sortType === sortType) ? !$scope.sortReverseModal : false;  
          $scope.sortType = sortType;  
        };  
    $scope.filterType = function(val){
      
      	if(isNaN(parseInt(val[$scope.sortType])))
      		if(!$scope.sortType)
      			return true;
      		else
      			return val[$scope.sortType];
      	else
      		return parseInt(val[$scope.sortType]);
      };
    $scope.confirmLinkModalOpen = function(rowData){
    	$("#confirmAdmit").modal("show");
    	$scope.confirmAdmitSchool = rowData;
    };
    $scope.linkStudents = function(school){
    	$scope.selectedStudentsId = [];
    	$("#loader-mask").show();
    	if(typeof school.school_id != 'undefined'){
    		var schoolId = school.school_id;
    	}
    	else
    		var schoolId = school.id;
    	for(var i=0; i<$scope.selectedStudents.length; i++){
    		$scope.selectedStudentsId.push($scope.selectedStudents[i].student_id);
    	}
    	/*httpServices.admitStudent($scope.selectedSchool.school_id, school.school_id, false, $scope.selectedStudentsId).then(function(data){
    		$scope.successMessage = "Student(s) added to the school successfully";
    		$("#link").modal("hide");
    		$("#confirmAdmit").modal("hide");
    		$("#successModal").modal("show");
    		$("#loader-mask").fadeOut();
    		$scope.getStudentsList($scope.selectedSchool);
    		$scope.findSchoolByBlock();
    	},function(){
    		
    	});*/
    	$http({
			url : "admitStudent?from_school="+$scope.selectedSchool.school_id +
				"&to_school="+ schoolId+
				"&linking="+false, 
			method : 'POST',
			data : JSON.stringify($scope.selectedStudentsId),
			contentType : 'application/json'
		}).then(function successCallback(response) {
			if(response.data.is_admitted=="true"){
			$scope.successMessage = "Student(s) enrolled successfully";
    		$("#successModal").modal("show");
    		$("#link").modal("hide");
    		$("#confirmAdmit").modal("hide");
			$("#loader-mask").fadeOut();
			$scope.getStudentsList($scope.selectedSchool);
			$scope.findSchoolByBlock();
		}
		else{
			$scope.errorMessage = "There is an error in saving data due to multiple login";
			$("#errorModal").modal('show');
			$("#link").modal("hide");
    		$("#confirmAdmit").modal("hide");
			$("#loader-mask").fadeOut();
			$scope.getStudentsList($scope.selectedSchool);
			$scope.findSchoolByBlock();
		}});
    };
    $scope.getStudentsList = function(school){
    	$scope.selectedSchool = school;
    	$scope.searchSchoolByBlock = "";
    	$("#loader-mask").show();
    	httpServices.findStudentsOfSchool(school.school_id).then(function(data){
    			$scope.allStudents = data;
    			$("#loader-mask").fadeOut();
    			$scope.studentColumns = Object.keys($scope.allStudents);
    			$scope.isNotAdmittedStudentAvailable = $filter('filter')($scope.allStudents, {"school Name":""},true).length;
    			$timeout(function(){
    				if($(".table-header-link-fixed.student-table-response")[0].offsetWidth > $(".table-header-link-fixed.student-table-response")[0].clientWidth){
    					var i=0;rowWidthList=[];
    					$(".static-header-container.static-header-student-container").html("<div class='static-header static-student-header'>"+$(".table-header-link-fixed.student-table-response").html()+"</div>");
    					$(".table-header-link-fixed.student-table-response thead tr").find("th").each(function(){
    						rowWidthList.push($(this)[0].offsetWidth);
    					});
    					$(".static-header.static-student-header thead tr").find("th").each(function(){
    						$(this).width(rowWidthList[i++]);
    					});
    				}
    				
    			}, 200);
    	},function(){
    			
    	});
    	$scope.sortReverse = false;
	    $scope.showStudentTable = true;
	    
	    	
    };
    $scope.backToSchoolList = function(){
    	$("#loader-mask").show();
    	$scope.sortReverse = false;
    	$timeout(function(){
    		$scope.allStudents = [];
        	$scope.showStudentTable = false;
        	$scope.searchStudent = "";
        	$("#loader-mask").fadeOut();
    	},500);
    	
    };
    $scope.isTableDataAvailable = function(searchSchoolByBlock, schoolList){
    	$scope.isNoTableDataAvailable = $('.rowSchool').length;
    };
    
    $scope.linkStudentModal = function(){
    	$scope.searchSchool = "";
    	$scope.selectedStudents = [];
    	$scope.selectedDistrictOtherForm = {};
    	$scope.selectedBlockOtherForm = {};
    	for(var i=0; i<$scope.allStudents.length; i++){
    		if($scope.allStudents[i].checked){
    			$scope.selectedStudents.push($scope.allStudents[i]);
    		}
    	}
    	if($scope.selectedStudents.length){
    		$("#loader-mask").show();
    		$scope.selectedSchoolDistance = "From Existing";
	    	$("#link").modal("show");
	    	httpServices.getDataForModelView($scope.selectedSchool.school_id).then(function(data){
	    		$scope.modalViewData = data;
    			$scope.distanceSchoolList = data.distanceJSON;
    			$scope.allDistricts = data.districts;
    			$scope.allBlocks = data.blocks;
    			$scope.allSchools = data.schools;
    			for(var i=0; i<$scope.allDistricts.length; i++){
    				if($scope.allDistricts[i].id == distId){
    					$scope.selectedDistrict = $scope.allDistricts[i];
    				}
    			}
    			for(var i=0; i<$scope.allBlocks.length; i++){
    				if($scope.allBlocks[i].id == blockId){
    					$scope.selectedBlock = $scope.allBlocks[i];
    				}
    			}
    			$("#loader-mask").fadeOut();
    		},function(){
    			
    		});
    	}
    	else{
    		$scope.errorMessage = "Please select atleast one student to enrol";
    		$("#errorModal").modal("show");
    	}
    	
    };
    $scope.selectDistance = function(distance){
    	
    	$scope.selectedSchoolDistance = distance;
    	if($scope.selectedSchoolDistance == 'Within 5km distance'){
    		$scope.allSchools = $scope.modalViewData.distanceJSON[0]['5'];
    	}
    	if($scope.selectedSchoolDistance == 'Within 7km distance'){
    		$scope.allSchools = $scope.modalViewData.distanceJSON[0]['5'].concat($scope.modalViewData.distanceJSON[0]['7']);
    	}
    	if($scope.selectedSchoolDistance == 'Within 10km distance'){
    		$scope.allSchools = $scope.modalViewData.distanceJSON[0]['5'].concat($scope.modalViewData.distanceJSON[0]['7']).concat($scope.modalViewData.distanceJSON[0]['10']);
    	}
    	if($scope.selectedSchoolDistance == "From Existing"){
    		$scope.allSchools = $scope.modalViewData.schools;
    	}
    	/*if($scope.selectedSchoolDistance == "Other School in UP"){
    		$scope.allDistrictsNewSchool =  JSON.parse(JSON.stringify($scope.allDistricts));
    	}*/
    	if($scope.selectedSchoolDistance == "Other State" || $scope.selectedSchoolDistance == "Migration"){
    		$("#loader-mask").show();
    		$scope.selectedStateOtherForm = undefined;
    		$scope.selectedDistrictOtherForm = undefined;
    		$scope.newSchoolName='';
//    		$scope.allDistrictNewSchool = [];
    		httpServices.getOtherStates().then(function(data){
    			$("#loader-mask").fadeOut();
    			$scope.allStatesNewSchool = data;
    		},function(){
    			
    		});
    	}
    	
    	// added by harsh
    	$scope.newSchoolUdiseCode=undefined;
    	$scope.newSchoolName='';
    	$scope.searchSchool = undefined;
    	$scope.selectedDistrictOtherForm =undefined;
		 $scope.selectedBlockOtherForm=undefined;
		 //
    };
    $scope.selectDistrict = function(district){
    	if($scope.selectedDistrict != district){
	    	$scope.selectedDistrict = district;
	    	$scope.selectedBlock = {};
	    	$scope.searchSchool = undefined;
	    	$("#loader-mask").show();
	    	httpServices.getBlocksByDistrict($scope.selectedDistrict.id).then(function(data){
	    		$scope.allBlocks = data;
	    		$("#loader-mask").fadeOut();
			},function(){
				
			});
    	}
    };
    $scope.selectBlock = function(block){
    	if($scope.selectedBlock != block){
    		$("#loader-mask").show();
    		$scope.searchSchool = undefined;
    		$scope.selectedBlock = block;
    		httpServices.getSchoolsByBlock(block.id).then(function(data){
    			$scope.allSchools = data.schools;
    			$("#loader-mask").fadeOut();
    		},function(){
    			
    		});
    	}
    };
    $scope.selectDistrictOtherForm = function(district){
    	
    	$scope.selectedDistrictOtherForm = district;
    	$scope.selectedBlockOtherForm = {};
    	if($scope.selectedSchoolDistance != 'Other State' && $scope.selectedSchoolDistance != 'Migration'){
    		$("#loader-mask").show();
    		httpServices.getBlocksByDistrict($scope.selectedDistrictOtherForm.id).then(function(data){
        		$scope.allBlocksNewSchool = data;
        		$("#loader-mask").fadeOut();
    		},function(){
    			
    		});
    	}
    	
    };
    $scope.selectStateOtherForm = function(state){
    	$("#loader-mask").show();
    	$scope.selectedStateOtherForm = state;
    	$scope.selectedDistrictOtherForm = undefined;
    	$scope.selectedBlockOtherForm = undefined;
    	httpServices.getAllOtherDistricts($scope.selectedStateOtherForm.key).then(function(data){
    		$scope.allDistrictsNewSchool = data;
    		$("#loader-mask").fadeOut();
		},function(){
			
		});
    };
    $scope.selectBlockOtherForm = function(block){
    	$scope.selectedBlockOtherForm = block;
    };
    $scope.selectSchoolTypeOtherForm = function(schoolType){
    	$scope.selectedSchoolTypeOtherForm = schoolType;
    	
    };
    
    $scope.addNewStudent = function(){
    	
    };
    $scope.saveSchool = function(){
    	$scope.selectedStudentsId = [];
    	for(var i=0; i<$scope.selectedStudents.length; i++){
    		$scope.selectedStudentsId.push($scope.selectedStudents[i].student_id);
    	}
    	if(($scope.selectedSchoolDistance == 'Other State' || $scope.selectedSchoolDistance == 'Migration') && (typeof $scope.selectedStateOtherForm == 'undefined' || !Object.keys($scope.selectedStateOtherForm).length)){
    		$scope.errorMessage = "Please provide state Name";
    		$("#errorModal").modal("show");	
    		return false;
    	}
    	if(typeof $scope.selectedDistrictOtherForm == 'undefined' || !Object.keys($scope.selectedDistrictOtherForm).length){
    		$scope.errorMessage = "Please provide district Name";
    		$("#errorModal").modal("show");	
    		return false;
    	}
    	else if($scope.selectedSchoolDistance != 'Other State' && $scope.selectedSchoolDistance != 'Migration' && (typeof $scope.selectedBlockOtherForm == 'undefined' || !Object.keys($scope.selectedBlockOtherForm).length)){
    		$scope.errorMessage = "Please provide block name";
    		$("#errorModal").modal("show");	
    		return false;
    	}
    	else if($scope.selectedSchoolDistance != 'Other State' && $scope.selectedSchoolDistance != 'Migration' && !$scope.newSchoolName){
    		$scope.errorMessage = "Please provide school name";
    		$("#errorModal").modal("show");	
    		return false;
    	}
    	else if($scope.selectedSchoolDistance != 'Migration' && $scope.newSchoolName.length > 200){
    		$scope.errorMessage = "School name must be less than 200 character length";
    		$("#errorModal").modal("show");	
    		return false;
    	}
    	else{
    		$("#loader-mask").show();
    		/*httpServices.saveSchool($scope.newSchoolName, $scope.selectedDistrictOtherForm.id, $scope.selectedBlockOtherForm.id, 12, $scope.newSchoolUdiseCode, $scope.selectedSchool.school_id, $scope.selectedStudentsId).then(function(data){
    			$scope.successMessage = "Student(s) added to the school successfully";
        		$("#successModal").modal("show");
        		$("#link").modal("hide");
    			$("#loader-mask").fadeOut();
    			$scope.getStudentsList($scope.selectedSchool);
    			$scope.findSchoolByBlock();
    		},function(){
    			
    		});*/
    		if(!$scope.newSchoolUdiseCode){
    			var udiseCode = "";
    		}
    		else{
    			var udiseCode = $scope.newSchoolUdiseCode;
    		}
    		if($scope.selectedSchoolDistance == 'Other State'){
    			if(!$scope.newSchoolName){
    				$scope.newSchoolName = "";
    			}
    			$http({
        			url : "admitStudentToOtherState?schoolName="+$scope.newSchoolName +
        				"&districtId="+ $scope.selectedDistrictOtherForm.key +
        				"&stateId="+ $scope.selectedStateOtherForm.key +
        				"&fromSchool="+ $scope.selectedSchool.school_id, 
        			method : 'POST',
        			data : JSON.stringify($scope.selectedStudentsId).toString(),
        			contentType : 'application/json'
        		}).then(function successCallback(response) {
        			if(response.data.is_admitted=="true"){
        				$scope.successMessage = "Student(s) enrolled successfully";
        	    		$("#successModal").modal("show");
        	    		$("#link").modal("hide");
        				$("#loader-mask").fadeOut();
        				$scope.getStudentsList($scope.selectedSchool);
        				$scope.findSchoolByBlock();
        			}
        			else{
        				$scope.errorMessage = "There is an error in saving data due to multiple login";
        				$("#errorModal").modal('show');
        				$("#loader-mask").fadeOut();
        				$("#link").modal("hide");
        				$scope.getStudentsList($scope.selectedSchool);
        				$scope.findSchoolByBlock();
        			}});
    		}
    		else if($scope.selectedSchoolDistance == 'Migration'){
    			$http({
        			url : "migrateStudent?districtId="+ $scope.selectedDistrictOtherForm.key +
        				"&stateId="+ $scope.selectedStateOtherForm.key , 
        			method : 'POST',
        			data : JSON.stringify($scope.selectedStudentsId).toString(),
        			contentType : 'application/json'
        		}).then(function successCallback(response) {
        			if(response.data.is_admitted=="true"){
        				$scope.successMessage = "Student(s) enrolled successfully";
        	    		$("#successModal").modal("show");
        	    		$("#link").modal("hide");
        				$("#loader-mask").fadeOut();
        				$scope.getStudentsList($scope.selectedSchool);
        				$scope.findSchoolByBlock();
        			}
        			else{
        				$scope.errorMessage = "There is an error in saving data due to multiple login";
        				$("#errorModal").modal('show');
        				$("#loader-mask").fadeOut();
        				$("#link").modal("hide");
        				$scope.getStudentsList($scope.selectedSchool);
        				$scope.findSchoolByBlock();
        			}});
    		}
    		else{
    			$http({
        			url : "saveSchool?schoolName="+$scope.newSchoolName +
        				"&district="+ $scope.selectedDistrictOtherForm.id +
        				"&block="+ $scope.selectedBlockOtherForm.id +
        				"&udiseCode="+ udiseCode +
        				"&schoolType="+ 12 + 
        				"&fromSchool="+ $scope.selectedSchool.school_id, 
        			method : 'POST',
        			data : JSON.stringify($scope.selectedStudentsId).toString(),
        			contentType : 'application/json'
        		}).then(function successCallback(response) {
        			if(response.data.status==1){
	        			$scope.successMessage = "Student(s) enrolled successfully";
	            		$("#successModal").modal("show");
	            		$("#link").modal("hide");
	        			$("#loader-mask").fadeOut();
	        			$scope.getStudentsList($scope.selectedSchool);
	        			$scope.findSchoolByBlock();
        			}
        			else{
        				$scope.errorMessage = response.data.message;
        				$("#errorModal").modal('show');
        				$("#loader-mask").fadeOut();
        				$("#link").modal("hide");
        				$scope.getStudentsList($scope.selectedSchool);
	        			$scope.findSchoolByBlock();
        			}
        		});
    		}
    		
    	}
    	
    };
    
    
    // suman's code
    		
    		httpServices.getStudentCaste().then(function(data){
    			$scope.studentCastes = data;
    		},function(){
    			
    		});
    $scope.backToStudentList = function(){
    	$("#loader-mask").show();
    	$timeout(function(){
    		$scope.addNewStudentSectionOpened = false;
    		$scope.addStudentStudentName = "";
    		$scope.addStudentFathersName = "";
    		$scope.addStudentMothersName = "";
    		$scope.studentRegistrationNo = "";
        	$scope.showStudentTable = true;
        	$("#loader-mask").fadeOut();
    	},500);
    	
    };	
    $scope.addNewStudentOpen = function(){
    	$scope.addNewStudentSectionOpened = true;
    	$scope.selectedGender=undefined;
		$scope.selectedCaste=undefined;
		$scope.studentRegistrationNo = undefined;
    	$scope.addStudentschoolName = $scope.selectedSchool['School Name'];
    	$scope.addStudentUdiseCode = $scope.selectedSchool['uidse_code'];
    };
    $scope.submitData = function()
    {
    	if($scope.addStudentStudentName==undefined || $scope.addStudentStudentName.trim()=='')
    		{
    		$scope.errorMessage = 'Please provide student name';
    		$("#errorModal").modal("show");
    		}
    	else if ($scope.addStudentFathersName==undefined || $scope.addStudentFathersName.trim()=='')
    		{
    		$scope.errorMessage = "Please provide father's name";
    		$("#errorModal").modal("show");
    		}
    	else if($scope.addStudentMothersName==undefined || $scope.addStudentMothersName.trim()=='')
    		{
    		$scope.errorMessage = "Please provide mother's name";
    		$("#errorModal").modal("show");
    		}
    	else if($scope.studentRegistrationNo==undefined || $scope.studentRegistrationNo.trim()=='')
		{
		$scope.errorMessage = "Please provide SR No.";
		$("#errorModal").modal("show");
		}
    	else if($scope.selectedGender==undefined || $scope.selectedGender == "")
    		{
    		$scope.errorMessage = "Please provide student's gender";
    		$("#errorModal").modal("show");
    		}
    	else if($scope.selectedCaste==undefined || $scope.selectedCaste=="")
    		{
    		$scope.errorMessage = "Please provide student's caste";
    		$("#errorModal").modal("show");
    		}
    	else
    		{
    		$scope.studentModel={
	    		 studentName:$scope.addStudentStudentName,
	    		 fathersName:$scope.addStudentFathersName,
	    		 mothersName:$scope.addStudentMothersName,
	    		 gender:$scope.selectedGender,
	     		 schoolId:$scope.selectedSchool.school_id,// to uncomment after integrating with link page
	//    		 schoolId:$scope.selectedSchool['school_id'], // to be deleted after integrating with link page
		         casteId:$scope.selectedCaste,
		         srRegistrationNo: $scope.studentRegistrationNo
    		};
    		$("#loader-mask").show();
    		
    		$http({
    			url : 'saveStudent', 
    			method : 'POST',
    			data : JSON.stringify($scope.studentModel),
    			contentType : 'application/json'
    		}).then(function successCallback(response) {
    			$("#loader-mask").fadeOut();
    			if(response.data.status==1)
    				{
    			$scope.successMessage = response.data.message;
    			$("#successModal").modal("show");
    				}
    			else
    		{		
    				$scope.errorMessage = response.data.message;
    				$("#errorModal").modal("show");
    		}
    			console.log(response.data);
    			$scope.studentModel = undefined;
    			$scope.addStudentStudentName=undefined;
    			$scope.addStudentFathersName=undefined;
    			$scope.addStudentMothersName=undefined;
    			$scope.selectedGender=undefined;
    			$scope.selectedCaste=undefined;
    	    		$scope.addNewStudentSectionOpened = false;
    	        	$scope.showStudentTable = true;
    	        	$scope.getStudentsList($scope.selectedSchool);
    	        	$scope.findSchoolByBlock();
    		}, function errorCallback(response) {
    		  });;
    		
    		}
    	
    }
}