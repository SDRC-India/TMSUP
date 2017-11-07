package org.sdrc.udise.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.sdrc.udise.domain.AcademicYear;
import org.sdrc.udise.domain.MasterArea;
import org.sdrc.udise.domain.SchoolCurrentAggregatedData;
import org.sdrc.udise.domain.SchoolDetails;
import org.sdrc.udise.domain.StudentSchoolMapping;
import org.sdrc.udise.domain.StudentsDetails;
import org.sdrc.udise.domain.TypeDetail;
import org.sdrc.udise.model.UserModel;
import org.sdrc.udise.model.ValueObject;
import org.sdrc.udise.repository.AcedemicYearRepository;
import org.sdrc.udise.repository.SchoolCurrentAggregatedDataRepository;
import org.sdrc.udise.repository.StudentDetailsRepository;
import org.sdrc.udise.repository.StudentSchoolMappingRepository;
import org.sdrc.udise.repository.TypeDetailsRepository;
import org.sdrc.udise.util.Constants;
import org.sdrc.udise.util.EnrollmentType;
import org.sdrc.udise.util.StateManager;
import org.sdrc.udise.util.StudentAlreadyAdmittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Azaruddin (azaruddin@sdrc.co.in)
 *
 */

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDetailsRepository studentDetailsRepository;

	@Autowired
	private AcedemicYearRepository acedemicYearRepository;

	@Autowired
	private StudentSchoolMappingRepository studentSchoolMappingRepository;

	@Autowired
	private SchoolCurrentAggregatedDataRepository schoolCurrentAggregatedDataRepository;

	@Autowired
	private TypeDetailsRepository typeDetailsRepository;

	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;

	@Autowired
	private StateManager stateManager;

	/**
	 * When new student is saved following actions are taking place : 1)A new
	 * record is inserted into student table. 2)A record is updated in
	 * school_aggregated_data table according to the current academic year.i.e
	 * leftout_students column is incremented by 1 if one student is added.
	 * 3)Record is inserted into student school mapping.
	 * 
	 * @throws A
	 *             runtimeException is thrown if no record found for school for
	 *             current academic year.
	 */

	@Override
	@Transactional
	public StudentsDetails saveStudent(String studentsName, String fatherName, String motherName, String gender, int schoolId, int casteId, String srRegistrationNo) {


		UserModel userModel = (UserModel) stateManager.getValue(Constants.Web.USER_PRINCIPAL);
		
		LocalDate localdate = new LocalDate();
		int year = localdate.getYear();
		int monthOfYear = localdate.getMonthOfYear();

		int currAcYear = year;
		if (monthOfYear < 4) {
			currAcYear = currAcYear - 1;
		}
		
		SchoolCurrentAggregatedData data = schoolCurrentAggregatedDataRepository.findBySchoolSchoolIdAndAcademicYearIdStartYear(schoolId, String.valueOf(currAcYear));
		if (data == null) {
			throw new RuntimeException("No record found for school for current acedemic year in school_aggregate_table.");
		}
		
		StudentsDetails studentDetail = studentDetailsRepository.findBySrRegistrationNumberIgnoreCaseAndSchoolSchoolId(srRegistrationNo.trim(), schoolId);
		if (studentDetail == null) {

		
		
			
		

			AcademicYear acedemicYear = acedemicYearRepository.findByStartYear(String.valueOf(currAcYear));
			SchoolDetails school = new SchoolDetails();
			school.setSchoolId(schoolId);

			StudentsDetails student = new StudentsDetails();
			// student.setAddharCardNumber(aadharNumber);
			student.setStudentName(studentsName);
			student.setFathersName(fatherName);
			student.setMothersName(motherName);
			student.setGender(gender);
			student.setSubmitted(false);
			student.setSchool(school);
			// student.setDateOfBirth(new java.sql.Date(dob.getTime()));
			student.setAcedemicYear(acedemicYear);
			student.setCaste(new TypeDetail(casteId));
			student.setSrRegistrationNumber(srRegistrationNo);
			student.setCreateDateTime(new Timestamp(new Date().getTime()));
			student.setCreatedBy(userModel.getName());
			student = studentDetailsRepository.save(student);

			StudentSchoolMapping studentSchoolMapping = new StudentSchoolMapping();
			studentSchoolMapping.setAcademicYear(acedemicYear);
			studentSchoolMapping.setStudentDetails(student);
			studentSchoolMapping.setLatest(true);
			studentSchoolMapping.setFromSchoolDetails(school);
			studentSchoolMapping.setCreatedBy(userModel.getName());
			studentSchoolMapping.setCreateDateTime(new java.sql.Timestamp(new Date().getTime()));
			studentSchoolMappingRepository.save(studentSchoolMapping);

		
			data.setLefoutStudents(data.getLefoutStudents() + 1);

			return student;
		} else {
			return null;
		}
	}

	/**
	 * @param fromSchool
	 *            Id of school from which students will be admitted from upper
	 *            primary to secondary
	 * @param toSchool
	 *            Id of school to which students will be admitted to secondary
	 * @param studentIds
	 *            List of ids of students that are to be admitted to secondary
	 * 
	 * @return true if students are admitted to secondary successfully
	 * @throws RuntimeException
	 *             if any exception occurs
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean admitStudent(int fromSchool, int toSchool, List<Integer> studentIds, int enrollTypeInt) {
		UserModel userModel = (UserModel) stateManager.getValue(Constants.Web.USER_PRINCIPAL);
		String academicYear = AcademicYear.getCurrentAcademicYear();

		List<StudentSchoolMapping> mappings = new ArrayList<>();

		
		//reading current aggregate table with pessimistic lock that generates 'select .. for update' query type.
		SchoolCurrentAggregatedData aggreData = schoolCurrentAggregatedDataRepository.findBySchoolSchoolIdAndAcademicYearIdStartYear(fromSchool, academicYear);


		for (Integer studentId : studentIds) {
			StudentsDetails student = studentDetailsRepository.findByStudentId(studentId);
			if (student.isSubmitted()) {
				throw new StudentAlreadyAdmittedException("Failed to admit student as student was already admitted to upper primary.Student Id :" + studentId);
			}
			student.setSubmitted(true);
			student.setUpdateDateTime(new java.sql.Timestamp(new Date().getTime()));
			student.setEnrolledBy(userModel.getUsername());
			student.setUpdatedBy(userModel.getUsername());

			SchoolDetails enrolledSchool = new SchoolDetails();
			enrolledSchool.setSchoolId(toSchool);
		

			TypeDetail enrollType = new TypeDetail();
			enrollType.setTypeDetailId(enrollTypeInt);
			

			StudentSchoolMapping studentSchoolMapping = studentSchoolMappingRepository.findByStudentDetailsStudentId(studentId);
			if (studentSchoolMapping == null) {
				throw new RuntimeException("No mapping found for student in 'student_school_mapping table' for student id :" + studentId);
			}

			// created proxy object.checking if this method works.
			SchoolDetails toSchoolDomain = new SchoolDetails();
			toSchoolDomain.setSchoolId(toSchool);

			// updating student school mapping
			studentSchoolMapping.setLinkedSchoolDetails(toSchoolDomain);
			studentSchoolMapping.setLatest(true);

			studentSchoolMapping.setEnrollType(enrollType);
			studentSchoolMapping.setUpdateDateTime(new java.sql.Timestamp(new Date().getTime()));

			mappings.add(studentSchoolMapping);
		}

		for (StudentSchoolMapping studentSchoolMapping : mappings) {
			studentSchoolMappingRepository.save(studentSchoolMapping);
		}
		
	
		aggreData.setAdmittedStudents(aggreData.getAdmittedStudents() + studentIds.size());
		aggreData.setLefoutStudents(aggreData.getLefoutStudents() - studentIds.size());

		schoolCurrentAggregatedDataRepository.save(aggreData);

		// Adding extra datas

		return true;
	}

	@Override
	@Transactional
	public boolean admitStudentToOtherState(int fromSchool, List<Integer> studentIds, int stateId, int districtId, String schoolName) {
		UserModel userModel = (UserModel) stateManager.getValue(Constants.Web.USER_PRINCIPAL);
		String academicYear = AcademicYear.getCurrentAcademicYear();
		
		//reading current aggregate table with pessimistic lock that generates 'select .. for update' query type. to allow concurrent access
		SchoolCurrentAggregatedData aggreData = schoolCurrentAggregatedDataRepository.findBySchoolSchoolIdAndAcademicYearIdStartYear(fromSchool, academicYear);

		List<StudentSchoolMapping> mappings = new ArrayList<>();
		for (Integer studentId : studentIds) {
			StudentsDetails student = studentDetailsRepository.findByStudentId(studentId);
			if (student.isSubmitted()) {
				throw new StudentAlreadyAdmittedException("Failed to admit student as student was already admitted to upper primary.Student Id :" + studentId);
			}
			student.setSubmitted(true);
			student.setUpdateDateTime(new java.sql.Timestamp(new Date().getTime()));
			student.setEnrolledBy(userModel.getUsername());
			student.setUpdatedBy(userModel.getUsername());

			MasterArea state = new MasterArea();
			state.setAreaId(stateId);

			MasterArea district = new MasterArea();
			district.setAreaId(districtId);

			TypeDetail enrollType = new TypeDetail();
			enrollType.setTypeDetailId(EnrollmentType.OTHER_STATE.getValue());

			StudentSchoolMapping studentSchoolMapping = studentSchoolMappingRepository.findByStudentDetailsStudentId(studentId);
			if (studentSchoolMapping == null) {
				throw new RuntimeException("No mapping found for student in 'student_school_mapping table' for student id :" + studentId);
			}

			// updating student school mapping

			studentSchoolMapping.setLatest(true);
			studentSchoolMapping.setOtherDistrict(district);
			studentSchoolMapping.setOtherState(state);
			studentSchoolMapping.setEnrollType(enrollType);

			studentSchoolMapping.setOtherStateSchoolName(schoolName);
			studentSchoolMapping.setUpdateDateTime(new java.sql.Timestamp(new Date().getTime()));
			mappings.add(studentSchoolMapping);
		}

		for (StudentSchoolMapping studentSchoolMapping : mappings) {
			// updating student school mapping table
			studentSchoolMappingRepository.save(studentSchoolMapping);
		}
		
	
		
		aggreData.setAdmittedStudents(aggreData.getAdmittedStudents() + studentIds.size());
		aggreData.setLefoutStudents(aggreData.getLefoutStudents() - studentIds.size());
		
		schoolCurrentAggregatedDataRepository.save(aggreData);

		

		return true;
	}

	@Override
	public List<ValueObject> getStudentsCaste() {
		List<ValueObject> studentCastes = new ArrayList<ValueObject>();
		int typeId = Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.CASTE_TYPE_ID, null, null));
		List<TypeDetail> casteDetails = typeDetailsRepository.findByTypeTypeIdOrderByTypeDetailIdAsc(typeId);

		for (TypeDetail typeDetail : casteDetails) {
			ValueObject valueObject = new ValueObject();
			valueObject.setKey(String.valueOf(typeDetail.getTypeDetailId()));
			valueObject.setValue(typeDetail.getTypeDetail());
			studentCastes.add(valueObject);
		}
		return studentCastes;
	}

	@Override
	@Transactional
	public boolean migrateStudent(List<Integer> students, int stateId, int districtId) {
		UserModel userModel = (UserModel) stateManager.getValue(Constants.Web.USER_PRINCIPAL);
		for (Integer studentId : students) {
			StudentsDetails student = studentDetailsRepository.findByStudentId(studentId);
			student.setSubmitted(true);
			student.setUpdateDateTime(new java.sql.Timestamp(new Date().getTime()));
			student.setEnrolledBy(userModel.getUsername());
			student.setUpdatedBy(userModel.getUsername());
			StudentSchoolMapping studentSchoolMapping = student.getStudentSchoolMapping() != null ? student.getStudentSchoolMapping().get(0) : null;

			if (studentSchoolMapping == null) {
				throw new RuntimeException("No mapping found for student in 'student_school_mapping table' for student id :" + studentId);
			}

			MasterArea state = new MasterArea();
			state.setAreaId(stateId);

			MasterArea district = new MasterArea();
			district.setAreaId(districtId);

			TypeDetail enrollType = new TypeDetail();
			enrollType.setTypeDetailId(EnrollmentType.MIGRATION.getValue());

			studentSchoolMapping.setLatest(true);
			studentSchoolMapping.setOtherDistrict(district);
			studentSchoolMapping.setOtherState(state);
			studentSchoolMapping.setEnrollType(enrollType);
			studentSchoolMapping.setUpdateDateTime(new java.sql.Timestamp(new Date().getTime()));
		}

		return true;
	}

}
