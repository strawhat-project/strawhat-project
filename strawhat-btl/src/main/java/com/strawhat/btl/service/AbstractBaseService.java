package com.strawhat.btl.service;

import com.github.pagehelper.PageHelper;
import com.google.common.base.CaseFormat;
import com.strawhat.dal.AffectedRowsException;
import com.strawhat.dal.model.BaseDO;
import com.strawhat.dal.mapper.BaseMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractBaseService<T extends BaseDO> implements BaseService<T>{

    protected static final int AFFECTED_ONE_ROWS = 1;

    protected BaseMapper<T> baseMapper;

    public AbstractBaseService(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public void save(@NotNull T entity) {
        int affectedRows = baseMapper.insertSelective(entity);
        assertAffectedRows(affectedRows,AFFECTED_ONE_ROWS);
    }

    @Override
    public void delete(@NotNull T entity) {
        delete(entity.getId());
    }

    @Override
    public void delete(long id) {
        int affectedRows = baseMapper.deleteByPrimaryKey(id);
        assertAffectedRows(affectedRows,AFFECTED_ONE_ROWS);
    }

    @Override
    public void update(@NotNull T entity, boolean selective) {
        int affectedRows = 0;
        if(selective){
            affectedRows = baseMapper.updateByPrimaryKeySelective(entity);
        }else {
            affectedRows = baseMapper.updateByPrimaryKey(entity);
        }
        assertAffectedRows(affectedRows,AFFECTED_ONE_ROWS);
    }

    @Override
    public void update(@NotNull T entity) {
        int affectedRows = baseMapper.updateByPrimaryKey(entity);
        assertAffectedRows(affectedRows,AFFECTED_ONE_ROWS);
    }

    @Override
    public T get(long id){
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> list(T entity){
        return baseMapper.selectSelective(entity);
    }

    @Override
    public Page<T> find(T entity, Pageable pageable){
        setPagination(pageable);
        List<T> list = list(entity);
        return toPage(list,pageable);
    }

    /**
     * 设置页码，一般在查询前使用
     * @param pageable
     */
    public void setPagination(Pageable pageable){
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize()
                , pageable.getSort().stream().map(order -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, order.getProperty()) + " " + order.getDirection()).collect(Collectors.joining(",")));
    }

    /**
     * 断言受影响的行
     * @param reality 实际
     * @param idea 理想
     */
    protected void assertAffectedRows(int reality,int idea){
        if(reality!=idea){
            throw new AffectedRowsException();
        }
    }

    public Page<T> toPage(List<T> list,Pageable pageable){
        Page<T> rsPage = null;
        if (list instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page page = (com.github.pagehelper.Page)list;
            rsPage = new PageImpl<T>(list,pageable,page.getTotal());
        } else if (list instanceof Collection) {
            rsPage= new PageImpl<T>(list);
        }
        return rsPage;
    }
}
