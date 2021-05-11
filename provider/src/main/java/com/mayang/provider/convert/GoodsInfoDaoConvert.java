package com.mayang.provider.convert;

import com.mayang.api.model.InfoDTO.GoodsInfoDTO;
import com.mayang.provider.dao.GoodsInfo.GoodsInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GoodsInfoDaoConvert {

    GoodsInfoDaoConvert INSTANCE = Mappers.getMapper(GoodsInfoDaoConvert.class);

    GoodsInfoDTO goodsDoToDto (GoodsInfoDO goodsInfoDO);

    GoodsInfoDO goodsDtoToDo (GoodsInfoDTO goodsInfoDTO);

}
