package com.hannuus.gamble.dao;

import com.hannuus.gamble.model.TopicImage;
import com.hannuus.gamble.model.TopicImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicImageMapper {
    int countByExample(TopicImageExample example);

    int deleteByExample(TopicImageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TopicImage record);

    int insertSelective(TopicImage record);

    List<TopicImage> selectByExample(TopicImageExample example);

    TopicImage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TopicImage record, @Param("example") TopicImageExample example);

    int updateByExample(@Param("record") TopicImage record, @Param("example") TopicImageExample example);

    int updateByPrimaryKeySelective(TopicImage record);

    int updateByPrimaryKey(TopicImage record);
}