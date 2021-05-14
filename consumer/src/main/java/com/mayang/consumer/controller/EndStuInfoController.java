package com.mayang.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mayang.api.BusinessEndService.StudentInfoService;
import com.mayang.api.model.InfoDTO.StuInfoDTO;
import com.mayang.api.model.param.StuAddParam;
import com.mayang.api.convert.StudentInfoVOConvert;
import com.mayang.api.model.param.StuEndUpdateParam;
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
    //todo:暂时不做/建议改成批量增加
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
    //127.0.0.1:9094/SouthEast/student/end/delete?stuNum=10417114
    //检验结果 : 完成了软删除
    @RequestMapping(value="SouthEast/student/end/delete",method=RequestMethod.GET)
    @ResponseBody
    public Boolean deleteStuInfo(Integer stuNum){
        return studentInfoService.DeleteInfo(stuNum);
    }

    /**
     * @description:更新学生信息,更新学生班级,院系,状态等信息
     * @param updateParam
     * @return
     */
    //127.0.0.1:9094/SouthEast/student/end/update?classRoom=计科1班&department=电子与计算机系&status=2&stuNum=10417114
    //结果:成功
    @RequestMapping(value="SouthEast/student/end/update",method=RequestMethod.GET)
    @ResponseBody
    public Boolean updateStuInfo(StuEndUpdateParam updateParam){
        StuInfoDTO stuInfoForUpdateDTO =StudentInfoVOConvert.INSTANCE.updateParamToDto(updateParam);
        return studentInfoService.UpdateInfo(stuInfoForUpdateDTO);
    }

    /**
     * @description:根据学号查询学生信息
     * @param stuNum
     * @return
     */
    //127.0.0.1:9094/SouthEast/student/end/selectbynum?stuNum=10417114
    //结果:成功
    @RequestMapping(value="SouthEast/student/end/selectbynum",method=RequestMethod.GET)
    @ResponseBody
    public StuInfoDTO selectInfo(Integer stuNum){
        StuInfoDTO stuInfoDTO = studentInfoService.GetInfoByNum(stuNum);
        return stuInfoDTO;
    }


}
