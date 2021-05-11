package com.mayang.provider.dao.EndUserInfo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>
 * 管理员账户信息表
 * </p>
 *
 * @author mayang
 * @since 2021-5-11
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("end_user")
@Data
public class EndUserDO {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 后台管理员id
     */
    private String userId;

    /**
     * 后台管理员密码
     */
    private String password;
}
