package org.sdrc.udise.model;

/**
 * 
 * @author Azaruddin (azaruddin@sdrc.co.in)
 *
 */

public class AdvancedSearchRequestModel {

	//1-division level data,2-district level data,3-block level data,4-school level data
		private int searchType;
		//areaid of division
		private int division;
		//areaid of district
		private int district;
		//area id of block
		private int block;
		//1-greater than,2-greater than equal to,3-equal to,4-less than,5-less than equal to
		private int ruleType;
		//1-leftout,2-admitted/enrolled
		private int indicator;
		//value of percentage
		private String searchCondition;
		//selected academic year.e.g 2017
		private String academicYear;

	public int getSearchType() {
		return searchType;
	}

	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

	public int getDivision() {
		return division;
	}

	public void setDivision(int division) {
		this.division = division;
	}

	public int getDistrict() {
		return district;
	}

	public void setDistrict(int district) {
		this.district = district;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public int getRuleType() {
		return ruleType;
	}

	public void setRuleType(int ruleType) {
		this.ruleType = ruleType;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public int getIndicator() {
		return indicator;
	}

	public void setIndicator(int indicator) {
		this.indicator = indicator;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	
	
	

}
