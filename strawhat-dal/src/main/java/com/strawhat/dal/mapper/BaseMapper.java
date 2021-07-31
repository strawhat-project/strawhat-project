package com.strawhat.dal.mapper;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T> extends Mapper<T> {

    int insert(T entity);

    int insertSelective(T Entity);

    int deleteByPrimaryKey(Serializable id);

    int updateByPrimaryKey(T Entity);

    int updateByPrimaryKeySelective(T Entity);

    T selectByPrimaryKey(Serializable id);

    List<T> selectSelective(T entity);

}
