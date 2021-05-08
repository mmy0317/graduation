package com.mayang.provider.dao.GoodsInfo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 商品信息详情表
 * </p>
 *
 * @author mayang
 * @since 2021-5-8
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("stu_good")
@Data
public class GoodsInfoDO {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品编号
     */
    private String goodsNum;

    /**
     * 商品状态 1上架 2下架 3审核
     */
    private Integer goodStatus;

    /**
     * 商品定价
     */
    private Integer price;

    /**
     * 商品名字
     */
    private String goodName;

    /**
     * 商品介绍图片的链接
     */
    private String picImg;

    /**
     * 上架学生的学号
     */
    private Integer stuNum;

    /**
     * 商品介绍文字
     */
    private String goodItems;

    /**
     *商品上架时间
     */
    private LocalDateTime goodStarttime;

    /**
     * 商品下架时间
     */
    private LocalDateTime goodEndtime;

    /**
     * 商品浏览次数
     */
    private Integer browseNum;

    /**
     * 商品更新时间
     */
    private LocalDateTime goodUpdatetime;

}
