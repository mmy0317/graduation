package com.mayang.provider;

import com.mayang.api.model.InfoDTO.StuInfoDTO;
import com.mayang.provider.Service.Impl.FrontStuInfoServiceImpl;
import com.mayang.provider.dao.StudentInfo.StuInfoDO;
import com.mayang.provider.dao.mapper.StuInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public
class ProviderApplicationTests {
    @Resource
    StuInfoMapper stuInfoMapper;
    @Resource
    FrontStuInfoServiceImpl frontStuInfoService;
    @Test
    public void test1(){
        //16个字段
        StuInfoDTO stuInfoDTO = new StuInfoDTO();
//        stuInfoDTO.setId(0);
        stuInfoDTO.setName("mayang");
        stuInfoDTO.setRealName("马扬");
        stuInfoDTO.setStuNum(10417114);
//        stuInfoDTO.setClassRoom("");
//        stuInfoDTO.setDepartment("");
        stuInfoDTO.setGender(1);
        stuInfoDTO.setPersonalWord("长风");
        stuInfoDTO.setPhone("18851976933");
        stuInfoDTO.setWechat("m18851976933");
        stuInfoDTO.setQqNum("1659254531");
        stuInfoDTO.setAge(21);
        stuInfoDTO.setPassword("leimiaomiao");
        stuInfoDTO.setStuStatus(2);
        stuInfoDTO.setStuDrom("桃三");
//        stuInfoDTO.setStuPic("");

        frontStuInfoService.StuAddInfo(stuInfoDTO);




    }

}
