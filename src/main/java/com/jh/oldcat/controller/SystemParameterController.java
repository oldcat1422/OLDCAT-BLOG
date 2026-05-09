package com.jh.oldcat.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jh.oldcat.entity.Article;
import com.jh.oldcat.entity.SystemParameter;
import com.jh.oldcat.entity.Tag;
import com.jh.oldcat.entity.VO.ArticleTagVo;
import com.jh.oldcat.entity.VO.TotalNumber;
import com.jh.oldcat.mapper.SystemParameterMapper;
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
    private SystemParameterMapper systemParameterMapper;

    /**
     * 后台-系统设置，展示所有的系统参数
     * @param systemParameter
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/getAllPara")
    public Result getAllPara(SystemParameter systemParameter,
                             @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                             @RequestParam(value = "pageSize",defaultValue = "20")Integer pageSize){
        IPage<SystemParameter> page = new Page<>(pageNo,pageSize);
        IPage<SystemParameter> allpara = systemParameterService.getAllPara(page, systemParameter);
        return Result.ok().data(allpara);
    }

    /**
     * 前台-关于-关于我
     * @return
     */
    @GetMapping("/getAboutMe")
    public Result getAboutMe(){
        QueryWrapper<SystemParameter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("para_name", "aboutMe");
        SystemParameter para = systemParameterService.getOne(queryWrapper);
        return Result.ok().data(para);
    }

    //关于页面-技术专长 3条
    @GetMapping("/getSkill")
    public Result getSkill(){
        QueryWrapper<SystemParameter> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("para_name", Arrays.asList("skillone", "skilltwo", "skillthree"));
        List<SystemParameter> list = systemParameterService.list(queryWrapper);
        return Result.ok().data(list);
    }

    //添加参数
    @PutMapping("addPara")
    public Result addPara(@RequestBody SystemParameter systemParameter){
        boolean save = systemParameterService.save(systemParameter);
        if (save) {
            return Result.ok().message("添加成功");
        }else {
            return Result.error().message("添加失败");
        }
    }

    //修改参数
    @PostMapping("updatePara")
    public Result updatePara(@RequestBody SystemParameter systemParameter){
        boolean b = systemParameterService.updateById(systemParameter);
        if (b) {
            return Result.ok().message("修改成功");
        }else {
            return Result.error().message("修改失败");
        }
    }

    //删除参数
    @PostMapping("deletePara")
    public Result deletePara(Integer paraId){
        boolean b = systemParameterService.removeById(paraId);
        if (b) {
            return Result.ok().message("删除成功");
        }else{
            return Result.error().message("删除失败");
        }
    }

    //获取统计总数
    @GetMapping("/getTotalNumber")
    public Result getTotalNumber(){
        TotalNumber totalNumber = systemParameterMapper.getTotalNumber();
        return Result.ok().data(totalNumber);
    }

}
