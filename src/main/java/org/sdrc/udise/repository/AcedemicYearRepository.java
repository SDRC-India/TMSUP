package org.sdrc.udise.repository;

import java.util.List;

import org.sdrc.udise.domain.AcademicYear;

public interface AcedemicYearRepository {

	AcademicYear findByStartYear(String startYear);
	
	List<AcademicYear> findAll();
}
