/**
 * 
 */
package org.sdrc.udise.model;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public class AreaWebModel {

	
	private int areaId;	
	private String areaName;	
	private int parentAreaId;	
	private boolean isDisabled;

	
	public boolean isDisabled() {
		return isDisabled;
	}
	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
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

	
}
