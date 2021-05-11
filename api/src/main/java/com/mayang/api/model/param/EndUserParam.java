package com.mayang.api.model.param;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value="endUserParam",description="后台管理人员信息")
public class EndUserParam {

    @ApiModelProperty(value="后台管理员id")
    @NotNull(message="id不能为空")
    private String userId;

    @ApiModelProperty(value="后台管理员密码")
    @NotNull(message="密码不能为空")
    private String userPassword;
}
