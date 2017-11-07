/**
 * 
 */
package org.sdrc.udise.service;

import org.sdrc.udise.model.MailModel;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public interface AggregateDataService {
	
	
	/**
	 * This method will aggregate data of no of student admitted and no of student left out at different area level
	 * @return
	 */
	public boolean aggregateDataForArea();
	
	
	/**
	 * 
	 * This method will aggregate data caste wise and gender wise and total data left out and admitted grouped by school
	 */
	public boolean aggregateDataForSchool();
	
	
	public boolean sendMail(MailModel mailModel) throws Exception;

}
