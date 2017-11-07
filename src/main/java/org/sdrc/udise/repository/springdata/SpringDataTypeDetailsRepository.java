/**
 * 
 */
package org.sdrc.udise.repository.springdata;

import org.sdrc.udise.domain.TypeDetail;
import org.sdrc.udise.repository.TypeDetailsRepository;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@RepositoryDefinition(domainClass=TypeDetail.class,idClass=Integer.class)
public interface SpringDataTypeDetailsRepository extends TypeDetailsRepository {

}
