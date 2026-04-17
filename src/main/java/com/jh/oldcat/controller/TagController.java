package com.jh.oldcat.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jh.oldcat.entity.Article;
import com.jh.oldcat.entity.Tag;
import com.jh.oldcat.service.TagService;
import com.jh.oldcat.utils.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import static net.sf.jsqlparser.parser.feature.Feature.update;

import java.util.List;

@RestController
@RequestMapping("/tag")
@CrossOrigin
@AllArgsConstructor
public class TagController {
    private TagService tagService;

    //添加标签
    @PutMapping("addTag")
    public Result updateArticle(@RequestBody Tag tag){
        boolean save = tagService.save(tag);
        if (save) {
            return Result.ok().message("添加成功");
        }else {
            return Result.error().message("修改失败");
        }
    }

    //修改
    @PostMapping("updateTag")
    public Result updateTag(@RequestBody Tag tag){
        boolean b = tagService.updateById(tag);
        if (b) {
            return Result.ok().message("修改成功");
        }else {
            return Result.error().message("修改失败");
        }
    }

    //删除
    @PostMapping("deleteTag")
    public Result deleteTag(Integer id){
        boolean b = tagService.removeById(id);
        if (b) {
            return Result.ok().message("删除成功");
        }else{
            return Result.error().message("删除失败");
        }
    }

    //获取全部tag(分页 pageNo当前页码  pageSize每页显示个数)
    @PostMapping("/getAllTag")
    public Result getAllArticlePage(Tag tag,
                                    @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                    @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        IPage<Tag> page = new Page<>(pageNo,pageSize);
        IPage<Tag> allTag= tagService.getAllTag(page, tag);
        return Result.ok().data(allTag);
    }

    //获取全部tag（不分页）
    @GetMapping("/getAllTags")
    public Result getAllTags(){
        List<Tag> tags = tagService.list();
        return Result.ok().data(tags);
    }

    //获取一个tag（修改时获取信息用的）
    @PostMapping("getOneTag")
    public Result getOneArticle(Integer id){
        Tag tag = tagService.getById(id);
        return Result.ok().data(tag);
    }

}
