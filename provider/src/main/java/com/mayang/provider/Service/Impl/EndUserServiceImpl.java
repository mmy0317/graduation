package com.mayang.provider.Service.Impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mayang.api.BusinessEndService.EndUserService;
import com.mayang.provider.dao.EndUserInfo.EndUserDO;
import com.mayang.provider.dao.mapper.EndUserMapper;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

public class EndUserServiceImpl implements EndUserService {

    @Resource
    EndUserMapper endUserMapper;

    @Override
    public Boolean EndUserLogin(String userId, String password) {
        EndUserDO endUserLoginDO = endUserMapper.selectOne(Wrappers.<EndUserDO>lambdaQuery().eq(EndUserDO::getUserId,userId));
        if (endUserLoginDO==null){
            throw new NullPointerException("该管理员账号并未存在");
        }
        if (StringUtils.isEmpty(endUserLoginDO.getUserPassword())||endUserLoginDO.getUserPassword()!=password){
            return false;
        }
        return true;
    }


    @Override
    public Boolean EndUserAdd(String userId, String password) {
        EndUserDO endUserAddInfoDO = null;
        endUserAddInfoDO
                .setUserId(userId)
                .setUserPassword(password);
        Integer add = endUserMapper.insert(endUserAddInfoDO);
        if (add<=0){
            return false;
        }
        return true;
    }
}
