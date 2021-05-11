package com.mayang.api.convert;

import com.mayang.api.model.InfoDTO.EndUserDTO;
import com.mayang.api.model.InfoDTO.StuInfoDTO;
import com.mayang.api.model.param.EndUserParam;
import com.mayang.api.model.param.StuAddParam;
import com.mayang.api.model.param.StuParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface StudentInfoVOConvert {
    StudentInfoVOConvert INSTANCE = Mappers.getMapper(StudentInfoVOConvert.class);

    StuInfoDTO addParamToDto(StuAddParam stuAddParam);

    StuInfoDTO paramToDto(StuParam stuParam);

    EndUserDTO endUserParamToDto (EndUserParam endUserParam);

}
