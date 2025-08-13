package com.jh.oldcat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.oldcat.entity.User;
import com.jh.oldcat.utils.Result;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-08-11
 */
public interface UserService extends IService<User> {


    User login(String username, String password);
}
