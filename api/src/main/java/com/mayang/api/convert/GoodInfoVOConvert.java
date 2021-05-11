package com.mayang.api.convert;

import com.mayang.api.model.InfoDTO.GoodsInfoDTO;
import com.mayang.api.model.param.GoodsAddParam;
import com.mayang.api.model.param.GoodsParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GoodInfoVOConvert {
    GoodInfoVOConvert INSTANCE =Mappers.getMapper(GoodInfoVOConvert.class);

    GoodsInfoDTO addParamToDto(GoodsAddParam goodsAddParam);

    GoodsInfoDTO paramToDto(GoodsParam goodsParam);
}
