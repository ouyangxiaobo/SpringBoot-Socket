package com.socket.server;

import com.socket.client.ClientSocket;

import java.util.concurrent.ConcurrentHashMap;

/*
 * @ClassName
 * @Decription TOO socket连接池
 * @Author HanniOvO
 * @Date 2019/7/16 15:21
 */
public class SocketPool {

    private static final ConcurrentHashMap<String, ClientSocket> ONLINE_SOCKET_MAP = new ConcurrentHashMap<>();


    //创建socket客户端连接
    public static void add(ClientSocket clientSocket){
        if (clientSocket != null && !clientSocket.getKey().isEmpty())
            ONLINE_SOCKET_MAP.put(clientSocket.getKey(), clientSocket);
    }

    //关闭socket客户端连接
    public static void remove(String key){
        if (!key.isEmpty())
            ONLINE_SOCKET_MAP.remove(key);
    }
}
