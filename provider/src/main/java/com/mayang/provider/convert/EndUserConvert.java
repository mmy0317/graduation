package com.mayang.provider.convert;

import com.mayang.api.model.InfoDTO.EndUserDTO;
import com.mayang.provider.dao.EndUserInfo.EndUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EndUserConvert {
    EndUserConvert INSTANCE =Mappers.getMapper(EndUserConvert.class);

    EndUserDTO endUserDoToDto (EndUserDO endUserDO);

    EndUserDO endUserDtoToDo (EndUserDTO endUserDTO);
}
