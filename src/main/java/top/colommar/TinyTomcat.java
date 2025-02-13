package top.colommar;

import java.io.*;
import java.net.*;
import java.util.*;

public class TinyTomcat {
    private int port = 8080;
    private Map<String, HttpServlet> servletMapping = new HashMap<>();

    public TinyTomcat(int port) {
        this.port = port;
    }

    public void addServlet(String path, HttpServlet servlet) {
        servletMapping.put(path, servlet);
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("TinyTomcat started on port " + port);

        while (true) {
            Socket client = serverSocket.accept();
            new Thread(() -> handleRequest(client)).start();
        }
    }

    private void handleRequest(Socket client) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(), true)
        ) {
            // 解析请求行
            String requestLine = in.readLine();
            if (requestLine == null || requestLine.isEmpty()) {
                return;
            }

            System.out.println("Request: " + requestLine);
            StringTokenizer tokenizer = new StringTokenizer(requestLine);
            String method = tokenizer.nextToken();
            String path = tokenizer.nextToken();

            // 处理 Servlet
            HttpServlet servlet = servletMapping.get(path);
            if (servlet != null) {
                HttpRequest request = new HttpRequest(method, in);
                HttpResponse response = new HttpResponse(out);

                if ("GET".equalsIgnoreCase(method)) {
                    servlet.doGet(request, response);
                } else if ("PUT".equalsIgnoreCase(method)) {
                    servlet.doPut(request, response);
                }
            } else {
                // 404 Not Found
                out.println("HTTP/1.1 404 Not Found");
                out.println("Content-Type: text/plain");
                out.println();
                out.println("404 Not Found: " + path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TinyTomcat server = new TinyTomcat(8080);
        server.addServlet("/hello", new HelloServlet());
        server.start();
    }
}

