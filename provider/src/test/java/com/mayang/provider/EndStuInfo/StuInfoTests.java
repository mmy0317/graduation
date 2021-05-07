package com.mayang.provider.EndStuInfo;

import com.mayang.api.BusinessEndService.StudentInfoService;
import com.mayang.api.model.StuInfoDTO.StuInfoDTO;
import com.mayang.provider.ProviderApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
public  class StuInfoTests extends ProviderApplication {

    @Resource
    StudentInfoService studentInfoService;

    @Test
    @Rollback
    @Transactional
    public void stuInfoCreat(){
        StuInfoDTO stuInfoDTO = new StuInfoDTO();
        stuInfoDTO.setName("mmy");
        stuInfoDTO.setRealName("马扬");
        stuInfoDTO.setStuNum(10417114);
        stuInfoDTO.setPhone("18851976933");
        stuInfoDTO.setGender(1);
        stuInfoDTO.setPersonalWord("dsjdb");
        stuInfoDTO.setWeChat("18851976933");
        stuInfoDTO.setAge(21);
        stuInfoDTO.setKey("212317mayang");
        stuInfoDTO.setStatus(2);
        stuInfoDTO.setDorm("桃园三舍403");
        stuInfoDTO.setQqNUM(1659254531);
        studentInfoService.AddInfo(stuInfoDTO);
        //todo:空指针异常
    }

}
