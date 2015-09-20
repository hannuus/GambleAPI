package com.hannuus.gamble.dao;

import com.hannuus.gamble.model.Topic;
import com.hannuus.gamble.model.TopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicMapper {

	int countByExample(TopicExample example);

	int deleteByExample(TopicExample example);

	int deleteByPrimaryKey(Long id);

	int insert(Topic record);

	int insertSelective(Topic record);

	List<Topic> selectByExampleWithBLOBs(TopicExample example);

	List<Topic> selectByExample(TopicExample example);

	Topic selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") Topic record,
			@Param("example") TopicExample example);

	int updateByExampleWithBLOBs(@Param("record") Topic record,
			@Param("example") TopicExample example);

	int updateByExample(@Param("record") Topic record,
			@Param("example") TopicExample example);

	int updateByPrimaryKeySelective(Topic record);

	int updateByPrimaryKeyWithBLOBs(Topic record);

	int updateByPrimaryKey(Topic record);

	int increaseUpCountByPrimaryKey(Long id);

	int increaseDownCountByPrimaryKey(Long id);

	List<Topic> findSampleTopics(Integer sampleDay);

}