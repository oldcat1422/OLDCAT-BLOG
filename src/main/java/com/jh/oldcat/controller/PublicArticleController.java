package com.jh.oldcat.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jh.oldcat.entity.Article;
import com.jh.oldcat.entity.VO.ArticleTagVo;
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





    /**
     * 前台-展示数据
     * 获取全部文章-状态为2（已上架）
     * (分页 pageNo当前页码  pageSize每页显示个数)
     * @param articleTagVo
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/getAllArticlePage")
    public Result getAllArticlePage(ArticleTagVo articleTagVo,
                                    @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                    @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize){
        IPage<Article> page = new Page<>(pageNo,pageSize);
        IPage<Article> allArticlePage = articleService.getAllArticlePage(page, articleTagVo);
        return Result.ok().data(allArticlePage);
    }

    //获取1篇文章
    @PostMapping("getOneArticle")
    public Result getOneArticle(Integer articleId){
        Article oneArticle = articleService.getOneArticle(articleId);
        return Result.ok().data(oneArticle);
    }

    //相关推荐4条
    @PostMapping("moreSuggest")
    public Result moreSuggest(Integer id){
        List<Article> article = articleService.moreSuggest(id);
        return Result.ok().message("推荐成功").data(article);
    }

    //点击后浏览量+1
    @PostMapping("addView")
    public Result addView(Integer articleId){
        articleService.addView(articleId);
        return Result.ok().message("浏览量+1");
    }

    //获取总浏览量
    @PostMapping("getViews")
    public Result getViews(){
        Integer views = articleService.getViews();
        if(views>999){
            System.out.println(views/1000.0 + "k");
            return Result.ok().data(views/1000.0 + "k");
        }
        return Result.ok().data(views);
    }
}
