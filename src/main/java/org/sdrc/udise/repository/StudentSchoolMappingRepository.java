package org.sdrc.udise.repository;

import java.util.List;

import org.sdrc.udise.domain.StudentSchoolMapping;

public interface StudentSchoolMappingRepository {

	List<StudentSchoolMapping> findByAcademicYearStartYearAndFromSchoolDetailsSchoolIdAndIsLatest(String acedemicYear,
			int schoolId, boolean isLatest);

	StudentSchoolMapping save(StudentSchoolMapping studentSchoolMapping);

	//added by azar
	StudentSchoolMapping findByStudentDetailsStudentId(int studentId);

	List<Object[]> getAggregateStudentsDataAtBlockLevelMigrated(String valueOf,
			int enrollTypeMigrated);

	List<Object[]> getAggregateStudentsDataAtDistrictLevelMigrated(
			String valueOf, int enrollTypeMigrated);

	List<Object[]> getAggregateStudentsDataAtDivisionLevelMigrated(
			String valueOf, int enrollTypeMigrated);

	List<Object[]> getAggregateStudentsDataAtSchoolLevelMigrated(
			String valueOf, int enrollTypeMigrated);
}
