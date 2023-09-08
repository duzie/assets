package com.f.assets.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class BillMonthDetailVo {


    private Date billDate;

    private int amount;

    private List<BillDetailVo> billDetails;



}
