/**
 * 
 */
package org.sdrc.udise.repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.sdrc.udise.domain.SchoolAggregatedData;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public interface SchoolAggregatedDataRepository {

	List<SchoolAggregatedData> findBySchoolBlockAreaIdAndAcademicYearIdStartYearOrderBySchoolSchoolNameAsc(
			int blockId, String valueOf);

	List<SchoolAggregatedData> findByAcademicYearIdStartYearAndSchoolBlockAreaId(
			String valueOf, int blockId);

	List<SchoolAggregatedData> findByAcademicYearIdStartYearAndSchoolDistrictIdAreaId(
			String valueOf, int districtId);

	<S extends SchoolAggregatedData> List<S> save(Iterable<S> schoolAggregatedDatas);
	
	
	// school level by leftout %
	List<SchoolAggregatedData> findBySchoolLevelAndLeftOutPercentGreaterThan(
			int block, String currentYear, float percent);

	List<SchoolAggregatedData> findBySchoolLevelAndLeftOutPercentGreaterThanEqualTo(
			int block, String currentYear, float percent);

	List<SchoolAggregatedData> findBySchoolLevelAndLeftOutPercentEqualTo(
			int block, String currentYear, float percent);

	List<SchoolAggregatedData> findBySchoolLevelAndLeftOutPercentLessThan(
			int block, String currentYear, float percent);

	List<SchoolAggregatedData> findBySchoolLevelAndLeftOutPercentLessThanEqualTo(
			int block, String currentYear, float percent);

	// school level by enrolled %
	List<SchoolAggregatedData> findBySchoolLevelAndEnrolledPercentGreaterThan(
			int block, String currentYear, float percent);

	List<SchoolAggregatedData> findBySchoolLevelAndEnrolledPercentGreaterThanEqualTo(
			int block, String currentYear, float percent);

	List<SchoolAggregatedData> findBySchoolLevelAndEnrolledPercentEqualTo(
			int block, String currentYear, float percent);

	List<SchoolAggregatedData> findBySchoolLevelAndEnrolledPercentLessThan(
			int block, String currentYear, float percent);

	List<SchoolAggregatedData> findBySchoolLevelAndEnrolledPercentLessThanEqualTo(
			int block, String currentYear, float percent);

	List<SchoolAggregatedData> findByAcademicYearIdStartYear(String valueOf);

	SchoolAggregatedData findBySchoolSchoolId(int schoolId);

	void save(SchoolAggregatedData schoolAggregatedData);

	void updateLastUpdatedDate(Date date, Timestamp timestamp);

}
