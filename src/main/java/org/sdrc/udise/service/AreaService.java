package org.sdrc.udise.service;

import java.util.List;
import java.util.Map;

import org.sdrc.udise.model.ValueObject;

/**
 * @author Azaruddin (azaruddin@sdrc.co.in)
 *
 */

public interface AreaService {

	public List<Map<String, String>> findBlocksByDistrict(int districtId);
	
	public List<Map<String, String>> findDistrictsByDivision(int division);
	
	public List<Map<String, String>> findAllDistricts();
	
	public List<ValueObject> getStates();
	
	public List<ValueObject> getDistricts(int stateId);

}
