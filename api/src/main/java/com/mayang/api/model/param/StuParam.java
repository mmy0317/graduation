package com.mayang.api.model.param;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@ApiModel(value="StuParam",description="学生信息查询")
@Data
public class StuParam {

    @ApiModelProperty(value="学生真实姓名")
    @NotEmpty(message="学生真实姓名不能为空")
    private String realName;

    @ApiModelProperty(value="学生学号")
    @NotEmpty(message="学生学号不能为空")
    private Integer stuNum;


    @ApiModelProperty(value="学生状态  1录取 2在校 3离校/毕业/辞退")
    @NotEmpty(message="学生状态不能为空")
    private Integer status;

}
