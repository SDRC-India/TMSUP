package org.sdrc.udise.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.sdrc.udise.domain.AcademicYear;
import org.sdrc.udise.domain.AggregationData;
import org.sdrc.udise.domain.Area;
import org.sdrc.udise.domain.SchoolAggregatedData;
import org.sdrc.udise.model.AdvancedSearchRequestModel;
import org.sdrc.udise.model.RawDataModel;
import org.sdrc.udise.model.UserModel;
import org.sdrc.udise.repository.AcedemicYearRepository;
import org.sdrc.udise.repository.AggregationDataRepository;
import org.sdrc.udise.repository.AreaRepository;
import org.sdrc.udise.repository.SchoolAggregatedDataRepository;
import org.sdrc.udise.repository.StudentDetailsRepository;
import org.sdrc.udise.repository.TypeDetailsRepository;
import org.sdrc.udise.util.Constants;
import org.sdrc.udise.util.EnrollmentType;
import org.sdrc.udise.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.expression.Lists;

/**
 * 
 * @author Azaruddin (azaruddin@sdrc.co.in)
 *
 */

@Service
public class AdvancedViewServiceImpl implements AdvancedViewService {

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private TypeDetailsRepository typeDetailsRepository;

	@Autowired
	private AggregationDataRepository aggregationDataRepository;

	@Autowired
	private SchoolAggregatedDataRepository schoolAggregatedDataRepository;

	@Autowired
	private StudentDetailsRepository studentDetailsRepository;

	@Autowired
	private AcedemicYearRepository acedemicYearRepository;

	@Value("${area.state}")
	private int levelState;
	@Value("${area.division}")
	private int levelDivision;
	@Value("${area.district}")
	private int levelDistrict;
	@Value("${area.block}")
	private int levelBlock;

	@Value("${indicator.leftout}")
	private String leftoutIndicatorName;
	@Value("${indicator.enrolled}")
	private String enrolledIndicatorName;

	@Value("${indicator.leftout.value}")
	private int leftoutIndicatorVal;
	@Value("${indicator.enrolled.value}")
	private int enrolledIndicatorVal;

	@Value("${searchType.division_level_data}")
	private String divisionSearchType;
	@Value("${searchType.division_level_data.value}")
	private int divisionSearchTypeValue;

	@Value("${searchType.district_level_data}")
	private String districtSearchType;
	@Value("${searchType.district_level_data.value}")
	private int districtSearchTypeValue;

	@Value("${searchType.block_level_data}")
	private String blockSearchType;
	@Value("${searchType.block_level_data.value}")
	private int blockSearchTypeValue;

	@Value("${searchType.school_level_data}")
	private String schoolSearchType;
	@Value("${searchType.school_level_data.value}")
	private int schoolSearchTypeValue;

	@Value("${rule.greater}")
	private String ruleGreater;
	@Value("${rule.greater.value}")
	private int ruleGreaterValue;

	@Value("${rule.greater.than.equal.to}")
	private String ruleGreaterThanEqualTo;
	@Value("${rule.greater.than.equal.to.value}")
	private int ruleGreaterThanEqualToValue;

	@Value("${rule.equal.to}")
	private String ruleEqualTo;
	@Value("${rule.equal.to.value}")
	private int ruleEqualToValue;

	@Value("${rule.less.than}")
	private String ruleLessThan;
	@Value("${rule.less.than.value}")
	private int ruleLessThanValue;

	@Value("${rule.less.than.equal.to}")
	private String ruleLessThanEqualTo;
	@Value("${rule.less.than.equal.to.value}")
	private int ruleLessThanEqualToValue;

	@Value("${STATE_NAME_IN_EXCEL}")
	private String STATE_NAME_IN_EXCEL;

	@Autowired
	private StateManager stateManager;

	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;

