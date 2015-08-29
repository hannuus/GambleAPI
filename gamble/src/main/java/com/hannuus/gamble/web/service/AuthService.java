package com.hannuus.gamble.web.service;

import java.util.Set;

/**
 * @author cuesky
 * @date 2015年8月28日 下午12:07:20
 */
public interface AuthService {

	/**
	 * 根据userName查询所有role
	 * 
	 * @param userName
	 * @return
	 */
	Set<String> findStringRoles(String userName);

	/**
	 * 根据userName查询所有permission
	 * 
	 * @param userName
	 * @return
	 */
	Set<String> findStringPermissions(String userName);

}
