package com.hannuus.gamble.dao;

import com.hannuus.gamble.model.GlobalParams;
import com.hannuus.gamble.model.GlobalParamsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GlobalParamsMapper {
    int countByExample(GlobalParamsExample example);

    int deleteByExample(GlobalParamsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GlobalParams record);

    int insertSelective(GlobalParams record);

    List<GlobalParams> selectByExample(GlobalParamsExample example);

    GlobalParams selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GlobalParams record, @Param("example") GlobalParamsExample example);

    int updateByExample(@Param("record") GlobalParams record, @Param("example") GlobalParamsExample example);

    int updateByPrimaryKeySelective(GlobalParams record);

    int updateByPrimaryKey(GlobalParams record);
}