package com.mayang.api.model.param;


import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@ApiModel(value="goodsParam",description="商品信息")
public class GoodsParam {

    @ApiModelProperty(value="商品编号")
    @NotNull(message="商品编号不能为空")
    private String goodsNum;

    @ApiModelProperty(value="商品状态 1上架 2下架 3审核")
    @NotNull(message="商品状态不能为空")
    private Integer goodStatus;

    @ApiModelProperty(value="商品定价")
    @NotNull(message="请告知商品定价")
    private Integer price;

    @ApiModelProperty(value="商品名字")
    @NotNull(message="请描述商品名字")
    private String goodName;

    @ApiModelProperty(value="商品介绍图片的链接")
    @NotNull(message="请上传一张商品介绍图片")
    private String picImg;

    @ApiModelProperty(value="上架学生的学号")
    @NotNull(message="上架学生的学号不能为空")
    private Integer stuNum;

    @ApiModelProperty(value="商品介绍文字")
    @NotNull(message="商品介绍文字不能为空")
    private String goodItems;

    @ApiModelProperty(value="商品上架时间")
    @NotNull(message="商品上架时间不能为空")
    private LocalDateTime goodStarttime;

    @ApiModelProperty(value="商品下架时间")
    private LocalDateTime goodEndtime;

    @ApiModelProperty(value="商品浏览次数")
    @NotNull(message="//")
    private Integer browseNum;

}
