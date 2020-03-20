package com.rehoshi.docmgt.aop;

import com.rehoshi.docmgt.domain.RespData;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@ControllerAdvice
public class DroolsInterceptor implements ResponseBodyAdvice<RespData> {
    @Autowired
    private KieSession kieSession ;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * 规则引擎拦截器
     * 拦截controller返回值
     * @return
     */
    @Override
    public RespData beforeBodyWrite(RespData respData, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        kieSession.insert(respData) ;
        kieSession.fireAllRules();
        return respData;
    }
}
