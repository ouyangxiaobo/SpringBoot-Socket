package com.socket;

import com.socket.server.SocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootSocketApplication {

    public static void main(String[] args) {



        ApplicationContext applicationContext = SpringApplication.run(SpringbootSocketApplication.class, args);
        applicationContext.getBean(SocketServer.class).start(8068);//在spring容器启动后，取到已经初始化的SocketServer，启动Socket服务
    }

}
