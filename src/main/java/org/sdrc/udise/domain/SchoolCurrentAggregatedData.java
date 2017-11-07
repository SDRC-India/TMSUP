/**
 * @author Azhar
 */

package org.sdrc.udise.domain;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity
@Table(name = "school_aggregate_data", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"school_id_fk", "academic_year_id_fk" }) })
public class SchoolCurrentAggregatedData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aggeragate_data_id")
	private Integer aggregateDataId;

	@Column(name = "leftout_students")
	private Integer lefoutStudents;

	@Column(name = "admitted_students")
	private Integer admittedStudents;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id_fk", nullable = false)
	private SchoolDetails school;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "academic_year_id_fk", nullable = false)
	private AcademicYear academicYearId;

	@Version
	public int version;

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

	public SchoolDetails getSchool() {
		return school;
	}

	public void setSchool(SchoolDetails school) {
		this.school = school;
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
