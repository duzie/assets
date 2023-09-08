package com.f.assets.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.f.assets.pojo.BillDetail;
import com.f.assets.pojo.BillMonthDetailVo;

import java.util.List;

public interface BillDetailService extends IService<BillDetail> {


    List<BillMonthDetailVo> findBillDetails(int balance);

}
