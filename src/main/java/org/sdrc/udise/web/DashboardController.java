package org.sdrc.udise.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.sdrc.udise.core.Authorize;
import org.sdrc.udise.model.DashboardDataModel;
import org.sdrc.udise.model.DashboardDropDownMenuModel;
import org.sdrc.udise.model.GoogleMapDataModel;
import org.sdrc.udise.model.UserModel;
import org.sdrc.udise.service.AggregateDataService;
import org.sdrc.udise.service.DashboardService;
import org.sdrc.udise.util.Constants;
import org.sdrc.udise.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@Controller
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private StateManager stateManager;

	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;

	@Autowired
	private AggregateDataService aggregateDataService;

	// @Authorize(feature="dashboard1",permission="view")
	@RequestMapping("/updateSchoolTable")
	@ResponseBody
	public boolean updateSchoolTable() {
		return dashboardService.insertSchools();
	}

	@RequestMapping("/aggregateData")
	@ResponseBody
	public boolean updateAggregateData() {
		aggregateDataService.aggregateDataForSchool();
		return aggregateDataService.aggregateDataForArea();
	}

	@Authorize(feature = "dashboard", permission = "view")
	@RequestMapping("/getDropDownJSON")
	@ResponseBody
	public DashboardDropDownMenuModel getDropDownJSON() {
		return dashboardService.fetchDropDownMenuForDashboard();
	}

	@Authorize(feature = "dashboard", permission = "view")
	@RequestMapping(value = "/getAggregateData", method = RequestMethod.POST)
	@ResponseBody
	public DashboardDataModel getAggregateData(
			@RequestParam("dvisionId") int divisionId,
			@RequestParam("districtId") int districtId,
			@RequestParam("blockId") int blockId,
			@RequestParam("schoolId") int schoolId) {
		UserModel userModel = (UserModel) stateManager
				.getValue(Constants.Web.USER_PRINCIPAL);
		int userAreaLevelId = userModel.getAreaLevel();

		if (districtId == 0 && blockId == 0 && schoolId == 0 && divisionId == 0) {
			if (userAreaLevelId == Integer.parseInt(applicationMessageSource
					.getMessage(Constants.Web.STATE_LEVEL, null, null))) {
				dashboardService.fetchDivisionWiseData(divisionId);
			} else if (userAreaLevelId == Integer
					.parseInt(applicationMessageSource.getMessage(
							Constants.Web.DIVISIONAL_LEVEL, null, null))) {
				dashboardService.fetchDistrictWiseData(districtId);
			}

			else if (userAreaLevelId == Integer
					.parseInt(applicationMessageSource.getMessage(
							Constants.Web.DISTRICT_LEVEL, null, null))) {
				dashboardService.fetchBlockWiseData(blockId);
			}

			else if (userAreaLevelId == Integer
					.parseInt(applicationMessageSource.getMessage(
							Constants.Web.BLOCK_LEVEL, null, null))) {
				dashboardService.fetchBlockWiseData(userModel.getAreaId());
			}
		}
		// when we have to fetch divison wise data then all other ids will be -1
		else if (districtId == -1 && blockId == -1 && schoolId == -1) {
			return dashboardService.fetchDivisionWiseData(divisionId);
		}
		// when we have to fetch district wise data then all other ids will be
		// -1
		else if (divisionId == -1 && blockId == -1 && schoolId == -1) {
			return dashboardService.fetchDistrictWiseData(districtId);
		}

		// when we have to fetch block wise data then all other ids will be -1
		else if (divisionId == -1 && districtId == -1 && schoolId == -1) {

			// if blockId == 0 and the and user level is of block then we will
			// set blockId as logged in user's area
			if (blockId == 0
					&& userAreaLevelId == Integer
							.parseInt(applicationMessageSource.getMessage(
									Constants.Web.BLOCK_LEVEL, null, null))) {
				blockId = userModel.getAreaId();
			}

			return dashboardService.fetchBlockWiseData(blockId);
		}

		// when we have to fetch school wise data then all other ids will be -1
		else if (divisionId == -1 && districtId == -1 && blockId == -1) {
			return dashboardService.fetchSchoolWiseWiseData(schoolId);
		}
		return null;
	}

	@Authorize(feature = "dashboard", permission = "view")
	@RequestMapping("/getGoogleMapView")
	@ResponseBody
	public List<GoogleMapDataModel> getGoogleMapView(
			@RequestParam("districtId") int districtId,
			@RequestParam("blockId") int blockId,
			@RequestParam("isAdmitted") boolean isAdmitted) {
		return dashboardService.getMapDataOfSchool(districtId, blockId,
				isAdmitted);
	}

	@Authorize(feature = "dashboard", permission = "view")
	@RequestMapping(value = "/exportPDF", method = RequestMethod.GET)
	@ResponseBody
	public String exportToPDf(@RequestParam("dvisionId") int divisionId,
			@RequestParam("districtId") int districtId,
			@RequestParam("blockId") int blockId,
			@RequestParam("schoolId") int schoolId,
			HttpServletResponse httpServletResponse) throws IOException {
		return  dashboardService.exportPdf(divisionId, districtId, blockId, schoolId);
	}
	
	@Authorize(feature = "dashboard", permission = "view")
	@RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
	public void downLoad(@RequestParam("fileName") String name,
			HttpServletResponse response) throws IOException {
		InputStream inputStream;
		String fileName = "";
		try {
			fileName = name.replaceAll("%3A", ":").replaceAll("%2F", "/")
					.replaceAll("%5C", "/").replaceAll("%2C", ",")
					.replaceAll("\\+", " ").replaceAll("%22", "")
					.replaceAll("%3F", "?").replaceAll("%3D", "=");
			inputStream = new FileInputStream(fileName);
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					new java.io.File(fileName).getName());
			response.setHeader(headerKey, headerValue);
			response.setContentType("application/octet-stream"); // for all file
																	// type
			ServletOutputStream outputStream = response.getOutputStream();
			FileCopyUtils.copy(inputStream, outputStream);
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			new File(fileName).delete();
		}
	}

	@Authorize(feature = "dashboard", permission = "view")
	@RequestMapping("/dashboard")
	public String getDashboardPage() {
		return "dashboard";
	}
}
