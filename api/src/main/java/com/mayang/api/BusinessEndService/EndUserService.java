package com.mayang.api.BusinessEndService;

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
     * @param userId
     * @param password
     * @return
     */
    Boolean EndUserAdd(String userId , String password);


}
