package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("Flag", "soul");
        //增加记录表进入接口的时间
        //insert into webtranslog ('01','地址名称','进入时间','');

        System.out.println("进入时间："+System.currentTimeMillis());
    }
}
