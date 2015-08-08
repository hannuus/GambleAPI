package com.hannuus.gamble.dao;

import com.hannuus.model.gamble.RoleGroupRight;
import com.hannuus.model.gamble.RoleGroupRightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleGroupRightMapper {
    int countByExample(RoleGroupRightExample example);

    int deleteByExample(RoleGroupRightExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoleGroupRight record);

    int insertSelective(RoleGroupRight record);

    List<RoleGroupRight> selectByExample(RoleGroupRightExample example);

    RoleGroupRight selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoleGroupRight record, @Param("example") RoleGroupRightExample example);

    int updateByExample(@Param("record") RoleGroupRight record, @Param("example") RoleGroupRightExample example);

    int updateByPrimaryKeySelective(RoleGroupRight record);

    int updateByPrimaryKey(RoleGroupRight record);
}