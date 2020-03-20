package com.rehoshi.docmgt.domain.entities;

import com.baomidou.mybatisplus.annotation.TableField;

import java.security.Policy;
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
    private Date createTime;
}
