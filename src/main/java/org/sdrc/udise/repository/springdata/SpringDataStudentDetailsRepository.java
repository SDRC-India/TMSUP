package org.sdrc.udise.repository.springdata;

import java.util.List;

import org.sdrc.udise.domain.StudentsDetails;
import org.sdrc.udise.repository.StudentDetailsRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

/**
 * 
 * @author Azar
 *
 */

@RepositoryDefinition(domainClass = StudentsDetails.class, idClass = Integer.class)
public interface SpringDataStudentDetailsRepository extends StudentDetailsRepository{

	@EntityGraph(value="graph.StudentsDetails.deoViewStudentsBySchool",type=EntityGraphType.FETCH)
	public List<StudentsDetails> findBySchoolSchoolIdAndAcedemicYearStartYearOrderByIsSubmittedAscStudentIdDesc(
			int schoolId, String valueOf);

	@Override
	@Query("SELECT student.school.block.areaId, "
			+ "COUNT(CASE WHEN student.isSubmitted = TRUE  THEN 1 END),"
			+ "COUNT(CASE WHEN student.isSubmitted=FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Boy' and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Girl' and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Third Gender' and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Boy' and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Girl' and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Third Gender' and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =13 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =14 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =15 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =16 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =17 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =13 and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =14 and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =15 and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =16 and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =17 and student.isSubmitted = FALSE THEN 1 END)"
			+ " FROM StudentsDetails student"
			+ " WHERE student.acedemicYear.startYear = :academicYear "
			+ " GROUP BY student.school.block.areaId")
	public List<Object[]> getAggregateStudentsDataAtBlockLevel(@Param("academicYear")String academicYear);
	
	
	@Override
	@Query("SELECT student.school.districtId.areaId,"
			+ "COUNT(CASE WHEN student.isSubmitted = TRUE  THEN 1 END),"
			+ "COUNT(CASE WHEN student.isSubmitted=FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Boy' and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Girl' and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Third Gender' and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Boy' and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Girl' and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Third Gender' and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =13 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =14 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =15 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =16 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =17 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =13 and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =14 and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =15 and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =16 and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =17 and student.isSubmitted = FALSE THEN 1 END)"
			+ " FROM StudentsDetails student"
			+ " WHERE student.acedemicYear.startYear = :academicYear "
			+ " GROUP BY student.school.districtId.areaId")
	public List<Object[]> getAggregateStudentsDataAtDistrictLevel(@Param("academicYear") String academicYear);
	
	
	@Override
	@Query("SELECT student.school.districtId.parentAreaId,"
			+ "COUNT(CASE WHEN student.isSubmitted = TRUE  THEN 1 END),"
			+ "COUNT(CASE WHEN student.isSubmitted=FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Boy' and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Girl' and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Third Gender' and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Boy' and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Girl' and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Third Gender' and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =13 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =14 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =15 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =16 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =17 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =13 and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =14 and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =15 and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =16 and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =17 and student.isSubmitted = FALSE THEN 1 END)"
			+ " FROM StudentsDetails student"
			+ " WHERE student.acedemicYear.startYear = :academicYear "
			+ " GROUP BY student.school.districtId.parentAreaId")
	public List<Object[]> getAggregateStudentsDataAtDivisionLevel(@Param("academicYear") String academicYear);
	
