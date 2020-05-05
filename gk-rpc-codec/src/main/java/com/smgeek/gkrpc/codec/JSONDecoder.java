package com.smgeek.gkrpc.codec;

import com.alibaba.fastjson.JSON;

/**基于json的反序列化实现
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 11:08
 */
public class JSONDecoder implements  Decoder {
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
