package org.sdrc.udise.service;

import java.util.List;

import org.sdrc.udise.model.DashboardDataModel;
import org.sdrc.udise.model.DashboardDropDownMenuModel;
import org.sdrc.udise.model.GoogleMapDataModel;

/**
 * 
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public interface DashboardService {

	/**
	 * Please don't uncomment its implementation 
	 * @return
	 */
	public boolean insertSchools();
	
	
	/**
	 * This method will return the all the divisional wise data if all division selected other wise data of district within a selected division
	 * @param divisionId
	 * @return  DashboardDataModel
	 */
	public DashboardDataModel fetchDivisionWiseData(int divisionId);
	
	
	/**
	 * This method will return the data of all the block within a selected district / district within a logged in user division if districtId == 0
	 * @param districtId
	 * @return DashboardDataModel
	 */
	public DashboardDataModel fetchDistrictWiseData(int districtId);
	
	/**
	 * This method will return the data of the school within the selected block / block within a logged in user district if blockId == 0
	 * @param blockId
	 * @return  DashboardDataModel
	 */
	public DashboardDataModel fetchBlockWiseData(int blockId);
	
	/**
	 * This method will return the student list of selected school
	 * @param schoolId
	 * @return DashboardDataModel
	 */
	public DashboardDataModel fetchSchoolWiseWiseData(int schoolId);
	
	/**
	 * This method will return the school type list and area list according the level of area assigned to the logged in user
	 * @see DashboardDropDownMenuModel
	 * @return DashboardDropDownMenuModel
	 */
	public DashboardDropDownMenuModel fetchDropDownMenuForDashboard();
	
	
	/**
	 * This method will return List<GoogleMapDataModel> for a selected district/block
	 * MapDataModel will hold the schoolName,its lat and long and 
	 * Percent of left out if isAdmitted is False else it will hold percent of admitted
	 *   
	 * @param districtId
	 * @param blockId
	 * @param isAdmitted
	 * @return
	 */
	public List<GoogleMapDataModel> getMapDataOfSchool(int districtId,int blockId,boolean isAdmitted);
	
	
	/**
	 * This method will use to import the data of tables showing in dashboard
	 * @param divisionId
	 * @param districtId
	 * @param blockId
	 * @param schoolId
	 * @return
	 */
	public String exportPdf(int divisionId,
			int districtId,int blockId,int schoolId);
}
