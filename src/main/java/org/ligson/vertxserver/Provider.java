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
public class Provider extends AbstractVerticle {


    // Convenience method so you can run it in your IDE
    public static void main(String[] args) throws Exception {
        Runner.runExample(Provider.class);
    }

    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);

        router.route(HttpMethod.GET, "/provider").handler(routingContext -> {
            System.out.println(Thread.currentThread().getName() + "======provider");
            System.out.println(routingContext.request().getParam("n"));
            routingContext.response().putHeader("content-type", "text/html").end("Hello World!");
            new Thread(() -> {
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("========callback---");
                HttpUtil.get("http://127.0.0.1:7777/callback");
            }).start();

        });
        vertx.createHttpServer().requestHandler(router).listen(9999);
    }
}
