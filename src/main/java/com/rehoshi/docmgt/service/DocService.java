package com.rehoshi.docmgt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rehoshi.docmgt.domain.entities.Doc;

import java.util.List;

public interface DocService extends IService<Doc> {
    /**
     * 根据分类获取Doc
     */
    List<Doc> listByCategory(String category);

    /**
     * 根据搜索关键字查询
     *
     * @return
     */
    List<Doc> listBySearch(String key);

    List<Doc> listRecommend();
}