	@SuppressWarnings("unchecked")
	public Map<String, JSONObject> getInitViewDataForAdvancedSearch() {

		UserModel userModel = (UserModel) stateManager.getValue(Constants.Web.USER_PRINCIPAL);

		List<Area> areas = new ArrayList<Area>();

		if (userModel.getAreaLevel() == Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.STATE_LEVEL, null, null))) {
			areas = areaRepository.findByIsLiveTrueAndLevelInOrderByLevelAscNameAsc(levelDivision, levelDistrict, levelBlock);
		} else if (userModel.getAreaLevel() == Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.DIVISIONAL_LEVEL, null, null))) {
			Area area = new Area();

			area.setAreaName(userModel.getAreaName());
			area.setAreaId(userModel.getAreaId());
			area.setParentAreaId(userModel.getParentAreaId());
			areas.add(area);
			areas.addAll(areaRepository.findByIsLiveTrueAndParentAreaIdOrderByAreaNameAsc(userModel.getAreaId()));
			areas.addAll(areaRepository.findByParentParentAreaIdAndIsLiveTrue(userModel.getAreaId()));
		}

		else if (userModel.getAreaLevel() == Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.DISTRICT_LEVEL, null, null))) {
			Area area = new Area();

			area.setAreaName(userModel.getDivisionName());
			area.setAreaId(userModel.getDivisionId());
			area.setParentAreaId(userModel.getStateId());
			areas.add(area);

			area = new Area();
			area.setAreaName(userModel.getAreaName());
			area.setAreaId(userModel.getAreaId());
			area.setParentAreaId(userModel.getParentAreaId());
			areas.add(area);
			areas.addAll(areaRepository.findByIsLiveTrueAndParentAreaIdOrderByAreaNameAsc(userModel.getAreaId()));
		}

		else if (userModel.getAreaLevel() == Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.BLOCK_LEVEL, null, null))) {
			Area area = new Area();

			area.setAreaName(userModel.getDivisionName());
			area.setAreaId(userModel.getDivisionId());
			area.setParentAreaId(userModel.getStateId());
			areas.add(area);
			area = new Area();

			area.setAreaName(userModel.getDistrictName());
			area.setAreaId(userModel.getDistrictId());
			area.setParentAreaId(userModel.getDivisionId());
			areas.add(area);

			area = new Area();
			area.setAreaName(userModel.getAreaName());
			area.setAreaId(userModel.getAreaId());
			area.setParentAreaId(userModel.getParentAreaId());
			areas.add(area);
		}
		JSONArray values = new JSONArray();
		JSONArray areaArray = new JSONArray();
		JSONArray ruleArray = new JSONArray();
		JSONArray indicatorArray = new JSONArray();
		JSONArray academicYearArray = new JSONArray();

		List<AcademicYear> academicYears = acedemicYearRepository.findAll();

		for (AcademicYear academicYear : academicYears) {
			JSONObject academicYearObj = new JSONObject();
			academicYearObj.put("name", academicYear.getAcademicYear());
			academicYearObj.put("id", academicYear.getAcademicYearId());
			academicYearArray.add(academicYearObj);
		}

		// //-------------------Initializing indicators--------------------
		JSONObject leftOutIndicator = new JSONObject();
		leftOutIndicator.put("name", leftoutIndicatorName);
		leftOutIndicator.put("id", leftoutIndicatorVal);

		JSONObject enrolledIndicator = new JSONObject();
		enrolledIndicator.put("name", enrolledIndicatorName);
		enrolledIndicator.put("id", enrolledIndicatorVal);

		indicatorArray.add(leftOutIndicator);
		indicatorArray.add(enrolledIndicator);

		// -------------------END-----------------------------------------

		// --------------------intializing search types-------------------
		JSONObject divisionLevel = new JSONObject();

		divisionLevel.put("name", divisionSearchType);
		divisionLevel.put("id", divisionSearchTypeValue);

		JSONObject districtLevel = new JSONObject();

		districtLevel.put("name", districtSearchType);
		districtLevel.put("id", districtSearchTypeValue);

		JSONObject blockLevel = new JSONObject();

		blockLevel.put("name", blockSearchType);
		blockLevel.put("id", blockSearchTypeValue);

		JSONObject schoolLevel = new JSONObject();

		schoolLevel.put("name", schoolSearchType);
		schoolLevel.put("id", schoolSearchTypeValue);

		// ----------------------------------END------------------------------

		if (userModel.getAreaLevel() == Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.STATE_LEVEL, null, null))) {
			values.add(divisionLevel);
		}
		if (userModel.getAreaLevel() == Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.STATE_LEVEL, null, null))
				|| userModel.getAreaLevel() == Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.DIVISIONAL_LEVEL, null, null))) {
			values.add(districtLevel);
		}
		if (userModel.getAreaLevel() == Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.STATE_LEVEL, null, null))
				|| userModel.getAreaLevel() == Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.DIVISIONAL_LEVEL, null, null))
				|| userModel.getAreaLevel() == Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.DISTRICT_LEVEL, null, null))) {
			values.add(blockLevel);
		}
		values.add(schoolLevel);

		for (Area area : areas) {
			JSONObject index = new JSONObject();
			index.put("areaId", area.getAreaId());
			index.put("areaName", area.getAreaName());
			index.put("parentAreaId", area.getParentAreaId());
			areaArray.add(index);
		}

		JSONObject index = new JSONObject();
		index.put("ruleName", ruleGreater);
		index.put("ruleId", ruleGreaterValue);
		ruleArray.add(index);

		index = new JSONObject();
		index.put("ruleName", ruleGreaterThanEqualTo);
		index.put("ruleId", ruleGreaterThanEqualToValue);
		ruleArray.add(index);

		index = new JSONObject();
		index.put("ruleName", ruleEqualTo);
		index.put("ruleId", ruleEqualToValue);
		ruleArray.add(index);

		index = new JSONObject();
		index.put("ruleName", ruleLessThan);
		index.put("ruleId", ruleLessThanValue);
		ruleArray.add(index);

		index = new JSONObject();
		index.put("ruleName", ruleLessThanEqualTo);
		index.put("ruleId", ruleLessThanEqualToValue);
		ruleArray.add(index);

		JSONObject data = new JSONObject();
		data.put("searchType", values);
		data.put("rules", ruleArray);
		data.put("areas", areaArray);
		data.put("indicator", indicatorArray);
		data.put("academicYears", academicYearArray);
		return data;
	}

	@Override
	@Transactional
	public List<Map<String, String>> searchData(AdvancedSearchRequestModel advancedSearchRequestModel) {

		int division = advancedSearchRequestModel.getDivision();
		int district = advancedSearchRequestModel.getDistrict();
		int block = advancedSearchRequestModel.getBlock();
		int ruleType = advancedSearchRequestModel.getRuleType();
		int indicator = advancedSearchRequestModel.getIndicator();
		String searchValue = advancedSearchRequestModel.getSearchCondition();
		String academicYear = advancedSearchRequestModel.getAcademicYear();

		List<AggregationData> data = null;
		List<SchoolAggregatedData> schoolAggregatedData = null;

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		switch (indicator) {
		case 1:// leftout

			switch (advancedSearchRequestModel.getSearchType()) {
			case 1:// search division level data
				switch (ruleType) {
				case 1:// greater than
					data = aggregationDataRepository.findByLeftOutPercentGreaterThan(levelDivision, academicYear, Float.parseFloat(searchValue));
					break;
				case 2:// greater than equal to
					data = aggregationDataRepository.findByLeftOutPercentGreaterThanEqualTo(levelDivision, academicYear, Float.parseFloat(searchValue));
					break;
				case 3:// equal to
					data = aggregationDataRepository.findByLeftOutPercentEqualTo(levelDivision, academicYear, Float.parseFloat(searchValue));
					break;
				case 4:// less than
					data = aggregationDataRepository.findByLeftOutPercentLessThan(levelDivision, academicYear, Float.parseFloat(searchValue));
					break;
				case 5:// less than equal to
					data = aggregationDataRepository.findByLeftOutPercentLessThanEqualTo(levelDivision, academicYear, Float.parseFloat(searchValue));
					break;
				}

				for (AggregationData aggregationData : data) {
					Map<String, String> map = new LinkedHashMap<>();
					map.put("Division Name", aggregationData.getAreaId().getAreaName());
					map.put("Total", String.valueOf(aggregationData.getAdmittedStudents() + aggregationData.getLefoutStudents()));
					map.put("Enrolled", String.valueOf(aggregationData.getAdmittedStudents()));
					map.put("Left Out", String.valueOf(aggregationData.getLefoutStudents()));
					map.put("Enrolled Percent", String.valueOf(aggregationData.findPercentOfAdmittedOut()));
					map.put("Left Out Percent", String.valueOf(aggregationData.findPercentOfLeftOut()));

					list.add(map);

				}
				break;
			case 2:// search district level data

				switch (ruleType) {

				case 1:// greater than
					data = aggregationDataRepository.findByParentAreaIdAndLeftOutPercentGreaterThan(division, academicYear, Float.parseFloat(searchValue));
					break;
				case 2:// greater than equal to
					data = aggregationDataRepository.findByParentAreaIdAndLeftOutPercentGreaterThanEqualTo(division, academicYear, Float.parseFloat(searchValue));
					break;
				case 3:// equal to
					data = aggregationDataRepository.findByParentAreaIdAndLeftOutPercentEqualTo(division, academicYear, Float.parseFloat(searchValue));
					break;
				case 4:// less than
					data = aggregationDataRepository.findByParentAreaIdAndLeftOutPercentLessThan(division, academicYear, Float.parseFloat(searchValue));
					break;
				case 5:// less than equal to
					data = aggregationDataRepository.findByParentAreaIdAndLeftOutPercentLessThanEqualTo(division, academicYear, Float.parseFloat(searchValue));
					break;
				}

				for (AggregationData aggregationData : data) {
					Map<String, String> map = new LinkedHashMap<>();
					map.put("District Name", aggregationData.getAreaId().getAreaName());
					map.put("Total", String.valueOf(aggregationData.getAdmittedStudents() + aggregationData.getLefoutStudents()));
					map.put("Enrolled", String.valueOf(aggregationData.getAdmittedStudents()));
					map.put("Left Out", String.valueOf(aggregationData.getLefoutStudents()));
					map.put("Enrolled Percent", String.valueOf(aggregationData.findPercentOfAdmittedOut()));
					map.put("Left Out Percent", String.valueOf(aggregationData.findPercentOfLeftOut()));

					list.add(map);

				}
				break;
			case 3:// block level leftout data
				switch (ruleType) {

				case 1:// greater than
					data = aggregationDataRepository.findByParentAreaIdAndLeftOutPercentGreaterThan(district, academicYear, Float.parseFloat(searchValue));
					break;
				case 2:// greater than equal to
					data = aggregationDataRepository.findByParentAreaIdAndLeftOutPercentGreaterThanEqualTo(district, academicYear, Float.parseFloat(searchValue));
					break;
				case 3:// equal to
					data = aggregationDataRepository.findByParentAreaIdAndLeftOutPercentEqualTo(district, academicYear, Float.parseFloat(searchValue));
					break;
				case 4:// less than
					data = aggregationDataRepository.findByParentAreaIdAndLeftOutPercentLessThan(district, academicYear, Float.parseFloat(searchValue));
					break;
				case 5:// less than equal to
					data = aggregationDataRepository.findByParentAreaIdAndLeftOutPercentLessThanEqualTo(district, academicYear, Float.parseFloat(searchValue));
					break;
				}
				for (AggregationData aggregationData : data) {
					Map<String, String> map = new LinkedHashMap<>();
					map.put("Block Name", aggregationData.getAreaId().getAreaName());
					map.put("Total", String.valueOf(aggregationData.getAdmittedStudents() + aggregationData.getLefoutStudents()));
					map.put("Enrolled", String.valueOf(aggregationData.getAdmittedStudents()));
					map.put("Left Out", String.valueOf(aggregationData.getLefoutStudents()));
					map.put("Enrolled Percent", String.valueOf(aggregationData.findPercentOfAdmittedOut()));
					map.put("Left Out Percent", String.valueOf(aggregationData.findPercentOfLeftOut()));

					list.add(map);

				}
				break;
			case 4:// school level leftout data
				switch (ruleType) {

				case 1:// greater than
					schoolAggregatedData = schoolAggregatedDataRepository.findBySchoolLevelAndLeftOutPercentGreaterThan(block, academicYear, Float.parseFloat(searchValue));
					break;
				case 2:// greater than equal to
					schoolAggregatedData = schoolAggregatedDataRepository.findBySchoolLevelAndLeftOutPercentGreaterThanEqualTo(block, academicYear, Float.parseFloat(searchValue));
					break;
				case 3:// equal to
					schoolAggregatedData = schoolAggregatedDataRepository.findBySchoolLevelAndLeftOutPercentEqualTo(block, academicYear, Float.parseFloat(searchValue));
					break;
				case 4:// less than
					schoolAggregatedData = schoolAggregatedDataRepository.findBySchoolLevelAndLeftOutPercentLessThan(block, academicYear, Float.parseFloat(searchValue));
					break;
				case 5:// less than equal to
					schoolAggregatedData = schoolAggregatedDataRepository.findBySchoolLevelAndLeftOutPercentLessThanEqualTo(block, academicYear, Float.parseFloat(searchValue));
					break;
				}
				for (SchoolAggregatedData aggregationData : schoolAggregatedData) {
					Map<String, String> map = new LinkedHashMap<>();
					map.put("School Name", aggregationData.getSchool().getSchoolName() + " (" + aggregationData.getSchool().getUdiseCode() + ")");
					map.put("Total", String.valueOf(aggregationData.getAdmittedStudents() + aggregationData.getLefoutStudents()));
					map.put("Enrolled", String.valueOf(aggregationData.getAdmittedStudents()));
					map.put("Left Out", String.valueOf(aggregationData.getLefoutStudents()));
					map.put("Enrolled Percent", String.valueOf(aggregationData.findPercentOfAdmittedOut()));
					map.put("Left Out Percent", String.valueOf(aggregationData.findPercentOfLeftOut()));

					list.add(map);

				}
				break;
			}

			break;
		// ---------------------------------------------------Enrolled
		// %---------------------------------------
		case 2:// enrolled

			switch (advancedSearchRequestModel.getSearchType()) {
			case 1:// search division level data

				switch (ruleType) {

				case 1:// greater than
					data = aggregationDataRepository.findByEnrolledPercentGreaterThan(levelDivision, academicYear, Float.parseFloat(searchValue));

					break;
				case 2:// greater than equal to
					data = aggregationDataRepository.findByEnrolledPercentGreaterThanEqualTo(levelDivision, academicYear, Float.parseFloat(searchValue));
					break;
				case 3:// equal to
					data = aggregationDataRepository.findByEnrolledPercentEqualTo(levelDivision, academicYear, Float.parseFloat(searchValue));
					break;
				case 4:// less than
					data = aggregationDataRepository.findByEnrolledPercentLessThan(levelDivision, academicYear, Float.parseFloat(searchValue));
					break;
				case 5:// less than equal to
					data = aggregationDataRepository.findByEnrolledPercentLessThanEqualTo(levelDivision, academicYear, Float.parseFloat(searchValue));
					break;
				}

				for (AggregationData aggregationData : data) {
					Map<String, String> map = new LinkedHashMap<>();
					map.put("Division Name", aggregationData.getAreaId().getAreaName());
					map.put("Total", String.valueOf(aggregationData.getAdmittedStudents() + aggregationData.getLefoutStudents()));
					map.put("Enrolled", String.valueOf(aggregationData.getAdmittedStudents()));
					map.put("Left Out", String.valueOf(aggregationData.getLefoutStudents()));
					map.put("Enrolled Percent", String.valueOf(aggregationData.findPercentOfAdmittedOut()));
					map.put("Left Out Percent", String.valueOf(aggregationData.findPercentOfLeftOut()));

					list.add(map);

				}
				break;
			case 2:// search district level data

				switch (ruleType) {

				case 1:// greater than
					data = aggregationDataRepository.findByParentAreaIdAndEnrolledPercentGreaterThan(division, academicYear, Float.parseFloat(searchValue));
					break;
				case 2:// greater than equal to
					data = aggregationDataRepository.findByParentAreaIdAndEnrolledPercentGreaterThanEqualTo(division, academicYear, Float.parseFloat(searchValue));
					break;
				case 3:// equal to
					data = aggregationDataRepository.findByParentAreaIdAndEnrolledPercentEqualTo(division, academicYear, Float.parseFloat(searchValue));
					break;
				case 4:// less than
					data = aggregationDataRepository.findByParentAreaIdAndEnrolledPercentLessThan(division, academicYear, Float.parseFloat(searchValue));
					break;
				case 5:// less than equal to
					data = aggregationDataRepository.findByParentAreaIdAndEnrolledPercentLessThanEqualTo(division, academicYear, Float.parseFloat(searchValue));
					break;
				}

				for (AggregationData aggregationData : data) {
					Map<String, String> map = new LinkedHashMap<>();
					map.put("District Name", aggregationData.getAreaId().getAreaName());
					map.put("Total", String.valueOf(aggregationData.getAdmittedStudents() + aggregationData.getLefoutStudents()));
					map.put("Enrolled", String.valueOf(aggregationData.getAdmittedStudents()));
					map.put("Left Out", String.valueOf(aggregationData.getLefoutStudents()));
					map.put("Enrolled Percent", String.valueOf(aggregationData.findPercentOfAdmittedOut()));
					map.put("Left Out Percent", String.valueOf(aggregationData.findPercentOfLeftOut()));

					list.add(map);

				}
				break;
			case 3:// block level enrolled data
				switch (ruleType) {

				case 1:// greater than
					data = aggregationDataRepository.findByParentAreaIdAndEnrolledPercentGreaterThan(district, academicYear, Float.parseFloat(searchValue));
					break;
				case 2:// greater than equal to
					data = aggregationDataRepository.findByParentAreaIdAndEnrolledPercentGreaterThanEqualTo(district, academicYear, Float.parseFloat(searchValue));
					break;
				case 3:// equal to
					data = aggregationDataRepository.findByParentAreaIdAndEnrolledPercentEqualTo(district, academicYear, Float.parseFloat(searchValue));
					break;
				case 4:// less than
					data = aggregationDataRepository.findByParentAreaIdAndEnrolledPercentLessThan(district, academicYear, Float.parseFloat(searchValue));
					break;
				case 5:// less than equal to
					data = aggregationDataRepository.findByParentAreaIdAndEnrolledPercentLessThanEqualTo(district, academicYear, Float.parseFloat(searchValue));
					break;
				}
				for (AggregationData aggregationData : data) {
					Map<String, String> map = new LinkedHashMap<>();
					map.put("Block Name", aggregationData.getAreaId().getAreaName());
					map.put("Total", String.valueOf(aggregationData.getAdmittedStudents() + aggregationData.getLefoutStudents()));
					map.put("Enrolled", String.valueOf(aggregationData.getAdmittedStudents()));
					map.put("Left Out", String.valueOf(aggregationData.getLefoutStudents()));
					map.put("Enrolled Percent", String.valueOf(aggregationData.findPercentOfAdmittedOut()));
					map.put("Left Out Percent", String.valueOf(aggregationData.findPercentOfLeftOut()));

					list.add(map);
				}
				break;
			case 4:// school level enrolled data
				switch (ruleType) {

				case 1:// greater than
					schoolAggregatedData = schoolAggregatedDataRepository.findBySchoolLevelAndEnrolledPercentGreaterThan(block, academicYear, Float.parseFloat(searchValue));
					break;
				case 2:// greater than equal to
					schoolAggregatedData = schoolAggregatedDataRepository.findBySchoolLevelAndEnrolledPercentGreaterThanEqualTo(block, academicYear, Float.parseFloat(searchValue));
					break;
				case 3:// equal to
					schoolAggregatedData = schoolAggregatedDataRepository.findBySchoolLevelAndEnrolledPercentEqualTo(block, academicYear, Float.parseFloat(searchValue));
					break;
				case 4:// less than
					schoolAggregatedData = schoolAggregatedDataRepository.findBySchoolLevelAndEnrolledPercentLessThan(block, academicYear, Float.parseFloat(searchValue));
					break;
				case 5:// less than equal to
					schoolAggregatedData = schoolAggregatedDataRepository.findBySchoolLevelAndEnrolledPercentLessThanEqualTo(block, academicYear, Float.parseFloat(searchValue));
					break;
				}
				for (SchoolAggregatedData aggregationData : schoolAggregatedData) {
					Map<String, String> map = new LinkedHashMap<>();
					map.put("School Name", aggregationData.getSchool().getSchoolName() + " (" + aggregationData.getSchool().getUdiseCode() + ")");
					map.put("Total", String.valueOf(aggregationData.getAdmittedStudents() + aggregationData.getLefoutStudents()));
					map.put("Enrolled", String.valueOf(aggregationData.getAdmittedStudents()));
					map.put("Left Out", String.valueOf(aggregationData.getLefoutStudents()));
					map.put("Enrolled Percent", String.valueOf(aggregationData.findPercentOfAdmittedOut()));
					map.put("Left Out Percent", String.valueOf(aggregationData.findPercentOfLeftOut()));
					list.add(map);

				}
				break;
			}
			break;
		}

		return list;
	}

	@Override
	@Transactional
	public File searchRawData(RawDataModel rawDataModel) {
		String acedemicYear = rawDataModel.getAcademicYear();
		int searchType = rawDataModel.getSearchType();
		int division = rawDataModel.getDivision();
		int district = rawDataModel.getDistrict();
		int block = rawDataModel.getBlock();

		List<Object[]> students = new ArrayList<>();
	
		switch (searchType) {

		case 1:// all divisions
			students = studentDetailsRepository.findByAcedemicYearStartYearAndSchoolForAllDivisions(acedemicYear);
			break;
		case 2:// division level
			students = studentDetailsRepository.findByAcedemicYearStartYearAndSchoolDivisionLevel(acedemicYear, division);
			break;
		case 3:// district level
			students = studentDetailsRepository.findByAcedemicYearStartYearAndSchoolDistrictLevel(acedemicYear, district);
			break;
		case 4:// block level
			students = studentDetailsRepository.findByAcedemicYearStartYearAndSchoolBlockLevel(acedemicYear, block);

			break;

		}


		int j=0;
		int splitterSize =(students.size()/1000000)+1;
		
	
		SXSSFWorkbook wb = new SXSSFWorkbook(100);

		
		for(int i=0;i<splitterSize;i++)
		{
		SXSSFSheet sheet = wb.createSheet("Sheet "+(i+1));


		Row row = sheet.createRow(2);

		/* Create HSSFFont object from the workbook */
		Font boldFont = wb.createFont();
		/* set the weight of the font */
		boldFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		/* attach the font to the style created earlier */

		// Top Left alignment
		CellStyle headerStyle = wb.createCellStyle();
		headerStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		headerStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
		headerStyle.setFont(boldFont);

		headerStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		headerStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		headerStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		headerStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		headerStyle.setFillForegroundColor((short) 200);
		headerStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		CellStyle headerStyle0 = wb.createCellStyle();
		headerStyle0.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		headerStyle0.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
		headerStyle0.setFont(boldFont);
		headerStyle0.setFillForegroundColor((short) 300);
		headerStyle0.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		CellStyle headerStyle1 = wb.createCellStyle();
		headerStyle1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		headerStyle1.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
		headerStyle1.setFont(boldFont);
		headerStyle1.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		headerStyle1.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		CellStyle colStyle = wb.createCellStyle();
		colStyle.setBorderBottom(CellStyle.BORDER_THIN);
		colStyle.setBorderTop(CellStyle.BORDER_THIN);
		colStyle.setBorderLeft(CellStyle.BORDER_THIN);
		colStyle.setBorderRight(CellStyle.BORDER_THIN);

		Row row0 = sheet.createRow(1);

		Cell cell_collab_enroll = row0.createCell(11);
		cell_collab_enroll.setCellStyle(headerStyle0);
		cell_collab_enroll.setCellValue((String) "ENROLLED TO");
		sheet.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
				1, // last row (0-based)
				11, // first column (0-based)
				14 // last column (0-based)
		));
		cell_collab_enroll.setCellStyle(headerStyle0);

		Cell cell_collab_migrate = row0.createCell(15);
		cell_collab_migrate.setCellStyle(headerStyle1);
		cell_collab_migrate.setCellValue((String) "MIGRATED TO");
		sheet.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
				1, // last row (0-based)
				15, // first column (0-based)
				16 // last column (0-based)
		));
		cell_collab_migrate.setCellStyle(headerStyle1);

		/*
		 * At this stage, we have a bold style created which we can attach to a
		 * cell
		 */

		int colCounter = 0;

		Cell cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);

		cell.setCellValue((String) "FROM SCHOOL");

		cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue((String) "STUDENT REG. NO");

		cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue((String) "STUDENT NAME");

		cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue((String) "GENDER");

		cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue((String) "CASTE");

		cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue((String) "FATHER NAME");

		cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue((String) "MOTHER NAME");

		cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue((String) "DIVISION");

		cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue((String) "DISTRICT");

		cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue((String) "BLOCK");

		cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue((String) "ENROLLMENT STATUS");

		cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue((String) "STATE");

		cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue((String) "DISTRICT");

		cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue((String) "ENROLLED TO SCHOOL");

		cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue((String) "ENROLLED SCHOOL UDISE-CODE");

		cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue((String) "STATE");

		cell = row.createCell(colCounter++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue((String) "DISTRICT");

		int creataDataRowsFrom = 3;

		//for(int counter=0;counter<=15;counter++)
		{
			
		//	List<Object[]> studentList=students.subList(i*1000000, ((i+1)*1000000)-1);
	for(int x=j ;x< (i+1)*1000000 && x < students.size(); x++ ){
			j=x;
			
			Object[] student = students.get(x);        
			colCounter = 0;
			row = sheet.createRow(creataDataRowsFrom++);

			cell = row.createCell(colCounter++);
			cell.setCellStyle(colStyle);
			cell.setCellValue(student[0] + " (" + student[1] + ")");

			cell = row.createCell(colCounter++);
			cell.setCellStyle(colStyle);
			cell.setCellValue(student[3].toString());

			cell = row.createCell(colCounter++);
			cell.setCellStyle(colStyle);
			cell.setCellValue(student[2].toString());

			cell = row.createCell(colCounter++);
			cell.setCellStyle(colStyle);
			cell.setCellValue(student[6].toString());

			cell = row.createCell(colCounter++);
			cell.setCellStyle(colStyle);
			cell.setCellValue(student[16].toString());

			cell = row.createCell(colCounter++);
			cell.setCellStyle(colStyle);
			cell.setCellValue(student[4].toString());

			cell = row.createCell(colCounter++);
			cell.setCellStyle(colStyle);
			cell.setCellValue(student[5].toString());

			cell = row.createCell(colCounter++);
			cell.setCellStyle(colStyle);
			cell.setCellValue(student[7].toString());

			cell = row.createCell(colCounter++);
			cell.setCellStyle(colStyle);
			cell.setCellValue(student[8].toString());

			cell = row.createCell(colCounter++);
			cell.setCellStyle(colStyle);
			cell.setCellValue(student[9].toString());

			EnrollmentType enrollTypeOfStudent = null;

			if (new Boolean(student[10].toString()) == true) {
				int enrollTypeId = Integer.valueOf(student[11].toString());
				for (EnrollmentType enrollmentType : EnrollmentType.values()) {
					if (enrollmentType.getValue() == enrollTypeId) {
						enrollTypeOfStudent = enrollmentType;
					}
				}
			}

			if (enrollTypeOfStudent != null) {
				switch (enrollTypeOfStudent) {
				case MIGRATION:

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) "MIGRATED");

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) "");

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) "");

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) "");

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) "");

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) ((student[14] != null) ? student[14].toString() : ""));

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) ((student[15] != null) ? student[15].toString() : ""));

					break;
				case OTHER_STATE:

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) "YES");

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) ((student[14] != null) ? student[14].toString() : ""));

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) ((student[15] != null) ? student[15].toString() : ""));

					if (student[12] != null) {

						cell = row.createCell(colCounter++);
						cell.setCellStyle(colStyle);
						cell.setCellValue((String) student[12].toString());

						cell = row.createCell(colCounter++);
						cell.setCellStyle(colStyle);
						cell.setCellValue((String) (student[18] != null ? student[18].toString() : ""));
					} else {

						cell = row.createCell(colCounter++);
						cell.setCellStyle(colStyle);
						cell.setCellValue((String) "");

						cell = row.createCell(colCounter++);
						cell.setCellStyle(colStyle);
						cell.setCellValue((String) "");
					}

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) "");

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) "");

					break;

				case NEW_ADDED_SCHOOL_IN_OTHER:

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) "YES");

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue(STATE_NAME_IN_EXCEL);

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) student[17].toString());

					if (student[13] != null) {

						cell = row.createCell(colCounter++);
						cell.setCellStyle(colStyle);
						cell.setCellValue((String) (student[13].toString()));

						cell = row.createCell(colCounter++);
						cell.setCellStyle(colStyle);
						cell.setCellValue((String) (student[18] != null ? student[18].toString() : ""));

					} else {

						cell = row.createCell(colCounter++);
						cell.setCellStyle(colStyle);
						cell.setCellValue((String) "");

						cell = row.createCell(colCounter++);
						cell.setCellStyle(colStyle);
						cell.setCellValue((String) "");

					}

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) "");

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) "");

					break;

				case WITHIN_SCHOOL_LIST:

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) "YES");

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) STATE_NAME_IN_EXCEL);

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) student[17].toString());

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) student[13].toString());

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) student[18].toString());

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) "");

					cell = row.createCell(colCounter++);
					cell.setCellStyle(colStyle);
					cell.setCellValue((String) "");

					break;
				}
			} else {

				cell = row.createCell(colCounter++);
				cell.setCellStyle(colStyle);
				cell.setCellValue((String) "NO");

				cell = row.createCell(colCounter++);
				cell.setCellStyle(colStyle);
				cell.setCellValue((String) "");

				cell = row.createCell(colCounter++);
				cell.setCellStyle(colStyle);
				cell.setCellValue((String) "");

				cell = row.createCell(colCounter++);
				cell.setCellStyle(colStyle);
				cell.setCellValue((String) "");

				cell = row.createCell(colCounter++);
				cell.setCellStyle(colStyle);
				cell.setCellValue((String) "");

				cell = row.createCell(colCounter++);
				cell.setCellStyle(colStyle);
				cell.setCellValue((String) "");

				cell = row.createCell(colCounter++);
				cell.setCellStyle(colStyle);
				cell.setCellValue((String) "");
			

			}

		}
	}
