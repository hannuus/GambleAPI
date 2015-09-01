package com.hannuus.gamble.web.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hannuus.gamble.domain.page.PageDTO;
import com.hannuus.gamble.domain.page.PageParams;
import com.hannuus.gamble.domain.page.PageQueryCallback;
import com.hannuus.gamble.model.Permission;
import com.hannuus.gamble.model.Resource;
import com.hannuus.gamble.model.Role;
import com.hannuus.gamble.model.User;
import com.hannuus.gamble.web.service.AuthService;

/**
 * Authority Web API
 * 
 * @author cuesky
 * @date 2015年9月1日 上午9:10:30
 */
@Controller
@RequestMapping("/auth")
public class AuthAction extends BaseAction {

	@Autowired
	AuthService authService;

	// about user============================================================

	/**
	 * 分页查询用户列表
	 * 
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/listUsers")
	public ModelAndView listUsers(ModelMap map, int pageNum, int pageSize) {
		pageQuery(pageNum, pageSize, map, new PageQueryCallback<User>() {
			@Override
			public PageDTO<User> query(PageParams params) {
				return authService.findUsersPage(params);
			}
		});
		return new ModelAndView("/auth/user_list");
	}

	/**
	 * 至新增用户页面
	 * 
	 * @return
	 */
	@RequestMapping("/toAddUser")
	public ModelAndView toAddUser() {
		return new ModelAndView("/auth/user_edit");
	}

	/**
	 * 新增用户
	 * 
	 * @param map
	 * @param user
	 * @return
	 */
	@RequestMapping("/doAddUser")
	public ModelAndView doAddUser(ModelMap map, User user) {
		authService.addUser(user);
		return listUsers(map, 1, 0);
	}

	/**
	 * 至更新用户页面
	 * 
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping("/toEditUser")
	public ModelAndView toEditUser(ModelMap map, Long id) {
		User user = authService.findUser(id);
		map.put("user", user);
		return new ModelAndView("/auth/user_edit");
	}

	/**
	 * 更新用户
	 * 
	 * @param map
	 * @param user
	 * @return
	 */
	@RequestMapping("/doEditUser")
	public ModelAndView doEditUser(ModelMap map, User user) {
		authService.updateUser(user);
		return listUsers(map, 1, 0);
	}

	/**
	 * 删除用户
	 * 
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping("/doDeleteUser")
	public ModelAndView doDeleteUser(ModelMap map, Long id) {
		// 清理用户角色
		authService.deleteUserRoleByUser(id);
		// 清理用户
		authService.deleteUser(id);
		return listUsers(map, 1, 0);
	}

	// about role============================================================

	/**
	 * 分页查询角色列表
	 * 
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/listRoles")
	public ModelAndView listRoles(ModelMap map, int pageNum, int pageSize) {
		pageQuery(pageNum, pageSize, map, new PageQueryCallback<Role>() {
			@Override
			public PageDTO<Role> query(PageParams params) {
				return authService.findRolesPage(params);
			}
		});
		return new ModelAndView("/auth/role_list");
	}

	/**
	 * 至新增角色页面
	 * 
	 * @return
	 */
	@RequestMapping("/toAddRole")
	public ModelAndView toAddRole() {
		return new ModelAndView("/auth/role_edit");
	}

	/**
	 * 新增角色
	 * 
	 * @param map
	 * @param role
	 * @return
	 */
	@RequestMapping("/doAddRole")
	public ModelAndView doAddRole(ModelMap map, Role role) {
		role.setCreatedDate(new Date());// 默认系统时间
		role.setAvailable(1);// 默认可用
		authService.addRole(role);
		return listRoles(map, 1, 0);
	}

	/**
	 * 至更新角色页面
	 * 
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping("/toEditRole")
	public ModelAndView toEditRole(ModelMap map, Long id) {
		Role role = authService.findRole(id);
		map.put("role", role);
		return new ModelAndView("/auth/role_edit");
	}

	/**
	 * 更新角色
	 * 
	 * @param map
	 * @param role
	 * @return
	 */
	@RequestMapping("/doEditRole")
	public ModelAndView doEditRole(ModelMap map, Role role) {
		authService.updateRole(role);
		return listRoles(map, 1, 0);
	}

