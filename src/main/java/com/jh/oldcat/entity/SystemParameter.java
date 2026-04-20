package com.jh.oldcat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemParameter {
    @TableId(value = "para_id", type = IdType.AUTO)
    private Integer paraId;
    private String paraName;
    private String paraVal;
    private String paraDescription;
}
