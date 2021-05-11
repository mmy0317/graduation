package com.mayang.api.BusinessStuInfoService;

import com.mayang.api.model.InfoDTO.StuInfoDTO;

public interface FrontStuInfoService {

    /**
     * @Description:学生注册
     * @param stuInfoDTO
     * @return
     */
    Boolean StuAddInfo(StuInfoDTO stuInfoDTO);

     /**
     * @Description:学生修改自己账户信息
     * @param stuInfoDTO
     * @return
     */
    Boolean StuUpdate(StuInfoDTO stuInfoDTO);

    /**
     * @Description 学生登录
     * @param stuNum
     * @param key
     * @return
     */
    Boolean StuLogin(Integer stuNum , String key);
}
