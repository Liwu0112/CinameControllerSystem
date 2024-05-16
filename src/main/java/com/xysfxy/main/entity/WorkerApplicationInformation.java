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
@TableName("worker_application_information")
public class WorkerApplicationInformation implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "application_id", type = IdType.AUTO)
    private Integer applicationId;

    private Integer workerId;

    private String workerName;

    private String workerApplication;

    private LocalDateTime applicationDate;

    private Integer applicationResult;

    public WorkerApplicationInformation(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public WorkerApplicationInformation() {
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
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

    public String getWorkerApplication() {
        return workerApplication;
    }

    public void setWorkerApplication(String workerApplication) {
        this.workerApplication = workerApplication;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Integer getApplicationResult() {
        return applicationResult;
    }

    public void setApplicationResult(Integer applicationResult) {
        this.applicationResult = applicationResult;
    }

    @Override
    public String toString() {
        return "WorkerApplicationInformation{" +
        "applicationId=" + applicationId +
        ", workerId=" + workerId +
        ", workerName=" + workerName +
        ", workerApplication=" + workerApplication +
        ", applicationDate=" + applicationDate +
        ", applicationResult=" + applicationResult +
        "}";
    }
}
