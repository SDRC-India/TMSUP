function reportController($scope, $http, httpServices){
	$scope.activeMenu = 'report';
	
	$scope.getLevels = function() {
		httpServices.getLevels().then(function(data) {
			$scope.levels = data;
			$scope.allIndicators = $scope.levels.indicator;
			$scope.areas = $scope.levels.areas;
			$scope.allDivisions = $scope.convert($scope.areas).children;
			$scope.searchTypes = $scope.levels.searchType;
			$scope.rules = $scope.levels.rules;
			$scope.allTimePeriods = $scope.levels.academicYears;
			//check for all district successful(used for 1st load and get pushpin)
			$scope.getSectorsSuccessful = true;
			
		});
	};
	$scope.findBlockByDistrict = function(distId){
		httpServices.getBlocksByDistrict(distId).then(function(data) {
			$scope.allBlocks = data;
		});
	};
	$scope.findDistrictByDivision = function(divisionId){
		httpServices.getDistrictsByDivision(divisionId).then(function(data) {
			$scope.allDistricts = data;
		});
	};
	$scope.getLevels();
	
	/*$scope.selectIndicator = function(indicator) {
		$scope.selectedIndicator = indicator;
	};*/
	$scope.selectSearchType = function(searchType) {
		$scope.selectedSearchType = searchType;
		$scope.selectedDivision = undefined;
		$scope.selectedDistrict = undefined;
		$scope.selectedBlock = undefined;
	};
	$scope.selectRule = function(rule){
		$scope.selectedRule = rule;
	};
	
	$scope.selectTimePeriod=function(timeperiod)
	{
		$scope.selectedTimePeriod=timeperiod;
	};
	$scope.selectDivision = function(division){
		$scope.selectedDivision = division;
		$scope.selectedDistrict = undefined;
		$scope.selectedBlock = undefined;
	};
	$scope.selectDistrict = function(district){
		$scope.selectedDistrict = district;
		$scope.selectedBlock = undefined;
	};
	$scope.selectBlock = function(block){
		$scope.selectedBlock = block;
	};
	
	$scope.downloadReport = function(){
		setTimeout(function(){
			$('[data-toggle="tooltip"]').tooltip();

			}, 500)
		/*if(!$scope.selectedIndicator){
			$scope.errorMsg = "Please select an indicator";
			$("#errorModal").modal("show");
		}
		else */if(!$scope.selectedSearchType){
			$scope.errorMsg = "Please select a search type";
			$("#errorModal").modal("show");
		}
		else if(!$scope.selectedTimePeriod)
		{
		$scope.errorMsg = "Please select a timeperiod";
		$("#errorModal").modal("show");
		}
		else if(!$scope.selectedDivision && ($scope.selectedSearchType.id != 1))
		{
		$scope.errorMsg = "Please select a division";
		$("#errorModal").modal("show");
		}
		else if(!$scope.selectedDistrict && ($scope.selectedSearchType.id != 1 && $scope.selectedSearchType.id != 2))
			{
			$scope.errorMsg = "Please select a district";
			$("#errorModal").modal("show");
			}
		else if(!$scope.selectedBlock && ($scope.selectedSearchType.id != 1 && $scope.selectedSearchType.id != 2 && $scope.selectedSearchType.id != 3))
		{
		$scope.errorMsg = "Please select a block";
		$("#errorModal").modal("show");
		}
		
		
		/*else if(!$scope.selectedRule){
			$scope.errorMsg = "Please select rule";
			$("#errorModal").modal("show");
		}*/
		
		/*else if($("#percentValue").val() == ""){
			$scope.errorMsg = "Please select percent value";
			$("#errorModal").modal("show");
		}*/
		else{
			
			$scope.RawDataModel = {
					"searchType": parseInt($scope.selectedSearchType.id),
					"division": $scope.selectedDivision ? parseInt($scope.selectedDivision.areaId):0,
					"district": $scope.selectedDistrict ? parseInt($scope.selectedDistrict.areaId):0,
					"block": $scope.selectedBlock ? parseInt($scope.selectedBlock.areaId):0,
					"academicYear": $scope.selectedTimePeriod.name.split("-")[0]
			};
			var selectedAreaName=$scope.selectedBlock ?$scope.selectedBlock.areaName : $scope.selectedDistrict?$scope.selectedDistrict.areaName : $scope.selectedDivision?$scope.selectedDivision.areaName:"Uttar Pradesh";
			$("#loader-mask-wait").show();
			$scope.successMessage = "Report has been downloaded Successfully";
			httpServices.searchRawData($scope.RawDataModel,selectedAreaName).then(function(data){
				$("#loader-mask-wait").fadeOut();
			});
		}
		
	};

	 $scope.convert = function(array){
		    var map = {};
		    for(var i = 0; i < array.length; i++){
		        var obj = array[i];
		        if(obj.parentAreaId == 2 || obj.parentAreaId == 0)
		        	obj.parentAreaId =  null;
		        if(!(obj.areaId in map)){
		            map[obj.areaId] = obj;
		            map[obj.areaId].children = [];
		        }

		        if(typeof map[obj.areaId].areaName == 'undefined'){
		            map[obj.areaId].areaId = String(obj.areaId);
		            map[obj.areaId].areaName = obj.areaName;
		            map[obj.areaId].parentAreaId= String(obj.parentAreaId);
		        }

		        var parent = obj.parentAreaId || '-';
		        if(!(parent in map)){
		            map[parent] = {};
		            map[parent].children = [];
		        }
		        map[parent].children.push(map[obj.areaId]);
		    }
		    return map['-'];
		};
	
	
	
	
}