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
        frontStuInfoService.StuAddInfo(stuInfoDTO);




    }

}
