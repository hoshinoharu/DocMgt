package com.rehoshi.docmgt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rehoshi.docmgt.dao.UserDao;
import com.rehoshi.docmgt.domain.entities.User;
import com.rehoshi.docmgt.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends HoshiService<UserDao, User> implements UserService {
    /**
     * 根据账号查询用户所有信息
     * @param account
     * @return
     */
    @Override
    public List<User> getByAccount(String account) {
        List<User> list;
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("account",account);
        list = list(queryWrapper);
        return list;
    }

    /**
     * 根据姓名查询所有信息
     * @param key
     * @return lsit
     */
    @Override
    public List<User> listBySearch(String key) {
        key = "%"+key+"%" ;
        List<User> userList;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", key)
        .or().like("account", key);
        if (needPage()) {
            userList = page(getPage(), wrapper).getRecords();
        } else {
            userList = list(wrapper);
        }
        return userList;
    }
}
