package com.rehoshi.docmgt.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rehoshi.docmgt.config.PageConfig;
import com.rehoshi.stream.HStream;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 自带规则引擎
 * @param <M>
 * @param <T>
 */
public abstract class HoshiService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {
    @Autowired
    private KieSession kieSession;
    @Autowired
    private HttpServletRequest request ;

    private PageConfig config ;

    private T fireOne(T one) {
        if (one != null) {
            kieSession.insert(one);
            kieSession.fireAllRules();
        }
        return one;
    }

    private <C extends Collection<T>> C fireAll(C collection) {
        HStream.$(collection).forEach(kieSession::insert);
        kieSession.fireAllRules();
        return collection;
    }

    @Override
    public T getOne(Wrapper<T> queryWrapper, boolean throwEx) {
        return fireOne(super.getOne(queryWrapper, throwEx));
    }

    @Override
    public T getById(Serializable id) {
        return fireOne(this.getBaseMapper().selectById(id));
    }

    @Override
    public List<T> listByIds(Collection<? extends Serializable> idList) {
        if(HStream.nullOrEmpty(idList)){
            return new ArrayList<>() ;
        }
        return fireAll(this.getBaseMapper().selectBatchIds(idList));
    }

    @Override
    public List<T> list(Wrapper<T> queryWrapper) {
        return fireAll(this.getBaseMapper().selectList(queryWrapper));
    }

    @Override
    public List<T> listByMap(Map<String, Object> columnMap) {
        return fireAll(this.getBaseMapper().selectByMap(columnMap));
    }

    @Override
    public <E extends IPage<T>> E page(E page, Wrapper<T> queryWrapper) {
        E e = this.getBaseMapper().selectPage(page, queryWrapper);
        fireAll(e.getRecords()) ;
        return e;
    }

    protected boolean needPage(){
        return needPage(true) ;
    }

    protected boolean needPage(boolean reset){
        if(reset){
            return getPageConfig().needPageThenReset() ;
        }
        return getPageConfig().needPage() ;
    }

    protected Page<T> getPage(){
        return new Page<>(getPageConfig().getPageIndex(), getPageConfig().getPageSize(), getPageConfig().isSearchCount());
    }

    private PageConfig getPageConfig(){
        if(config == null){
            config = PageConfig.get(request) ;
        }
        return config;
    }

}

