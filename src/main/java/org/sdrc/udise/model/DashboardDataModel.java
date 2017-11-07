/**
 * 
 */
package org.sdrc.udise.model;

import java.util.List;
import java.util.Map;

/**
 * 
 * This model will contain the complete data of the dashboard for UP-RMSA
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 * @version 1.0.0
 *
 */
public class DashboardDataModel {

	
	// this will contain the data for the data table of the dashboard
	private  List<Map<String,String>> dataTableData;
	
	// this  will contain the data for the totalData of the table
	private  Map<String,String> totalData;
	
	
	// this will contain the data of pie chart of the dashboard
	private List<List<PieChart>> pieChartData;

	// contains the selection's name of dashboard
	private String selection;
	
	// contains the lastUpdatedDate
	private String lastUpdatedDate;
	
	private String stateName;
	
	
	
	private String districtName;
	
	private String divisionName;

	private String blockName;
	
	private String schoolName;
	
	
	// this will contain the data of pie chart of  gender the dashboard
	private List<List<PieChart>> genderPieChartData;
	
	
	// this will contain the data of pie chart of caste the dashboard
	private List<StackedLineChartModel> castePieChartData;
	
	
	public String getBlockName() {
		return blockName;
	}


	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}


	public String getSchoolName() {
		return schoolName;
	}


	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}


	public String getStateName() {
		return stateName;
	}


	public void setStateName(String stateName) {
		this.stateName = stateName;
	}




	public String getDistrictName() {
		return districtName;
	}


	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}


	public String getDivisionName() {
		return divisionName;
	}


	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}


	public String getSelection() {
		return selection;
	}


	public void setSelection(String selection) {
		this.selection = selection;
	}


	public List<Map<String, String>> getDataTableData() {
		return dataTableData;
	}


	public void setDataTableData(List<Map<String, String>> dataTableData) {
		this.dataTableData = dataTableData;
	}


	public Map<String, String> getTotalData() {
		return totalData;
	}


	public void setTotalData(Map<String, String> totalData) {
		this.totalData = totalData;
	}


	public List<List<PieChart>> getPieChartData() {
		return pieChartData;
	}


	public void setPieChartData(List<List<PieChart>> pieChartData) {
		this.pieChartData = pieChartData;
	}


	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}


	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}


	public List<List<PieChart>> getGenderPieChartData() {
		return genderPieChartData;
	}


	public void setGenderPieChartData(List<List<PieChart>> genderPieChartData) {
		this.genderPieChartData = genderPieChartData;
	}


	public List<StackedLineChartModel> getCastePieChartData() {
		return castePieChartData;
	}


	public void setCastePieChartData(List<StackedLineChartModel> castePieChartData) {
		this.castePieChartData = castePieChartData;
	}
	
	
}
