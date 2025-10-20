package com.tencent.wxcloudrun.config;

import com.tencent.wxcloudrun.service.DecryptionService;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class DecryptionRequestWrapper extends HttpServletRequestWrapper {
    private final byte[] decryptedBody;
    private final DecryptionService decryptionService;

    public DecryptionRequestWrapper(HttpServletRequest request,
                                    DecryptionService decryptionService) throws IOException {
        super(request);
        this.decryptionService = decryptionService;
        this.decryptedBody = decryptRequestBody(request);
    }

    private byte[] decryptRequestBody(HttpServletRequest request) throws IOException {
        String encryptedData = request.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));

        // 调用解密服务
        String decryptedData = decryptionService.decrypt(encryptedData);
        return decryptedData.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public ServletInputStream getInputStream() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decryptedBody);
        return new ServletInputStream() {
            @Override
            public int read() {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return byteArrayInputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener listener) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}
