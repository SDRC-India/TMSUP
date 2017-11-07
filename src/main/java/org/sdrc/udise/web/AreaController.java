package org.sdrc.udise.web;

import java.util.List;
import java.util.Map;

import org.sdrc.udise.core.Authorize;
import org.sdrc.udise.model.ValueObject;
import org.sdrc.udise.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author Azar
 *
 */

@Controller
public class AreaController {

	@Autowired
	private AreaService areaService;

	@Authorize(feature = "link", permission = "view")
	@RequestMapping("/getAllDistricts")
	@ResponseBody
	public List<Map<String, String>> getAllDistricts() {
		return areaService.findAllDistricts();
	}
	
	@Authorize(feature = "link", permission = "view")
	@RequestMapping("/getBlocksByDistrict")
	@ResponseBody
	public List<Map<String, String>> findBlocksByDistrict(@RequestParam(value = "district") int districtId) {
		return areaService.findBlocksByDistrict(districtId);
	}
	
	
	@Authorize(feature = "link", permission = "view")
	@RequestMapping("/getDistrictsByDivision")
	@ResponseBody
	public List<Map<String, String>> findDistrictsByDivision(@RequestParam(value = "division") int division) {
		return areaService.findDistrictsByDivision(division);
	}


	@Authorize(feature = "link", permission = "view")
	@RequestMapping("/getOtherStates")
	@ResponseBody
	public List<ValueObject> getOtherStates() {
  return areaService.getStates();
	}
	
	@Authorize(feature = "link", permission = "view")
	@RequestMapping("/getAllOtherDistricts")
	@ResponseBody
	public List<ValueObject> getAllOtherDistricts(@RequestParam(value = "stateId") int stateId) {
		  return areaService.getDistricts(stateId);
	}
	
	
}
