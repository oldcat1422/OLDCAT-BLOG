package com.jh.oldcat.controller;

import com.alibaba.fastjson.JSON;
import com.jh.oldcat.entity.User;
import com.jh.oldcat.service.UserService;
import com.jh.oldcat.utils.JWTUtils;
import com.jh.oldcat.utils.Result;
import io.jsonwebtoken.Jwt;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author jh
 * @since 2022-08-11
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 测试
     * @return
     */
    @PostMapping("/checkToken")
    public Result checkToken(String token){
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        return Result.ok().message("stringObjectMap");
    }


}

