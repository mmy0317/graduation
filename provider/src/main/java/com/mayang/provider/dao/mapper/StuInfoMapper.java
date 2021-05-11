package com.mayang.provider.dao.mapper;

import com.mayang.provider.dao.StudentInfo.StuInfoDO;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface StuInfoMapper extends BaseMapper<StuInfoDO>{
    @Select("SELECT * FROM stu_main_info  WHERE stu_num = #{stuNum}")
    StuInfoDO selectStuInfoByStuId (@Param("stuNum")Integer stuNum);
}
