package com.mayang.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mayang.api.BusinessStuInfoService.FrontStuInfoService;
import com.mayang.api.convert.StudentInfoVOConvert;
import com.mayang.api.model.InfoDTO.StuInfoDTO;
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
    //127.0.0.1:9094/SouthEast/student/front/creat?name= &realName= &stuNum= &gender= &personalWord= &phone= &wechat= &qqNum= &age= &password= &stuStatus= &stuDrom=
    //127.0.0.1:9094/SouthEast/student/front/creat?name=长风&realName=马扬&stuNum=10417114&gender=1&personalWord=计算机一班班草&phone=18851976933&wechat=m18851976933&qqNum=1659254531&age=21&password=leimiaomiao&stuStatus=2&stuDrom=桃园三舍403
    //return true
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
    //127.0.0.1:9094/SouthEast/student/front/update?name=长风&realName=马阳&stuNum=10417114&gender=1&personalWord=计算机一班班草&phone=18851976933&wechat=m18851976933&qqNum=1659254531&age=21&password=leimiaomiao&stuStatus=2&stuDrom=桃园三舍403
    //检验结果 : 能成功修改
    @RequestMapping(value="SouthEast/student/front/update",method=RequestMethod.GET)
    public Boolean UpdateInfoByStu(StuAddParam addParam){
        StuInfoDTO stuInfoForUpdateDTO = StudentInfoVOConvert.INSTANCE.addParamToDto(addParam);
        return  frontStuInfoService.StuUpdate(stuInfoForUpdateDTO);
    }

    /**
     * 学生登录
     * @param stuNum
     * @param password
     * @return
     */
    //127.0.0.1:9094/SouthEast/student/front/login?stuNum=10417115&password=20000315ymq
    //检验结果 : 可以通过账号密码进行登录 , 业务逻辑正确
    @RequestMapping(value="SouthEast/student/front/login")
    public Boolean LoginByStu(Integer stuNum , String password){
        return frontStuInfoService.StuLogin(stuNum,password);
    }
}
