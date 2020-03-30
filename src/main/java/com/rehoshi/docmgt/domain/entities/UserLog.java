package com.rehoshi.docmgt.domain.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rehoshi.docmgt.json.SpDateDeSerializer;
import com.rehoshi.docmgt.json.SpDateSerializer;

import java.util.Date;

public class UserLog {
    /**
     * 操作ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id ;
    /**
     * 用户ID
     */
    private String userId ;
    @TableField(exist = false)
    private User user ;

    /**
     * 查询内容
     */
    private String searchContent ;

    /**
     * 查询时间
     */
    @JsonDeserialize(using = SpDateDeSerializer.class)
    @JsonSerialize(using = SpDateSerializer.class)
    private Date searchTime ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public Date getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(Date searchTime) {
        this.searchTime = searchTime;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
