package com.mayang.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mayang.api.BusinessEndService.EndUserService;
import com.mayang.api.convert.EndUserVOConvert;
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
     * @param id
     * @param password
     * @return
     */
    @RequestMapping(value="SouthEast/user/end/login",method=RequestMethod.GET)
    @ResponseBody
    public Boolean EndUserLogin(String id, String password){
        return endUserService.EndUserLogin(id, password);
    }

    /**
     * 后台管理员增加
     * @param endUserParam
     * @return
     */
    @RequestMapping(value="SouthEast/user/end/add",method=RequestMethod.GET)
    public Boolean EndUserAdd(EndUserParam endUserParam){
        if (endUserParam == null){
            throw new NullPointerException("参数不能为空");
        }
        EndUserDTO endUserDTO = EndUserVOConvert.INSTANCE.endUserParamToDto(endUserParam);
        return endUserService.EndUserAdd(endUserDTO);
    }
}
