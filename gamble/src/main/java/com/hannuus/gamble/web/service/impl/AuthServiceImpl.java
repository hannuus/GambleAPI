package com.hannuus.gamble.web.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hannuus.gamble.dao.PermissionMapper;
import com.hannuus.gamble.dao.ResourceMapper;
import com.hannuus.gamble.dao.RoleResourcePermissionMapper;
import com.hannuus.gamble.dao.UserRoleMapper;
import com.hannuus.gamble.model.Permission;
import com.hannuus.gamble.model.Resource;
import com.hannuus.gamble.model.RoleResourcePermission;
import com.hannuus.gamble.utils.GambleUtils;
import com.hannuus.gamble.web.service.AuthService;

/**
 * @author cuesky
 * @date 2015年8月28日 下午12:21:04
 */
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserRoleMapper userRoleMapper;
	@Autowired
	RoleResourcePermissionMapper roleResourcePermissionMapper;
	@Autowired
	ResourceMapper resourceMapper;
	@Autowired
	PermissionMapper permissionMapper;

	@Override
	public Set<String> findStringRoles(String userName) {
		// System.out.println(userRoleMapper.findRoleValuesByUserName(userName)
		// .getClass() + "......");
		String[] roleValues = userRoleMapper.findRoleValuesByUserName(userName);
		Set<String> roles = new HashSet<String>();
		for (String roleValue : roleValues) {
			String role = GambleUtils.Security.findRoleValue(roleValue, 0);
			if (!StringUtils.isEmpty(role)) {
				roles.add(role);
			}
		}
		return roles;
	}

	@Override
	public Set<String> findStringPermissions(String userName) {
		List<RoleResourcePermission> resourcePermissions = roleResourcePermissionMapper
				.findResourcePermissionsByUserName(userName);
		Set<String> stringPermissions = new HashSet<String>();
		for (RoleResourcePermission roleResourcePermission : resourcePermissions) {
			String resourceValue = getResourceValue(roleResourcePermission);
			String permissionValues = getPermissionValues(roleResourcePermission);
			StringBuilder buf = new StringBuilder();
			buf.append(resourceValue);
			buf.append(":");
			buf.append(permissionValues);
			stringPermissions.add(buf.toString());
		}
		return stringPermissions;
	}

	private String getResourceValue(
			RoleResourcePermission roleResourcePermission) {
		Long resourceId = roleResourcePermission.getResourceId();
		Resource resource = resourceMapper.selectByPrimaryKey(resourceId);
		String resourceValue = resource.getResourceValue();
		return resourceValue;
	}

	private String getPermissionValues(
			RoleResourcePermission roleResourcePermission) {
		StringBuilder buf = new StringBuilder();
		String permissionIds = roleResourcePermission.getPermissionIds();
		String[] arr = permissionIds.split(",");
		for (String permissionId : arr) {
			Permission permission = permissionMapper.selectByPrimaryKey(Long
					.valueOf(permissionId));
			String permissionValue = permission.getPermissionValue();
			buf.append(permissionValue);
			buf.append(",");
		}
		return buf.deleteCharAt(buf.length() - 1).toString();
	}

}
