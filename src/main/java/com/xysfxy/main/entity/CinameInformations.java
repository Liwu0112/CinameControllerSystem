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
@TableName("ciname_informations")
public class CinameInformations implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "ciname_id", type = IdType.AUTO)
    private Integer cinameId;

    private String cinameName;

    private LocalDateTime beginDate;

    private LocalDateTime overDate;

    private String cinamePlace;

    private Integer cinamePrice;

    private Integer isDelete;

    public CinameInformations() {
    }

    public CinameInformations(Integer cinameId) {
        this.cinameId = cinameId;
    }

    public Integer getCinameId() {
        return cinameId;
    }

    public void setCinameId(Integer cinameId) {
        this.cinameId = cinameId;
    }

    public String getCinameName() {
        return cinameName;
    }

    public void setCinameName(String cinameName) {
        this.cinameName = cinameName;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDateTime getOverDate() {
        return overDate;
    }

    public void setOverDate(LocalDateTime overDate) {
        this.overDate = overDate;
    }

    public String getCinamePlace() {
        return cinamePlace;
    }

    public void setCinamePlace(String cinamePlace) {
        this.cinamePlace = cinamePlace;
    }

    public Integer getCinamePrice() {
        return cinamePrice;
    }

    public void setCinamePrice(Integer cinamePrice) {
        this.cinamePrice = cinamePrice;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "CinameInformations{" +
        "cinameId=" + cinameId +
        ", cinameName=" + cinameName +
        ", beginDate=" + beginDate +
        ", overDate=" + overDate +
        ", cinamePlace=" + cinamePlace +
        ", cinamePrice=" + cinamePrice +
        ", isDelete=" + isDelete +
        "}";
    }
}
