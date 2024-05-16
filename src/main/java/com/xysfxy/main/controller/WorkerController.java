package com.xysfxy.main.controller;

import com.xysfxy.main.dto.resp.WorkerInformationRespDto;
import com.xysfxy.main.entity.*;
import com.xysfxy.main.service.WorkerService;
import com.xysfxy.main.utils.BeanUtils;
import com.xysfxy.main.utils.api.BaseApiService;
import com.xysfxy.main.utils.api.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/worker")
@ResponseBody
public class WorkerController extends BaseApiService {
    @Autowired
    private WorkerService workerService;
    //员工查询所有电影信息
    @GetMapping("/workerSelectAllCinemas")
    public BaseResponse<List<CinameInformations>> workerSelectAllCinema(){
        List<CinameInformations> cinemainformations = workerService.workerSelectAllCiname();
        return setResultSuccessData(cinemainformations);
    }
    //员工修改电影信息
    @PutMapping("/workerUpdateCiname")
    public BaseResponse workerUpdateCiname(@RequestBody CinameInformations cinameInformations){
        if (cinameInformations == null){
            return setResultError("cinameInformation is null");
        }
        int result = workerService.wokerUpdateCiname(cinameInformations);
        return setResultDb(result);
    }
    //员工根据id查询电影信息
    @GetMapping("/workerGetById/{cinameId}")
    public BaseResponse workerGetById(@PathVariable("cinameId") Integer cinameId){
        if (cinameId == null){
            return setResultError("cinameId is null");
        }
        CinameInformations cinemainformations = workerService.workerGetById(cinameId);
        if (cinemainformations == null){
            return setResultError("ciname is null");
        }
        return setResultSuccessData(cinemainformations);
    }
    //员工根据id下线电影信息（逻辑删除）
    @PutMapping("/workerDeleteCiname/{cinameId}")
    public BaseResponse workerDeleteCiname(@PathVariable("cinameId") Integer cinameId){
        if (cinameId == null){
            return setResultError("cinameId is null");
        }
        //已购电影票无法下架
        CinameInformations cinameInformations = workerService.workerGetById(cinameId);
        if (cinameInformations == null){
            return setResultError("电影信息不存在");
        }
        String cinameName = cinameInformations.getCinameName();
        List<UserSelectMytickets> userSelectMytickets = workerService.workerSelectSell(cinameName);
        if (userSelectMytickets.size() != 0){
            return setResultError("该电影票已经出售，无法下架");
        }
        int result = workerService.workerDeleteCiname(cinameId);
        return setResultDb(result);
    }
    //员工根据id重新发布电影信息
    @PutMapping("/workerAddCiname/{cinameId}")
    public BaseResponse workerAddCiname(@PathVariable("cinameId") Integer cinameId){
        if (cinameId == null){
            return setResultError("cinameId is null");
        }
        int result = workerService.workerAddCiname(cinameId);
        return setResultDb(result);
    }
    //员工新增电影信息
    @PostMapping("/workerInsterCiname")
    public BaseResponse workerInsterCiname(@RequestBody CinameInformations cinameInformations){
        if (cinameInformations == null){
            return setResultError("cinameInformation is null");
        }
        int result = workerService.workerInsterCiname(cinameInformations);
        return result>0?setResultSuccessData(cinameInformations):setResultError();
    }
    //员工彻底删除电影
    @DeleteMapping("/workerDeleteCinameOutRight/{cinameId}")
    public BaseResponse workerDeleteCinameOutRight(@PathVariable("cinameId") Integer cinameId){
        if (cinameId == null){
            return setResultError("cinameId is null");
        }
        //已购电影票无法删除
        CinameInformations cinameInformations = workerService.workerGetById(cinameId);
        String cinameName = cinameInformations.getCinameName();
        List<UserSelectMytickets> userSelectMytickets = workerService.workerSelectSell(cinameName);
        if (userSelectMytickets.size() != 0){
            return setResultError("该电影票已经出售，无法删除");
        }
        int result = workerService.workerDeleteCinameOutRight(cinameId);
        return setResultDb(result);
    }
    //员工申请休假
    @PostMapping("/workerIsRelex")
    public BaseResponse workerIsRelex(@RequestBody WorkerApplicationInformation workerApplicationInformation){
        if (workerApplicationInformation == null){
            return setResultError("workerApplicationInformation is null");
        }
        String workerApplication = workerApplicationInformation.getWorkerApplication();
        String workerName = workerApplicationInformation.getWorkerName();
        //根据用户名获取用户已提交的所有申请
        List<WorkerApplicationInformation> workerApplicationInformations = workerService.workerSelectMyApplication(workerName);
        //遍历申请，如果该员工已提交类似申请，并且管理员尚未处理，则无法提交重复申请
        for(int i=0;i<workerApplicationInformations.size();i++){
            WorkerApplicationInformation dbWorkerApplicationInformation = workerApplicationInformations.get(i);
            String dbWorkerApplication = dbWorkerApplicationInformation.getWorkerApplication();
            Integer dbApplicationResult = dbWorkerApplicationInformation.getApplicationResult();
            if (dbWorkerApplication.equals(workerApplication) && dbApplicationResult == 0){
                return setResultError("该申请已存在");
            }
        }
        int result = workerService.workerIsRelex(workerApplicationInformation);
        return setResultDb(result);
    }
    //员工查看自己的申请信息
    @GetMapping("/workerSelectMyApplication/{workerName}")
    public BaseResponse<WorkerApplicationInformation> workerSelectMyApplication(@PathVariable("workerName") String workerName){
        if (workerName == null){
            return setResultError("workerId is null");
        }
        List<WorkerApplicationInformation> myApplication = workerService.workerSelectMyApplication(workerName);
        if (myApplication == null){
            return setResultError("myApplication is null");
        }
        return setResultSuccessData(myApplication);
    }
    //员工登录
    @PostMapping("/workerLogin")
    public BaseResponse workerLogin(@RequestBody WorkerInformations workerInformations){
        if (workerInformations == null){
            return setResultError("workerInformation is null");
        }
        String workerName = workerInformations.getWorkerName();
        String workerPassword = workerInformations.getWorkerPassword();
        WorkerInformations dbWorker = workerService.workerLogin(workerName, workerPassword);
        Integer isDelete = dbWorker.getIsDelete();
        if (isDelete == 1){
            return setResultError("员工信息不存在");
        }
        if (dbWorker == null){
            return setResultError("员工信息不存在");
        }
        WorkerInformationRespDto workerInformationRespDto = BeanUtils.doToDto(dbWorker, WorkerInformationRespDto.class);
        return setResultSuccessData(workerInformationRespDto);
    }
    //根据员工名称查找员工信息
    @GetMapping("/workerSelectByWorkerName/{workerName}")
    public BaseResponse<WorkerInformations> workerSelectByWorkerName(@PathVariable("workerName") String workerName){
        if (workerName == null){
            return setResultError("workerName is null");
        }
        WorkerInformations workerInformations = workerService.workerSelectByWorkerName(workerName);
        if (workerInformations == null){
            return setResultError("workerInformations is null");
        }
        WorkerInformationRespDto workerInformationRespDto = BeanUtils.doToDto(workerInformations, WorkerInformationRespDto.class);
        return setResultSuccessData(workerInformationRespDto);
    }
    //员工取消申请
    @DeleteMapping("/workerDeleteApplication/{applicationId}")
    public BaseResponse workerDeleteApplication(@PathVariable("applicationId") Integer applicationId){
        if (applicationId == null){
            return setResultError("applicationId is null");
        }
        int result = workerService.workerDeleteApplication(applicationId);
        return setResultDb(result);
    }
    //员工查看历史申请信息
    @GetMapping("/workerSelectHistoryApplication/{workerName}")
    public BaseResponse<WorkerApplicationInformation> workerSelectHistoryApplication(@PathVariable("workerName") String workerName){
        if (workerName == null){
            return setResultError("workerId is null");
        }
        List<WorkerApplicationInformation> myApplication = workerService.workerSelectHistoryApplication(workerName);
        if (myApplication == null){
            return setResultError("myApplication is null");
        }
        return setResultSuccessData(myApplication);
    }
    //员工查询所有零售信息
    @GetMapping("/workerSelectAllRetail")
    public BaseResponse<List<RetailInformations>> workerSelectAllRetail() {
        List<RetailInformations> retailInformations = workerService.workerSelectAllRetail();
        return setResultSuccessData(retailInformations);
    }
    //员工新增零售
    @PostMapping("/workerInsterRetail")
    public BaseResponse workerInsterRetail(@RequestBody RetailInformations retailInformations) {
        if (retailInformations == null){
            return setResultError("retailInformations is null");
        }
        int result = workerService.workerInsterRetail(retailInformations);
        return setResultDb(result);
    }
    //员工根据retailId补货
    @PutMapping("/workerAddRetail")
    public BaseResponse workerAddRetail(@RequestBody RetailInformations retailInformations){
        if (retailInformations == null){
            return setResultError("retailInformations is null");
        }
        Integer retailId = retailInformations.getRetailId();
        Integer retailCount = retailInformations.getRetailCount();
        if (retailId == null || retailCount == null){
            return setResultError("请输入完整信息");
        }
        int result = workerService.workerAddRetail(retailId, retailCount);
        return setResultDb(result);
    }
    //员工根据retailId获取零售信息
    @GetMapping("/workerSelectRetailById/{retailId}")
    public BaseResponse<RetailInformations> workerSelectRetailById(@PathVariable("retailId") Integer retailId) {
        if (retailId == null){
            return setResultError("retailId is null");
        }
        RetailInformations retailInformations = workerService.workerSelectRetailById(retailId);
        if (retailInformations == null){
            return setResultError("retailInformations is null");
        }
        return setResultSuccessData(retailInformations);
    }
    //员工根据retailId下架商品
    @PutMapping("/workerOffShelvesRetail/{retailId}")
    public BaseResponse workerOffShelvesRetail(@PathVariable("retailId") Integer retailId) {
        if (retailId == null){
            return setResultError("retailId is null");
        }
        int result = workerService.workerOffShelvesRetail(retailId);
        return setResultDb(result);
    }
    //员工根据retailId上架商品
    @PutMapping("/workerShelvesRetail/{retailId}")
    public BaseResponse workerShelvesRetail(@PathVariable("retailId") Integer retailId) {
        if (retailId == null){
            return setResultError("retailId is null");
        }
        int result = workerService.workerShelvesRetail(retailId);
        return setResultDb(result);
    }
    //员工彻底删除商品
    @DeleteMapping("/workerDeleteRetail/{retailId}")
    public BaseResponse workerDeleteRetail(@PathVariable("retailId") Integer retailId) {
        if (retailId == null){
            return setResultError("retailId is null");
        }
        int result = workerService.workerDeleteRetail(retailId);
        return setResultDb(result);
    }
    //员工根据电影名查询电影已售信息
    @GetMapping("/workerSelectSell/{cinameName}")
    public BaseResponse<List<UserSelectMytickets>> workerSelectSell(@PathVariable("cinameName") String cinameName){
        if (cinameName == null){
            return setResultError("cinameName is null");
        }
        List<UserSelectMytickets> userSelectMytickets = workerService.workerSelectSell(cinameName);
        return setResultSuccessData(userSelectMytickets);
    }
    //员工修改个人信息
    @PutMapping("/workerUpdateMyInformation")
    public BaseResponse workerUpdateMyInformation(@RequestBody WorkerInformations workerInformations){
        if (workerInformations == null){
            return setResultError("workerInformations is null");
        }
        int result = workerService.workerUpdateMyInformation(workerInformations);
        return setResultDb(result);
    }
    //员工修改密码
    @PutMapping("/workerUpdatePassword")
    public BaseResponse workerUpdatePassword(@RequestBody WorkerInformations workerInformations){
        if (workerInformations == null){
            return setResultError("workerInformations is null");
        }
        String workerName = workerInformations.getWorkerName();
        //根据员工姓名获取员工信息
        WorkerInformations dbWorker = workerService.workerSelectByWorkerName(workerName);
        Integer workerId = dbWorker.getWorkerId();
        String workerPassword = workerInformations.getWorkerPassword();
        int result = workerService.workerUpdatePassword(workerId,workerPassword);
        return setResultDb(result);
    }
    //员工查看电影购票信息
    @GetMapping("/workerSelectCinameInformation/{cinameId}")
    public BaseResponse<List<UserSelectMytickets>> workerSelectCinameInformation(@PathVariable("cinameId") Integer cinameId){
        if (cinameId == null){
            return setResultError("cinameId is null");
        }
        //根据电影id获取电影信息
        CinameInformations cinameInformations = workerService.workerGetById(cinameId);
        String cinameName = cinameInformations.getCinameName();
        List<UserSelectMytickets> userSelectMytickets = workerService.workerSelectCinameInformation(cinameName);
        return setResultSuccessData(userSelectMytickets);
    }
    //员工强制退票
    @PutMapping("/workerDeleteUserTickets/{ticketId}")
    public BaseResponse workerDeleteUserTickets(@PathVariable("ticketId") Integer ticketId){
        if (ticketId == null){
            return setResultError("ticketId is null");
        }
        int result = workerService.workerDeleteUserTickets(ticketId);
        return setResultDb(result);
    }
}
