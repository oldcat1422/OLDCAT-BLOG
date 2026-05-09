package com.jh.oldcat.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jh.oldcat.entity.User;
import com.jh.oldcat.service.UserService;
import com.jh.oldcat.utils.JWTUtils;
import com.jh.oldcat.utils.Result;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    //添加用户
    @PutMapping("addUser")
    public Result addUser(@RequestBody User user){
        boolean save = userService.save(user);
        if (save) {
            return Result.ok().message("添加成功");
        }else {
            return Result.error().message("添加失败");
        }
    }

    //修改用户
    @PostMapping("updateUser")
    public Result updateUser(@RequestBody User user){
        boolean b = userService.updateById(user);
        if (b) {
            return Result.ok().message("修改成功");
        }else {
            return Result.error().message("修改失败");
        }
    }

    //删除用户
    @PostMapping("deleteUser")
    public Result deleteUser(Integer id){
        boolean b = userService.removeById(id);
        if (b) {
            return Result.ok().message("删除成功");
        }else{
            return Result.error().message("删除失败");
        }
    }

    //获取全部用户(分页)
    @PostMapping("/getAllUser")
    public Result getAllUser(User user,
                            @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                            @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        IPage<User> page = new Page<>(pageNo,pageSize);
        IPage<User> allUser = userService.getAllUser(page, user);
        return Result.ok().data(allUser);
    }

    //获取一个用户
    @PostMapping("getOneUser")
    public Result getOneUser(Integer id){
        User user = userService.getById(id);
        return Result.ok().data(user);
    }

}

