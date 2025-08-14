package com.jh.oldcat.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.oldcat.entity.Tag;
import com.jh.oldcat.mapper.TagMapper;
import com.jh.oldcat.service.ArticleService;
import com.jh.oldcat.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Resource
    TagMapper tagMapper;

    @Override
    public IPage<Tag> getAllTag(IPage<Tag> page, Tag tag) {
        return tagMapper.getAllTag(page,tag);
    }
}
