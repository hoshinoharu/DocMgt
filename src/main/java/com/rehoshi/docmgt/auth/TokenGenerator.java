package com.rehoshi.docmgt.auth;

import com.rehoshi.docmgt.domain.entities.User;

import java.util.Base64;
import java.util.Calendar;

public class TokenGenerator {

    public static String generateToken(User user){
        //获取用户id
        String id = user.getId();
        Calendar instance = Calendar.getInstance() ;
        instance.add(Calendar.MONTH, 1);
        //获取当前时间一个月后的时间戳，即设置过期时间为一个月
        long timeInMillis = instance.getTimeInMillis();
        //拼接token
        String token = String.format("%s|%s", id, timeInMillis) ;
        //base64加密token
        token = Base64.getEncoder().encodeToString(token.getBytes()) ;
        return token ;
    }

    public static String[] splitToken(String encodeToken){
        //解密base64字符串
        byte[] decode = Base64.getDecoder().decode(encodeToken);
        //重新构建String
        String s = new String(decode);

        String[] split = s.split("\\|");
        return split ;
    }
}
