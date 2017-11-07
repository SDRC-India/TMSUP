package org.sdrc.udise.repository.springdata;

import org.sdrc.udise.domain.AcademicYear;
import org.sdrc.udise.repository.AcedemicYearRepository;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass=AcademicYear.class,idClass=Integer.class)
public interface SpringDataAcedemicYearRepository extends AcedemicYearRepository{

}
