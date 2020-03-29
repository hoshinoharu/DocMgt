package com.rehoshi.docmgt.domain.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rehoshi.docmgt.json.SpDateDeSerializer;
import com.rehoshi.docmgt.json.SpDateSerializer;

import java.util.Date;

public class Doc {

    /**
     * 分类
     */
    public interface Category{
        //政策
        String POLICY = "policy" ;
        //养老金
        String PENSION = "pension" ;
        //报道
        String NEWS = "news" ;
        //其他
        String OTHERS = "others" ;
    }

    /**
     * 文档id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id ;
    /**
     * 标题
     */
    private String title ;
    /**
     * 文档内容缓存
     */
    private String content ;
    /**
     * 标签 按照，分割
     */
    private String tag ;

    /**
     * 文档分类 默认是政策文档
     */
    private String category = Category.POLICY ;

    /**
     * 创建人
     */
    private String creatorId ;
    @TableField(exist = false)
    private User creator ;

    /**
     * 创建时间
     */
    @JsonDeserialize(using = SpDateDeSerializer.class)
    @JsonSerialize(using = SpDateSerializer.class)
    private Date createTime;

    /**
     * 文档 Url
     *
     */
    private  String docUrl;

    /**
     * 文件的原本路径
     */
    private String originPath ;


    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOriginPath() {
        return originPath;
    }

    public void setOriginPath(String originPath) {
        this.originPath = originPath;
    }
}
