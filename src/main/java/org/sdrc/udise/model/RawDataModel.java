package org.sdrc.udise.model;

/**
 * 
 * @author Azaruddin (azaruddin@sdrc.co.in)
 *
 */

public class RawDataModel {
	// 1-division level data,2-district level data,3-block level data,4-school
	// level data
	private int searchType;
	// areaid of division
	private int division;
	// areaid of district
	private int district;
	// area id of block
	private int block;

	// selected academic year.e.g 2017
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

	

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	
	

}
