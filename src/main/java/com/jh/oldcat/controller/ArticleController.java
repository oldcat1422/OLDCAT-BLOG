package com.jh.oldcat.controller;

import com.jh.oldcat.entity.Article;
import com.jh.oldcat.service.ArticleService;
import com.jh.oldcat.utils.Result;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jh
 * @since 2022-08-11
 */
@RestController
@RequestMapping("/article")
@CrossOrigin
@AllArgsConstructor
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //添加文章
    @PostMapping("/addArticle")
    public Result addArticle(@RequestBody Article article) {
        boolean save = articleService.save(article);
        if (save) {
            return Result.ok().message("添加成功");
        }else {
            return Result.error().message("登陆失败");

        }
    }

    //修改文章
    @PutMapping("updateArticle")
    public Result updateArticle(@RequestBody Article article){
        boolean update = articleService.updateById(article);
        if (update) {
            return Result.ok().message("修改成功");
        }else {
            return Result.error().message("修改失败");
        }
    }

    //删除文章
    @DeleteMapping("deleteArticle")
    public Result deleteArticle(Integer articleId){
        Boolean b = articleService.deleteArticle(articleId);
        if (b){
            return Result.ok().message("删除成功").data(b);
        }else {
            return Result.error().message("删除失败").data(b);

        }
    }

}
