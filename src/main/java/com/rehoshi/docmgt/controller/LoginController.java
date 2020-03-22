package com.rehoshi.docmgt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rehoshi.docmgt.domain.RespData;
import com.rehoshi.docmgt.domain.entities.User;
import com.rehoshi.docmgt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/login/")
/**
 *  功能：登入登出，返回json格式
 *  方法：loginIn(),loginOut()
 *  作者：
 *  日期：2020.3.20
 */
public class LoginController {
    @Autowired
    private UserService userService;


    /***
     * 功能：登入，根据用户名，密码登录
     * 参数：
     * @Author:
     * @data:2020.3.20
     */


    /*public RespData<List<User>> loginIn(@RequestParam(required = true,defaultValue = "account") String account, String password){
        return $(RespData);
       *//* QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",user.getAccount());
        queryWrapper.eq("password",user.getPassword());
        u = userService.getOne(queryWrapper);
        if(u != null){
            session.setAttribute("user",u);
        }else if((!u.getAccount().equals(user.getAccount())) && (!u.getPassword().equals(user.getPassword()))){
            model.addAttribute("error","用户名或密码错误");
            return "loginIn";
        }else{
            model.addAttribute("error","用户名或密码不能为空！");
            return "loginIn";
        }
        return null;*//*
    }
*/
    /***
     * 功能：登出
     *
     * @param session
     * @return
     * @Author:
     * @Date:2020.3.20
     */
   /* public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return null;
    }*/

}
