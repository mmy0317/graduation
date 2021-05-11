package com.mayang.api.model.InfoDTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class StuInfoDTO implements Serializable {

    private String name;

    private String realName;

    private Integer stuNum;

    private  String phone;

    private String classRoom;

    private String department;

    /**
     * 性别 1男 2 女 3 秘密
     */
    private Integer gender;

    private String personalWord;

    private String weChat;

    private Integer age;

    private String key;

    /**
     * 学生状态  1录取 2在校 3离校/毕业/辞退
     */
    private Integer stuStatus;

    private String stuDorm;

    private Integer qqNUM;

    private String stuPic;

}
