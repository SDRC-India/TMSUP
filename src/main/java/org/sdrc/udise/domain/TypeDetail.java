package org.sdrc.udise.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 */

@Entity
@Table(name = "type_detail")

public class TypeDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "typeDetailId")
	private Integer typeDetailId;

	@Column(name = "type_detail", length = 100)
	private String typeDetail;

	@Column(length = 2000)
	private String description;

	@Column(name = "created_date", nullable = false)
	private Timestamp createdDate;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	// ******** bi-directional many-to-one association to Type *******

	@ManyToOne
	@JoinColumn(name = "type_id_fk", nullable = false)
	private Type type;
	// ******** bi-directional one-to-many association to SchoolDetails *******
	@OneToMany(mappedBy = "schoolType")
	private List<SchoolDetails> schoolDetails;
	
	// ******** bi-directional one-to-many association to StudentsDetails *******
	@OneToMany(mappedBy = "caste")
	private List<StudentsDetails> studentDetails;
	
	
	public TypeDetail(int casteId) {
		this.typeDetailId = casteId;
	}

	public TypeDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	// getter setter
	public Integer getTypeDetailId() {
		return typeDetailId;
	}

	public void setTypeDetailId(Integer typeDetailId) {
		this.typeDetailId = typeDetailId;
	}

	public String getTypeDetail() {
		return typeDetail;
	}

	public void setTypeDetail(String typeDetail) {
		this.typeDetail = typeDetail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<SchoolDetails> getSchoolDetails() {
		return schoolDetails;
	}

	public void setSchoolDetails(List<SchoolDetails> schoolDetails) {
		this.schoolDetails = schoolDetails;
	}

	public List<StudentsDetails> getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(List<StudentsDetails> studentDetails) {
		this.studentDetails = studentDetails;
	}

}
