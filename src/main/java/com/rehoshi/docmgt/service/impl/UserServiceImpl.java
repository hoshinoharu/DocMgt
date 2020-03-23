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
     * 更新用户
     *
     * @param user
     */
    @Override
    public void update(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("id", user.getId());
        getBaseMapper().update(user, queryWrapper);
    }

    /**
     * 根据关键字查询用户名，描述字段
     *
     * @param key
     * @return list(需要分页则分页 ， 不需要分页返回list)
     */
    @Override
    public List<User> getKey(String key) {
        List<User> list;
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("name", "description").eq("account", key);
        if (needPage()) {
            list = page(getPage(), queryWrapper).getRecords();
        } else {
            list = list(queryWrapper);
        }
        return list;
    }

    /**
     * 根据账号查询用户所有信息
     * @param account
     * @return
     */
    @Override
    public List<User> getAccount(String account) {
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
    public List<User> getName(String key) {
        List<User> userList;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", key);
        if (needPage()) {
            userList = page(getPage(), wrapper).getRecords();
        } else {
            userList = list(wrapper);
        }
        return userList;
    }
}
