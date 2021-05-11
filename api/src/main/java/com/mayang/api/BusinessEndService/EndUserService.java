package com.mayang.api.BusinessEndService;


import com.mayang.api.model.InfoDTO.EndUserDTO;

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
     * @param endUserDTO
     * @return
     */
    Boolean EndUserAdd(EndUserDTO endUserDTO);


}
