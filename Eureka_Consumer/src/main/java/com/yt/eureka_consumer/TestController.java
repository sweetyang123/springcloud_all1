package com.yt.eureka_consumer;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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
    @Autowired
    EurekaClient client1;
    @Autowired
    LoadBalancerClient lb;


    @GetMapping("/geth")
    public String getHello(){
        return "hi";
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

    @GetMapping("/geth2")
    public String getClient2() {
        String object="";
        List<InstanceInfo> instances = client1.getInstancesByVipAddress("provider", false);
        for (InstanceInfo info:instances) {
//            LAPTOP-14ILDFJM:provider:7001
            System.out.println(info.getInstanceId());
            String url = "http://" + info.getHostName() + ":" + info.getPort() + "/geth";
            System.out.println(url);
            RestTemplate template = new RestTemplate();
           object = template.getForObject(url, String.class);
        }
        return object;
        }
        //ribbon客户端负载均衡
    @GetMapping("/geth3")
    public String getClient3() {
        ServiceInstance instance = lb.choose("provider");
        String url = instance.getUri() + "/geth";
        RestTemplate template = new RestTemplate();
        String  object = template.getForObject(url, String.class);
        System.out.println(url+"===="+object);
        return "ooxx";

        }
    }
