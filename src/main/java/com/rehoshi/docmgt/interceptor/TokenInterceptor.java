package com.rehoshi.docmgt.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rehoshi.docmgt.auth.TokenGenerator;
import com.rehoshi.docmgt.domain.RespData;
import com.rehoshi.docmgt.domain.entities.User;
import com.rehoshi.docmgt.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Objects;

public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService ;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头参数
        String token = request.getHeader("token'");
        if(StringUtils.isEmpty(token)){
            //如果请求头没有 获取请求体参数
            token = request.getParameter("token") ;
        }

        if(!StringUtils.isEmpty(token)){
            //拆分凭证
            String[] strings = TokenGenerator.splitToken(token);
            String userId = strings[0] ;
            long expireTime = Long.parseLong(strings[1]) ;
            long curTime = Calendar.getInstance().getTimeInMillis();
            //判断是否过期
            if(curTime < expireTime){
                User byId = userService.getById(userId);
                //比对token是否一致
                if(byId != null && Objects.equals(byId.getToken(), token)){
                    //放行继续业务
                    return true ;
                }
            }
        }
        //返回登录失败
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String json = new ObjectMapper().writeValueAsString(RespData.failed().msg("登录验证过期，请重新登录"));
        response.getWriter().write(json);
        return false;
    }
}
