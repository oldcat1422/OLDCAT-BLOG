package com.jh.oldcat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.oldcat.entity.SystemParameter;
import com.jh.oldcat.entity.Tag;

public interface SystemParameterService extends IService<SystemParameter> {
    IPage<SystemParameter> getAllPara(IPage<SystemParameter> page, SystemParameter systemParameter);
}
