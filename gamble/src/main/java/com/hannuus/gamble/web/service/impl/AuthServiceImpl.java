package com.hannuus.gamble.web.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hannuus.gamble.dao.PermissionMapper;
import com.hannuus.gamble.dao.ResourceMapper;
import com.hannuus.gamble.dao.RoleMapper;
import com.hannuus.gamble.dao.RoleResourcePermissionMapper;
import com.hannuus.gamble.dao.UserMapper;
import com.hannuus.gamble.dao.UserRoleMapper;
import com.hannuus.gamble.domain.page.PageDTO;
import com.hannuus.gamble.domain.page.PageParams;
import com.hannuus.gamble.model.Permission;
import com.hannuus.gamble.model.PermissionExample;
import com.hannuus.gamble.model.Resource;
import com.hannuus.gamble.model.ResourceExample;
import com.hannuus.gamble.model.Role;
import com.hannuus.gamble.model.RoleExample;
import com.hannuus.gamble.model.RoleResourcePermission;
import com.hannuus.gamble.model.RoleResourcePermissionExample;
import com.hannuus.gamble.model.User;
import com.hannuus.gamble.model.UserExample;
import com.hannuus.gamble.model.UserRole;
import com.hannuus.gamble.model.UserRoleExample;
import com.hannuus.gamble.utils.GambleUtils;
import com.hannuus.gamble.web.service.AuthService;

