package com.mayang.api.BusinessEndService;


import com.mayang.api.model.StuInfoDTO.EndUserDTO;
import com.mayang.api.model.param.EndUserParam;

public interface EndUserService {

    /**
     * 后台管理员登录
     * @param userId
     * @param password
     * @return
     */
    Boolean EndUserLogin(String userId , String password);


    /**
     * 新增后台管理员
     * @param endUserParam
     * @return
     */
    Boolean EndUserAdd(EndUserDTO endUserDTO);


}
