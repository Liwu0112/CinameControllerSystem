package com.xysfxy.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zengjing
 * @since 2023-11-23
 */
@TableName("user_buyretsil")
public class UserBuyretsil implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "orders_id", type = IdType.AUTO)
    private Integer ordersId;

    private Integer userId;

    private Integer retailId;

    private String retailName;

    private Integer payPrice;


    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRetailId() {
        return retailId;
    }

    public void setRetailId(Integer retailId) {
        this.retailId = retailId;
    }

    public String getRetailName() {
        return retailName;
    }

    public void setRetailName(String retailName) {
        this.retailName = retailName;
    }

    public Integer getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Integer payPrice) {
        this.payPrice = payPrice;
    }

    @Override
    public String toString() {
        return "UserBuyretsil{" +
        "ordersId=" + ordersId +
        ", userId=" + userId +
        ", retailId=" + retailId +
        ", retailName=" + retailName +
        ", payPrice=" + payPrice +
        "}";
    }
}
