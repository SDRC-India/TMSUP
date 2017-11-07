package org.sdrc.udise.repository;

import java.util.List;

import org.sdrc.udise.domain.TypeDetail;

public interface TypeDetailsRepository {

	List<TypeDetail> findAll();

	List<TypeDetail> findByTypeDetailIdIn(
			List<Integer> upperPrimarySchoolTypeIds);

	List<TypeDetail> findByTypeTypeId(int typeId);

	List<TypeDetail> findByTypeTypeIdOrderByTypeDetailIdAsc(int typeId);

}
