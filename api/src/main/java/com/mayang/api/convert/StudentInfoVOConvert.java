package com.mayang.api.convert;

import com.mayang.api.model.InfoDTO.EndUserDTO;
import com.mayang.api.model.InfoDTO.OrdersDTO;
import com.mayang.api.model.InfoDTO.StuInfoDTO;
import com.mayang.api.model.param.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface StudentInfoVOConvert {
    StudentInfoVOConvert INSTANCE = Mappers.getMapper(StudentInfoVOConvert.class);

    StuInfoDTO addParamToDto(StuAddParam stuAddParam);

    StuInfoDTO updateParamToDto(StuEndUpdateParam stuEndUpdateParam);

    StuInfoDTO paramToDto(StuParam stuParam);

    EndUserDTO endUserParamToDto (EndUserParam endUserParam);

    OrdersDTO ordersParamToDto(OrdersParam ordersParam);

}
