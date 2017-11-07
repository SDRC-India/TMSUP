package org.sdrc.udise.model;

import java.util.List;
/**
 * 
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public class UserModel {

	private Integer userId;
	private String name;
	private String emailId;
	private String username;
	private String password;
	private boolean isLive;
	private Integer areaId;
	private String areaName;
	private long userLoginMetaId;
	private int areaLevel;
	private String country;
	private String roleName;
	private int roleId;
	private List<UserAreaModel> userAreaModels;
	
	private String blockName;
	
	private int blockId;
	
	private String districtName;
	
	private int districtId;
	
	private String divisionName;
	
	private int  divisionId;
	
	private int stateId;
	
	private String stateName;
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public int getBlockId() {
		return blockId;
	}
	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public int getDistrictId() {
		return districtId;
	}
	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public int getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(int divisionId) {
		this.divisionId = divisionId;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	/// added by harsh at laxamn system
	private int parentAreaId;
	private String parentAreaName;
	
	public String getParentAreaName() {
		return parentAreaName;
	}
	public void setParentAreaName(String parentAreaName) {
		this.parentAreaName = parentAreaName;
	}
	public int getParentAreaId() {
		return parentAreaId;
	}
	public void setParentAreaId(int parentAreaId) {
		this.parentAreaId = parentAreaId;
	}
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLive() {
		return isLive;
	}
	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}
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
	public long getUserLoginMetaId() {
		return userLoginMetaId;
	}
	public void setUserLoginMetaId(long userLoginMetaId) {
		this.userLoginMetaId = userLoginMetaId;
	}
	public int getAreaLevel() {
		return areaLevel;
	}
	public void setAreaLevel(int areaLevel) {
		this.areaLevel = areaLevel;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<UserAreaModel> getUserAreaModels() {
		return userAreaModels;
	}
	public void setUserAreaModels(List<UserAreaModel> userAreaModels) {
		this.userAreaModels = userAreaModels;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	

}
