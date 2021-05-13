package com.mayang.api.model.param;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value="odersparam",description="订单创建前端需要传递的参数")
public class OrdersParam implements Serializable {

    @ApiModelProperty(value="被交易商品唯一码")
    @NotNull(message="被交易商品不能为空")
    private String goodsNum;

    @ApiModelProperty(value="出借/出租学生的学号")
    @NotNull(message="学生学号不能为空")
    private Integer rstuNum;

    @ApiModelProperty(value="请求租借商品学生的学号")
    @NotNull(message="学生学号不能为空")
    private Integer bstuNum;


}
