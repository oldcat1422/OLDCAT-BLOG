package com.jh.oldcat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jh.oldcat.entity.SystemParameter;
import com.jh.oldcat.entity.Tag;
import com.jh.oldcat.entity.VO.TotalNumber;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemParameterMapper extends BaseMapper<SystemParameter> {
    TotalNumber getTotalNumber();
    IPage<SystemParameter> getAllPara(IPage<SystemParameter> page, SystemParameter systemParameter);
}
