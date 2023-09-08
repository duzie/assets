package com.f.assets.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.f.assets.pojo.Bill;

import java.util.List;

public interface BillService extends IService<Bill> {

    void add(Bill bill);

    void deleteBill(long id);

    /**
     * 最近一个月的账单名
     *
     * @return List<String>
     */
    List<String> labels();
}
