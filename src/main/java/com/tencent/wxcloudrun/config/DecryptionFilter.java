package com.tencent.wxcloudrun.config;

import com.tencent.wxcloudrun.service.DecryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DecryptionFilter implements Filter {

    @Autowired
    private DecryptionService decryptionService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 检查是否需要解密（可根据路径、头信息等判断）
        if (shouldDecrypt(httpRequest)) {
            // 包装请求，进行解密处理
            DecryptionRequestWrapper wrappedRequest =
                    new DecryptionRequestWrapper(httpRequest, decryptionService);
            chain.doFilter(wrappedRequest, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean shouldDecrypt(HttpServletRequest request) {
        // 根据业务逻辑判断是否需要解密
        return request.getHeader("Encrypted") != null
                || request.getRequestURI().contains("/secure/");
    }
}
