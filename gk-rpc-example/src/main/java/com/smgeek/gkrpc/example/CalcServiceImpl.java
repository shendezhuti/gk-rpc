package com.smgeek.gkrpc.example;

/**
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-25 01:14
 */
public class CalcServiceImpl implements  CalcService {

    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        return a-b;
    }
}
