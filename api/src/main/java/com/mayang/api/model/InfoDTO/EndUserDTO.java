package com.mayang.api.model.InfoDTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class EndUserDTO implements Serializable {

    /**
     * 后台管理员id
     */
    private String userId;

    /**
     * 后台管理员密码
     */
    private String userPassword;
}
