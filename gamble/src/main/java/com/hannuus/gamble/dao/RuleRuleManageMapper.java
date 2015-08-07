package com.hannuus.gamble.dao;

import com.hannuus.gamble.bean.RuleRuleManage;
import com.hannuus.gamble.bean.RuleRuleManageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RuleRuleManageMapper {
    int countByExample(RuleRuleManageExample example);

    int deleteByExample(RuleRuleManageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RuleRuleManage record);

    int insertSelective(RuleRuleManage record);

    List<RuleRuleManage> selectByExample(RuleRuleManageExample example);

    RuleRuleManage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RuleRuleManage record, @Param("example") RuleRuleManageExample example);

    int updateByExample(@Param("record") RuleRuleManage record, @Param("example") RuleRuleManageExample example);

    int updateByPrimaryKeySelective(RuleRuleManage record);

    int updateByPrimaryKey(RuleRuleManage record);
}