/**
 * 
 */
package org.sdrc.udise.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.joda.time.LocalDate;
import org.sdrc.udise.domain.AcademicYear;
import org.sdrc.udise.domain.AggregationData;
import org.sdrc.udise.domain.Area;
import org.sdrc.udise.domain.SchoolAggregatedData;
import org.sdrc.udise.domain.SchoolDetails;
import org.sdrc.udise.model.MailModel;
import org.sdrc.udise.repository.AcedemicYearRepository;
import org.sdrc.udise.repository.AggregationDataRepository;
import org.sdrc.udise.repository.SchoolAggregatedDataRepository;
import org.sdrc.udise.repository.SchoolCurrentAggregatedDataRepository;
import org.sdrc.udise.repository.StudentDetailsRepository;
import org.sdrc.udise.repository.StudentSchoolMappingRepository;
import org.sdrc.udise.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@Service
public class AggregateDataServiceImpl implements AggregateDataService {
	
	@Autowired
	private AggregationDataRepository aggregationDataRepository; 
	
	@Autowired
	private StudentDetailsRepository studentsDetailsRepository;
	
	@Autowired
	private StudentSchoolMappingRepository studentSchoolMappingRepository;
	
	@Autowired
	private SchoolAggregatedDataRepository schoolAggregatedDataRepository;
	
	@Autowired
	private SchoolCurrentAggregatedDataRepository schoolCurrentAggregatedDataRepository;
	
	@Autowired 
	private  AcedemicYearRepository acedemicYearRepository;
	
	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;

