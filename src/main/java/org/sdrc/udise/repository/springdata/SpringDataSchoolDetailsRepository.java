/**
 * 
 */
package org.sdrc.udise.repository.springdata;

import java.util.List;

import org.sdrc.udise.domain.SchoolDetails;
import org.sdrc.udise.repository.SchoolDetailsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@RepositoryDefinition(domainClass = SchoolDetails.class, idClass = Integer.class)
public interface SpringDataSchoolDetailsRepository extends SchoolDetailsRepository {

	@Query("select s from SchoolDetails s join s.block a join fetch s.schoolLatLongLinks where a.areaId = :blockId")
	List<SchoolDetails> findByBlockAreaIdFetchJoinLatLongLink(@Param("blockId") int blockId);

/*<<<<<<< .mine*/
	@Query("select s from SchoolDetails s join s.block a left join fetch s.schoolLatLongLinks where a.areaId = :blockId")
	List<SchoolDetails> findByBlockAreaIdFetchJoinLatLongLinkLeftJoin(@Param("blockId") int blockId);

	
/*=======*/
	@Query("select s from SchoolDetails s join s.schoolType t join s.block a join fetch s.schoolLatLongLinks where   a.areaId = :blockId and t.typeDetailId IN (:schoolTypeList)")
	List<SchoolDetails> findSecondarySchoolsByBlockAreaIdFetchJoinLatLongLink(@Param("blockId") int blockId, @Param("schoolTypeList") List<Integer> schoolTypeList);

	@Override
	@Query("SELECT s.udiseCode from SchoolDetails s where s.udiseCode !=NULL ")
	public List<String> findAllUdiseCode();
}
