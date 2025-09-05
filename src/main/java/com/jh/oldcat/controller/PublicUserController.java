package com.jh.oldcat.controller;

import com.jh.oldcat.entity.User;
import com.jh.oldcat.service.UserService;
import com.jh.oldcat.utils.JWTUtils;
import com.jh.oldcat.utils.Result;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public/user")
@CrossOrigin
@AllArgsConstructor
public class PublicUserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){

        User loginuser = userService.login(user.getUsername(),user.getPassword());
        if(loginuser != null){
            //根据得到的用户信息生成token
            Map<String, String> map = new HashMap<>();
            String token = JWTUtils.createToken(loginuser);
            map.put("token", token);
            map.put("role", loginuser.getRole().toString());
            return Result.ok().message("登录成功").data(map);
        }else{
            return Result.error().message("登陆失败");
        }
    }


}