	/* (non-Javadoc)
	 * @see org.sdrc.udise.service.AggregateDataService#aggregateDataForArea()
	 */
	@Override
	@Transactional
	public boolean aggregateDataForArea() {
		
		LocalDate localdate = new LocalDate();
		int year = localdate.getYear();
		int monthOfYear = localdate.getMonthOfYear();

		int acedemicYear = year;
		if (monthOfYear < 4) {
			acedemicYear = year - 1;
		}
		
		AcademicYear academicYear=acedemicYearRepository.findByStartYear(String.valueOf(acedemicYear));
		int enrollTypeMigrated=Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.MIGRATION, null,null));
		
		//getting all records i.e. of division,district,block for the current academic year.
		List<AggregationData>  aggregationDatas=aggregationDataRepository.findByAcademicYearIdStartYearOrderByAreaIdAreaNameAsc(String.valueOf(acedemicYear));
		
		
		//creating a map for all records fetched above with key as areaId
		Map<Integer,AggregationData> aggregateDataMap=new HashMap<Integer,AggregationData>();
		for(AggregationData aggregationData :aggregationDatas)
		{
			aggregateDataMap.put(aggregationData.getAreaId().getAreaId(), aggregationData);
		}
		
		
		{
			//returned is areaId->blockId,integer->submitted,integer->not submitted group by block
		List<Object[]> blockAggregatedData=studentsDetailsRepository.getAggregateStudentsDataAtBlockLevel(String.valueOf(acedemicYear));
		//returned is areaId->districtId,integer->submitted,integer->not submitted group by block
		List<Object[]> districtAggregatedData=studentsDetailsRepository.getAggregateStudentsDataAtDistrictLevel(String.valueOf(acedemicYear));
		//returned is areaId->divisionId,integer->submitted,integer->not submitted group by block
		List<Object[]> divisionAggregatedData=studentsDetailsRepository.getAggregateStudentsDataAtDivisionLevel(String.valueOf(acedemicYear));
		
		
		//returned is areaId->blockId,integer->migrated group by block
		List<Object[]> blockAggregatedDataForMigration=studentSchoolMappingRepository.getAggregateStudentsDataAtBlockLevelMigrated(String.valueOf(acedemicYear),enrollTypeMigrated);
		//returned is areaId->districtId,integer->migrated group by block
		List<Object[]> districtAggregatedDataForMigration=studentSchoolMappingRepository.getAggregateStudentsDataAtDistrictLevelMigrated(String.valueOf(acedemicYear),enrollTypeMigrated);
		//returned is areaId->divisionId,integer->migrated group by block
		List<Object[]> divisionAggregatedDataForMigration=studentSchoolMappingRepository.getAggregateStudentsDataAtDivisionLevelMigrated(String.valueOf(acedemicYear),enrollTypeMigrated);
		
		blockAggregatedData.addAll(districtAggregatedData);
		blockAggregatedData.addAll(divisionAggregatedData);
		
		
		blockAggregatedDataForMigration.addAll(districtAggregatedDataForMigration);
		blockAggregatedDataForMigration.addAll(divisionAggregatedDataForMigration);
		
		Map<String,List<Integer>>  migratedDataMap=new HashMap<String, List<Integer>>();
		
		for(Object [] migratedData:blockAggregatedDataForMigration)
		{
			List<Integer> migratedDataList=new ArrayList<Integer>();
			migratedDataList.add(Integer.parseInt(migratedData[1].toString()));
			migratedDataList.add(Integer.parseInt(migratedData[2].toString()));
			migratedDataList.add(Integer.parseInt(migratedData[3].toString()));
			migratedDataList.add(Integer.parseInt(migratedData[4].toString()));
			migratedDataList.add(Integer.parseInt(migratedData[5].toString()));
			migratedDataList.add(Integer.parseInt(migratedData[6].toString()));
			migratedDataList.add(Integer.parseInt(migratedData[7].toString()));
			migratedDataList.add(Integer.parseInt(migratedData[8].toString()));
			migratedDataList.add(Integer.parseInt(migratedData[9].toString()));
			migratedDataMap.put(migratedData[0].toString(), migratedDataList);
		}
		
		List<AggregationData> newAggregateData=new ArrayList<AggregationData>();
		if(blockAggregatedData.size()>0)
		{
		for(Object[] aggregateData : blockAggregatedData)
		{
			AggregationData aggregationData=new AggregationData();
			
			// if the aggregate table contain data for the area then it will get updated
			if(aggregateDataMap.containsKey(Integer.parseInt(aggregateData[0].toString())))
			{
			aggregationData=aggregateDataMap.get(Integer.parseInt(aggregateData[0].toString()));
			}
			
			//  if the aggregate table doesn't contain the data for the area then it will
			// insert a new column
			else
			{
				Area area = new Area();
				area.setAreaId(Integer.parseInt(aggregateData[0].toString()));
				aggregationData.setAreaId(area);
				aggregationData.setAcademicYearId(academicYear);
			}
			int totalMigrated=0,boyMigrated=0,girlMigrated=0,thirdGenderMigrated=0,
					genMigrated=0,obcMigrated=0,scGenderMigrated=0,stGenderMigrated=0,minorityMigrated=0;
			
			if(migratedDataMap.containsKey(aggregateData[0].toString()))
			{
				totalMigrated=migratedDataMap.get(aggregateData[0].toString()).get(0);
				boyMigrated=migratedDataMap.get(aggregateData[0].toString()).get(1);
				girlMigrated=migratedDataMap.get(aggregateData[0].toString()).get(2);
				thirdGenderMigrated=migratedDataMap.get(aggregateData[0].toString()).get(3);
				genMigrated=migratedDataMap.get(aggregateData[0].toString()).get(4);
				obcMigrated=migratedDataMap.get(aggregateData[0].toString()).get(5);
				scGenderMigrated=migratedDataMap.get(aggregateData[0].toString()).get(6);
				stGenderMigrated=migratedDataMap.get(aggregateData[0].toString()).get(7);
				minorityMigrated=migratedDataMap.get(aggregateData[0].toString()).get(8);
				migratedDataMap.remove(aggregateData[0].toString());
			}
			aggregationData.setAdmittedStudents(Integer.parseInt(aggregateData[1].toString())-totalMigrated);
			aggregationData.setLefoutStudents(Integer.parseInt(aggregateData[2].toString())+totalMigrated);
			aggregationData.setAdmittedBoys(Integer.parseInt(aggregateData[3].toString())-boyMigrated);
			aggregationData.setAdmittedGirls(Integer.parseInt(aggregateData[4].toString())-girlMigrated);
			aggregationData.setAdmittedThirdGender(Integer.parseInt(aggregateData[5].toString())-thirdGenderMigrated);
			aggregationData.setLeftoutBoys(Integer.parseInt(aggregateData[6].toString())+boyMigrated);
			aggregationData.setLeftoutGirls(Integer.parseInt(aggregateData[7].toString())+girlMigrated);
			aggregationData.setLeftoutThirdGender(Integer.parseInt(aggregateData[8].toString())+thirdGenderMigrated);
			aggregationData.setAdmittedStudentsGen(Integer.parseInt(aggregateData[9].toString())-genMigrated);
			aggregationData.setAdmittedStudentsOBC(Integer.parseInt(aggregateData[10].toString())-obcMigrated);
			aggregationData.setAdmittedStudentsSC(Integer.parseInt(aggregateData[11].toString())-scGenderMigrated);
			aggregationData.setAdmittedStudentsST(Integer.parseInt(aggregateData[12].toString())-stGenderMigrated);
			aggregationData.setAdmittedStudentsMinority(Integer.parseInt(aggregateData[13].toString())-minorityMigrated);
			
			aggregationData.setLeftoutStudentsGen(Integer.parseInt(aggregateData[14].toString())+genMigrated);
			aggregationData.setLeftoutStudentsOBC(Integer.parseInt(aggregateData[15].toString())+obcMigrated);
			aggregationData.setLeftoutStudentsSC(Integer.parseInt(aggregateData[16].toString())+scGenderMigrated);
			aggregationData.setLeftoutStudentsST(Integer.parseInt(aggregateData[17].toString())+stGenderMigrated);
			aggregationData.setLeftoutStudentsMinority(Integer.parseInt(aggregateData[18].toString())+minorityMigrated);
			
			aggregationData.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
			newAggregateData.add(aggregationData);
			
		}
		
		if(!migratedDataMap.isEmpty())
		{
		
			for (Map.Entry<String, List<Integer>> entry : migratedDataMap.entrySet())
			{
				AggregationData aggregationData=new AggregationData();
				
				if(aggregateDataMap.containsKey(Integer.parseInt(entry.getKey())))
				{
				aggregationData=aggregateDataMap.get(Integer.parseInt(entry.getKey()));
				}
				else
				{
					Area area = new Area();
					area.setAreaId(Integer.parseInt(entry.getKey()));
					aggregationData.setAreaId(area);
					aggregationData.setAcademicYearId(academicYear);
					
					
					aggregationData.setAdmittedStudents(0);
					//aggregationData.setLefoutStudents(Integer.parseInt(aggregateData[2].toString())+totalMigrated);
					aggregationData.setAdmittedBoys(0);
					aggregationData.setAdmittedGirls(0);
					aggregationData.setAdmittedThirdGender(0);
					aggregationData.setAdmittedStudentsGen(0);
					aggregationData.setAdmittedStudentsOBC(0);
					aggregationData.setAdmittedStudentsSC(0);
					aggregationData.setAdmittedStudentsST(0);
					aggregationData.setAdmittedStudentsMinority(0);
				}
				aggregationData.setLefoutStudents(entry.getValue().get(0));
				aggregationData.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
				aggregationData.setLeftoutBoys(entry.getValue().get(1));
				aggregationData.setLeftoutGirls(entry.getValue().get(2));
				aggregationData.setLeftoutThirdGender(entry.getValue().get(3));
				aggregationData.setLeftoutStudentsGen(entry.getValue().get(4));
				aggregationData.setLeftoutStudentsOBC(entry.getValue().get(5));
				aggregationData.setLeftoutStudentsSC(entry.getValue().get(6));
				aggregationData.setLeftoutStudentsST(entry.getValue().get(7));
				aggregationData.setLeftoutStudentsMinority(entry.getValue().get(8));
				newAggregateData.add(aggregationData);
			}
				
		}
		
		aggregationDataRepository.save(newAggregateData);
		aggregationDataRepository.updateLastUpdatedDate(new Date(new java.util.Date().getTime()));

		}
		return true;
		}
		
		
	}

	@Override
	@Transactional
	public boolean aggregateDataForSchool() {
	
		LocalDate localdate = new LocalDate();
		int year = localdate.getYear();
		int monthOfYear = localdate.getMonthOfYear();

		int acedemicYear = year;
		if (monthOfYear < 4) {
			acedemicYear = year - 1;
		}
		
		AcademicYear academicYear=acedemicYearRepository.findByStartYear(String.valueOf(acedemicYear));
		int enrollTypeMigrated=Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.MIGRATION, null,null));
		
		
		//returned is areaId->blockId,integer->submitted,integer->not submitted group by school
				List<Object[]> schoolLevelAggregatedData=studentsDetailsRepository.getAggregateStudentsDataAtSchoolLevel(String.valueOf(acedemicYear));
			
				
				
				//returned is areaId->blockId,integer->migrated group by school
				List<Object[]> schoolAggregatedDataForMigration=studentSchoolMappingRepository.getAggregateStudentsDataAtSchoolLevelMigrated(String.valueOf(acedemicYear),enrollTypeMigrated);

				
				Map<String,List<Integer>>  migratedDataMap=new HashMap<String, List<Integer>>();
				
				for(Object [] migratedData:schoolAggregatedDataForMigration)
				{
					List<Integer> migratedDataList=new ArrayList<Integer>();
					migratedDataList.add(Integer.parseInt(migratedData[1].toString()));
					migratedDataList.add(Integer.parseInt(migratedData[2].toString()));
					migratedDataList.add(Integer.parseInt(migratedData[3].toString()));
					migratedDataList.add(Integer.parseInt(migratedData[4].toString()));
					migratedDataList.add(Integer.parseInt(migratedData[5].toString()));
					migratedDataList.add(Integer.parseInt(migratedData[6].toString()));
					migratedDataList.add(Integer.parseInt(migratedData[7].toString()));
					migratedDataList.add(Integer.parseInt(migratedData[8].toString()));
					migratedDataList.add(Integer.parseInt(migratedData[9].toString()));
					migratedDataMap.put(migratedData[0].toString(), migratedDataList);
				}
				
				
		
		List<SchoolAggregatedData> schoolAggregatedDatas=schoolAggregatedDataRepository.findByAcademicYearIdStartYear(String.valueOf(acedemicYear));
		
		Map<Integer,SchoolAggregatedData> schoolAggregatedDataMap=new HashMap<Integer, SchoolAggregatedData>();
		
		for(SchoolAggregatedData schoolAggregatedData:schoolAggregatedDatas)
		{
			schoolAggregatedDataMap.put(schoolAggregatedData.getSchool().getSchoolId(),schoolAggregatedData);
		}
		
		List<SchoolAggregatedData> newAggregateDatas=new ArrayList<SchoolAggregatedData>();
		
		for(Object[] schoolCurrentAggregatedData:schoolLevelAggregatedData)
		{
			SchoolAggregatedData newAggregateData=new SchoolAggregatedData();
			if(schoolAggregatedDataMap.containsKey(Integer.parseInt(schoolCurrentAggregatedData[0].toString())))
			{
				newAggregateData=schoolAggregatedDataMap.get(Integer.parseInt(schoolCurrentAggregatedData[0].toString()));
			}
				else
			{
				SchoolDetails schoolDetails=new SchoolDetails();
				
				schoolDetails.setSchoolId(Integer.parseInt(schoolCurrentAggregatedData[0].toString()));
				newAggregateData.setSchool(schoolDetails);
				newAggregateData.setAcademicYearId(academicYear);
			}

			
			int totalMigrated=0,boyMigrated=0,girlMigrated=0,thirdGenderMigrated=0,
					genMigrated=0,obcMigrated=0,scGenderMigrated=0,stGenderMigrated=0,minorityMigrated=0;
			
			if(migratedDataMap.containsKey(schoolCurrentAggregatedData[0].toString()))
			{
				totalMigrated=migratedDataMap.get(schoolCurrentAggregatedData[0].toString()).get(0);
				boyMigrated=migratedDataMap.get(schoolCurrentAggregatedData[0].toString()).get(1);
				girlMigrated=migratedDataMap.get(schoolCurrentAggregatedData[0].toString()).get(2);
				thirdGenderMigrated=migratedDataMap.get(schoolCurrentAggregatedData[0].toString()).get(3);
				genMigrated=migratedDataMap.get(schoolCurrentAggregatedData[0].toString()).get(4);
				obcMigrated=migratedDataMap.get(schoolCurrentAggregatedData[0].toString()).get(5);
				scGenderMigrated=migratedDataMap.get(schoolCurrentAggregatedData[0].toString()).get(6);
				stGenderMigrated=migratedDataMap.get(schoolCurrentAggregatedData[0].toString()).get(7);
				minorityMigrated=migratedDataMap.get(schoolCurrentAggregatedData[0].toString()).get(8);
				migratedDataMap.remove(schoolCurrentAggregatedData[0].toString());
			}
			newAggregateData.setAdmittedStudents(Integer.parseInt(schoolCurrentAggregatedData[1].toString())-totalMigrated);
			newAggregateData.setLefoutStudents(Integer.parseInt(schoolCurrentAggregatedData[2].toString())+totalMigrated);
			newAggregateData.setAdmittedBoys(Integer.parseInt(schoolCurrentAggregatedData[3].toString())-boyMigrated);
			newAggregateData.setAdmittedGirls(Integer.parseInt(schoolCurrentAggregatedData[4].toString())-girlMigrated);
			newAggregateData.setAdmittedThirdGender(Integer.parseInt(schoolCurrentAggregatedData[5].toString())-thirdGenderMigrated);
			newAggregateData.setLeftoutBoys(Integer.parseInt(schoolCurrentAggregatedData[6].toString())+boyMigrated);
			newAggregateData.setLeftoutGirls(Integer.parseInt(schoolCurrentAggregatedData[7].toString())+girlMigrated);
			newAggregateData.setLeftoutThirdGender(Integer.parseInt(schoolCurrentAggregatedData[8].toString())+thirdGenderMigrated);
			newAggregateData.setAdmittedStudentsGen(Integer.parseInt(schoolCurrentAggregatedData[9].toString())-genMigrated);
			newAggregateData.setAdmittedStudentsOBC(Integer.parseInt(schoolCurrentAggregatedData[10].toString())-obcMigrated);
			newAggregateData.setAdmittedStudentsSC(Integer.parseInt(schoolCurrentAggregatedData[11].toString())-scGenderMigrated);
			newAggregateData.setAdmittedStudentsST(Integer.parseInt(schoolCurrentAggregatedData[12].toString())-stGenderMigrated);
			newAggregateData.setAdmittedStudentsMinority(Integer.parseInt(schoolCurrentAggregatedData[13].toString())-minorityMigrated);
			
			newAggregateData.setLeftoutStudentsGen(Integer.parseInt(schoolCurrentAggregatedData[14].toString())+genMigrated);
			newAggregateData.setLeftoutStudentsOBC(Integer.parseInt(schoolCurrentAggregatedData[15].toString())+obcMigrated);
			newAggregateData.setLeftoutStudentsSC(Integer.parseInt(schoolCurrentAggregatedData[16].toString())+scGenderMigrated);
			newAggregateData.setLeftoutStudentsST(Integer.parseInt(schoolCurrentAggregatedData[17].toString())+stGenderMigrated);
			newAggregateData.setLeftoutStudentsMinority(Integer.parseInt(schoolCurrentAggregatedData[18].toString())+minorityMigrated);
			
			newAggregateData.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
			schoolAggregatedDatas.add(newAggregateData);
			
			newAggregateDatas.add(newAggregateData);
		}
		//throw new RuntimeException("Custom Exception");
		
		if(!migratedDataMap.isEmpty())
		{
		
			for (Map.Entry<String, List<Integer>> entry : migratedDataMap.entrySet())
			{
				SchoolAggregatedData aggregationData=new SchoolAggregatedData();
				
				if(schoolAggregatedDataMap.containsKey(Integer.parseInt(entry.getKey())))
				{
				aggregationData=schoolAggregatedDataMap.get(Integer.parseInt(entry.getKey()));
				}
				else
				{
					SchoolDetails area = new SchoolDetails();
					area.setSchoolId(Integer.parseInt(entry.getKey()));
					aggregationData.setSchool(area);
					aggregationData.setAcademicYearId(academicYear);
					
					
					aggregationData.setAdmittedStudents(0);
					aggregationData.setAdmittedBoys(0);
					aggregationData.setAdmittedGirls(0);
					aggregationData.setAdmittedThirdGender(0);
					aggregationData.setAdmittedStudentsGen(0);
					aggregationData.setAdmittedStudentsOBC(0);
					aggregationData.setAdmittedStudentsSC(0);
					aggregationData.setAdmittedStudentsST(0);
					aggregationData.setAdmittedStudentsMinority(0);
				}
				aggregationData.setLefoutStudents(entry.getValue().get(0));
				aggregationData.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
				aggregationData.setLeftoutBoys(entry.getValue().get(1));
				aggregationData.setLeftoutGirls(entry.getValue().get(2));
				aggregationData.setLeftoutThirdGender(entry.getValue().get(3));
				aggregationData.setLeftoutStudentsGen(entry.getValue().get(4));
				aggregationData.setLeftoutStudentsOBC(entry.getValue().get(5));
				aggregationData.setLeftoutStudentsSC(entry.getValue().get(6));
				aggregationData.setLeftoutStudentsST(entry.getValue().get(7));
				aggregationData.setLeftoutStudentsMinority(entry.getValue().get(8));
				schoolAggregatedDatas.add(aggregationData);
			}
				
		}
		schoolAggregatedDataRepository.save(schoolAggregatedDatas);
		schoolAggregatedDataRepository.updateLastUpdatedDate(new Date(new java.util.Date().getTime()),new Timestamp(new java.util.Date().getTime()));
		return true;
	}

	@Override
	public boolean sendMail(MailModel mail) throws Exception {
		Properties props = new Properties();
		props.put(
				applicationMessageSource.getMessage(Constants.Web.SMTP_HOST_KEY, null, null),
				applicationMessageSource.getMessage(Constants.Web.SMTP_HOST, null, null));
		props.put(applicationMessageSource.getMessage(Constants.Web.SOCKETFACTORY_PORT_KEY,
				null, null), applicationMessageSource.getMessage(
				Constants.Web.SOCKETFACTORY_PORT, null, null));
		props.put(applicationMessageSource.getMessage(Constants.Web.SOCKETFACTORY_CLASS_KEY,
				null, null), applicationMessageSource.getMessage(
				Constants.Web.SOCKETFACTORY_CLASS, null, null));
		props.put(
				applicationMessageSource.getMessage(Constants.Web.SMTP_AUTH_KEY, null, null),
				applicationMessageSource.getMessage(Constants.Web.SMTP_AUTH, null, null));
		props.put(
				applicationMessageSource.getMessage(Constants.Web.SMTP_PORT_KEY, null, null),
				applicationMessageSource.getMessage(Constants.Web.SMTP_PORT, null, null));

		javax.mail.Session session = javax.mail.Session.getDefaultInstance(
				props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(applicationMessageSource
								.getMessage(Constants.Web.AUTHENTICATION_USERID,
										null, null), applicationMessageSource.getMessage(
								Constants.Web.AUTHENTICATION_PASSWORD, null, null));
					}
				});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(applicationMessageSource.getMessage(
				Constants.Web.AUTHENTICATION_USERID, null, null)));

		// adding "to"
		List<String> toList = mail.getToEmailIds();
		String toAddress = "";

		for (String to : toList) {
			toAddress += to;
			if (toList.size() > 1) {
				toAddress += ",";
			}
		}

		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toAddress));

		// adding "cc"
		List<String> ccList = mail.getCcEmailIds();
		if (null != ccList && ccList.size() > 0) {
			String ccAddress = "";

			for (String cc : ccList) {
				ccAddress += cc;
				if (ccList.size() > 1) {
					ccAddress += ",";
				}
			}

			message.setRecipients(Message.RecipientType.CC,
					InternetAddress.parse(ccAddress));
		}

		

		message.setSubject(mail.getSubject());

		//set mail message
		
		String mailMessageBody = null != mail.getMessage() ? mail.getMessage() : "";

		String msg = (String) ("<html>"
				+ "<body >Dear " + mail.getToUserName() + ",<br><br>"
		// + "NOTIFICATION DETAILS:" + "\n" + "Message : " + mail.getMsg()
				+ mailMessageBody + "<br><br>" + "<b>Regards," + "<br>" + mail.getFromUserName()+"</b>"
						+ "	</body>"
						+ "</html>");
		
		// for attaching files and send it through email	
		if(mail.getAttachments()==null)
		{
			message.setContent(msg,"text/html");
		}
	/*	
		else if(mail.getAttachments().size() > 0) {
			
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(msg,"text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			
			
			Iterator<Entry<String, String>> it = mail.getAttachments().entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<String, String> pair = (Map.Entry<String, String>)it.next();
		        
		        String path = (String)pair.getValue();
				String name = (String)pair.getKey();
				
				messageBodyPart = new MimeBodyPart();
				String filename = path +  name;
				DataSource source = new FileDataSource(filename);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(name);
				multipart.addBodyPart(messageBodyPart);
		        
		    }
			
			message.setContent(multipart);
			
		} */
		
		Transport.send(message);
		return true;
	}

}
