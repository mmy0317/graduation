package com.mayang.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mayang.api.BusinessEndService.StudentInfoService;
import com.mayang.api.model.InfoDTO.StuInfoDTO;
import com.mayang.api.model.param.StuAddParam;
import com.mayang.api.convert.StudentInfoVOConvert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndStuInfoController {

    @Reference
    private StudentInfoService studentInfoService;

    /**
     * @description 后台增加学生信息
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
    @RequestMapping(value="SouthEast/student/end/delete",method=RequestMethod.GET)
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

    /**
     * @description:根据学号查询学生信息
     * @param stuNum
     * @return
     */
    //127.0.0.1:9094/SouthEast/student/end/selectbynum?stuNum=10417123
    /*
     WHERE (stu_num = 1041712' at line 1
### The error may exist in com/mayang/provider/dao/mapper/StuInfoMapper.java (best guess)
### The error may involve defaultParameterMap
### The error occurred while setting parameters
### SQL: SELECT  id,name,real_name,stu_num,class_room,department,gender,personal_word,phone,wechat,qq_num,age,key,stu_status,stu_drom,stu_pic  FROM stu_main_info     WHERE (stu_num = ?)
### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'key,stu_status,stu_drom,stu_pic  FROM stu_main_info

     */
    @RequestMapping(value="SouthEast/student/end/selectbynum",method=RequestMethod.GET)
    @ResponseBody
    public StuInfoDTO selectInfo(Integer stuNum){
        StuInfoDTO stuInfoDTO = studentInfoService.GetInfoByNum(stuNum);
        return stuInfoDTO;
    }


}
