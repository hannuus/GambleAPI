package com.hannuus.gamble.dao;

import com.hannuus.gamble.model.User;
import com.hannuus.gamble.model.UserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	int countByExample(UserExample example);

	int deleteByExample(UserExample example);

	int deleteByPrimaryKey(Long id);

	int insert(User record);

	int insertSelective(User record);

	List<User> selectByExample(UserExample example);

	User selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") User record,
			@Param("example") UserExample example);

	int updateByExample(@Param("record") User record,
			@Param("example") UserExample example);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	User checkLogin(@Param("userName") String userName,
			@Param("password") String password);

	int countByRole(String roleValue);

	List<User> selectByRole(@Param("roleValue") String roleValue,
			@Param("start") int start, @Param("pageSize") int pageSize);

	int insertUserRole(@Param("userId") Long userId,
			@Param("roleValue") String roleValue);

	List<Long> selectIdsByRole(Integer virtualType);

}