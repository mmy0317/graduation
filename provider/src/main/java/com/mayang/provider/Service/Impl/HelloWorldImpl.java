package com.mayang.provider.Service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mayang.api.BusinessEndService.HelloWorldService;
@Service
public class HelloWorldImpl implements HelloWorldService {
    @Override
    public String SayHello(String name) {
        return  "Hello "+ name + ",This is springboot-dubbo test";
    }

}
