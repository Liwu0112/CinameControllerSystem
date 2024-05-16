package com.xysfxy.main.dto.resp;

import com.xysfxy.main.utils.DesensitizationUtil;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkerInformationRespDto {
    //员工id
    private Integer workerId;
    //员工姓名
    private String workerName;
    //员工入职时间
    private LocalDateTime workerIntodate;
    //员工岗位
    private String workerPost;
    //员工年龄
    private String workerAge;
    //员工性别
    private String workerSex;
    //员工电话（脱敏）
    private String workerPhone;
    //员工上班状态
    private Integer isRelex;
    //手机脱敏
    public String getWorkerPhone() {
        return DesensitizationUtil.mobileEncrypt(workerPhone);
    }
}
