package com.xysfxy.main.controller;

import com.xysfxy.main.dto.req.UserLoginReqDto;
import com.xysfxy.main.dto.resp.UserInformationRespDto;
import com.xysfxy.main.entity.*;
import com.xysfxy.main.service.UserService;
import com.xysfxy.main.utils.BeanUtils;
import com.xysfxy.main.utils.api.BaseApiService;
import com.xysfxy.main.utils.api.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
@ResponseBody
public class UserController extends BaseApiService {
    @Autowired
    private UserService userService;
    //用户查询所有电影信息
    @GetMapping("/userSelectAllCinema")
    public BaseResponse<List<CinameInformations>> userSelectAllCinema(){
        List<CinameInformations> cinemainformations = userService.userSelectAllCiname();
        return setResultSuccessData(cinemainformations);
    }
    //用户登录
    @PostMapping("/userLogin")
    public BaseResponse userLogin(@RequestBody UserLoginReqDto user){
        if (user == null){
            return setResultError("userInformations is null");
        }
        if (user.getUserName() == null){
            return setResultError("userName is null");
        }
        if (user.getUserPassword() == null){
            return setResultError("userPassword is null");
        }
        UserInformations userinformations = userService.userLogin(user.getUserName(),user.getUserPassword());
        if (userinformations == null){
            return setResultError("用户信息不存在");
        }
        Integer isDelete = userinformations.getIsDelete();
        if (isDelete == 1){
            return setResultError("用户信息不存在");
        }
        UserInformationRespDto userInformationRespDto = BeanUtils.doToDto(userinformations, UserInformationRespDto.class);
        return setResultSuccessData(userInformationRespDto);
    }
    //用户注册
    @PostMapping("/userRegister")
    public BaseResponse userRegister(@RequestBody UserInformations userinformations){
        int result = userService.userRegister(userinformations);
        return setResultDb(result);
    }
    //根据用户名查找用户信息
    @GetMapping("/userSelectMyInformation/{userName}")
    public BaseResponse<UserInformations> userSelectMyInformation(@PathVariable("userName") String userName){
        if (userName == null){
            return setResultError("userName is null");
        }
        UserInformations userinformations = userService.userSelectMyInformation(userName);
        UserInformationRespDto userInformationRespDto = BeanUtils.doToDto(userinformations, UserInformationRespDto.class);
        return setResultSuccessData(userInformationRespDto);
    }
    //根据电影id查询电影信息
    @GetMapping("/userSelectCinameById/{cinameId}")
    public BaseResponse userSelectCinameById(@PathVariable("cinameId") Integer cinameId){
        if (cinameId == null){
            return setResultError("cinameId is null");
        }
        CinameInformations cinemainformations = userService.userSelectCinameById(cinameId);
        return setResultSuccessData(cinemainformations);
    }
    //用户购票
    @PostMapping("/userBuyTicket")
    public BaseResponse userBuyTicket(@RequestBody UserSelectMytickets userselectmytickets){
        if (userselectmytickets == null){
            return setResultError("ciname is null");
        }
        //获取userId和cinameId
        Integer userId = userselectmytickets.getUserId();
        //根据用户id获取用户购买的所有票数
        List<UserSelectMytickets> userSelectMytickets = userService.userSelectMytickets(userId);
        //购票规则，用户所购票数不能大于5，用户余额不能小于票价
        if (userSelectMytickets.size() >= 5){
            return setResultError("tickets is max");
        }
        String cinameName = userselectmytickets.getCinameName();
        //根据电影名称获取电影信息
        CinameInformations cinameInformations = userService.userSelectCinameByName(cinameName);
        Integer cinameId = cinameInformations.getCinameId();
        //修改余额
        int update = userService.userUpdateBanlance(userId, cinameId);
        //数据库用户购票表新增数据
        int insert = userService.userBuyTicket(userselectmytickets);
        return setResultDb(update+insert);
    }
    //用户查看自己的购票信息
    @GetMapping("/userSelectMyTicket/{userName}")
    public BaseResponse<List<UserSelectMytickets>> userSelectMyTicket(@PathVariable("userName") String userName){
        if (userName == null){
            return setResultError("userName is null");
        }
        //根据用户名获取用户信息
        UserInformations userInformations = userService.userSelectMyInformation(userName);
        Integer userId = userInformations.getUserId();
        List<UserSelectMytickets> userSelectMytickets = userService.userSelectMyTicket(userId);
        return setResultSuccessData(userSelectMytickets);
    }
    //用户根据购票id退票
    @DeleteMapping("/userDeleteTicket/{ticketId}")
    @Transactional
    public BaseResponse userDeleteTicket(@PathVariable("ticketId") Integer ticketId){
        if (ticketId == null){
            setResultError("ticketId is null");
        }
        //根据ticketId获取购票信息表对应的用户id,用户退票，用户购票时-1
        //根据用户id获取用户购票数
        UserSelectMytickets userSelectMytickets = userService.userSelectByTicketId(ticketId);
        Integer userId = userSelectMytickets.getUserId();
        String cinameName = userSelectMytickets.getCinameName();
        //根据电影名获取电影信息
        CinameInformations cinameInformations = userService.userSelectCinameByName(cinameName);
        Integer cinameId = cinameInformations.getCinameId();
        userService.userUpdateBanlanceOver(userId,cinameId);
        //数据库删除用户购票数据
        int result = userService.userDeleteTicket(ticketId);
        return setResultDb(result);
    }
    //用户修改个人信息
    @PutMapping("/userUpdateMyInformation")
    public BaseResponse userUpdateMyInformation(@RequestBody UserInformations userInformations){
        if (userInformations == null){
            setResultError("userInformation is null");
        }
        int result = userService.userUpdateMyInformation(userInformations);
        return setResultDb(result);
    }
    //用户查询所有可购买商品
    @GetMapping("/userSelectAllRetail")
    public BaseResponse<List<RetailInformations>> userSelectAllRetail() {
        List<RetailInformations> retailInformations = userService.userSelectAllRetail();
        return setResultSuccessData(retailInformations);
    }
    //用户购物
    @PostMapping("/userBuyRetail")
    public BaseResponse userBuyRetail(@RequestBody UserBuyretsil userBuyretsil) {
        if (userBuyretsil == null){
            return setResultError("userBuyretail is null");
        }
        //对于的商品数-1
        //对应用户的余额减少
        Integer retailId = userBuyretsil.getRetailId();
        Integer userId = userBuyretsil.getUserId();
        userService.userUpdateBalance(userId,retailId);
        int result = userService.userBuyRetail(userBuyretsil);
        return setResultDb(result);
    }
    //用户查询已购商品
    @GetMapping("/userSelectMyRetail/{userName}")
    public BaseResponse<List<UserBuyretsil>> userSelectMyRetail(@PathVariable("userName") String userName) {
        if (userName == null){
            return setResultError("userId is null");
        }
        //根据用户名获取用户信息
        UserInformations userInformations = userService.userSelectMyInformation(userName);
        Integer userId = userInformations.getUserId();
        List<UserBuyretsil> userBuyretsils = userService.userSelectMyRetail(userId);
        return setResultSuccessData(userBuyretsils);
    }
    //用户修改密码
    @PutMapping("/userUpdatePassword")
    public BaseResponse userUpdatePassword(@RequestBody UserInformations userInformations){
        if (userInformations == null){
            return setResultError("userInformations is null");
        }
        String userName = userInformations.getUserName();
        //根据用户名获取用户信息
        UserInformations user = userService.userSelectMyInformation(userName);
        Integer userId = user.getUserId();
        String userPassword = userInformations.getUserPassword();
        int result = userService.userUpdatePassword(userId, userPassword);
        return setResultDb(result);
    }
}
