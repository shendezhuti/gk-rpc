package com.smgeek.gkrpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**表示网络传输的一个端点
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-23 22:44
 */

@Data
@AllArgsConstructor

public class Peer {

    private String host;
    private int port;
}
