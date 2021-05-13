package com.mayang.provider.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mayang.provider.dao.GoodsInfo.GoodsInfoDO;
import com.mayang.provider.dao.StudentInfo.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrdersInfoMapper extends BaseMapper<OrderDO> {
    @Select("SELECT * FROM stu_good WHERE good_status = 1 AND goods_num = #{goodsNum}")
    GoodsInfoDO selectGoods (@Param("goodsNum")String goodsNum);
}
