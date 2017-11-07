/**
 * 
 */
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

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@Entity
@Table(name = "area")
public class Area implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "areaId")
	private Integer areaId;

	@Column(name = "area_name")
	private String areaName;

	@Column(name = "parent_area_id")
	private int parentAreaId;

	private int level;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@Column(name = "isLive")
	private boolean isLive;
	
	
	@Column(name="areaCode")
	private String areaCode;

	// ******** bi-directional one-to-many association to UserAreaMapping
	// *******
	@OneToMany(mappedBy = "area",fetch=FetchType.LAZY)
	private List<UserAreaMapping> userAreaMappings;

	// ******** bi-directional one-to-many association to AggregationData
	// *******
	@OneToMany(mappedBy = "areaId",fetch=FetchType.LAZY)
	private List<AggregationData> aggregationDatas;

	// ******** bi-directional one-to-many association to SchoolDetails *******
	@OneToMany(mappedBy = "village",fetch=FetchType.LAZY)
	private List<SchoolDetails> schoolDetailsVillage;

	// ******** bi-directional one-to-many association to SchoolDetails *******
	@OneToMany(mappedBy = "block",fetch=FetchType.LAZY)
	private List<SchoolDetails> schoolDetailsBlock;

	// ******** bi-directional one-to-many association to SchoolDetails *******
	@OneToMany(mappedBy = "districtId",fetch=FetchType.LAZY)
	private List<SchoolDetails> schoolDetailsDistrict;

	public List<AggregationData> getAggregationDatas() {
		return aggregationDatas;
	}

	public void setAggregationDatas(List<AggregationData> aggregationDatas) {
		this.aggregationDatas = aggregationDatas;
	}

	public List<SchoolDetails> getSchoolDetailsVillage() {
		return schoolDetailsVillage;
	}

	public void setSchoolDetailsVillage(List<SchoolDetails> schoolDetailsVillage) {
		this.schoolDetailsVillage = schoolDetailsVillage;
	}

	public List<SchoolDetails> getSchoolDetailsBlock() {
		return schoolDetailsBlock;
	}

	public void setSchoolDetailsBlock(List<SchoolDetails> schoolDetailsBlock) {
		this.schoolDetailsBlock = schoolDetailsBlock;
	}

	public List<SchoolDetails> getSchoolDetailsDistrict() {
		return schoolDetailsDistrict;
	}

	public void setSchoolDetailsDistrict(
			List<SchoolDetails> schoolDetailsDistrict) {
		this.schoolDetailsDistrict = schoolDetailsDistrict;
	}

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

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<UserAreaMapping> getUserAreaMappings() {
		return userAreaMappings;
	}

	public void setUserAreaMappings(List<UserAreaMapping> userAreaMappings) {
		this.userAreaMappings = userAreaMappings;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
}
