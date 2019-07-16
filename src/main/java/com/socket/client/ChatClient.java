package com.socket.client;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

/*
 * @ClassName
 * @Decription TOO
 * @Author HanniOvO
 * @Date 2019/7/16 15:52
 */
@Slf4j
public class ChatClient {

    public static void main(String[] args) throws IOException {
        String host = "192.168.0.103";
        int port = 8068;

        //与服务端建立连接
        Socket socket = new Socket(host, port);

        socket.setOOBInline(true);
        System.out.println(host);

        //建立连接后获取输出流
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        String uuid = UUID.randomUUID().toString();
        log.info("uuid: {}", uuid);
        outputStream.write(uuid.getBytes());

        log.info("host: {}",host);
        outputStream.write(host.getBytes());
        String content = "";
        while (true){
            byte[] buff = new byte[1024];
            inputStream.read(buff);
            String buffer = new String(buff, "utf-8");
            content += buffer;
            log.info("info: {}", buff);
            File file = new File("json.json");

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.flush();
        }
    }
}