	@Override
	//@Query(value="SELECT sd.school_name,sd.udise_code,st.student_name,st.sr_regd_no,st.fathers_name,st.mothers_name,st.geneder,div.area_name AS division_name,district.area_name AS district_name,block.area_name AS block_name, st.issubmitted, mappings.enroll_type_id_fk, mappings.other_state_school_name FROM student_details st INNER JOIN academic_year ac on st.acedemic_year_id_fk=ac.academic_year_id LEFT OUTER JOIN school_details sd ON sd.schoolid = st.school_id_fk LEFT OUTER JOIN area block ON block.areaid = sd.block_id_fk  LEFT OUTER JOIN area district ON district.areaid = sd.district_id_fk LEFT OUTER JOIN area div ON district.parent_area_id = div.areaid LEFT OUTER JOIN student_school_mapping mappings ON mappings.student_id_fk = st.student_id WHERE ac.start_year = :startYear",nativeQuery=true)
	@Query(value="SELECT sd.school_name,sd.udise_code,st.student_name,st.sr_regd_no,st.fathers_name,st.mothers_name,st.geneder,div.area_name AS division_name,district.area_name AS district_name,block.area_name AS block_name, st.issubmitted, mappings.enroll_type_id_fk, mappings.other_state_school_name,link_school.school_name as linked_school_name,mas.area_name as migrated_state,mad.area_name as migrated_district,typ.type_detail,dist_area.area_name as link_school_dist,link_school.udise_code AS link_udise_code FROM student_details st INNER JOIN academic_year ac on st.acedemic_year_id_fk=ac.academic_year_id LEFT OUTER JOIN school_details sd ON sd.schoolid = st.school_id_fk LEFT OUTER JOIN area block ON block.areaid = sd.block_id_fk  LEFT OUTER JOIN area district ON district.areaid = sd.district_id_fk LEFT OUTER JOIN area div ON district.parent_area_id = div.areaid LEFT OUTER JOIN student_school_mapping mappings ON mappings.student_id_fk = st.student_id  LEFT OUTER JOIN school_details link_school ON mappings.linked_school_id_fk = link_school.schoolid LEFT OUTER JOIN area dist_area ON dist_area.areaid = link_school.district_id_fk LEFT OUTER JOIN master_area mas ON mappings.other_state_id_fk=mas.master_area_id LEFT OUTER JOIN master_area mad ON mappings.other_district_id_fk=mad.master_area_id LEFT OUTER JOIN type_detail typ ON typ.typedetailid=st.caste_id_fk  WHERE ac.start_year = :startYear  order by division_name,district_name,block_name,sd.school_name,st.student_name asc",nativeQuery=true)
	public List<Object[]> findByAcedemicYearStartYearAndSchoolForAllDivisions(@Param("startYear") String startYear);

	@Override
	//@Query(value="SELECT sd.school_name,sd.udise_code,st.student_name,st.sr_regd_no,st.fathers_name,st.mothers_name,st.geneder,div.area_name AS division_name,district.area_name AS district_name,block.area_name AS block_name, st.issubmitted, mappings.enroll_type_id_fk, mappings.other_state_school_name FROM student_details st INNER JOIN academic_year ac on st.acedemic_year_id_fk=ac.academic_year_id LEFT OUTER JOIN school_details sd ON sd.schoolid = st.school_id_fk LEFT OUTER JOIN area block ON block.areaid = sd.block_id_fk  LEFT OUTER JOIN area district ON district.areaid = sd.district_id_fk LEFT OUTER JOIN area div ON district.parent_area_id = div.areaid LEFT OUTER JOIN student_school_mapping mappings ON mappings.student_id_fk = st.student_id WHERE ac.start_year = :startYear and div.areaid = :division",nativeQuery=true)
	@Query(value="SELECT sd.school_name,sd.udise_code,st.student_name,st.sr_regd_no,st.fathers_name,st.mothers_name,st.geneder,div.area_name AS division_name,district.area_name AS district_name,block.area_name AS block_name, st.issubmitted, mappings.enroll_type_id_fk, mappings.other_state_school_name,link_school.school_name as linked_school_name,mas.area_name as migrated_state,mad.area_name as migrated_district,typ.type_detail,dist_area.area_name as link_school_dist,link_school.udise_code AS link_udise_code FROM student_details st INNER JOIN academic_year ac on st.acedemic_year_id_fk=ac.academic_year_id LEFT OUTER JOIN school_details sd ON sd.schoolid = st.school_id_fk LEFT OUTER JOIN area block ON block.areaid = sd.block_id_fk  LEFT OUTER JOIN area district ON district.areaid = sd.district_id_fk LEFT OUTER JOIN area div ON district.parent_area_id = div.areaid LEFT OUTER JOIN student_school_mapping mappings ON mappings.student_id_fk = st.student_id  LEFT OUTER JOIN school_details link_school ON mappings.linked_school_id_fk = link_school.schoolid LEFT OUTER JOIN area dist_area ON dist_area.areaid = link_school.district_id_fk LEFT OUTER JOIN master_area mas ON mappings.other_state_id_fk=mas.master_area_id LEFT OUTER JOIN master_area mad ON mappings.other_district_id_fk=mad.master_area_id LEFT OUTER JOIN type_detail typ ON typ.typedetailid=st.caste_id_fk  WHERE ac.start_year = :startYear and div.areaid = :division order by division_name,district_name,block_name,sd.school_name,st.student_name asc",nativeQuery=true)
	public List<Object[]> findByAcedemicYearStartYearAndSchoolDivisionLevel(@Param("startYear") String startYear,@Param("division") int division);

