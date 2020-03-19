package com.rehoshi.docmgt.controller;

import com.rehoshi.docmgt.domain.RespData;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doc")
public class DocController {

    @GetMapping("/list")
    public RespData<Boolean> list(){
        RespData<Boolean> succeed = RespData.succeed();
        return  succeed;
    }
}
