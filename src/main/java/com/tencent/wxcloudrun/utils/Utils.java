package com.tencent.wxcloudrun.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
@Component
public class Utils {

    public void printHead(HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder sb = new StringBuilder("请求头列表：\n");
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            // 根据头名称获取对应的值（可能有多个值，用逗号分隔）
            String headerValue = request.getHeader(headerName);
            sb.append(headerName).append(": ").append(headerValue).append("\n");
        }
        log.info("" + sb.toString());
    }
}
