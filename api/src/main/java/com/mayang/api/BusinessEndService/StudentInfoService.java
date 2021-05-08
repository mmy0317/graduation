package com.mayang.api.BusinessEndService;

import com.mayang.api.model.StuInfoDTO.StuInfoDTO;

public interface StudentInfoService {
    /**
     * @description:增加学生信息
     * @param stuInfoDTO
     * @return
     */
    Boolean AddInfo(StuInfoDTO stuInfoDTO);

//    /**
//     * @description:获取活码分页 传回一个StuInfoVO类型的长度为10的列表
//     * @param stuParam
//     * @return
//     */
//    List<StuInfoDTO> InfoPage(StuParam stuParam);

    /**
     * @description:根据学号查询信息
     * @param stuNum
     * @return
     */
    StuInfoDTO GetInfoByNum(Integer stuNum);

    /**
     * @description:学生毕业或者离校,软删除学生信息(根据学号/单个删除)
     * @param stuNum
     * 入参:学生学号
     * @return
     */
    Boolean DeleteInfo(Integer stuNum);

    /**
     * @description:更新学生信息
     * @param stuInfoDTO
     * @return
     */
    Boolean UpdateInfo(StuInfoDTO stuInfoDTO);



}
