package org.sdrc.udise.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.sdrc.udise.model.MailModel;
import org.sdrc.udise.service.AggregateDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * 
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public class DailyAggregationForAreaJob extends QuartzJobBean {

	private AggregateDataService aggregateDataService;
	
	
	public void setAggregateDataService(AggregateDataService aggregateDataService) {
		this.aggregateDataService = aggregateDataService;
	}




	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try
		{
		aggregateDataService.aggregateDataForArea();
		aggregateDataService.aggregateDataForSchool();
		}
		catch(Exception e)
		{
			MailModel mailModel=new MailModel();
			List<String> toEmailIds=new ArrayList<String>();
			
			String message="An Exception has been occured on http://tmsup.org .Please find below the exception stack trace <br><br>";
			
			for(int i=0;i<e.getStackTrace().length;i++)
			{
				message+="Class Name :"+e.getStackTrace()[i].getClassName() +"  Method Name :"+e.getStackTrace()[i].getMethodName() +"() Line Number :"+e.getStackTrace()[i].getLineNumber()+" Exception Cause:"+e.getLocalizedMessage()+"<br><br>";
			}
			
			toEmailIds.add("harsh@sdrc.co.in");
			toEmailIds.add("azaruddin@sdrc.co.in");
			/*toEmailIds.add("tms.rmsaup@sdrc.co.in");*/
			mailModel.setToEmailIds(toEmailIds);
			mailModel.setMessage(message);
			mailModel.setSubject("Transition Monitoring Sytsem : Exception Log");
			mailModel.setFromUserName("System Log");
			mailModel.setToUserName("TMS Team");
			
			try
			{
			aggregateDataService.sendMail(mailModel);
			e.printStackTrace();
			}
			catch(Exception mailException)
			{
				mailException.printStackTrace();
			}
		
		}
		
	}

}
