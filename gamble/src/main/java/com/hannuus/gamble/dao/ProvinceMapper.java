package com.hannuus.gamble.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hannuus.gamble.bean.Province;
import com.hannuus.gamble.bean.ProvinceExample;

public interface ProvinceMapper {
	
    int countByExample(ProvinceExample example);

    int deleteByExample(ProvinceExample example);

    int insert(Province record);

    int insertSelective(Province record);

    List<Province> selectByExample(ProvinceExample example);

    int updateByExampleSelective(@Param("record") Province record, @Param("example") ProvinceExample example);

    int updateByExample(@Param("record") Province record, @Param("example") ProvinceExample example);
}