package mang.demo.general.http;



import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import mang.util.common.JsonUtil;
import mang.util.demo.http.User;

/**
 * 从官网的quickStart改了下  用于练习post请求 该post请求可传json格式字符串
 * */
public class QuickStartPOST3 {

    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            HttpPost httpPost = new HttpPost("http://127.0.0.1:8083/index/post3.do");
            User user =new User();
            user.setName("hello");
            user.setDate(new Date());
            user.setId(0);
            
            String userStr=JsonUtil.obj2String(user);
            
            StringEntity stringEntity=new StringEntity(userStr);
            //注 如下2句很重要 否则有可能报415错误
            stringEntity.setContentType("application/json");
            stringEntity.setContentEncoding("UTF-8");
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
