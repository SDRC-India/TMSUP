package org.sdrc.udise.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;

/**
 * @author Azaruddin (azaruddin@sdrc.co.in)
 *
 */
public interface SchoolService {

	public List<Map<String, String>> getDashBoardViewOfDEO(String acedemicYear);

	public Map<String, JSONArray> getAllSecondarySchoolInBlock(int blockId);

	public List<Map<String, String>> getAllStudentsBySchool(int schoolId);

	public Map<String, JSONArray> getJsonDataForModelView(int schoolId);

	public boolean saveSchool(String schoolName, int districtId, int blockId,
			String udiseCode,

			int schoolType, int fromSchool, List<Integer> studentsIds);

}
