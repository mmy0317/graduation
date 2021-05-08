package com.mayang.provider.Service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mayang.api.BusinessGoodInfoService.FrontGoodInfoService;
import com.mayang.api.model.Enum.GoodsStatus;
import com.mayang.api.model.StuInfoDTO.GoodsInfoDTO;
import com.mayang.api.utils.MyException;
import com.mayang.provider.convert.GoodsInfoDaoConvert;
import com.mayang.provider.dao.GoodsInfo.GoodsInfoDO;
import com.mayang.provider.dao.mapper.GoodsInfoMapper;

import javax.annotation.Resource;

@Service
public class FrontGoodInfoServiceImpl implements FrontGoodInfoService {
    @Resource
    GoodsInfoMapper goodsInfoMapper;

    @Override
    public Boolean StuAddGoods(GoodsInfoDTO goodsInfoDTO) {
        if (goodsInfoDTO==null){
            throw new NullPointerException("参数不能为空");
        }
        GoodsInfoDO goodsInsertInfoDO =GoodsInfoDaoConvert.INSTANCE.goodsDtoToDo(goodsInfoDTO);
        Integer add = goodsInfoMapper.insert(goodsInsertInfoDO);
        if (add > 1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean StuDeleteGoods(String goodsNum) {
        LambdaQueryWrapper<GoodsInfoDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        GoodsInfoDO goodsSelectInfoDO = goodsInfoMapper.selectOne(lambdaQueryWrapper.eq(GoodsInfoDO::getGoodsNum,goodsNum));
        if (goodsSelectInfoDO.getGoodStatus().equals(GoodsStatus.GOODS_DOWN.getCode())){
            throw new MyException("该商品已下架");
        }
        goodsSelectInfoDO.setGoodStatus(GoodsStatus.GOODS_DOWN.getCode());
        goodsInfoMapper.deleteById(goodsSelectInfoDO.getId());
        Integer delete = goodsInfoMapper.insert(goodsSelectInfoDO);
        if (delete > 1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean StuUpdateGoods(GoodsInfoDTO goodsInfoDTO) {
        String goodsNum = goodsInfoDTO.getGoodsNum();
        GoodsInfoDO goodsSelInfoDO = goodsInfoMapper.selectOne(Wrappers.<GoodsInfoDO>lambdaQuery()
                .eq(GoodsInfoDO::getGoodsNum,goodsNum)
                .eq(GoodsInfoDO::getStuNum,goodsInfoDTO.getStuNum())
                .ne(GoodsInfoDO::getGoodStatus,GoodsStatus.GOODS_DOWN.getCode()));
        //信息回填
        GoodsInfoDO goodsUpdateInfoDO = GoodsInfoDaoConvert.INSTANCE.goodsDtoToDo(goodsInfoDTO);
        goodsUpdateInfoDO.setId(goodsSelInfoDO.getId());
        goodsUpdateInfoDO.setGoodStarttime(goodsSelInfoDO.getGoodStarttime());
        //实现更新
        goodsInfoMapper.deleteById(goodsSelInfoDO.getId());
        Integer update = goodsInfoMapper.insert(goodsUpdateInfoDO);
        if (update>1){
            return true;
        }
        return false;
    }

    @Override
    public GoodsInfoDTO StuSelectSelfGood(Integer stuNum, String goodsNum) {
        LambdaQueryWrapper<GoodsInfoDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        GoodsInfoDO goodsSelectOneInfoDO = goodsInfoMapper.selectOne(lambdaQueryWrapper
                .eq(GoodsInfoDO::getGoodsNum,goodsNum)
                .eq(GoodsInfoDO::getStuNum,stuNum)
                .ne(GoodsInfoDO::getGoodStatus,GoodsStatus.GOODS_DOWN.getCode() ));
        GoodsInfoDTO goodsSelectOneInfoDTO = GoodsInfoDaoConvert.INSTANCE.goodsDoToDto(goodsSelectOneInfoDO);
        return goodsSelectOneInfoDTO;
    }
}
