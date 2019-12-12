package com.syy.springboot.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * DAO公共基类，由MybatisGenerator自动生成请勿修改
 *
 * @param <Model> The Model Class 这里是泛型不是Model类
 * @param <PK>    The Primary Key Class 如果是无主键，则可以用Model来跳过，如果是多主键则是Key类
 */
@Mapper
public interface MyBatisBaseMapper<Model, PK extends Serializable> {

    /**
     * findList
     *
     * @param record
     * @return
     */
    List<Model> findList(Model record);

    /**
     * deleteByPrimaryKey
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(PK id);

    /**
     * insert
     *
     * @param record
     * @return
     */
    int insert(Model record);

    /**
     * insertSelective
     *
     * @param record
     * @return
     */
    boolean insertSelective(Model record);

    /**
     * selectByPrimaryKey
     *
     * @param id
     * @return
     */
    Model selectByPrimaryKey(PK id);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record
     * @return
     */
    boolean updateByPrimaryKeySelective(Model record);

    /**
     * updateByPrimaryKey
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Model record);


    /**
     * count
     *
     * @param map
     * @return
     */
    int count(Map<String, Object> map);

    /**
     * selectByExample
     *
     * @param map
     * @return
     */
    List<Model> selectByExample(Map<String, Object> map);
}