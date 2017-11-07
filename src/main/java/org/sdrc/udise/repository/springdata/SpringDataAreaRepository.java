/**
 * 
 */
package org.sdrc.udise.repository.springdata;
import java.util.List;

import org.sdrc.udise.domain.Area;
import org.sdrc.udise.repository.AreaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@RepositoryDefinition(domainClass=Area.class,idClass=Integer.class)
public interface SpringDataAreaRepository extends AreaRepository {

	@Override
	@Query("SELECT  area FROM Area area,Area parentarea "
			+ " WHERE parentarea.parentAreaId=:parentAreaId "
			+ " AND area.parentAreaId=parentarea.areaId "
			+ " AND area.isLive = TRUE "
			+ " ORDER BY area.areaName ASC ")
	public List<Area> findByParentParentAreaIdAndIsLiveTrue(@Param("parentAreaId")Integer parentAreaId);

	/**
	 * 
	 * @param areaIdOfParent
	 * @author Azar
	 * @return List<Area> i.e All the areas whose level is greater than
	 */
	@Query("SELECT  area FROM Area area "
			+ " WHERE area.level in :levels AND area.isLive = TRUE "
			+ " ORDER BY area.areaName ASC")
	List<Area> findByIsLiveTrueAndLevelInOrderByLevelAscNameAsc(@Param("levels")int... level);
	
}
