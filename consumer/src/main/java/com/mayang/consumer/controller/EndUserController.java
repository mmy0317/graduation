package com.mayang.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mayang.api.BusinessEndService.EndUserService;
import com.mayang.api.convert.StudentInfoVOConvert;
import com.mayang.api.model.InfoDTO.EndUserDTO;
import com.mayang.api.model.param.EndUserParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndUserController {

    @Reference
    private EndUserService endUserService;

    /**
     * 后台管理员登录
     * @param userId
     * @param password
     * @return
     */
    //127.0.0.1:9094/SouthEast/user/end/login?userId=mayang&password=mayang
    //返回值 false 应该返回true
    @RequestMapping(value="SouthEast/user/end/login",method=RequestMethod.GET)
    @ResponseBody
    public Boolean EndUserLogin(String userId, String password){
        return endUserService.EndUserLogin(userId, password);
    }

    /**
     * 后台管理员增加
     * @param endUserParam
     * @return
     */
    //127.0.0.1:9094/SouthEast/user/end/add?userId=mayang&password=miaomiao
    //true
    @RequestMapping(value="SouthEast/user/end/add")
    public Boolean EndUserAdd(EndUserParam endUserParam){
        EndUserDTO endUserDTO =StudentInfoVOConvert.INSTANCE.endUserParamToDto(endUserParam);
        return endUserService.EndUserAdd(endUserDTO);
    }
}
