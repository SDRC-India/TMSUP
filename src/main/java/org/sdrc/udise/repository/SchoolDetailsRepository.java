package org.sdrc.udise.repository;

import java.util.List;

import org.sdrc.udise.domain.SchoolDetails;

/**
 * 
 * @author Azar
 *
 */

public interface SchoolDetailsRepository {

	SchoolDetails findBySchoolId(int id);

	List<SchoolDetails> findByBlockAreaId(int blockId);

	List<SchoolDetails> findByBlockAreaIdFetchJoinLatLongLink(int blockId);



	List<SchoolDetails> findByBlockAreaIdFetchJoinLatLongLinkLeftJoin(
			int blockId);

	List<SchoolDetails> findSecondarySchoolsByBlockAreaIdFetchJoinLatLongLink(int blockId,
			List<Integer> schoolTypeList);


	SchoolDetails save(SchoolDetails school);

	
	SchoolDetails findBySchoolNameIgnoreCaseAndBlockAreaId(String schoolName,
			int blockId);

	<S extends SchoolDetails> List<S> save(Iterable<S> schoolDetails);

	List<SchoolDetails> findAll();

	List<SchoolDetails> findByDistrictIdIsLiveTrue();

	List<SchoolDetails> findByDistrictIdIsLiveTrueAndSchoolTypeTypeDetailIdIsIn(
			List<Integer> upperPrimarySchoolTypeIds);
	
	List<String> findAllUdiseCode();

	List<SchoolDetails> findByBlockAreaIdInAndSchoolTypeTypeDetailIdIn(
			List<Integer> newAreaIds, List<Integer> upperPrimarySchoolTypeIds);

	List<SchoolDetails> findByUdiseCodeIsNotNull();

}
