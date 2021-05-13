package com.mayang.provider.Service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mayang.api.BusinessGoodInfoService.EndGoodInfoService;
import com.mayang.api.model.Enum.GoodsStatus;
import com.mayang.api.model.InfoDTO.GoodsInfoDTO;
import com.mayang.api.utils.MyException;
import com.mayang.provider.convert.GoodsInfoDaoConvert;
import com.mayang.provider.dao.GoodsInfo.GoodsInfoDO;
import com.mayang.provider.dao.mapper.GoodsInfoMapper;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


@Service
public class EndGoodInfoServiceImpl implements EndGoodInfoService {
    @Resource
    GoodsInfoMapper goodsInfoMapper;

    @Override
    public Boolean deleteGoods(String goodsNum) {
        LambdaQueryWrapper<GoodsInfoDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        GoodsInfoDO goodsDelSelInfoDO = goodsInfoMapper.selectOne(lambdaQueryWrapper
                .eq(GoodsInfoDO::getGoodsNum,goodsNum));
        if (goodsDelSelInfoDO.getGoodStatus() == GoodsStatus.GOODS_DOWN.getCode()){
            new MyException("该商品已经下架!");
        }else {
            goodsDelSelInfoDO.setGoodStatus(GoodsStatus.GOODS_DOWN.getCode());
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//标注时间格式
            Date dateNow = new Date();
            String timeNow =sf.format(dateNow);
            goodsDelSelInfoDO.setGoodEndtime(timeNow);
        }
        goodsInfoMapper.delete(lambdaQueryWrapper.eq(GoodsInfoDO::getGoodsNum,goodsNum));
        Integer insertInfo = goodsInfoMapper.insert(goodsDelSelInfoDO);
        if (insertInfo>0){
            return true;
        }
        return null;
    }

    @Override
    public GoodsInfoDTO selectGoodsInfo(String goodsNum) {
        LambdaQueryWrapper<GoodsInfoDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        GoodsInfoDO goodsSelectInfoDO = goodsInfoMapper.selectOne(lambdaQueryWrapper
                .eq(GoodsInfoDO::getGoodsNum,goodsNum)
                .ne(GoodsInfoDO::getGoodStatus,GoodsStatus.GOODS_IN.getCode()));
        GoodsInfoDTO goodsSelectInfoDTO = GoodsInfoDaoConvert.INSTANCE.goodsDoToDto(goodsSelectInfoDO);
        return goodsSelectInfoDTO;
    }

}

