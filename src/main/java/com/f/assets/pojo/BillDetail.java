package com.f.assets.pojo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class BillDetail {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 日期
     */
    private Date billDate;

    /**
     * 账单ID
     */
    private Long billId;

    private String billName;
}