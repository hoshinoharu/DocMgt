package com.rehoshi.docmgt.controller;

import com.rehoshi.docmgt.domain.RespData;

import java.util.function.Consumer;

public class HoshiController {

    protected <T>RespData<T> $(Consumer<RespData<T>> consumer){
        RespData<T> respData = RespData.failed(null) ;
        try {
            consumer.accept(respData);
        }catch (Exception e){
            e.printStackTrace();
            respData.success(false).setDesc(e.getMessage()); ;
        }
        return respData ;
    }
}
