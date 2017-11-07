package org.sdrc.udise.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.sdrc.udise.core.Authorize;
import org.sdrc.udise.domain.AcademicYear;
import org.sdrc.udise.model.StudentModel;
import org.sdrc.udise.model.ValueObject;
import org.sdrc.udise.service.SchoolService;
import org.sdrc.udise.service.StudentService;
import org.sdrc.udise.util.Constants;
import org.sdrc.udise.util.EnrollmentType;
import org.sdrc.udise.util.StudentAlreadyAdmittedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataEntryOperatorController {

	private final Logger _log = LoggerFactory.getLogger(DataEntryOperatorController.class);

	@Autowired
	public SchoolService schoolSevice;

	@Autowired
	public StudentService studentService;

	@Autowired
	ResourceBundleMessageSource errorMessageSource;

	/**
	 * Handles dashboard view of DEO for block
	 * 
	 * @return List of Schools of block that are active and are primary or upper
	 *         primary.
	 */

	@Authorize(feature = "link", permission = "view")
	@RequestMapping(value = "/findSchoolByBlock", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> getAllSchoolByBlock() {
		String acedemicYear = AcademicYear.getCurrentAcademicYear();
		return schoolSevice.getDashBoardViewOfDEO(acedemicYear);
	}

	/**
	 * 
	 * @param schoolId
	 *            Return all students of the school for current academic year
	 * @return List<Map<String, String>>
	 */

	@Authorize(feature = "link", permission = "view")
	@RequestMapping(value = "/findStudentsOfSchool", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> getAllStudentsOfSchool(@RequestParam(value = "id") int schoolId) {
		return schoolSevice.getAllStudentsBySchool(schoolId);
	}

	@Authorize(feature = "link", permission = "edit")
	@RequestMapping(value = "/admitStudent", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> admitStudent(@RequestParam(value = "from_school") int fromSchool, @RequestParam(value = "to_school") int toSchool, @RequestParam(value = "linking") boolean isLinked,
			@RequestBody List<String> student_id) {

		Map<String, String> map = new HashMap<>();
		try {
			List<Integer> ids = new ArrayList<>();
			for (String id : student_id) {
				ids.add(Integer.parseInt(id));
			}

			boolean submitted = studentService.admitStudent(fromSchool, toSchool, ids, EnrollmentType.WITHIN_SCHOOL_LIST.getValue());
			if (submitted) {
				map.put("is_admitted", "true");
			} else
				map.put("is_admitted", "false");
		} catch (Exception e) {
			_log.error("Failed to admit students with ids {}. Exception : {}", student_id, e);
			map.put("is_admitted", "false");
		}
		return map;
	}

	@Authorize(feature = "link", permission = "edit")
	@RequestMapping(value = "/admitStudentToOtherState", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> admitStudentToOtherState(@RequestParam(value = "fromSchool") int fromSchool, @RequestBody List<String> student_id, @RequestParam(value = "stateId") int stateId,
			@RequestParam(value = "districtId") int districtId, @RequestParam(value = "schoolName") String schoolName) {

		Map<String, String> map = new HashMap<>();
		try {
			List<Integer> ids = new ArrayList<>();
			for (String id : student_id) {
				ids.add(Integer.parseInt(id));
			}

			boolean submitted = studentService.admitStudentToOtherState(fromSchool, ids, stateId, districtId, schoolName);
			if (submitted) {
				map.put("is_admitted", "true");
			} else
				map.put("is_admitted", "false");
		} catch (Exception e) {
		
			_log.error("Failed to admit students with ids {}. Exception : {}", student_id, e);
			map.put("is_admitted", "false");
		}
		return map;

	}

	@Authorize(feature = "link", permission = "edit")
	@RequestMapping(value = "/migrateStudent", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> migrateStudent(@RequestBody List<String> student_id, @RequestParam(value = "stateId") int stateId, @RequestParam(value = "districtId") int districtId) {

		Map<String, String> map = new HashMap<>();
		try {
			List<Integer> ids = new ArrayList<>();
			for (String id : student_id) {
				ids.add(Integer.parseInt(id));
			}

			boolean submitted = studentService.migrateStudent(ids, stateId, districtId);
			if (submitted) {
				map.put("is_admitted", "true");
			} else
				map.put("is_admitted", "false");
		} catch (Exception e) {
			_log.error("Failed to admit students with ids {}. Exception : {}", student_id, e);
			map.put("is_admitted", "false");
		}
		return map;

	}

	/**
	 * 
	 * @param schoolId
	 * @return json of :1)List of schools in the block.2)List of districts.3)km
	 *         json.
	 */

	@Authorize(feature = "link", permission = "view")
	@RequestMapping(value = "/getDataForModelView")
	@ResponseBody
	public Map<String, JSONArray> getJsonDataForModelView(@RequestParam(value = "id") int schoolId) {
		return schoolSevice.getJsonDataForModelView(schoolId);
	}

	@Authorize(feature = "link", permission = "edit")
	@RequestMapping(value = "/saveStudent")
	@ResponseBody
	public Map<String, Object> saveStudent(@RequestBody StudentModel model) {
		Map<String, Object> map = new HashMap<>();
		try {
			if (studentService.saveStudent(model.getStudentName(), model.getFathersName(), model.getMothersName(), model.getGender(), model.getSchoolId(), model.getCasteId(),
					model.getSrRegistrationNo()) == null) {
				map.put("status", 0);
				map.put("message", errorMessageSource.getMessage(Constants.Web.DUPLICATE_STUDENT_ENTRY, null, null));
			} else {
				map.put("status", 1);
				map.put("message", errorMessageSource.getMessage(Constants.Web.NEW_STUDENT_ADDED_SUCCESS, null, null));
			}
		} catch (Exception e) {
			_log.error("Failed to save student.{}", e);
			map.put("status", 0);
			map.put("message", "Failed to save student.");
		}
		return map;
	}

	@Authorize(feature = "link", permission = "edit")
	@RequestMapping("/getStudentCaste")
	@ResponseBody
	List<ValueObject> getCaste() {
		return studentService.getStudentsCaste();
	}

	@Authorize(feature = "link", permission = "view")
	@RequestMapping("/manageStudents")
	public String getLinkPage() {
		return "link";
	}

}
