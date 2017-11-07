/**
 * 
 */
package org.sdrc.udise.domain;

import java.sql.Date;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@Entity
@Table(name="aggregate_data")
public class AggregationData {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="aggeragate_data_id")
	private Integer aggregateDataId;
	
	@Column(name="leftout_students")
	private Integer lefoutStudents;
	
	@Column(name="admitted_students")
	private Integer admittedStudents;
	
	@Column(name="admitted_boys")
	private Integer admittedBoys;
	
	
	@Column(name="admitted_girls")
	private Integer admittedGirls;
	
	@Column(name="admitted_thirdGender")
	private Integer admittedThirdGender;
	
	
	@Column(name="admitted_students_gen")
	private Integer admittedStudentsGen;
	
	@Column(name="admitted_students_obc")
	private Integer admittedStudentsOBC;
	
	@Column(name="admitted_students_SC")
	private Integer admittedStudentsSC;
	
	@Column(name="admitted_students_ST")
	private Integer admittedStudentsST;
	
	@Column(name="admitted_students_Minority")
	private Integer admittedStudentsMinority;
	
	
	@Column(name="leftout_boys")
	private Integer leftoutBoys;
	
	
	@Column(name="leftout_girls")
	private Integer leftoutGirls;
	
	@Column(name="leftout_thirdGender")
	private Integer leftoutThirdGender;
	
	@Column(name="leftout_students_gen")
	private Integer leftoutStudentsGen;
	
	@Column(name="leftout_students_obc")
	private Integer leftoutStudentsOBC;
	
	@Column(name="leftout_students_SC")
	private Integer leftoutStudentsSC;
	
	public Integer getAdmittedBoys() {
		return admittedBoys;
	}

	public void setAdmittedBoys(Integer admittedBoys) {
		this.admittedBoys = admittedBoys;
	}

	public Integer getAdmittedGirls() {
		return admittedGirls;
	}

	public void setAdmittedGirls(Integer admittedGirls) {
		this.admittedGirls = admittedGirls;
	}

	public Integer getAdmittedStudentsGen() {
		return admittedStudentsGen;
	}

	public void setAdmittedStudentsGen(Integer admittedStudentsGen) {
		this.admittedStudentsGen = admittedStudentsGen;
	}

	public Integer getAdmittedStudentsOBC() {
		return admittedStudentsOBC;
	}

	public void setAdmittedStudentsOBC(Integer admittedStudentsOBC) {
		this.admittedStudentsOBC = admittedStudentsOBC;
	}

	public Integer getAdmittedStudentsSC() {
		return admittedStudentsSC;
	}

	public void setAdmittedStudentsSC(Integer admittedStudentsSC) {
		this.admittedStudentsSC = admittedStudentsSC;
	}

	public Integer getAdmittedStudentsST() {
		return admittedStudentsST;
	}

	public void setAdmittedStudentsST(Integer admittedStudentsST) {
		this.admittedStudentsST = admittedStudentsST;
	}

	public Integer getAdmittedStudentsMinority() {
		return admittedStudentsMinority;
	}

	public void setAdmittedStudentsMinority(Integer admittedStudentsMinority) {
		this.admittedStudentsMinority = admittedStudentsMinority;
	}

	public Integer getLeftoutBoys() {
		return leftoutBoys;
	}

	public void setLeftoutBoys(Integer leftoutBoys) {
		this.leftoutBoys = leftoutBoys;
	}

	public Integer getLeftoutGirls() {
		return leftoutGirls;
	}

	public void setLeftoutGirls(Integer leftoutGirls) {
		this.leftoutGirls = leftoutGirls;
	}

	public Integer getLeftoutStudentsGen() {
		return leftoutStudentsGen;
	}

	public void setLeftoutStudentsGen(Integer leftoutStudentsGen) {
		this.leftoutStudentsGen = leftoutStudentsGen;
	}

	public Integer getLeftoutStudentsOBC() {
		return leftoutStudentsOBC;
	}

	public void setLeftoutStudentsOBC(Integer leftoutStudentsOBC) {
		this.leftoutStudentsOBC = leftoutStudentsOBC;
	}

	public Integer getLeftoutStudentsSC() {
		return leftoutStudentsSC;
	}

	public void setLeftoutStudentsSC(Integer leftoutStudentsSC) {
		this.leftoutStudentsSC = leftoutStudentsSC;
	}

	public Integer getLeftoutStudentsST() {
		return leftoutStudentsST;
	}

	public void setLeftoutStudentsST(Integer leftoutStudentsST) {
		this.leftoutStudentsST = leftoutStudentsST;
	}

	public Integer getLeftoutStudentsMinority() {
		return leftoutStudentsMinority;
	}

	public void setLeftoutStudentsMinority(Integer leftoutStudentsMinority) {
		this.leftoutStudentsMinority = leftoutStudentsMinority;
	}

	@Column(name="leftout_students_ST")
	private Integer leftoutStudentsST;
	
	@Column(name="leftout_students_Minority")
	private Integer leftoutStudentsMinority;
	
	
	@ManyToOne
	@JoinColumn(name="area_id_fk",nullable=false)
	private Area areaId;
	
	@ManyToOne
	@JoinColumn(name="academic_year_id_fk",nullable=false)
	private AcademicYear academicYearId;
	
	@Column(name="last_updated_date")
	private Date lastUpdatedDate;

	
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	// getter setters
	public Integer getAggregateDataId() {
		return aggregateDataId;
	}

	public void setAggregateDataId(Integer aggregateDataId) {
		this.aggregateDataId = aggregateDataId;
	}

	public Integer getLefoutStudents() {
		return lefoutStudents;
	}

	public void setLefoutStudents(Integer lefoutStudents) {
		this.lefoutStudents = lefoutStudents;
	}

	public Integer getAdmittedStudents() {
		return admittedStudents;
	}

	public void setAdmittedStudents(Integer admittedStudents) {
		this.admittedStudents = admittedStudents;
	}

	public Area getAreaId() {
		return areaId;
	}

	public void setAreaId(Area areaId) {
		this.areaId = areaId;
	}

	public AcademicYear getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(AcademicYear academicYearId) {
		this.academicYearId = academicYearId;
	}
	
	
	public double findPercentOfLeftOut() {
		double totalStudents = lefoutStudents + admittedStudents;
		DecimalFormat df = new DecimalFormat(".#");
		if (totalStudents > 0) {
			double percent = (lefoutStudents / totalStudents) * 100;
			return Double.valueOf(df.format(percent));
		}

		else
			return 0.0;
	}

	public double findPercentOfAdmittedOut() {
		double totalStudents = lefoutStudents + admittedStudents;
		DecimalFormat df = new DecimalFormat(".#");
		if (totalStudents > 0) {
			double percent = (admittedStudents / totalStudents) * 100;
			return Double.valueOf(df.format(percent));
		}

		else
			return 0.0;
	}

	public Integer getAdmittedThirdGender() {
		return admittedThirdGender;
	}

	public void setAdmittedThirdGender(Integer admittedThirdGender) {
		this.admittedThirdGender = admittedThirdGender;
	}

	public Integer getLeftoutThirdGender() {
		return leftoutThirdGender;
	}

	public void setLeftoutThirdGender(Integer leftoutThirdGender) {
		this.leftoutThirdGender = leftoutThirdGender;
	}
	
	
}
