/**
 * 
 */
package org.sdrc.udise.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in) This domain will hold the data of
 *         each school and their school within a distance range in
 * 
 * 
 */

@Entity
@Table(name = "Lat_Long_Link")
public class SchoolLatLongLink {

	@Id
	@Column(name = "lat_long_link_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer latLongLinkId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id_fk", nullable = false, unique = true)
	private SchoolDetails school;

	@Column(name = "distance_json", nullable = true, columnDefinition = "text", length = 65556)
	private String distanceJSON;

	@Column(name = "isLive")
	private boolean isLive;

	// getter setters
	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public Integer getLatLongLinkId() {
		return latLongLinkId;
	}

	public void setLatLongLinkId(Integer latLongLinkId) {
		this.latLongLinkId = latLongLinkId;
	}

	public SchoolDetails getSchool() {
		return school;
	}

	public void setSchool(SchoolDetails school) {
		this.school = school;
	}

	public String getDistanceJSON() {
		return distanceJSON;
	}

	public void setDistanceJSON(String distanceJSON) {
		this.distanceJSON = distanceJSON;
	}

}
