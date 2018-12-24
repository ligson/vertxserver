package org.ligson.vertxserver;


import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Bg extends AbstractVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) throws Exception {
        Runner.runExample(Bg.class);
    }

    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);

        router.route().handler(routingContext -> {
            System.out.println(Thread.currentThread().getName()+"======bg");
            System.out.println(routingContext.request().getParam("n"));
            routingContext.response().putHeader("content-type", "text/html").end("Hello World!");
            Invoke.get("http://127.0.0.1:8080/consumer");
        });

        vertx.createHttpServer().requestHandler(router).listen(7777);
    }
}
