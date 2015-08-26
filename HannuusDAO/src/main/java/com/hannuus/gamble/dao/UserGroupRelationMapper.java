package com.hannuus.gamble.dao;

import com.hannuus.gamble.model.UserGroupRelation;
import com.hannuus.gamble.model.UserGroupRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserGroupRelationMapper {
    int countByExample(UserGroupRelationExample example);

    int deleteByExample(UserGroupRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserGroupRelation record);

    int insertSelective(UserGroupRelation record);

    List<UserGroupRelation> selectByExample(UserGroupRelationExample example);

    UserGroupRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserGroupRelation record, @Param("example") UserGroupRelationExample example);

    int updateByExample(@Param("record") UserGroupRelation record, @Param("example") UserGroupRelationExample example);

    int updateByPrimaryKeySelective(UserGroupRelation record);

    int updateByPrimaryKey(UserGroupRelation record);
}