package com.hannuus.gamble.dao;

import com.hannuus.gamble.model.BlackList;
import com.hannuus.gamble.model.BlackListExample;
import com.hannuus.gamble.model.User;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BlackListMapper {

	int countByExample(BlackListExample example);

	int deleteByExample(BlackListExample example);

	int deleteByPrimaryKey(Long id);

	int insert(BlackList record);

	int insertSelective(BlackList record);

	List<BlackList> selectByExample(BlackListExample example);

	BlackList selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") BlackList record,
			@Param("example") BlackListExample example);

	int updateByExample(@Param("record") BlackList record,
			@Param("example") BlackListExample example);

	int updateByPrimaryKeySelective(BlackList record);

	int updateByPrimaryKey(BlackList record);

	public List<User> findBlackList(Long userId);

}