package com.hannuus.gamble.dao;

import com.hannuus.model.gamble.RoleGroup;
import com.hannuus.model.gamble.RoleGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleGroupMapper {
    int countByExample(RoleGroupExample example);

    int deleteByExample(RoleGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoleGroup record);

    int insertSelective(RoleGroup record);

    List<RoleGroup> selectByExample(RoleGroupExample example);

    RoleGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoleGroup record, @Param("example") RoleGroupExample example);

    int updateByExample(@Param("record") RoleGroup record, @Param("example") RoleGroupExample example);

    int updateByPrimaryKeySelective(RoleGroup record);

    int updateByPrimaryKey(RoleGroup record);
}