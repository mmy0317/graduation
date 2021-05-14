package com.mayang.api.model.param;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@ApiModel(value="StuEndUpdateParam",description="后台更新学生信息")
public class StuEndUpdateParam implements Serializable {

    @ApiModelProperty(value="学生学号")
    @NotEmpty(message="学生学号不能为空")
    private Integer stuNum;

    @ApiModelProperty(value="学生班级")
    private String classRoom;

    @ApiModelProperty(value="学生院系")
    private String department;

    @ApiModelProperty(value="学生状态  1录取 2在校 3离校/毕业/辞退")
    @NotEmpty(message="学生状态不能为空")
    private Integer status;
}
