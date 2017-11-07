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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
*
* @author Harsh Pratyush (harsh@sdrc.co.in)        
*/


@Entity
@Table(name = "type")
public class Type implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "typeId")
	private Integer typeId;

	@Column(name = "type_name")
	private String typeName;

	private String description;
	
	@Column(name = "created_date", nullable = false)
	private Timestamp createdDate;
	
	@Column(name = "updated_date")
	private Timestamp updatedDate;

	// ******** bi-directional one-to-many association to TypeDetail *******
	
	@OneToMany(mappedBy = "type",fetch=FetchType.LAZY)
	private List<TypeDetail> typeDetails;

	//GETTER SETTER **********************
	
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TypeDetail> getTypeDetails() {
		return typeDetails;
	}

	public void setTypeDetails(List<TypeDetail> typeDetails) {
		this.typeDetails = typeDetails;
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

}
