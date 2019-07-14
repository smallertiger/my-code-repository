package http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetAndPost {


    public static void main(String args[]) throws Exception{

        //getWhitoutHttpClient();
        postWhitoutHttpClient();
    }


    /**
     * 使用 java.net 包完成一个 HTTP GET 请求的一般流程
     *
     * 1.使用 java.net.URL 类创建一个 URL 类的实例 ，参数为要请求的网址
     * 2.调用 URL 实例的 openConnection() 方法打开一个到远程服务器的连接 conection
     * 3.然后对连接 conection 设置一些属性，比如调用 setRequestMethod() 设置请求方法，调用 setRequestProperty() 添加一些查询参数
     * 4.设置完了属性之后，就可以调用连接的 getResponseCode() 发起请求并返回请求的响应状态码
     * 5.判断返回的响应状态吗，如果为 200 则表示成功，然后就可以调用连接的 getInputStream() 获取输入流
     * 6.有了输入流之后，就可以使用 java.io 包中的相关类读取响应的数据
     * 7.其它的对响应数据的处理逻辑
     */
    public static void getWhitoutHttpClient() throws Exception {
        // 1. 创建 URL 实例
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");

        // 2. 打开到远程服务器的连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 3. 设置连接属性，比如请求方法和请求参数
        connection.setRequestMethod("GET");
        connection.setRequestProperty("userId", "2");

        // 4. 发起请求并获取响应的状态码
        int responseCode = connection.getResponseCode();

        // 5. 根据状态码作出一些判断，如果为 200 则表示成功
        String readLine = null;
        if(responseCode == HttpURLConnection.HTTP_OK) {
            // 6. 使用 getInputStream() 获取输入流并读取输入流里的数据
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // 7. 其它处理逻辑，这里直接输出响应的数据
            StringBuffer sb = new StringBuffer();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            System.out.println("JSON String Result" + sb.toString());
        }else {
            System.out.println("GET NOT WORKED");
        }
    }

    /**
     * HTTP GET 请求和 HTTP POST 请求的区别，注意，我们说的是 HTTP 方面
     *
     * GET 请求的请求方法是 GET 、POST 的请求方法是 POST - 这是最大的差别了，也是唯一可以说的差别
     * GET 和 POST 请求都可以把请求参数放到 URL 中，但是大小受 URL 大小限制，而 POST 请求还可以把请求参数放到请求正文中
     *
     * HTTP POST 请求的一般流程了
     *
     * 使用 java.net.URL 类创建一个 URL 类的实例 urlForGetRequest ，参数为要请求的网址
     * 调用 URL 实例的 openConnection() 方法打开一个到远程服务器的连接 conection
     * 然后对连接 conection 设置一些属性，比如调用 setRequestMethod() 设置请求方法，调用 setRequestProperty() 添加一些查询参数
     * 调用连接的 setDoOutput(true) 方法设置该请求有请求正文
     * 接着调用连接的 getOutputStream() 方法打开正文输出流 os
     * 之后调用 os.write() 写入输出数据，调用 os.flush() 刷新输出缓存和调用 os.close() 关闭输出流
     * 设置完了属性之后，就可以调用连接的 getResponseCode() 发起请求并返回请求的响应状态码
     * 判断返回的响应状态吗，如果为 200 则表示成功，然后就可以调用连接的 getInputStream() 获取输入流
     * 有了输入流之后，就可以使用 java.io 包中的相关类读取响应的数据
     * 其它的对响应数据的处理逻辑
     */
    public static void postWhitoutHttpClient() throws Exception {
        // 要提交的数据
        final String POST_PARAMS = "{\n" + "\"userId\": 101,\r\n" +
                "    \"id\": 101,\r\n" +
                "    \"title\": \"Test Title\",\r\n" +
                "    \"body\": \"Test Body\"" + "\n}";

        // 1. 创建 URL 实例
        URL obj = new URL("https://jsonplaceholder.typicode.com/posts");

        // 2. 打开到远程服务器的连接
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();

        // 3. 设置一些属性，比如设置请求方法和请求参数
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("userId", "a1bcdefgh");

        // 因为提交的是 JSON 数据，所以需要设置请求类型
        postConnection.setRequestProperty("Content-Type", "application/json");

        // 4. 设置该请求有请求正文
        postConnection.setDoOutput(true);

        // 5. 打开请求正文输出流
        OutputStream os = postConnection.getOutputStream();

        // 6. 写入输出数据，刷新缓存，关闭输出流
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();

        // 7. 发起请求并获取请求响应状态码
        int responseCode = postConnection.getResponseCode();
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());

        // 8. 判断请求状态响应码，如果为 201 则表示成功创建，因为服务器返回 201 ,其实返回 200 也是可以的
        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            // 9. 对响应数据的处理
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            System.out.println(response.toString());
        } else {
            System.out.println("POST NOT WORKED");
        }
    }

}
