package com.hannuus.gamble.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hannuus.gamble.dao.RoleManageMapper;
import com.hannuus.gamble.web.service.PermissionService;

/**
 * @author cuesky
 * @date 2015年8月8日 下午5:45:33
 */
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	RoleManageMapper roleManageMapper;

	@Override
	public boolean isPermissionDefined(String permissionPath, Long loginUserId) {
		Integer num = roleManageMapper.containsPermission(loginUserId,
				permissionPath);
		return num != null;
	}

}
