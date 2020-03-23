package com.rehoshi.docmgt.domain;

import com.rehoshi.docmgt.common.BeanFactory;
import org.kie.api.runtime.KieSession;

public class RespData<T> {

    public interface Code{
        int SUCCESS = 200 ;
        int SERVER_ERROR = 500 ;
        int TOKEN_TIME_OUT = 1000 ;
    }

    private Integer code;
    private Boolean success;
    private T data;
    private String msg;
    private String desc ;

    public RespData() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public RespData<T> desc(String desc){
        setDesc(desc);
        return this ;
    }

    public RespData<T> code(Integer code) {
        setCode(code);
        return this;
    }

    public RespData<T> success(Boolean success) {
        setSuccess(success);
        return this;
    }

    public RespData<T> data(T data) {
        setData(data);
        return this;
    }

    public RespData<T> msg(String msg){
        setMsg(msg);
        return this ;
    }

    public static<T> RespData<T> succeed(T data){
        return new RespData<T>().data(data).success(true) ;
    }

    public static RespData<Boolean> succeed(){
        return succeed(true) ;
    }


    public static <T> RespData<T> failed(T data){
        return new RespData<T>().data(data).success(false) ;
    }

    public static RespData<Boolean> failed(){
        return failed(false) ;
    }
}
