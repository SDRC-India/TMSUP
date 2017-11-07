package org.sdrc.udise.repository.springdata;

import java.util.List;

import javax.persistence.LockModeType;

import org.sdrc.udise.domain.StudentSchoolMapping;
import org.sdrc.udise.repository.StudentSchoolMappingRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

@RepositoryDefinition(domainClass=StudentSchoolMapping.class,idClass=Integer.class)
public interface SpringDataStudentSchoolMappingRepository extends StudentSchoolMappingRepository{


	@Override
	@Query("SELECT studentMapping.fromSchoolDetails.districtId.areaId,COUNT(*) "
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.gender ='Boy' THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.gender ='Girl' THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.gender ='Third Gender' THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =13 THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =14 THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =15 THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =16 THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =17 THEN 1 END)"
			+ " FROM StudentSchoolMapping studentMapping "
			+ "WHERE studentMapping.academicYear.startYear = :startYear"
			+ " AND studentMapping.enrollType.typeDetailId =:enrollTypeMigrated"
			+ " GROUP BY studentMapping.fromSchoolDetails.districtId.areaId")
	public List<Object[]> getAggregateStudentsDataAtDistrictLevelMigrated(
			@Param("startYear")String valueOf,@Param("enrollTypeMigrated") int enrollTypeMigrated);
	
	
	
	@Override
	@Query("SELECT studentMapping.fromSchoolDetails.districtId.parentAreaId,COUNT(*) "
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.gender ='Boy' THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.gender ='Girl' THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.gender ='Third Gender' THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =13 THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =14 THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =15 THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =16 THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =17 THEN 1 END)"
			+ " FROM StudentSchoolMapping studentMapping "
			+ "WHERE studentMapping.academicYear.startYear = :startYear"
			+ " AND studentMapping.enrollType.typeDetailId =:enrollTypeMigrated"
			+ " GROUP BY studentMapping.fromSchoolDetails.districtId.parentAreaId")
	public List<Object[]> getAggregateStudentsDataAtDivisionLevelMigrated(
			@Param("startYear")String valueOf,@Param("enrollTypeMigrated") int enrollTypeMigrated);
	
	@Override
	@Query(	"SELECT studentMapping.fromSchoolDetails.block.areaId,COUNT(*) "
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.gender ='Boy' THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.gender ='Girl' THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.gender ='Third Gender' THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =13 THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =14 THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =15 THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =16 THEN 1 END)"
			+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =17 THEN 1 END)"
			+ " FROM StudentSchoolMapping studentMapping "
			+ "WHERE studentMapping.academicYear.startYear = :startYear"
			+ " AND studentMapping.enrollType.typeDetailId =:enrollTypeMigrated"
			+ " GROUP BY studentMapping.fromSchoolDetails.block.areaId")
	public List<Object[]> getAggregateStudentsDataAtBlockLevelMigrated(
			@Param("startYear")String valueOf,@Param("enrollTypeMigrated") int enrollTypeMigrated);


  @Override
   @Query("SELECT studentMapping.fromSchoolDetails.schoolId,COUNT(*) "
		+ ",COUNT(CASE WHEN studentMapping.studentDetails.gender ='Boy' THEN 1 END)"
		+ ",COUNT(CASE WHEN studentMapping.studentDetails.gender ='Girl' THEN 1 END)"
		+ ",COUNT(CASE WHEN studentMapping.studentDetails.gender ='Third Gender' THEN 1 END)"
		+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =13 THEN 1 END)"
		+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =14 THEN 1 END)"
		+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =15 THEN 1 END)"
		+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =16 THEN 1 END)"
		+ ",COUNT(CASE WHEN studentMapping.studentDetails.caste.typeDetailId =17 THEN 1 END)"
		+ " FROM StudentSchoolMapping studentMapping "
		+ "WHERE studentMapping.academicYear.startYear = :startYear"
		+ " AND studentMapping.enrollType.typeDetailId =:enrollTypeMigrated"
		+ " GROUP BY studentMapping.fromSchoolDetails.schoolId")
	public List<Object[]> getAggregateStudentsDataAtSchoolLevelMigrated(
			@Param("startYear")String valueOf,@Param("enrollTypeMigrated") int enrollTypeMigrated);
}
