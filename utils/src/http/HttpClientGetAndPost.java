package http;

import com.sun.javafx.fxml.builder.URLBuilder;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class HttpClientGetAndPost {

    public static void main(String args[]) throws Exception {
        //doGet();
        doPost();
    }

    public static void doGet() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://www.baidu.com?query=花千骨");
        // 设置请求超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(1000)
                .setConnectTimeout(1000)
                .setSocketTimeout(1000).build();
        get.setConfig(requestConfig);
        get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        CloseableHttpResponse response = httpClient.execute(get);
        int code = response.getStatusLine().getStatusCode();
        System.out.println(code);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity, "UTF-8");
        System.out.println(content);
        response.close();
        httpClient.close();
    }

    public static void doPost() throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://targethost/login");
        CloseableHttpResponse response = null;
        try {
            // 设置请求超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(1000)
                    .setConnectTimeout(1000)
                    .setSocketTimeout(1000).build();
            //post.setConfig(requestConfig);
            post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("username", "vip"));
            params.add(new BasicNameValuePair("password", "secret"));
            post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            response = client.execute(post);
            int code = response.getStatusLine().getStatusCode();
            System.out.println(code);
            String res = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(res);
        }finally {
            response.close();
            client.close();
        }
    }
}
