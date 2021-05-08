package com.mayang.api.BusinessGoodInfoService;

import com.mayang.api.model.StuInfoDTO.GoodsInfoDTO;


public interface EndGoodInfoService {
    /**
     * @Description:管理员下架商品(修改商品状态)
     * @param goodsNum
     * @return Boolean
     */
    Boolean deleteGoods (String goodsNum);

    /**
     * @Description:管理员根据商品号查询商品详情
     * @param goodsNum
     * @return GoodsInfoDTO
     */
    GoodsInfoDTO selectGoodsInfo(String goodsNum);
}
