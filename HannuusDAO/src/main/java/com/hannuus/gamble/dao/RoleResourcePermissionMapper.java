package com.hannuus.gamble.dao;

import com.hannuus.gamble.model.RoleResourcePermission;
import com.hannuus.gamble.model.RoleResourcePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleResourcePermissionMapper {
    int countByExample(RoleResourcePermissionExample example);

    int deleteByExample(RoleResourcePermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoleResourcePermission record);

    int insertSelective(RoleResourcePermission record);

    List<RoleResourcePermission> selectByExample(RoleResourcePermissionExample example);

    RoleResourcePermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoleResourcePermission record, @Param("example") RoleResourcePermissionExample example);

    int updateByExample(@Param("record") RoleResourcePermission record, @Param("example") RoleResourcePermissionExample example);

    int updateByPrimaryKeySelective(RoleResourcePermission record);

    int updateByPrimaryKey(RoleResourcePermission record);
}