# tiny-tomcat
- 最小tomcat
- 这个 MyTomcat 主要模拟 Tomcat 的核心功能，包括 Servlet 的 请求分发 (dispatch) 以及 service/doGet/doPost 逻辑。

# step
1. 服务器端口监听：使用 ServerSocket 监听 HTTP 请求。
2. 请求解析：封装 MyRequest 解析 HTTP 请求（包括 GET 和 POST）。
3. 响应封装：封装 MyResponse，构造 HTTP 响应。
4. 请求分发：dispatch() 方法解析 URL，找到对应 Servlet 并调用 service()。
5. Servlet 生命周期：利用 Java 反射动态加载 Servlet 处理请求。

| **Tomcat 组件** | **MyTomcat 对应实现** | **功能描述** |
|--------------|----------------|----------------|
| **`ServerSocket`** | `new ServerSocket(port)` | 监听端口，等待 HTTP 请求 |
| **`Connector`** | `Socket client = serverSocket.accept()` | 接收 HTTP 连接 |
| **`Request`** | `MyRequest` | 解析 HTTP 请求 |
| **`Response`** | `MyResponse` | 构造 HTTP 响应 |
| **`Servlet`** | `MyServlet` | 处理业务逻辑 |
| **`Dispatcher`** | `dispatch(request, response)` | 查找 `Servlet` 并调用 `service()` |
| **`Web.xml`** | `servletMapping`（`Map<String, String>`） | 用 `Map` 代替 `web.xml` 配置 |

