package com.mayang.api.model.InfoDTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersDTO implements Serializable {

    /**
     * 被交易商品唯一码
     */
    private String goodsNum;
    /**
     * 出借/出租学生的学号
     */
    private Integer rstuNum;

    /**
     * 请求租借商品学生的学号
     */
    private Integer bstuNum;

    /**
     * 订单的唯一编号
     */
    private String orderNum;

    /**
     * 订单的创建时间
     */
    private String startDate;

    /**
     * 订单完成时间
     */
    private String finishDate;

    /**
     * 订单状态 1进行中 2已取消 3订单完成
     */
    private Integer orderStatus;

    /**
     * 订单取消时间
     */
    private String ordersCancel;

}
