package com.smgeek.gkrpc.transport;

import com.smgeek.gkrpc.Peer;
import org.apache.commons.io.IOUtils;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-24 13:44
 */
public class HttpTransportClient implements TransportClient {
    private String url;
    @Override
    public void connect(Peer peer) {
            this.url= "http://"+peer.getHost()+":"+peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpConnection = (HttpURLConnection) new URL(url).openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);
            httpConnection.setUseCaches(false);
            httpConnection.setRequestMethod("POST");

            httpConnection.connect();
            IOUtils.copy(data,httpConnection.getOutputStream());

            int resultCode = httpConnection.getResponseCode();
            if(resultCode==HttpURLConnection.HTTP_OK){
                return httpConnection.getInputStream();
            }else{
                return httpConnection.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }


    }

    @Override
    public void close() {

    }
}
