package org.sdrc.udise.util;

public enum EnrollmentType {

	WITHIN_SCHOOL_LIST(18), NEW_ADDED_SCHOOL_IN_OTHER(19), OTHER_STATE(20), MIGRATION(
			21);

	private int value;

	EnrollmentType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
