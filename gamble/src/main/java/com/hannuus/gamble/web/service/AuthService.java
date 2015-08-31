package com.hannuus.gamble.web.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hannuus.gamble.model.Permission;
import com.hannuus.gamble.model.Resource;
import com.hannuus.gamble.model.Role;
import com.hannuus.gamble.model.User;

/**
 * 该接口用来操作所有权限相关的Domain
 * 
 * @author cuesky
 * @date 2015年8月28日 下午12:07:20
 */
public interface AuthService {

	// about shiro==============================================================

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

	// about user==============================================================

	public void addUser(User user);

	public void deleteUser(Long id);

	public void batchDeleteUsers(Long[] ids);

	public void updateUser(User user);

	public User findUser(Long id);

	public List<User> findUsers();

	public List<User> findUsersLike(String name);

	/**
	 * For ajax validate
	 * 
	 * @param userName
	 * @return
	 */
	public boolean isUserExists(String userName);

	// about role==============================================================

	public void addRole(Role role);

	public void deleteRole(Long id);

	public void batchDeleteRoles(Long[] ids);

	public void updateRole(Role role);

	public Role findRole(Long id);

	public List<Role> findRoles();

	public List<Role> findRolesLike(String name);

	/**
	 * For ajax validate
	 * 
	 * @param name
	 * @param roleValue
	 * @return
	 */
	public boolean isRoleExists(String name, String roleValue);

	// about resource==========================================================

	public void addResource(Resource resource);

	public void deleteResource(Long id);

	public void batchDeleteResources(Long[] ids);

	public void updateResource(Resource resource);

	public Resource findResource(Long id);

	public List<Resource> findResources();

	public List<Resource> findResourcesLike(String name);

	/**
	 * For ajax validate
	 * 
	 * @param name
	 * @param resourceValue
	 * @return
	 */
	public boolean isResourceExists(String name, String resourceValue);

	// about permission========================================================

	public void addPermission(Permission permission);

	public void deletePermission(Long id);

	public void batchDeletePermissions(Long[] ids);

	public void updatePermission(Permission permission);

	public Permission findPermission(Long id);

	public List<Permission> findPermissions();

	public List<Permission> findPermissionsLike(String name);

	/**
	 * For ajax validate
	 * 
	 * @param name
	 * @param permissionValue
	 * @return
	 */
	public boolean isPermissionExists(String name, String permissionValue);

	// about RBAC==============================================================
	// FIXME 待改良

	// 角色分配方案1：基于checkbox

	/**
	 * 根据userId查询该用户拥有的所有角色
	 * 
	 * @param userId
	 * @return
	 */
	public List<Role> findRolesByUserId(Long userId);

	/**
	 * 给一个用户授予一批角色<br>
	 * 首先清除用户对应的所有角色，然后进行更新
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void assignRoles(Long userId, Long[] roleIds);

	/**
	 * 给多个用户授予同一批角色<br>
	 * 首先清除用户对应的所有角色，然后进行更新
	 * 
	 * @param userIds
	 * @param roleIds
	 */
	public void batchAssignRoles(Long[] userIds, Long[] roleIds);

	// 资源权限分配方案1：基于checkbox

	/**
	 * 查询该角色对应的所有资源
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Resource> findRBACResources(Long roleId);

	/**
	 * 查询该角色+资源对应的所有权限
	 * 
	 * @param roleId
	 * @param resourceId
	 * @return
	 */
	public List<Permission> findRBACPermissions(Long roleId, Long resourceId);

	/**
	 * 给某角色分配RRP<br>
	 * 先根据roleId清理掉该角色对应的所有资源权限，然后再授予
	 * 
	 * @param roleId
	 * @param permissionMap
	 */
	public void assignResourceAndPermissions(Long roleId,
			Map<Long, Long[]> permissionMap);

}
