package mang.demo.general.http;



import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 从官网的quickStart改了下  用于练习post请求  注意其请求的controoler的参数写法不一样 @RequestBody String name
 * */
public class QuickStartPOST2 {

    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            HttpPost httpPost = new HttpPost("http://127.0.0.1:8083/index/post2.do");
            StringEntity stringEntity=new StringEntity("name");
            httpPost.setEntity(stringEntity);
            CloseableHttpResponse response2 = httpclient.execute(httpPost);

            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                
                String result = EntityUtils.toString(entity2);
                System.out.println(result);
                
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }
        } finally {
            httpclient.close();
        }
    }

}
