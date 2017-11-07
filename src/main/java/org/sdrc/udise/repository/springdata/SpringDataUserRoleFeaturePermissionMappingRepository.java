/**
 * 
 */
package org.sdrc.udise.repository.springdata;

import org.sdrc.udise.domain.UserRoleFeaturePermissionMapping;
import org.sdrc.udise.repository.UserRoleFeaturePermissionMappingRepository;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@RepositoryDefinition(domainClass=UserRoleFeaturePermissionMapping.class,idClass=Integer.class)
public interface SpringDataUserRoleFeaturePermissionMappingRepository extends
		UserRoleFeaturePermissionMappingRepository {

}
