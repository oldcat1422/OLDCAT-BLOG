package com.jh.oldcat.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jh.oldcat.entity.SystemParameter;
import com.jh.oldcat.entity.Tag;
import com.jh.oldcat.service.SystemParameterService;
import com.jh.oldcat.service.TagService;
import com.jh.oldcat.utils.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/syspara")
@CrossOrigin
@AllArgsConstructor
public class SystemParameterController {
    private SystemParameterService systemParameterService;

    //获取全部tag（不分页）
    @GetMapping("/getAllPara")
    public Result getAllTags(){
        return Result.ok();
    }

    //关于页面-技术专长 3条
    @GetMapping("/getSkill")
    public Result getSkill(){
        QueryWrapper<SystemParameter> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("para_name", Arrays.asList("skillone", "skilltwo", "skillthree"));
        List<SystemParameter> list = systemParameterService.list(queryWrapper);
        return Result.ok().data(list);
    }


}
