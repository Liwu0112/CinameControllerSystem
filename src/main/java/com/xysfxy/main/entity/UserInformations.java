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
 * @since 2023-11-22
 */
@TableName("user_informations")
public class UserInformations implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String userPhone;

    private String userPassword;

    private Integer userBalance;

    private Integer isDelete;

    private Integer ticketsCount;

    public UserInformations(Integer userId) {
        this.userId = userId;
    }

    public UserInformations() {
    }

    public UserInformations(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(Integer userBalance) {
        this.userBalance = userBalance;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getTicketsCount() {
        return ticketsCount;
    }

    public void setTicketsCount(Integer ticketsCount) {
        this.ticketsCount = ticketsCount;
    }

    @Override
    public String toString() {
        return "UserInformations{" +
        "userId=" + userId +
        ", userName=" + userName +
        ", userPhone=" + userPhone +
        ", userPassword=" + userPassword +
        ", userBalance=" + userBalance +
        ", isDelete=" + isDelete +
        ", ticketsCount=" + ticketsCount +
        "}";
    }
}
