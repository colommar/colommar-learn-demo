package top.colommar;

import java.io.*;

public class HttpResponse {
    private PrintWriter out;

    public HttpResponse(PrintWriter out) {
        this.out = out;
    }

    public void send(int statusCode, String body) {
        String statusText = switch (statusCode) {
            case 200 -> "OK";
            case 404 -> "Not Found";
            case 405 -> "Method Not Allowed";
            default -> "Error";
        };

        out.println("HTTP/1.1 " + statusCode + " " + statusText);
        out.println("Content-Type: text/plain");
        out.println("Content-Length: " + body.length());
        out.println();
        out.println(body);
    }
}

