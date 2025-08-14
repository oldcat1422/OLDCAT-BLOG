package com.jh.oldcat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.oldcat.entity.Tag;

public interface TagService extends IService<Tag> {
    IPage<Tag> getAllTag(IPage<Tag> page, Tag tag);
}
