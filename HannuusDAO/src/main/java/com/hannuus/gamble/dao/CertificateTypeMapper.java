package com.hannuus.gamble.dao;

import com.hannuus.gamble.model.CertificateType;
import com.hannuus.gamble.model.CertificateTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertificateTypeMapper {
    int countByExample(CertificateTypeExample example);

    int deleteByExample(CertificateTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CertificateType record);

    int insertSelective(CertificateType record);

    List<CertificateType> selectByExample(CertificateTypeExample example);

    CertificateType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CertificateType record, @Param("example") CertificateTypeExample example);

    int updateByExample(@Param("record") CertificateType record, @Param("example") CertificateTypeExample example);

    int updateByPrimaryKeySelective(CertificateType record);

    int updateByPrimaryKey(CertificateType record);
}