package com.mayang.api.model.InfoDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class GoodsInfoDTO implements Serializable {

    /**
     * 商品编号
     */
    private String goodsNum;

    /**
     * 商品状态 1上架 2下架
     */
    private Integer goodStatus;

    private Integer price;

    private String goodName;

    private String picImg;

    private Integer stuNum;

    private String goodItems;

    /**
     *商品上架时间
     */
    private String goodStarttime;

    /**
     * 商品下架时间
     */
    private String goodEndtime;

    /**
     * 浏览量
     */
    private Integer browseNum;

    /**
     * 商品更新时间
     */
    private String goodUpdatetime;

}
