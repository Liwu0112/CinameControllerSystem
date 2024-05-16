package com.xysfxy.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xysfxy.main.entity.AdminInformations;
import com.xysfxy.main.entity.WorkerApplicationInformation;
import com.xysfxy.main.entity.WorkerInformations;
import com.xysfxy.main.mapper.AdminInformationsMapper;
import com.xysfxy.main.mapper.WorkerApplicationInformationMapper;
import com.xysfxy.main.mapper.WorkerInformationsMapper;
import com.xysfxy.main.service.AdminService;
import com.xysfxy.main.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private WorkerInformationsMapper workerinformationsMapper;
    @Autowired
    private WorkerApplicationInformationMapper applicationMapper;
    @Autowired
    private AdminInformationsMapper adminMapper;

    //管理员查询所有员工
    @Override
    public List<WorkerInformations> adminSelectAllWorker() {
        QueryWrapper<WorkerInformations> listQueryWrapper = new QueryWrapper<>();
        listQueryWrapper.eq("is_delete",0);
        List<WorkerInformations> workerinformations = workerinformationsMapper.selectList(listQueryWrapper);
        return workerinformations;
    }
    //管理员根据id删除员工
    @Override
    @Transactional
    public int adminDeleteById(Integer workerId) {
        WorkerInformations workerinformations = new WorkerInformations(workerId);
        workerinformations.setIsDelete(1);
        return workerinformationsMapper.updateById(workerinformations);
    }
    //管理员根据id查找员工
    @Override
    public WorkerInformations adminSelectById(Integer workerId) {
        return workerinformationsMapper.selectById(workerId);
    }
    //管理员修改员工信息
    @Override
    @Transactional
    public int adminChangeWorkerPost(WorkerInformations workerinformations) {
        QueryWrapper<WorkerInformations> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("worker_id",workerinformations.getWorkerId());
        return workerinformationsMapper.update(workerinformations,queryWrapper);
    }
    //管理员新增员工信息
    @Override
    @Transactional
    public int adminInsterWorker(WorkerInformations workerinformations) {
        //new 一个空对象，接收前端数据，存入数据库
        WorkerInformations insterWorker = new WorkerInformations();
        insterWorker.setWorkerName(workerinformations.getWorkerName());
        insterWorker.setWorkerAge(workerinformations.getWorkerAge());
        insterWorker.setWorkerSex(workerinformations.getWorkerSex());
        insterWorker.setWorkerIntodate(workerinformations.getWorkerIntodate());
        insterWorker.setWorkerPost(workerinformations.getWorkerPost());
        insterWorker.setWorkerPhone(workerinformations.getWorkerPhone());
        String workerPassword = workerinformations.getWorkerPassword();
        //对输入的密码进行加密,存入数据库
        String md5Password = MD5Utils.md5(workerPassword);
        insterWorker.setWorkerPassword(md5Password);
        insterWorker.setIsDelete(workerinformations.getIsDelete());
        insterWorker.setIsRelex(workerinformations.getIsRelex());
        return workerinformationsMapper.insert(insterWorker);
    }
    //管理员查看员工申请
    @Override
    public List<WorkerApplicationInformation> adminSelectApplication() {
        QueryWrapper<WorkerApplicationInformation> listQueryWrapper = new QueryWrapper<>();
        listQueryWrapper.eq("application_result",0);
        return applicationMapper.selectList(listQueryWrapper);
    }
    //管理员同意员工申请
    @Override
    @Transactional
    public int adminAgreeApplication(Integer applicationId) {
        WorkerApplicationInformation workerApplicationInformation = new WorkerApplicationInformation(applicationId);
        workerApplicationInformation.setApplicationResult(1);
        return applicationMapper.updateById(workerApplicationInformation);
    }
    //管理员登录
    @Override
    public AdminInformations adminLogin(String adminName, String adminPassword) {
        LambdaQueryWrapper<AdminInformations> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminInformations::getAdminName,adminName);
        //根据adminName查询数据库对应数据
        AdminInformations adminInformations = adminMapper.selectOne(queryWrapper);
        String dbAdminPassword = adminInformations.getAdminPassword();
        //对输入密码进行加密，与数据库密码进行比对
        String md5Password = MD5Utils.md5(adminPassword);
        if (!(dbAdminPassword.equals(md5Password))){
            return null;
        }
        return adminInformations;
    }
    //根据applicationId查找员工信息
    @Override
    public WorkerInformations adminSelectByApplicationId(Integer applicationId) {
        //根据id查询员工申请信息
        WorkerApplicationInformation workerApplicationInformation = applicationMapper.selectById(applicationId);
        String workerName = workerApplicationInformation.getWorkerName();
        //根据员工姓名查找员工信息
        QueryWrapper<WorkerInformations> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("worker_name",workerName);
        return workerinformationsMapper.selectOne(queryWrapper);
    }
    //管理员查看已处理信息
    @Override
    public List<WorkerApplicationInformation> adminSelectOverApplication() {
        QueryWrapper<WorkerApplicationInformation> listQueryWrapper = new QueryWrapper<>();
        listQueryWrapper.ne("application_result",0);
        return applicationMapper.selectList(listQueryWrapper);
    }
    //管理员拒绝员工申请
    @Override
    @Transactional
    public int adminRefuseApplication(Integer applicationId) {
        WorkerApplicationInformation workerApplicationInformation = new WorkerApplicationInformation(applicationId);
        workerApplicationInformation.setApplicationResult(2);
        return applicationMapper.updateById(workerApplicationInformation);
    }
    //管理员同意员工辞职申请
    @Override
    @Transactional
    public int adminAgreeIsDelete(Integer workerId) {
        QueryWrapper<WorkerInformations> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("worker_id",workerId);
        WorkerInformations workerInformations = workerinformationsMapper.selectOne(queryWrapper);
        workerInformations.setIsDelete(1);
        return workerinformationsMapper.updateById(workerInformations);
    }
    //管理员同意员工休假申请
    @Override
    @Transactional
    public int adminAgreeIsRelex(Integer workerId) {
        //根据workerId查询员工信息
        WorkerInformations workerInformations = workerinformationsMapper.selectById(workerId);
        workerInformations.setIsRelex(1);
        return workerinformationsMapper.updateById(workerInformations);
    }
    //管理员同意员工复工申请
    @Override
    @Transactional
    public int adminAgreeOverRelex(Integer workerId) {
        //根据workerId查询员工信息
        WorkerInformations workerInformations = workerinformationsMapper.selectById(workerId);
        workerInformations.setIsRelex(0);
        return workerinformationsMapper.updateById(workerInformations);
    }
}
