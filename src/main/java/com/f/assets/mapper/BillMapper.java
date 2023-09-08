package com.f.assets.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.f.assets.pojo.Bill;

import java.util.List;

public interface BillMapper extends BaseMapper<Bill> {

    List<String> labels();
}