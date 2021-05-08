package com.mayang.api.model.param;

import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.wordnik.swagger.annotations.ApiModel;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value="StuAddParam",description="新增学生信息")
public class StuAddParam implements Serializable {
    @ApiModelProperty(value="学生昵称")
    @NotNull(message="学生姓名不能为空")
    private String name;

    @ApiModelProperty(value="学生真实姓名")
    @NotEmpty(message="学生真实姓名不能为空")
    private String realName;

    @ApiModelProperty(value="学生学号")
    @NotEmpty(message="学生学号不能为空")
    private Integer stuNum;

    @ApiModelProperty(value="学生手机号")
    @NotEmpty(message="学生手机号不能为空")
    private  String phone;

    @ApiModelProperty(value="学生班级")
    private String classRoom;

    @ApiModelProperty(value="学生院系")
    private String department;

    @ApiModelProperty(value="性别 1男 2 女 3 秘密")
    @NotEmpty(message="学生性别不能为空")
    private Integer gender;

    @ApiModelProperty(value="学生个性签名")
    private String personalWord;

    @ApiModelProperty(value="学生微信号")
    @NotEmpty(message="学生微信号不能为空")
    private String weChat;

    @ApiModelProperty(value="学生年龄")
    @NotEmpty(message="学生年龄不能为空")
    private Integer age;

    @ApiModelProperty(value="学生账号密码")
    @NotEmpty(message="学生密码")
    private String key;

//    @ApiModelProperty(value="学生状态  1录取 2在校 3离校/毕业/辞退")
//    @NotEmpty(message="学生状态不能为空")
//    private Integer status;

    @ApiModelProperty(value="学生宿舍")
    private String dorm;

    @ApiModelProperty(value="学生qq号")
    private Integer qqNUM;

    @ApiModelProperty(value="头像链接")
    private String pic;

}
