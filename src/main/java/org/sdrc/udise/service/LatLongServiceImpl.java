package org.sdrc.udise.service;

import java.io.Serializable;
import java.sql.Timestamp;


public class LatLongServiceImpl implements LatLongService {

	//private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM
	// @SuppressWarnings("unchecked")
	// public static void main(String args[]) throws
	// IOException, SQLException {
	// int counter = 1;
	// final String API_KEY = "AIzaSyBnBa7UFnWZN65TQbzwBMe2bWeKhzZQ2yE";
	// final String url =
	// "https://maps.googleapis.com/maps/api/distancematrix/json";
	// String jdbcdriverClassName = "org.postgresql.Driver";
	// String jdbcurl = "jdbc:postgresql://192.168.1.36:5432/udise";
	// //String jdbcurl = "jdbc:postgresql://127.0.0.1:5432/udise_r";
	//
	// String jdbcusername = "postgres";
	// String jdbcpassword = "admin";
	//
	// new LatLongServiceImpl().insertJson(jdbcurl, jdbcusername, jdbcpassword);

	//
	// }

	// @SuppressWarnings("unchecked")
	// public void insertJson(String jdbcurl, String jdbcusername,
	// String jdbcpassword) {
	// Connection connection = null;
	// PreparedStatement ps = null;
	// try {
	// connection = DriverManager.getConnection(jdbcurl, jdbcusername,
	// jdbcpassword);
	//
	// ps = connection
	// .prepareStatement("insert into lat_long_link (distance_json,islive,school_id_fk) values (?,'true',?) ");
	//
	// List<School> schools = this
	// .getRecordsFromDataSource(connection);
	//
	// for (final School schoolOrigin : schools) {
	// JSONObject finalJson = new JSONObject();
	// JSONArray km_5_array = new JSONArray();
	// JSONArray km_7_array = new JSONArray();
	// JSONArray km_10_array = new JSONArray();
	//
	// for (final School schoolDestination : schools) {
	// if (((schoolOrigin.getSchoolId() != schoolDestination
	// .getSchoolId()) &&
	//
	// ((schoolDestination.getSchoolType() == 2)
	// || (schoolDestination.getSchoolType() == 3)
	// || (schoolDestination.getSchoolType() == 4)
	// || (schoolDestination.getSchoolType() == 5)
	// || (schoolDestination.getSchoolType() == 9) || (schoolDestination
	// .getSchoolType() == 11)
	//
	// ))) {
	//
	// if (((schoolOrigin.getLatitude() != null && !schoolOrigin
	// .getLatitude().isEmpty()) && (schoolOrigin
	// .getLongitutde() != null && !schoolOrigin
	// .getLongitutde().isEmpty())
	//
	// )
	// && (schoolDestination.getLatitude() != null && !schoolDestination
	// .getLatitude().isEmpty())
	// && (schoolDestination.getLongitutde() != null && !schoolDestination
	// .getLongitutde().isEmpty())) {
	//
	// double distance = this.distance(Double
	// .parseDouble(schoolOrigin.getLatitude()),
	// Double.parseDouble(schoolOrigin
	// .getLongitutde()), Double
	// .parseDouble(schoolDestination
	// .getLatitude()), Double
	// .parseDouble(schoolDestination
	// .getLongitutde()));
	//
	// JSONObject schoolD = new JSONObject();
	// schoolD.put("id", schoolDestination.getSchoolId());
	// schoolD.put("School Name",
	// schoolDestination.getSchoolName());
	// schoolD.put("Block Name",
	// schoolDestination.getBlockName());
	// schoolD.put("District Name",
	// schoolDestination.getDistrictName());
	// schoolD.put("Udise Code",
	// schoolDestination.getUdiseCode());
	//
	// if (distance <= 5) {
	//
	// km_5_array.add(km_5_array.size(), schoolD);
	// } else if (distance <= 7) {
	// km_7_array.add(km_7_array.size(), schoolD);
	// } else if (distance <= 10) {
	// km_10_array.add(km_10_array.size(), schoolD);
	// }
	//
	// }
	//
	// }
	// }
	//
	// finalJson.put(5, km_5_array);
	// finalJson.put(7, km_7_array);
	// finalJson.put(10, km_10_array);
	//
	// ps.setString(1, finalJson.toString());
	// ps.setInt(2, schoolOrigin.getSchoolId());
	//
	// ps.executeUpdate();
	//
	// }
	// System.out.println("Finished");
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// if (connection != null) {
	// try {
	// connection.close();
	// } catch (SQLException e) {
	// 
	// e.printStackTrace();
	// }
	// }
	// }
	// }
	//
	// public double distance(double startLat, double startLong, double endLat,
	// double endLong) {
	//
	// double dLat = Math.toRadians((endLat - startLat));
	// double dLong = Math.toRadians((endLong - startLong));
	//
	// startLat = Math.toRadians(startLat);
	// endLat = Math.toRadians(endLat);
	//
	// double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat)
	// * haversin(dLong);
	// double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	//
	// return EARTH_RADIUS * c; // <-- d
	// }
	//
	// public static double haversin(double val) {
	// return Math.pow(Math.sin(val / 2), 2);
	// }
	//
	// public List<School> getRecordsFromDataSource(Connection connection)
	// throws Exception {
	//
	// List<SchoolDetails> schools = new ArrayList<>();
	// try {
	//
	// Statement smt = connection.createStatement();
	// // ResultSet rs = smt.executeQuery(
	// // "select s.*,a.area_name as block_name,a2.area_name as
	// // district_name from school_details s inner join area a on
	// // a.areaid=s.block_id_fk "
	// // + " inner join area a2 on a2.areaid=s.district_id_fk where
	// // s.lattitude IS NOT NULL AND s.longitude IS NOT NULL");
	// //
	// ResultSet rs = smt
	// .executeQuery("select s.*,a.area_name as block_name,a2.area_name as district_name from school_details s inner join area a on a.areaid=s.block_id_fk "
	// + " inner join area a2 on a2.areaid=s.district_id_fk");
	//
	// while (rs.next()) {
	//
	// School school = new School();
	// school.setSchoolId(rs.getInt("schoolId"));
	// school.setLatitude(rs.getString("lattitude"));
	// school.setLongitutde(rs.getString("longitude"));
	// school.setSchoolName(rs.getString("school_name"));
	// school.setUdiseCode(rs.getString("udise_code"));
	// school.setBlockName(rs.getString("block_name"));
	// school.setDistrictName(rs.getString("district_name"));
	// school.setSchoolType(rs.getInt("school_type_fk"));
	//
	// schools.add(school);
	// }
	// } catch (Exception e) {
	// throw e;
	//
	// }
	//
	// return schools;
	//
	// }

}

class School implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer schoolId;

	private String udiseCode;

	private String schoolName;

	private String createdBy;

	private Timestamp createdDate;

	private boolean isLive;

	private String latitude;

	private String longitutde;

	private String blockName;

	private String districtName;

	private int schoolType;

	public int getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(int schoolType) {
		this.schoolType = schoolType;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getUdiseCode() {
		return udiseCode;
	}

	public void setUdiseCode(String udiseCode) {
		this.udiseCode = udiseCode;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitutde() {
		return longitutde;
	}

	public void setLongitutde(String longitutde) {
		this.longitutde = longitutde;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@Override
	public String toString() {
		return "SchoolDetails [schoolId=" + schoolId + ", udiseCode="
				+ udiseCode + ", schoolName=" + schoolName + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", isLive="
				+ isLive + ", latitude=" + latitude + ", longitutde="
				+ longitutde + ", blockName=" + blockName + ", districtName="
				+ districtName + "]";
	}

}
