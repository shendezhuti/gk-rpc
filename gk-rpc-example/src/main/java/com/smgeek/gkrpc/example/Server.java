package com.smgeek.gkrpc.example;

import com.smgeek.rpc.server.RpcServer;
import com.smgeek.rpc.server.RpcServerConfig;

/**
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-25 01:13
 */
public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer();

        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
