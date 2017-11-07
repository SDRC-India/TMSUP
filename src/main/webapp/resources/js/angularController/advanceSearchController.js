function advanceSearchController($scope, $http, httpServices){
	$scope.activeMenu = 'advance';
	$scope.allPatterns = [{value:1, name:"> (greater than)"}, {value: 2, name:"= (equal to)"}, {value: 3, name:">= (greater than/equal to)"}, {value: 4, name: "< (less than)"}, {value:5, name:"<= (less than/equal to)"}]
	$('[data-toggle="tooltip"]').tooltip();
	
	$scope.getLevels = function() {
		$("#loader-mask").show();
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
			$("#loader-mask").fadeOut();
			
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
	
	$scope.selectIndicator = function(indicator) {
		$scope.selectedIndicator = indicator;
	};
	$scope.selectSearchType = function(searchType) {
		$scope.clearSelection(searchType);
		$scope.selectedSearchType = searchType;
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
	
	$scope.clearSelection =function(searchType)
	{
		if($scope.selectedSearchType!=searchType)
			{
		$scope.selectedDivision = undefined;
		$scope.selectedDistrict = undefined;
		$scope.selectedBlock = undefined;
			}
	}
	
	$scope.viewData = function(){
		
		setTimeout(function() {
			$('html,body').animate({
				scrollTop : $('#target-table').offset().top -250
				}, 'slow');
			}, 200)
		setTimeout(function(){
			$('[data-toggle="tooltip"]').tooltip();

			}, 500)
		if(!$scope.selectedIndicator){
			$scope.errorMsg = "Please select an indicator";
			$("#errorModal").modal("show");
		}
		else if(!$scope.selectedSearchType){
			$scope.errorMsg = "Please select a search type";
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
		
		
		else if(!$scope.selectedRule){
			$scope.errorMsg = "Please select rule";
			$("#errorModal").modal("show");
		}
		else if(!$scope.selectedTimePeriod)
		{
		$scope.errorMsg = "Please select a timeperiod";
		$("#errorModal").modal("show");
		}
		else if($("#percentValue").val() == ""){
			$scope.errorMsg = "Please select percent value";
			$("#errorModal").modal("show");
		}
		else{
			$scope.advancedSearchRequestModel = {
					"searchType": parseInt($scope.selectedSearchType.id),
					"division": $scope.selectedDivision ? parseInt($scope.selectedDivision.areaId):0,
					"district": $scope.selectedDistrict ? parseInt($scope.selectedDistrict.areaId):0,
					"block": $scope.selectedBlock ? parseInt($scope.selectedBlock.areaId):0,
					"ruleType": parseInt($scope.selectedRule.ruleId),
					"indicator": parseInt($scope.selectedIndicator.id),
					"searchCondition": $("#percentValue").val(),
					"academicYear": $scope.selectedTimePeriod.name.split("-")[0]
			};
			
			$("#loader-mask").show();
			httpServices.getAdvanceSearchData($scope.advancedSearchRequestModel).then(function(data){
				$scope.tableData = data;
				if($scope.tableData.length){
					$scope.columns = Object.keys($scope.tableData[0]);
					$scope.sortSelectedColumn = "District Name";
					$scope.sortType = '';
					$scope.order($scope.sortSelectedColumn);
					
				}
				else{
					$scope.errorMsg = "No data available for this selection";
					$("#errorModal").modal("show");
					
				}
				$("#loader-mask").fadeOut();
			});
		}
		
	};
	$scope.order = function (sortType) {  
        $scope.sortReverse = ($scope.sortType === sortType) ? !$scope.sortReverse : false;  
        $scope.sortType = sortType;  
      };
      
    $scope.filterType = function(val){
      	if(val['District'] == "Chhattisgarh"){
      		if(isNaN(parseInt(val[$scope.sortType]))){
  	    		if($scope.sortReverse == true)
  	    			return "";
  	    		else
  	    			return "zzz";
      		}	
      		else{
      			if($scope.sortReverse == true)
  	    			return -1;
  	    		else
  	    			return 9e12;
      		}
      	}
      	if(isNaN(parseInt(val[$scope.sortType])))
      	return val[$scope.sortType];
      	else
      		return parseInt(val[$scope.sortType]);
    }; 
    
    $scope.exportTableData = function(id){
    	var htmls = "";
        var uri = 'data:application/vnd.ms-excel;base64,';
        var template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'; 
        var base64 = function(s) {
            return window.btoa(unescape(encodeURIComponent(s)));
        };

        var format = function(s, c) {
            return s.replace(/{(\w+)}/g, function(m, p) {
                return c[p];
            });
        };

        var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
        var textRange; var j=0;
        tab = document.getElementById(id); // id of table

        for(j = 0 ; j < tab.rows.length ; j++) 
        {     
            tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
            //tab_text=tab_text+"</tr>";
        }

        tab_text=tab_text+"</table>";
        tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
        tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
        tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params


        var ctx = {
            worksheet : 'Worksheet',
            table : tab_text
        };


        var link = document.createElement("a");
        link.download = "advanceSearch.xls";
        link.href = uri + base64(format(template, ctx));
        
        if(typeof InstallTrigger === 'undefined'){
        	link.click();
        }
        else{
        	window.location.href = link.href;
        }
    };
    
    
    $scope.selectSortColumn = function(column){
    	if($scope.sortSelectedColumn != column){
    		$(".sortSelected").removeClass("sortSelected");
        	$scope.sortSelectedColumn = column;
    	}
    };
    $scope.filterFloat = function(value) {
        if (/^(\-|\+)?([0-9]+(\.[0-9]+)?|Infinity)$/
        	      .test(value))
        	      return Number(value);
        	  return value;
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