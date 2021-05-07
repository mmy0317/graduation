package com.mayang.provider.dao.StudentInfo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生信息详情表
 * </p>
 *
 * @author mayang
 * @since 2021-5-7
 */

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("stu_main_info")
@Data
public class StuInfoDO {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 昵称
     */
    private Integer name;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 学号
     */
    private Integer stuNum;

    /**
     * 班级
     */
    private String classRoom;

    /**
     * 院系
     */
    private String department;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 个签
     */
    private String personalWord;

    /**
     * 手机号
     */
    private Integer phone;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * qq号
     */
    private Integer qqNum;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 密码
     */
    private String key;

    /**
     * 学生状态
     */
    private Integer stuStatus;

    /**
     * 学生宿舍
     */
    private String stuDrom;

    /**
     * 头像链接
     */
    private String pic;



}
