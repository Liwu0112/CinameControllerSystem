package com.xysfxy.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xysfxy.main.entity.*;
import com.xysfxy.main.mapper.*;
import com.xysfxy.main.service.UserService;
import com.xysfxy.main.utils.MD5Utils;
import com.xysfxy.main.utils.api.BaseApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl extends BaseApiService implements UserService {
    @Autowired
    private CinameInformationsMapper cinemainformationsMapper;
    @Autowired
    private UserSelectMyticketsMapper userselectmyticketsMapper;
    @Autowired
    private UserInformationsMapper userinformationsMapper;
    @Autowired
    private RetailInformationsMapper retailInformationsMapper;
    @Autowired
    private UserBuyretsilMapper userBuyretsilMapper;
    //用户查询所有电影信息
    @Override
    public List<CinameInformations> userSelectAllCiname() {
        QueryWrapper<CinameInformations> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete",0);
        List<CinameInformations> cinemainformations = cinemainformationsMapper.selectList(queryWrapper);
        return cinemainformations;
    }
    //用户查询自己电影信息
    @Override
    public List<UserSelectMytickets> userSelectMytickets(Integer userId) {
        QueryWrapper<UserSelectMytickets> userscheduledticketQueryWrapper = new QueryWrapper<>();
        userscheduledticketQueryWrapper.eq("user_id",userId);
        return userselectmyticketsMapper.selectList(userscheduledticketQueryWrapper);
    }
    //用户登录
    @Override
    public UserInformations userLogin(String userName, String userPassword) {
        //根据用户名获取用户信息
        QueryWrapper<UserInformations> userinformationsQueryWrapper = new QueryWrapper<>();
        userinformationsQueryWrapper.eq("user_name",userName);
        UserInformations loginUserinformations = userinformationsMapper.selectOne(userinformationsQueryWrapper);
        if (loginUserinformations == null){
            return null;
        }
        String dbUserPassword = loginUserinformations.getUserPassword();
        //对用户输入的密码进行加密，与数据库中密码进行比对
        String md5UserPassword = MD5Utils.md5(userPassword);
        if (!(dbUserPassword.equals(md5UserPassword))){
            return null;
        }
        return loginUserinformations;
    }
    //用户注册
    @Override
    @Transactional
    public int userRegister(UserInformations userinformations) {
        //对密码进行加密
        String userPassword = userinformations.getUserPassword();
        String md5Password = MD5Utils.md5(userPassword);
        userinformations.setUserPassword(md5Password);
        return userinformationsMapper.insert(userinformations);
    }
    //根据用户名查找用户信息
    @Override
    public UserInformations userSelectMyInformation(String userName) {
        QueryWrapper<UserInformations> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        return userinformationsMapper.selectOne(queryWrapper);
    }
    //根据电影id查询电影信息
    @Override
    public CinameInformations userSelectCinameById(Integer cinameId) {
        return cinemainformationsMapper.selectById(cinameId);
    }
    //用户根据电影名称查询电影信息
    @Override
    public CinameInformations userSelectCinameByName(String cinameName) {
        QueryWrapper<CinameInformations> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ciname_name",cinameName);
        return cinemainformationsMapper.selectOne(queryWrapper);
    }

    //用户购票
    @Override
    @Transactional
    public int userBuyTicket(UserSelectMytickets userselectmytickets) {
        int insert = userselectmyticketsMapper.insert(userselectmytickets);
        return insert;
    }
    //用户查看自己的购票信息
    @Override
    public List<UserSelectMytickets> userSelectMyTicket(Integer userId) {
        QueryWrapper<UserSelectMytickets> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return userselectmyticketsMapper.selectList(queryWrapper);
    }
    //根据id查询用户信息
    @Override
    public UserInformations userSelectMyById(Integer userId) {
        return userinformationsMapper.selectById(userId);
    }
    //根据购票id退票
    @Override
    @Transactional
    public int userDeleteTicket(Integer ticketId) {
        return userselectmyticketsMapper.deleteById(ticketId);
    }
    //根据ticketId查询用户信息
    @Override
    public UserSelectMytickets userSelectByTicketId(Integer ticketId) {
        return userselectmyticketsMapper.selectById(ticketId);
    }
    //用户根据用户名修改个人信息
    @Override
    @Transactional
    public int userUpdateMyInformation(UserInformations userInformations) {
        QueryWrapper<UserInformations> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userInformations.getUserName());
        int update = userinformationsMapper.update(userInformations, queryWrapper);
        return update;
    }
    //用户查询可购买商品
    @Override
    public List<RetailInformations> userSelectAllRetail() {
        QueryWrapper<RetailInformations> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete",0);
        return retailInformationsMapper.selectList(queryWrapper);
    }
    //用户购物
    @Override
    @Transactional
    public int userBuyRetail(UserBuyretsil userBuyretsil) {
        return userBuyretsilMapper.insert(userBuyretsil);
    }
    //用户查询已购商品
    @Override
    public List<UserBuyretsil> userSelectMyRetail(Integer userId) {
        QueryWrapper<UserBuyretsil> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return userBuyretsilMapper.selectList(queryWrapper);
    }
    //用户修改密码
    @Override
    @Transactional
    public int userUpdatePassword(Integer userId, String password) {
        UserInformations userInformations = new UserInformations(userId);
        //密码加密
        String md5Password = MD5Utils.md5(password);
        userInformations.setUserPassword(md5Password);
        return userinformationsMapper.updateById(userInformations);
    }
    //用户购物后修改余额
    @Override
    @Transactional
    public int userUpdateBalance(Integer userId,Integer retailId) {
        //根据userId获取用户信息
        UserInformations userInformations = userinformationsMapper.selectById(userId);
        Integer userBalance = userInformations.getUserBalance();
        //根据retailId获取零售信息
        RetailInformations retailInformations = retailInformationsMapper.selectById(retailId);
        Integer retailPrice = retailInformations.getRetailPrice();
        //购物规则，用户余额不能小于零售价格，零售库存量不能小于0
        if (userBalance < retailPrice){
            return 0;
        }
        userInformations.setUserBalance(userBalance-retailPrice);
        Integer retailCount = retailInformations.getRetailCount();
        if (retailCount <= 0){
            return 0;
        }
        retailInformations.setRetailCount(retailCount-1);
        return userinformationsMapper.updateById(userInformations)+retailInformationsMapper.updateById(retailInformations);
    }
    //用户购票后修改余额
    @Override
    @Transactional
    public int userUpdateBanlance(Integer userId, Integer cinameId) {
        //根据userId获取用户信息
        UserInformations userInformations = userinformationsMapper.selectById(userId);
        //根据cinameId获取电影信息
        CinameInformations cinameInformations = cinemainformationsMapper.selectById(cinameId);
        Integer ticketsCount = userInformations.getTicketsCount();
        userInformations.setTicketsCount(ticketsCount+1);
        Integer cinamePrice = cinameInformations.getCinamePrice();
        Integer userBalance = userInformations.getUserBalance();
        if (userBalance < cinamePrice){
            return 0;
        }
        userInformations.setUserBalance(userBalance-cinamePrice);
        return userinformationsMapper.updateById(userInformations)+cinemainformationsMapper.updateById(cinameInformations);
    }
    //用户退票后休改余额
    @Override
    @Transactional
    public int userUpdateBanlanceOver(Integer userId, Integer cinameId) {
        //根据userId获取用户信息
        UserInformations userInformations = userinformationsMapper.selectById(userId);
        //根据cinameId获取电影信息
        CinameInformations cinameInformations = cinemainformationsMapper.selectById(cinameId);
        Integer ticketsCount = userInformations.getTicketsCount();
        if (ticketsCount<=0){
            return 0;
        }
        userInformations.setTicketsCount(ticketsCount-1);
        Integer cinamePrice = cinameInformations.getCinamePrice();
        Integer userBalance = userInformations.getUserBalance();
        userInformations.setUserBalance(userBalance+cinamePrice);
        QueryWrapper<UserSelectMytickets> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId)
                .eq("ciname_id",cinameId);
        return userinformationsMapper.updateById(userInformations)+cinemainformationsMapper.updateById(cinameInformations);
    }
}
