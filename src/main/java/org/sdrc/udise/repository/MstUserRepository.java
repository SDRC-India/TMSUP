/**
 * 
 */
package org.sdrc.udise.repository;

import org.sdrc.udise.domain.MSTUser;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public interface MstUserRepository {

	@Transactional
	MSTUser save(MSTUser mstUser);

	MSTUser findByUserNameAndPassword(String username, String password);

	MSTUser findByUserId(Integer userId);

}
