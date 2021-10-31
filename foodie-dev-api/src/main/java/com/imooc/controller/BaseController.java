package com.imooc.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujinqiang
 * @create 2021-10-14 10:30
 */
public class BaseController {
    public static final Integer COOKIE_DELETE = 0;
    public static final String FOODIE_SHOPCART = "shopcart";


    public Map<String, String> getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<>();
        List<FieldError> errorList = result.getFieldErrors();
        for (FieldError error : errorList) {
            // 发送验证错误的时候所对应的某个属性
            String field = error.getField();
            // 验证的错误消息
            String msg = error.getDefaultMessage();
            map.put(field, msg);
        }
        return map;
    }


    public void setCookie(HttpServletRequest request,
                          HttpServletResponse response,
                          String cookieName,
                          String cookieValue,
                          Integer maxAge) {
        try {
            cookieValue = URLEncoder.encode(cookieValue, "UTF-8");
            setCookieValue(request, response, cookieName, cookieValue, maxAge);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void setCookieValue(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, Integer maxAge) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(maxAge);
//        cookie.setDomain("foodie-dev.com");
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void deleteCookie(HttpServletRequest request,
                             HttpServletResponse response,
                             String cookieName) {
        try {
            String deleteValue = URLEncoder.encode("", "utf-8");
            setCookieValue(request, response, cookieName, deleteValue, COOKIE_DELETE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
