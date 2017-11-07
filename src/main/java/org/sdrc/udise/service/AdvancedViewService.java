package org.sdrc.udise.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.sdrc.udise.model.AdvancedSearchRequestModel;
import org.sdrc.udise.model.RawDataModel;
/**
 * 
 * @author Azaruddin (azaruddin@sdrc.co.in)
 *
 */
public interface AdvancedViewService {

	public Map<String, JSONObject> getInitViewDataForAdvancedSearch();
	
	public List<Map<String, String>> searchData(AdvancedSearchRequestModel advancedSearchRequestModel);
	
	public File searchRawData( RawDataModel rawDataModel);

}