	@Override
	//@Query(value="SELECT sd.school_name,sd.udise_code,st.student_name,st.sr_regd_no,st.fathers_name,st.mothers_name,st.geneder,div.area_name AS division_name,district.area_name AS district_name,block.area_name AS block_name, st.issubmitted, mappings.enroll_type_id_fk, mappings.other_state_school_name FROM student_details st INNER JOIN academic_year ac on st.acedemic_year_id_fk=ac.academic_year_id LEFT OUTER JOIN school_details sd ON sd.schoolid = st.school_id_fk LEFT OUTER JOIN area block ON block.areaid = sd.block_id_fk  LEFT OUTER JOIN area district ON district.areaid = sd.district_id_fk LEFT OUTER JOIN area div ON district.parent_area_id = div.areaid LEFT OUTER JOIN student_school_mapping mappings ON mappings.student_id_fk = st.student_id WHERE ac.start_year = :startYear and district.areaid = :district",nativeQuery=true)
	@Query(value="SELECT sd.school_name,sd.udise_code,st.student_name,st.sr_regd_no,st.fathers_name,st.mothers_name,st.geneder,div.area_name AS division_name,district.area_name AS district_name,block.area_name AS block_name, st.issubmitted, mappings.enroll_type_id_fk, mappings.other_state_school_name,link_school.school_name as linked_school_name,mas.area_name as migrated_state,mad.area_name as migrated_district,typ.type_detail,dist_area.area_name as link_school_dist,link_school.udise_code AS link_udise_code FROM student_details st INNER JOIN academic_year ac on st.acedemic_year_id_fk=ac.academic_year_id LEFT OUTER JOIN school_details sd ON sd.schoolid = st.school_id_fk LEFT OUTER JOIN area block ON block.areaid = sd.block_id_fk  LEFT OUTER JOIN area district ON district.areaid = sd.district_id_fk LEFT OUTER JOIN area div ON district.parent_area_id = div.areaid LEFT OUTER JOIN student_school_mapping mappings ON mappings.student_id_fk = st.student_id  LEFT OUTER JOIN school_details link_school ON mappings.linked_school_id_fk = link_school.schoolid LEFT OUTER JOIN area dist_area ON dist_area.areaid = link_school.district_id_fk LEFT OUTER JOIN master_area mas ON mappings.other_state_id_fk=mas.master_area_id LEFT OUTER JOIN master_area mad ON mappings.other_district_id_fk=mad.master_area_id LEFT OUTER JOIN type_detail typ ON typ.typedetailid=st.caste_id_fk  WHERE ac.start_year = :startYear and district.areaid = :district order by division_name,district_name,block_name,sd.school_name,st.student_name asc",nativeQuery=true)
	public List<Object[]> findByAcedemicYearStartYearAndSchoolDistrictLevel(@Param("startYear") String startYear,@Param("district") int district);
	
