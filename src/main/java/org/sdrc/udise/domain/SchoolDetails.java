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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
@Entity
@Table(name = "School_Details")
public class SchoolDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schoolId")
	private Integer schoolId;

	@Column(name = "udise_code", nullable = true, unique = true)
	private String udiseCode;

	@Column(name = "school_name", nullable = false)
	private String schoolName;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "is_Live")
	private boolean isLive;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_type_fk", nullable = false)
	private TypeDetail schoolType;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "affilation_type_fk",nullable=true) private TypeDetail
	 * affilationType;;
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "village_id_fk")
	private Area village;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "block_id_fk", nullable = false)
	private Area block;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "district_id_fk", nullable = false)
	private Area districtId;

	@Column(name = "lattitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitutde;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="school_management_type_fk")
	private TypeDetail schoolManagementType;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="school_area_type")
	private TypeDetail schoolAreaType;  
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="school_type_Other_fk")
	private TypeDetail schoolTypeOther;
	
	@Column(name="estd_yr")
	private String estdYear;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="affilation_type_board_secondary")
	private TypeDetail affilationTypeBoardSecondary;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="affilation_type_board_higher_secondary")
	private TypeDetail affilationTypeBoardHigherSecondary;
	
	

	// ******** bi-directional one-to-many association to SchoolLatLongLink
	// *******

	@OneToOne(mappedBy = "school")
	private SchoolLatLongLink schoolLatLongLinks;

	// ******** bi-directional one-to-many association to StudentSchoolMapping
	// *******
	@OneToMany(mappedBy = "fromSchoolDetails", fetch = FetchType.LAZY)
	private List<StudentSchoolMapping> tostudentSchoolMapping;

	// ******** bi-directional one-to-many association to StudentSchoolMapping
	// *******
	@OneToMany(mappedBy = "linkedSchoolDetails", fetch = FetchType.LAZY)
	private List<StudentSchoolMapping> fromstudentSchoolMapping;

	// ******** bi-directional one-to-many association to StudentsDetails
	// *******
	@OneToMany(mappedBy="school",fetch=FetchType.LAZY)
	private List<StudentsDetails> studentDetails;
	
	
	@OneToMany(mappedBy="school",fetch=FetchType.LAZY)
	private List<SchoolAggregatedData> schoolAggregatedDatas;
	
	
	@OneToMany(mappedBy="school",fetch=FetchType.LAZY)
	private List<SchoolCurrentAggregatedData> schoolCurrentAggregatedData;
	// getter setters

	public List<SchoolCurrentAggregatedData> getSchoolCurrentAggregatedData() {
		return schoolCurrentAggregatedData;
	}

	public void setSchoolCurrentAggregatedData(
			List<SchoolCurrentAggregatedData> schoolCurrentAggregatedData) {
		this.schoolCurrentAggregatedData = schoolCurrentAggregatedData;
	}

	public List<SchoolAggregatedData> getSchoolAggregatedDatas() {
		return schoolAggregatedDatas;
	}

	public void setSchoolAggregatedDatas(
			List<SchoolAggregatedData> schoolAggregatedDatas) {
		this.schoolAggregatedDatas = schoolAggregatedDatas;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitutde() {
		return longitutde;
	}

	public void setLongitutde(String longitutde) {
		this.longitutde = longitutde;
	}

	public SchoolLatLongLink getSchoolLatLongLinks() {
		return schoolLatLongLinks;
	}

	public void setSchoolLatLongLinks(SchoolLatLongLink schoolLatLongLinks) {
		this.schoolLatLongLinks = schoolLatLongLinks;
	}

	public List<StudentSchoolMapping> getTostudentSchoolMapping() {
		return tostudentSchoolMapping;
	}

	public void setTostudentSchoolMapping(List<StudentSchoolMapping> tostudentSchoolMapping) {
		this.tostudentSchoolMapping = tostudentSchoolMapping;
	}

	public List<StudentSchoolMapping> getFromstudentSchoolMapping() {
		return fromstudentSchoolMapping;
	}

	public void setFromstudentSchoolMapping(List<StudentSchoolMapping> fromstudentSchoolMapping) {
		this.fromstudentSchoolMapping = fromstudentSchoolMapping;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getUdiseCode() {
		return udiseCode;
	}

	public void setUdiseCode(String udiseCode) {
		this.udiseCode = udiseCode;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public TypeDetail getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(TypeDetail schoolType) {
		this.schoolType = schoolType;
	}

	public Area getVillage() {
		return village;
	}

	public void setVillage(Area village) {
		this.village = village;
	}

	public Area getBlock() {
		return block;
	}

	public void setBlock(Area block) {
		this.block = block;
	}

	public Area getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Area districtId) {
		this.districtId = districtId;
	}

	public List<StudentsDetails> getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(List<StudentsDetails> studentDetails) {
		this.studentDetails = studentDetails;
	}

	public TypeDetail getSchoolManagementType() {
		return schoolManagementType;
	}

	public void setSchoolManagementType(TypeDetail schoolManagementType) {
		this.schoolManagementType = schoolManagementType;
	}

	public TypeDetail getSchoolAreaType() {
		return schoolAreaType;
	}

	public void setSchoolAreaType(TypeDetail schoolAreaType) {
		this.schoolAreaType = schoolAreaType;
	}

	public TypeDetail getSchoolTypeOther() {
		return schoolTypeOther;
	}

	public void setSchoolTypeOther(TypeDetail schoolTypeOther) {
		this.schoolTypeOther = schoolTypeOther;
	}

	public String getEstdYear() {
		return estdYear;
	}

	public void setEstdYear(String estdYear) {
		this.estdYear = estdYear;
	}

	public TypeDetail getAffilationTypeBoardSecondary() {
		return affilationTypeBoardSecondary;
	}

	public void setAffilationTypeBoardSecondary(
			TypeDetail affilationTypeBoardSecondary) {
		this.affilationTypeBoardSecondary = affilationTypeBoardSecondary;
	}

	public TypeDetail getAffilationTypeBoardHigherSecondary() {
		return affilationTypeBoardHigherSecondary;
	}

	public void setAffilationTypeBoardHigherSecondary(
			TypeDetail affilationTypeBoardHigherSecondary) {
		this.affilationTypeBoardHigherSecondary = affilationTypeBoardHigherSecondary;
	}

}
