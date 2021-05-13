package com.mayang.provider.Service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mayang.api.BusinessGoodInfoService.FrontGoodInfoService;
import com.mayang.api.model.Enum.GoodsStatus;
import com.mayang.api.model.InfoDTO.GoodsInfoDTO;
import com.mayang.api.utils.MyException;
import com.mayang.provider.convert.GoodsInfoDaoConvert;
import com.mayang.provider.dao.GoodsInfo.GoodsInfoDO;
import com.mayang.provider.dao.mapper.GoodsInfoMapper;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FrontGoodInfoServiceImpl implements FrontGoodInfoService {
    @Resource
    GoodsInfoMapper goodsInfoMapper;

    /**
     * @Decription  学生上架商品
     * @param goodsInfoDTO
     * @return
     */
    @Override
    public Boolean StuAddGoods(GoodsInfoDTO goodsInfoDTO) {
        if (goodsInfoDTO==null){
            throw new NullPointerException("参数不能为空");
        }
        GoodsInfoDO goodsInsertInfoDO =GoodsInfoDaoConvert.INSTANCE.goodsDtoToDo(goodsInfoDTO);
        Integer add = goodsInfoMapper.insert(goodsInsertInfoDO);
        if (add == 1){
            return true;
        }
        return false;
    }

    /**
     * @Decription 学生下架自己的商品
     * @param goodsNum
     * @return
     */
    @Override
    public Boolean StuDeleteGoods(String goodsNum) {
        LambdaQueryWrapper<GoodsInfoDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        GoodsInfoDO goodsSelectInfoDO = goodsInfoMapper.selectOne(lambdaQueryWrapper.eq(GoodsInfoDO::getGoodsNum,goodsNum));
        if (goodsSelectInfoDO.getGoodStatus().equals(GoodsStatus.GOODS_DOWN.getCode())){
            throw new MyException("该商品已下架");
        }
        goodsSelectInfoDO.setGoodStatus(GoodsStatus.GOODS_DOWN.getCode());
        //回填删除时间
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//标注时间格式
        Date dateNow = new Date();
        String timeNow =sf.format(dateNow);
        goodsSelectInfoDO.setGoodEndtime(timeNow);
        //删除旧的,增加新的
        goodsInfoMapper.deleteById(goodsSelectInfoDO.getId());
        Integer delete = goodsInfoMapper.insert(goodsSelectInfoDO);
        if (delete == 1){
            return true;
        }
        return false;
    }

    /**
     * @Decription 学生更新自己的商品,我们在学生点击更新商品的时候回查询商品的详情,再将学生更新的内容返回来
     * @param goodsInfoDTO
     * @return
     */
    @Override
    public Boolean StuUpdateGoods(GoodsInfoDTO goodsInfoDTO) {
        String goodsNum = goodsInfoDTO.getGoodsNum();
        //根据商品id查询商品
        GoodsInfoDO goodsSelInfoDO = goodsInfoMapper.selectOne(Wrappers.<GoodsInfoDO>lambdaQuery()
                .eq(GoodsInfoDO::getGoodsNum,goodsNum)
                .eq(GoodsInfoDO::getStuNum,goodsInfoDTO.getStuNum())
                .ne(GoodsInfoDO::getGoodStatus,GoodsStatus.GOODS_DOWN.getCode()));
        //查看更改
        //从查出来的对象中获取数据,回填id,唯一码
        GoodsInfoDO goodsUpdateInfoDO = GoodsInfoDaoConvert.INSTANCE.goodsDtoToDo(goodsInfoDTO);
        goodsUpdateInfoDO.setId(goodsSelInfoDO.getId());
        //goodsUpdateInfoDO.setGoodsNum(goodsInfoDTO.getGoodsNum());
        goodsUpdateInfoDO.setGoodStarttime(goodsSelInfoDO.getGoodStarttime());
        //实现更新
        goodsInfoMapper.deleteById(goodsSelInfoDO.getId());
        Integer update = goodsInfoMapper.insert(goodsUpdateInfoDO);
        if (update>=1){
            return true;
        }
        return false;
    }

    /**
     * @Decription 学生查看自己上架的某个商品的详情
     * @param stuNum
     * @return
     */
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

    /**
     * @Description 学生查看自己名下的所有上架商品
     * @param stuNum
     * @return
     */
    @Override
    public List<GoodsInfoDTO> StuSelectAllGoods(Integer stuNum) {
        //列表用于获取数据
        List<GoodsInfoDO> selectAllDOList=new ArrayList<>();
        //从数据库取数据
        selectAllDOList=goodsInfoMapper.selectList(Wrappers.<GoodsInfoDO>lambdaQuery()
                .eq(GoodsInfoDO::getGoodStatus, GoodsStatus.GOODS_IN.getCode())
                .eq(GoodsInfoDO::getStuNum, stuNum));
        //数据转化 使用stream()流方法 jdk8新功能
        if (selectAllDOList == null) {
            throw new NullPointerException("您没有任何上架商品");
        } else {
            List<GoodsInfoDTO> selectAllDTOList=selectAllDOList.stream()
                    .map(selectAllDO -> GoodsInfoDaoConvert.INSTANCE.goodsDoToDto(selectAllDO))
                    .collect(Collectors.toList());
            return selectAllDTOList;
        }
    }
}
