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
        //先处理文章数据
        if(article.getTitle() == null || article.getTitle().length() == 0){
            return Result.error().message("文章标题不能为空");
        }else if(article.getNeirong() == null || article.getNeirong().length() == 0){
            return Result.error().message("文章正文不能为空");
        } else if (article.getTag() == null) {
            return Result.error().message("请选择文章标签");
        }else{
            article.setState(0);    //默认文章状态设置成0未上架
            boolean save = articleService.save(article);
            if (save) {
                return Result.ok().message("添加成功");
            }else {
                return Result.error().message("登陆失败");
            }
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

    /**
     * 删除文章（将状态改为  0已删除  ）
     * @param articleId
     * @return
     */
    @DeleteMapping("deleteArticle")
    public Result deleteArticle(Integer articleId){
        Boolean b = articleService.deleteArticle(articleId);
        if (b){
            return Result.ok().message("删除成功").data(b);
        }else {
            return Result.error().message("删除失败").data(b);

        }
    }

    /**
     * 后台-文章管理-回收站
     * 回收站（状态为0的文章）
     * @param articleTagVo
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/recycle")
    public Result recycle(ArticleTagVo articleTagVo,
                          @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                          @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        IPage<Article> page = new Page<>(pageNo,pageSize);
        IPage<Article> allArticle = articleService.recycle(page, articleTagVo);
        return Result.ok().message("获取成功").data(allArticle);
    }
    /**
     * 后台-文章设置
     * 获取全部文章-（状态不等于0）未删除的
     * @return
     */
    @GetMapping("/getAllArticle")
    public Result getAllArticle(ArticleTagVo articleTagVo,
                                @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        IPage<Article> page = new Page<>(pageNo,pageSize);
        IPage<Article> allArticle = articleService.getAllArticle(page, articleTagVo);
        return Result.ok().message("获取成功").data(allArticle);
    }
}
