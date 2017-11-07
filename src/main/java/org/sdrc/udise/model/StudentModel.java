package org.sdrc.udise.model;

import java.sql.Date;

public class StudentModel {

	private Integer studentId;

	private String studentName;

	private String fathersName;

	private String mothersName;

	private Date dateOfBirth;

	private String gender;

	private String addharCardNumber;

	private int schoolId;
	
	private int casteId;
	
	
	private String srRegistrationNo;

	public int getCasteId() {
		return casteId;
	}

	public void setCasteId(int casteId) {
		this.casteId = casteId;
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

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getSrRegistrationNo() {
		return srRegistrationNo;
	}

	public void setSrRegistrationNo(String srRegistrationNo) {
		this.srRegistrationNo = srRegistrationNo;
	}

	
	
	
	
}
