package com.example.demo.aop;

import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Objects;

@ControllerAdvice
public class RequestBodyEncryptAdvice implements RequestBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage message, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        if(Objects.requireNonNull(parameter.getMethod()).isAnnotationPresent(Security.class)){
            Security security = parameter.getMethodAnnotation(Security.class);
            boolean decrypt = security.decrypt();
            if(decrypt){
                return new RequestBodyEncryptAdvice.SecurityMessage(message);
            }

        }
        return message;
    }

   static class SecurityMessage implements HttpInputMessage {

        private HttpHeaders headers;
        private InputStream body;

        SecurityMessage(HttpInputMessage message)throws IOException{
            this.headers=message.getHeaders();
            String content = IOUtils.toString(message.getBody(), Charset.defaultCharset());
            this.body=IOUtils.toInputStream(content,Charset.defaultCharset());
            System.out.println("=============================");

        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
