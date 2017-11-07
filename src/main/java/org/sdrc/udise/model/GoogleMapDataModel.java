package org.sdrc.udise.model;


/**
 * This model will be used for sending data for google map data
 * 
 * @author Harsh Pratyush
 * @version 1.0.0
 * 
 */

public class GoogleMapDataModel {
	
	private int id;
	private String udiseCode;
	private String leftOutPercent;
	private String longitude;
	private String latitude;
	private String schoolName;
	private boolean showWindow;
	private String icon;
	public int getId() {
		return id;
	}
	public void setId(int Id) {
		this.id = Id;
	}
	public String getUdiseCode() {
		return udiseCode;
	}
	public void setUdiseCode(String udiseCode) {
		this.udiseCode = udiseCode;
	}
	public String getLeftOutPercent() {
		return leftOutPercent;
	}
	public void setLeftOutPercent(String leftOutPercent) {
		this.leftOutPercent = leftOutPercent;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public boolean isShowWindow() {
		return showWindow;
	}
	public void setShowWindow(boolean showWindow) {
		this.showWindow = showWindow;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}


	
}
