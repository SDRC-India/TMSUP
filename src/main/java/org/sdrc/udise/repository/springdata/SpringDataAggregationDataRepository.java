
/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
package org.sdrc.udise.repository.springdata;

import java.sql.Date;
import java.util.List;

import org.sdrc.udise.domain.AggregationData;
import org.sdrc.udise.repository.AggregationDataRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@RepositoryDefinition(domainClass = AggregationData.class, idClass = Integer.class)
public interface SpringDataAggregationDataRepository extends
		AggregationDataRepository {

	// ---------------------------@Author Azaruddin
	// (azaruddin@sdrc.co.in)-----------------
	// division level by leftout percent
	
	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar ON a.area_id_fk=ar.areaid  "
			+ "WHERE "
			+ "ar.level= :levelDivision and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.leftout_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) > :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByLeftOutPercentGreaterThan(
			@Param("levelDivision") int levelDivision,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar ON a.area_id_fk=ar.areaid  "
			+ "WHERE "
			+ "ar.level= :levelDivision and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.leftout_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) >= :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByLeftOutPercentGreaterThanEqualTo(
			@Param("levelDivision") int levelDivision,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar ON a.area_id_fk=ar.areaid  "
			+ "WHERE "
			+ "ar.level= :levelDivision and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.leftout_students as decimal) * 100 /(a.leftout_students + a.admitted_students),1) as decimal) = :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByLeftOutPercentEqualTo(
			@Param("levelDivision") int levelDivision,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar ON a.area_id_fk=ar.areaid  "
			+ "WHERE "
			+ "ar.level= :levelDivision and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.leftout_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) < :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByLeftOutPercentLessThan(
			@Param("levelDivision") int levelDivision,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar ON a.area_id_fk=ar.areaid  "
			+ "WHERE "
			+ "ar.level= :levelDivision and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.leftout_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) <= :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByLeftOutPercentLessThanEqualTo(
			@Param("levelDivision") int levelDivision,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	// district,block level,school level by leftout %
	
	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON "
			+ "a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar "
			+ "ON ar.areaid=a.area_id_fk "
			+ "WHERE "
			+ "ar.parent_area_id= :area_id and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.leftout_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) > :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByParentAreaIdAndLeftOutPercentGreaterThan(
			@Param("area_id") int area_id,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON "
			+ "a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar "
			+ "ON ar.areaid=a.area_id_fk "
			+ "WHERE "
			+ "ar.parent_area_id= :area_id and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.leftout_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) >= :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByParentAreaIdAndLeftOutPercentGreaterThanEqualTo(
			@Param("area_id") int area_id,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON "
			+ "a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar "
			+ "ON ar.areaid=a.area_id_fk "
			+ "WHERE "
			+ "ar.parent_area_id= :area_id and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.leftout_students as decimal) * 100 /(a.leftout_students + a.admitted_students),1) as decimal) = :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByParentAreaIdAndLeftOutPercentEqualTo(
			@Param("area_id") int area_id,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON "
			+ "a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar "
			+ "ON ar.areaid=a.area_id_fk "
			+ "WHERE "
			+ "ar.parent_area_id= :area_id and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.leftout_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) < :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByParentAreaIdAndLeftOutPercentLessThan(
			@Param("area_id") int area_id,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON "
			+ "a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar "
			+ "ON ar.areaid=a.area_id_fk "
			+ "WHERE "
			+ "ar.parent_area_id= :area_id and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.leftout_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) <= :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByParentAreaIdAndLeftOutPercentLessThanEqualTo(
			@Param("area_id") int area_id,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	// --------------------------------------------------Enrolled percent
	// calculation-------------------------------------------

	// division level by enrolled/admitted percent
	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar ON a.area_id_fk=ar.areaid  "
			+ "WHERE "
			+ "ar.level= :levelDivision and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.admitted_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) > :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByEnrolledPercentGreaterThan(
			@Param("levelDivision") int levelDivision,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar ON a.area_id_fk=ar.areaid  "
			+ "WHERE "
			+ "ar.level= :levelDivision and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.admitted_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) >= :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByEnrolledPercentGreaterThanEqualTo(
			@Param("levelDivision") int levelDivision,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar ON a.area_id_fk=ar.areaid  "
			+ "WHERE "
			+ "ar.level= :levelDivision and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.admitted_students as decimal) * 100 /(a.leftout_students + a.admitted_students),1) as decimal) = :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByEnrolledPercentEqualTo(
			@Param("levelDivision") int levelDivision,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar ON a.area_id_fk=ar.areaid  "
			+ "WHERE "
			+ "ar.level= :levelDivision and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.admitted_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) < :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByEnrolledPercentLessThan(
			@Param("levelDivision") int levelDivision,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar ON a.area_id_fk=ar.areaid  "
			+ "WHERE "
			+ "ar.level= :levelDivision and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.admitted_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) <= :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByEnrolledPercentLessThanEqualTo(
			@Param("levelDivision") int levelDivision,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	// district,block level,school level by leftout %
	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON "
			+ "a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar "
			+ "ON ar.areaid=a.area_id_fk "
			+ "WHERE "
			+ "ar.parent_area_id= :area_id and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.admitted_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) > :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByParentAreaIdAndEnrolledPercentGreaterThan(
			@Param("area_id") int area_id,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON "
			+ "a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar "
			+ "ON ar.areaid=a.area_id_fk "
			+ "WHERE "
			+ "ar.parent_area_id= :area_id and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.admitted_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) >= :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByParentAreaIdAndEnrolledPercentGreaterThanEqualTo(
			@Param("area_id") int area_id,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON "
			+ "a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar "
			+ "ON ar.areaid=a.area_id_fk "
			+ "WHERE "
			+ "ar.parent_area_id= :area_id and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.admitted_students as decimal) * 100 /(a.leftout_students + a.admitted_students),1) as decimal) = :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByParentAreaIdAndEnrolledPercentEqualTo(
			@Param("area_id") int area_id,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON "
			+ "a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar "
			+ "ON ar.areaid=a.area_id_fk "
			+ "WHERE "
			+ "ar.parent_area_id= :area_id and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.admitted_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) < :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByParentAreaIdAndEnrolledPercentLessThan(
			@Param("area_id") int area_id,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);

	@Query(value = "select a.* from aggregate_data a "
			+ "inner join "
			+ "academic_year ay "
			+ "ON "
			+ "a.academic_year_id_fk=ay.academic_year_id "
			+ "inner join "
			+ "area ar "
			+ "ON ar.areaid=a.area_id_fk "
			+ "WHERE "
			+ "ar.parent_area_id= :area_id and ay.start_year= :currentYear "
			+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.admitted_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) <= :percent else false end", nativeQuery = true)
	@Override
	List<AggregationData> findByParentAreaIdAndEnrolledPercentLessThanEqualTo(
			@Param("area_id") int area_id,
			@Param("currentYear") String currentYear,
			@Param("percent") float percent);
	
	
	@Modifying
	@Transactional
	@Query("UPDATE  AggregationData a SET a.lastUpdatedDate = :lastUpdatedDate ")
	@Override
	public void updateLastUpdatedDate(@Param("lastUpdatedDate")Date date);

}
