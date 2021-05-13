package com.mayang.provider.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mayang.provider.dao.EndUserInfo.EndUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EndUserMapper extends BaseMapper<EndUserDO> {

    @Select("SELECT password FROM end_user WHERE user_id = #{stuId}")
    String userLogin(@Param("stuId") String stuId);

    @Select("SELECT user_id FROM end_user WHERE user_id = #{userId}")
    String selectUserId(@Param("userId")String userId);
}
