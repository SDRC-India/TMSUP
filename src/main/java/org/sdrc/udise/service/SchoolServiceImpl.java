package org.sdrc.udise.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sdrc.udise.domain.Area;
import org.sdrc.udise.domain.SchoolCurrentAggregatedData;
import org.sdrc.udise.domain.SchoolDetails;
import org.sdrc.udise.domain.StudentsDetails;
import org.sdrc.udise.domain.TypeDetail;
import org.sdrc.udise.model.UserModel;
import org.sdrc.udise.repository.AreaRepository;
import org.sdrc.udise.repository.SchoolCurrentAggregatedDataRepository;
import org.sdrc.udise.repository.SchoolDetailsRepository;
import org.sdrc.udise.repository.StudentDetailsRepository;
import org.sdrc.udise.util.Constants;
import org.sdrc.udise.util.EnrollmentType;
import org.sdrc.udise.util.StateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Azaruddin (azaruddin@sdrc.co.in)
 *
 */

@Service
public class SchoolServiceImpl implements SchoolService {

	Logger _log = LoggerFactory.getLogger(SchoolServiceImpl.class);

	@Autowired
	private StateManager stateManager;

	@Autowired
	private SchoolCurrentAggregatedDataRepository schoolCurrentAggregatedDataRepository;

	@Autowired
	private StudentDetailsRepository studentDetailsRepository;

	@Autowired
	private SchoolDetailsRepository schoolDetailsRepository;

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private StudentService studentService;

	@Value("${area.district}")
	private int levelId;
	
	@Autowired
	ResourceBundleMessageSource applicationMessageSource;

	@Value("#{'${school.has.secondary.id}'.split(',')}")
	private List<Integer> typeIdsOfSecondarySchools;

	/**
	 * Method is called for block level deo dashboard view. First
	 * school_aggregate_data table is searched against blockId and current
	 * academic year. If block is active and school is present in block and is
	 * active,but no record is found for school in school_aggregate_data for
	 * current academic year,school is not returned for view in dashboard for
	 * deo.
	 */

	@Override
	@Transactional
	public List<Map<String, String>> getDashBoardViewOfDEO(String acedemicYear) {

		UserModel userModel = (UserModel) stateManager.getValue(Constants.Web.USER_PRINCIPAL);
		int areaId = userModel.getAreaId();

		

		List<SchoolCurrentAggregatedData> aggratedDatas = schoolCurrentAggregatedDataRepository.findByAcademicYearIdStartYearAndSchoolBlockAreaId(acedemicYear, areaId);

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (SchoolCurrentAggregatedData aggratedData : aggratedDatas) {
			SchoolDetails schoolDetail = aggratedData.getSchool();
			Map<String, String> map = new HashMap<>();
			map.put("School Name", schoolDetail.getSchoolName());
			map.put("Total",String.valueOf(aggratedData.getAdmittedStudents()+ aggratedData.getLefoutStudents()));
			map.put("Admitted",String.valueOf(aggratedData.getAdmittedStudents()));
			map.put("Left Out",String.valueOf(aggratedData.getLefoutStudents()));
			map.put("Left Out Percent",String.valueOf(aggratedData.findPercentOfLeftOut()));
			map.put("school_id", String.valueOf(schoolDetail.getSchoolId()));
			map.put("uidse_code",schoolDetail.getUdiseCode() != null ? schoolDetail.getUdiseCode() : "NA");
			list.add(map);
		}
		return list;

	}

	/**
	 * @param schoolId
	 *            The id of school for which all the student in the school are
	 *            returned for current academic year
	 * 
	 * @return List of students in school for current academic year.
	 */

