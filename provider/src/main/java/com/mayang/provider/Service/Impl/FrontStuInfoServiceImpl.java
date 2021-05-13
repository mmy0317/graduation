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
        this.returnParam(stuInfoDTO);
        this.check(stuInfoDTO);
        //对于数据库查重,查看这个人是否存在
        StuInfoDO stuInfoDOFindOne = stuInfoMapper
                .selectStuInfoForCheck(stuInfoDTO.getStuNum(),StuStatus.GRADUATION.getCode());
        if (stuInfoDOFindOne != null) {
            throw new MyException("该用户已存在");
        }
        if (stuInfoDTO.getName() == null) {
            stuInfoDTO.setName(stuInfoDTO.getRealName());
        }
        StuInfoDO stuInfoDO=StudentInfoDaoConvert.INSTANCE.stuDtoToDo(stuInfoDTO);
        Integer i = stuInfoMapper.insert(stuInfoDO);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean StuUpdate(StuInfoDTO stuInfoDTO) {
        if (stuInfoDTO==null){
            throw new NullPointerException("前端传回参数错误");
        }
        Integer stuNum = stuInfoDTO.getStuNum();
        stuInfoMapper.delete(Wrappers.<StuInfoDO>lambdaQuery()
                .eq(StuInfoDO::getStuNum, stuNum)
                .ne(StuInfoDO::getStuStatus, StuStatus.GRADUATION.getCode()));
        this.returnParam(stuInfoDTO);
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
        if (stuloginInfoDO.getPassword()==null){
            throw new MyException("该用户信息有误");
        }
        if (!stuloginInfoDO.getPassword().equals(key)){
            return false;
        }
        return true;
    }


    /**
     * 对于stuInfoDTO数据的一些判定
     * @param stuInfoDTO
     */
    public void check(StuInfoDTO stuInfoDTO){
        //为空判断
        if (stuInfoDTO==null){
            throw new NullPointerException("参数不能为空");
        }
        //默认图片
        if (stuInfoDTO.getStuPic()=="" || stuInfoDTO.getStuPic()==null){
            stuInfoDTO.setStuPic("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic2.zhimg.com%2F50%2Fv2-6afa72220d29f045c15217aa6b275808_hd.jpg&refer=http%3A%2F%2Fpic2.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1622885197&t=fbfa3e0ca0a494bdb735ce712ebab9ad");
        }
        if (StringUtils.isEmpty(stuInfoDTO.getRealName())){
            throw new IllegalArgumentException("姓名参数错误");
        }
        if (stuInfoDTO.getStuNum()==null){
            throw new IllegalArgumentException("学号参数错误");
        }
        if (StringUtils.isEmpty(stuInfoDTO.getPassword())|| StringUtils.isBlank(stuInfoDTO.getPassword())){
            throw new IllegalArgumentException("密码参数错误");
        }
    }

    /**
     * 参数回填
     */
    public void returnParam(StuInfoDTO stuInfoDTO){
        //为空判断
        if (stuInfoDTO==null){
            throw new NullPointerException("参数不能为空");
        }
        //为空判断,进行插入
        if (StringUtils.isEmpty(stuInfoDTO.getClassRoom())){
            String classroom = String.valueOf(stuInfoDTO.getStuNum()).substring(5,6);//获取班级
            stuInfoDTO.setClassRoom(classroom);
        }
        ////////由于不了解学校对于院系的设定 , 因此此处暂不做学号对于院系中文名称的回填//////
        if (StringUtils.isEmpty(stuInfoDTO.getDepartment())){
            String department = String.valueOf(stuInfoDTO.getStuNum()).substring(0,3);//获取院系
            stuInfoDTO.setDepartment(department);
        }

    }
}
