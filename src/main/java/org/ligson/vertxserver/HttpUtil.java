package org.ligson.vertxserver;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class HttpUtil {
    public static void get(String url) {
        RestTemplate template = new RestTemplateBuilder().build();
        /*CloseableHttpClient client = HttpClientBuilder.create().build();
        System.out.println(url);
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse res = client.execute(httpGet);
            System.out.println(EntityUtils.toString(res.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        String msg = template.getForObject(url, String.class);
        System.out.println(msg);
    }
}
