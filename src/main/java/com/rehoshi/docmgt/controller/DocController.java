package com.rehoshi.docmgt.controller;

import com.rehoshi.docmgt.config.PageConfig;
import com.rehoshi.docmgt.domain.RespData;
import com.rehoshi.docmgt.domain.entities.Doc;
import com.rehoshi.docmgt.service.DocService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController extends HoshiController {

    @Autowired
    private DocService docService;
    @Autowired
    private PageConfig pageConfig;

    @GetMapping("/list/{pageIndex}/{pageSize}")
    public RespData<List<Doc>> list(@PathVariable Integer pageIndex,
                                    @PathVariable Integer pageSize,
                                    @RequestParam(required = false, defaultValue = "") String searchKey) {
        pageConfig.index(pageIndex).size(pageSize);
        return $(listRespData -> {
            listRespData.success(true).data(docService.listBySearch(searchKey));
        });
    }

    @GetMapping("/list/{category}/{pageIndex}/{pageSize}")
    public RespData<List<Doc>> listByCategory(@PathVariable String category,
                                              @PathVariable Integer pageIndex,
                                              @PathVariable Integer pageSize) {
        pageConfig.index(pageIndex).size(pageSize);
        return $(listRespData -> {
            listRespData.success(true).data(docService.listByCategory(category)) ;
        });
    }
}
