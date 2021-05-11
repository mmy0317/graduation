package com.mayang.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mayang.api.BusinessEndService.EndUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndUserController {
    @Reference
    EndUserService endUserService;

    /**
     * 后台管理员登录
     * @param userId
     * @param password
     * @return
     */
    @RequestMapping(value="SouthEast/user/end/login",method=RequestMethod.GET)
    public Boolean EndUserLogin(String userId, String password){
        return endUserService.EndUserLogin(userId, password);
    }

    /**
     * 后台管理员增加
     * @param userId
     * @param password
     * @return
     */
    @RequestMapping(value="SouthEast/user/end/add",method=RequestMethod.POST)
    public Boolean EndUserAdd(String userId, String password){
        return endUserService.EndUserAdd(userId,password);
    }
}
