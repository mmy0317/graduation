package com.mayang.api.convert;

import com.mayang.api.model.StuInfoDTO.EndUserDTO;
import com.mayang.api.model.param.EndUserParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EndUserFrontConvert {
    EndUserFrontConvert INSTANCE =Mappers.getMapper(EndUserFrontConvert.class);

    EndUserDTO endUserParamToDto(EndUserParam endUserParam);
}
