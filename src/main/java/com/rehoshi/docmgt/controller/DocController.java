package com.rehoshi.docmgt.controller;

import com.rehoshi.docmgt.config.PageConfig;
import com.rehoshi.docmgt.domain.RespData;
import com.rehoshi.docmgt.domain.entities.Doc;
import com.rehoshi.docmgt.service.DocService;
import com.rehoshi.docmgt.service.UserService;
import com.rehoshi.docmgt.service.impl.UserServiceImpl;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController extends HoshiController{

    @Autowired
    private DocService docService;

    @GetMapping("/list")
    public RespData<Boolean> list(){
        return $(resp->{
            List<Doc> docList = docService.listBySearch("");
        });
    }

}
