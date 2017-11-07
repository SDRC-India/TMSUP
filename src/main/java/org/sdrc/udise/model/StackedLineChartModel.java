/**
 * 
 */
package org.sdrc.udise.model;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public class StackedLineChartModel {
	
	// we are going to keep variable as cap because we are going to use variable name as legend in d3
	String Label;
	String Leftout;
	String Enrolled;
	public String getLabel() {
		return Label;
	}
	public void setLabel(String Label) {
		this.Label = Label;
	}
	public String getLeftout() {
		return Leftout;
	}
	public void setLeftout(String Leftout) {
		this.Leftout = Leftout;
	}
	public String getEnrolled() {
		return Enrolled;
	}
	public void setEnrolled(String Enrolled) {
		this.Enrolled = Enrolled;
	}

}
