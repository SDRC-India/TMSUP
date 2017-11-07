package org.sdrc.udise.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sdrc.udise.domain.Area;
import org.sdrc.udise.domain.MasterArea;
import org.sdrc.udise.model.ValueObject;
import org.sdrc.udise.repository.AreaRepository;
import org.sdrc.udise.repository.MasterAreaRepository;
import org.sdrc.udise.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

/**
 * @author Azaruddin (azaruddin@sdrc.co.in)
 *
 */

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private MasterAreaRepository masterAreaRepository;

	@Value("${area.district}")
	private int levelId;
	
	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;

	@Override
	public List<Map<String, String>> findBlocksByDistrict(int districtId) {

		List<Map<String, String>> list = new ArrayList<>();
		List<Area> blocks = areaRepository.findByParentAreaIdOrderByAreaNameAsc(districtId);
		for (Area area : blocks) {
			Map<String, String> obj = new HashMap<>();
			obj.put("id", area.getAreaId() + "");
			obj.put("name", area.getAreaName());
			list.add(obj);
		}
		return list;

	}
	
	@Override
	public List<Map<String, String>> findDistrictsByDivision(int division) {
		List<Map<String, String>> list = new ArrayList<>();
		List<Area> districts = areaRepository.findByIsLiveTrueAndParentAreaIdOrderByAreaNameAsc(division);
		for (Area area : districts) {
			Map<String, String> obj = new HashMap<>();
			obj.put("id", area.getAreaId() + "");
			obj.put("name", area.getAreaName());
			list.add(obj);
		}
		return list;
	}

	@Override
	public List<Map<String, String>> findAllDistricts() {
		List<Map<String, String>> list = new ArrayList<>();
		List<Area> blocks = areaRepository.findByLevelOrderByAreaNameAsc(levelId);
		for (Area area : blocks) {
			Map<String, String> obj = new HashMap<>();
			obj.put("id", area.getAreaId() + "");
			obj.put("name", area.getAreaName());
			list.add(obj);
		}
		return list;
	}

	@Override
	public List<ValueObject> getStates() {
		List<MasterArea> masterAreas=masterAreaRepository.findByLevel(Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.STATE_LEVEL, null,null)));
		List<ValueObject> areaList=new ArrayList<ValueObject>();
		
		for(MasterArea area:masterAreas)
		{
			ValueObject valueObject=new ValueObject();
			valueObject.setKey(String.valueOf(area.getAreaId()));
			valueObject.setValue(area.getAreaName());
			areaList.add(valueObject);
		}
		
		return areaList;
	}

	@Override
	public List<ValueObject> getDistricts(int stateId) {
		List<MasterArea> masterAreas=masterAreaRepository.findByParentAreaId(stateId);
List<ValueObject> areaList=new ArrayList<ValueObject>();
		
		for(MasterArea area:masterAreas)
		{
			ValueObject valueObject=new ValueObject();
			valueObject.setKey(String.valueOf(area.getAreaId()));
			valueObject.setValue(area.getAreaName());
			areaList.add(valueObject);
		}
		
		return areaList;
	}


	

}
