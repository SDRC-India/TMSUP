/**
 * 
 */
package org.sdrc.udise.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 * 
 */

@Entity
@Table(name = "student_details", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"school_id_fk", "student_name", "fathers_name", "mothers_name","sr_regd_no"}) })


@NamedEntityGraph(name = "graph.StudentsDetails.deoViewStudentsBySchool", 
attributeNodes = {@NamedAttributeNode(value = "school",subgraph="schoolGraph"),@NamedAttributeNode(value = "caste"),@NamedAttributeNode(value = "studentSchoolMapping",subgraph="studentSchoolMappingGraph")},
subgraphs ={@NamedSubgraph(name="schoolGraph",attributeNodes=@NamedAttributeNode(value="schoolLatLongLinks")),
		@NamedSubgraph(name="studentSchoolMappingGraph",attributeNodes=@NamedAttributeNode(value="linkedSchoolDetails"))})


public class StudentsDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentId;

	@Column(name = "student_name", nullable = false)
	private String studentName;

	@Column(name = "fathers_name")
	private String fathersName;

	@Column(name = "mothers_name")
	private String mothersName;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "geneder")
	private String gender;

	@Column(name = "adharCardNumber")
	private String addharCardNumber;

	@Column(name = "sr_regd_no", length = 20)
	private String srRegistrationNumber;

	@Column(name = "isSubmitted")
	private boolean isSubmitted;
	
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "enrolled_by")
	private String enrolledBy;
	
	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name="created_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Column(name="updated_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caste_id_fk", nullable = false)
	private TypeDetail caste;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id_fk", nullable = false)
	private SchoolDetails school;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acedemic_year_id_fk", nullable = false)
	private AcademicYear acedemicYear;



	@OneToMany(mappedBy="studentDetails")
	private List<StudentSchoolMapping> studentSchoolMapping;
	
	
	
//	// Added by azar
//
//	@OneToOne
//	@JoinTable(name = "student_linked_school", joinColumns = { @JoinColumn(name = "student_id", referencedColumnName = "student_id") }, inverseJoinColumns = { @JoinColumn(name = "school_id", referencedColumnName = "schoolId") })
//	private SchoolDetails linkedSchool;
//
//	@OneToOne
//	@JoinTable(name = "student_other_state", joinColumns = { @JoinColumn(name = "student_id", referencedColumnName = "student_id") }, inverseJoinColumns = { @JoinColumn(name = "area_id", referencedColumnName = "areaId") })
//	private Area otherState;
//
//	@OneToOne
//	@JoinTable(name = "student_other_district", joinColumns = { @JoinColumn(name = "student_id", referencedColumnName = "student_id") }, inverseJoinColumns = { @JoinColumn(name = "areaId", referencedColumnName = "areaId") })
//	private Area otherDistrict;
//
//	@OneToOne
//	@JoinTable(name = "student_enroll_type", joinColumns = { @JoinColumn(name = "student_id", referencedColumnName = "student_id") }, inverseJoinColumns = { @JoinColumn(name = "typeDetailId", referencedColumnName = "typeDetailId") })
//	private TypeDetail enrollType;
//
//	// End of added data by azar
	

	// ******** bi-directional one-to-many association to StudentSchoolMapping
	// *******

	// getter setters

	public TypeDetail getCaste() {
		return caste;
	}

	public void setCaste(TypeDetail caste) {
		this.caste = caste;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getMothersName() {
		return mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddharCardNumber() {
		return addharCardNumber;
	}

	public void setAddharCardNumber(String addharCardNumber) {
		this.addharCardNumber = addharCardNumber;
	}

	public String getSrRegistrationNumber() {
		return srRegistrationNumber;
	}

	public void setSrRegistrationNumber(String srRegistrationNumber) {
		this.srRegistrationNumber = srRegistrationNumber;
	}

	public boolean isSubmitted() {
		return isSubmitted;
	}

	public void setSubmitted(boolean isSubmitted) {
		this.isSubmitted = isSubmitted;
	}

	public SchoolDetails getSchool() {
		return school;
	}

	public void setSchool(SchoolDetails school) {
		this.school = school;
	}

	public AcademicYear getAcedemicYear() {
		return acedemicYear;
	}

	public void setAcedemicYear(AcademicYear acedemicYear) {
		this.acedemicYear = acedemicYear;
	}

	public List<StudentSchoolMapping> getStudentSchoolMapping() {
		return studentSchoolMapping;
	}

	public void setStudentSchoolMapping(
			List<StudentSchoolMapping> studentSchoolMapping) {
		this.studentSchoolMapping = studentSchoolMapping;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getEnrolledBy() {
		return enrolledBy;
	}

	public void setEnrolledBy(String enrolledBy) {
		this.enrolledBy = enrolledBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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
