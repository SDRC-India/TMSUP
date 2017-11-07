/**
 * 
 */
package org.sdrc.udise.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sdrc.udise.domain.Area;
import org.sdrc.udise.domain.MSTUser;
import org.sdrc.udise.domain.UserLoginMeta;
import org.sdrc.udise.model.UserModel;
import org.sdrc.udise.repository.AreaRepository;
import org.sdrc.udise.repository.MstUserRepository;
import org.sdrc.udise.repository.UserLoginMetaRepository;
import org.sdrc.udise.util.Constants;
import org.sdrc.udise.util.DomainToModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@Service
public class UserServiceImpl implements UserService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sdrc.udise.service.UserService#getUserData()
	 */
	@Autowired
	MstUserRepository mSTUserRepository;

	@Autowired
	AreaRepository areaRepository;
	
	@Autowired
	UserLoginMetaRepository userLoginMetaRepository;
	
	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;

	@Override
	@Transactional
	public UserModel getUserData(String username, String password) {
		UserModel userModel = null;

		Map<Integer, Area> areaIdDomainMap = new HashMap<>();
		List<Area> areas = areaRepository.findAll();

		for (Area area : areas) {
			areaIdDomainMap.put(area.getAreaId(), area);
		}

		MSTUser user = mSTUserRepository.findByUserNameAndPassword(username,
				password);
		if (user != null) {
			userModel = new UserModel();
			userModel.setUserId(user.getUserId());
			userModel.setName(user.getName());
			userModel.setUsername(user.getUserName());
			userModel.setPassword(user.getPassword());
			userModel.setEmailId(user.getEmail());
			userModel.setLive(user.getIsLive());
			userModel.setAreaId(user.getUserAreaMappings().get(0).getArea()
					.getAreaId());
			userModel.setRoleName(user.getUserAreaMappings().get(0)
					.getUserRoleFeaturePermissionMappings().get(0)
					.getRoleFeaturePermissionScheme().getRole().getRoleName());

			userModel.setRoleId(user.getUserAreaMappings().get(0)
					.getUserRoleFeaturePermissionMappings().get(0)
					.getRoleFeaturePermissionScheme().getRole().getRoleId());
			userModel.setUserAreaModels(DomainToModelConverter
					.toUserAreaMappingModel(user.getUserAreaMappings()));
			userModel.setAreaLevel(user.getUserAreaMappings().get(0).getArea()
					.getLevel());
			userModel.setParentAreaId(user.getUserAreaMappings().get(0).getArea()
					.getParentAreaId());
			userModel.setAreaName(user.getUserAreaMappings().get(0).getArea()
					.getAreaName());
			
			if(userModel.getParentAreaId()!=-1)
			{
			userModel.setParentAreaName(areaIdDomainMap.get(userModel.getParentAreaId()).getAreaName());
			}
			
			if(userModel.getRoleId()!=5)
			{
				if(userModel.getAreaLevel()==Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.STATE_LEVEL,null,null)))
				{
				userModel.setStateId(userModel.getAreaId());
				userModel.setStateName(userModel.getAreaName());
				}
				else if(userModel.getAreaLevel()==Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.DIVISIONAL_LEVEL,null,null)))
				{
					userModel.setStateId(userModel.getParentAreaId());
					userModel.setStateName(areaIdDomainMap.get(userModel.getParentAreaId()).getAreaName());
					userModel.setDivisionId(userModel.getAreaId());
					userModel.setDivisionName(userModel.getAreaName());
				}
				else if(userModel.getAreaLevel()==Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.DISTRICT_LEVEL,null,null)))
				{
					userModel.setDistrictId(userModel.getAreaId());
					userModel.setDistrictName(userModel.getAreaName());
					
					userModel.setDivisionId(userModel.getParentAreaId());
					userModel.setDivisionName(areaIdDomainMap.get(userModel.getParentAreaId()).getAreaName());
					
					userModel.setStateId(areaIdDomainMap.get(areaIdDomainMap.get(userModel.getDivisionId()).getParentAreaId()).getAreaId());
					userModel.setStateName(areaIdDomainMap.get(areaIdDomainMap.get(userModel.getDivisionId()).getParentAreaId()).getAreaName());
				
				
			
				}
				else
				{	
				userModel.setBlockId(userModel.getAreaId());
				userModel.setBlockName(userModel.getAreaName());
				

				userModel.setDistrictId(userModel.getParentAreaId());
				userModel.setDistrictName(areaIdDomainMap.get(userModel.getParentAreaId()).getAreaName());
				
				userModel.setDivisionId(areaIdDomainMap.get(areaIdDomainMap.get(userModel.getDistrictId()).getParentAreaId()).getAreaId());
				userModel.setDivisionName(areaIdDomainMap.get(areaIdDomainMap.get(userModel.getDistrictId()).getParentAreaId()).getAreaName());
				
				userModel.setStateId(areaIdDomainMap.get(areaIdDomainMap.get(userModel.getDivisionId()).getParentAreaId()).getAreaId());
				userModel.setStateName(areaIdDomainMap.get(areaIdDomainMap.get(userModel.getDivisionId()).getParentAreaId()).getAreaName());
			
			
		
			
				}
			}
		}
		return userModel;
	}
	
	@Override
	@Transactional
	public long saveUserLoginMeta(String ipAddress, Integer userId, String userAgent,String sessionID) {
		MSTUser mstUser=mSTUserRepository.findByUserId(userId);
		UserLoginMeta userLoginMeta = new UserLoginMeta();
		userLoginMeta.setUserIpAddress(ipAddress);
		userLoginMeta.setMstUser(mstUser);
		userLoginMeta.setLoggedInDateTime(new Timestamp(new Date().getTime()));
		userLoginMeta.setUserAgent(userAgent);
		userLoginMeta.setLoggedIn(true);
		userLoginMeta.setSeesionID(sessionID.split("=")[1].trim());
		return userLoginMetaRepository.save(userLoginMeta).getUserLogInMetaId();
	}

	// update login meta while signing out
	@Override
	@Transactional
	public void updateLoggedOutStatus(long userLoginMetaId, Timestamp loggedOutDateTime) {

		// while server start up
		if (userLoginMetaId == -1) {
			userLoginMetaRepository.updateStatusForAll(loggedOutDateTime);
		} else
			userLoginMetaRepository.updateStatus(loggedOutDateTime, userLoginMetaId);
	}

	@Override
	public List<UserLoginMeta> findActiveUserLoginMeta(Integer userId) {
		return userLoginMetaRepository.findByMstUserUserIdAndIsLoggedInTrue(userId);
	}

}
