package com.mayang.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mayang.api.BusinessEndService.HelloWorldService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
    @Reference
    private HelloWorldService helloWorldService;

    @RequestMapping(value="/sayHello/{name}",method=RequestMethod.GET)
    public String SayHello(@PathVariable String name){
        return helloWorldService.SayHello(name);
    }
}
