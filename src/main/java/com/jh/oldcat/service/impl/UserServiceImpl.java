package com.jh.oldcat.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.oldcat.entity.User;
import com.jh.oldcat.mapper.UserMapper;
import com.jh.oldcat.service.UserService;
import com.jh.oldcat.utils.JWTUtils;
import com.jh.oldcat.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-08-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;



    @Override
    public User login(String username, String password) {
        //1、检查参数是否合法
        //2、检查用户名和密码，去user表中查询是否存在
        //3、不存在，登录失败
        //4、存在，使用jwt 生成token 返回给前端
        //5、token放入redis中
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        queryWrapper.eq(User::getPassword,password);
        queryWrapper.last("limit 1");

        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public IPage<User> getAllUser(IPage<User> page, User user) {
        return userMapper.getAllUser(page, user);
    }

}
