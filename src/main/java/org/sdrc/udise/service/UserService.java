package org.sdrc.udise.service;

import java.sql.Timestamp;
import java.util.List;

import org.sdrc.udise.domain.UserLoginMeta;
import org.sdrc.udise.model.UserModel;

/**
 * 
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
public interface UserService {

	UserModel getUserData(String username, String password);

	long saveUserLoginMeta(String ipAddress, Integer userId, String userAgent, String sessionID);

	void updateLoggedOutStatus(long userLoginMeta, Timestamp loggedOutDateTime);

	List<UserLoginMeta> findActiveUserLoginMeta(Integer userId);
}
