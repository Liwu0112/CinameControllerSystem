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
@TableName("retail_informations")
public class RetailInformations implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "retail_id", type = IdType.AUTO)
    private Integer retailId;

    private String retailName;

    private Integer retailPrice;

    private String retailIntroduce;

    private Integer retailCount;

    private Integer isDelete;

    public RetailInformations(Integer retailId) {
        this.retailId = retailId;
    }

    public RetailInformations() {
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

    public Integer getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Integer retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getRetailIntroduce() {
        return retailIntroduce;
    }

    public void setRetailIntroduce(String retailIntroduce) {
        this.retailIntroduce = retailIntroduce;
    }

    public Integer getRetailCount() {
        return retailCount;
    }

    public void setRetailCount(Integer retailCount) {
        this.retailCount = retailCount;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "RetailInformations{" +
        "retailId=" + retailId +
        ", retailName=" + retailName +
        ", retailPrice=" + retailPrice +
        ", retailIntroduce=" + retailIntroduce +
        ", retailCount=" + retailCount +
        ", isDelete=" + isDelete +
        "}";
    }
}
