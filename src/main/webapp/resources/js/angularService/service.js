/* @Laxman Paikaray(laxman@sdrc.co.in)
 * 
 * */
function checkSessionOut(data) {
	if (typeof data == 'string'
			&& data.indexOf("You are not authorized to view this page") != -1) {
		$("body")
				.append(
						'<div id="sessionOutMessage" class="modal fade" role="dialog"><div class="modal-dialog"><div class="modal-content"><div class="modal-body text-center"><h3>Session has been expired</h3><a href="login" class="btn btn-default errorOk" type="submit">OK</a></div></div></div></div>');
		$("#sessionOutMessage").modal("show");
	}
}

function httpServices($http, $q) {
	/* report page */
	this.getSectors = function() {
		var deferred = $q.defer();
		// get posts from database
		$http.get("getSectors").then(function(result) {
			checkSessionOut(result.data);
			jsonData = result.data;
			deferred.resolve(jsonData);
		}, function(error) {
			jsonData = error;
			deferred.reject(error);
		});
		jsonData = deferred.promise;
		return $q.when(jsonData);
	};
	this.searchRawData = function(RawDataModel,Areaname) {
		var deferred = $q.defer();
		// get posts from database
		$http(
				{
					url : 'searchRawData',
					method : 'POST',
					data : RawDataModel,
					
					contentType : 'application/json',
					responseType:'arraybuffer'
				}).then(function(result) {
					jsonData = result.data;
				
					
						
						
						var fName = Areaname+".xlsx";
						var file =new Blob([jsonData], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
						var fileURL = (window.URL || window.webkitURL).createObjectURL(file);

			          
			//Blob, client side object created to with holding browser specific download popup, on the URL created with the help of window obj.
						var downloadLink = document.createElement("a");

						document.body.appendChild(downloadLink);
						downloadLink.style = "display: none";

						downloadLink.href = fileURL;
						downloadLink.download = fName;
						downloadLink.click();
						
					//	$("#successModal").modal("show");
						deferred.resolve(jsonData);
					
					
			
		}, function(error) {
			jsonData = error;
			deferred.reject(error);
		});
		// $http.post("searchRawData", RawDataModel)
		// .then(function(result) {
		// // checkSessionOut(result.data);
		// jsonData = result.data;
		// deferred.resolve(jsonData);
		// }, function(error) {
		// jsonData = error;
		// deferred.reject(error);
		// });
		jsonData = deferred.promise;
		return $q.when(jsonData);
	};
	/* end*********** */

	// advance search
	this.getLevels = function() {
		var deferred = $q.defer();
		// get posts from database
		$http.get("intializeAdvancedView").then(function(result) {
			checkSessionOut(result.data);
			jsonData = result.data;
			deferred.resolve(jsonData);
		}, function(error) {
			jsonData = error;
			deferred.reject(error);
		});
		jsonData = deferred.promise;
		return $q.when(jsonData);
	};
	this.getAdvanceSearchData = function(advancedSearchRequestModel) {
		var deferred = $q.defer();
		// get posts from database
		$http.post("searchData", advancedSearchRequestModel).then(
				function(result) {
					checkSessionOut(result.data);
					jsonData = result.data;
					deferred.resolve(jsonData);
				}, function(error) {
					jsonData = error;
					deferred.reject(error);
				});
		jsonData = deferred.promise;
		return $q.when(jsonData);
	};

	/* end */
	this.findSchoolByBlock = function() {
		var deferred = $q.defer();
		// get posts from database
		$http.get("findSchoolByBlock").then(function(result) {
			checkSessionOut(result.data);
			jsonData = result.data;
			deferred.resolve(jsonData);
		}, function(error) {
			jsonData = error;
			deferred.reject(error);
		});
		jsonData = deferred.promise;
		return $q.when(jsonData);
	};

	this.getSchoolTypes = function() {
		var deferred = $q.defer();
		// get posts from database
		$http.get("getSchoolTypes").then(function(result) {
			checkSessionOut(result.data);
			jsonData = result.data;
			deferred.resolve(jsonData);
		}, function(error) {
			jsonData = error;
			deferred.reject(error);
		});
		jsonData = deferred.promise;
		return $q.when(jsonData);
	};
	this.findStudentsOfSchool = function(schoolId) {
		var deferred = $q.defer();
		// get posts from database
		$http.get("findStudentsOfSchool?id=" + schoolId).then(function(result) {
			checkSessionOut(result.data);
			jsonData = result.data;
			deferred.resolve(jsonData);
		}, function(error) {
			jsonData = error;
			deferred.reject(error);
		});
		jsonData = deferred.promise;
		return $q.when(jsonData);
	};
	this.getDataForModelView = function(schoolId) {
		var deferred = $q.defer();
		// get posts from database
		$http.get("getDataForModelView?id=" + schoolId).then(function(result) {
			checkSessionOut(result.data);
			jsonData = result.data;
			deferred.resolve(jsonData);
		}, function(error) {
			jsonData = error;
			deferred.reject(error);
		});
		jsonData = deferred.promise;
		return $q.when(jsonData);
	};
	this.getBlocksByDistrict = function(distId) {
		var deferred = $q.defer();
		// get posts from database
		$http.get("getBlocksByDistrict?district=" + distId).then(
				function(result) {
					// checkSessionOut(result.data); //commented since it is
					// used in advance search section too
					jsonData = result.data;
					deferred.resolve(jsonData);
				}, function(error) {
					jsonData = error;
					deferred.reject(error);
				});
		jsonData = deferred.promise;
		return $q.when(jsonData);
	};
	this.getDistrictsByDivision = function(divisionId) {
		var deferred = $q.defer();
		// get posts from database
		$http.get("getDistrictsByDivision?division=" + divisionId).then(
				function(result) {
					// checkSessionOut(result.data); //commented since it is
					// used in advance search section too
					jsonData = result.data;
					deferred.resolve(jsonData);
				}, function(error) {
					jsonData = error;
					deferred.reject(error);
				});
		jsonData = deferred.promise;
		return $q.when(jsonData);
	};
	this.getSchoolsByBlock = function(blockId) {
		var deferred = $q.defer();
		// get posts from database
		$http.get("getSchoolsByBlock?block=" + blockId).then(function(result) {
			checkSessionOut(result.data);
			jsonData = result.data;
			deferred.resolve(jsonData);
		}, function(error) {
			jsonData = error;
			deferred.reject(error);
		});
		jsonData = deferred.promise;
		return $q.when(jsonData);
	};
	this.saveSchool = function(schoolName, distId, blockId, schoolType,
			udiseCode, schoolId, studentsIdList) {
		var deferred = $q.defer();
		// get posts from database
		$http.get(
				"saveSchool?schoolName=" + schoolName + "&district=" + distId
						+ "&block=" + blockId + "&udiseCode=" + udiseCode
						+ "&schoolType=" + schoolType + "&fromSchool="
						+ schoolId + "&student_id[]=" + studentsIdList).then(
				function(result) {
					checkSessionOut(result.data);
					jsonData = result.data;
					deferred.resolve(jsonData);
				}, function(error) {
					jsonData = error;
					deferred.reject(error);
				});
		jsonData = deferred.promise;
		return $q.when(jsonData);
	};
	/*
	 * this.admitStudent = function(fromSchool, toSchool, isLinked, student_id){
	 * var deferred = $q.defer(); // get posts from database
	 * $http.get("admitStudent?from_school="+fromSchool + "&to_school="+
	 * toSchool + "&linking="+isLinked+ "&student_id[]="+ student_id )
	 * .then(function(result) { checkSessionOut(result.data); jsonData =
	 * result.data; deferred.resolve(jsonData); }, function(error) { jsonData =
	 * error; deferred.reject(error); }); jsonData = deferred.promise; return
	 * $q.when(jsonData); };
	 */
	/*
	 * this.getPiechartData = function(){ var deferred = $q.defer(); // get
	 * posts from database $http.get("resources/json/pie-chart.json")
	 * .then(function(result) { checkSessionOut(result.data); jsonData =
	 * result.data; deferred.resolve(jsonData); }, function(error) { jsonData =
	 * error; deferred.reject(error); }); jsonData = deferred.promise; return
	 * $q.when(jsonData); };
	 */

	this.getStudentCaste = function() {
		var deferred = $q.defer();
		// get posts from database
		$http.get("getStudentCaste").then(function(result) {
			checkSessionOut(result.data);
			jsonData = result.data;
			deferred.resolve(jsonData);
		}, function(error) {
			jsonData = error;
			deferred.reject(error);
		});
		jsonData = deferred.promise;
		return $q.when(jsonData);
	};
	this.getOtherStates = function() {
		var deferred = $q.defer();
		// get posts from database
		$http.get("getOtherStates").then(function(result) {
			checkSessionOut(result.data);
			jsonData = result.data;
			deferred.resolve(jsonData);
		}, function(error) {
			jsonData = error;
			deferred.reject(error);
		});
		jsonData = deferred.promise;
		return $q.when(jsonData);
	};
	this.getAllOtherDistricts = function(stateId) {
		var deferred = $q.defer();
		// get posts from database
		$http.get("getAllOtherDistricts?stateId=" + stateId).then(
				function(result) {
					checkSessionOut(result.data);
					jsonData = result.data;
					deferred.resolve(jsonData);
				}, function(error) {
					jsonData = error;
					deferred.reject(error);
				});
		jsonData = deferred.promise;
		return $q.when(jsonData);
	};
	this.getAggregateData = function(dvisionId, distId, blockId, schoolId) {
		var deferred = $q.defer();
		// get posts from database
		$http.post(
				"getAggregateData?dvisionId=" + dvisionId + "&districtId="
						+ districtId + "&blockId=" + blockId + "&schoolId="
						+ schoolId).then(function(result) {
			checkSessionOut(result.data);
			jsonData = result.data;
			deferred.resolve(jsonData);
		}, function(error) {
			jsonData = error;
			deferred.reject(error);
		});
		jsonData = deferred.promise;
		return $q.when(jsonData);
	};
}
function minmax(value, min, max) {
	if (parseInt(value) < min || isNaN(value))
		return 0;
	else if (parseInt(value) > max)
		return 100;
	else
		return value;
}