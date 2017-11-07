package org.sdrc.udise.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.sdrc.udise.core.Authorize;
import org.sdrc.udise.model.TypeDetailModel;
import org.sdrc.udise.service.SchoolService;
import org.sdrc.udise.service.TypeDetailService;
import org.sdrc.udise.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Azaruddin (azaruddin@sdrc.co.in)
 *
 */

/**
 * 
 * @return School Upper Primary School Types/Categories
 */

@Controller
public class SchoolController {

	@Autowired
	private TypeDetailService typeDetailService;

	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private ResourceBundleMessageSource errorMessageSource;

	private final Logger _log = LoggerFactory.getLogger(SchoolController.class);

	@Authorize(feature = "link", permission = "view")
	@RequestMapping(value = "/getSchoolTypes")
	@ResponseBody
	public List<TypeDetailModel> findAllSecondarySchoolTypeComboList() {
		return typeDetailService.findAllSecondarySchoolTypeComboList();
	}

	@Authorize(feature = "link", permission = "view")
	@RequestMapping(value = "/getSchoolsByBlock")
	@ResponseBody
	public Map<String, JSONArray> getAllSecondarySchoolInBlock(
			@RequestParam("block") int blockId) {
		return schoolService.getAllSecondarySchoolInBlock(blockId);
	}

	@Authorize(feature = "link", permission = "edit")
	@RequestMapping(value = "/saveSchool", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveSchool(
			@RequestParam(value = "schoolName") String schoolName,
			@RequestParam(value = "district") int districtId,
			@RequestParam(value = "block") int blockId,
			@RequestParam(value = "udiseCode") String udiseCode,
			@RequestParam(value = "schoolType") int schoolType,
			@RequestParam(value = "fromSchool") int fromSchool,
			@RequestBody List<String> student_id) {
		Map<String, Object> map = new HashMap<>();

		try {
			List<Integer> ids = new ArrayList<>();

			for (String id : student_id) {
				ids.add(Integer.parseInt(id));
			}
			schoolService.saveSchool(schoolName, districtId, blockId,
					udiseCode, schoolType, fromSchool, ids);
			map.put("status", 1);
			map.put("message", errorMessageSource.getMessage(Constants.Web.SUCCESS_ENROLL, null,null));
		} catch (DuplicateKeyException e) {
			_log.error("Failed to save school.{}", e);
			map.put("status", 0);
			map.put("message",
					errorMessageSource.getMessage(Constants.Web.DUPLICATE_SCHOOL_ENTRY, null,null));
		} catch (Exception e) {
			_log.error("Failed to save school.{}", e);
			map.put("status", 0);
			map.put("message", errorMessageSource.getMessage(Constants.Web.SERVER_ERROR, null,null));
		}
		return map;

	}

}
