package org.ligson.vertxserver;


import org.ligson.vertxserver.feign.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
@RestController
@SpringBootApplication
public class Proxy {


    private Lock lock = new Lock();

    @RequestMapping("/proxy")
    public String proxy() {
        System.out.println(Thread.currentThread().getName() + "======proxy");
        HttpUtil.get("http://127.0.0.1:8888/consumer");
        lock.lock();
        return lock.getData();
    }

    @RequestMapping("/callback")
    public String callback() {
        System.out.println(Thread.currentThread().getName() + "======callback");
        lock.setData("hello");
        lock.unlock();
        return "ok";
    }

    @PostMapping("/test")
    public String test(@RequestBody User user) {
        return "ok";
    }

    public static void main(String[] args) {
        SpringApplication.run(Proxy.class, args);
    }

}
