package com.mayang.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mayang.api.BusinessStuInfoService.FrontStuInfoService;
import com.mayang.api.convert.StudentInfoVOConvert;
import com.mayang.api.model.StuInfoDTO.StuInfoDTO;
import com.mayang.api.model.param.StuAddParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrontStuInfoController {

    @Reference
    FrontStuInfoService frontStuInfoService;

    /**
     * 学生注册
     * @param addParam
     * @return
     */
    @RequestMapping(value="SouthEast/student/front/creat",method=RequestMethod.GET)
    public Boolean CreatInfoByStu(StuAddParam addParam){
        StuInfoDTO stuInfoForInsertDTO = StudentInfoVOConvert.INSTANCE.addParamToDto(addParam);
        return frontStuInfoService.StuAddInfo(stuInfoForInsertDTO);
    }

    /**
     * 学生更新个人信息
     * @param addParam
     * @return
     */
    @RequestMapping(value="SouthEast/student/front/update",method=RequestMethod.POST)
    public Boolean UpdateInfoByStu(StuAddParam addParam){
        StuInfoDTO stuInfoForUpdateDTO = StudentInfoVOConvert.INSTANCE.addParamToDto(addParam);
        return  frontStuInfoService.StuUpdate(stuInfoForUpdateDTO);
    }

    /**
     * 学生登录
     * @param stuNum
     * @param key
     * @return
     */
    @RequestMapping(value="SouthEast/student/front/login")
    public Boolean LoginByStu(Integer stuNum , String key){
        return frontStuInfoService.StuLogin(stuNum,key);
    }
}
