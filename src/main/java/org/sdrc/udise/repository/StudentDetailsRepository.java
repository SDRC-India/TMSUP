package org.sdrc.udise.repository;

import java.sql.Timestamp;
import java.util.List;

import org.sdrc.udise.domain.StudentsDetails;

public interface StudentDetailsRepository {

	public List<StudentsDetails> findBySchoolSchoolIdAndAcedemicYearStartYear(
			int schoolId, String acedemicYear);

	public StudentsDetails findByStudentId(int studentId);

	StudentsDetails save(StudentsDetails student);

	public StudentsDetails findByStudentNameIgnoreCaseAndFathersNameIgnoreCaseAndMothersNameIgnoreCaseAndSchoolSchoolId(
			String trim, String trim2, String trim3, int schoolId);

	public List<StudentsDetails> findBySchoolSchoolIdAndAcedemicYearStartYearOrderByStudentIdDesc(
			int schoolId, String acedemicYear);

	public List<StudentsDetails> findBySchoolSchoolIdAndAcedemicYearStartYearOrderByIsSubmittedAscStudentIdDesc(
			int schoolId, String valueOf);

	public StudentsDetails findBySrRegistrationNumberIgnoreCaseAndSchoolSchoolId(
			String trim, int schoolId);

	public List<Object[]> getAggregateStudentsDataAtBlockLevel(
			String academicYear);

	public List<Object[]> getAggregateStudentsDataAtDistrictLevel(
			String academicYear);

	public List<Object[]> getAggregateStudentsDataAtDivisionLevel(
			String academicYear);

	// ------------division level--------------------
	/**
	 * @author Azaruddin (azaruddin@sdrc.co.in)
	 */
	public List<Object[]> findByAcedemicYearStartYearAndSchoolForAllDivisions(
			String startYear);

	public List<Object[]> findByAcedemicYearStartYearAndSchoolDivisionLevel(
			String startYear,int division);

	public List<Object[]> findByAcedemicYearStartYearAndSchoolDistrictLevel(
			String acedemicYear,int district);

	public List<Object[]> findByAcedemicYearStartYearAndSchoolBlockLevel(
			String acedemicYear,int block);

	public List<StudentsDetails> findBySchoolSchoolIdAndAcedemicYearStartYearAndCreateDateTimeLessThan(
			int schoolId, String valueOf, Timestamp timestampDate);

	public List<Object[]> getAggregateStudentsDataAtSchoolLevel(String valueOf);

}
