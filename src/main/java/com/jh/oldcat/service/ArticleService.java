package com.jh.oldcat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.oldcat.entity.Article;

import java.util.List;

public interface ArticleService  extends IService<Article> {


    IPage<Article> getAllArticlePage(IPage<Article> page, Article article);

    List<Article> getAllArticle();

    Boolean deleteArticle(Integer articleId);
}
