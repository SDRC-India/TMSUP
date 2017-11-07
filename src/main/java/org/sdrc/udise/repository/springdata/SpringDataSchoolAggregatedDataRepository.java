/**
 * 
 */
package org.sdrc.udise.repository.springdata;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.sdrc.udise.domain.SchoolAggregatedData;
import org.sdrc.udise.repository.SchoolAggregatedDataRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@RepositoryDefinition(domainClass=SchoolAggregatedData.class,idClass=Integer.class)
public interface SpringDataSchoolAggregatedDataRepository extends
		SchoolAggregatedDataRepository {


	@Query("select ag from SchoolAggregatedData ag join ag.academicYearId ay "
			+ " join fetch ag.school asch join asch.block area join fetch asch.schoolLatLongLinks "
			+ "  WHERE ay.startYear =:startYear and area.areaId =:areaId ")
	List<SchoolAggregatedData> findByAcademicYearIdStartYearAndSchoolBlockAreaId(
			@Param("startYear") String startYear, @Param("areaId") int areaId);



	 @Override
		@Query("select ag from SchoolAggregatedData ag join ag.academicYearId ay "
				+ " join fetch ag.school asch join asch.districtId area join fetch asch.schoolLatLongLinks "
				+ "  WHERE ay.startYear =:startYear and area.areaId =:areaId ")
	public List<SchoolAggregatedData> findByAcademicYearIdStartYearAndSchoolDistrictIdAreaId(
			@Param("startYear") String startYear, @Param("areaId") int areaId);
	 
	 
	 

		///-------------------------------School level leftout-----------------
		
		
		//school level by leftout %
		@Query(value = "select a.* from school_aggregated_data a "
				+ "inner join "
				+ "academic_year ay "
				+ "ON "
				+ "a.academic_year_id_fk=ay.academic_year_id "
				+ "inner join "
				+ "school_details sc "
				+ "ON sc.schoolid=a.school_id_fk "
				+ "WHERE "
				+ "sc.block_id_fk= :area_id and ay.start_year= :currentYear "
				+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.leftout_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) > :percent else false end", nativeQuery = true)
		
				List<SchoolAggregatedData> findBySchoolLevelAndLeftOutPercentGreaterThan(
						@Param("area_id") int block,
						@Param("currentYear") String currentYear,
						@Param("percent") float percent);
				
		@Query(value = "select a.* from school_aggregated_data a "
				+ "inner join "
				+ "academic_year ay "
				+ "ON "
				+ "a.academic_year_id_fk=ay.academic_year_id "
				+ "inner join "
				+ "school_details sc "
				+ "ON sc.schoolid=a.school_id_fk "
				+ "WHERE "
				+ "sc.block_id_fk= :area_id and ay.start_year= :currentYear "
				+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.leftout_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) >= :percent else false end", nativeQuery = true)
		
				List<SchoolAggregatedData> findBySchoolLevelAndLeftOutPercentGreaterThanEqualTo(
						@Param("area_id") int block,
						@Param("currentYear") String currentYear,
						@Param("percent") float percent);
				
		@Query(value = "select a.* from school_aggregated_data a "
				+ "inner join "
				+ "academic_year ay "
				+ "ON "
				+ "a.academic_year_id_fk=ay.academic_year_id "
				+ "inner join "
				+ "school_details sc "
				+ "ON sc.schoolid=a.school_id_fk "
				+ "WHERE "
				+ "sc.block_id_fk= :area_id and ay.start_year= :currentYear "
				+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.leftout_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) = :percent else false end", nativeQuery = true)
		
				List<SchoolAggregatedData> findBySchoolLevelAndLeftOutPercentEqualTo(
						@Param("area_id") int block,
						@Param("currentYear") String currentYear,
						@Param("percent") float percent);
				
		@Query(value = "select a.* from school_aggregated_data a "
				+ "inner join "
				+ "academic_year ay "
				+ "ON "
				+ "a.academic_year_id_fk=ay.academic_year_id "
				+ "inner join "
				+ "school_details sc "
				+ "ON sc.schoolid=a.school_id_fk "
				+ "WHERE "
				+ "sc.block_id_fk= :area_id and ay.start_year= :currentYear "
				+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.leftout_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) < :percent else false end", nativeQuery = true)
		
				List<SchoolAggregatedData> findBySchoolLevelAndLeftOutPercentLessThan(
						@Param("area_id") int block,
						@Param("currentYear") String currentYear,
						@Param("percent") float percent);
				
		@Query(value = "select a.* from school_aggregated_data a "
				+ "inner join "
				+ "academic_year ay "
				+ "ON "
				+ "a.academic_year_id_fk=ay.academic_year_id "
				+ "inner join "
				+ "school_details sc "
				+ "ON sc.schoolid=a.school_id_fk "
				+ "WHERE "
				+ "sc.block_id_fk= :area_id and ay.start_year= :currentYear "
				+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.leftout_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) <= :percent else false end", nativeQuery = true)
		
				List<SchoolAggregatedData> findBySchoolLevelAndLeftOutPercentLessThanEqualTo(
						@Param("area_id") int block,
						@Param("currentYear") String currentYear,
						@Param("percent") float percent);
		
		
		///------------------------------------------Enrolled % school level
		

		//school level by enrolled %
		@Query(value = "select a.* from school_aggregated_data a "
				+ "inner join "
				+ "academic_year ay "
				+ "ON "
				+ "a.academic_year_id_fk=ay.academic_year_id "
				+ "inner join "
				+ "school_details sc "
				+ "ON sc.schoolid=a.school_id_fk "
				+ "WHERE "
				+ "sc.block_id_fk= :area_id and ay.start_year= :currentYear "
				+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.admitted_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) > :percent else false end", nativeQuery = true)
		
				List<SchoolAggregatedData> findBySchoolLevelAndEnrolledPercentGreaterThan(
						@Param("area_id") int block,
						@Param("currentYear") String currentYear,
						@Param("percent") float percent);
				
		@Query(value = "select a.* from school_aggregated_data a "
				+ "inner join "
				+ "academic_year ay "
				+ "ON "
				+ "a.academic_year_id_fk=ay.academic_year_id "
				+ "inner join "
				+ "school_details sc "
				+ "ON sc.schoolid=a.school_id_fk "
				+ "WHERE "
				+ "sc.block_id_fk= :area_id and ay.start_year= :currentYear "
				+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.admitted_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) >= :percent else false end", nativeQuery = true)
		
				List<SchoolAggregatedData> findBySchoolLevelAndEnrolledPercentGreaterThanEqualTo(
						@Param("area_id") int block,
						@Param("currentYear") String currentYear,
						@Param("percent") float percent);
				
		@Query(value = "select a.* from school_aggregated_data a "
				+ "inner join "
				+ "academic_year ay "
				+ "ON "
				+ "a.academic_year_id_fk=ay.academic_year_id "
				+ "inner join "
				+ "school_details sc "
				+ "ON sc.schoolid=a.school_id_fk "
				+ "WHERE "
				+ "sc.block_id_fk= :area_id and ay.start_year= :currentYear "
				+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.admitted_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) = :percent else false end", nativeQuery = true)
		
				List<SchoolAggregatedData> findBySchoolLevelAndEnrolledPercentEqualTo(
						@Param("area_id") int block,
						@Param("currentYear") String currentYear,
						@Param("percent") float percent);
				
		@Query(value = "select a.* from school_aggregated_data a "
				+ "inner join "
				+ "academic_year ay "
				+ "ON "
				+ "a.academic_year_id_fk=ay.academic_year_id "
				+ "inner join "
				+ "school_details sc "
				+ "ON sc.schoolid=a.school_id_fk "
				+ "WHERE "
				+ "sc.block_id_fk= :area_id and ay.start_year= :currentYear "
				+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.admitted_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) < :percent else false end", nativeQuery = true)
		
				List<SchoolAggregatedData> findBySchoolLevelAndEnrolledPercentLessThan(
						@Param("area_id") int block,
						@Param("currentYear") String currentYear,
						@Param("percent") float percent);
				
		@Query(value = "select a.* from school_aggregated_data a "
				+ "inner join "
				+ "academic_year ay "
				+ "ON "
				+ "a.academic_year_id_fk=ay.academic_year_id "
				+ "inner join "
				+ "school_details sc "
				+ "ON sc.schoolid=a.school_id_fk "
				+ "WHERE "
				+ "sc.block_id_fk= :area_id and ay.start_year= :currentYear "
				+ "and case when a.leftout_students + a.admitted_students !=0 then cast(round(cast(a.admitted_students as decimal) * 100 /(a.leftout_students + a.admitted_students),2) as decimal) <= :percent else false end", nativeQuery = true)
		
				List<SchoolAggregatedData> findBySchoolLevelAndEnrolledPercentLessThanEqualTo(
						@Param("area_id") int block,
						@Param("currentYear") String currentYear,
						@Param("percent") float percent);
	 
	 
	 @Override
		@Modifying
		@Transactional
		@Query("UPDATE  SchoolAggregatedData a SET a.lastUpdatedDate = :lastUpdatedDate , a.lastUpdatedDateTime =:dateTime ")
	public void updateLastUpdatedDate(@Param("lastUpdatedDate")Date date,@Param("dateTime") Timestamp dateTime);
	 
	 
}
