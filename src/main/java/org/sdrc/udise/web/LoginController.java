/**
 * 
 */
package org.sdrc.udise.web;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sdrc.udise.model.UserModel;
import org.sdrc.udise.service.UserService;
import org.sdrc.udise.util.Constants;
import org.sdrc.udise.util.DuplicateLoginUserException;
import org.sdrc.udise.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */

@Controller
public class LoginController implements AuthenticationProvider {

	@Autowired
	private ResourceBundleMessageSource errorMessageSource;
	
	@Autowired
	private ResourceBundleMessageSource applicationMessageSource; 

	private final StateManager stateManager;

	@Autowired
	private MessageDigestPasswordEncoder passwordEncoder;

	@Autowired
	public LoginController(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	@Autowired
	UserService userService;
	
	private static Instrumentation instrumentation;
	
	
	@RequestMapping(value = "/webLogin", method = RequestMethod.POST)
	public String authorize(HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			@RequestParam("username") String username,
			@RequestParam("password") String password, Model model)
			throws IOException {
		List<String> errMessgs = new ArrayList<String>();
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					username, password);
			token.setDetails(new WebAuthenticationDetails(request));
			Authentication authentication = this.authenticate(token);
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			SecurityContextHolder.getContext().setAuthentication(null);
			errMessgs.add(errorMessageSource.getMessage(
					Constants.Web.INVALID_CREDENTIALS, null, null));
			redirectAttributes.addFlashAttribute("formError", errMessgs);
			redirectAttributes.addFlashAttribute("className",
					errorMessageSource.getMessage("bootstrap.alert.danger",
							null, null));
			return "redirect:/login";
		} catch (DuplicateLoginUserException e) {
			SecurityContextHolder.getContext().setAuthentication(null);
			errMessgs
					.add(errorMessageSource.getMessage(
							Constants.Web.MAX_USER_LIMIT_EXCEED, null, null));
			redirectAttributes.addFlashAttribute("formError", errMessgs);
			redirectAttributes.addFlashAttribute("className",
					errorMessageSource.getMessage("bootstrap.alert.danger",
							null, null));
			return "redirect:/login";
		}
		
	
		model.addAttribute("userDetail", ((UserModel) stateManager
				.getValue(Constants.Web.USER_PRINCIPAL)));
		if(((UserModel) stateManager
				.getValue(Constants.Web.USER_PRINCIPAL)).getRoleId()==5)// will bring from properties
			return "redirect:/manageStudents";
		else
		return "redirect:/dashboard";
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		String encodedPassword = passwordEncoder.encodePassword(
				authentication.getName(),
				(String) authentication.getCredentials());
		UserModel UserModel =userService.getUserData(authentication.getName(), encodedPassword);
		if (UserModel == null
				|| !UserModel.getPassword().equals(encodedPassword))
			throw new BadCredentialsException("Invalid User!");

		// if user is already logged-in in any other system, then restrict
			if(UserModel.getRoleId()==5)
			{
		if( userService.findActiveUserLoginMeta(UserModel.getUserId()).size()>=Integer.parseInt(applicationMessageSource.getMessage(Constants.Web.MAX_USER, null,null)))
		 throw new DuplicateLoginUserException("User is already logged in!");
			}
		stateManager.setValue(Constants.Web.USER_PRINCIPAL, UserModel);

		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		String sessionID=request.getHeader(Constants.Web.COOKIES);
		String ipAddress = getIpAddr(request);
		String userAgent = request.getHeader("User-Agent");
		long loginMetaId = 0;
		loginMetaId=userService.saveUserLoginMeta(ipAddress,
								 UserModel.getUserId(), userAgent,sessionID);
	//	stateManager.setValue(Constants.Web.LOGIN_META_ID, loginMetaId);
		UserModel = (UserModel) stateManager
				.getValue(Constants.Web.USER_PRINCIPAL);
		UserModel.setUserLoginMetaId(loginMetaId);
		
		//System.out.println(ObjectSizeFetcher.getObjectSize(UserModel));
		stateManager.setValue(Constants.Web.USER_PRINCIPAL, UserModel);

		return new UsernamePasswordAuthenticationToken(
				authentication.getName(),
				(String) authentication.getCredentials(), null);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}

	@RequestMapping(value = "/webLogout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse resp,
			RedirectAttributes redirectAttributes) throws IOException,
			ServletException {

		HttpSession session = request.getSession(false);
		if (session != null) {
		//	long userLoginMetaId = (long) stateManager
					//.getValue(Constants.Web.LOGIN_META_ID);
			int userId=((UserModel) stateManager
					.getValue(Constants.Web.USER_PRINCIPAL)).getUserId();
			 userService.updateLoggedOutStatus(((UserModel) stateManager
						.getValue(Constants.Web.USER_PRINCIPAL)).getUserLoginMetaId(), new
			 Timestamp(new Date().getTime()));
			stateManager.setValue(Constants.Web.USER_PRINCIPAL, null);
			request.getSession().setAttribute(Constants.Web.USER_PRINCIPAL,
					null);
			request.getSession().invalidate();
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			attr.getRequest().getSession(true)
					.removeAttribute(Constants.Web.USER_PRINCIPAL);
			attr.getRequest().getSession(true).invalidate();

			request.logout();

			List<String> errMessgs = new ArrayList<>();

			errMessgs.add(errorMessageSource.getMessage(
					Constants.Web.SUCCESS_LOGGED_OUT, null, null));
			redirectAttributes.addFlashAttribute("formError", errMessgs);
			redirectAttributes.addFlashAttribute("className",
					errorMessageSource.getMessage("bootstrap.alert.success",
							null, null));
			return "redirect:/";
		} else {
			request.getSession().invalidate();
			return "redirect:/";
		}
	}

	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
/*	public String logoutOtherUser(@RequestParm("userId") userID)*/

}


 class ObjectSizeFetcher {
private static Instrumentation instrumentation;

public static void premain(String args, Instrumentation inst) {
instrumentation = inst;
}

public static long getObjectSize(Object o) {
return instrumentation.getObjectSize(o);
}
} 

