package com.hannuus.gamble.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hannuus.gamble.model.UserChannel;
import com.hannuus.gamble.model.UserChannelExample;

public interface UserChannelMapper {
    int countByExample(UserChannelExample example);

    int deleteByExample(UserChannelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserChannel record);

    int insertSelective(UserChannel record);

    List<UserChannel> selectByExample(UserChannelExample example);

    UserChannel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserChannel record, @Param("example") UserChannelExample example);

    int updateByExample(@Param("record") UserChannel record, @Param("example") UserChannelExample example);

    int updateByPrimaryKeySelective(UserChannel record);

    int updateByPrimaryKey(UserChannel record);
}