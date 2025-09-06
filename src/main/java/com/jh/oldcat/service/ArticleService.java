package com.jh.oldcat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.oldcat.entity.Article;
import com.jh.oldcat.entity.VO.ArticleTagVo;

import java.util.List;

public interface ArticleService  extends IService<Article> {


    IPage<Article> getAllArticlePage(IPage<Article> page, ArticleTagVo articleTagVo);
    

    Boolean deleteArticle(Integer articleId);

    Article getOneArticle(Integer articleId);

    List<Article> moreSuggest(Integer id);

    Boolean addView(Integer articleId);

    Integer getViews();

    IPage<Article> getAllArticle(IPage<Article> page, ArticleTagVo articleTagVo);

    IPage<Article> recycle(IPage<Article> page, ArticleTagVo articleTagVo);
}
