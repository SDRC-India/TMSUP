package org.sdrc.udise.core;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClientError;
import org.sdrc.udise.model.FeaturePermissionMappingModel;
import org.sdrc.udise.model.UserAreaModel;
import org.sdrc.udise.model.UserModel;
import org.sdrc.udise.util.Constants;
import org.sdrc.udise.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
@Component
public class AuthorizeInterceptor extends HandlerInterceptorAdapter {
	private final StateManager stateManager;
	private final ResourceBundleMessageSource errorMessageSource;

	@Autowired
	public AuthorizeInterceptor(StateManager stateManager,
			ResourceBundleMessageSource errorMessageSource) {
		this.stateManager = stateManager;
		this.errorMessageSource = errorMessageSource;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		
		LocalTime starttime = LocalTime.parse("00:00");
		LocalTime endtime = LocalTime.parse("00:10");
		
		LocalTime currentTime = LocalTime.now();
		
		if(!(currentTime.isBefore(starttime)||currentTime.isAfter(endtime)))
		{
			throw new HttpClientError(errorMessageSource.getMessage(
					Constants.Web.MAINTENANCE_SITE, null, null));
		}
		
		if (!(handler instanceof HandlerMethod))
			return true;

		Authorize authorize = ((HandlerMethod) handler)
				.getMethodAnnotation(Authorize.class);

		if (authorize == null)
			return true;

		UserModel user = (UserModel) stateManager.getValue(Constants.Web.USER_PRINCIPAL);
		if (user == null)
			throw new AccessDeniedException(errorMessageSource.getMessage(
					Constants.Web.ACCESS_DENIED, null, null));
		
		List<String> feature = new ArrayList<String>();
		feature =	Arrays.asList(authorize.feature().split(","));
		String permission = authorize.permission();
		
		List<UserAreaModel> userAreaModels = user != null ? user.getUserAreaModels() : null;
		
		if (null != userAreaModels) {
			for (UserAreaModel userAreaModel : userAreaModels) {
				if (userAreaModel.getUserRoleFeaturePermissionMappings() != null) {
					for (int i = 0; i < userAreaModel.getUserRoleFeaturePermissionMappings().size(); i++) {
						FeaturePermissionMappingModel fpMapping = userAreaModel.getUserRoleFeaturePermissionMappings()
								.get(i).getRoleFeaturePermissionSchemeModel().getFeaturePermissionMapping();
						if (feature.contains(fpMapping.getFeature().getFeatureName())
								&& permission.equals(fpMapping.getPermission().getPermissionName())) {
							return true;
						}
					}

				}
			}
		}
		
		throw new AccessDeniedException(errorMessageSource.getMessage(
				Constants.Web.ACCESS_DENIED, null, null));
	}

	
}