	/**
	 * 删除角色
	 * 
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping("/doDeleteRole")
	public ModelAndView doDeleteRole(ModelMap map, Long id) {
		// 清理用户角色
		authService.deleteUserRoleByRole(id);
		// 清理RBAC
		authService.deleteRBACByRole(id);
		// 清理角色
		authService.deleteRole(id);
		return listRoles(map, 1, 0);
	}

	// about resource======================================================

	/**
	 * 分页查询资源列表
	 * 
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/listResources")
	public ModelAndView listResources(ModelMap map, int pageNum, int pageSize) {
		pageQuery(pageNum, pageSize, map, new PageQueryCallback<Resource>() {
			@Override
			public PageDTO<Resource> query(PageParams params) {
				return authService.findResourcesPage(params);
			}
		});
		return new ModelAndView("/auth/resource_list");
	}

	/**
	 * 至新增资源页面
	 * 
	 * @return
	 */
	@RequestMapping("/toAddResource")
	public ModelAndView toAddResource() {
		return new ModelAndView("/auth/resource_edit");
	}

	/**
	 * 新增资源
	 * 
	 * @param map
	 * @param resource
	 * @return
	 */
	@RequestMapping("/doAddResource")
	public ModelAndView doAddResource(ModelMap map, Resource resource) {
		resource.setCreatedDate(new Date());// 默认系统时间
		resource.setAvailable(1);// 默认可用
		authService.addResource(resource);
		return listResources(map, 1, 0);
	}

	/**
	 * 至更新资源页面
	 * 
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping("/toEditResource")
	public ModelAndView toEditResource(ModelMap map, Long id) {
		Resource resource = authService.findResource(id);
		map.put("resource", resource);
		return new ModelAndView("/auth/resource_edit");
	}

	/**
	 * 更新资源
	 * 
	 * @param map
	 * @param resource
	 * @return
	 */
	@RequestMapping("/doEditResource")
	public ModelAndView doEditResource(ModelMap map, Resource resource) {
		authService.updateResource(resource);
		return listResources(map, 1, 0);
	}

	/**
	 * 删除资源
	 * 
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping("/doDeleteResource")
	public ModelAndView doDeleteResource(ModelMap map, Long id) {
		// 清理RBAC
		authService.deleteRBACByResource(id);
		// 清理角色
		authService.deleteResource(id);
		return listResources(map, 1, 0);
	}

	// about permission======================================================

	/**
	 * 分页查询权限列表
	 * 
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/listPermissions")
	public ModelAndView listPermissions(ModelMap map, int pageNum, int pageSize) {
		pageQuery(pageNum, pageSize, map, new PageQueryCallback<Permission>() {
			@Override
			public PageDTO<Permission> query(PageParams params) {
				return authService.findPermissionsPage(params);
			}
		});
		return new ModelAndView("/auth/permission_list");
	}

	/**
	 * 至新增权限页面
	 * 
	 * @return
	 */
	@RequestMapping("/toAddPermission")
	public ModelAndView toAddPermission() {
		return new ModelAndView("/auth/permission_edit");
	}

	/**
	 * 新增权限
	 * 
	 * @param map
	 * @param resource
	 * @return
	 */
	@RequestMapping("/doAddPermission")
	public ModelAndView doAddPermission(ModelMap map, Permission permission) {
		permission.setCreatedDate(new Date());// 默认系统时间
		permission.setAvailable(1);// 默认可用
		authService.addPermission(permission);
		return listPermissions(map, 1, 0);
	}

	/**
	 * 至更新权限页面
	 * 
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping("/toEditPermission")
	public ModelAndView toEditPermission(ModelMap map, Long id) {
		Permission permission = authService.findPermission(id);
		map.put("resource", permission);
		return new ModelAndView("/auth/permission_edit");
	}

	/**
	 * 更新权限
	 * 
	 * @param map
	 * @param permission
	 * @return
	 */
	@RequestMapping("/doEditPermission")
	public ModelAndView doEditPermission(ModelMap map, Permission permission) {
		authService.updatePermission(permission);
		return listPermissions(map, 1, 0);
	}

	/**
	 * 删除权限
	 * 
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping("/doDeletePermission")
	public ModelAndView doDeletePermission(ModelMap map, Long id) {
		// 清理RBAC中对应权限信息 permissionIds
		authService.deleteRBACByPermission(id);
		// 清理角色
		authService.deletePermission(id);
		return listPermissions(map, 1, 0);
	}

}
