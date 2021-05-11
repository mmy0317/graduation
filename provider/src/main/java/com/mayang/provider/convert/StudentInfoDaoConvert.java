package com.mayang.provider.convert;


import com.mayang.api.model.InfoDTO.StuInfoDTO;
import com.mayang.provider.dao.StudentInfo.StuInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentInfoDaoConvert {

    StudentInfoDaoConvert INSTANCE = Mappers.getMapper(StudentInfoDaoConvert.class);

    StuInfoDO stuDtoToDo(StuInfoDTO stuInfoDTO);

    StuInfoDTO stuDoToDto(StuInfoDO stuInfoDO);
}
