package com.jh.oldcat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.oldcat.entity.Article;
import com.jh.oldcat.entity.VO.ArticleTagVo;

import java.util.List;

public interface ArticleService  extends IService<Article> {


    IPage<Article> getAllArticlePage(IPage<Article> page, ArticleTagVo articleTagVo);

    List<Article> getAllArticle();

    Boolean deleteArticle(Integer articleId);
}
