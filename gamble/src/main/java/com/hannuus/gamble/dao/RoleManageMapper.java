package com.hannuus.gamble.dao;

import com.hannuus.gamble.bean.RoleManage;
import com.hannuus.gamble.bean.RoleManageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleManageMapper {

	int countByExample(RoleManageExample example);

	int deleteByExample(RoleManageExample example);

	int deleteByPrimaryKey(Long id);

	int insert(RoleManage record);

	int insertSelective(RoleManage record);

	List<RoleManage> selectByExample(RoleManageExample example);

	RoleManage selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") RoleManage record,
			@Param("example") RoleManageExample example);

	int updateByExample(@Param("record") RoleManage record,
			@Param("example") RoleManageExample example);

	int updateByPrimaryKeySelective(RoleManage record);

	int updateByPrimaryKey(RoleManage record);

	Integer containsPermission(@Param("id") Long loginUserId,
			@Param("permissionPath") String permissionPath);
}