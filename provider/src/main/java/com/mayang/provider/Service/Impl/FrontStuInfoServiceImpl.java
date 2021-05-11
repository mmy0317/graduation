package com.mayang.provider.Service.Impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mayang.api.BusinessStuInfoService.FrontStuInfoService;
import com.mayang.api.model.Enum.StuStatus;
import com.mayang.api.model.InfoDTO.StuInfoDTO;
import com.mayang.api.utils.MyException;
import com.mayang.provider.convert.StudentInfoDaoConvert;
import com.mayang.provider.dao.StudentInfo.StuInfoDO;
import com.mayang.provider.dao.mapper.StuInfoMapper;

import javax.annotation.Resource;

@Service
public class FrontStuInfoServiceImpl implements FrontStuInfoService {

    @Resource
    StuInfoMapper stuInfoMapper;

    @Override
    public Boolean StuAddInfo(StuInfoDTO stuInfoDTO) {
        this.Check(stuInfoDTO);
        if (stuInfoDTO.getName() == null) {
            stuInfoDTO.setName(stuInfoDTO.getRealName());
        }
        StuInfoDO stuInfoDO=StudentInfoDaoConvert.INSTANCE.stuDtoToDo(stuInfoDTO);
        Integer i=stuInfoMapper.insert(stuInfoDO);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean StuUpdate(StuInfoDTO stuInfoDTO) {
        this.Check(stuInfoDTO);
        Integer stuNum=stuInfoDTO.getStuNum();
        stuInfoMapper.delete(Wrappers.<StuInfoDO>lambdaQuery()
                .eq(StuInfoDO::getStuNum, stuNum)
                .ne(StuInfoDO::getStuStatus, StuStatus.GRADUATION.getCode()));
        StuInfoDO stuInfoInsertDO=StudentInfoDaoConvert.INSTANCE.stuDtoToDo(stuInfoDTO);
        Integer add=stuInfoMapper.insert(stuInfoInsertDO);
        if (add > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean StuLogin(Integer stuNum, String key) {
        LambdaQueryWrapper<StuInfoDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        StuInfoDO stuloginInfoDO = stuInfoMapper.selectOne(lambdaQueryWrapper
                .eq(StuInfoDO::getStuNum,stuNum)
                .eq(StuInfoDO::getStuStatus,StuStatus.READING.getCode()));//在读的学生才能进入系统
        if (stuloginInfoDO==null){
            throw new NullPointerException("该学生没有注册或未处于在读状态");
        }
        if (stuloginInfoDO.getKey()==null){
            throw new MyException("该用户信息有误");
        }
        if (stuloginInfoDO.getKey()!=key){
            return false;
        }
        return true;
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
        if (StringUtils.isEmpty(stuInfoDTO.getKey())|| StringUtils.isBlank(stuInfoDTO.getKey())){
            throw new IllegalArgumentException("密码参数错误");
        }
    }
}
