package org.sdrc.udise.repository;

import java.util.List;

import org.sdrc.udise.domain.SchoolCurrentAggregatedData;

/**
 * 
 * @author Azar
 *
 */

public interface SchoolCurrentAggregatedDataRepository {

	List<SchoolCurrentAggregatedData> findByAcademicYearIdStartYearAndSchoolBlockAreaId(String startYear, int areaId);

	SchoolCurrentAggregatedData findBySchoolSchoolIdAndAcademicYearIdStartYear(int schoolId, String acedemicStartYear);

	void save(SchoolCurrentAggregatedData schoolCurrentAggregatedData);

	List<SchoolCurrentAggregatedData> findBySchoolBlockAreaIdAndAcademicYearIdStartYearOrderBySchoolSchoolNameAsc(
			int blockId, String startYear);
	
	<S extends SchoolCurrentAggregatedData> List<S> save(Iterable<S> schoolCurrentAggregatedDatas);

	List<SchoolCurrentAggregatedData> findByAcademicYearIdStartYearAndSchoolDistrictIdAreaId(
			String startYear, int districtId);

	List<SchoolCurrentAggregatedData> findByAcademicYearIdStartYear(
			String valueOf);
	
}
