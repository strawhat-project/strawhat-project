package com.strawhat.btl.service;

import com.strawhat.dal.model.BaseDO;
import com.strawhat.service.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<T extends BaseDO> extends Service {

    /**
     * 增删改查
     * @param entity
     * @return
     */
    void save(T entity);

    void delete(T entity);

    void delete(long id);

    void update(T entity,boolean selective);

    void update(T entity);

    T get(long id);

    List<T> list(T entity);

    Page<T> find(T entity, Pageable pageable);
}
