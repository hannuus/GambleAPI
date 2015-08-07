package com.hannuus.gamble.dao;

import com.hannuus.gamble.bean.RuleGroup;
import com.hannuus.gamble.bean.RuleGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RuleGroupMapper {
    int countByExample(RuleGroupExample example);

    int deleteByExample(RuleGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RuleGroup record);

    int insertSelective(RuleGroup record);

    List<RuleGroup> selectByExample(RuleGroupExample example);

    RuleGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RuleGroup record, @Param("example") RuleGroupExample example);

    int updateByExample(@Param("record") RuleGroup record, @Param("example") RuleGroupExample example);

    int updateByPrimaryKeySelective(RuleGroup record);

    int updateByPrimaryKey(RuleGroup record);
}