	@Override
	@Transactional
	public List<Map<String, String>> getAllStudentsBySchool(int schoolId) {

		LocalDate localdate = new LocalDate();
		int year = localdate.getYear();
		int monthOfYear = localdate.getMonthOfYear();

		int acedemicYear = year;
		if (monthOfYear < 4) {
			acedemicYear = year - 1;
		}

		List<StudentsDetails> students = studentDetailsRepository
				.findBySchoolSchoolIdAndAcedemicYearStartYearOrderByIsSubmittedAscStudentIdDesc(
						schoolId, String.valueOf(acedemicYear));

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (StudentsDetails studentsDetail : students) {

			Map<String, String> map = new HashMap<>();
			map.put("Student's Name", studentsDetail.getStudentName());
			map.put("SR No.", studentsDetail.getSrRegistrationNumber());
			map.put("Father's Name", studentsDetail.getFathersName());
			map.put("Mother's Name", studentsDetail.getMothersName());

			map.put("Caste", studentsDetail.getCaste().getTypeDetail());
			map.put("Gender", studentsDetail.getGender());
			map.put("Status", String.valueOf(studentsDetail.isSubmitted()));
			map.put("student_id", String.valueOf(studentsDetail.getStudentId()));

			if (studentsDetail.isSubmitted() == true) {
				
				if(studentsDetail.getStudentSchoolMapping()
						.get(0).getEnrollType().getTypeDetailId()==Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.OTHER_STATE,null,null))||studentsDetail.getStudentSchoolMapping()
								.get(0).getEnrollType().getTypeDetailId()==Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.MIGRATION,null,null)))
				{
					if(studentsDetail.getStudentSchoolMapping()
							.get(0).getEnrollType().getTypeDetailId()==Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.MIGRATION,null,null)))
					{
						map.put("school Name","Migrated");

						map.put("udise_code","");
						}
					else
					{
						String schoolName=studentsDetail.getStudentSchoolMapping().get(0).getOtherStateSchoolName()==null?"":studentsDetail.getStudentSchoolMapping().get(0).getOtherStateSchoolName();
						
						if(schoolName.equals(""))
						{
							schoolName=studentsDetail.getStudentSchoolMapping().get(0).getOtherDistrict().getAreaName()+", "+studentsDetail.getStudentSchoolMapping().get(0).getOtherState().getAreaName();
						}
						else
						{
							schoolName+=", "+studentsDetail.getStudentSchoolMapping().get(0).getOtherDistrict().getAreaName()+", "+studentsDetail.getStudentSchoolMapping().get(0).getOtherState().getAreaName();
						}
						map.put("school Name", schoolName);

						map.put("udise_code","");
						}
					
					
				}
				else
				{
				map.put("school Name", studentsDetail.getStudentSchoolMapping()
						.get(0).getLinkedSchoolDetails().getSchoolName());

				map.put("udise_code",
						studentsDetail.getStudentSchoolMapping().get(0)
								.getLinkedSchoolDetails().getUdiseCode() != null ? studentsDetail
								.getStudentSchoolMapping().get(0)
								.getLinkedSchoolDetails().getUdiseCode()
								: "N/A");
				}
				// map.put("school Name", studentsDetail.getLinkedSchool()
				// .getSchoolName());
				//
				// map.put("udise_code", studentsDetail.getLinkedSchool()
				// .getUdiseCode() != null ? studentsDetail
				// .getLinkedSchool().getUdiseCode() : "N/A");
			} else {
				map.put("school Name", "");

			}
			list.add(map);
		}
		return list;
	}

	/**
	 * @param schoolId
	 *            School id of school
	 * @return List of all districts,list of all schools in block of the school
	 *         passed as parameter and a distance json of school.
	 * 
	 * @throws ParseException
	 */

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Map<String, JSONArray> getJsonDataForModelView(int schoolId) {

		Map<String, JSONArray> map = new HashMap<>();
		JSONArray topArray = new JSONArray();
		SchoolDetails details = schoolDetailsRepository
				.findBySchoolId(schoolId);
		String json = details.getSchoolLatLongLinks().getDistanceJSON();

		JSONArray distanceArray = new JSONArray();
		if (json != null && !json.isEmpty()) {
			try {
				distanceArray.add((JSONObject) new JSONParser().parse(json));
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		map.put("distanceJSON", distanceArray);
		topArray.add(distanceArray);
		int blockIdOfSchool = details.getBlock().getAreaId();
		int districtIdOfSchool = details.getDistrictId().getAreaId();
		_log.debug("block_id={}", blockIdOfSchool);
		_log.debug("districtIdOfSchool={}", districtIdOfSchool);

		List<SchoolDetails> schools = schoolDetailsRepository
				.findSecondarySchoolsByBlockAreaIdFetchJoinLatLongLink(
						blockIdOfSchool, typeIdsOfSecondarySchools);
		JSONArray schArray = new JSONArray();
		for (SchoolDetails school : schools) {
			JSONObject schoolMap = new JSONObject();
			schoolMap.put("School Name", school.getSchoolName());
			schoolMap.put("Block Name",
					String.valueOf(school.getBlock().getAreaName()));
			schoolMap.put("District Name",
					String.valueOf(school.getDistrictId().getAreaName()));
			schoolMap.put("Udise Code", String.valueOf(school.getUdiseCode()));
			schoolMap.put("school_id", String.valueOf(school.getSchoolId()));
			schArray.add(schoolMap);
		}

		map.put("schools", schArray);

		_log.debug("Firing query to find list of districts.");
		List<Area> districts = areaRepository
				.findByLevelOrderByAreaNameAsc(levelId);
		JSONArray districtsArray = new JSONArray();
		for (Area area : districts) {
			JSONObject obj = new JSONObject();
			obj.put("id", area.getAreaId());
			obj.put("name", area.getAreaName());
			districtsArray.add(districtsArray.size(), obj);
		}

		map.put("districts", districtsArray);

		_log.debug("Firing query to find list of blocks of the district in which school is located");
		List<Area> blocksInDistrictOfSchool = areaRepository
				.findByIsLiveTrueAndParentAreaIdOrderByAreaNameAsc(districtIdOfSchool);

		JSONArray blocksArray = new JSONArray();
		for (Area area : blocksInDistrictOfSchool) {
			JSONObject obj = new JSONObject();
			obj.put("id", area.getAreaId());
			obj.put("name", area.getAreaName());
			blocksArray.add(blocksArray.size(), obj);
		}
		map.put("blocks", blocksArray);

		return map;
	}

	@Override
	@Transactional
	public boolean saveSchool(String schoolName, int districtId, int blockId,
			String udiseCode, int schoolType, int fromSchool,
			List<Integer> studentsIds) {

		UserModel userModel = (UserModel) stateManager
				.getValue(Constants.Web.USER_PRINCIPAL);

		Area block = new Area();
		block.setAreaId(blockId);

		Area district = new Area();
		district.setAreaId(districtId);

		TypeDetail schoolTypeDomain = new TypeDetail();
		schoolTypeDomain.setTypeDetailId(12);// school type set to others by
												// default for schools added
												// later

		SchoolDetails school = new SchoolDetails();
		school = schoolDetailsRepository
				.findBySchoolNameIgnoreCaseAndBlockAreaId(schoolName, blockId);
		if (school == null) {
			school = new SchoolDetails();
			school.setBlock(block);
			school.setDistrictId(district);
			school.setCreatedBy(userModel.getUsername());
			school.setCreatedDate(new java.sql.Timestamp(new Date().getTime()));
			school.setLatitude("0.00");
			school.setLongitutde("0.00");
			school.setSchoolType(schoolTypeDomain);

			school.setLive(true);
			school.setVillage(null);
			school.setSchoolName(schoolName);
			if (!udiseCode.trim().equalsIgnoreCase(""))
				school.setUdiseCode(udiseCode);

			school = schoolDetailsRepository.save(school);
		}

		studentService.admitStudent(fromSchool, school.getSchoolId(),
				studentsIds,
				EnrollmentType.NEW_ADDED_SCHOOL_IN_OTHER.getValue());
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, JSONArray> getAllSecondarySchoolInBlock(int blockId) {

		List<SchoolDetails> schools = schoolDetailsRepository
				.findSecondarySchoolsByBlockAreaIdFetchJoinLatLongLink(blockId,
						typeIdsOfSecondarySchools);
		Map<String, JSONArray> map = new HashMap<>();

		JSONArray schArray = new JSONArray();
		for (SchoolDetails school : schools) {
			JSONObject schoolMap = new JSONObject();
			schoolMap.put("School Name", school.getSchoolName());
			schoolMap.put("Block Name",
					String.valueOf(school.getBlock().getAreaName()));
			schoolMap.put("District Name",
					String.valueOf(school.getDistrictId().getAreaName()));
			schoolMap.put("Udise Code", String.valueOf(school.getUdiseCode()));
			schoolMap.put("school_id", String.valueOf(school.getSchoolId()));
			schArray.add(schoolMap);
		}

		map.put("schools", schArray);
		return map;
	}

}
