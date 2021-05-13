package com.mayang.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mayang.api.BusinessGoodInfoService.FrontGoodInfoService;
import com.mayang.api.convert.GoodInfoVOConvert;
import com.mayang.api.model.Enum.GoodsStatus;
import com.mayang.api.model.InfoDTO.GoodsInfoDTO;
import com.mayang.api.model.param.GoodsAddParam;
import com.mayang.api.model.param.GoodsParam;
import com.mayang.api.model.param.StuParam;
import com.mayang.api.utils.MyException;
import com.alibaba.dubbo.common.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class FrontGoodsController {

    @Reference
    FrontGoodInfoService frontGoodInfoService;

    /**
     * @Description 学生创建需要上架的商品
     * @param goodsAddParam
     * @return
     */
    //127.0.0.1:9094/SouthEast/goods/front/creat?price=1000&goodName=Java技术手册&picImg=null&goodItems=学习Java的绝世秘籍&realName=马扬&stuNum=10417114&status=2
    //检测结果:成功插入,返回true
    @RequestMapping(value="SouthEast/goods/front/creat",method=RequestMethod.GET)
    @ResponseBody
    public Boolean CreatGoods(GoodsAddParam goodsAddParam , StuParam stuParam){
        GoodsInfoDTO goodsInsertInfoDTO = GoodInfoVOConvert.INSTANCE.addParamToDto(goodsAddParam);
        if (goodsInsertInfoDTO.getPrice()<0){
            throw new MyException("商品价格错误");
        }
        if (StringUtils.isEmpty(goodsInsertInfoDTO.getGoodName())){
            throw new NullPointerException("商品名字为空");
        }
        //回填时间
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//标注时间格式
        Date dateNow = new Date();
        String timeNow =sf.format(dateNow);
        goodsInsertInfoDTO.setGoodStarttime(timeNow);
        //其余数据回填
        goodsInsertInfoDTO.setGoodStatus(GoodsStatus.GOODS_IN.getCode());
        goodsInsertInfoDTO.setGoodsNum(UUID.randomUUID().toString());
        goodsInsertInfoDTO.setStuNum(stuParam.getStuNum());
        return frontGoodInfoService.StuAddGoods(goodsInsertInfoDTO);
    }

    /**
     * @Description 学生下架商品(软删除)
     * @param goodsNum
     * @return
     */
    //127.0.0.1:9094/SouthEast/goods/front/delete?goodsNum=dfe87a1f-9b76-440d-9f22-ad7458e1733a
    //检测结果 : 成功下架,返回true
    @RequestMapping(value="SouthEast/goods/front/delete",method=RequestMethod.GET)
    @ResponseBody
    public Boolean DeleteGoods(String goodsNum){
        return frontGoodInfoService.StuDeleteGoods(goodsNum);
    }

    /**
     * @Description 学生更新自己的商品
     * @param goodsParam
     * @return
     */
    //127.0.0.1:9094/SouthEast/goods/front/update?goodsNum=717a1b6b-c964-4b2f-937e-fef4f7cadce2&goodStatus=1&price=120&goodName=java技术手册&picImg=null&goodItems=学习Java的绝世秘籍&stuNum=10417114&browseNum=0
    //检测结果 : 可以完成修改
    @RequestMapping(value="SouthEast/goods/front/update",method=RequestMethod.GET)
    @ResponseBody
    public Boolean UpdateGoods(GoodsParam goodsParam){
        GoodsInfoDTO goodsUpdateInfoDTO = GoodInfoVOConvert.INSTANCE.paramToDto(goodsParam);
        if (goodsUpdateInfoDTO.getPrice()<0){
            throw new MyException("商品价格错误");
        }
        if (StringUtils.isEmpty(goodsUpdateInfoDTO.getGoodName())){
            throw new NullPointerException("商品名字为空");
        }
        //回填更新时间
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//标注时间格式
        Date dateNow = new Date();
        String timeNow =sf.format(dateNow);
        goodsUpdateInfoDTO.setGoodUpdatetime(timeNow);
        return frontGoodInfoService.StuUpdateGoods(goodsUpdateInfoDTO);
    }

    /**
     * @Description 学生查询自己名下的某个商品的详情
     * @param stuNum
     * @param goodsNum
     * @return
     */
    //127.0.0.1:9094/SouthEast/goods/front/selectone?stuNum=10417114&goodsNum=5faad270-1a00-476c-8a60-db3ee7cccc4c
    //监测成果:可成功返回数据
    @RequestMapping(value="SouthEast/goods/front/selectone",method=RequestMethod.GET)
    @ResponseBody
    public GoodsInfoDTO SelectOneGood(Integer stuNum,String goodsNum){
        GoodsInfoDTO goodsInfoDTO = frontGoodInfoService.StuSelectSelfGood(stuNum, goodsNum);
        return goodsInfoDTO;
    }

    /**
     * 学生查看自己名下的所有上架商品
     * @param stuNum
     * @return
     */
    //127.0.0.1:9094/SouthEast/goods/front/selectall?stuNum=10417114
    //检测结果 : 可以完成列表的查询
    @RequestMapping(value="SouthEast/goods/front/selectall",method=RequestMethod.GET)
    @ResponseBody
    public List<GoodsInfoDTO> SelectAllGoods(Integer stuNum){
        return frontGoodInfoService.StuSelectAllGoods(stuNum);
    }

}
