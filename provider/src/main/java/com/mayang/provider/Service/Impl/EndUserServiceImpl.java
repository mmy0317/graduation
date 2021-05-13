package com.mayang.provider.Service.Impl;


import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mayang.api.BusinessEndService.EndUserService;
import com.mayang.api.model.InfoDTO.EndUserDTO;

import com.mayang.api.utils.MyException;
import com.mayang.provider.convert.EndUserConvert;
import com.mayang.provider.dao.EndUserInfo.EndUserDO;
import com.mayang.provider.dao.mapper.EndUserMapper;


import javax.annotation.Resource;

@Service
public class EndUserServiceImpl implements EndUserService {

    @Resource
    EndUserMapper endUserMapper;

    @Override
    public Boolean EndUserLogin(String userId, String password) {
        String userPassword =endUserMapper.userLogin(userId);
        if (userPassword == ""){
            throw new NullPointerException("该管理员账户不存在");
        }
        if (!userPassword.equals(password)){
            return false;
        }
        return true;
    }


    @Override
    public Boolean EndUserAdd(EndUserDTO endUserDTO) {
        if (endUserDTO==null){
            throw new NullPointerException("参数为空");
        }
        //数据类型转换,进行数据插入
        EndUserDO endUserInsertDO =EndUserConvert.INSTANCE.endUserDtoToDo(endUserDTO);
        //从数据库中查找是否存在相同的id
        String checkUserId = endUserMapper.selectUserId(endUserInsertDO.getUserId());
        if (StringUtils.isEmpty(endUserMapper.selectUserId(endUserInsertDO.getUserId()))){
            Integer add = endUserMapper.insert(endUserInsertDO);
            if (add<=0){
                return false;
            }
            return true;
        }
        return false;
    }


}
