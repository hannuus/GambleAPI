package com.hannuus.gamble.dao;

import com.hannuus.gamble.model.VoteUser;
import com.hannuus.gamble.model.VoteUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteUserMapper {
    int countByExample(VoteUserExample example);

    int deleteByExample(VoteUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VoteUser record);

    int insertSelective(VoteUser record);

    List<VoteUser> selectByExample(VoteUserExample example);

    VoteUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VoteUser record, @Param("example") VoteUserExample example);

    int updateByExample(@Param("record") VoteUser record, @Param("example") VoteUserExample example);

    int updateByPrimaryKeySelective(VoteUser record);

    int updateByPrimaryKey(VoteUser record);
}