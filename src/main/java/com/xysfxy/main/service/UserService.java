package com.xysfxy.main.service;

import com.xysfxy.main.entity.*;

import java.util.List;

public interface UserService {
    //用户查看所有电影信息
    List<CinameInformations> userSelectAllCiname();

    //用户查看本人电影信息
    List<UserSelectMytickets> userSelectMytickets(Integer userId);

    //用户登录
    UserInformations userLogin(String userName, String userPassword);

    //用户注册
    int userRegister(UserInformations userinformations);

    //根据用户名查找用户信息
    UserInformations userSelectMyInformation(String userName);

    //根据电影id查询电影信息
    CinameInformations userSelectCinameById(Integer cinameId);

    //用户根据电影名查询电影信息
    CinameInformations userSelectCinameByName(String cinameName);

    //用户购票
    int userBuyTicket(UserSelectMytickets userselectmytickets);

    //用户查看自己的购票信息
    List<UserSelectMytickets> userSelectMyTicket(Integer userId);

    //用户根据用户id查询用户信息
    UserInformations userSelectMyById(Integer userId);

    //用户根据购票id退票
    int userDeleteTicket(Integer ticketId);

    //根据ticketId查询用户信息
    UserSelectMytickets userSelectByTicketId(Integer ticketId);

    //用户修改个人信息
    int userUpdateMyInformation(UserInformations userInformations);

    //用户查询可购买商品
    List<RetailInformations> userSelectAllRetail();

    //用户购物
    int userBuyRetail(UserBuyretsil userBuyretsil);

    //用户查询已购商品
    List<UserBuyretsil> userSelectMyRetail(Integer userId);

    //用户修改密码
    int userUpdatePassword(Integer userId, String password);

    //用户购买后修改余额
    int userUpdateBalance(Integer userId, Integer retailId);

    //用户购票后修改余额
    int userUpdateBanlance(Integer userId, Integer cinameId);

    //用户退票后修改余额
    int userUpdateBanlanceOver(Integer userId, Integer cinameId);
}
