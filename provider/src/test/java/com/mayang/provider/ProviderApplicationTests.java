package com.mayang.provider;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mayang.api.convert.GoodInfoVOConvert;
import com.mayang.api.model.Enum.GoodsStatus;
import com.mayang.api.model.InfoDTO.EndUserDTO;
import com.mayang.api.model.InfoDTO.GoodsInfoDTO;
import com.mayang.api.model.InfoDTO.StuInfoDTO;
import com.mayang.api.model.param.GoodsAddParam;
import com.mayang.api.model.param.StuParam;
import com.mayang.api.utils.MyException;
import com.mayang.provider.Service.Impl.EndUserServiceImpl;
import com.mayang.provider.Service.Impl.FrontGoodInfoServiceImpl;
import com.mayang.provider.Service.Impl.FrontStuInfoServiceImpl;
import com.mayang.provider.dao.StudentInfo.StuInfoDO;
import com.mayang.provider.dao.mapper.StuInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public
class ProviderApplicationTests {
    @Resource
    FrontGoodInfoServiceImpl frontGoodInfoService;
    @Resource
    FrontStuInfoServiceImpl frontStuInfoService;
    @Resource
    EndUserServiceImpl endUserService;


    /**
     * 测试学生信息插入 通过
     */
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

    /**
     * 测试学生上架商品
     */

    @Test
    @Rollback
    public void test2(){
        StuParam stuParam = new StuParam();
        stuParam.setRealName("马扬");
        stuParam.setStuNum(10417114);
        stuParam.setStatus(2);

        GoodsAddParam goodsAddParam = new GoodsAddParam();
        goodsAddParam.setPrice(58);
        goodsAddParam.setGoodName("JAVA技术手册");
        goodsAddParam.setPicImg("null");
//        goodsAddParam.setStuNum(10417114);
        goodsAddParam.setGoodItems("Java学习的绝世秘籍");


        //数据类型转换
        GoodsInfoDTO goodsInsertInfoDTO = GoodInfoVOConvert.INSTANCE.addParamToDto(goodsAddParam);
        //数据校验
        if (goodsInsertInfoDTO.getPrice()<0){
            throw new MyException("商品价格错误");
        }
        if (StringUtils.isEmpty(goodsInsertInfoDTO.getGoodName())){
            throw new NullPointerException("商品名字为空");
        }

        //时间数据回填
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//标注时间格式
        Date dateNow = new Date();
        String startTime =sf.format(dateNow);
        goodsInsertInfoDTO.setGoodStarttime(startTime);

        goodsInsertInfoDTO.setGoodStatus(GoodsStatus.GOODS_IN.getCode());
        goodsInsertInfoDTO.setGoodsNum(UUID.randomUUID().toString());
        goodsInsertInfoDTO.setStuNum(stuParam.getStuNum());
        //provider端插入
        System.out.println(frontGoodInfoService.StuAddGoods(goodsInsertInfoDTO));
    }


    /**
     * 测试后台管理员登录
     */
    @Test
    public void test3(){
        String userName = "mayang";
        String password = "miaomiao";
        System.out.println(endUserService.EndUserLogin(userName, password));


    }

    /**
     * 测试后台管理员注册
     */
    @Test
    public void test4(){
        EndUserDTO endUserDTO = new EndUserDTO();
        endUserDTO.setUserId("wangzi");
        endUserDTO.setPassword("xiaozhao");
        System.out.println(endUserService.EndUserAdd(endUserDTO));


    }

    @Test
    @Rollback(value=true)
    @Transactional//rollback的注解需要和Transactional一起使用后
    public void test5(){
        GoodsInfoDTO goodsInfoDTO = new GoodsInfoDTO();
        goodsInfoDTO.setGoodsNum("5faad270-1a00-476c-8a60-db3ee7cccc4c");
        goodsInfoDTO.setGoodStatus(1);
        goodsInfoDTO.setPrice(1000);
        goodsInfoDTO.setGoodName("Java技术手册");
        goodsInfoDTO.setPicImg("null");
        goodsInfoDTO.setGoodItems("学习Java的绝世秘籍");
        goodsInfoDTO.setStuNum(10417114);
        goodsInfoDTO.setBrowseNum(0);

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//标注时间格式
        Date dateNow = new Date();
        String timeNow =sf.format(dateNow);
        goodsInfoDTO.setGoodUpdatetime(timeNow);

        frontGoodInfoService.StuUpdateGoods(goodsInfoDTO);
    }

}
