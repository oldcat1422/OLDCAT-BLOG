package com.jh.oldcat.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jh.oldcat.entity.Article;
import com.jh.oldcat.service.ArticleService;
import com.jh.oldcat.utils.Result;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/public/article")
@CrossOrigin
@AllArgsConstructor
public class PublicArticleController {

    @Autowired
    private ArticleService articleService;

    //获取全部文章
    @GetMapping("/getAllArticle")
    public Result getAllArticle(){
        List<Article> allArticle = articleService.getAllArticle();
        return Result.ok().message("获取成功").data(allArticle);
    }

    //获取全部文章(分页 pageNo当前页码  pageSize每页显示个数)
    @PostMapping("/getAllArticlePage")
    public Result getAllArticlePage(Article article,
                                 @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                 @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        IPage<Article> page = new Page<>(pageNo,pageSize);
        IPage<Article> allArticlePage = articleService.getAllArticlePage(page, article);
        return Result.ok().data(allArticlePage);
    }
}