	@Override
	//@Query(value="SELECT sd.school_name,sd.udise_code,st.student_name,st.sr_regd_no,st.fathers_name,st.mothers_name,st.geneder,div.area_name AS division_name,district.area_name AS district_name,block.area_name AS block_name, st.issubmitted, mappings.enroll_type_id_fk, mappings.other_state_school_name FROM student_details st INNER JOIN academic_year ac on st.acedemic_year_id_fk=ac.academic_year_id LEFT OUTER JOIN school_details sd ON sd.schoolid = st.school_id_fk LEFT OUTER JOIN area block ON block.areaid = sd.block_id_fk  LEFT OUTER JOIN area district ON district.areaid = sd.district_id_fk LEFT OUTER JOIN area div ON district.parent_area_id = div.areaid LEFT OUTER JOIN student_school_mapping mappings ON mappings.student_id_fk = st.student_id WHERE ac.start_year = :startYear and block.areaid = :block",nativeQuery=true)
	@Query(value="SELECT sd.school_name,sd.udise_code,st.student_name,st.sr_regd_no,st.fathers_name,st.mothers_name,st.geneder,div.area_name AS division_name,district.area_name AS district_name,block.area_name AS block_name, st.issubmitted, mappings.enroll_type_id_fk, mappings.other_state_school_name,link_school.school_name as linked_school_name,mas.area_name as migrated_state,mad.area_name as migrated_district,typ.type_detail,dist_area.area_name as link_school_dist,link_school.udise_code AS link_udise_code FROM student_details st INNER JOIN academic_year ac on st.acedemic_year_id_fk=ac.academic_year_id LEFT OUTER JOIN school_details sd ON sd.schoolid = st.school_id_fk LEFT OUTER JOIN area block ON block.areaid = sd.block_id_fk  LEFT OUTER JOIN area district ON district.areaid = sd.district_id_fk LEFT OUTER JOIN area div ON district.parent_area_id = div.areaid LEFT OUTER JOIN student_school_mapping mappings ON mappings.student_id_fk = st.student_id LEFT OUTER JOIN school_details link_school ON mappings.linked_school_id_fk = link_school.schoolid LEFT OUTER JOIN area dist_area ON dist_area.areaid = link_school.district_id_fk LEFT OUTER JOIN master_area mas ON mappings.other_state_id_fk=mas.master_area_id LEFT OUTER JOIN master_area mad ON mappings.other_district_id_fk=mad.master_area_id LEFT OUTER JOIN type_detail typ ON typ.typedetailid=st.caste_id_fk  WHERE ac.start_year = :startYear and block.areaid = :block order by division_name,district_name,block_name,sd.school_name,st.student_name asc",nativeQuery=true)
	public List<Object[]> findByAcedemicYearStartYearAndSchoolBlockLevel(@Param("startYear") String startYear,@Param("block") int block);
	
	
	
	
	
	@Override
	@Query("SELECT student.school.schoolId,"
			+ "COUNT(CASE WHEN student.isSubmitted = TRUE  THEN 1 END),"
			+ "COUNT(CASE WHEN student.isSubmitted=FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Boy' and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Girl' and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Third Gender' and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Boy' and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Girl' and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.gender ='Third Gender' and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =13 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =14 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =15 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =16 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =17 and student.isSubmitted = TRUE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =13 and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =14 and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =15 and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =16 and student.isSubmitted = FALSE THEN 1 END)"
			+ ",COUNT(CASE WHEN student.caste.typeDetailId =17 and student.isSubmitted = FALSE THEN 1 END)"
			+ " FROM StudentsDetails student"
			+ " WHERE student.acedemicYear.startYear = :academicYear "
			+ " GROUP BY student.school.schoolId")
	public List<Object[]> getAggregateStudentsDataAtSchoolLevel(@Param("academicYear") String academicYear);
}
