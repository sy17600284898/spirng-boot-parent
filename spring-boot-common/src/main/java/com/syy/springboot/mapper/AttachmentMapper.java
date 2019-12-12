package com.syy.springboot.mapper;

import commercial.base.db.mysql.commercial.entity.Attachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttachmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attachment record);

    int insertSelective(Attachment record);

    Attachment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attachment record);

    int updateByPrimaryKey(Attachment record);

    void insertBatch(@Param("list") List<Attachment> list);

    /**
     * getListByforeignKey
     * @param foreignKey
     * @return
     */
    List<Attachment> getListByforeignKey(@Param("foreignKey") Integer foreignKey);

}