package com.rehoshi.docmgt.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.rehoshi.docmgt.config.PageConfig;
import com.rehoshi.docmgt.domain.RespData;
import com.rehoshi.docmgt.domain.entities.Doc;
import com.rehoshi.docmgt.domain.entities.User;
import com.rehoshi.docmgt.service.DocService;
import com.rehoshi.docmgt.service.UserService;
import com.rehoshi.docmgt.service.impl.UserServiceImpl;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController extends HoshiController {

    @Autowired
    private DocService docService;


    /**
     * 加载文档
     *
     * @param doc
     * @return 返回一个ID，检验是否加载成功
     * @author:SQY
     * @date:2020.3.25
     */
    @PostMapping("/load")
    public RespData<Boolean> load(Doc doc) {
        return $(booleanRespData -> {
            docService.save(doc);
            if (doc.getId() == null) {
                RespData.succeed(false).msg("加载失败，");
            } else {
                RespData.succeed(true).msg("加载成功");
            }
        });
    }

    /**
     * 根据ID删除文档
     *
     * @param id
     * @return
     * @author:SQY
     * @date:2020.3.25
     */
    @DeleteMapping("/del/{id}")
    public RespData<Boolean> del(@PathVariable String id) {
        return $(booleanRespData -> {
            if (id == null) {
                RespData.succeed(false).msg("ID为空删除失败");
            } else {
                docService.removeById(id);
                RespData.succeed(true).msg("删除成功");
            }
        });
    }

    /**
     * 根据ID更改
     *
     * @param doc
     * @return
     * @author:SQY date:2020.3.25
     */
    @PutMapping("/update")
    public RespData<Boolean> update(Doc doc) {
        return $(booleanRespData -> {
            if (doc.getId() == null) {
                booleanRespData.success(false).msg("未能获取更新ID");
            } else {
                docService.updateById(doc);
            }
        });
    }

    /**
     * 根据关键字查询
     *
     * @return
     * @author:SQY
     * @date:2020.3.25
     */
    @GetMapping("/list/{pageIndex}/{pageSize}")
    public RespData<List<Doc>> list(String key, @PathVariable int pageIndex, @PathVariable int pageSize) {
        return $(resp -> {
            $page().index(pageIndex).size(pageSize);
            List<Doc> docList = docService.listBySearch(key);
            resp.success(true).data(docList) ;
        });
    }

    /**
     * 推荐最近上传
     * @param pageIndex
     * @param pageSize
     * @return
     * @author:SQY
     * @date:2020.3.25
     */
    @GetMapping("/list/recommend/{pageIndex}/{pageSize}")
    public RespData<List<Doc>> listRecommend(@PathVariable int pageIndex, @PathVariable int pageSize) {
        return $(listRespData -> {
            $page().index(pageIndex).size(pageSize);
            List<Doc> docList = docService.listRecommend();
            listRespData.success(true).data(docList)  ;
        });
    }

    public RespData<List<Doc>> listCategory(){
        return $(resp->{

        }) ;
    }

}
