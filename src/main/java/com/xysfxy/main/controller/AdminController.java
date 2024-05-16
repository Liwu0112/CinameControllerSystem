package com.xysfxy.main.controller;

import com.xysfxy.main.dto.resp.WorkerInformationRespDto;
import com.xysfxy.main.entity.AdminInformations;
import com.xysfxy.main.entity.WorkerApplicationInformation;
import com.xysfxy.main.entity.WorkerInformations;
import com.xysfxy.main.service.AdminService;
import com.xysfxy.main.utils.BeanUtils;
import com.xysfxy.main.utils.api.BaseApiService;
import com.xysfxy.main.utils.api.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
@ResponseBody
public class AdminController extends BaseApiService {
    @Autowired
    private AdminService adminService;
    @GetMapping("/adminSelectAllWorker")
    //管理员查看所有员工
    public BaseResponse<List<WorkerInformations>> adminSelectAllWorker(){
        List<WorkerInformations> workerinformationsList = adminService.adminSelectAllWorker();
        return setResultSuccessData(workerinformationsList);
    }
    //管理员根据id辞退员工
    @PutMapping("/adminDeleteById/{workerId}")
    public BaseResponse adminDeleteById(@PathVariable("workerId") Integer workerId){
        if (workerId == null){
            return setResultError("workerId is null");
        }
        int reslut = adminService.adminDeleteById(workerId);
        return setResultDb(reslut);
    }
    //管理员根据id查找员工
    @GetMapping("/adminSelectById/{workerId}")
    public BaseResponse adminSelectById(@PathVariable("workerId") Integer workerId){
        if (workerId == null){
            return setResultError("workerId is null");
        }
        WorkerInformations workerinformations = adminService.adminSelectById(workerId);
        if (workerinformations == null){
            return setResultError("workerinformations is null");
        }
        WorkerInformationRespDto workerInformationRespDto = BeanUtils.doToDto(workerinformations, WorkerInformationRespDto.class);
        return setResultSuccessData(workerInformationRespDto);
    }
    //管理员修改员工
    @PutMapping("/adminChangeWorkerPost")
    public BaseResponse adminChangeWorkerPost(@RequestBody WorkerInformations workerinformations){
        int result = adminService.adminChangeWorkerPost(workerinformations);
        return setResultDb(result);
    }
    //管理员新增员工
    @PostMapping("adminInsterWorker")
    public BaseResponse adminInsterWorker(@RequestBody WorkerInformations workerinformations){
        WorkerInformations selectById = adminService.adminSelectById(workerinformations.getWorkerId());
        if (selectById != null){
            return setResultError();
        }
        int result = adminService.adminInsterWorker(workerinformations);
        return setResultDb(result);
    }
    //管理员查看员工申请
    @GetMapping("/adminSelectApplication")
    public BaseResponse<List<WorkerApplicationInformation>> adminSelectApplication(){
        List<WorkerApplicationInformation> application = adminService.adminSelectApplication();
        return setResultSuccessData(application);
    }
    //管理员同意员工辞职申请
    @PutMapping("/adminAgreeDeleteApplication/{applicationId}")
    @Transactional
    public BaseResponse adminAgreeDeleteApplication(@PathVariable("applicationId")Integer applicationId){
        if (applicationId == null){
            return setResultError("applicationId is null");
        }
        WorkerInformations workerInformations = adminService.adminSelectByApplicationId(applicationId);
        if (workerInformations == null){
            return setResultError("员工信息不存在");
        }
        Integer workerId = workerInformations.getWorkerId();
        int update = adminService.adminAgreeIsDelete(workerId);
        int result = adminService.adminAgreeApplication(applicationId);
        return setResultDb(result+update);
    }
    //管理员登录
    @PostMapping("/adminLogin")
    public BaseResponse<AdminInformations> adminLogin(@RequestBody AdminInformations adminInformations){
        if (adminInformations == null){
            return setResultError("adminInformations is null");
        }
        String adminName = adminInformations.getAdminName();
        String adminPassword = adminInformations.getAdminPassword();
        AdminInformations adminLogin = adminService.adminLogin(adminName, adminPassword);
        if (adminLogin == null){
            return setResultError("账号或密码错误");
        }
        return setResultSuccessData(adminLogin);
    }
    //管理员同意员工休假申请
    @PutMapping("/adminAgreeRelexApplication/{applicationId}")
    @Transactional
    public BaseResponse adminAgreeRelexApplication(@PathVariable("applicationId")Integer applicationId){
        if (applicationId == null){
            return setResultError("applicationId");
        }
        WorkerInformations workerInformations = adminService.adminSelectByApplicationId(applicationId);
        Integer workerId = workerInformations.getWorkerId();
        int update = adminService.adminAgreeIsRelex(workerId);
        int result = adminService.adminAgreeApplication(applicationId);
        return setResultDb(result+update);
    }
    //管理员查看已处理申请
    @GetMapping("/adminSelectOverApplication")
    public BaseResponse<List<WorkerApplicationInformation>> adminSelectOverApplication(){
        List<WorkerApplicationInformation> workerApplicationInformations = adminService.adminSelectOverApplication();
        return setResultSuccessData(workerApplicationInformations);
    }
    //管理员拒绝员工申请
    @PutMapping("/adminRefuseApplication/{applicationId}")
    public BaseResponse adminRefuseApplication(@PathVariable("applicationId") Integer applicationId) {
        if (applicationId == null){
            return setResultError("applicationId is null");
        }
        int result = adminService.adminRefuseApplication(applicationId);
        return setResultDb(result);
    }
    //管理员同意员工复工申请
    @PutMapping("/adminAgreeWorkApplication/{applicationId}")
    @Transactional
    public BaseResponse adminAgreeWorkApplication(@PathVariable("applicationId")Integer applicationId){
        if (applicationId == null){
            return setResultError("applicationId is null");
        }
        WorkerInformations workerInformations = adminService.adminSelectByApplicationId(applicationId);
        if (workerInformations == null){
            return setResultError("员工信息不存在");
        }
        Integer workerId = workerInformations.getWorkerId();
        adminService.adminAgreeOverRelex(workerId);
        int result = adminService.adminAgreeApplication(applicationId);
        return setResultDb(result);
    }
}
