package io.github.kimmking.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("kk", "java-2-nio");
        
        //增加记录表返回接口的时间
        //insert into webtranslog ('01','地址名称','','返回时间');

        System.out.println("返回时间："+System.currentTimeMillis());
    }
}
