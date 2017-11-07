package org.sdrc.udise.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.LocalDate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.sdrc.udise.domain.AcademicYear;
import org.sdrc.udise.domain.AggregationData;
import org.sdrc.udise.domain.Area;
import org.sdrc.udise.domain.MSTUser;
import org.sdrc.udise.domain.Role;
import org.sdrc.udise.domain.RoleFeaturePermissionScheme;
import org.sdrc.udise.domain.SchoolAggregatedData;
import org.sdrc.udise.domain.SchoolCurrentAggregatedData;
import org.sdrc.udise.domain.SchoolDetails;
import org.sdrc.udise.domain.SchoolLatLongLink;
import org.sdrc.udise.domain.StudentsDetails;
import org.sdrc.udise.domain.TypeDetail;
import org.sdrc.udise.domain.UserAreaMapping;
import org.sdrc.udise.domain.UserRoleFeaturePermissionMapping;
import org.sdrc.udise.model.AreaWebModel;
import org.sdrc.udise.model.DashboardDataModel;
import org.sdrc.udise.model.DashboardDropDownMenuModel;
import org.sdrc.udise.model.GoogleMapDataModel;
import org.sdrc.udise.model.PieChart;
import org.sdrc.udise.model.StackedLineChartModel;
import org.sdrc.udise.model.UserModel;
import org.sdrc.udise.model.ValueObject;
import org.sdrc.udise.repository.AcedemicYearRepository;
import org.sdrc.udise.repository.AggregationDataRepository;
import org.sdrc.udise.repository.AreaRepository;
import org.sdrc.udise.repository.MasterAreaRepository;
import org.sdrc.udise.repository.MstUserRepository;
import org.sdrc.udise.repository.RoleRepository;
import org.sdrc.udise.repository.SchoolAggregatedDataRepository;
import org.sdrc.udise.repository.SchoolCurrentAggregatedDataRepository;
import org.sdrc.udise.repository.SchoolDetailsRepository;
import org.sdrc.udise.repository.StudentDetailsRepository;
import org.sdrc.udise.repository.TypeDetailsRepository;
import org.sdrc.udise.repository.TypeRepository;
import org.sdrc.udise.repository.UserAreaMappingRepository;
import org.sdrc.udise.repository.UserRoleFeaturePermissionMappingRepository;
import org.sdrc.udise.util.Constants;
import org.sdrc.udise.util.HeaderFooter;
import org.sdrc.udise.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private TypeDetailsRepository typeDetailsRepository;

	@Autowired
	private SchoolDetailsRepository schoolDetailsRepository;

	@Autowired
	private MstUserRepository mstUserRepository;

	@Autowired
	private MessageDigestPasswordEncoder passwordEncoder;

	@Autowired
	private UserAreaMappingRepository userAreaMappingRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleFeaturePermissionMappingRepository userRoleFeaturePermissionMappingRepository;

	@Autowired
	private StateManager stateManager;

	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;

	@Autowired
	private AggregationDataRepository aggregationDataRepository;

	@Autowired
	private SchoolCurrentAggregatedDataRepository schoolCurrentAggregatedDataRepository;

	@Autowired
	private StudentDetailsRepository studentDetailsRepository;

	@Autowired
	private AcedemicYearRepository academicYearRepository;

	@Autowired
	private MasterAreaRepository masterAreaRepository;

	@Autowired
	private SchoolAggregatedDataRepository schoolAggregatedDataRepository;

	@Autowired
	private TypeRepository typeRepository;

	@Autowired
	private ResourceBundleMessageSource errorMessageSource;

	@Autowired
	private ServletContext context;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	private static DecimalFormat df = new DecimalFormat("0.0");

	@Override
	@Transactional
	// / pleasae dont uncomment it.Please get in touch with Harsh
	// Pratyush(harsh@sdrc.co.in) before uncommenting
	// dear brother/sister if u will uncomment this code it will start playing
	// with database
	// this code is used for the master data entry
	public boolean insertSchools() {/*
		try {

			FileInputStream fileinput = new FileInputStream(
					"D:/tms/AVINSAH SIR TRANSTION Template.xlsx");

			XSSFWorkbook workbook = new XSSFWorkbook(fileinput);

			LocalDate localdate = new LocalDate();
			int year = localdate.getYear();
			int monthOfYear = localdate.getMonthOfYear();

			int acedemicYear = year;
			if (monthOfYear < 4) {
				acedemicYear = year - 1;
			}

			AcademicYear academicYear = academicYearRepository
					.findByStartYear(String.valueOf(acedemicYear));

			Integer lastId = 37098;
			int k = 508;

			List<Area> areas = areaRepository.findByLevelOrderByAreaNameAsc(4);
			areas.addAll(areaRepository.findByLevelOrderByAreaNameAsc(5));

			Map<String, Area> areaMap = new HashMap<String, Area>();

			for (Area area : areas) {
				if (area.getLevel() == 4)
					areaMap.put(area.getAreaName().trim().toLowerCase() + "_"
							+ area.getLevel(), area);
				else
					areaMap.put(area.getAreaName().trim().toLowerCase() + "_"
							+ area.getLevel() + "_" + area.getParentAreaId(),
							area);
			}
			List<TypeDetail> typeDetails = typeDetailsRepository.findAll();

			Map<String, TypeDetail> schoolTypeMap = new HashMap<String, TypeDetail>();

			Map<String, TypeDetail> schoolAreaTypeMap = new HashMap<String, TypeDetail>();

			Map<String, TypeDetail> schoolManagementTypeMap = new HashMap<String, TypeDetail>();

			Map<String, TypeDetail> schoolOtherTypeMap = new HashMap<String, TypeDetail>();

			Map<String, TypeDetail> schoolAffiliationTypeMap = new HashMap<String, TypeDetail>();

			for (TypeDetail typeDetail : typeDetails) {
				int i = typeDetail.getType().getTypeId();

				switch (i) {
				case 2:
					schoolTypeMap.put(typeDetail.getTypeDetail().trim()
							.toLowerCase(), typeDetail);
					break;

				case 8:
					schoolManagementTypeMap.put(typeDetail.getTypeDetail()
							.trim().toLowerCase(), typeDetail);
					break;

				case 9:
					schoolAreaTypeMap.put(typeDetail.getTypeDetail().trim()
							.toLowerCase(), typeDetail);
					break;

				case 10:
					schoolOtherTypeMap.put(typeDetail.getTypeDetail().trim()
							.toLowerCase(), typeDetail);
					break;

				case 11:
					schoolAffiliationTypeMap.put(typeDetail.getTypeDetail(),
							typeDetail);
					break;
				}
			}

			Map<String, String> schoolAffilationType = new HashMap<String, String>();

			List<Role> roles = roleRepository.findAll();

			Map<Integer, Role> roleMap = new HashMap<Integer, Role>();
			for (Role role : roles) {
				roleMap.put(role.getRoleId(), role);
			}

			UserAreaMapping newAreaMapping = new UserAreaMapping();
			Role role = new Role();

			List<SchoolAggregatedData> schoolAggregatedDatas = new ArrayList<SchoolAggregatedData>();
			List<SchoolCurrentAggregatedData> schoolCurrentAggregatedDatas = new ArrayList<SchoolCurrentAggregatedData>();
			List<AggregationData> aggregationDatas = new ArrayList<AggregationData>();

			schoolAffilationType.put("1", "C.B.S.E");
			schoolAffilationType.put("2", "State Board");
			schoolAffilationType.put("3", "I.C.S.E");
			schoolAffilationType.put("4", "International Board");
			schoolAffilationType.put("5", "Others");
			System.out.println("School query started");
			List<SchoolDetails> schoolDetails = schoolDetailsRepository
					.findByUdiseCodeIsNotNull();
			System.out.println("School query ended");
			 List<Area> newBlocks=new ArrayList<Area>(); 
			// List<SchoolDetails> schoolDetailsList=new
			// ArrayList<SchoolDetails>();
			Map<String, SchoolDetails> schoolDetailsMap = new HashMap<String, SchoolDetails>();

			List<String> typeDetailIds = Arrays.asList(applicationMessageSource
					.getMessage(Constants.Web.UPPER_PRIMARY_SCHOOL_IDS, null,
							null).split(","));

			List<Integer> upperPrimarySchoolTypeIds = new ArrayList<Integer>();
			for (String typeDetailId : typeDetailIds) {
				upperPrimarySchoolTypeIds.add(Integer.parseInt(typeDetailId));
			}

			for (SchoolDetails schoolDetail : schoolDetails) {
				schoolDetailsMap.put(schoolDetail.getUdiseCode(), schoolDetail);
			}

			XSSFSheet schoolSheet = workbook.getSheet("Sheet1");

			for (int i = 1; i <= 62; i++) {
				Row row = schoolSheet.getRow(i);

				// DISTRICT_CODE DISTRICT_NAME BLKCD BLKNAME CLUCD CLUNAME VILCD
				// VILNAME SCHCD SCHNAME SCHMGT_DESC SCHMGT SCHCAT RURURB
				// SCHCAT_DESC RURURB_1 SCHTYPE ESTDYEAR Affiliation of board
				// Secondary level Affiliation of board Higher Secondary level

				Cell udiseCode = row.getCell(8);
				Cell distID = row.getCell(1);
				Cell blockId = row.getCell(3);
				Cell schoolName = row.getCell(9);
				Cell schoolManagementType = row.getCell(10);
				Cell schoolTypeId = row.getCell(14);
				Cell schoolRularUrban = row.getCell(15);
				Cell schoolTypeOtherCell = row.getCell(16);
				Cell schoolEstdYr = row.getCell(17);
				Cell schoolAffilationnBoardSecondary = row.getCell(18);
				Cell schoolAffilationnBoardHigherSecondary = row.getCell(19);

				if (!schoolTypeId.getStringCellValue().contains("No Response")) {
					SchoolDetails schoolDetail;
					if (schoolDetailsMap.containsKey(String.valueOf(
							udiseCode.getStringCellValue()).trim())) {
						schoolDetail = schoolDetailsMap.get(String.valueOf(
								udiseCode.getStringCellValue()).trim());
						// schoolDetail.setBlock(areaMap.get(blockId.getStringCellValue().trim().toLowerCase()+"_"+5+areaMap.get(distID.getStringCellValue().trim().toLowerCase()+"_"+4)));
						// schoolDetail.setDistrictId(areaMap.get(distID.getStringCellValue().trim().toLowerCase()+"_"+4));
					}

					else {
						schoolDetail = new SchoolDetails();
						schoolDetail.setSchoolName(schoolName
								.getStringCellValue().trim());
						schoolDetail.setUdiseCode(String.valueOf(
								udiseCode.getStringCellValue()).trim());
						if (areaMap.containsKey(blockId.getStringCellValue()
								.trim().toLowerCase()
								+ "_"
								+ 5
								+ "_"
								+ areaMap.get(
										distID.getStringCellValue().trim()
												.toLowerCase()
												+ "_" + 4).getAreaId())) {
							schoolDetail.setBlock(areaMap.get(blockId
									.getStringCellValue().trim().toLowerCase()
									+ "_"
									+ 5
									+ "_"
									+ areaMap.get(
											distID.getStringCellValue().trim()
													.toLowerCase()
													+ "_" + 4).getAreaId()));
						} else {
							Area newBlock = new Area();

							newBlock.setAreaId(lastId++);
							newBlock.setAreaName(blockId.getStringCellValue()
									.trim());
							newBlock.setLevel(5);
							Area parentArea = areaMap.get(distID
									.getStringCellValue().trim().toLowerCase()
									+ "_" + 4);
							newBlock.setParentAreaId(parentArea.getAreaId());
							newBlock.setLive(parentArea.isLive());

							Area newArea = areaRepository.save(newBlock);
							areaMap.put(
									newArea.getAreaName().trim().toLowerCase()
											+ "_" + 5 + "_"
											+ newArea.getParentAreaId(),
									newArea);
							if (newArea.isLive()) {
								AggregationData aggregationData = new AggregationData();

								aggregationData.setAcademicYearId(academicYear);
								aggregationData.setAreaId(newArea);

								aggregationData.setAdmittedBoys(0);
								aggregationData.setAdmittedGirls(0);
								aggregationData.setAdmittedStudents(0);
								aggregationData.setAdmittedStudentsGen(0);
								aggregationData.setAdmittedStudentsMinority(0);
								aggregationData.setAdmittedStudentsOBC(0);
								aggregationData.setAdmittedStudentsSC(0);
								aggregationData.setAdmittedStudentsST(0);
								aggregationData.setAdmittedThirdGender(0);

								aggregationData.setLeftoutBoys(0);
								aggregationData.setLeftoutGirls(0);
								aggregationData.setLefoutStudents(0);
								aggregationData.setLeftoutStudentsGen(0);
								aggregationData.setLeftoutStudentsMinority(0);
								aggregationData.setLeftoutStudentsOBC(0);
								aggregationData.setLeftoutStudentsSC(0);
								aggregationData.setLeftoutStudentsST(0);
								aggregationData.setLeftoutThirdGender(0);
								aggregationDatas.add(aggregationData);

								UserAreaMapping areaMapping = new UserAreaMapping();

								MSTUser mstUser = new MSTUser();

								mstUser.setUserId(k++);
								mstUser.setCreatedDate(new Timestamp(
										new java.util.Date().getTime()));
								mstUser.setIsLive(true);
								mstUser.setName(newArea.getAreaName() + "_"
										+ newArea.getParentAreaId()
										+ newArea.getLevel());
								mstUser.setPassword(passwordEncoder.encodePassword(
										newArea.getAreaName() + "_"
												+ newArea.getParentAreaId()
												+ newArea.getLevel(),
										newArea.getAreaName() + "_001"));
								mstUser.setUserName(newArea.getAreaName() + "_"
										+ newArea.getParentAreaId()
										+ newArea.getLevel());

								MSTUser newUser = mstUserRepository
										.save(mstUser);

								areaMapping.setArea(newArea);
								areaMapping.setIsLive(true);
								areaMapping.setUser(newUser);
								areaMapping.setCreatedDate(new Timestamp(
										new java.util.Date().getTime()));
								// userAreaMappings.add(areaMapping);
								newAreaMapping = userAreaMappingRepository
										.save(areaMapping);

								UserRoleFeaturePermissionMapping featurePermissionMapping = new UserRoleFeaturePermissionMapping();

								role = roleMap.get(4);

								for (RoleFeaturePermissionScheme roleFeaturePermissionScheme : role
										.getRoleFeaturePermissionSchemes()) {
									featurePermissionMapping = new UserRoleFeaturePermissionMapping();
									featurePermissionMapping
											.setUserAreaMapping(newAreaMapping);
									featurePermissionMapping
											.setRoleFeaturePermissionScheme(roleFeaturePermissionScheme);
									featurePermissionMapping
											.setUpdatedBy("harsh");
									featurePermissionMapping
									
											.setUpdatedDate(new Timestamp(
													new java.util.Date()
															.getTime()));
									userRoleFeaturePermissionMappingRepository
											.save(featurePermissionMapping);
								}

								role = new Role();
								role = roleMap.get(5);

								UserAreaMapping areaMapping1 = new UserAreaMapping();

								MSTUser mstUser1 = new MSTUser();
								mstUser1.setUserId(k++);
								mstUser1.setCreatedDate(new Timestamp(
										new java.util.Date().getTime()));
								mstUser1.setIsLive(true);
								mstUser1.setName("deo_" + newArea.getAreaName()
										+ "_" + newArea.getParentAreaId()
										+ newArea.getLevel());
								mstUser1.setPassword(passwordEncoder.encodePassword(
										"deo_" + newArea.getAreaName() + "_"
												+ newArea.getParentAreaId()
												+ newArea.getLevel(), "deo_"
												+ newArea.getAreaName()
												+ "_001"));
								mstUser1.setUserName("deo_"
										+ newArea.getAreaName() + "_"
										+ newArea.getParentAreaId()
										+ newArea.getLevel());

								MSTUser newUser1 = mstUserRepository
										.save(mstUser1);

								areaMapping1.setArea(newArea);
								areaMapping1.setIsLive(true);
								areaMapping1.setUser(newUser1);
								areaMapping1.setCreatedDate(new Timestamp(
										new java.util.Date().getTime()));
								newAreaMapping = new UserAreaMapping();
								newAreaMapping = userAreaMappingRepository
										.save(areaMapping1);

								for (RoleFeaturePermissionScheme roleFeaturePermissionScheme : role
										.getRoleFeaturePermissionSchemes()) {
									featurePermissionMapping = new UserRoleFeaturePermissionMapping();
									featurePermissionMapping
											.setUserAreaMapping(newAreaMapping);
									featurePermissionMapping
											.setRoleFeaturePermissionScheme(roleFeaturePermissionScheme);
									featurePermissionMapping
											.setUpdatedBy("harsh");
									featurePermissionMapping
											.setUpdatedDate(new Timestamp(
													new java.util.Date()
															.getTime()));

									userRoleFeaturePermissionMappingRepository
											.save(featurePermissionMapping);
								}

							}
							schoolDetail.setBlock(newArea);
							schoolDetail.setDistrictId(parentArea);
						}
						schoolDetail.setDistrictId(areaMap.get(distID
								.getStringCellValue().trim().toLowerCase()
								+ "_" + 4));
//						schoolDetail.setSchoolType(schoolTypeMap
//								.get(schoolTypeId.getStringCellValue().trim()
//										.toLowerCase()));
						schoolDetail.setCreatedDate(new Timestamp(
								new java.util.Date().getTime()));
						schoolDetail.setLive(true);

						// to be edited
						double schoolTypeSecondary=0,schoolTypeHigher=0;
						try
						{
						 schoolTypeHigher = schoolAffilationnBoardHigherSecondary
								.getNumericCellValue();
						}
						catch(Exception e)
						{
						schoolTypeHigher =Double.valueOf(Double.valueOf( schoolAffilationnBoardHigherSecondary
						.getStringCellValue()));
						}
						try
						{
						 schoolTypeSecondary = schoolAffilationnBoardSecondary
								.getNumericCellValue();
						}
						catch(Exception e)
						{
							schoolTypeSecondary =Double.valueOf(Double.valueOf( schoolAffilationnBoardSecondary
									.getStringCellValue()));
									}
						
						if (schoolTypeHigher > 0) {
							schoolDetail
									.setAffilationTypeBoardHigherSecondary(schoolAffiliationTypeMap.get(schoolAffilationType.get(String
											.valueOf((int) schoolTypeHigher))));
						}

						if (schoolTypeSecondary > 0) {
							schoolDetail
									.setAffilationTypeBoardSecondary(schoolAffiliationTypeMap.get(schoolAffilationType.get(String
											.valueOf((int) schoolTypeSecondary))));
						}
						//
						if (schoolEstdYr != null) {
							try {
								schoolDetail.setEstdYear(schoolEstdYr
										.getStringCellValue());
							} catch (Exception e) {
								schoolDetail.setEstdYear(String
										.valueOf((int) schoolEstdYr
												.getNumericCellValue()));
							}
						}
						if (schoolRularUrban != null)
							schoolDetail.setSchoolAreaType(schoolAreaTypeMap
									.get(schoolRularUrban.getStringCellValue()
											.trim().toLowerCase()));
						if (schoolTypeOtherCell != null && schoolTypeOtherCell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC)
							schoolDetail
									.setSchoolTypeOther(schoolOtherTypeMap
											.get(String
													.valueOf(
															(int) schoolTypeOtherCell
																	.getNumericCellValue())
													.trim().toLowerCase()));

						if (schoolManagementType != null)
							schoolDetail
									.setSchoolManagementType(schoolManagementTypeMap
											.get(schoolManagementType
													.getStringCellValue()
													.trim().toLowerCase()));
					}

					schoolDetail.setSchoolType(new TypeDetail(6));
					SchoolDetails schoolDetailSave = schoolDetailsRepository
							.save(schoolDetail);

					if (schoolDetailSave.getSchoolLatLongLinks() == null) {
						// SchoolLatLongLink schoolLatLongLink=new
						// SchoolLatLongLink();

						SchoolLatLongLink latLongLink = new SchoolLatLongLink();
						latLongLink.setLive(true);
						latLongLink.setSchool(schoolDetailSave);
						JSONArray jsonArray = new JSONArray();

						JSONObject jsonObject = new JSONObject();

						jsonObject.put("5", jsonArray);
						jsonObject.put("7", jsonArray);
						jsonObject.put("10", jsonArray);

						latLongLink.setDistanceJSON(jsonObject.toString());

						typeRepository.save(latLongLink);

					}

					if (upperPrimarySchoolTypeIds.contains(schoolDetailSave
							.getSchoolType().getTypeDetailId())
							&& schoolDetailSave.getSchoolAggregatedDatas() == null) {
						SchoolAggregatedData aggregationData = new SchoolAggregatedData();

						aggregationData.setAcademicYearId(academicYear);
						aggregationData.setSchool(schoolDetailSave);

						aggregationData.setAdmittedBoys(0);
						aggregationData.setAdmittedGirls(0);
						aggregationData.setAdmittedStudents(0);
						aggregationData.setAdmittedStudentsGen(0);
						aggregationData.setAdmittedStudentsMinority(0);
						aggregationData.setAdmittedStudentsOBC(0);
						aggregationData.setAdmittedStudentsSC(0);
						aggregationData.setAdmittedStudentsST(0);
						aggregationData.setAdmittedThirdGender(0);

						aggregationData.setLeftoutBoys(0);
						aggregationData.setLeftoutGirls(0);
						aggregationData.setLefoutStudents(0);
						aggregationData.setLeftoutStudentsGen(0);
						aggregationData.setLeftoutStudentsMinority(0);
						aggregationData.setLeftoutStudentsOBC(0);
						aggregationData.setLeftoutStudentsSC(0);
						aggregationData.setLeftoutStudentsST(0);
						aggregationData.setLeftoutThirdGender(0);

						schoolAggregatedDatas.add(aggregationData);

						if(schoolDetailSave.getSchoolCurrentAggregatedData()== null)
						{
						SchoolCurrentAggregatedData schoolCurrentAggregatedData = new SchoolCurrentAggregatedData();
						schoolCurrentAggregatedData
								.setAcademicYearId(academicYear);
						schoolCurrentAggregatedData.setAdmittedStudents(0);
						schoolCurrentAggregatedData.setLefoutStudents(0);
						schoolCurrentAggregatedData.setSchool(schoolDetailSave);
						schoolCurrentAggregatedData.setVersion(0);

						schoolCurrentAggregatedDatas
								.add(schoolCurrentAggregatedData);
						}
					}
				}

			}
			schoolCurrentAggregatedDataRepository
					.save(schoolCurrentAggregatedDatas);
			schoolAggregatedDataRepository.save(schoolAggregatedDatas);
			aggregationDataRepository.save(aggregationDatas);
			workbook.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

				*/
		return false;}

	@Override
	public DashboardDataModel fetchDivisionWiseData(int divisionId) {

		DashboardDataModel dashboardDataModel = new DashboardDataModel();
		List<AggregationData> aggregateData = new ArrayList<AggregationData>();

		List<Map<String, String>> divisionalDatas = new ArrayList<Map<String, String>>();
		LocalDate localdate = new LocalDate();

		int total = 0, leftout = 0, admitted = 0, admittedBoys = 0, admittedGirls = 0, admittedThirdGeneder = 0, leftoutBoys = 0, leftoutGirls = 0, leftoutThirdGender = 0, admittedGen = 0, admittedobc = 0, admittedSc = 0, admittedSt = 0, admittedMinority = 0, leftoutGen = 0, leftoutobc = 0, leftoutSc = 0, leftoutSt = 0, leftoutMinority = 0;

		int year = localdate.getYear();
		int monthOfYear = localdate.getMonthOfYear();

		int acedemicYear = year;
		if (monthOfYear < 4) {
			acedemicYear = year - 1;
		}

		acedemicYear = 2017;
		String selection = "Uttar Pradesh ";

		dashboardDataModel.setStateName("Uttar Pradesh ");
		// if divisionId is 0 then we have to send aggregated data of the all
		// the division
		if (divisionId == 0) {

			aggregateData = aggregationDataRepository
					.findByAreaIdLevelAndAcademicYearIdStartYearOrderByAreaIdAreaNameAsc(
							Integer.parseInt(applicationMessageSource
									.getMessage(Constants.Web.DIVISIONAL_LEVEL,
											null, null)), String
									.valueOf(acedemicYear));

			for (AggregationData aggregationData : aggregateData) {
				Map<String, String> divisionalData = new LinkedHashMap<String, String>();

				divisionalData.put("Division ", aggregationData.getAreaId()
						.getAreaName());
				divisionalData.put(
						"Total ",
						String.valueOf(aggregationData.getAdmittedStudents()
								+ aggregationData.getLefoutStudents()));
				if (aggregationData.getAdmittedStudents()
						+ aggregationData.getLefoutStudents() > 0) {
					divisionalData.put("Enrolled ", String
							.valueOf(aggregationData.getAdmittedStudents()));
					divisionalData
							.put("Left Out", String.valueOf(aggregationData
									.getLefoutStudents()));
					divisionalData.put("Left Out %", String
							.valueOf(aggregationData.findPercentOfLeftOut()));
				} else {
					divisionalData.put("Enrolled ", "-");
					divisionalData.put("Left Out", "-");
					divisionalData.put("Left Out %", "-");

				}
				// adding total for all the division
				total += aggregationData.getAdmittedStudents()
						+ aggregationData.getLefoutStudents();

				// adding admitted for all the division
				admitted += aggregationData.getAdmittedStudents();

				// adding leftout for all the division
				leftout += aggregationData.getLefoutStudents();

				admittedBoys += aggregationData.getAdmittedBoys();

				admittedGirls += aggregationData.getAdmittedGirls();

				admittedThirdGeneder += aggregationData
						.getAdmittedThirdGender();

				admittedGen += aggregationData.getAdmittedStudentsGen();

				admittedobc += aggregationData.getAdmittedStudentsOBC();

				admittedSc += aggregationData.getAdmittedStudentsSC();

				admittedSt += aggregationData.getAdmittedStudentsST();

				admittedMinority += aggregationData
						.getAdmittedStudentsMinority();

				leftoutBoys += aggregationData.getLeftoutBoys();

				leftoutGirls += aggregationData.getLeftoutGirls();

				leftoutThirdGender += aggregationData.getLeftoutThirdGender();

				leftoutGen += aggregationData.getLeftoutStudentsGen();

				leftoutobc += aggregationData.getLeftoutStudentsOBC();

				leftoutSc += aggregationData.getLeftoutStudentsSC();

				leftoutSt += aggregationData.getLeftoutStudentsST();

				leftoutMinority += aggregationData.getLeftoutStudentsMinority();

				divisionalDatas.add(divisionalData);
			}
		}

		// else if divisionId is not 0 then we have to send aggregated data of
		// the all the district within the division
		else {

			Area area = areaRepository.findByAreaId(divisionId);

			selection += " > " + area.getAreaName();
			dashboardDataModel.setDivisionName(area.getAreaName());

			aggregateData = aggregationDataRepository
					.findByAreaIdParentAreaIdAndAcademicYearIdStartYearOrderByAreaIdAreaNameAsc(
							divisionId, String.valueOf(acedemicYear));

			for (AggregationData aggregationData : aggregateData) {
				Map<String, String> divisionalData = new LinkedHashMap<String, String>();

				divisionalData.put("District ", aggregationData.getAreaId()
						.getAreaName());
				divisionalData.put(
						"Total ",
						String.valueOf(aggregationData.getAdmittedStudents()
								+ aggregationData.getLefoutStudents()));
				if (aggregationData.getAdmittedStudents()
						+ aggregationData.getLefoutStudents() > 0) {
					divisionalData.put("Enrolled ", String
							.valueOf(aggregationData.getAdmittedStudents()));
					divisionalData
							.put("Left Out", String.valueOf(aggregationData
									.getLefoutStudents()));
					divisionalData.put("Left Out %", String
							.valueOf(aggregationData.findPercentOfLeftOut()));
				} else {
					divisionalData.put("Enrolled ", "-");
					divisionalData.put("Left Out", "-");
					divisionalData.put("Left Out %", "-");

				}

				// adding total for all the district within the division
				total += aggregationData.getAdmittedStudents()
						+ aggregationData.getLefoutStudents();

				// adding admitted for all the district within the division
				admitted += aggregationData.getAdmittedStudents();

				// adding leftout for all the district within the division
				leftout += aggregationData.getLefoutStudents();

				admittedBoys += aggregationData.getAdmittedBoys();

				admittedGirls += aggregationData.getAdmittedGirls();

				admittedThirdGeneder += aggregationData
						.getAdmittedThirdGender();

				admittedGen += aggregationData.getAdmittedStudentsGen();

				admittedobc += aggregationData.getAdmittedStudentsOBC();

				admittedSc += aggregationData.getAdmittedStudentsSC();

				admittedSt += aggregationData.getAdmittedStudentsST();

				admittedMinority += aggregationData
						.getAdmittedStudentsMinority();

				leftoutBoys += aggregationData.getLeftoutBoys();

				leftoutGirls += aggregationData.getLeftoutGirls();

				leftoutThirdGender += aggregationData.getLeftoutThirdGender();

				leftoutGen += aggregationData.getLeftoutStudentsGen();

				leftoutobc += aggregationData.getLeftoutStudentsOBC();

				leftoutSc += aggregationData.getLeftoutStudentsSC();

				leftoutSt += aggregationData.getLeftoutStudentsST();

				leftoutMinority += aggregationData.getLeftoutStudentsMinority();

				divisionalDatas.add(divisionalData);
			}
		}

		Map<String, String> aggregatedDataOfSelectedArea = new HashMap<String, String>();
		aggregatedDataOfSelectedArea.put("Total ", String.valueOf(total));
		aggregatedDataOfSelectedArea.put("Admitted ", String.valueOf(admitted));
		aggregatedDataOfSelectedArea.put("Left Out", String.valueOf(leftout));

		List<PieChart> pieCharts = new ArrayList<PieChart>();

		// if total >0 then only the pie chart will be visible
		if (total > 0) {
			PieChart pieChart = new PieChart();

			pieChart.setLabel("Left Out");
			if (leftout > 0)
				if ((leftout * 100) / total > 0)
					pieChart.setValue(df.format((leftout * 100) / total));
				else
					pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution");
			pieCharts.add(pieChart);

			pieChart = new PieChart();

			pieChart.setLabel("Enrolled");
			if (admitted > 0)
				pieChart.setValue(df.format((admitted * 100) / total));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution");

			pieCharts.add(pieChart);
		}

		// boy pie chart
		List<PieChart> boyPieChart = new ArrayList<PieChart>();

		if ((admittedBoys + leftoutBoys) > 0) {
			int boysTotal = admittedBoys + leftoutBoys;
			PieChart pieChart = new PieChart();

			pieChart.setLabel("Enrolled");
			if (admittedBoys > 0)
				pieChart.setValue(df.format((admittedBoys * 100) / boysTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution Boys");
			boyPieChart.add(pieChart);

			pieChart = new PieChart();

			pieChart.setLabel("Left Out");
			if (leftoutBoys > 0)
				pieChart.setValue(df.format((leftoutBoys * 100) / boysTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution Boys");

			boyPieChart.add(pieChart);
			Collections.reverse(boyPieChart);
		}

		// girl pie chart
		List<PieChart> girlsPiechart = new ArrayList<PieChart>();

		if ((admittedGirls + leftoutGirls) > 0) {
			int girlsTotal = admittedGirls + leftoutGirls;
			PieChart pieChart = new PieChart();

			pieChart.setLabel("Enrolled");
			if (admittedGirls > 0)
				pieChart.setValue(df.format((admittedGirls * 100) / girlsTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution of Girls");
			girlsPiechart.add(pieChart);

			pieChart = new PieChart();

			pieChart.setLabel("Left Out");
			if (leftoutGirls > 0)
				pieChart.setValue(df.format((leftoutGirls * 100) / girlsTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution of Girls");

			girlsPiechart.add(pieChart);
			Collections.reverse(girlsPiechart);
		}

		// third geneder pie chart
		List<PieChart> thirdGenderPieChart = new ArrayList<PieChart>();

		if ((admittedThirdGeneder + leftoutThirdGender) > 0) {
			int thirdgenderTotal = admittedThirdGeneder + leftoutThirdGender;
			PieChart pieChart = new PieChart();

			pieChart.setLabel("Enrolled");
			if (admittedThirdGeneder > 0)
				pieChart.setValue(df.format((admittedThirdGeneder * 100)
						/ thirdgenderTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution of Third Gender");
			thirdGenderPieChart.add(pieChart);

			pieChart = new PieChart();

			pieChart.setLabel("Left Out");
			if (leftoutThirdGender > 0)
				pieChart.setValue(df.format((leftoutThirdGender * 100)
						/ thirdgenderTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution of Third Gender");

			thirdGenderPieChart.add(pieChart);
			Collections.reverse(thirdGenderPieChart);
		}

		// / general caste pie
		List<StackedLineChartModel> stackedChart=new ArrayList<StackedLineChartModel>();
		StackedLineChartModel genCastePieChart = new StackedLineChartModel();

		if ((admittedGen + leftoutGen) > 0) {
			double genCasteTotal = admittedGen + leftoutGen;
	
			genCastePieChart.setLabel("General");
			if (admittedGen > 0)
				genCastePieChart.setEnrolled(df
						.format((admittedGen * 100) / genCasteTotal));
			else
				genCastePieChart.setEnrolled("0.0");
		
			if (leftoutGen > 0)
				genCastePieChart.setLeftout(df.format((leftoutGen * 100) / genCasteTotal));
			else
				genCastePieChart.setLeftout("0.0");
	
			stackedChart.add(genCastePieChart);

			
		}

		// OBC Caste pie
		StackedLineChartModel obcCastePieChart = new StackedLineChartModel();

		if ((admittedobc + leftoutobc) > 0) {
			double obcCasteTotal = admittedobc + leftoutobc;
			obcCastePieChart.setLabel("OBC");
			if (admittedobc > 0)
				obcCastePieChart.setEnrolled(df
						.format((admittedobc * 100) / obcCasteTotal));
			else
				obcCastePieChart.setEnrolled("0.0");
			

			//obcCastePieChart.setLeftout("Left Out");
			if (leftoutobc > 0)
				obcCastePieChart.setLeftout(df.format((leftoutobc * 100) / obcCasteTotal));
			else
				obcCastePieChart.setLeftout("0.0");

			stackedChart.add(obcCastePieChart);
		}

		// sc caste pie chart

		StackedLineChartModel scCastePieChart = new StackedLineChartModel();

		if ((admittedSc + leftoutSc) > 0) {
			double scCasteTotal = admittedSc + leftoutSc;
			scCastePieChart.setLabel("SC");
			if (admittedSc > 0)
				scCastePieChart.setEnrolled(df
						.format((admittedSc * 100) / scCasteTotal));
			else
				scCastePieChart.setEnrolled("0.0");
			

			//obcCastePieChart.setLeftout("Left Out");
			if (leftoutSc > 0)
				scCastePieChart.setLeftout(df.format((leftoutSc * 100) / scCasteTotal));
			else
				scCastePieChart.setLeftout("0.0");

			stackedChart.add(scCastePieChart);
		}
		StackedLineChartModel stCastePieChart = new StackedLineChartModel();

		if ((admittedSt + leftoutSt) > 0) {
			double stCasteTotal = admittedSt + leftoutSt;
			stCastePieChart.setLabel("ST");
			if (admittedSt > 0)
				stCastePieChart.setEnrolled(df
						.format((admittedSt * 100) / stCasteTotal));
			else
				stCastePieChart.setEnrolled("0.0");
			

			//obcCastePieChart.setLeftout("Left Out");
			if (leftoutSt > 0)
				stCastePieChart.setLeftout(df.format((leftoutSt * 100) / stCasteTotal));
			else
				stCastePieChart.setLeftout("0.0");

			stackedChart.add(stCastePieChart);
		}

		// minorty data
		StackedLineChartModel minortyCastePieChart = new StackedLineChartModel();

		if ((admittedMinority + leftoutMinority) > 0) {
			double minorityCasteTotal = admittedMinority + leftoutMinority;
			minortyCastePieChart.setLabel("Minority");
			if (admittedMinority > 0)
				minortyCastePieChart.setEnrolled(df
						.format((admittedMinority * 100) / minorityCasteTotal));
			else
				minortyCastePieChart.setEnrolled("0.0");
			

			//obcCastePieChart.setLeftout("Left Out");
			if (leftoutMinority > 0)
				minortyCastePieChart.setLeftout(df.format((leftoutMinority * 100) / minorityCasteTotal));
			else
				minortyCastePieChart.setLeftout("0.0");

			stackedChart.add(minortyCastePieChart);
		}

		List<List<PieChart>> listOfListPieChart = new ArrayList<List<PieChart>>();
		if (pieCharts.size() > 0)
			listOfListPieChart.add(pieCharts);

		List<List<PieChart>> genderPieChart = new ArrayList<List<PieChart>>();
		if (boyPieChart.size() > 0)
			genderPieChart.add(boyPieChart);
		if (girlsPiechart.size() > 0)
			genderPieChart.add(girlsPiechart);
		if (thirdGenderPieChart.size() > 0)
			genderPieChart.add(thirdGenderPieChart);


		dashboardDataModel.setPieChartData(listOfListPieChart);

		dashboardDataModel.setGenderPieChartData(genderPieChart);

		dashboardDataModel.setCastePieChartData(stackedChart);

		dashboardDataModel.setDataTableData(divisionalDatas);

		dashboardDataModel.setTotalData(aggregatedDataOfSelectedArea);

		dashboardDataModel.setSelection(selection);

		dashboardDataModel.setLastUpdatedDate(sdf.format(aggregateData.get(0)
				.getLastUpdatedDate()));

		return dashboardDataModel;
	}

	@Override
	public DashboardDataModel fetchDistrictWiseData(int districtId) {

		DashboardDataModel dashboardDataModel = new DashboardDataModel();
		int total = 0, leftout = 0, admitted = 0, admittedBoys = 0, admittedGirls = 0, admittedThirdGeneder = 0, leftoutBoys = 0, leftoutGirls = 0, leftoutThirdGender = 0, admittedGen = 0, admittedobc = 0, admittedSc = 0, admittedSt = 0, admittedMinority = 0, leftoutGen = 0, leftoutobc = 0, leftoutSc = 0, leftoutSt = 0, leftoutMinority = 0;

		List<AggregationData> aggregateData = new ArrayList<AggregationData>();

		List<Map<String, String>> districtDatas = new ArrayList<Map<String, String>>();
		LocalDate localdate = new LocalDate();
		int year = localdate.getYear();
		int monthOfYear = localdate.getMonthOfYear();

		int acedemicYear = year;
		if (monthOfYear < 4) {
			acedemicYear = year - 1;
		}

		acedemicYear = 2017;
		String selection = "Uttar Pradesh ";
		dashboardDataModel.setStateName("Uttar Pradesh ");
		List<Area> areaList = areaRepository.findAll();

		Map<Integer, Area> areaMap = new HashMap<Integer, Area>();
		for (Area area : areaList) {
			areaMap.put(area.getAreaId(), area);
		}

		UserModel userModel = (UserModel) stateManager
				.getValue(Constants.Web.USER_PRINCIPAL);
		/* int userAreaLevelId=userModel.getAreaLevel(); */

		// district id will only be zero in case of the logged in user is
		// divisional level
		// if districtId==0 then we will bring the data of all the district
		// within the logged in user division
		if (districtId == 0) {
			int divisionId = userModel.getAreaId();

			selection += " > " + areaMap.get(divisionId).getAreaName();
			dashboardDataModel.setDivisionName(areaMap.get(divisionId)
					.getAreaName());

			aggregateData = aggregationDataRepository
					.findByAreaIdParentAreaIdAndAcademicYearIdStartYearOrderByAreaIdAreaNameAsc(
							divisionId, String.valueOf(acedemicYear));

			for (AggregationData aggregationData : aggregateData) {
				Map<String, String> distrcitData = new LinkedHashMap<String, String>();

				distrcitData.put("District ", aggregationData.getAreaId()
						.getAreaName());
				distrcitData.put(
						"Total ",
						String.valueOf(aggregationData.getAdmittedStudents()
								+ aggregationData.getLefoutStudents()));
				if (aggregationData.getAdmittedStudents()
						+ aggregationData.getLefoutStudents() > 0) {
					distrcitData.put("Enrolled ", String
							.valueOf(aggregationData.getAdmittedStudents()));
					distrcitData
							.put("Left Out", String.valueOf(aggregationData
									.getLefoutStudents()));
					distrcitData.put("Left Out %", String
							.valueOf(aggregationData.findPercentOfLeftOut()));
				} else {
					distrcitData.put("Enrolled ", "-");
					distrcitData.put("Left Out", "-");
					distrcitData.put("Left Out %", "-");
				}
				// adding total for all the district within the division
				total += aggregationData.getAdmittedStudents()
						+ aggregationData.getLefoutStudents();

				// adding admitted for all the district within the division
				admitted += aggregationData.getAdmittedStudents();

				// adding leftout for all the district within the division
				leftout += aggregationData.getLefoutStudents();

				admittedBoys += aggregationData.getAdmittedBoys();

				admittedGirls += aggregationData.getAdmittedGirls();

				admittedThirdGeneder += aggregationData
						.getAdmittedThirdGender();

				admittedGen += aggregationData.getAdmittedStudentsGen();

				admittedobc += aggregationData.getAdmittedStudentsOBC();

				admittedSc += aggregationData.getAdmittedStudentsSC();

				admittedSt += aggregationData.getAdmittedStudentsST();

				admittedMinority += aggregationData
						.getAdmittedStudentsMinority();

				leftoutBoys += aggregationData.getLeftoutBoys();

				leftoutGirls += aggregationData.getLeftoutGirls();

				leftoutThirdGender += aggregationData.getLeftoutThirdGender();

				leftoutGen += aggregationData.getLeftoutStudentsGen();

				leftoutobc += aggregationData.getLeftoutStudentsOBC();

				leftoutSc += aggregationData.getLeftoutStudentsSC();

				leftoutSt += aggregationData.getLeftoutStudentsST();

				leftoutMinority += aggregationData.getLeftoutStudentsMinority();

				districtDatas.add(distrcitData);
			}

		}

		else {

			selection += " > "
					+ areaMap.get(areaMap.get(districtId).getParentAreaId())
							.getAreaName();
			selection += " > " + areaMap.get(districtId).getAreaName();
			dashboardDataModel.setDivisionName(areaMap.get(
					areaMap.get(districtId).getParentAreaId()).getAreaName());
			dashboardDataModel.setDistrictName(areaMap.get(districtId)
					.getAreaName());

			aggregateData = aggregationDataRepository
					.findByAreaIdParentAreaIdAndAcademicYearIdStartYearOrderByAreaIdAreaNameAsc(
							districtId, String.valueOf(acedemicYear));

			for (AggregationData aggregationData : aggregateData) {
				Map<String, String> blockData = new LinkedHashMap<String, String>();

				blockData.put("Block ", aggregationData.getAreaId()
						.getAreaName());
				blockData.put(
						"Total ",
						String.valueOf(aggregationData.getAdmittedStudents()
								+ aggregationData.getLefoutStudents()));
				if (aggregationData.getAdmittedStudents()
						+ aggregationData.getLefoutStudents() > 0) {
					blockData.put("Enrolled ", String.valueOf(aggregationData
							.getAdmittedStudents()));
					blockData
							.put("Left Out", String.valueOf(aggregationData
									.getLefoutStudents()));
					blockData.put("Left Out %", String.valueOf(aggregationData
							.findPercentOfLeftOut()));
				} else {
					blockData.put("Enrolled ", "-");
					blockData.put("Left Out", "-");
					blockData.put("Left Out %", "-");
				}
				// adding total for all the block within the district
				total += aggregationData.getAdmittedStudents()
						+ aggregationData.getLefoutStudents();

				// adding admitted for all the block within the district
				admitted += aggregationData.getAdmittedStudents();

				// adding leftout for all the block within the district
				leftout += aggregationData.getLefoutStudents();

				admittedBoys += aggregationData.getAdmittedBoys();

				admittedGirls += aggregationData.getAdmittedGirls();

				admittedThirdGeneder += aggregationData
						.getAdmittedThirdGender();

				admittedGen += aggregationData.getAdmittedStudentsGen();

				admittedobc += aggregationData.getAdmittedStudentsOBC();

				admittedSc += aggregationData.getAdmittedStudentsSC();

				admittedSt += aggregationData.getAdmittedStudentsST();

				admittedMinority += aggregationData
						.getAdmittedStudentsMinority();

				leftoutBoys += aggregationData.getLeftoutBoys();

				leftoutGirls += aggregationData.getLeftoutGirls();

				leftoutThirdGender += aggregationData.getLeftoutThirdGender();

				leftoutGen += aggregationData.getLeftoutStudentsGen();

				leftoutobc += aggregationData.getLeftoutStudentsOBC();

				leftoutSc += aggregationData.getLeftoutStudentsSC();

				leftoutSt += aggregationData.getLeftoutStudentsST();

				leftoutMinority += aggregationData.getLeftoutStudentsMinority();

				districtDatas.add(blockData);
			}

		}
		Map<String, String> aggregatedDataOfSelectedArea = new HashMap<String, String>();
		aggregatedDataOfSelectedArea.put("Total ", String.valueOf(total));
		aggregatedDataOfSelectedArea.put("Admitted ", String.valueOf(admitted));
		aggregatedDataOfSelectedArea.put("Left Out", String.valueOf(leftout));

		List<PieChart> pieCharts = new ArrayList<PieChart>();

		// if total >0 then only the pie chart will be visible
		if (total > 0) {
			PieChart pieChart = new PieChart();

			pieChart.setLabel("Left Out");
			if (leftout > 0)
				pieChart.setValue(df.format((leftout * 100) / total));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution");
			pieCharts.add(pieChart);

			pieChart = new PieChart();

			pieChart.setLabel("Enrolled");
			if (admitted > 0)
				pieChart.setValue(df.format((admitted * 100) / total));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution");

			pieCharts.add(pieChart);
		} 		// boy pie chart
		List<PieChart> boyPieChart = new ArrayList<PieChart>();

		if ((admittedBoys + leftoutBoys) > 0) {
			int boysTotal = admittedBoys + leftoutBoys;
			PieChart pieChart = new PieChart();

			pieChart.setLabel("Enrolled");
			if (admittedBoys > 0)
				pieChart.setValue(df.format((admittedBoys * 100) / boysTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution Boys");
			boyPieChart.add(pieChart);

			pieChart = new PieChart();

			pieChart.setLabel("Left Out");
			if (leftoutBoys > 0)
				pieChart.setValue(df.format((leftoutBoys * 100) / boysTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution Boys");

			boyPieChart.add(pieChart);
			Collections.reverse(boyPieChart);
		}

		// girl pie chart
		List<PieChart> girlsPiechart = new ArrayList<PieChart>();

		if ((admittedGirls + leftoutGirls) > 0) {
			int girlsTotal = admittedGirls + leftoutGirls;
			PieChart pieChart = new PieChart();

			pieChart.setLabel("Enrolled");
			if (admittedGirls > 0)
				pieChart.setValue(df.format((admittedGirls * 100) / girlsTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution of Girls");
			girlsPiechart.add(pieChart);

			pieChart = new PieChart();

			pieChart.setLabel("Left Out");
			if (leftoutGirls > 0)
				pieChart.setValue(df.format((leftoutGirls * 100) / girlsTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution of Girls");

			girlsPiechart.add(pieChart);
			Collections.reverse(girlsPiechart);
		}

		// third geneder pie chart
		List<PieChart> thirdGenderPieChart = new ArrayList<PieChart>();

		if ((admittedThirdGeneder + leftoutThirdGender) > 0) {
			int thirdgenderTotal = admittedThirdGeneder + leftoutThirdGender;
			PieChart pieChart = new PieChart();

			pieChart.setLabel("Enrolled");
			if (admittedThirdGeneder > 0)
				pieChart.setValue(df.format((admittedThirdGeneder * 100)
						/ thirdgenderTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution of Third Gender");
			thirdGenderPieChart.add(pieChart);

			pieChart = new PieChart();

			pieChart.setLabel("Left Out");
			if (leftoutThirdGender > 0)
				pieChart.setValue(df.format((leftoutThirdGender * 100)
						/ thirdgenderTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution of Third Gender");

			thirdGenderPieChart.add(pieChart);
			Collections.reverse(thirdGenderPieChart);
		}

		// / general caste pie
				List<StackedLineChartModel> stackedChart=new ArrayList<StackedLineChartModel>();
				StackedLineChartModel genCastePieChart = new StackedLineChartModel();

				if ((admittedGen + leftoutGen) > 0) {
					double genCasteTotal = admittedGen + leftoutGen;
			
					genCastePieChart.setLabel("General");
					if (admittedGen > 0)
						genCastePieChart.setEnrolled(df
								.format((admittedGen * 100) / genCasteTotal));
					else
						genCastePieChart.setEnrolled("0.0");
				
					if (leftoutGen > 0)
						genCastePieChart.setLeftout(df.format((leftoutGen * 100) / genCasteTotal));
					else
						genCastePieChart.setLeftout("0.0");
			
					stackedChart.add(genCastePieChart);

					
				}

				// OBC Caste pie
				StackedLineChartModel obcCastePieChart = new StackedLineChartModel();

				if ((admittedobc + leftoutobc) > 0) {
					double obcCasteTotal = admittedobc + leftoutobc;
					obcCastePieChart.setLabel("OBC");
					if (admittedobc > 0)
						obcCastePieChart.setEnrolled(df
								.format((admittedobc * 100) / obcCasteTotal));
					else
						obcCastePieChart.setEnrolled("0.0");
					

					//obcCastePieChart.setLeftout("Left Out");
					if (leftoutobc > 0)
						obcCastePieChart.setLeftout(df.format((leftoutobc * 100) / obcCasteTotal));
					else
						obcCastePieChart.setLeftout("0.0");

					stackedChart.add(obcCastePieChart);
				}

				// sc caste pie chart

				StackedLineChartModel scCastePieChart = new StackedLineChartModel();

				if ((admittedSc + leftoutSc) > 0) {
					double scCasteTotal = admittedSc + leftoutSc;
					scCastePieChart.setLabel("SC");
					if (admittedSc > 0)
						scCastePieChart.setEnrolled(df
								.format((admittedSc * 100) / scCasteTotal));
					else
						scCastePieChart.setEnrolled("0.0");
					

					//obcCastePieChart.setLeftout("Left Out");
					if (leftoutSc > 0)
						scCastePieChart.setLeftout(df.format((leftoutSc * 100) / scCasteTotal));
					else
						scCastePieChart.setLeftout("0.0");

					stackedChart.add(scCastePieChart);
				}
				StackedLineChartModel stCastePieChart = new StackedLineChartModel();

				if ((admittedSt + leftoutSt) > 0) {
					double stCasteTotal = admittedSt + leftoutSt;
					stCastePieChart.setLabel("ST");
					if (admittedSt > 0)
						stCastePieChart.setEnrolled(df
								.format((admittedSt * 100) / stCasteTotal));
					else
						stCastePieChart.setEnrolled("0.0");
					

					//obcCastePieChart.setLeftout("Left Out");
					if (leftoutSt > 0)
						stCastePieChart.setLeftout(df.format((leftoutSt * 100) / stCasteTotal));
					else
						stCastePieChart.setLeftout("0.0");

					stackedChart.add(stCastePieChart);
				}

				// minorty data
				StackedLineChartModel minortyCastePieChart = new StackedLineChartModel();

				if ((admittedMinority + leftoutMinority) > 0) {
					double minorityCasteTotal = admittedMinority + leftoutMinority;
					minortyCastePieChart.setLabel("Minority");
					if (admittedMinority > 0)
						minortyCastePieChart.setEnrolled(df
								.format((admittedMinority * 100) / minorityCasteTotal));
					else
						minortyCastePieChart.setEnrolled("0.0");
					

					//obcCastePieChart.setLeftout("Left Out");
					if (leftoutMinority > 0)
						minortyCastePieChart.setLeftout(df.format((leftoutMinority * 100) / minorityCasteTotal));
					else
						minortyCastePieChart.setLeftout("0.0");

					stackedChart.add(minortyCastePieChart);
				}


		List<List<PieChart>> listOfListPieChart = new ArrayList<List<PieChart>>();
		if (pieCharts.size() > 0)
			listOfListPieChart.add(pieCharts);

		List<List<PieChart>> genderPieChart = new ArrayList<List<PieChart>>();
		if (boyPieChart.size() > 0)
			genderPieChart.add(boyPieChart);
		if (girlsPiechart.size() > 0)
			genderPieChart.add(girlsPiechart);
		if (thirdGenderPieChart.size() > 0)
			genderPieChart.add(thirdGenderPieChart);



		dashboardDataModel.setPieChartData(listOfListPieChart);

		dashboardDataModel.setGenderPieChartData(genderPieChart);

		dashboardDataModel.setCastePieChartData(stackedChart);

		dashboardDataModel.setDataTableData(districtDatas);

		dashboardDataModel.setTotalData(aggregatedDataOfSelectedArea);

		dashboardDataModel.setSelection(selection);
		dashboardDataModel.setLastUpdatedDate(sdf.format(aggregateData.get(0)
				.getLastUpdatedDate()));

		return dashboardDataModel;
	}

	@Override
	@Transactional
	public DashboardDataModel fetchBlockWiseData(int blockId) {

		DashboardDataModel dashboardDataModel = new DashboardDataModel();
		int total = 0, leftout = 0, admitted = 0, admittedBoys = 0, admittedGirls = 0, admittedThirdGeneder = 0, leftoutBoys = 0, leftoutGirls = 0, leftoutThirdGender = 0, admittedGen = 0, admittedobc = 0, admittedSc = 0, admittedSt = 0, admittedMinority = 0, leftoutGen = 0, leftoutobc = 0, leftoutSc = 0, leftoutSt = 0, leftoutMinority = 0;
		String lastUpdatedDate = null;
		List<Map<String, String>> schoolDatas = new ArrayList<Map<String, String>>();
		LocalDate localdate = new LocalDate();
		int year = localdate.getYear();
		int monthOfYear = localdate.getMonthOfYear();

		int acedemicYear = year;
		if (monthOfYear < 4) {
			acedemicYear = year - 1;
		}
		acedemicYear = 2017;
		String selection = "Uttar Pradesh ";
		dashboardDataModel.setStateName("Uttar Pradesh ");
		List<Area> areaList = areaRepository.findAll();

		Map<Integer, Area> areaMap = new HashMap<Integer, Area>();
		for (Area area : areaList) {
			areaMap.put(area.getAreaId(), area);
		}

		UserModel userModel = (UserModel) stateManager
				.getValue(Constants.Web.USER_PRINCIPAL);
		// district id will only be zero in case of the logged in user is
		// district level
		// if blockId==0 then we will bring the data of all the block within the
		// logged in user district
		if (blockId == 0) {
			int districtId = userModel.getAreaId();

			selection += " > "
					+ areaMap.get(areaMap.get(districtId).getParentAreaId())
							.getAreaName();
			selection += " > " + areaMap.get(districtId).getAreaName();
			dashboardDataModel.setDivisionName(areaMap.get(
					areaMap.get(districtId).getParentAreaId()).getAreaName());
			dashboardDataModel.setDistrictName(areaMap.get(districtId)
					.getAreaName());
			List<AggregationData> aggregateData = aggregationDataRepository
					.findByAreaIdParentAreaIdAndAcademicYearIdStartYearOrderByAreaIdAreaNameAsc(
							districtId, String.valueOf(acedemicYear));
			lastUpdatedDate = sdf.format(aggregateData.get(0)
					.getLastUpdatedDate());
			for (AggregationData aggregationData : aggregateData) {
				Map<String, String> blockData = new LinkedHashMap<String, String>();

				blockData.put("Block ", aggregationData.getAreaId()
						.getAreaName());
				blockData.put(
						"Total ",
						String.valueOf(aggregationData.getAdmittedStudents()
								+ aggregationData.getLefoutStudents()));
				if (aggregationData.getAdmittedStudents()
						+ aggregationData.getLefoutStudents() > 0) {
					blockData.put("Enrolled ", String.valueOf(aggregationData
							.getAdmittedStudents()));
					blockData
							.put("Left Out", String.valueOf(aggregationData
									.getLefoutStudents()));
					blockData.put("Left Out %", String.valueOf(aggregationData
							.findPercentOfLeftOut()));
				} else {
					blockData.put("Enrolled ", "-");
					blockData.put("Left Out", "-");
					blockData.put("Left Out %", "-");
				}

				// adding total for all the district within the division
				total += aggregationData.getAdmittedStudents()
						+ aggregationData.getLefoutStudents();

				// adding admitted for all the district within the division
				admitted += aggregationData.getAdmittedStudents();

				// adding leftout for all the district within the division
				leftout += aggregationData.getLefoutStudents();

				admittedBoys += aggregationData.getAdmittedBoys();

				admittedGirls += aggregationData.getAdmittedGirls();

				admittedThirdGeneder += aggregationData
						.getAdmittedThirdGender();

				admittedGen += aggregationData.getAdmittedStudentsGen();

				admittedobc += aggregationData.getAdmittedStudentsOBC();

				admittedSc += aggregationData.getAdmittedStudentsSC();

				admittedSt += aggregationData.getAdmittedStudentsST();

				admittedMinority += aggregationData
						.getAdmittedStudentsMinority();

				leftoutBoys += aggregationData.getLeftoutBoys();

				leftoutGirls += aggregationData.getLeftoutGirls();

				leftoutThirdGender += aggregationData.getLeftoutThirdGender();

				leftoutGen += aggregationData.getLeftoutStudentsGen();

				leftoutobc += aggregationData.getLeftoutStudentsOBC();

				leftoutSc += aggregationData.getLeftoutStudentsSC();

				leftoutSt += aggregationData.getLeftoutStudentsST();

				leftoutMinority += aggregationData.getLeftoutStudentsMinority();

				schoolDatas.add(blockData);
			}

		} else {

			selection += " > "
					+ areaMap.get(
							areaMap.get(areaMap.get(blockId).getParentAreaId())
									.getParentAreaId()).getAreaName();
			selection += " > "
					+ areaMap.get(areaMap.get(blockId).getParentAreaId())
							.getAreaName();
			selection += " > " + areaMap.get(blockId).getAreaName();
			dashboardDataModel.setDivisionName(areaMap.get(
					areaMap.get(areaMap.get(blockId).getParentAreaId())
							.getParentAreaId()).getAreaName());
			dashboardDataModel.setDistrictName(areaMap.get(
					areaMap.get(blockId).getParentAreaId()).getAreaName());
			dashboardDataModel.setBlockName(areaMap.get(blockId).getAreaName());

			List<SchoolAggregatedData> schoolAggregatedDatas = schoolAggregatedDataRepository
					.findBySchoolBlockAreaIdAndAcademicYearIdStartYearOrderBySchoolSchoolNameAsc(
							blockId, String.valueOf(acedemicYear));
			lastUpdatedDate = sdf.format(schoolAggregatedDatas.get(0)
					.getLastUpdatedDate());
			for (SchoolAggregatedData aggregationData : schoolAggregatedDatas) {
				Map<String, String> schoolData = new LinkedHashMap<String, String>();

				schoolData.put("School Name ", aggregationData.getSchool()
						.getSchoolName());
				schoolData.put(
						"Total ",
						String.valueOf(aggregationData.getAdmittedStudents()
								+ aggregationData.getLefoutStudents()));
				if (aggregationData.getAdmittedStudents()
						+ aggregationData.getLefoutStudents() > 0) {
					schoolData.put("Enrolled ", String.valueOf(aggregationData
							.getAdmittedStudents()));
					schoolData
							.put("Left Out", String.valueOf(aggregationData
									.getLefoutStudents()));
					schoolData.put("Left Out %", String.valueOf(aggregationData
							.findPercentOfLeftOut()));
					schoolData.put(
							"schoolType",
							String.valueOf(aggregationData.getSchool()
									.getSchoolType().getTypeDetailId()));
				} else {
					schoolData.put("Enrolled ", "-");
					schoolData.put("Left Out", "-");
					schoolData.put("Left Out %", "-");
				}
				schoolData.put(
						"schoolType",
						String.valueOf(aggregationData.getSchool()
								.getSchoolType().getTypeDetailId()));
				schoolData.put("schoolId", String.valueOf(aggregationData
						.getSchool().getSchoolId()));

				// adding total for all the school within the block
				total += aggregationData.getAdmittedStudents()
						+ aggregationData.getLefoutStudents();

				// adding admitted for all the school within the block
				admitted += aggregationData.getAdmittedStudents();

				// adding leftout for all the school within the block
				leftout += aggregationData.getLefoutStudents();

				admittedBoys += aggregationData.getAdmittedBoys();

				admittedGirls += aggregationData.getAdmittedGirls();

				admittedThirdGeneder += aggregationData
						.getAdmittedThirdGender();

				admittedGen += aggregationData.getAdmittedStudentsGen();

				admittedobc += aggregationData.getAdmittedStudentsOBC();

				admittedSc += aggregationData.getAdmittedStudentsSC();

				admittedSt += aggregationData.getAdmittedStudentsST();

				admittedMinority += aggregationData
						.getAdmittedStudentsMinority();

				leftoutBoys += aggregationData.getLeftoutBoys();

				leftoutGirls += aggregationData.getLeftoutGirls();

				leftoutThirdGender += aggregationData.getLeftoutThirdGender();

				leftoutGen += aggregationData.getLeftoutStudentsGen();

				leftoutobc += aggregationData.getLeftoutStudentsOBC();

				leftoutSc += aggregationData.getLeftoutStudentsSC();

				leftoutSt += aggregationData.getLeftoutStudentsST();

				leftoutMinority += aggregationData.getLeftoutStudentsMinority();

				schoolDatas.add(schoolData);
			}
		}
		Map<String, String> aggregatedDataOfSelectedArea = new HashMap<String, String>();
		aggregatedDataOfSelectedArea.put("Total ", String.valueOf(total));
		aggregatedDataOfSelectedArea.put("Admitted ", String.valueOf(admitted));
		aggregatedDataOfSelectedArea.put("Left Out", String.valueOf(leftout));

		List<PieChart> pieCharts = new ArrayList<PieChart>();

		// if total >0 then only the pie chart will be visible
		if (total > 0) {
			PieChart pieChart = new PieChart();

			pieChart.setLabel("Left Out");
			if (leftout > 0)
				pieChart.setValue(df.format((leftout * 100) / total));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution");
			pieCharts.add(pieChart);

			pieChart = new PieChart();

			pieChart.setLabel("Enrolled");
			if (admitted > 0)
				pieChart.setValue(df.format((admitted * 100) / total));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution");

			pieCharts.add(pieChart);
		}
		// boy pie chart
		List<PieChart> boyPieChart = new ArrayList<PieChart>();

		if ((admittedBoys + leftoutBoys) > 0) {
			int boysTotal = admittedBoys + leftoutBoys;
			PieChart pieChart = new PieChart();

			pieChart.setLabel("Enrolled");
			if (admittedBoys > 0)
				pieChart.setValue(df.format((admittedBoys * 100) / boysTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution Boys");
			boyPieChart.add(pieChart);

			pieChart = new PieChart();

			pieChart.setLabel("Left Out");
			if (leftoutBoys > 0)
				pieChart.setValue(df.format((leftoutBoys * 100) / boysTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution Boys");

			boyPieChart.add(pieChart);
			Collections.reverse(boyPieChart);
		}

		// girl pie chart
		List<PieChart> girlsPiechart = new ArrayList<PieChart>();

		if ((admittedGirls + leftoutGirls) > 0) {
			int girlsTotal = admittedGirls + leftoutGirls;
			PieChart pieChart = new PieChart();

			pieChart.setLabel("Enrolled");
			if (admittedGirls > 0)
				pieChart.setValue(df.format((admittedGirls * 100) / girlsTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution of Girls");
			girlsPiechart.add(pieChart);

			pieChart = new PieChart();

			pieChart.setLabel("Left Out");
			if (leftoutGirls > 0)
				pieChart.setValue(df.format((leftoutGirls * 100) / girlsTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution of Girls");

			girlsPiechart.add(pieChart);
			Collections.reverse(girlsPiechart);
		}

		// third geneder pie chart
		List<PieChart> thirdGenderPieChart = new ArrayList<PieChart>();

		if ((admittedThirdGeneder + leftoutThirdGender) > 0) {
			int thirdgenderTotal = admittedThirdGeneder + leftoutThirdGender;
			PieChart pieChart = new PieChart();

			pieChart.setLabel("Enrolled");
			if (admittedThirdGeneder > 0)
				pieChart.setValue(df.format((admittedThirdGeneder * 100)
						/ thirdgenderTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution of Third Gender");
			thirdGenderPieChart.add(pieChart);

			pieChart = new PieChart();

			pieChart.setLabel("Left Out");
			if (leftoutThirdGender > 0)
				pieChart.setValue(df.format((leftoutThirdGender * 100)
						/ thirdgenderTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution of Third Gender");

			thirdGenderPieChart.add(pieChart);
			Collections.reverse(thirdGenderPieChart);
		}

		// / general caste pie
				List<StackedLineChartModel> stackedChart=new ArrayList<StackedLineChartModel>();
				StackedLineChartModel genCastePieChart = new StackedLineChartModel();

				if ((admittedGen + leftoutGen) > 0) {
					double genCasteTotal = admittedGen + leftoutGen;
			
					genCastePieChart.setLabel("General");
					if (admittedGen > 0)
						genCastePieChart.setEnrolled(df
								.format((admittedGen * 100) / genCasteTotal));
					else
						genCastePieChart.setEnrolled("0.0");
				
					if (leftoutGen > 0)
						genCastePieChart.setLeftout(df.format((leftoutGen * 100) / genCasteTotal));
					else
						genCastePieChart.setLeftout("0.0");
			
					stackedChart.add(genCastePieChart);

					
				}

				// OBC Caste pie
				StackedLineChartModel obcCastePieChart = new StackedLineChartModel();

				if ((admittedobc + leftoutobc) > 0) {
					double obcCasteTotal = admittedobc + leftoutobc;
					obcCastePieChart.setLabel("OBC");
					if (admittedobc > 0)
						obcCastePieChart.setEnrolled(df
								.format((admittedobc * 100) / obcCasteTotal));
					else
						obcCastePieChart.setEnrolled("0.0");
					

					//obcCastePieChart.setLeftout("Left Out");
					if (leftoutobc > 0)
						obcCastePieChart.setLeftout(df.format((leftoutobc * 100) / obcCasteTotal));
					else
						obcCastePieChart.setLeftout("0.0");

					stackedChart.add(obcCastePieChart);
				}

				// sc caste pie chart

				StackedLineChartModel scCastePieChart = new StackedLineChartModel();

				if ((admittedSc + leftoutSc) > 0) {
					double scCasteTotal = admittedSc + leftoutSc;
					scCastePieChart.setLabel("SC");
					if (admittedSc > 0)
						scCastePieChart.setEnrolled(df
								.format((admittedSc * 100) / scCasteTotal));
					else
						scCastePieChart.setEnrolled("0.0");
					

					//obcCastePieChart.setLeftout("Left Out");
					if (leftoutSc > 0)
						scCastePieChart.setLeftout(df.format((leftoutSc * 100) / scCasteTotal));
					else
						scCastePieChart.setLeftout("0.0");

					stackedChart.add(scCastePieChart);
				}
				StackedLineChartModel stCastePieChart = new StackedLineChartModel();

				if ((admittedSt + leftoutSt) > 0) {
					double stCasteTotal = admittedSt + leftoutSt;
					stCastePieChart.setLabel("ST");
					if (admittedSt > 0)
						stCastePieChart.setEnrolled(df
								.format((admittedSt * 100) / stCasteTotal));
					else
						stCastePieChart.setEnrolled("0.0");
					

					//obcCastePieChart.setLeftout("Left Out");
					if (leftoutSt > 0)
						stCastePieChart.setLeftout(df.format((leftoutSt * 100) / stCasteTotal));
					else
						stCastePieChart.setLeftout("0.0");

					stackedChart.add(stCastePieChart);
				}

				// minorty data
				StackedLineChartModel minortyCastePieChart = new StackedLineChartModel();

				if ((admittedMinority + leftoutMinority) > 0) {
					double minorityCasteTotal = admittedMinority + leftoutMinority;
					minortyCastePieChart.setLabel("Minority");
					if (admittedMinority > 0)
						minortyCastePieChart.setEnrolled(df
								.format((admittedMinority * 100) / minorityCasteTotal));
					else
						minortyCastePieChart.setEnrolled("0.0");
					

					//obcCastePieChart.setLeftout("Left Out");
					if (leftoutMinority > 0)
						minortyCastePieChart.setLeftout(df.format((leftoutMinority * 100) / minorityCasteTotal));
					else
						minortyCastePieChart.setLeftout("0.0");

					stackedChart.add(minortyCastePieChart);
				}


		List<List<PieChart>> listOfListPieChart = new ArrayList<List<PieChart>>();
		if (pieCharts.size() > 0)
			listOfListPieChart.add(pieCharts);

		List<List<PieChart>> genderPieChart = new ArrayList<List<PieChart>>();
		if (boyPieChart.size() > 0)
			genderPieChart.add(boyPieChart);
		if (girlsPiechart.size() > 0)
			genderPieChart.add(girlsPiechart);
		if (thirdGenderPieChart.size() > 0)
			genderPieChart.add(thirdGenderPieChart);


		dashboardDataModel.setPieChartData(listOfListPieChart);

		dashboardDataModel.setGenderPieChartData(genderPieChart);

		dashboardDataModel.setCastePieChartData(stackedChart);

		dashboardDataModel.setDataTableData(schoolDatas);

		dashboardDataModel.setTotalData(aggregatedDataOfSelectedArea);

		dashboardDataModel.setSelection(selection);

		dashboardDataModel.setLastUpdatedDate(lastUpdatedDate);

		return dashboardDataModel;
	}

	@Override
	@Transactional
	public DashboardDataModel fetchSchoolWiseWiseData(int schoolId) {
		List<Map<String, String>> studentsData = new ArrayList<Map<String, String>>();

		DashboardDataModel dashboardDataModel = new DashboardDataModel();

		int total = 0, leftout = 0, admitted = 0, admittedBoys = 0, admittedGirls = 0, admittedThirdGeneder = 0, leftoutBoys = 0, leftoutGirls = 0, leftoutThirdGender = 0, admittedGen = 0, admittedobc = 0, admittedSc = 0, admittedSt = 0, admittedMinority = 0, leftoutGen = 0, leftoutobc = 0, leftoutSc = 0, leftoutSt = 0, leftoutMinority = 0;

		String selection = "Uttar Pradesh ";
		dashboardDataModel.setStateName("Uttar Pradesh ");

		LocalDate localdate = new LocalDate();
		int year = localdate.getYear();
		int monthOfYear = localdate.getMonthOfYear();

		int acedemicYear = year;
		if (monthOfYear < 4) {
			acedemicYear = year - 1;
		}
		acedemicYear = 2017;

		// latest date for the last job execution
		SchoolAggregatedData schoolAggregatedData = schoolAggregatedDataRepository
				.findBySchoolSchoolId(schoolId);

		Date lastUpdatedDate = schoolAggregatedData.getLastUpdatedDate();

		Timestamp timestampDate = schoolAggregatedData.getLastUpdatedDateTime();

		// we will bring only those student who where added to database before
		// the job was executed for the school aggregation
		List<StudentsDetails> studentsDetailsList = studentDetailsRepository
				.findBySchoolSchoolIdAndAcedemicYearStartYearAndCreateDateTimeLessThan(
						schoolId, String.valueOf(acedemicYear), timestampDate);

		SchoolDetails schoolDetails = schoolAggregatedData.getSchool();

		Area area = areaRepository.findByAreaId(schoolDetails.getDistrictId()
				.getParentAreaId());

		selection += " > " + area.getAreaName() + " > "
				+ schoolDetails.getDistrictId().getAreaName() + " > "
				+ schoolDetails.getBlock().getAreaName() + " > "
				+ schoolDetails.getSchoolName();

		dashboardDataModel.setDivisionName(area.getAreaName());
		dashboardDataModel.setDistrictName(schoolDetails.getDistrictId()
				.getAreaName());
		dashboardDataModel.setBlockName(schoolDetails.getBlock().getAreaName());
		dashboardDataModel.setSchoolName(schoolDetails.getSchoolName());

		for (StudentsDetails studentsDetails : studentsDetailsList) {
			Map<String, String> studentMap = new LinkedHashMap<String, String>();
			studentMap.put("Student's Name ", studentsDetails.getStudentName());
			studentMap.put("Student Reg No.",
					studentsDetails.getSrRegistrationNumber());
			studentMap.put("Father's Name", studentsDetails.getFathersName());
			studentMap.put("Mother's Name", studentsDetails.getMothersName());
			studentMap.put("Gender", studentsDetails.getGender());
			studentMap.put("Social Category", studentsDetails.getCaste()
					.getTypeDetail());
			// studentMap.put("Status",
			// studentsDetails.isSubmitted()==true?"green":"red");

			total++;

			// if student is enrolled and enrolled before the last job execution
			// for
			// the school aggregation then it will be shown enrolled else it
			// will be showed leftout
			if (studentsDetails.isSubmitted() == true
					&& studentsDetails.getUpdateDateTime().before(
							lastUpdatedDate)
					&& !DateUtils.isSameDay(
							studentsDetails.getUpdateDateTime(),
							lastUpdatedDate)) {

				if (studentsDetails.getStudentSchoolMapping().get(0)
						.getEnrollType().getTypeDetailId() == Integer
						.parseInt(applicationMessageSource.getMessage(
								Constants.Web.OTHER_STATE, null, null))
						|| studentsDetails.getStudentSchoolMapping().get(0)
								.getEnrollType().getTypeDetailId() == Integer
								.parseInt(applicationMessageSource.getMessage(
										Constants.Web.MIGRATION, null, null))) {
					// if student is migrated then school name was set to
					// migrated and it will be count under left out
					if (studentsDetails.getStudentSchoolMapping().get(0)
							.getEnrollType().getTypeDetailId() == Integer
							.parseInt(applicationMessageSource.getMessage(
									Constants.Web.MIGRATION, null, null))) {
						studentMap.put("Enrolled To", "Migrated");

						// studentMap.put("udise_code","");
						leftout++;
					} else {
						String schoolName = studentsDetails
								.getStudentSchoolMapping().get(0)
								.getOtherStateSchoolName() == null ? ""
								: studentsDetails.getStudentSchoolMapping()
										.get(0).getOtherStateSchoolName();

						if (schoolName.equals("")) {
							schoolName = studentsDetails
									.getStudentSchoolMapping().get(0)
									.getOtherDistrict().getAreaName()
									+ ", "
									+ studentsDetails.getStudentSchoolMapping()
											.get(0).getOtherState()
											.getAreaName();
						} else {
							schoolName += ", "
									+ studentsDetails.getStudentSchoolMapping()
											.get(0).getOtherDistrict()
											.getAreaName()
									+ ", "
									+ studentsDetails.getStudentSchoolMapping()
											.get(0).getOtherState()
											.getAreaName();
						}
						studentMap.put("Enrolled To", schoolName);

						// studentMap.put("udise_code","");
					}
					admitted++;

				} else {
					String udise_code = studentsDetails
							.getStudentSchoolMapping().get(0)
							.getLinkedSchoolDetails().getUdiseCode() != null ? studentsDetails
							.getStudentSchoolMapping().get(0)
							.getLinkedSchoolDetails().getUdiseCode()
							: "N/A";

					{
						studentMap.put("Enrolled To", studentsDetails
								.getStudentSchoolMapping().get(0)
								.getLinkedSchoolDetails().getSchoolName()
								+ " ( " + udise_code + " )");
					}
					/*
					 * studentMap.put("udise_code",
					 * studentsDetails.getStudentSchoolMapping().get(0)
					 * .getLinkedSchoolDetails().getUdiseCode() != null ?
					 * studentsDetails .getStudentSchoolMapping().get(0)
					 * .getLinkedSchoolDetails().getUdiseCode() : "N/A");
					 */

					admitted++;
				}

			} else {
				studentMap.put("Enrolled To", "");
				// studentMap.put("udise_code","");
				leftout++;

			}

			studentsData.add(studentMap);
		}

		Map<String, String> aggregatedDataOfSelectedArea = new HashMap<String, String>();
		aggregatedDataOfSelectedArea.put("Total ", String.valueOf(total));
		aggregatedDataOfSelectedArea.put("Admitted ", String.valueOf(admitted));
		aggregatedDataOfSelectedArea.put("Left Out", String.valueOf(leftout));

		List<PieChart> pieCharts = new ArrayList<PieChart>();

		admittedBoys += schoolAggregatedData.getAdmittedBoys();

		admittedGirls += schoolAggregatedData.getAdmittedGirls();

		admittedThirdGeneder += schoolAggregatedData.getAdmittedThirdGender();

		admittedGen += schoolAggregatedData.getAdmittedStudentsGen();

		admittedobc += schoolAggregatedData.getAdmittedStudentsOBC();

		admittedSc += schoolAggregatedData.getAdmittedStudentsSC();

		admittedSt += schoolAggregatedData.getAdmittedStudentsST();

		admittedMinority += schoolAggregatedData.getAdmittedStudentsMinority();

		leftoutBoys += schoolAggregatedData.getLeftoutBoys();

		leftoutGirls += schoolAggregatedData.getLeftoutGirls();

		leftoutThirdGender += schoolAggregatedData.getLeftoutThirdGender();

		leftoutGen += schoolAggregatedData.getLeftoutStudentsGen();

		leftoutobc += schoolAggregatedData.getLeftoutStudentsOBC();

		leftoutSc += schoolAggregatedData.getLeftoutStudentsSC();

		leftoutSt += schoolAggregatedData.getLeftoutStudentsST();

		leftoutMinority += schoolAggregatedData.getLeftoutStudentsMinority();

		// if total >0 then only the pie chart will be visible
		if (total > 0) {
			PieChart pieChart = new PieChart();

			pieChart.setLabel("Left Out");
			if (leftout > 0)
				pieChart.setValue(df.format((leftout * 100) / total));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution");
			pieCharts.add(pieChart);

			pieChart = new PieChart();

			pieChart.setLabel("Enrolled");
			if (admitted > 0)
				pieChart.setValue(df.format((admitted * 100) / total));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution");

			pieCharts.add(pieChart);
		}
		// boy pie chart
		List<PieChart> boyPieChart = new ArrayList<PieChart>();

		if ((admittedBoys + leftoutBoys) > 0) {
			int boysTotal = admittedBoys + leftoutBoys;
			PieChart pieChart = new PieChart();

			pieChart.setLabel("Enrolled");
			if (admittedBoys > 0)
				pieChart.setValue(df.format((admittedBoys * 100) / boysTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution Boys");
			boyPieChart.add(pieChart);

			pieChart = new PieChart();

			pieChart.setLabel("Left Out");
			if (leftoutBoys > 0)
				pieChart.setValue(df.format((leftoutBoys * 100) / boysTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution Boys");

			boyPieChart.add(pieChart);
			Collections.reverse(boyPieChart);
		}

		// girl pie chart
		List<PieChart> girlsPiechart = new ArrayList<PieChart>();

		if ((admittedGirls + leftoutGirls) > 0) {
			int girlsTotal = admittedGirls + leftoutGirls;
			PieChart pieChart = new PieChart();

			pieChart.setLabel("Enrolled");
			if (admittedGirls > 0)
				pieChart.setValue(df.format((admittedGirls * 100) / girlsTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution of Girls");
			girlsPiechart.add(pieChart);

			pieChart = new PieChart();

			pieChart.setLabel("Left Out");
			if (leftoutGirls > 0)
				pieChart.setValue(df.format((leftoutGirls * 100) / girlsTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution of Girls");

			girlsPiechart.add(pieChart);
			Collections.reverse(girlsPiechart);
		}

		// third geneder pie chart
		List<PieChart> thirdGenderPieChart = new ArrayList<PieChart>();

		if ((admittedThirdGeneder + leftoutThirdGender) > 0) {
			int thirdgenderTotal = admittedThirdGeneder + leftoutThirdGender;
			PieChart pieChart = new PieChart();

			pieChart.setLabel("Enrolled");
			if (admittedThirdGeneder > 0)
				pieChart.setValue(df.format((admittedThirdGeneder * 100)
						/ thirdgenderTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution of Third Gender");
			thirdGenderPieChart.add(pieChart);

			pieChart = new PieChart();

			pieChart.setLabel("Left Out");
			if (leftoutThirdGender > 0)
				pieChart.setValue(df.format((leftoutThirdGender * 100)
						/ thirdgenderTotal));
			else
				pieChart.setValue("0.0");
			pieChart.setName("Percent Distribution of Third Gender");

			thirdGenderPieChart.add(pieChart);
			Collections.reverse(thirdGenderPieChart);
		}

		// / general caste pie
				List<StackedLineChartModel> stackedChart=new ArrayList<StackedLineChartModel>();
				StackedLineChartModel genCastePieChart = new StackedLineChartModel();

				if ((admittedGen + leftoutGen) > 0) {
					double genCasteTotal = admittedGen + leftoutGen;
			
					genCastePieChart.setLabel("General");
					if (admittedGen > 0)
						genCastePieChart.setEnrolled(df
								.format((admittedGen * 100) / genCasteTotal));
					else
						genCastePieChart.setEnrolled("0.0");
				
					if (leftoutGen > 0)
						genCastePieChart.setLeftout(df.format((leftoutGen * 100) / genCasteTotal));
					else
						genCastePieChart.setLeftout("0.0");
			
					stackedChart.add(genCastePieChart);

					
				}

				// OBC Caste pie
				StackedLineChartModel obcCastePieChart = new StackedLineChartModel();

				if ((admittedobc + leftoutobc) > 0) {
					double obcCasteTotal = admittedobc + leftoutobc;
					obcCastePieChart.setLabel("OBC");
					if (admittedobc > 0)
						obcCastePieChart.setEnrolled(df
								.format((admittedobc * 100) / obcCasteTotal));
					else
						obcCastePieChart.setEnrolled("0.0");
					

					//obcCastePieChart.setLeftout("Left Out");
					if (leftoutobc > 0)
						obcCastePieChart.setLeftout(df.format((leftoutobc * 100) / obcCasteTotal));
					else
						obcCastePieChart.setLeftout("0.0");

					stackedChart.add(obcCastePieChart);
				}

				// sc caste pie chart

				StackedLineChartModel scCastePieChart = new StackedLineChartModel();

				if ((admittedSc + leftoutSc) > 0) {
					double scCasteTotal = admittedSc + leftoutSc;
					scCastePieChart.setLabel("SC");
					if (admittedSc > 0)
						scCastePieChart.setEnrolled(df
								.format((admittedSc * 100) / scCasteTotal));
					else
						scCastePieChart.setEnrolled("0.0");
					

					//obcCastePieChart.setLeftout("Left Out");
					if (leftoutSc > 0)
						scCastePieChart.setLeftout(df.format((leftoutSc * 100) / scCasteTotal));
					else
						scCastePieChart.setLeftout("0.0");

					stackedChart.add(scCastePieChart);
				}
				StackedLineChartModel stCastePieChart = new StackedLineChartModel();

				if ((admittedSt + leftoutSt) > 0) {
					double stCasteTotal = admittedSt + leftoutSt;
					stCastePieChart.setLabel("ST");
					if (admittedSt > 0)
						stCastePieChart.setEnrolled(df
								.format((admittedSt * 100) / stCasteTotal));
					else
						stCastePieChart.setEnrolled("0.0");
					

					//obcCastePieChart.setLeftout("Left Out");
					if (leftoutSt > 0)
						stCastePieChart.setLeftout(df.format((leftoutSt * 100) / stCasteTotal));
					else
						stCastePieChart.setLeftout("0.0");

					stackedChart.add(stCastePieChart);
				}

				// minorty data
				StackedLineChartModel minortyCastePieChart = new StackedLineChartModel();

				if ((admittedMinority + leftoutMinority) > 0) {
					double minorityCasteTotal = admittedMinority + leftoutMinority;
					minortyCastePieChart.setLabel("Minority");
					if (admittedMinority > 0)
						minortyCastePieChart.setEnrolled(df
								.format((admittedMinority * 100) / minorityCasteTotal));
					else
						minortyCastePieChart.setEnrolled("0.0");
					

					//obcCastePieChart.setLeftout("Left Out");
					if (leftoutMinority > 0)
						minortyCastePieChart.setLeftout(df.format((leftoutMinority * 100) / minorityCasteTotal));
					else
						minortyCastePieChart.setLeftout("0.0");

					stackedChart.add(minortyCastePieChart);
				}

		List<List<PieChart>> listOfListPieChart = new ArrayList<List<PieChart>>();
		if (pieCharts.size() > 0)
			listOfListPieChart.add(pieCharts);

		List<List<PieChart>> genderPieChart = new ArrayList<List<PieChart>>();
		if (boyPieChart.size() > 0)
			genderPieChart.add(boyPieChart);
		if (girlsPiechart.size() > 0)
			genderPieChart.add(girlsPiechart);
		if (thirdGenderPieChart.size() > 0)
			genderPieChart.add(thirdGenderPieChart);


		dashboardDataModel.setPieChartData(listOfListPieChart);

		dashboardDataModel.setGenderPieChartData(genderPieChart);

		dashboardDataModel.setCastePieChartData(stackedChart);
		dashboardDataModel.setDataTableData(studentsData);

		dashboardDataModel.setTotalData(aggregatedDataOfSelectedArea);

		dashboardDataModel.setSelection(selection);
		dashboardDataModel.setLastUpdatedDate(sdf.format(lastUpdatedDate));
		return dashboardDataModel;
	}

	@Override
	public DashboardDropDownMenuModel fetchDropDownMenuForDashboard() {

		UserModel userModel = (UserModel) stateManager
				.getValue(Constants.Web.USER_PRINCIPAL);

		DashboardDropDownMenuModel dashboardDropDownMenuModel = new DashboardDropDownMenuModel();
		List<Area> areas = areaRepository
				.findByIsLiveTrueAndLevelLessThanOrderByLevelAscAreaNameAsc(6);

		Map<Integer, List<Area>> areaLevelMap = new HashMap<Integer, List<Area>>();

		for (Area area : areas) {
			if (areaLevelMap.containsKey(area.getLevel())) {
				areaLevelMap.get(area.getLevel()).add(area);
			} else {
				List<Area> areaList = new ArrayList<Area>();
				areaList.add(area);
				areaLevelMap.put(area.getLevel(), areaList);
			}
		}

		int userAreaLevelId = userModel.getAreaLevel();

		// divisions
		if (userAreaLevelId == Integer.parseInt(applicationMessageSource
				.getMessage(Constants.Web.STATE_LEVEL, null, null))) {
			List<Area> divisionList = new ArrayList<Area>();

			if (userAreaLevelId == Integer.parseInt(applicationMessageSource
					.getMessage(Constants.Web.STATE_LEVEL, null, null)))
				divisionList = areaLevelMap.get(Integer
						.parseInt(applicationMessageSource.getMessage(
								Constants.Web.DIVISIONAL_LEVEL, null, null)));
			/*
			 * else { Area
			 * division=areaRepository.findByAreaId(userModel.getAreaId());
			 * divisionList.add(division); }
			 */
			List<AreaWebModel> divisionModelList = new ArrayList<AreaWebModel>();

			AreaWebModel areaWebModel = new AreaWebModel();
			// if(userAreaLevelId==Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.STATE_LEVEL,null,null)))
			/*
			 * { areaWebModel.setAreaId(0);
			 * areaWebModel.setAreaName("All Division");
			 * areaWebModel.setParentAreaId(0);
			 * divisionModelList.add(areaWebModel); }
			 */

			for (Area division : divisionList) {
				areaWebModel = new AreaWebModel();
				areaWebModel.setAreaId(division.getAreaId());
				areaWebModel.setAreaName(division.getAreaName());
				areaWebModel.setParentAreaId(division.getParentAreaId());
				if (division.getAreaId() == userModel.getAreaId()) {
					areaWebModel.setDisabled(true);
				}
				divisionModelList.add(areaWebModel);
			}
			dashboardDropDownMenuModel.setDivision(divisionModelList);
		}

		// districts
		if (userAreaLevelId == Integer.parseInt(applicationMessageSource
				.getMessage(Constants.Web.STATE_LEVEL, null, null))
				|| userAreaLevelId == Integer
						.parseInt(applicationMessageSource.getMessage(
								Constants.Web.DIVISIONAL_LEVEL, null, null))) {
			List<Area> districtList = new ArrayList<Area>();
			if (userAreaLevelId == Integer.parseInt(applicationMessageSource
					.getMessage(Constants.Web.STATE_LEVEL, null, null))) {
				districtList = areaLevelMap.get(Integer
						.parseInt(applicationMessageSource.getMessage(
								Constants.Web.DISTRICT_LEVEL, null, null)));

			}

			else if (userAreaLevelId == Integer
					.parseInt(applicationMessageSource.getMessage(
							Constants.Web.DIVISIONAL_LEVEL, null, null))) {
				districtList = areaRepository
						.findByIsLiveTrueAndParentAreaIdOrderByAreaNameAsc(userModel
								.getAreaId());
			}

			/*
			 * else if
			 * (userAreaLevelId==Integer.parseInt(applicationMessageSource
			 * .getMessage(Constants.Web.DISTRICT_LEVEL,null,null))) { Area
			 * district=areaRepository.findByAreaId(userModel.getAreaId());
			 * districtList.add(district); }
			 */
			List<AreaWebModel> districtModelList = new ArrayList<AreaWebModel>();

			AreaWebModel areaWebModel = new AreaWebModel();
			/*
			 * if (userAreaLevelId==Integer.parseInt(applicationMessageSource.
			 * getMessage(Constants.Web.DIVISIONAL_LEVEL,null,null))) {
			 * areaWebModel.setAreaId(0);
			 * areaWebModel.setAreaName("All District");
			 * areaWebModel.setParentAreaId(0);
			 * districtModelList.add(areaWebModel); }
			 */

			for (Area district : districtList) {
				areaWebModel = new AreaWebModel();
				areaWebModel.setAreaId(district.getAreaId());
				areaWebModel.setAreaName(district.getAreaName());
				areaWebModel.setParentAreaId(district.getParentAreaId());
				if (district.getAreaId() == userModel.getAreaId()) {
					areaWebModel.setDisabled(true);
				}
				districtModelList.add(areaWebModel);
			}
			dashboardDropDownMenuModel.setDistrict(districtModelList);

		}

		// block
		if (userAreaLevelId == Integer.parseInt(applicationMessageSource
				.getMessage(Constants.Web.STATE_LEVEL, null, null))
				|| userAreaLevelId == Integer
						.parseInt(applicationMessageSource.getMessage(
								Constants.Web.DIVISIONAL_LEVEL, null, null))
				|| userAreaLevelId == Integer.parseInt(applicationMessageSource
						.getMessage(Constants.Web.DISTRICT_LEVEL, null, null))) {

			List<Area> blockList = new ArrayList<Area>();
			if (userAreaLevelId == Integer.parseInt(applicationMessageSource
					.getMessage(Constants.Web.STATE_LEVEL, null, null))) {
				blockList = areaLevelMap.get(Integer
						.parseInt(applicationMessageSource.getMessage(
								Constants.Web.BLOCK_LEVEL, null, null)));

			} else if (userAreaLevelId == Integer
					.parseInt(applicationMessageSource.getMessage(
							Constants.Web.DIVISIONAL_LEVEL, null, null))) {
				blockList = areaRepository
						.findByParentParentAreaIdAndIsLiveTrue(userModel
								.getAreaId());
			}

			else if (userAreaLevelId == Integer
					.parseInt(applicationMessageSource.getMessage(
							Constants.Web.DISTRICT_LEVEL, null, null))) {
				blockList = areaRepository
						.findByIsLiveTrueAndParentAreaIdOrderByAreaNameAsc(userModel
								.getAreaId());
			}

			/*
			 * else if
			 * (userAreaLevelId==Integer.parseInt(applicationMessageSource
			 * .getMessage(Constants.Web.BLOCK_LEVEL,null,null))) { Area
			 * block=areaRepository.findByAreaId(userModel.getAreaId());
			 * blockList.add(block); }
			 */
			List<AreaWebModel> blockModelList = new ArrayList<AreaWebModel>();

			AreaWebModel areaWebModel = new AreaWebModel();
			/*
			 * if (userAreaLevelId!=Integer.parseInt(applicationMessageSource.
			 * getMessage(Constants.Web.DISTRICT_LEVEL,null,null))) {
			 * areaWebModel.setAreaId(0); areaWebModel.setAreaName("All Block");
			 * areaWebModel.setParentAreaId(0);
			 * blockModelList.add(areaWebModel); }
			 */

			for (Area block : blockList) {
				areaWebModel = new AreaWebModel();
				areaWebModel.setAreaId(block.getAreaId());
				areaWebModel.setAreaName(block.getAreaName());
				areaWebModel.setParentAreaId(block.getParentAreaId());
				if (block.getAreaId() == userModel.getAreaId()) {
					areaWebModel.setDisabled(true);
				}
				blockModelList.add(areaWebModel);
			}
			dashboardDropDownMenuModel.setBlock(blockModelList);

		}

		List<String> typeDetailIds = Arrays.asList(applicationMessageSource
				.getMessage(Constants.Web.UPPER_PRIMARY_SCHOOL_IDS, null, null)
				.split(","));

		List<Integer> upperPrimarySchoolTypeIds = new ArrayList<Integer>();
		for (String typeDetailId : typeDetailIds) {
			upperPrimarySchoolTypeIds.add(Integer.parseInt(typeDetailId));
		}

		List<TypeDetail> schoolTypes = typeDetailsRepository
				.findByTypeDetailIdIn(upperPrimarySchoolTypeIds);

		List<ValueObject> schoolTypesJSON = new ArrayList<ValueObject>();

		for (TypeDetail schoolType : schoolTypes) {
			ValueObject valueObject = new ValueObject();

			valueObject.setKey(String.valueOf(schoolType.getTypeDetailId()));
			valueObject.setValue(schoolType.getTypeDetail());
			schoolTypesJSON.add(valueObject);
		}
		dashboardDropDownMenuModel.setSchoolType(schoolTypesJSON);

		return dashboardDropDownMenuModel;
	}

	@Override
	public List<GoogleMapDataModel> getMapDataOfSchool(int districtId,
			int blockId, boolean isAdmitted) {
		List<GoogleMapDataModel> googleMapDataModelList = new ArrayList<GoogleMapDataModel>();
		LocalDate localdate = new LocalDate();
		int year = localdate.getYear();
		int monthOfYear = localdate.getMonthOfYear();

		int acedemicYear = year;
		if (monthOfYear < 4) {
			acedemicYear = year - 1;
		}
		acedemicYear = 2017;
		UserModel userModel = (UserModel) stateManager
				.getValue(Constants.Web.USER_PRINCIPAL);

		List<SchoolAggregatedData> schoolCurrentAggregateDatas = new ArrayList<SchoolAggregatedData>();

		// if blockId is -1 and logged in user is of district level then we will
		// set the districtId as the area Id of the area assigned to user
		if (blockId == -1
				&& userModel.getAreaLevel() == Integer
						.parseInt(applicationMessageSource.getMessage(
								Constants.Web.DISTRICT_LEVEL, null, null))) {
			districtId = userModel.getAreaId();
		}

		// if blockId is -1 and logged in user is of block level then we will
		// set the blockId as the area Id of the area assigned to user
		else if (blockId == -1
				&& userModel.getAreaLevel() == Integer
						.parseInt(applicationMessageSource.getMessage(
								Constants.Web.BLOCK_LEVEL, null, null))) {
			blockId = userModel.getAreaId();
		}

		// if blockId is not == -1
		if (blockId != -1) {
			// if blockId is 0 then we will set blockId as the area Id of the
			// area assigned to user
			if (blockId == 0) {
				blockId = userModel.getAreaId();
			}

			schoolCurrentAggregateDatas = schoolAggregatedDataRepository
					.findByAcademicYearIdStartYearAndSchoolBlockAreaId(
							String.valueOf(acedemicYear), blockId);

		} else {
			schoolCurrentAggregateDatas = schoolAggregatedDataRepository
					.findByAcademicYearIdStartYearAndSchoolDistrictIdAreaId(
							String.valueOf(acedemicYear), districtId);
		}

		for (SchoolAggregatedData schoolCurrentAggregateData : schoolCurrentAggregateDatas) {
			GoogleMapDataModel googleMapDataModel = new GoogleMapDataModel();
			SchoolDetails schoolDetails = new SchoolDetails();
			schoolDetails = schoolCurrentAggregateData.getSchool();
			if (schoolDetails.getLatitude() != null
					&& schoolDetails.getLongitutde() != null
					&& !schoolDetails.getLatitude().trim().equalsIgnoreCase("")
					&& !schoolDetails.getLongitutde().trim()
							.equalsIgnoreCase("")) {
				googleMapDataModel.setLatitude(schoolDetails.getLatitude());
				googleMapDataModel.setLongitude(schoolDetails.getLongitutde());
				googleMapDataModel.setId(schoolDetails.getSchoolId());
				googleMapDataModel.setSchoolName(schoolDetails.getSchoolName());
				googleMapDataModel.setUdiseCode(schoolDetails.getUdiseCode());

				// if isAdmited == true this means user is asking for admitted
				// percent
				/*
				 * if(isAdmitted) {
				 * googleMapDataModel.setLeftOutPercent(String.valueOf
				 * (schoolCurrentAggregateData.findPercentOfAdmittedOut())); if
				 * (schoolCurrentAggregateData.findPercentOfAdmittedOut() >= 80)
				 * { googleMapDataModel .setIcon(errorMessageSource.getMessage(
				 * Constants.Web.MAP_GREEN_ICON, null, null)); } else if
				 * (schoolCurrentAggregateData.findPercentOfAdmittedOut() >= 50
				 * && schoolCurrentAggregateData.findPercentOfAdmittedOut() <
				 * 80) { googleMapDataModel
				 * .setIcon(errorMessageSource.getMessage(
				 * Constants.Web.MAP_ORANGE_ICON, null, null)); } else if
				 * (schoolCurrentAggregateData.findPercentOfAdmittedOut() >= 0
				 * && schoolCurrentAggregateData.findPercentOfAdmittedOut() <
				 * 50) { googleMapDataModel
				 * .setIcon(errorMessageSource.getMessage(
				 * Constants.Web.MAP_RED_ICON, null, null)); }
				 * 
				 * }
				 */
				// else user is asking for left out percent
				// else
				{
					googleMapDataModel.setLeftOutPercent(String
							.valueOf(schoolCurrentAggregateData
									.findPercentOfLeftOut())
							+ " %");

					if (schoolCurrentAggregateData.findPercentOfLeftOut() >= 50) {
						googleMapDataModel.setIcon(errorMessageSource
								.getMessage(Constants.Web.MAP_RED_ICON, null,
										null));
					} else if (schoolCurrentAggregateData
							.findPercentOfLeftOut() < 50
							&& schoolCurrentAggregateData
									.findPercentOfLeftOut() > 20) {
						googleMapDataModel.setIcon(errorMessageSource
								.getMessage(Constants.Web.MAP_ORANGE_ICON,
										null, null));
					} else if (schoolCurrentAggregateData
							.findPercentOfLeftOut() <= 20) {
						googleMapDataModel.setIcon(errorMessageSource
								.getMessage(Constants.Web.MAP_GREEN_ICON, null,
										null));
					}
				}

				if (schoolCurrentAggregateData.getLefoutStudents()
						+ schoolCurrentAggregateData.getAdmittedStudents() == 0) {
					googleMapDataModel.setIcon(errorMessageSource.getMessage(
							Constants.Web.MAP_GREY_ICON, null, null));
					googleMapDataModel.setLeftOutPercent("N/A");
				}
				googleMapDataModelList.add(googleMapDataModel);
			}
		}

		return googleMapDataModelList;
	}

	@Override
	@Transactional
	public String exportPdf(int divisionId, int districtId, int blockId,
			int schoolId) {
		UserModel userModel = (UserModel) stateManager
				.getValue(Constants.Web.USER_PRINCIPAL);
		int userAreaLevelId = userModel.getAreaLevel();

		DashboardDataModel dashboardDataModel = new DashboardDataModel();

		if (districtId == 0 && blockId == 0 && schoolId == 0 && divisionId == 0) {
			if (userAreaLevelId == Integer.parseInt(applicationMessageSource
					.getMessage(Constants.Web.STATE_LEVEL, null, null))) {
				dashboardDataModel = fetchDivisionWiseData(divisionId);
			} else if (userAreaLevelId == Integer
					.parseInt(applicationMessageSource.getMessage(
							Constants.Web.DIVISIONAL_LEVEL, null, null))) {
				dashboardDataModel = fetchDistrictWiseData(districtId);
			}

			else if (userAreaLevelId == Integer
					.parseInt(applicationMessageSource.getMessage(
							Constants.Web.DISTRICT_LEVEL, null, null))) {
				dashboardDataModel = fetchBlockWiseData(blockId);
			}

			else if (userAreaLevelId == Integer
					.parseInt(applicationMessageSource.getMessage(
							Constants.Web.BLOCK_LEVEL, null, null))) {
				dashboardDataModel = fetchBlockWiseData(userModel.getAreaId());
			}
		}

		if (schoolId != -1) {
			dashboardDataModel = fetchSchoolWiseWiseData(schoolId);
		}

		else if (blockId != -1) {
			dashboardDataModel = fetchBlockWiseData(blockId);
		}

		else if (districtId != -1) {
			dashboardDataModel = fetchDistrictWiseData(districtId);
		}

		else {
			dashboardDataModel = fetchDivisionWiseData(divisionId);
		}
		String outputPath = applicationMessageSource.getMessage(
				Constants.Web.FILE_PATH, null, null);
		String fileName = outputPath
				+ dashboardDataModel.getSelection().split(">")[dashboardDataModel
						.getSelection().split(">").length - 1] + ".pdf";
		// File file = null;
		try {
			// file = File.createTempFile(fileName, ".pdf");

			// FileOutputStream outputStream = new FileOutputStream(file);

			Font smallBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD,
					BaseColor.WHITE);
			Font blackSmallBold = new Font(Font.FontFamily.HELVETICA, 10,
					Font.BOLD, BaseColor.BLACK);
			Font dataFont = new Font(Font.FontFamily.HELVETICA, 10);

			Document document = new Document(PageSize.A4.rotate());

			Paragraph dashboardTitle = new Paragraph();
			dashboardTitle.setAlignment(Element.ALIGN_CENTER);
			dashboardTitle.setSpacingAfter(10);
			Chunk dashboardChunk = new Chunk("Dashboard Score Card");
			dashboardTitle.add(dashboardChunk);

			Paragraph blankSpace = new Paragraph();
			blankSpace.setAlignment(Element.ALIGN_CENTER);
			blankSpace.setSpacingAfter(10);
			Chunk blankSpaceChunk = new Chunk("          ");
			blankSpace.add(blankSpaceChunk);

			String selection = " State : " + dashboardDataModel.getStateName();

			if (dashboardDataModel.getDivisionName() != null) {
				selection += "  Division : "
						+ dashboardDataModel.getDivisionName();

				if (dashboardDataModel.getDistrictName() != null) {
					selection += "\t \t District : "
							+ dashboardDataModel.getDistrictName();

					if (dashboardDataModel.getBlockName() != null) {
						selection += "  Block : "
								+ dashboardDataModel.getBlockName();

						if (dashboardDataModel.getSchoolName() != null) {
							selection += "\t \t  School Name : "
									+ dashboardDataModel.getSchoolName();
						}
					}
				}
			}

			Paragraph selectionParagaraph = new Paragraph();
			selectionParagaraph.setAlignment(Element.ALIGN_CENTER);
			selectionParagaraph.setSpacingAfter(10);
			Chunk selectionSpaceChunk = new Chunk(selection);
			selectionParagaraph.add(selectionSpaceChunk);

			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(fileName));
			HeaderFooter headerFooter = new HeaderFooter(context,
					applicationMessageSource.getMessage(
							Constants.Web.DOMAIN_NAME, null, null));
			writer.setPageEvent(headerFooter);

			document.open();
			PdfPTable dashboardDataTable = null;
			// float[] dashboardDatacolumnWidths;
			BaseColor cellColor = WebColors.getRGBColor("#E8E3E2");
			BaseColor areaNameColor = WebColors.getRGBColor("#333a3b");
			BaseColor totalColor = WebColors.getRGBColor("#BABABA");
			BaseColor enrolledColor = WebColors.getRGBColor("#aedc5c");
			BaseColor leftOutColor = WebColors.getRGBColor("#f07258");

			// student level data
			if (dashboardDataModel.getSchoolName() != null) {

				dashboardDataTable = new PdfPTable(7);
				// float [] dashboardDatacolumnWidths = new float[] {30f, 30f,
				// 30f ,30f , 30f,30f,30f };
				// dashboardDataTable.setWidths(dashboardDatacolumnWidths);
				PdfPCell dashboardDataCell0 = new PdfPCell(new Paragraph(
						"Student's Name", smallBold));
				PdfPCell dashboardDataCell1 = new PdfPCell(new Paragraph(
						"Student Reg No.", smallBold));
				PdfPCell dashboardDataCell3 = new PdfPCell(new Paragraph(
						"Father's Name", smallBold));

				PdfPCell dashboardDataCell4 = new PdfPCell(new Paragraph(
						"Mother's Name", smallBold));

				PdfPCell dashboardDataCell5 = new PdfPCell(new Paragraph(
						"Gender", smallBold));

				PdfPCell dashboardDataCell6 = new PdfPCell(new Paragraph(
						"Social Category", smallBold));

				PdfPCell dashboardDataCell7 = new PdfPCell(new Paragraph(
						"Enrolled To", smallBold));

				dashboardDataCell0.setBackgroundColor(areaNameColor);
				dashboardDataCell1.setBackgroundColor(areaNameColor);
				dashboardDataCell3.setBackgroundColor(areaNameColor);
				dashboardDataCell4.setBackgroundColor(areaNameColor);
				dashboardDataCell5.setBackgroundColor(areaNameColor);
				dashboardDataCell6.setBackgroundColor(areaNameColor);
				dashboardDataCell7.setBackgroundColor(areaNameColor);
				dashboardDataCell0.setBorderColor(BaseColor.WHITE);
				dashboardDataCell1.setBorderColor(BaseColor.WHITE);
				dashboardDataCell3.setBorderColor(BaseColor.WHITE);
				dashboardDataCell4.setBorderColor(BaseColor.WHITE);
				dashboardDataCell5.setBorderColor(BaseColor.WHITE);
				dashboardDataCell6.setBorderColor(BaseColor.WHITE);
				dashboardDataCell7.setBorderColor(BaseColor.WHITE);

				dashboardDataCell0.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell7.setHorizontalAlignment(Element.ALIGN_CENTER);

				dashboardDataTable.addCell(dashboardDataCell0);
				dashboardDataTable.addCell(dashboardDataCell1);
				dashboardDataTable.addCell(dashboardDataCell3);
				dashboardDataTable.addCell(dashboardDataCell4);
				dashboardDataTable.addCell(dashboardDataCell5);
				dashboardDataTable.addCell(dashboardDataCell6);
				dashboardDataTable.addCell(dashboardDataCell7);

				int i = 1;
				for (Map<String, String> dashboardData : dashboardDataModel
						.getDataTableData()) {
					PdfPCell data0 = new PdfPCell(new Paragraph(
							dashboardData.get("Student's Name "), dataFont));
					PdfPCell data1 = new PdfPCell(new Paragraph(
							dashboardData.get("Student Reg No."), dataFont));
					PdfPCell data2 = new PdfPCell(new Paragraph(
							dashboardData.get("Father's Name"), dataFont));
					PdfPCell data3 = new PdfPCell(new Paragraph(
							dashboardData.get("Mother's Name"), dataFont));
					PdfPCell data4 = new PdfPCell(new Paragraph(
							dashboardData.get("Gender"), dataFont));
					PdfPCell data5 = new PdfPCell(new Paragraph(
							dashboardData.get("Social Category"), dataFont));
					PdfPCell data6 = new PdfPCell(new Paragraph(
							dashboardData.get("Enrolled To"), dataFont));

					if (i % 2 == 0) {
						data0.setBackgroundColor(cellColor);
						data1.setBackgroundColor(cellColor);
						data2.setBackgroundColor(cellColor);
						data3.setBackgroundColor(cellColor);
						data4.setBackgroundColor(cellColor);
						data5.setBackgroundColor(cellColor);
						data6.setBackgroundColor(cellColor);
						data0.setBorderColor(BaseColor.WHITE);
						data1.setBorderColor(BaseColor.WHITE);
						data2.setBorderColor(BaseColor.WHITE);
						data3.setBorderColor(BaseColor.WHITE);
						data4.setBorderColor(BaseColor.WHITE);
						data5.setBorderColor(BaseColor.WHITE);
						data6.setBorderColor(BaseColor.WHITE);

					} else {
						data0.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data1.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data2.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data3.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data4.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data5.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data6.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data0.setBorderColor(BaseColor.WHITE);
						data1.setBorderColor(BaseColor.WHITE);
						data2.setBorderColor(BaseColor.WHITE);
						data3.setBorderColor(BaseColor.WHITE);
						data4.setBorderColor(BaseColor.WHITE);
						data5.setBorderColor(BaseColor.WHITE);
						data6.setBorderColor(BaseColor.WHITE);

					}
					i++;
					dashboardDataTable.addCell(data0);
					dashboardDataTable.addCell(data1);
					dashboardDataTable.addCell(data2);
					dashboardDataTable.addCell(data3);
					dashboardDataTable.addCell(data4);
					dashboardDataTable.addCell(data5);
					dashboardDataTable.addCell(data6);

				}

			}

			// school level data
			else if (dashboardDataModel.getBlockName() != null) {
				dashboardDataTable = new PdfPTable(5);
				// float [] dashboardDatacolumnWidths = new float[] {30f, 30f,
				// 30f ,30f , 30f };
				// dashboardDataTable.setWidths(dashboardDatacolumnWidths);
				PdfPCell dashboardDataCell0 = new PdfPCell(new Paragraph(
						"School Name", smallBold));
				PdfPCell dashboardDataCell1 = new PdfPCell(new Paragraph(
						"Total", blackSmallBold));
				PdfPCell dashboardDataCell3 = new PdfPCell(new Paragraph(
						"Enrolled", blackSmallBold));

				PdfPCell dashboardDataCell4 = new PdfPCell(new Paragraph(
						"Left Out", blackSmallBold));

				PdfPCell dashboardDataCell5 = new PdfPCell(new Paragraph(
						"Left Out %", blackSmallBold));

				dashboardDataCell0.setBackgroundColor(areaNameColor);
				dashboardDataCell1.setBackgroundColor(totalColor);
				dashboardDataCell3.setBackgroundColor(enrolledColor);
				dashboardDataCell4.setBackgroundColor(leftOutColor);
				dashboardDataCell5.setBackgroundColor(leftOutColor);
				dashboardDataCell0.setBorderColor(BaseColor.WHITE);
				dashboardDataCell1.setBorderColor(BaseColor.WHITE);
				dashboardDataCell3.setBorderColor(BaseColor.WHITE);
				dashboardDataCell4.setBorderColor(BaseColor.WHITE);
				dashboardDataCell5.setBorderColor(BaseColor.WHITE);

				dashboardDataCell0.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell5.setHorizontalAlignment(Element.ALIGN_CENTER);

				dashboardDataTable.addCell(dashboardDataCell0);
				dashboardDataTable.addCell(dashboardDataCell1);
				dashboardDataTable.addCell(dashboardDataCell3);
				dashboardDataTable.addCell(dashboardDataCell4);
				dashboardDataTable.addCell(dashboardDataCell5);

				int i = 1;
				for (Map<String, String> dashboardData : dashboardDataModel
						.getDataTableData()) {
					PdfPCell data0 = new PdfPCell(new Paragraph(
							dashboardData.get("School Name "), dataFont));
					PdfPCell data1 = new PdfPCell(new Paragraph(
							dashboardData.get("Total "), dataFont));
					PdfPCell data2 = new PdfPCell(new Paragraph(
							dashboardData.get("Enrolled "), dataFont));
					PdfPCell data3 = new PdfPCell(new Paragraph(
							dashboardData.get("Left Out"), dataFont));
					PdfPCell data4 = new PdfPCell(new Paragraph(
							dashboardData.get("Left Out %"), dataFont));

					if (i % 2 == 0) {
						data0.setBackgroundColor(cellColor);
						data1.setBackgroundColor(cellColor);
						data2.setBackgroundColor(cellColor);
						data3.setBackgroundColor(cellColor);
						data4.setBackgroundColor(cellColor);

						data0.setBorderColor(BaseColor.WHITE);
						data1.setBorderColor(BaseColor.WHITE);
						data2.setBorderColor(BaseColor.WHITE);
						data3.setBorderColor(BaseColor.WHITE);
						data4.setBorderColor(BaseColor.WHITE);

					} else {
						data0.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data1.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data2.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data3.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data4.setBackgroundColor(BaseColor.LIGHT_GRAY);

						data0.setBorderColor(BaseColor.WHITE);
						data1.setBorderColor(BaseColor.WHITE);
						data2.setBorderColor(BaseColor.WHITE);
						data3.setBorderColor(BaseColor.WHITE);
						data4.setBorderColor(BaseColor.WHITE);

					}
					i++;
					dashboardDataTable.addCell(data0);
					dashboardDataTable.addCell(data1);
					dashboardDataTable.addCell(data2);
					dashboardDataTable.addCell(data3);
					dashboardDataTable.addCell(data4);

				}

				// dashboardDatacolumnWidths = new float[] {30f, 30f, 30f ,30f ,
				// 30f,30f, };
			}

			// block Level data
			else if (dashboardDataModel.getDistrictName() != null) {
				dashboardDataTable = new PdfPTable(5);
				// float [] dashboardDatacolumnWidths = new float[] {30f, 30f,
				// 30f ,30f , 30f };
				// dashboardDataTable.setWidths(dashboardDatacolumnWidths);
				PdfPCell dashboardDataCell0 = new PdfPCell(new Paragraph(
						"Block ", smallBold));
				PdfPCell dashboardDataCell1 = new PdfPCell(new Paragraph(
						"Total", blackSmallBold));
				PdfPCell dashboardDataCell3 = new PdfPCell(new Paragraph(
						"Enrolled", blackSmallBold));

				PdfPCell dashboardDataCell4 = new PdfPCell(new Paragraph(
						"Left Out", blackSmallBold));

				PdfPCell dashboardDataCell5 = new PdfPCell(new Paragraph(
						"Left Out %", blackSmallBold));

				dashboardDataCell0.setBackgroundColor(areaNameColor);
				dashboardDataCell1.setBackgroundColor(totalColor);
				dashboardDataCell3.setBackgroundColor(enrolledColor);
				dashboardDataCell4.setBackgroundColor(leftOutColor);
				dashboardDataCell5.setBackgroundColor(leftOutColor);
				dashboardDataCell0.setBorderColor(BaseColor.WHITE);
				dashboardDataCell1.setBorderColor(BaseColor.WHITE);
				dashboardDataCell3.setBorderColor(BaseColor.WHITE);
				dashboardDataCell4.setBorderColor(BaseColor.WHITE);
				dashboardDataCell5.setBorderColor(BaseColor.WHITE);

				dashboardDataCell0.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell5.setHorizontalAlignment(Element.ALIGN_CENTER);

				dashboardDataTable.addCell(dashboardDataCell0);
				dashboardDataTable.addCell(dashboardDataCell1);
				dashboardDataTable.addCell(dashboardDataCell3);
				dashboardDataTable.addCell(dashboardDataCell4);
				dashboardDataTable.addCell(dashboardDataCell5);

				int i = 1;
				for (Map<String, String> dashboardData : dashboardDataModel
						.getDataTableData()) {
					PdfPCell data0 = new PdfPCell(new Paragraph(
							dashboardData.get("Block "), dataFont));
					PdfPCell data1 = new PdfPCell(new Paragraph(
							dashboardData.get("Total "), dataFont));
					PdfPCell data2 = new PdfPCell(new Paragraph(
							dashboardData.get("Enrolled "), dataFont));
					PdfPCell data3 = new PdfPCell(new Paragraph(
							dashboardData.get("Left Out"), dataFont));
					PdfPCell data4 = new PdfPCell(new Paragraph(
							dashboardData.get("Left Out %"), dataFont));

					if (i % 2 == 0) {
						data0.setBackgroundColor(cellColor);
						data1.setBackgroundColor(cellColor);
						data2.setBackgroundColor(cellColor);
						data3.setBackgroundColor(cellColor);
						data4.setBackgroundColor(cellColor);

						data0.setBorderColor(BaseColor.WHITE);
						data1.setBorderColor(BaseColor.WHITE);
						data2.setBorderColor(BaseColor.WHITE);
						data3.setBorderColor(BaseColor.WHITE);
						data4.setBorderColor(BaseColor.WHITE);

					} else {
						data0.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data1.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data2.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data3.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data4.setBackgroundColor(BaseColor.LIGHT_GRAY);

						data0.setBorderColor(BaseColor.WHITE);
						data1.setBorderColor(BaseColor.WHITE);
						data2.setBorderColor(BaseColor.WHITE);
						data3.setBorderColor(BaseColor.WHITE);
						data4.setBorderColor(BaseColor.WHITE);

					}
					i++;
					dashboardDataTable.addCell(data0);
					dashboardDataTable.addCell(data1);
					dashboardDataTable.addCell(data2);
					dashboardDataTable.addCell(data3);
					dashboardDataTable.addCell(data4);

				}

				// dashboardDatacolumnWidths = new float[] {30f, 30f, 30f ,30f ,
				// 30f,30f, };
			}
			// District Level Data
			else if (dashboardDataModel.getDivisionName() != null) {
				dashboardDataTable = new PdfPTable(5);
				// float [] dashboardDatacolumnWidths = new float[] {30f, 30f,
				// 30f ,30f , 30f };
				// dashboardDataTable.setWidths(dashboardDatacolumnWidths);
				PdfPCell dashboardDataCell0 = new PdfPCell(new Paragraph(
						"District ", blackSmallBold));
				PdfPCell dashboardDataCell1 = new PdfPCell(new Paragraph(
						"Total", blackSmallBold));
				PdfPCell dashboardDataCell3 = new PdfPCell(new Paragraph(
						"Enrolled", blackSmallBold));

				PdfPCell dashboardDataCell4 = new PdfPCell(new Paragraph(
						"Left Out", blackSmallBold));

				PdfPCell dashboardDataCell5 = new PdfPCell(new Paragraph(
						"Left Out %", blackSmallBold));

				dashboardDataCell0.setBackgroundColor(areaNameColor);
				dashboardDataCell1.setBackgroundColor(totalColor);
				dashboardDataCell3.setBackgroundColor(enrolledColor);
				dashboardDataCell4.setBackgroundColor(leftOutColor);
				dashboardDataCell5.setBackgroundColor(leftOutColor);
				dashboardDataCell0.setBorderColor(BaseColor.WHITE);
				dashboardDataCell1.setBorderColor(BaseColor.WHITE);
				dashboardDataCell3.setBorderColor(BaseColor.WHITE);
				dashboardDataCell4.setBorderColor(BaseColor.WHITE);
				dashboardDataCell5.setBorderColor(BaseColor.WHITE);

				dashboardDataCell0.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell5.setHorizontalAlignment(Element.ALIGN_CENTER);

				dashboardDataTable.addCell(dashboardDataCell0);
				dashboardDataTable.addCell(dashboardDataCell1);
				dashboardDataTable.addCell(dashboardDataCell3);
				dashboardDataTable.addCell(dashboardDataCell4);
				dashboardDataTable.addCell(dashboardDataCell5);

				int i = 1;
				for (Map<String, String> dashboardData : dashboardDataModel
						.getDataTableData()) {
					PdfPCell data0 = new PdfPCell(new Paragraph(
							dashboardData.get("District "), dataFont));
					PdfPCell data1 = new PdfPCell(new Paragraph(
							dashboardData.get("Total "), dataFont));
					PdfPCell data2 = new PdfPCell(new Paragraph(
							dashboardData.get("Enrolled "), dataFont));
					PdfPCell data3 = new PdfPCell(new Paragraph(
							dashboardData.get("Left Out"), dataFont));
					PdfPCell data4 = new PdfPCell(new Paragraph(
							dashboardData.get("Left Out %"), dataFont));

					if (i % 2 == 0) {
						data0.setBackgroundColor(cellColor);
						data1.setBackgroundColor(cellColor);
						data2.setBackgroundColor(cellColor);
						data3.setBackgroundColor(cellColor);
						data4.setBackgroundColor(cellColor);

						data0.setBorderColor(BaseColor.WHITE);
						data1.setBorderColor(BaseColor.WHITE);
						data2.setBorderColor(BaseColor.WHITE);
						data3.setBorderColor(BaseColor.WHITE);
						data4.setBorderColor(BaseColor.WHITE);

					} else {
						data0.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data1.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data2.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data3.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data4.setBackgroundColor(BaseColor.LIGHT_GRAY);

						data0.setBorderColor(BaseColor.WHITE);
						data1.setBorderColor(BaseColor.WHITE);
						data2.setBorderColor(BaseColor.WHITE);
						data3.setBorderColor(BaseColor.WHITE);
						data4.setBorderColor(BaseColor.WHITE);

					}
					i++;
					dashboardDataTable.addCell(data0);
					dashboardDataTable.addCell(data1);
					dashboardDataTable.addCell(data2);
					dashboardDataTable.addCell(data3);
					dashboardDataTable.addCell(data4);

				}

				// dashboardDatacolumnWidths = new float[] {30f, 30f, 30f ,30f ,
				// 30f,30f, };
			}
			// division level data
			else {

				dashboardDataTable = new PdfPTable(5);
				// float [] dashboardDatacolumnWidths = new float[] {30f, 30f,
				// 30f ,30f , 30f };
				// dashboardDataTable.setWidths(dashboardDatacolumnWidths);
				PdfPCell dashboardDataCell0 = new PdfPCell(new Paragraph(
						"Division ", smallBold));
				PdfPCell dashboardDataCell1 = new PdfPCell(new Paragraph(
						"Total", blackSmallBold));
				PdfPCell dashboardDataCell3 = new PdfPCell(new Paragraph(
						"Enrolled", blackSmallBold));

				PdfPCell dashboardDataCell4 = new PdfPCell(new Paragraph(
						"Left Out", blackSmallBold));

				PdfPCell dashboardDataCell5 = new PdfPCell(new Paragraph(
						"Left Out %", blackSmallBold));

				dashboardDataCell0.setBackgroundColor(areaNameColor);
				dashboardDataCell1.setBackgroundColor(totalColor);
				dashboardDataCell3.setBackgroundColor(enrolledColor);
				dashboardDataCell4.setBackgroundColor(leftOutColor);
				dashboardDataCell5.setBackgroundColor(leftOutColor);
				dashboardDataCell0.setBorderColor(BaseColor.WHITE);
				dashboardDataCell1.setBorderColor(BaseColor.WHITE);
				dashboardDataCell3.setBorderColor(BaseColor.WHITE);
				dashboardDataCell4.setBorderColor(BaseColor.WHITE);
				dashboardDataCell5.setBorderColor(BaseColor.WHITE);

				dashboardDataCell0.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				dashboardDataCell5.setHorizontalAlignment(Element.ALIGN_CENTER);

				dashboardDataTable.addCell(dashboardDataCell0);
				dashboardDataTable.addCell(dashboardDataCell1);
				dashboardDataTable.addCell(dashboardDataCell3);
				dashboardDataTable.addCell(dashboardDataCell4);
				dashboardDataTable.addCell(dashboardDataCell5);

				int i = 1;
				for (Map<String, String> dashboardData : dashboardDataModel
						.getDataTableData()) {
					PdfPCell data0 = new PdfPCell(new Paragraph(
							dashboardData.get("Division "), dataFont));
					PdfPCell data1 = new PdfPCell(new Paragraph(
							dashboardData.get("Total "), dataFont));
					PdfPCell data2 = new PdfPCell(new Paragraph(
							dashboardData.get("Enrolled "), dataFont));
					PdfPCell data3 = new PdfPCell(new Paragraph(
							dashboardData.get("Left Out"), dataFont));
					PdfPCell data4 = new PdfPCell(new Paragraph(
							dashboardData.get("Left Out %"), dataFont));

					if (i % 2 == 0) {
						data0.setBackgroundColor(cellColor);
						data1.setBackgroundColor(cellColor);
						data2.setBackgroundColor(cellColor);
						data3.setBackgroundColor(cellColor);
						data4.setBackgroundColor(cellColor);

						data0.setBorderColor(BaseColor.WHITE);
						data1.setBorderColor(BaseColor.WHITE);
						data2.setBorderColor(BaseColor.WHITE);
						data3.setBorderColor(BaseColor.WHITE);
						data4.setBorderColor(BaseColor.WHITE);

					} else {
						data0.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data1.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data2.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data3.setBackgroundColor(BaseColor.LIGHT_GRAY);
						data4.setBackgroundColor(BaseColor.LIGHT_GRAY);

						data0.setBorderColor(BaseColor.WHITE);
						data1.setBorderColor(BaseColor.WHITE);
						data2.setBorderColor(BaseColor.WHITE);
						data3.setBorderColor(BaseColor.WHITE);
						data4.setBorderColor(BaseColor.WHITE);

					}
					i++;
					dashboardDataTable.addCell(data0);
					dashboardDataTable.addCell(data1);
					dashboardDataTable.addCell(data2);
					dashboardDataTable.addCell(data3);
					dashboardDataTable.addCell(data4);

				}

				// dashboardDatacolumnWidths = new float[] {30f, 30f, 30f ,30f ,
				// 30f,30f, };

			}

			document.add(blankSpace);
			document.add(dashboardTitle);

			document.add(selectionParagaraph);

			document.add(dashboardDataTable);
			document.close();

			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

}
