package com.mayang.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mayang.api.BusinessGoodInfoService.EndGoodInfoService;
import com.mayang.api.model.InfoDTO.GoodsInfoDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

    //127.0.0.1:9094/SouthEast/goods/end/delete?goodsNum=32fb6efe-378e-4ec4-8b1b-95e6c8198a0a
    //检测结果 : 逻辑正确 , 完成检测
    @RequestMapping(value="SouthEast/goods/end/delete",method=RequestMethod.GET)
    @ResponseBody
    public Boolean DeleteGoodsInfo (String goodsNum){
        return endGoodInfoService.deleteGoods(goodsNum);
    }

    /**
     * @Description 管理员根据商品编号查询商品信息
     * @param goodsNum
     * @return
     */
    //127.0.0.1:9094/SouthEast/goods/end/select?goodsNum=32fb6efe-378e-4ec4-8b1b-95e6c8198a0a
    //检测结果 : 逻辑正确 , 完成检测
    @RequestMapping(value="SouthEast/goods/end/select",method=RequestMethod.GET)
    @ResponseBody
    public GoodsInfoDTO SelectGoodsInfo (String goodsNum){
        GoodsInfoDTO goodsInfoDTO = endGoodInfoService.selectGoodsInfo(goodsNum);
        return goodsInfoDTO;
    }

}
