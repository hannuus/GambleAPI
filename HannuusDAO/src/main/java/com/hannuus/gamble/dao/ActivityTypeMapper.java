package com.hannuus.gamble.dao;

import com.hannuus.gamble.model.ActivityType;
import com.hannuus.gamble.model.ActivityTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityTypeMapper {
    int countByExample(ActivityTypeExample example);

    int deleteByExample(ActivityTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityType record);

    int insertSelective(ActivityType record);

    List<ActivityType> selectByExample(ActivityTypeExample example);

    ActivityType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityType record, @Param("example") ActivityTypeExample example);

    int updateByExample(@Param("record") ActivityType record, @Param("example") ActivityTypeExample example);

    int updateByPrimaryKeySelective(ActivityType record);

    int updateByPrimaryKey(ActivityType record);
}