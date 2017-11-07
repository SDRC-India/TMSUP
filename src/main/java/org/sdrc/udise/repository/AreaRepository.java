/**
 * 
 */
package org.sdrc.udise.repository;

import java.util.List;

import org.sdrc.udise.domain.Area;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 * @author Azar
 *
 */
public interface AreaRepository {

	List<Area> findAll();
	
	List<Area> findByOrderByAreaIdDesc();

	/**
	 * 
	 * @param levelID
	 * @author Harsh
	 * @return List<Area> i.e All the areas whose level is less than
	 */
	List<Area> findByIsLiveTrueAndLevelLessThanOrderByLevelAsc(int levelId);

	
	List<Area> findByIsLiveTrueAndLevelLessThanOrderByLevelAscAreaNameAsc(int levelId);
	
	/**
	 * 
	 * @param areaIdOfParent
	 * @author Azar
	 * @return List<Area> i.e All the areas whose level is greater than
	 */
	List<Area> findByIsLiveTrueAndLevelInOrderByLevelAscNameAsc(int... level);
	/**
	 * 
	 * @param areaIdOfParent
	 * @author Azar
	 * @return List<Area> i.e All the child of parent.
	 */
	List<Area> findByIsLiveTrueAndParentAreaIdOrderByAreaNameAsc(int areaIdOfParent);
	
	List<Area> findByParentAreaIdOrderByAreaNameAsc(int areaIdOfParent);
	
	

	/**
	 * 
	 * @param levelId
	 * @author Azar
	 * @return List<Area> i.e All the districts
	 */

	List<Area> findByIsLiveTrueAndLevelOrderByAreaNameAsc(int levelId);
	
	List<Area> findByLevelOrderByAreaNameAsc(int levelId);

	List<Area> findByParentAreaIdAndIsLiveTrue(Integer parentId);

	Area findByAreaId(Integer areaId);

	List<Area> findByParentParentAreaIdAndIsLiveTrue(Integer parentAreaId);

	
	@Transactional
	Area save(Area area);

	List<Area> findByAreaIdIn(List<Integer> newAreaIds);

}
