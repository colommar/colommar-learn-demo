package top.colommar;

import java.io.*;

public class HelloServlet extends HttpServlet {
    @Override
    public void doGet(HttpRequest request, HttpResponse response) throws IOException {
        response.send(200, "Hello, TinyTomcat!");
    }

    @Override
    public void doPut(HttpRequest request, HttpResponse response) throws IOException {
        response.send(200, "Received PUT request: " + request.getBody());
    }
}
