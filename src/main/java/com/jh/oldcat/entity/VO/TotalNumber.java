package com.jh.oldcat.entity.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalNumber {
    private Long ArticleNumber;
    private Long TagNumber;
    private Long UserNumber;
    private BigDecimal PageViews;
}
