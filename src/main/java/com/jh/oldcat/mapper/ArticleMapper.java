package com.jh.oldcat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jh.oldcat.entity.Article;
import com.jh.oldcat.entity.VO.ArticleTagVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {


    IPage<Article> getAllArticlePage(IPage<Article> page, ArticleTagVo articleTagVo);

    Boolean deleteArticle(Integer articleId);

    List<Article> moreSuggest(Integer id);

    Integer getViews();

    IPage<Article> getAllArticle(IPage<Article> page, ArticleTagVo articleTagVo);
}
