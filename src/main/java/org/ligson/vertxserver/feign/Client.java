package org.ligson.vertxserver.feign;

import feign.Feign;
import feign.Request;
import feign.Response;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

import java.io.IOException;

public class Client {
    public static class MyClient extends feign.Client.Default {

        public MyClient() {
            super(null, null);
        }

        @Override
        public Response execute(Request request, Request.Options options) throws IOException {
            return super.execute(request, options);
        }
    }

    public static void main(String[] args) {
        MyService myService = Feign.builder().options(new Request.Options(60000, 60000))
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(MyService.class, "http://localhost:7777");
        System.out.println(myService.userInfo(new User()));
    }
}
