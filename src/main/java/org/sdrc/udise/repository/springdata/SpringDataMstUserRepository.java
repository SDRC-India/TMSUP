package org.sdrc.udise.repository.springdata;

import org.sdrc.udise.domain.MSTUser;
import org.sdrc.udise.repository.MstUserRepository;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * 
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@RepositoryDefinition(domainClass=MSTUser.class,idClass=Integer.class)
public interface SpringDataMstUserRepository extends MstUserRepository{

}
