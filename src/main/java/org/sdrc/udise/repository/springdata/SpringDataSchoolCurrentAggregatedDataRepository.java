package org.sdrc.udise.repository.springdata;

import java.util.List;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.sdrc.udise.domain.SchoolCurrentAggregatedData;
import org.sdrc.udise.repository.SchoolCurrentAggregatedDataRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

@RepositoryDefinition(domainClass = SchoolCurrentAggregatedData.class, idClass = Integer.class)
public interface SpringDataSchoolCurrentAggregatedDataRepository extends
		SchoolCurrentAggregatedDataRepository {

	@Query("select ag from SchoolCurrentAggregatedData ag join ag.academicYearId ay "
			+ " join fetch ag.school asch join asch.block area join fetch asch.schoolLatLongLinks "
			+ "  WHERE ay.startYear =:startYear and area.areaId =:areaId ")
	List<SchoolCurrentAggregatedData> findByAcademicYearIdStartYearAndSchoolBlockAreaId(
			@Param("startYear") String startYear, @Param("areaId") int areaId);


	 @Override
		@Query("select ag from SchoolCurrentAggregatedData ag join ag.academicYearId ay "
				+ " join fetch ag.school asch join asch.districtId area join fetch asch.schoolLatLongLinks "
				+ "  WHERE ay.startYear =:startYear and area.areaId =:areaId ")
	public List<SchoolCurrentAggregatedData> findByAcademicYearIdStartYearAndSchoolDistrictIdAreaId(
			String startYear, int districtId);
	 
	 
	 @Transactional
	 @Lock(LockModeType.PESSIMISTIC_WRITE)
	 SchoolCurrentAggregatedData findBySchoolSchoolIdAndAcademicYearIdStartYear(int schoolId, String acedemicStartYear);
}
