/**
 * 
 */
package org.sdrc.udise.repository.springdata;

import org.sdrc.udise.domain.UserAreaMapping;
import org.sdrc.udise.repository.UserAreaMappingRepository;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@RepositoryDefinition(domainClass=UserAreaMapping.class,idClass=Integer.class)
public interface SpringDataUserAreaMappingRepository extends UserAreaMappingRepository{

}
