package com.smgeek.gkrpc.codec;

import com.alibaba.fastjson.JSON;

/** 基于json的序列化实现
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 11:06
 */
public class JSONEncoder implements  Encoder {

    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
