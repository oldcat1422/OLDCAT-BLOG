package com.jh.oldcat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.oldcat.entity.Article;
import com.jh.oldcat.entity.VO.ArticleTagVo;
import com.jh.oldcat.mapper.ArticleMapper;
import com.jh.oldcat.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    ArticleMapper articleMapper;


    @Override
    public IPage<Article> getAllArticlePage(IPage<Article> page, ArticleTagVo articleTagVo) {
        return articleMapper.getAllArticlePage(page,articleTagVo);
    }

    @Override
    public List<Article> getAllArticle() {
        return articleMapper.getAllArticle();
    }

    @Override
    public Boolean deleteArticle(Integer articleId) {
        return articleMapper.deleteArticle(articleId);
    }
}
