package com.rehoshi.docmgt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rehoshi.docmgt.dao.UserDao;
import com.rehoshi.docmgt.domain.entities.User;
import com.rehoshi.docmgt.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl extends HoshiService<UserDao, User>implements UserService {
    @Override
    public List<User> selectByName(String name) {
        List<User> userList ;
        QueryWrapper<User> wrapper = new QueryWrapper<>() ;
        wrapper.eq("category", name) ;
        if(needPage()){
            userList = page(getPage(),wrapper).getRecords();
        }else {
            userList = list(wrapper) ;
        }
        return userList;
    }

    @Override
    public List<User> selectByAccount(String account) {
        List<User> userList;
        QueryWrapper<User> wrapper = new QueryWrapper<>() ;
        wrapper.eq("account", account) ;
        if(needPage()){
            userList = page(getPage(),wrapper).getRecords();
        }else {
            userList = list(wrapper);
        }
        return userList;

    }




}
