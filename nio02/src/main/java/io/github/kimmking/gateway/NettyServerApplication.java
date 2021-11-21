package io.github.kimmking.gateway;


import io.github.kimmking.gateway.inbound.HttpInboundServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NettyServerApplication {
    
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";
    
    public static void main(String[] args) {

        String proxyPort = System.getProperty("proxyPort","8888");

        NettyServerApplication NettyServer = new NettyServerApplication();
        
        // 获取应用中配置的地址
        String proxyServers = System.getProperty("proxyServers",NettyServer.getUrl());
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        HttpInboundServer server = new HttpInboundServer(port, Arrays.asList(proxyServers.split(",")));
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + server.toString());
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    /**
     * 获取地址，并组装
     * @return
     */
    private String getUrl()
    {
    	String tUrl ="";
    
    	String tPath="D:/learn_java/JavaCourseCodes-main/02nio/nio02/target/UrlAddress";
        File f = new File(tPath);
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        String temp="";
        try {
            inputStream = new FileInputStream(f);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            
            while((temp=reader.readLine())!=null)
            {
            	System.out.println(temp);
            	tUrl=tUrl+temp+",";
            }

            inputStream.close();
            tUrl =tUrl.substring(0, tUrl.length()-1);
            System.out.println(tUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	return tUrl;
    }
}
