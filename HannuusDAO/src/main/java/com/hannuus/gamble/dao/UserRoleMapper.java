package com.hannuus.gamble.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hannuus.gamble.model.Role;
import com.hannuus.gamble.model.UserRole;
import com.hannuus.gamble.model.UserRoleExample;

public interface UserRoleMapper {

	int countByExample(UserRoleExample example);

	int deleteByExample(UserRoleExample example);

	int deleteByPrimaryKey(Long id);

	int insert(UserRole record);

	int insertSelective(UserRole record);

	List<UserRole> selectByExample(UserRoleExample example);

	UserRole selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") UserRole record,
			@Param("example") UserRoleExample example);

	int updateByExample(@Param("record") UserRole record,
			@Param("example") UserRoleExample example);

	int updateByPrimaryKeySelective(UserRole record);

	int updateByPrimaryKey(UserRole record);

	String[] findRoleValuesByUserName(String userName);

	List<Role> findRolesByUserId(Long userId);

}