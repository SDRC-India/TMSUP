/**
 * 
 */
package org.sdrc.udise.repository.springdata;

import org.sdrc.udise.domain.MasterArea;
import org.sdrc.udise.repository.MasterAreaRepository;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@RepositoryDefinition(domainClass=MasterArea.class,idClass=Integer.class)
public interface SpringDataMasterAreaRepository extends MasterAreaRepository {

}
