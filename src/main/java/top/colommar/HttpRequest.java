package top.colommar;

import java.io.*;
import java.util.*;

public class HttpRequest {
    private String method;
    private Map<String, String> headers = new HashMap<>();
    private String body;

    public HttpRequest(String method, BufferedReader in) throws IOException {
        this.method = method;
        String line;

        // 读取 HTTP 头部
        while ((line = in.readLine()) != null && !line.isEmpty()) {
            String[] parts = line.split(": ", 2);
            if (parts.length == 2) {
                headers.put(parts[0], parts[1]);
            }
        }

        // 读取请求体
        if ("PUT".equalsIgnoreCase(method)) {
            int contentLength = Integer.parseInt(headers.getOrDefault("Content-Length", "0"));
            char[] bodyChars = new char[contentLength];
            in.read(bodyChars);
            this.body = new String(bodyChars);
        }
    }

    public String getMethod() {
        return method;
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    public String getBody() {
        return body;
    }
}

