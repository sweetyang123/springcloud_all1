package com.yt.eureka_provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
//        获取eureka服务注册列表
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
    @Autowired
    HealthStatusService healthStatusService;
    @GetMapping("/geth2")
    public String getClient2(@RequestParam("status") Boolean status) {
//        设置服务上下线
        healthStatusService.setStatus(status);
        return healthStatusService.getStatus();
        }
    @GetMapping("/geth3")
    public String getClient3() {
        RestTemplate template;
        return "OK";
        }
    }
