package com.socket.server;

import com.socket.client.ClientSocket;
import com.socket.handler.SocketHandler;
import com.socket.model.User;
import com.socket.service.UserService;
import com.socket.utils.JSONUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.socket.handler.SocketHandler.register;

/*
 * @ClassName
 * @Decription TOO
 * @Author HanniOvO
 * @Date 2019/7/16 15:17
 */
@Data
@Slf4j
@NoArgsConstructor
@Component
@PropertySource("classpath:socket.properties")
public class SocketServer {

    @Value("${socket.port}")
    private Integer port;

    private Boolean started;

    private ServerSocket serverSocket;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args){
        new SocketServer().start(8068);
    }

    @Autowired
    private UserService userService;//测试使用


    //服务端启动
    public void start(Integer port){
        log.info("port: {}, {}", this.port, port);
        try {
            serverSocket =  new ServerSocket(port == null ? this.port : port);
            started = true;
            log.info("Socket服务已启动，占用端口： {}", serverSocket.getLocalPort());
        } catch (IOException e) {
            log.error("端口冲突,异常信息：{}", e);
            System.exit(0);
        }

        while (started){
            try {
                //
                Socket socket = serverSocket.accept();
                socket.setKeepAlive(true);
                ClientSocket register = register(socket);
                log.info("客户端已连接，其Key值为：{}", register.getKey());
                List<User> list = userService.queryAllUsersList();
                for (User user: list){
                    System.out.println("用户=="+user.toString());
                }
                SocketHandler.sendMessage(register, JSONUtil.toJSONString(list));
                if (register != null){
                    executorService.submit(register);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
