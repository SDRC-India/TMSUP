package org.sdrc.udise.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.sdrc.udise.core.Authorize;
import org.sdrc.udise.model.AdvancedSearchRequestModel;
import org.sdrc.udise.model.RawDataModel;
import org.sdrc.udise.service.AdvancedViewService;
import org.sdrc.udise.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author Azaruddin (azaruddin@sdrc.co.in)
 *
 */

@Controller
public class AdvancedViewController {

	@Autowired
	private StateManager stateManager;

	@Autowired
	private AdvancedViewService advancedViewService;

	@RequestMapping(value = "/intializeAdvancedView")
	@Authorize(feature="dashboard",permission="view")
	@ResponseBody
	public Map<String, JSONObject> getInitViewDataForAdvancedSearch() {

		Map<String, JSONObject> data = advancedViewService
				.getInitViewDataForAdvancedSearch();

		return data;
	}

	
	@Authorize(feature="dashboard",permission="view")
	@RequestMapping(value = "/searchData", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public List<Map<String, String>> searchData(
			@RequestBody AdvancedSearchRequestModel advancedSearchRequestModel) {
		return advancedViewService.searchData(advancedSearchRequestModel);
	}
	@Authorize(feature="dashboard",permission="view")
	@RequestMapping(value = "/searchRawData", method = RequestMethod.POST, consumes = "application/json")
	public void searchRawData(@RequestBody RawDataModel rawDataModel,
			HttpServletResponse httpServletResponse) throws IOException {
		File file = advancedViewService.searchRawData(rawDataModel);
		try {
			String mimeType;
			mimeType = "application/octet-stream";
			httpServletResponse.setContentType(mimeType);
			httpServletResponse.setHeader("Content-Disposition", String.format(
					"attachment; filename=\"%s\"", file.getName()));
			httpServletResponse.setContentLength((int) file.length());
			InputStream inputStream = new BufferedInputStream(
					new FileInputStream(file));
			FileCopyUtils.copy(inputStream,
					httpServletResponse.getOutputStream());

		} finally {
			httpServletResponse.getOutputStream().close();
			if (file != null) {
				file.delete();
			}
		}
	}
	
	@Authorize(feature="dashboard",permission="view")
	@RequestMapping("/report")
	public String getReportPage()
	{
		return "report";
	}
	
	@Authorize(feature="dashboard",permission="view")
	@RequestMapping("advance")
	public String getAdvanceSearchPage()
	{
		return "advanceSearch";
	}

}
