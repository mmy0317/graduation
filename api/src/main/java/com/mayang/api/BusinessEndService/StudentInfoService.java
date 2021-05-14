package com.mayang.api.BusinessEndService;

import com.mayang.api.model.InfoDTO.StuInfoDTO;

public interface StudentInfoService {
    /**
     * @description:管理员后台增加学生信息
     * @param stuInfoDTO
     * @return
     */
    Boolean AddInfo(StuInfoDTO stuInfoDTO);


    /**
     * @description:管理员后台根据学号查询信息
     * @param stuNum
     * @return
     */
    StuInfoDTO GetInfoByNum(Integer stuNum);

    /**
     * @description:学生毕业或者离校,管理员后台软删除学生信息(根据学号/单个删除)
     * @param stuNum
     * 入参:学生学号
     * @return
     */
    Boolean DeleteInfo(Integer stuNum);

    /**
     * @description:管理员后台更新学生信息
     * @param stuInfoDTO
     * @return
     */
    Boolean
    UpdateInfo(StuInfoDTO stuInfoDTO);


}