/**
 * @author cuesky
 * @date 2015年8月28日 下午12:21:04
 */
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	RoleMapper roleMapper;
	@Autowired
	UserRoleMapper userRoleMapper;
	@Autowired
	ResourceMapper resourceMapper;
	@Autowired
	PermissionMapper permissionMapper;
	@Autowired
	RoleResourcePermissionMapper roleResourcePermissionMapper;

	@Override
	public Set<String> findStringRoles(String userName) {
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
		userMapper.insertSelective(user);
	}

	@Override
	public void deleteUser(Long id) {
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDeleteUsers(Long[] ids) {
		for (Long id : ids) {
			deleteUser(id);
		}
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public User findUser(Long id) {
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public List<User> findUsers() {
		List<User> list = userMapper.selectByExample(new UserExample());
		return list;
	}

	@Override
	public PageDTO<User> findUsersPage(PageParams params) {
		int total = userMapper.countByExample(null);
		UserExample example = new UserExample();
		example.setLimitStart(params.getStart());
		example.setLimitEnd(params.getPageSize());
		List<User> list = userMapper.selectByExample(example);
		return new PageDTO<User>(total, list);
	}

	@Override
	public List<User> findUsersLike(String name) {
		UserExample example = new UserExample();
		example.createCriteria().andUserNameLike("%" + name + "%");
		List<User> list = userMapper.selectByExample(example);
		return list;
	}

	@Override
	public boolean isUserExists(String userName) {
		UserExample example = new UserExample();
		example.createCriteria().andUserNameEqualTo(userName);
		int count = userMapper.countByExample(example);
		return count >= 1;
	}

	@Override
	public void deleteUserRoleByUser(Long userId) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(userId);
		userRoleMapper.deleteByExample(example);
	}

	@Override
	public void addRole(Role role) {
		roleMapper.insertSelective(role);
	}

	@Override
	public void deleteRole(Long id) {
		roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDeleteRoles(Long[] ids) {
		for (Long id : ids) {
			deleteRole(id);
		}
	}

	@Override
	public void updateRole(Role role) {
		roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public Role findRole(Long id) {
		Role role = roleMapper.selectByPrimaryKey(id);
		return role;
	}

	@Override
	public List<Role> findRoles() {
		List<Role> list = roleMapper.selectByExample(new RoleExample());
		return list;
	}

	@Override
	public PageDTO<Role> findRolesPage(PageParams params) {
		int total = roleMapper.countByExample(null);
		RoleExample example = new RoleExample();
		example.setLimitStart(params.getStart());
		example.setLimitEnd(params.getPageSize());
		List<Role> list = roleMapper.selectByExample(example);
		return new PageDTO<Role>(total, list);
	}

	@Override
	public List<Role> findRolesLike(String name) {
		RoleExample example = new RoleExample();
		example.createCriteria().andNameLike("%" + name + "%");
		List<Role> list = roleMapper.selectByExample(example);
		return list;
	}

	@Override
	public boolean isRoleExists(String name, String roleValue) {
		RoleExample example = new RoleExample();
		example.or().andNameEqualTo(name);
		example.or().andRoleValueEqualTo(roleValue);
		int count = roleMapper.countByExample(example);
		return count >= 1;
	}

	@Override
	public void deleteUserRoleByRole(Long roleId) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		userRoleMapper.deleteByExample(example);
	}

	@Override
	public void addResource(Resource resource) {
		resourceMapper.insertSelective(resource);
	}

	@Override
	public void deleteResource(Long id) {
		resourceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDeleteResources(Long[] ids) {
		for (Long id : ids) {
			deleteResource(id);
		}
	}

	@Override
	public void updateResource(Resource resource) {
		resourceMapper.updateByPrimaryKeySelective(resource);
	}

	@Override
	public Resource findResource(Long id) {
		Resource resource = resourceMapper.selectByPrimaryKey(id);
		return resource;
	}

	@Override
	public List<Resource> findResources() {
		List<Resource> list = resourceMapper
				.selectByExample(new ResourceExample());
		return list;
	}

	@Override
	public PageDTO<Resource> findResourcesPage(PageParams params) {
		int total = resourceMapper.countByExample(null);
		ResourceExample example = new ResourceExample();
		example.setLimitEnd(params.getStart());
		example.setLimitEnd(params.getPageSize());
		List<Resource> list = resourceMapper.selectByExample(example);
		return new PageDTO<Resource>(total, list);
	}

	@Override
	public List<Resource> findResourcesLike(String name) {
		ResourceExample example = new ResourceExample();
		example.createCriteria().andNameLike("%" + name + "%");
		List<Resource> list = resourceMapper.selectByExample(example);
		return list;
	}

	@Override
	public boolean isResourceExists(String name, String resourceValue) {
		ResourceExample example = new ResourceExample();
		example.or().andNameEqualTo(name);
		example.or().andResourceValueEqualTo(resourceValue);
		int count = resourceMapper.countByExample(example);
		return count >= 1;
	}

	@Override
	public void addPermission(Permission permission) {
		permissionMapper.insertSelective(permission);
	}

	@Override
	public void deletePermission(Long id) {
		permissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDeletePermissions(Long[] ids) {
		for (Long id : ids) {
			deletePermission(id);
		}
	}

	@Override
	public void updatePermission(Permission permission) {
		permissionMapper.updateByPrimaryKeySelective(permission);
	}

	@Override
	public Permission findPermission(Long id) {
		Permission permission = permissionMapper.selectByPrimaryKey(id);
		return permission;
	}

	@Override
	public List<Permission> findPermissions() {
		List<Permission> list = permissionMapper
				.selectByExample(new PermissionExample());
		return list;
	}

	@Override
	public PageDTO<Permission> findPermissionsPage(PageParams params) {
		int total = permissionMapper.countByExample(null);
		PermissionExample example = new PermissionExample();
		example.setLimitStart(params.getStart());
		example.setLimitEnd(params.getPageSize());
		List<Permission> list = permissionMapper.selectByExample(example);
		return new PageDTO<Permission>(total, list);
	}

	@Override
	public List<Permission> findPermissionsLike(String name) {
		PermissionExample example = new PermissionExample();
		example.createCriteria().andNameLike(name);
		List<Permission> list = permissionMapper.selectByExample(example);
		return list;
	}

	@Override
	public boolean isPermissionExists(String name, String permissionValue) {
		PermissionExample example = new PermissionExample();
		example.or().andNameEqualTo(name);
		example.or().andPermissionValueEqualTo(permissionValue);
		int count = permissionMapper.countByExample(example);
		return count >= 1;
	}

	@Override
	public List<Role> findRolesByUserId(Long userId) {
		List<Role> list = userRoleMapper.findRolesByUserId(userId);
		return list;
	}

	@Override
	public void assignRoles(Long userId, Long[] roleIds) {
		// 根据userId删除该用户所有角色
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(userId);
		userRoleMapper.deleteByExample(example);

		// 角色重新分配
		for (Long roleId : roleIds) {
			UserRole userRole = new UserRole();
			userRole.setUserId(userId);
			userRole.setRoleId(roleId);
			userRoleMapper.insertSelective(userRole);
		}
	}

	@Override
	public void batchAssignRoles(Long[] userIds, Long[] roleIds) {
		for (Long userId : userIds) {
			assignRoles(userId, roleIds);
		}
	}

	@Override
	public List<Resource> findRBACResources(Long roleId) {
		List<Resource> list = roleResourcePermissionMapper
				.findRBACResources(roleId);
		return list;
	}

	@Override
	public List<Permission> findRBACPermissions(Long roleId, Long resourceId) {
		List<Permission> permissions = null;
		RoleResourcePermissionExample example = new RoleResourcePermissionExample();
		example.createCriteria().andRoleIdEqualTo(roleId)
				.andResourceIdEqualTo(resourceId);
		List<RoleResourcePermission> list = roleResourcePermissionMapper
				.selectByExample(example);
		if (list != null && list.size() > 0) {
			RoleResourcePermission roleResourcePermission = list.get(0);
			String permissionIds = roleResourcePermission.getPermissionIds();
			Long[] arr = GambleUtils.Array.toLongArray(permissionIds, ",");
			PermissionExample ex = new PermissionExample();
			ex.createCriteria().andIdIn(Arrays.asList(arr));
			permissions = permissionMapper.selectByExample(ex);
		}
		return permissions;
	}

	@Override
	public void assignResourceAndPermissions(Long roleId,
			Map<Long, Long[]> permissionMap) {
		// 清理该角色对应所有资源和权限
		RoleResourcePermissionExample example = new RoleResourcePermissionExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		roleResourcePermissionMapper.deleteByExample(example);

		// 授予该角色资源和权限
		Set<Long> keySet = permissionMap.keySet();
		for (Long resourceId : keySet) {
			RoleResourcePermission rrp = new RoleResourcePermission();
			rrp.setRoleId(roleId);
			rrp.setResourceId(resourceId);
			String permissionIds = GambleUtils.Array.toString(permissionMap
					.get(resourceId));
			rrp.setPermissionIds(permissionIds);
			roleResourcePermissionMapper.insertSelective(rrp);
		}
	}

	@Override
	public void deleteRBACByRole(Long roleId) {
		RoleResourcePermissionExample example = new RoleResourcePermissionExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		roleResourcePermissionMapper.deleteByExample(example);
	}

	@Override
	public void deleteRBACByResource(Long resourceId) {
		RoleResourcePermissionExample example = new RoleResourcePermissionExample();
		example.createCriteria().andResourceIdEqualTo(resourceId);
		roleResourcePermissionMapper.deleteByExample(example);
	}

	@Override
	public void deleteRBACByPermission(Long permissionId) {
		// FIXME cuesky 算法待完善
		RoleResourcePermissionExample example = new RoleResourcePermissionExample();
		example.createCriteria().andPermissionIdsLike("%" + permissionId + "%");
		List<RoleResourcePermission> list = roleResourcePermissionMapper
				.selectByExample(example);
		deletePermissions(list, permissionId);
		for (RoleResourcePermission rrp : list) {
			String permissionIds = rrp.getPermissionIds();
			if (StringUtils.isEmpty(permissionIds.trim())) {
				// 没有permission则删除RBAC记录
				roleResourcePermissionMapper.deleteByPrimaryKey(rrp.getId());
			} else {
				// 有则更新
				roleResourcePermissionMapper.updateByPrimaryKeySelective(rrp);
			}
		}
	}

	/**
	 * 擦除permissionId字符串
	 * 
	 * @param list
	 * @param permissionId
	 */
	private void deletePermissions(List<RoleResourcePermission> list,
			Long permissionId) {
		for (int i = 0; i < list.size(); i++) {
			RoleResourcePermission rrp = list.get(i);
			String permissionIds = rrp.getPermissionIds();
			permissionIds = GambleUtils.Security.removePermissionId(
					permissionIds, permissionId);
			rrp.setPermissionIds(permissionIds);
		}
	}

}
