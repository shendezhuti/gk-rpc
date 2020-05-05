package com.smgeek.gkrpc.codec;

/**反序列化
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 10:42
 */
public interface Decoder {

    <T> T decode(byte[]bytes,Class<T> clazz);
}
