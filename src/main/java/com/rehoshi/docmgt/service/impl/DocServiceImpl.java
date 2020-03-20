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

    @Override
    public List<Doc> listByCategory(String category) {
        List<Doc> docList ;
        QueryWrapper<Doc> wrapper = new QueryWrapper<>() ;
        wrapper.eq("category", category) ;
        docList = page(getPage(), wrapper).getRecords();
        return docList;
    }

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
}
