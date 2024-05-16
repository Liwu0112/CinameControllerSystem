package com.xysfxy.main.service;

import com.xysfxy.main.entity.AdminInformations;
import com.xysfxy.main.entity.WorkerApplicationInformation;
import com.xysfxy.main.entity.WorkerInformations;


import java.util.List;

public interface AdminService {
    /**
     * 管理员查看员工信息
     */
    List<WorkerInformations> adminSelectAllWorker();
    /**
     * 管理员根据id删除员工
     */
    int adminDeleteById(Integer workerId);
    /**
     * 管理员根据id查找员工
     */
    WorkerInformations adminSelectById(Integer workerId);
    /**
     * 管理员修改员工信息
     */
    int adminChangeWorkerPost(WorkerInformations workerinformations);
    /**
     * 管理员新增员工信息
     */
    int adminInsterWorker(WorkerInformations workerinformations);
    /**
     * 管理员查看员工申请
     */
    List<WorkerApplicationInformation> adminSelectApplication();
    /**
     * 管理员根据id同意员工申请
     * （数据库删除申请内容）
     * （员工表员工显示状态改为不显示）
     */
    int adminAgreeApplication(Integer applicationId);
    /**
     * 管理员登录
     */
    AdminInformations adminLogin(String adminName,String adminPassword);

    /**
     * 根据applicationId查找员工信息
     */
    WorkerInformations adminSelectByApplicationId(Integer applicationId);
    /**
     * 管理员查看已处理信息
     */
    List<WorkerApplicationInformation> adminSelectOverApplication();
    /**
     * 管理员拒绝员工申请
     */
    int adminRefuseApplication(Integer applicationId);
    /**
     * 管理员同意员工辞职申请
     */
    int adminAgreeIsDelete(Integer workerId);
    /**
     * 管理员同意员工休假申请
     */
    int adminAgreeIsRelex(Integer workerId);
    /**
     * 管理员同意员工复工申请
     */
    int adminAgreeOverRelex(Integer workerId);
}
