package top.colommar;

import java.io.IOException;

public abstract class HttpServlet {
    public void doGet(HttpRequest request, HttpResponse response) throws IOException {
        response.send(405, "GET method not allowed");
    }

    public void doPut(HttpRequest request, HttpResponse response) throws IOException {
        response.send(405, "PUT method not allowed");
    }
}
