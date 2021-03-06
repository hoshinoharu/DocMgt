package com.rehoshi.docmgt.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rehoshi.docmgt.domain.entities.User;

import java.util.List;

public interface UserService extends IService<User> {
    /***
     * 根据用户名查询用户所有信息
     */
    List<User> listBySearch(String key);

    /***
     * 根据账号查询用户信息
     */
    List<User> getByAccount(String account);


}
