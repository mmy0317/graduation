package com.mayang.api.BusinessStuInfoService;

import com.mayang.api.model.StuInfoDTO.StuInfoDTO;

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
}
