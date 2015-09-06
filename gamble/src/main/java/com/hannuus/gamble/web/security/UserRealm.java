package com.hannuus.gamble.web.security;

import java.util.Arrays;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hannuus.gamble.model.UserToken;
import com.hannuus.gamble.web.service.AuthService;
import com.hannuus.gamble.web.service.CustomService;

/**
 * @author cuesky
 * @date 2015年8月28日 下午12:20:27
 */
@Component
public class UserRealm extends AuthorizingRealm {

	private Logger logger = Logger.getLogger(getClass());

	// @Autowired
	// UserService userService;
	// @Autowired
	// LoginService loginService;

	@Autowired
	AuthService authService;
	@Autowired
	CustomService customService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		logger.debug("doGetAuthorizationInfo in...");
		String userName = (String) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roles = authService.findStringRoles(userName);
		info.setRoles(roles);
		logger.debug(userName + " roles" + Arrays.toString(roles.toArray()));

		Set<String> permissions = authService.findStringPermissions(userName);
		info.setStringPermissions(permissions);
		logger.debug(userName + " perms"
				+ Arrays.toString(permissions.toArray()));

		logger.debug("doGetAuthorizationInfo out...");
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		logger.debug("doGetAuthenticationInfo in...");
		UsernamePasswordToken t = (UsernamePasswordToken) token;
		String username = t.getUsername();
		String password = new String(t.getPassword(), 0, t.getPassword().length);
		// AccessToken at = loginService.userLogin(username, password);
		UserToken userToken = customService.loginByRealm(username, password);
		if (userToken == null) {
			throw new UnknownAccountException();
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,
				password, getName());
		logger.debug("doGetAuthenticationInfo out...");
		return info;
	}

}
