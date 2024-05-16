package com.xysfxy.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zengjing
 * @since 2023-11-22
 */
@TableName("user_select_mytickets")
public class UserSelectMytickets implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "ticket_id", type = IdType.AUTO)
    private Integer ticketId;

    private Integer userId;

    private String cinameName;

    private LocalDateTime beginData;

    private LocalDateTime overData;

    private String cinamePlace;


    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCinameName() {
        return cinameName;
    }

    public void setCinameName(String cinameName) {
        this.cinameName = cinameName;
    }

    public LocalDateTime getBeginData() {
        return beginData;
    }

    public void setBeginData(LocalDateTime beginData) {
        this.beginData = beginData;
    }

    public LocalDateTime getOverData() {
        return overData;
    }

    public void setOverData(LocalDateTime overData) {
        this.overData = overData;
    }

    public String getCinamePlace() {
        return cinamePlace;
    }

    public void setCinamePlace(String cinamePlace) {
        this.cinamePlace = cinamePlace;
    }

    @Override
    public String toString() {
        return "UserSelectMytickets{" +
        "ticketId=" + ticketId +
        ", userId=" + userId +
        ", cinameName=" + cinameName +
        ", beginData=" + beginData +
        ", overData=" + overData +
        ", cinamePlace=" + cinamePlace +
        "}";
    }
}
