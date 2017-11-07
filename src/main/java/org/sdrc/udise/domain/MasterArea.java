package org.sdrc.udise.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "master_area")
public class MasterArea implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "master_area_id")
	private Integer areaId;

	@Column(name = "area_name")
	private String areaName;

	@Column(name = "parent_area_id")
	private int parentAreaId;

	@Column
	private int level;

	@Column(name = "created_date")
	private Timestamp createdDate;


 //***Bi-Directional OneToMany mapping with StudentSchoolMapping for state column    **//
	@OneToMany(mappedBy = "otherState",fetch=FetchType.LAZY)
	private List<StudentSchoolMapping> studentSchoolMappingFromState;
	//***Bi-Directional OneToMany mapping with StudentSchoolMapping for district column    **//
	@OneToMany(mappedBy = "otherDistrict",fetch=FetchType.LAZY)
	private List<StudentSchoolMapping> studentSchoolMappingFromDistrict;


	// getter setters
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getParentAreaId() {
		return parentAreaId;
	}

	public void setParentAreaId(int parentAreaId) {
		this.parentAreaId = parentAreaId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	


	public List<StudentSchoolMapping> getStudentSchoolMappingFromState() {
		return studentSchoolMappingFromState;
	}

	public void setStudentSchoolMappingFromState(
			List<StudentSchoolMapping> studentSchoolMappingFromState) {
		this.studentSchoolMappingFromState = studentSchoolMappingFromState;
	}

	public List<StudentSchoolMapping> getStudentSchoolMappingFromDistrict() {
		return studentSchoolMappingFromDistrict;
	}

	public void setStudentSchoolMappingFromDistrict(
			List<StudentSchoolMapping> studentSchoolMappingFromDistrict) {
		this.studentSchoolMappingFromDistrict = studentSchoolMappingFromDistrict;
	}

	
	
	
}