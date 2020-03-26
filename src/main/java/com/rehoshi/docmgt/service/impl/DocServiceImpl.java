package com.rehoshi.docmgt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rehoshi.docmgt.config.PageConfig;
import com.rehoshi.docmgt.dao.DocDao;
import com.rehoshi.docmgt.domain.entities.Doc;
import com.rehoshi.docmgt.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocServiceImpl extends HoshiService<DocDao, Doc> implements DocService {
    /**
     * 根据分类查询
     * @param category
     * @return
     */
    @Override
    public List<Doc> listByCategory(String category) {
        List<Doc> docList ;
        QueryWrapper<Doc> wrapper = new QueryWrapper<>() ;
        wrapper.eq("category", category) ;
        docList = page(getPage(), wrapper).getRecords();
        return docList;
    }

    /**
     * 根据关键字查询
     * @param key
     * @return
     */
    @Override
    public List<Doc> listBySearch(String key) {
        List<Doc> docList ;
        QueryWrapper<Doc> wrapper = new QueryWrapper<>() ;
        wrapper.like("content", "%"+key+"%")
                .or().like("title", "%"+key+"%")
                .or().like("tag", "%"+key+"%");
        docList = page(getPage(), wrapper).getRecords();
        return docList;
    }

    /**
     * 根据日期倒排序，推荐最近上传的十条
     * @return
     * @author：SQY
     * @date:2020.3.25
     *
     */
    @Override
    public List<Doc> listRecommend() {
        List<Doc> listDoc;
        QueryWrapper<Doc> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("createTime");
        listDoc = page(getPage(),queryWrapper).getRecords();
        return listDoc;
    }
}
