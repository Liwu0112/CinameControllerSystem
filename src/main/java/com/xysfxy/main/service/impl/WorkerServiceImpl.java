package com.xysfxy.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xysfxy.main.entity.*;
import com.xysfxy.main.mapper.*;
import com.xysfxy.main.service.WorkerService;
import com.xysfxy.main.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private CinameInformationsMapper cinameInformationsMapper;
    @Autowired
    private WorkerApplicationInformationMapper applicationMapper;
    @Autowired
    private WorkerInformationsMapper workerInformationsMapper;
    @Autowired
    private RetailInformationsMapper retailInformationsMapper;
    @Autowired
    private UserSelectMyticketsMapper userSelectMyticketsMapper;
    @Autowired
    private UserInformationsMapper userInformationsMapper;
    //员工查看所有电影信息
    @Override
    public List<CinameInformations> workerSelectAllCiname() {
        QueryWrapper<CinameInformations> queryWrapper = new QueryWrapper<>();
        List<CinameInformations> cinemainformations = cinameInformationsMapper.selectList(queryWrapper);
        return cinemainformations;
    }
    //员工修改电影信息
    @Override
    @Transactional
    public int wokerUpdateCiname(CinameInformations cinemainformations) {
        //根据id修改电影信息
        QueryWrapper<CinameInformations> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ciname_id",cinemainformations.getCinameId());
        int result = cinameInformationsMapper.update(cinemainformations,queryWrapper);
        return result;
    }
    //员工根据id获取电影信息
    @Override
    public CinameInformations workerGetById(Integer cinameId) {
        CinameInformations cinemainformations = cinameInformationsMapper.selectById(cinameId);
        return cinemainformations;
    }
    //员工删除电影信息（逻辑删除）
    @Override
    @Transactional
    public int workerDeleteCiname(Integer cinameId) {
        CinameInformations cinemainformations = new CinameInformations(cinameId);
        cinemainformations.setIsDelete(1);
        return cinameInformationsMapper.updateById(cinemainformations);
    }
    //员工发布电影信息
    @Override
    public int workerAddCiname(Integer cinameId) {
        CinameInformations cinemainformations = new CinameInformations(cinameId);
        cinemainformations.setIsDelete(0);
        return cinameInformationsMapper.updateById(cinemainformations);
    }
    //员工新增电影信息
    @Override
    @Transactional
    public int workerInsterCiname(CinameInformations cinemainformations) {
        return cinameInformationsMapper.insert(cinemainformations);
    }
    //员工彻底删除电影
    @Override
    @Transactional
    public int workerDeleteCinameOutRight(Integer cinameId) {
        return cinameInformationsMapper.deleteById(cinameId);
    }
    //员工申请休假
    @Override
    @Transactional
    public int workerIsRelex(WorkerApplicationInformation workerapplicationinformation) {
        return applicationMapper.insert(workerapplicationinformation);
    }
    //员工查看自己的申请信息
    @Override
    public List<WorkerApplicationInformation> workerSelectMyApplication(String workerName) {
        QueryWrapper<WorkerApplicationInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("worker_name",workerName)
                .eq("application_result",0);
        return applicationMapper.selectList(queryWrapper);
    }
    //员工登录
    @Override
    public WorkerInformations workerLogin(String workerName, String workerPassword) {
        //根据员工姓名获取员工信息
        QueryWrapper<WorkerInformations> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("worker_name",workerName);
        WorkerInformations workerInformations = workerInformationsMapper.selectOne(queryWrapper);
        //对密码进行加密
        String dbWorkerPassword = workerInformations.getWorkerPassword();
        String md5WorkerPassword = MD5Utils.md5(workerPassword);
        if (!(dbWorkerPassword.equals(md5WorkerPassword))){
            return null;
        }
        return workerInformations;
    }
    //根据员工名称查找员工信息
    @Override
    public WorkerInformations workerSelectByWorkerName(String workerName) {
        QueryWrapper<WorkerInformations> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("worker_name",workerName);
        return workerInformationsMapper.selectOne(queryWrapper);
    }
    //员工取消申请
    @Override
    public int workerDeleteApplication(Integer applicationId) {
        return  applicationMapper.deleteById(applicationId);
    }
    //员工查看历史申请信息
    @Override
    public List<WorkerApplicationInformation> workerSelectHistoryApplication(String workerName) {
        QueryWrapper<WorkerApplicationInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("worker_name",workerName)
                .ne("application_result",0);
        return applicationMapper.selectList(queryWrapper);
    }
    //员工查询所有零售
    @Override
    public List<RetailInformations> workerSelectAllRetail() {
        QueryWrapper<RetailInformations> queryWrapper = new QueryWrapper<>();
        return retailInformationsMapper.selectList(queryWrapper);
    }
    //员工新增零售
    @Override
    @Transactional
    public int workerInsterRetail(RetailInformations retailInformations) {
        return retailInformationsMapper.insert(retailInformations);
    }
    //员工补货
    @Override
    @Transactional
    public int workerAddRetail(Integer retailId,Integer retailCount) {
        //根据id获取零售信息
        QueryWrapper<RetailInformations> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("retail_id",retailId);
        RetailInformations retailInformations = retailInformationsMapper.selectOne(queryWrapper);
        //修改货物库存
        retailInformations.setRetailCount(retailCount);
        return retailInformationsMapper.updateById(retailInformations);
    }
    //根据retailId获取零售信息
    @Override
    public RetailInformations workerSelectRetailById(Integer retailId) {
        return retailInformationsMapper.selectById(retailId);
    }
    //根据retailId下架商品
    @Override
    @Transactional
    public int workerOffShelvesRetail(Integer retailId) {
        RetailInformations retailInformations = new RetailInformations(retailId);
        retailInformations.setIsDelete(1);
        return retailInformationsMapper.updateById(retailInformations);
    }
    //根据retailId上架商品
    @Override
    @Transactional
    public int workerShelvesRetail(Integer retailId) {
        RetailInformations retailInformations = new RetailInformations(retailId);
        retailInformations.setIsDelete(0);
        return retailInformationsMapper.updateById(retailInformations);
    }
    //员工彻底删除商品
    @Override
    @Transactional
    public int workerDeleteRetail(Integer retailId) {
        return retailInformationsMapper.deleteById(retailId);
    }
    //员工根据电影名查询电影已售信息
    @Override
    public List<UserSelectMytickets> workerSelectSell(String cinameName) {
        QueryWrapper<UserSelectMytickets> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ciname_name",cinameName);
        return userSelectMyticketsMapper.selectList(queryWrapper);
    }
    //员工修养个人信息
    @Override
    public int workerUpdateMyInformation(WorkerInformations workerInformations) {
        WorkerInformations updateInformation = new WorkerInformations(workerInformations.getWorkerId());
        updateInformation.setWorkerName(workerInformations.getWorkerName());
        updateInformation.setWorkerAge(workerInformations.getWorkerAge());
        updateInformation.setWorkerSex(workerInformations.getWorkerSex());
        updateInformation.setWorkerIntodate(workerInformations.getWorkerIntodate());
        updateInformation.setWorkerPost(workerInformations.getWorkerPost());
        updateInformation.setWorkerPhone(workerInformations.getWorkerPhone());
        updateInformation.setWorkerPassword(workerInformations.getWorkerPassword());
        updateInformation.setIsRelex(workerInformations.getIsRelex());
        updateInformation.setIsDelete(workerInformations.getIsDelete());
        return workerInformationsMapper.updateById(updateInformation);
    }
    //员工修改密码
    @Override
    public int workerUpdatePassword(Integer workerId, String workerPassword) {
        WorkerInformations workerInformations = new WorkerInformations(workerId);
        String md5Password = MD5Utils.md5(workerPassword);
        workerInformations.setWorkerPassword(md5Password);
        return workerInformationsMapper.updateById(workerInformations);
    }
    //用户查看电影购票信息
    @Override
    public List<UserSelectMytickets> workerSelectCinameInformation(String cinameName) {
        QueryWrapper<UserSelectMytickets> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ciname_name",cinameName);
        return userSelectMyticketsMapper.selectList(queryWrapper);
    }
    //员工强制退票
    @Override
    @Transactional
    public int workerDeleteUserTickets(Integer ticketId) {
        //获取该id对应的用户信息
        UserSelectMytickets userSelectMytickets = userSelectMyticketsMapper.selectById(ticketId);
        Integer userId = userSelectMytickets.getUserId();
        UserInformations userInformations = userInformationsMapper.selectById(userId);
        //获取到电影信息
        String cinameName = userSelectMytickets.getCinameName();
        QueryWrapper<CinameInformations> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ciname_name",cinameName);
        CinameInformations cinameInformations = cinameInformationsMapper.selectOne(queryWrapper);
        //用户余额归还
        Integer cinamePrice = cinameInformations.getCinamePrice();
        userInformations.setUserBalance(userInformations.getUserBalance()+cinamePrice);
        //用户购票数-1
        userInformations.setTicketsCount(userInformations.getTicketsCount()-1);
        int update = userInformationsMapper.updateById(userInformations);
        //用户购票表数据删除
        int delete = userSelectMyticketsMapper.deleteById(userSelectMytickets);
        return update + delete == 2 ? 1:0;
    }
}
