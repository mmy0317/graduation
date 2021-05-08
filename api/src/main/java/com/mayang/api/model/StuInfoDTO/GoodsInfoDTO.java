package com.mayang.api.model.StuInfoDTO;

import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
public class GoodsInfoDTO {

    /**
     * 商品编号
     */
    private String goodsNum;

    /**
     * 商品状态 1上架 2下架 3审核
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
    private LocalDateTime goodStarttime;

    /**
     * 商品下架时间
     */
    private LocalDateTime goodEndtime;

    private Integer browseNum;

}
