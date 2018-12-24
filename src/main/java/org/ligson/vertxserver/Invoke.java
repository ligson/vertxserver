package org.ligson.vertxserver;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Invoke extends AbstractVerticle {

    public static void get(String url) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        System.out.println(url);
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse res = client.execute(httpGet);
            System.out.println(EntityUtils.toString(res.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) throws Exception {
        Runner.runExample(Invoke.class);
    }

    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);

        router.route(HttpMethod.GET, "/provider").handler(routingContext -> {
            System.out.println(Thread.currentThread().getName() + "======provider");
            System.out.println(routingContext.request().getParam("n"));
            routingContext.response().putHeader("content-type", "text/html").end("Hello World!");
            get("http://127.0.0.1:7777/bg");
        });

        router.route(HttpMethod.GET, "/consumer").handler(routingContext -> {
            System.out.println(Thread.currentThread().getName() + "======consumer");
            System.out.println(routingContext.request().getParam("n"));
            routingContext.response().putHeader("content-type", "text/html").end("Hello World!");
            //get("http://127.0.0.1:7777/bg");
            System.out.println("ok.....");
            for (int i = 0; i < 100000; i++) {

            }
        });

        vertx.createHttpServer().requestHandler(router).listen(8080);
    }
}
