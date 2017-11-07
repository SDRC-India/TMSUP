
package org.sdrc.udise.repository;

import java.sql.Date;
import java.util.List;

import org.sdrc.udise.domain.AggregationData;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public interface AggregationDataRepository {

	List<AggregationData> findByAreaIdLevelAndAcademicYearIdStartYear(
			int parseInt, String currentYear);

	List<AggregationData> findByAreaIdLevelAndAcademicYearIdStartYearOrderByAreaIdAreaNameAsc(
			int level, String academicYear);

	List<AggregationData> findByAreaIdParentAreaIdAndAcademicYearIdStartYearOrderByAreaIdAreaNameAsc(
			int divisionId, String valueOf);

	@Transactional
	<S extends AggregationData> List<S> save(
			Iterable<S> aggreagateAggregationDatas);

	List<AggregationData> findByAcademicYearIdStartYearOrderByAreaIdAreaNameAsc(
			String startYear);

	// --------------Author : azaruddin-----------------------------

	// division level
	List<AggregationData> findByLeftOutPercentGreaterThan(int levelDivision,
			String currentYear, float percent);

	List<AggregationData> findByLeftOutPercentGreaterThanEqualTo(
			int levelDivision, String currentYear, float percent);

	List<AggregationData> findByLeftOutPercentEqualTo(int levelDivision,
			String currentYear, float percent);

	List<AggregationData> findByLeftOutPercentLessThan(int levelDivision,
			String currentYear, float percent);

	List<AggregationData> findByLeftOutPercentLessThanEqualTo(
			int levelDivision, String currentYear, float percent);

	// district,block level by leftout %
	List<AggregationData> findByParentAreaIdAndLeftOutPercentGreaterThan(int area,
			String currentYear, float percent);

	List<AggregationData> findByParentAreaIdAndLeftOutPercentGreaterThanEqualTo(
			int area, String currentYear, float percent);

	List<AggregationData> findByParentAreaIdAndLeftOutPercentEqualTo(int area,
			String currentYear, float percent);

	List<AggregationData> findByParentAreaIdAndLeftOutPercentLessThan(int area,
			String currentYear, float percent);

	List<AggregationData> findByParentAreaIdAndLeftOutPercentLessThanEqualTo(
			int area, String currentYear, float percent);
	
	
	
	//-------------------------------------------enrolled----------------
	
	// division level
	List<AggregationData> findByEnrolledPercentGreaterThan(int levelDivision,
			String currentYear, float percent);

	List<AggregationData> findByEnrolledPercentGreaterThanEqualTo(
			int levelDivision, String currentYear, float percent);

	List<AggregationData> findByEnrolledPercentEqualTo(int levelDivision,
			String currentYear, float percent);

	List<AggregationData> findByEnrolledPercentLessThan(int levelDivision,
			String currentYear, float percent);

	List<AggregationData> findByEnrolledPercentLessThanEqualTo(
			int levelDivision, String currentYear, float percent);

	// district,block level by leftout %
	List<AggregationData> findByParentAreaIdAndEnrolledPercentGreaterThan(int area,
			String currentYear, float percent);

	List<AggregationData> findByParentAreaIdAndEnrolledPercentGreaterThanEqualTo(
			int area, String currentYear, float percent);

	List<AggregationData> findByParentAreaIdAndEnrolledPercentEqualTo(int area,
			String currentYear, float percent);

	List<AggregationData> findByParentAreaIdAndEnrolledPercentLessThan(int area,
			String currentYear, float percent);

	List<AggregationData> findByParentAreaIdAndEnrolledPercentLessThanEqualTo(
			int area, String currentYear, float percent);

	void updateLastUpdatedDate(Date date);



}
