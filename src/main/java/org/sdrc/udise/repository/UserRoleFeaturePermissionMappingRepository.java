/**
 * 
 */
package org.sdrc.udise.repository;

import java.util.List;

import org.sdrc.udise.domain.UserRoleFeaturePermissionMapping;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public interface UserRoleFeaturePermissionMappingRepository {

	<S extends UserRoleFeaturePermissionMapping> List<S> save(Iterable<S> userRoleFeaturePermissionMappings);

	void save(UserRoleFeaturePermissionMapping featurePermissionMapping);
}
