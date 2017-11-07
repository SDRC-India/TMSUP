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
* This entity class holds the master user list
*
*/

@Entity
@Table(name = "mst_user")
public class MSTUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "userId")
	private Integer userId;
	
	public MSTUser() {
		super();
	}

	@Column(length=100)
	private String name;
	
	@Column(name = "user_name")
	private String userName;
	
	private String password;
	
	private String email;
	
	@Column(name = "is_live")
	private Boolean isLive;
	
	@Column(name="created_date")
	private Timestamp createdDate;
	
//	******** bi-directional one-to-many association to UserAreaMapping *******
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	private List<UserAreaMapping> userAreaMappings;

	
	public MSTUser(Integer userId) {
		// TODO Auto-generated constructor stub
	}

	// getter setters
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsLive() {
		return isLive;
	}

	public void setIsLive(Boolean isLive) {
		this.isLive = isLive;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public List<UserAreaMapping> getUserAreaMappings() {
		return userAreaMappings;
	}

	public void setUserAreaMappings(List<UserAreaMapping> userAreaMappings) {
		this.userAreaMappings = userAreaMappings;
	}
	
	
	
	
}
