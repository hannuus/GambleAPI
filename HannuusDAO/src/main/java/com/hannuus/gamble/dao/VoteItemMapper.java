package com.hannuus.gamble.dao;

import com.hannuus.gamble.model.VoteItem;
import com.hannuus.gamble.model.VoteItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteItemMapper {
    int countByExample(VoteItemExample example);

    int deleteByExample(VoteItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VoteItem record);

    int insertSelective(VoteItem record);

    List<VoteItem> selectByExample(VoteItemExample example);

    VoteItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VoteItem record, @Param("example") VoteItemExample example);

    int updateByExample(@Param("record") VoteItem record, @Param("example") VoteItemExample example);

    int updateByPrimaryKeySelective(VoteItem record);

    int updateByPrimaryKey(VoteItem record);
}