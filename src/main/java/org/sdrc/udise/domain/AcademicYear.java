/**
 * 
 */
package org.sdrc.udise.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@Entity
@Table(name = "academic_year")
public class AcademicYear implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "academic_year_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer academicYearId;

	@Column(name = "start_year")
	private String startYear;

	@Column(name = "end_year")
	private String endYear;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "academic_year")
	private String academicYear;

	// ******** bi-directional one-to-many association to StudentSchoolMapping
	// *******
	@OneToMany(mappedBy = "academicYear")
	private List<StudentSchoolMapping> studentSchoolMapping;

	// ******** bi-directional one-to-many association to AggregationData
	// *******
	@OneToMany(mappedBy = "academicYearId")
	private List<AggregationData> aggrregationData;

	// ******** bi-directional one-to-many association to AggregationData
	// *******
	@OneToMany(mappedBy = "acedemicYear")
	private List<StudentsDetails> StudentsDetails;

	public List<StudentsDetails> getStudentsDetails() {
		return StudentsDetails;
	}

	public void setStudentsDetails(List<StudentsDetails> studentsDetails) {
		StudentsDetails = studentsDetails;
	}

	// getter setters
	public List<StudentSchoolMapping> getStudentSchoolMapping() {
		return studentSchoolMapping;
	}

	public void setStudentSchoolMapping(
			List<StudentSchoolMapping> studentSchoolMapping) {
		this.studentSchoolMapping = studentSchoolMapping;
	}

	public List<AggregationData> getAggrregationData() {
		return aggrregationData;
	}

	public void setAggrregationData(List<AggregationData> aggrregationData) {
		this.aggrregationData = aggrregationData;
	}

	public Integer getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(Integer academicYearId) {
		this.academicYearId = academicYearId;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	// public static String getCurrentAcademicYear() {
	// LocalDate localdate = new LocalDate();
	// int year = localdate.getYear();
	// int monthOfYear = localdate.getMonthOfYear();
	//
	// int currAcYear = year;
	// if (monthOfYear < 4) {
	// currAcYear = currAcYear - 1;
	// }
	// return String.valueOf(currAcYear);
	// }

	/**
	 * Method returns current academic year.This method will work on any time
	 * zone on which server is hosted.
	 * 
	 * @return <<String>> current academic year in Timezone Asia/Kolkata
	 */
	public static String getCurrentAcademicYear() {
		// Current date in "Asia/Kolkata", you can get it from ZoneId javadoc
		LocalDate todayKolkata = LocalDate.now(ZoneId.of("Asia/Kolkata"));
		int year = todayKolkata.getYear();
		int monthOfYear = todayKolkata.getMonthValue();
		System.out.println("Year value : " + year);
		System.out.println("Month value : " + monthOfYear);
		int currAcYear = year;
		if (monthOfYear < 4) {
			currAcYear = currAcYear - 1;
		}
		return String.valueOf(currAcYear);

	}

}
