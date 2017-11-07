package org.sdrc.udise.model;

import java.util.List;

/**
 * 
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public class DashboardDropDownMenuModel {

	// holds the divisions list
	private List<AreaWebModel> division;
	
	// holds the districts list
	private List<AreaWebModel> district;
	
	// holds the block list
	private List<AreaWebModel> block;
	
	
	// holds the school type
	private List<ValueObject> schoolType;

	public List<AreaWebModel> getDivision() {
		return division;
	}

	public void setDivision(List<AreaWebModel> division) {
		this.division = division;
	}

	public List<AreaWebModel> getDistrict() {
		return district;
	}

	public void setDistrict(List<AreaWebModel> district) {
		this.district = district;
	}

	public List<AreaWebModel> getBlock() {
		return block;
	}

	public void setBlock(List<AreaWebModel> block) {
		this.block = block;
	}

	public List<ValueObject> getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(List<ValueObject> schoolType) {
		this.schoolType = schoolType;
	}
	
	
}
