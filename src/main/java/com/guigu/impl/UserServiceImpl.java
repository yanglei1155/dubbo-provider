package com.guigu.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.guigu.mapper.UserMapper;
import com.guigu.pojo.Account;
import com.guigu.pojo.User;
import com.guigu.pojo.basecom.Result;
import com.guigu.pojo.code.ResultCode;
import com.guigu.service.AccountService;
import com.guigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Service
@Component
public class UserServiceImpl implements UserService {
    @Reference
    private AccountService accountService;
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUser() {
        User user=new User();
        user.setUsername("张三");
        user.setPassword("123");
        return user;
    }

    @Override
    public Result saveUser(User user) {
        Result result=new Result();
        try {
            String uid = UUID.randomUUID().toString().replaceAll("-", "");
            user.setUid(uid);
            userMapper.saveUseAndAccount(user);
            Account account=new Account();
            account.setUid(uid);
            account.setMoney(100.00);
            accountService.saveAccount(account);
            result.setMessage("保存成");
            result.setStatusCode(ResultCode.success);
        } catch (Exception e) {
            result.setMessage("保存失败");
            result.setStatusCode(ResultCode.failure);
            e.printStackTrace();
        }
        return result;
    }
}
