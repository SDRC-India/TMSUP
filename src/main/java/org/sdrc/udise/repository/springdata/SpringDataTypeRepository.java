/**
 * 
 */
package org.sdrc.udise.repository.springdata;

import org.sdrc.udise.domain.SchoolLatLongLink;
import org.sdrc.udise.domain.Type;
import org.sdrc.udise.repository.TypeRepository;
import org.springframework.data.repository.RepositoryDefinition;



/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@RepositoryDefinition(domainClass=SchoolLatLongLink.class,idClass=Integer.class)
public interface SpringDataTypeRepository extends TypeRepository {

}
