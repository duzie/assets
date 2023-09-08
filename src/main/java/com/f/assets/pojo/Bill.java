package com.f.assets.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Bill {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 账单名称
     */
    private String name;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 期数
     */
    private Integer periods;

    /**
     * 周期
     */
    private Integer cycle;

    /**
     * 开始日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    private Date createDate;
}