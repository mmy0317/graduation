package com.mayang.api.BusinessGoodInfoService;

import com.mayang.api.model.StuInfoDTO.GoodsInfoDTO;
import com.mayang.api.model.param.GoodsAddParam;
import com.mayang.api.model.param.GoodsParam;

public interface FrontGoodInfoService {

    /**
     * @Decription  学生上架商品
     * @param goodsInfoDTO
     * @return
     */
    Boolean StuAddGoods (GoodsInfoDTO goodsInfoDTO);

    /**
     * @Decription 学生下架自己的商品
     * @param goodsNum
     * @return
     */
    Boolean StuDeleteGoods (String goodsNum);

    /**
     * @Decription 学生更新自己的商品
     * @param goodsInfoDTO
     * @return
     */
    Boolean StuUpdateGoods (GoodsInfoDTO goodsInfoDTO);

    /**
     * @Decription 学生查看自己上架的某个商品的详情
     * @param stuNum
     * @return
     */
    GoodsInfoDTO StuSelectSelfGood (Integer stuNum , String goodsNum);

    //todo:查找自己名下的商品的信息(hash)


}
