package com.mayang.provider.Service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mayang.api.BusinessStuInfoService.FrontStuInfoService;
import com.mayang.api.model.Enum.StuStatus;
import com.mayang.api.model.StuInfoDTO.StuInfoDTO;
import com.mayang.provider.convert.StudentInfoDaoConvert;
import com.mayang.provider.dao.StudentInfo.StuInfoDO;
import com.mayang.provider.dao.mapper.StuInfoMapper;

import javax.annotation.Resource;

@Service
public class FrontStuInfoServiceImpl implements FrontStuInfoService {

    StudentInfoImpl studentInfo;

    @Resource
    StuInfoMapper stuInfoMapper;

    @Override
    public Boolean StuAddInfo(StuInfoDTO stuInfoDTO) {
        studentInfo.Check(stuInfoDTO);
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
        studentInfo.Check(stuInfoDTO);
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
}
