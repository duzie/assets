package com.f.assets.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f.assets.mapper.BillDetailMapper;
import com.f.assets.pojo.BillDetail;
import com.f.assets.pojo.BillDetailVo;
import com.f.assets.pojo.BillMonthDetailVo;
import com.f.assets.service.BillDetailService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BillDetailServiceImpl extends ServiceImpl<BillDetailMapper, BillDetail> implements BillDetailService {

    @Override
    public List<BillMonthDetailVo> findBillDetails(int balance) {
        QueryWrapper<BillDetail> wrapper = new QueryWrapper<>();
        wrapper.ge("bill_date", DateUtils.truncate(new Date(), Calendar.DATE));
        List<BillDetail> list = list(wrapper);
        Map<Date, List<BillDetail>> collect = list.stream().collect(Collectors.groupingBy(BillDetail::getBillDate));
        Set<Date> dates = collect.keySet();

        List<BillDetailVo> billDetailVos = new ArrayList<>();
        for (Date date : dates) {
            BillDetailVo billDetailVo = new BillDetailVo();
            billDetailVo.setBillDate(date);
            billDetailVo.setAmount(collect.get(date).stream().mapToDouble(b->b.getAmount().doubleValue()).sum());
            billDetailVo.setBillDetails(collect.get(date));
            billDetailVos.add(billDetailVo);
        }
        billDetailVos.sort(Comparator.comparing(BillDetailVo::getBillDate));

        for (BillDetailVo billDetailVo : billDetailVos) {
            balance = billDetailVo.getAmount().intValue() + balance;
            billDetailVo.setBalance(balance);
        }

        Map<Date, List<BillDetailVo>> monthCollect = billDetailVos.stream().collect(Collectors.groupingBy(BillDetailVo::getBillByMonth));
        List<BillMonthDetailVo> billMonthDetailVos = new ArrayList<>();
        Set<Date> dates1 = monthCollect.keySet();
        for (Date date : dates1) {
            BillMonthDetailVo billMonthDetailVo = new BillMonthDetailVo();
            billMonthDetailVo.setBillDate(date);
            billMonthDetailVo.setBillDetails(monthCollect.get(date));
            billMonthDetailVo.setAmount(monthCollect.get(date).stream().mapToDouble(BillDetailVo::getAmount).sum());
            billMonthDetailVos.add(billMonthDetailVo);
        }
        billMonthDetailVos.sort(Comparator.comparing(BillMonthDetailVo::getBillDate));
        return billMonthDetailVos;
    }
}
