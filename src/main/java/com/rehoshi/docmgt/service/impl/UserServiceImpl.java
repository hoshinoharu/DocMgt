package com.rehoshi.docmgt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rehoshi.docmgt.dao.UserDao;
import com.rehoshi.docmgt.domain.entities.Doc;
import com.rehoshi.docmgt.domain.entities.User;
import com.rehoshi.docmgt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public List<User> selectByAccount() {
        List<User> userList ;
        User user = new User();
        QueryWrapper<User> wrapper = new QueryWrapper<>() ;
        wrapper.eq("account", user.getName()) ;
        if(needPage()){
            userList = page(getPage(),wrapper).getRecords();
        }else {
            userList = list(wrapper);
        }
        return userList;

    }




}
