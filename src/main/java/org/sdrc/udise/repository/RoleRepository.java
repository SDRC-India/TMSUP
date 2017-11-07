package org.sdrc.udise.repository;

import java.util.List;

import org.sdrc.udise.domain.Role;

/**
 * 
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public interface RoleRepository {

	List<Role> findAll();

}
