/**
 * 
 */
package org.sdrc.udise.repository;



import java.util.List;

import org.sdrc.udise.domain.MasterArea;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public interface MasterAreaRepository {

	@Transactional
	<S extends MasterArea> List<S> save(Iterable<S> area);

	List<MasterArea> findByLevel(int areaLevel);

	List<MasterArea> findByParentAreaId(int parentId);

}
