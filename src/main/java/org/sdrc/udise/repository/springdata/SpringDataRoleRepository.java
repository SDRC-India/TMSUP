/**
 * 
 */
package org.sdrc.udise.repository.springdata;

import org.sdrc.udise.domain.Role;
import org.sdrc.udise.repository.RoleRepository;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@RepositoryDefinition(domainClass=Role.class,idClass=Integer.class)
public interface SpringDataRoleRepository extends RoleRepository {

}
