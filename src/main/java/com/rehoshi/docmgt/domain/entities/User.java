package com.rehoshi.docmgt.domain.entities;

import java.util.Date;

public class User {

    /**
     * 用户角色
     */
    public interface Role{
        String ADMIN = "admin" ;
        String USER = "user" ;
    }

    /**
     * 唯一标识
     */
    private String id ;
    /**
     * 用户名称
     */
    private String name ;

    /**
     * 用户账号 用于登录
     */
    private String account ;

    /**
     * 用户密码
     */
    private String password ;

    /**
     * 用户角色
     */
    private String role = Role.USER;

    /**
     * 创建时间
     */
    private Date createTime ;
}
