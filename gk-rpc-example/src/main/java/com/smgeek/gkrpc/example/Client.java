package com.smgeek.gkrpc.example;

import com.smgeek.gkrpc.client.RpcClient;

/**
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-25 01:14
 */
public class Client {

    public static void main(String[] args) {
        RpcClient client = new RpcClient();
       CalcService service =  client.getProxy(CalcService.class);
       int r1 = service.add(1,2);
       int r2 = service.minus(10,8);
       System.out.println(r1);
       System.out.println(r2);
    }
}
