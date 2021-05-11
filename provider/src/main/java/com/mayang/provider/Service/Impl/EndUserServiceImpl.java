package com.mayang.provider.Service.Impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mayang.api.BusinessEndService.EndUserService;
import com.mayang.api.model.InfoDTO.EndUserDTO;

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
        EndUserDO endUserLoginDO = endUserMapper.selectOne(Wrappers.<EndUserDO>lambdaQuery().eq(EndUserDO::getUserId,userId));
        if (endUserLoginDO==null){
            throw new NullPointerException("该管理员账号并未存在");
        }
        if (endUserLoginDO.getPassword()==null){
            return false;
        }
        if (endUserLoginDO.getPassword()!=password){
            return false;
        }

        return true;
    }


    @Override
    public Boolean EndUserAdd(EndUserDTO endUserDTO) {
        if (endUserDTO==null){
            throw new NullPointerException("参数为空");
        }
        EndUserDO endUserInsertDO =EndUserConvert.INSTANCE.endUserDtoToDo(endUserDTO);
        Integer add = endUserMapper.insert(endUserInsertDO);
        if (add<=0){
            return false;
        }
        return true;
    }


}
