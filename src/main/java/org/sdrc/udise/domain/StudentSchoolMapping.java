package org.sdrc.udise.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
@Entity
@Table(name = "student_school_mapping")
public class StudentSchoolMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "child_school_mapping_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer childSchooleMappingID;

	@Column(name = "is_latest")
	private boolean isLatest;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id_fk", nullable = false, unique = true)
	private StudentsDetails studentDetails;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_school_id_fk", nullable = false)
	private SchoolDetails fromSchoolDetails;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "linked_school_id_fk", nullable = true)
	private SchoolDetails linkedSchoolDetails;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "academic_year_id_fk")
	private AcademicYear academicYear;

	// Added by azar

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "other_state_id_fk", nullable = true)
	private MasterArea otherState;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "other_district_id_fk", nullable = true)
	private MasterArea otherDistrict;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "enroll_type_id_fk")
	private TypeDetail enrollType;

	
	@Column(name="other_state_school_name",nullable=true)
	private String otherStateSchoolName;
	
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name="created_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;
	


	@Column(name="updated_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDateTime;
	
	// End of added data by azar

	// getter setters
	public AcademicYear getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}

	public Integer getChildSchooleMappingID() {
		return childSchooleMappingID;
	}

	public void setChildSchooleMappingID(Integer childSchooleMappingID) {
		this.childSchooleMappingID = childSchooleMappingID;
	}

	public boolean isLatest() {
		return isLatest;
	}

	public void setLatest(boolean isLatest) {
		this.isLatest = isLatest;
	}

	public StudentsDetails getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(StudentsDetails studentDetails) {
		this.studentDetails = studentDetails;
	}

	public SchoolDetails getFromSchoolDetails() {
		return fromSchoolDetails;
	}

	public void setFromSchoolDetails(SchoolDetails fromSchoolDetails) {
		this.fromSchoolDetails = fromSchoolDetails;
	}

	public SchoolDetails getLinkedSchoolDetails() {
		return linkedSchoolDetails;
	}

	public void setLinkedSchoolDetails(SchoolDetails linkedSchoolDetails) {
		this.linkedSchoolDetails = linkedSchoolDetails;
	}

	public MasterArea getOtherState() {
		return otherState;
	}

	public void setOtherState(MasterArea otherState) {
		this.otherState = otherState;
	}

	public MasterArea getOtherDistrict() {
		return otherDistrict;
	}

	public void setOtherDistrict(MasterArea otherDistrict) {
		this.otherDistrict = otherDistrict;
	}

	public TypeDetail getEnrollType() {
		return enrollType;
	}

	public void setEnrollType(TypeDetail enrollType) {
		this.enrollType = enrollType;
	}

	public String getOtherStateSchoolName() {
		return otherStateSchoolName;
	}

	public void setOtherStateSchoolName(String otherStateSchoolName) {
		this.otherStateSchoolName = otherStateSchoolName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	
	
	
}
