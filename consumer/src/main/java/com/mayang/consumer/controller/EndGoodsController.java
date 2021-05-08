package com.mayang.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mayang.api.BusinessGoodInfoService.EndGoodInfoService;
import com.mayang.api.model.StuInfoDTO.GoodsInfoDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndGoodsController {

    @Reference
    EndGoodInfoService endGoodInfoService;

    /**
     * @Description 管理员根据商品编号下架商品
     * @param goodsNum
     * @return
     */
    @RequestMapping(value="SouthEast/goods/end/delete",method=RequestMethod.DELETE)
    public Boolean DeleteGoodsInfo (String goodsNum){
        return endGoodInfoService.deleteGoods(goodsNum);
    }

    /**
     * @Description 管理员根据商品编号查询商品信息
     * @param goodsNum
     * @return
     */
    @RequestMapping(value="SouthEast/goods/end/select",method=RequestMethod.POST)
    public GoodsInfoDTO SelectGoodsInfo (String goodsNum){
        GoodsInfoDTO goodsInfoDTO = endGoodInfoService.selectGoodsInfo(goodsNum);
        return goodsInfoDTO;
    }

}
