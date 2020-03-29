package com.rehoshi.docmgt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rehoshi.docmgt.dao.DocDao;
import com.rehoshi.docmgt.domain.entities.Doc;
import com.rehoshi.docmgt.service.DocService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static final Map<String, String> category = new HashMap<>() ;
    static {
        category.put("新闻", Doc.Category.NEWS) ;
        category.put("政策", Doc.Category.POLICY) ;
        category.put("养老金", Doc.Category.PENSION) ;
        category.put("其他", Doc.Category.OTHERS) ;
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
        wrapper.like("content", key)
                .or().like("title", key)
                .or().like("tag", key);
        if(category.containsKey(key)){
            wrapper.or().eq("category", category.get(key));
        }
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
        queryWrapper.orderByDesc("create_time");
        listDoc = page(getPage(),queryWrapper).getRecords();
        return listDoc;
    }
}
