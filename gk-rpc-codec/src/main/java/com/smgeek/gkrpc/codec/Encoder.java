package com.smgeek.gkrpc.codec;

/** 序列化
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 10:44
 */
public interface Encoder {

        byte[]encode(Object obj);
}
