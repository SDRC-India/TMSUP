package org.sdrc.udise.model;

public class SchoolDetailModel {

	private int id;
	private String schoolName;
	private int areaId;
	private int admitted;
	private int total;
	private int leftOut;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getAdmitted() {
		return admitted;
	}

	public void setAdmitted(int admitted) {
		this.admitted = admitted;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getLeftOut() {
		return leftOut;
	}

	public void setLeftOut(int leftOut) {
		this.leftOut = leftOut;
	}

}
