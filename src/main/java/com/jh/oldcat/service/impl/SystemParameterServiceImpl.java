package com.jh.oldcat.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.oldcat.entity.SystemParameter;
import com.jh.oldcat.entity.Tag;
import com.jh.oldcat.mapper.SystemParameterMapper;
import com.jh.oldcat.mapper.TagMapper;
import com.jh.oldcat.service.SystemParameterService;
import com.jh.oldcat.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SystemParameterServiceImpl extends ServiceImpl<SystemParameterMapper, SystemParameter> implements SystemParameterService {

    @Resource
    SystemParameterMapper systemParameterMapper;


}
