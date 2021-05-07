package com.mayang.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mayang.api.BusinessEndService.StudentInfoService;
import com.mayang.api.model.StuInfoDTO.StuInfoDTO;
import com.mayang.api.model.param.StuAddParam;
import com.mayang.api.convert.StudentInfoVOConvert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndStuInfoController {

    //todo:controller负责转换数据类型为DTO
    @Reference
    private StudentInfoService studentInfoService;

    /**
     * @description 增加学生信息
     * @param stuAddParam
     * @return
     */
    @RequestMapping(value="SouthEast/student/end/creat",method=RequestMethod.GET)
    public Boolean creatStuInfo(StuAddParam stuAddParam){
        StuInfoDTO stuInfoForInsertDTO = StudentInfoVOConvert.INSTANCE.addParamToDto(stuAddParam);
        return studentInfoService.AddInfo(stuInfoForInsertDTO);
    }

    /**
     * @description 学生毕业或者离校,根据学号软删除学生信息
     * @param stuNum
     * @return
     */
    @RequestMapping(value="SouthEast/student/end/delete",method=RequestMethod.DELETE)
    public Boolean deleteStuInfo(Integer stuNum){
        return studentInfoService.DeleteInfo(stuNum);
    }

    /**
     * @description:更新学生信息
     * @param addParam
     * @return
     */
    @RequestMapping(value="SouthEast/student/end/update",method=RequestMethod.POST)
    public Boolean updateStuInfo(StuAddParam addParam){
        StuInfoDTO stuInfoForUpdateDTO =StudentInfoVOConvert.INSTANCE.addParamToDto(addParam);
        return studentInfoService.UpdateInfo(stuInfoForUpdateDTO);
    }

    @RequestMapping(value="SouthEast/student/end/selectbynum",method=RequestMethod.GET)
    public StuInfoDTO selectInfo(Integer stuNum){
        //todo:写一个result类用于返回数据
        StuInfoDTO stuInfoDTO = studentInfoService.GetInfoByNum(stuNum);
        return stuInfoDTO;
    }


}
