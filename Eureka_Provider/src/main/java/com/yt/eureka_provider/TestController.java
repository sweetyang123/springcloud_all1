package com.yt.eureka_provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/geth")
    public String getHello(){
        return "hello";
    }
}
