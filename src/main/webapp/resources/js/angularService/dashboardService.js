/* @Laxman Paikaray(laxman@sdrc.co.in)
 * 
 * */
function checkSessionOut(data){
	if(typeof data == 'string' && data.indexOf("You are not authorized to view this page") != -1){
		$("body").append('<div id="sessionOutMessage" class="modal fade" role="dialog"><div class="modal-dialog"><div class="modal-content"><div class="modal-body text-center"><h3>Session is expired</h3><a href="home" class="btn btn-default errorOk" type="submit">OK</a></div></div></div></div>');
		$("#sessionOutMessage").modal("show");
	}
}

 
  function dashboardServices($http, $q) {
	
	  	this.getPiechartData = function(){
			var deferred = $q.defer();
	        // get posts from database
			$http.get("resources/json/pie-chart.json")
	          .then(function(result) {
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
		this.getDropDownJSON = function(){
			var deferred = $q.defer();
	        // get posts from database
			$http.get("getDropDownJSON")
	          .then(function(result) {
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
		this.getAggregateData = function(dvisionId, districtId, blockId, schoolId){
			var deferred = $q.defer();
	        // get posts from database
			$http.post("getAggregateData?dvisionId=" + dvisionId +
					"&districtId=" + districtId +
					"&blockId=" + blockId +
					"&schoolId=" + schoolId)
	          .then(function(result) {
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
		this.getGoogleMapData = function(districtId, blockId, isAdmitted){
			var deferred = $q.defer();
	        // get posts from database
			$http.get("getGoogleMapView?districtId=" + districtId
					+ "&blockId=" + blockId
					+ "&isAdmitted=" + isAdmitted)
	          .then(function(result) {
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