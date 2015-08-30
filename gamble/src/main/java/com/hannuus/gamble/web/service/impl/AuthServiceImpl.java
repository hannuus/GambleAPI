package com.hannuus.gamble.web.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import com.hannuus.gamble.model.Role;
import com.hannuus.gamble.model.RoleResourcePermission;
import com.hannuus.gamble.model.User;
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

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchDeleteUsers(Long[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUsersLike(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUserExists(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addRole(Role role) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRole(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchDeleteRoles(Long[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRole(Role role) {
		// TODO Auto-generated method stub

	}

	@Override
	public Role findRole(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findRolesLike(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRoleExists(String name, String roleValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addResource(Resource resource) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteResource(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchDeleteResources(Long[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateResource(Resource resource) {
		// TODO Auto-generated method stub

	}

	@Override
	public Resource findResource(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> findResources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> findResourcesLike(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isResourceExists(String name, String resourceValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPermission(Permission permission) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePermission(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchDeletePermissions(Long[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePermission(Permission permission) {
		// TODO Auto-generated method stub

	}

	@Override
	public Permission findPermission(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permission> findPermissions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permission> findPermissionsLike(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPermissionExists(String name, String permissionValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Role> findRolesByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignRoles(Long userId, Long[] roleIds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchAssignRoles(Long[] userIds, Long[] roleIds) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Resource> findRBACResources(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permission> findRBACPermissions(Long roleId, Long resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignResourceAndPermissions(Long roleId,
			Map<Long, Long[]> permissionMap) {
		// TODO Auto-generated method stub

	}

}
