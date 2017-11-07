package org.sdrc.udise.service;

import java.util.List;

import org.sdrc.udise.domain.StudentsDetails;
import org.sdrc.udise.model.ValueObject;

/**
 * @author Azaruddin (azaruddin@sdrc.co.in)
 *
 */

public interface StudentService {

	public StudentsDetails saveStudent(String studentsName, String fatherName,
			String motherName, String gender, int schoolId, int casteId,
			String srRegistrationNo);

	public boolean admitStudent(int fromSchool, int toSchool,
			List<Integer> students, int enrollType);

	public boolean admitStudentToOtherState(int fromSchool,
			List<Integer> students, int stateId, int districtId,String schoolName);

	public boolean migrateStudent(List<Integer> students, int stateId,
			int districtId);

	/**
	 * @author Harsh Pratyush(harsh@sdrc.co.in)
	 * @return
	 */
	public List<ValueObject> getStudentsCaste();
}
