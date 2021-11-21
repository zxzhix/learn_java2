package io.github.kimmking.gateway.router;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomHttpEndpointRouter implements HttpEndpointRouter {
    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        System.out.println(urls.get(random.nextInt(size)));
        //增加线程池，从线程池来分配，当线程池用完后，其他连接进行等待
       
        return urls.get(random.nextInt(size));
    }
    
    public static void main(String[] args) {
		
    	RandomHttpEndpointRouter randhttp = new RandomHttpEndpointRouter();
    	List tlist = new ArrayList();
    	tlist.add("Http1");
    	tlist.add("Http2");
    	tlist.add("Http3");
    	tlist.add("Http4");
    	tlist.add("Http5");
    	
    	randhttp.route(tlist);
    	
	}
}
