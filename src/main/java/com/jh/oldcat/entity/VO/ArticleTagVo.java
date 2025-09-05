package com.jh.oldcat.entity.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTagVo {
    private Integer id;
    private String title;
    private String neirong;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date time;
    private Integer state;
    private String description;//摘要
    private String tag;
    private String image;
    private Integer pageViews;
}
