package com.mayang.provider;

import com.mayang.api.BusinessEndService.StudentInfoService;
import com.mayang.api.model.StuInfoDTO.StuInfoDTO;
import com.mayang.provider.dao.mapper.StuInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
class ProviderApplicationTests {


    @Resource
    StudentInfoService studentInfoService;

    @Test
    void contextLoads() {
    }



    @Test
//    @Rollback
//    @Transactional
    public void stuInfoCreat(){
        StuInfoDTO stuInfoDTO = new StuInfoDTO();
        stuInfoDTO.setName("mmy");
        stuInfoDTO.setRealName("马扬");
        stuInfoDTO.setStuNum(10417114);
        stuInfoDTO.setPhone("18851976933");
        stuInfoDTO.setClassRoom("");
        stuInfoDTO.setDepartment("");
        stuInfoDTO.setGender(1);
        stuInfoDTO.setPersonalWord(" ");
        stuInfoDTO.setWeChat("18851976933");
        stuInfoDTO.setAge(21);
        stuInfoDTO.setKey("212317mayang");
        stuInfoDTO.setStatus(2);
        stuInfoDTO.setDorm("桃园三舍403");
        stuInfoDTO.setQqNUM(1659254531);
        stuInfoDTO.setPic(" ");
        studentInfoService.AddInfo(stuInfoDTO);
    }

}