//		sheet.autoSizeColumn(0);
//		sheet.autoSizeColumn(1);
//		sheet.autoSizeColumn(2);
//		sheet.autoSizeColumn(3);
//		sheet.autoSizeColumn(4);
//		sheet.autoSizeColumn(5);
//		sheet.autoSizeColumn(6);
//		sheet.autoSizeColumn(7);
//		sheet.autoSizeColumn(8);
//		sheet.autoSizeColumn(9);
//		sheet.autoSizeColumn(10);
//		sheet.autoSizeColumn(11);
//		sheet.autoSizeColumn(12);
//		sheet.autoSizeColumn(13);
//		sheet.autoSizeColumn(14);
//		sheet.autoSizeColumn(15);
//		sheet.autoSizeColumn(16);
	
		}
		File file = null;
		FileOutputStream outputStream = null;
		try {
			file = File.createTempFile("rawdatareport", ".xlsx");
			outputStream = new FileOutputStream(file);
			wb.write(outputStream);
			outputStream.close();
			wb.close();
			wb.dispose();

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to generate raw data excel file.", e);
		} finally {
			if (wb != null) {
				try {
					wb.close();
				} catch (IOException e) {
					throw new RuntimeException("Unable to close data excel file ouput stream.", e);
				}
			}
		}
		return file;
	}

}
