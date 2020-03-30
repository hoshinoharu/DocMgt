package com.rehoshi.docmgt.controller;

import com.rehoshi.docmgt.domain.RespData;
import com.rehoshi.docmgt.domain.entities.Doc;
import com.rehoshi.docmgt.domain.entities.User;
import com.rehoshi.docmgt.domain.entities.UserLog;
import com.rehoshi.docmgt.service.DocService;
import com.rehoshi.docmgt.service.UserLogService;
import com.rehoshi.docmgt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController extends HoshiController {

    @Autowired
    private DocService docService;
    @Autowired
    private UserService userService ;
    @Autowired
    private UserLogService userLogService ;
    /**
     * 加载文档
     *
     * @param doc
     * @return 返回一个ID，检验是否加载成功
     * @author:SQY
     * @date:2020.3.25
     */
    @PostMapping("/add")
    public RespData<Boolean> add(@RequestBody Doc doc) {
        return $(resp -> {
            doc.setContent(FileController.analysis(doc.getDocUrl()));
            docService.save(doc);
            if (doc.getId() == null) {
                resp.success(false).data(false).msg("加载失败，");
            } else {
                resp.success(true).data(true).msg("加载成功");
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
        return $(resp -> {
            if (id == null) {
                resp.success(false).data(true).msg("ID为空删除失败");
            } else {
                docService.removeById(id);
                resp.success(true).data(true).msg("删除成功");
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
    public RespData<Boolean> update(@RequestBody Doc doc) {
        return $(booleanRespData -> {
            if (doc.getId() == null) {
                booleanRespData.success(false).msg("未能获取更新ID");
            } else {
                doc.setContent(FileController.analysis(doc.getDocUrl()));
                docService.updateById(doc);
                booleanRespData.success(true).data(true).msg("修改成功") ;
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
    public RespData<List<Doc>> list(@RequestParam("key") String key, @PathVariable int pageIndex, @PathVariable int pageSize,
                                    @RequestAttribute("curUser") User user) {
        return $(resp -> {
            if(pageIndex == 1){
                UserLog userLog = new UserLog();
                userLog.setSearchTime(new Date());
                userLog.setUserId(user.getId());
                userLog.setSearchContent(key);
                userLogService.save(userLog) ;
            }
            $page().index(pageIndex).size(pageSize);
            List<Doc> docList = docService.listBySearch(key);
            resp.success(true).data(attachUser(docList)) ;
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
            listRespData.success(true).data(attachUser(docList))  ;
        });
    }

    public RespData<List<Doc>> listCategory(){
        return $(resp->{

        }) ;
    }


    private Doc attachUser(Doc doc){
        if(doc.getCreatorId() != null){
            User byId = userService.getById(doc.getCreatorId());
            doc.setCreator(byId);
        }
        return doc ;
    }

    private List<Doc> attachUser(List<Doc> docList){
        if(docList != null){
            docList.forEach(this::attachUser);
        }
        return docList ;
    }


}
