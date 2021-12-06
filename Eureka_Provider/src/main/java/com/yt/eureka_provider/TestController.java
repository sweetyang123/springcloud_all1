package com.yt.eureka_provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    DiscoveryClient client;
    @GetMapping("/geth")
    public String getHello(){
        return "hello";
    }
    @GetMapping("/geth1")
    public String getClient1(){

        List<String> services = client.getServices();
        for (String s:services) {
            System.out.println(s);
        }
        List<ServiceInstance> instances = client.getInstances("provider");
        for (ServiceInstance service:instances) {
            System.out.println("ServiceInstance: "+service.getHost()+"---"+
                    service.getPort()+" 1 "+service.getInstanceId()+" 2 "+service.getMetadata());
        }
        return client.description();
    }
}
