package com.mayang.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mayang.api.BusinessEndService.EndUserService;
import com.mayang.api.convert.StudentInfoVOConvert;
import com.mayang.api.model.StuInfoDTO.EndUserDTO;
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
    @RequestMapping(value="SouthEast/user/end/add",method=RequestMethod.GET)
    public Boolean EndUserAdd(EndUserParam endUserParam){
        EndUserDTO endUserDTO =StudentInfoVOConvert.INSTANCE.endUserParamToDto(endUserParam);
        return endUserService.EndUserAdd(endUserDTO);
    }
}
