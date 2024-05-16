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
 * @since 2023-11-23
 */
@TableName("worker_informations")
public class WorkerInformations implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "worker_id", type = IdType.AUTO)
    private Integer workerId;

    private String workerName;

    private String workerAge;

    private String workerSex;

    private LocalDateTime workerIntodate;

    private String workerPost;

    private String workerPhone;

    private String workerPassword;

    private Integer isDelete;

    private Integer isRelex;

    public WorkerInformations(Integer workerId) {
        this.workerId = workerId;
    }

    public WorkerInformations() {
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerAge() {
        return workerAge;
    }

    public void setWorkerAge(String workerAge) {
        this.workerAge = workerAge;
    }

    public String getWorkerSex() {
        return workerSex;
    }

    public void setWorkerSex(String workerSex) {
        this.workerSex = workerSex;
    }

    public LocalDateTime getWorkerIntodate() {
        return workerIntodate;
    }

    public void setWorkerIntodate(LocalDateTime workerIntodate) {
        this.workerIntodate = workerIntodate;
    }

    public String getWorkerPost() {
        return workerPost;
    }

    public void setWorkerPost(String workerPost) {
        this.workerPost = workerPost;
    }

    public String getWorkerPhone() {
        return workerPhone;
    }

    public void setWorkerPhone(String workerPhone) {
        this.workerPhone = workerPhone;
    }

    public String getWorkerPassword() {
        return workerPassword;
    }

    public void setWorkerPassword(String workerPassword) {
        this.workerPassword = workerPassword;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsRelex() {
        return isRelex;
    }

    public void setIsRelex(Integer isRelex) {
        this.isRelex = isRelex;
    }

    @Override
    public String toString() {
        return "WorkerInformations{" +
        "workerId=" + workerId +
        ", workerName=" + workerName +
        ", workerAge=" + workerAge +
        ", workerSex=" + workerSex +
        ", workerIntodate=" + workerIntodate +
        ", workerPost=" + workerPost +
        ", workerPhone=" + workerPhone +
        ", workerPassword=" + workerPassword +
        ", isDelete=" + isDelete +
        ", isRelex=" + isRelex +
        "}";
    }
}
