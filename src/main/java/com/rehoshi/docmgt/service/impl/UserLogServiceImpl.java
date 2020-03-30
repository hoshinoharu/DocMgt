package com.rehoshi.docmgt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rehoshi.docmgt.dao.UserLogDao;
import com.rehoshi.docmgt.domain.entities.UserLog;
import com.rehoshi.docmgt.service.UserLogService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserLogServiceImpl extends HoshiService<UserLogDao, UserLog> implements UserLogService {
    @Override
    public List<UserLog> list() {
        QueryWrapper<UserLog> wrapper = new QueryWrapper<>() ;
        wrapper.orderByDesc("search_time") ;
        return page(getPage(), wrapper).getRecords();
    }
}
