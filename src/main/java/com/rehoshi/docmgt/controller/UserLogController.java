package com.rehoshi.docmgt.controller;

import com.rehoshi.docmgt.domain.RespData;
import com.rehoshi.docmgt.domain.entities.UserLog;
import com.rehoshi.docmgt.service.UserLogService;
import com.rehoshi.docmgt.service.UserService;
import com.rehoshi.stream.HStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/log")
public class UserLogController extends HoshiController {

    @Autowired
    private UserLogService userLogService ;
    @Autowired
    private UserService userService ;

    @GetMapping("/list/{pageIndex}/{pageSize}")
    public RespData<List<UserLog>> list(@PathVariable int pageIndex,@PathVariable int pageSize){
        return $(resp->{
            $page().index(pageIndex).size(pageSize) ;
            List<UserLog> list = userLogService.list();
            HStream.$(list).forEach(userLog -> {
                userLog.setUser(userService.getById(userLog.getUserId()));
            });
            resp.success(true).data(list).msg("查询成功") ;
        });
    }
}
