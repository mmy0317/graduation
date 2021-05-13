package com.mayang.provider.Service.Impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mayang.api.BusinessEndService.StudentInfoService;
import com.mayang.api.model.Enum.StuStatus;
import com.mayang.api.model.InfoDTO.StuInfoDTO;
import com.mayang.provider.convert.StudentInfoDaoConvert;
import com.mayang.provider.dao.StudentInfo.StuInfoDO;
import com.mayang.api.utils.MyException;

import com.mayang.provider.dao.mapper.StuInfoMapper;


import javax.annotation.Resource;

@Service
public class StudentInfoImpl implements StudentInfoService{

    @Resource
    StuInfoMapper stuInfoMapper;

    @Override
    public Boolean AddInfo(StuInfoDTO stuInfoDTO) {
        //对于数据进行判断
        this.Check(stuInfoDTO);
        //真实姓名回填昵称
        if (stuInfoDTO.getName()==null){
            stuInfoDTO.setName(stuInfoDTO.getRealName());
        }
        StuInfoDO stuInfoDO = StudentInfoDaoConvert.INSTANCE.stuDtoToDo(stuInfoDTO);
        Integer i = stuInfoMapper.insert(stuInfoDO);
        if (i>0){
            return true;
        }
        return false;
    }

//    @Override
//    public List<StuInfoDTO> InfoPage(StuParam stuParam) {
//        return null;
//    }

    @Override
    public StuInfoDTO GetInfoByNum(Integer stuNum) {
        StuInfoDO stuSelectByNumInfoDO = stuInfoMapper.selectStuInfoByStuId(stuNum);
        //后台能查询到即便离校的学生的信息
        if (stuSelectByNumInfoDO==null){
            throw new MyException("输入的学号有误");
        }
        StuInfoDTO stuSelectByNumInfoDTO = StudentInfoDaoConvert.INSTANCE.stuDoToDto(stuSelectByNumInfoDO);
        return stuSelectByNumInfoDTO;
    }


    /**
     * @description:学生毕业或者开除,软删除学生信息
     * @param stuNum
     * @return Boolean
     */
    @Override
    public Boolean DeleteInfo(Integer stuNum) {
        LambdaQueryWrapper<StuInfoDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        StuInfoDO stuSelectForDeleteInfoDO = stuInfoMapper.selectOne(lambdaQueryWrapper
                .eq(StuInfoDO::getStuNum, stuNum)
                .ne(StuInfoDO::getStuStatus,StuStatus.GRADUATION.getCode()));
        stuSelectForDeleteInfoDO.setStuStatus(StuStatus.GRADUATION.getCode());
        stuInfoMapper.deleteById(stuSelectForDeleteInfoDO.getId());
        Integer i = stuInfoMapper.insert(stuSelectForDeleteInfoDO);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * @description:修改学生个人信息
     * @param stuInfoForUpdateDTO
     * @return Boolean
     */
    @Override
    public Boolean UpdateInfo(StuInfoDTO stuInfoForUpdateDTO) {
        this.Check(stuInfoForUpdateDTO);
        StuInfoDO stuInfoForUpdateDO = StudentInfoDaoConvert.INSTANCE.stuDtoToDo(stuInfoForUpdateDTO);
        LambdaQueryWrapper<StuInfoDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        stuInfoMapper.delete(lambdaQueryWrapper
                .eq(StuInfoDO::getStuNum, stuInfoForUpdateDTO.getStuNum())
                .eq(StuInfoDO::getRealName,stuInfoForUpdateDTO.getRealName())
                .ne(StuInfoDO::getStuStatus, StuStatus.GRADUATION.getCode()));
        Integer i = stuInfoMapper.insert(stuInfoForUpdateDO);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 对于stuInfoDTO数据的一些判定
     * @param stuInfoDTO
     */
    public void Check(StuInfoDTO stuInfoDTO){
        //为空判断
        if (stuInfoDTO==null){
            throw new NullPointerException("参数不能为空");
        }
        //对于数据库进行一个查重 ,看看当前用户是否已经注册
        LambdaQueryWrapper<StuInfoDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        StuInfoDO stuInfoDOFindOne = stuInfoMapper.selectOne(lambdaQueryWrapper
                .eq(StuInfoDO::getStuNum, stuInfoDTO.getStuNum())
                .ne(StuInfoDO::getStuStatus, StuStatus.GRADUATION.getCode()));
        if (stuInfoDOFindOne != null) {
            throw new MyException("该用户已存在");
        }
        //其余判断
        if (StringUtils.isEmpty(stuInfoDTO.getClassRoom())){
            String classroom = String.valueOf(stuInfoDTO.getStuNum()).substring(5,6);//获取班级
            stuInfoDTO.setClassRoom(classroom+"班");
        }
        ////////由于不了解学校对于院系的设定 , 因此此处暂不做学号对于院系中文名称的回填//////
        if (StringUtils.isEmpty(stuInfoDTO.getDepartment())){
            String department = String.valueOf(stuInfoDTO.getStuNum()).substring(0,3);//获取院系
            stuInfoDTO.setDepartment(department+"系");
        }
        if (StringUtils.isEmpty(stuInfoDTO.getRealName())){
            throw new IllegalArgumentException("姓名参数错误");
        }
        if (stuInfoDTO.getStuNum()==null){
            throw new IllegalArgumentException("学号参数错误");
        }
        if (StringUtils.isEmpty(stuInfoDTO.getPassword())|| StringUtils.isBlank(stuInfoDTO.getPassword()
        )){
            throw new IllegalArgumentException("密码参数错误");
        }
    }
}
