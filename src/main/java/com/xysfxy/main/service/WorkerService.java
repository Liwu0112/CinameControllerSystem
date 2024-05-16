package com.xysfxy.main.service;



import com.xysfxy.main.entity.*;

import java.util.List;

public interface WorkerService {
    /**
     * 员工查询所有电影信息
     * @return
     */
    List<CinameInformations> workerSelectAllCiname();

    /**
     * 员工根据id修改电影信息
     * @return
     */
    int wokerUpdateCiname(CinameInformations cinemainformations);
    /**
     * 员工根据id查找电影信息
     */
    CinameInformations workerGetById(Integer cinameId);
    /**
     * 员工根据id删除电影信息
     */
    int workerDeleteCiname(Integer cinameId);
    /**
     * 员工根据重新新增电影信息
     */
    int workerAddCiname(Integer cinameId);
    /**
     * 员工新增电影
     */
    int workerInsterCiname(CinameInformations cinemainformations);
    /**
     * 员工彻底删除电影
     * 对于已经过时的电影，需要进行删除处理
     */
    int workerDeleteCinameOutRight(Integer cinameId);
    /**
     * 员工申请休假
     */
    int workerIsRelex(WorkerApplicationInformation workerapplicationinformation);
    /**
     * 员工查看自己的申请信息
     */
    List<WorkerApplicationInformation> workerSelectMyApplication(String workerName);
    /**
     * 员工登录
     */
    WorkerInformations workerLogin(String workerName, String workerPassword);
    /**
     * 根据员工名称查找员工信息
     */
    WorkerInformations workerSelectByWorkerName(String workerName);
    /**
     * 员工取消申请
     */
    int workerDeleteApplication(Integer applicationId);
    /**
     * 员工查看历史申请信息
     */
    List<WorkerApplicationInformation> workerSelectHistoryApplication(String workerName);
    /**
     * 员工查看零售信息
     */
    List<RetailInformations> workerSelectAllRetail();
    /**
     * 员工新增零售
     */
    int workerInsterRetail(RetailInformations retailInformations);
    /**
     * 员工根据零售id补货
     */
    int workerAddRetail(Integer retailId,Integer retailCount);
    /**
     * 根据retailId获取零售信息
     */
    RetailInformations workerSelectRetailById(Integer retailId);
    /**
     * 员工根据retailId对商品进行下架处理（逻辑删除）
     */
    int workerOffShelvesRetail(Integer retailId);
    /**
     * 员工根据retailId对商品进行上架处理
     */
    int workerShelvesRetail(Integer retailId);
    /**
     * 员工彻底删除商品
     * 对于已经过时或者过期的商品，需要进行彻底删除
     */
    int workerDeleteRetail(Integer retailId);
    /**
     * 员工根据电影名查询电影已售信息
     */
    List<UserSelectMytickets> workerSelectSell(String cinameName);
    /**
     * 员工修改个人信息
     */
    int workerUpdateMyInformation(WorkerInformations workerInformations);
    /**
     * 员工修改密码
     */
    int workerUpdatePassword(Integer workerId,String workerPassword);
    /**
     * 员工查看电影购票信息
     */
    List<UserSelectMytickets> workerSelectCinameInformation(String cinameName);
    /**
     * 员工强制用户退票
     */
    int workerDeleteUserTickets(Integer ticketId);
}

