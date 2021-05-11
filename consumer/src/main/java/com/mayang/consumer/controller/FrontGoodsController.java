package com.mayang.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.mayang.api.BusinessGoodInfoService.FrontGoodInfoService;
import com.mayang.api.convert.GoodInfoVOConvert;
import com.mayang.api.model.Enum.GoodsStatus;
import com.mayang.api.model.StuInfoDTO.GoodsInfoDTO;
import com.mayang.api.model.param.GoodsAddParam;
import com.mayang.api.model.param.GoodsParam;
import com.mayang.api.model.param.StuParam;
import com.mayang.api.utils.MyException;
import com.alibaba.dubbo.common.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class FrontGoodsController {

    @Reference
    FrontGoodInfoService frontGoodInfoService;

    /**
     * @Description 学生创建需要上架的商品
     * @param goodsAddParam
     * @return
     */
    @RequestMapping(value="SouthEast/goods/front/creat",method=RequestMethod.GET)
    public Boolean CreatGoods(GoodsAddParam goodsAddParam , StuParam stuParam){
        GoodsInfoDTO goodsInsertInfoDTO = GoodInfoVOConvert.INSTANCE.addParamToDto(goodsAddParam);
        if (goodsInsertInfoDTO.getPrice()<0){
            throw new MyException("商品价格错误");
        }
        if (StringUtils.isEmpty(goodsInsertInfoDTO.getGoodName())){
            throw new NullPointerException("商品名字为空");
        }
        goodsInsertInfoDTO.setGoodStarttime(LocalDateTime.now());
        goodsInsertInfoDTO.setGoodStatus(GoodsStatus.GOODS_IN.getCode());
        goodsInsertInfoDTO.setGoodsNum(UUID.randomUUID().toString());
        goodsInsertInfoDTO.setStuNum(stuParam.getStuNum());
        return frontGoodInfoService.StuAddGoods(goodsInsertInfoDTO);
    }

    /**
     * @Description 学生下架商品(软删除)
     * @param goodsNum
     * @return
     */
    @RequestMapping(value="SouthEast/goods/front/delete",method=RequestMethod.DELETE)
    public Boolean DeleteGoods(String goodsNum){
        return frontGoodInfoService.StuDeleteGoods(goodsNum);
    }

    /**
     * @Description 学生更新自己的商品
     * @param goodsParam
     * @return
     */
    @RequestMapping(value="SouthEast/goods/front/update",method=RequestMethod.GET)
    public Boolean UpdateGoods(GoodsParam goodsParam){
        GoodsInfoDTO goodsUpdateInfoDTO = GoodInfoVOConvert.INSTANCE.paramToDto(goodsParam);
        if (goodsUpdateInfoDTO.getPrice()<0){
            throw new MyException("商品价格错误");
        }
        if (StringUtils.isEmpty(goodsUpdateInfoDTO.getGoodName())){
            throw new NullPointerException("商品名字为空");
        }
        //回填更新时间
        goodsUpdateInfoDTO.setGoodUpdatetime(LocalDateTime.now());
        return frontGoodInfoService.StuUpdateGoods(goodsUpdateInfoDTO);
    }

    /**
     * @Description 学生查询自己名下的某个商品的详情
     * @param stuParam
     * @param goodsNum
     * @return
     */
    @RequestMapping(value="SouthEast/goods/front/selectone",method=RequestMethod.POST)
    public GoodsInfoDTO SelectOneGood(StuParam stuParam,String goodsNum){
        Integer stuNum = stuParam.getStuNum();
        GoodsInfoDTO goodsInfoDTO = frontGoodInfoService.StuSelectSelfGood(stuNum, goodsNum);
        return goodsInfoDTO;
    }

    /**
     * 学生查看自己名下的所有上架商品
     * @param stuNum
     * @return
     */
    @RequestMapping(value="SouthEast/goods/front/selectall",method=RequestMethod.GET)
    public List<GoodsInfoDTO> SelectAllGoods(Integer stuNum){
        return frontGoodInfoService.StuSelectAllGoods(stuNum);
    }

}
