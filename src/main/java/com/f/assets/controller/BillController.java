package com.f.assets.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.f.assets.pojo.Bill;
import com.f.assets.pojo.BillMonthDetailVo;
import com.f.assets.service.BillDetailService;
import com.f.assets.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class BillController {

    @Autowired
    BillDetailService billDetailService;

    @Autowired
    BillService billService;

    private static int balance = -1;

    @GetMapping
    public String index(Model model) {
        List<BillMonthDetailVo> billDetails = billDetailService.findBillDetails(balance);
        model.addAttribute("billMonth", billDetails);
        model.addAttribute("balance", balance);
        return "index";
    }

    @ResponseBody
    @GetMapping("data")
    public List<BillMonthDetailVo> data() {
        return billDetailService.findBillDetails(balance);
    }

    /**
     *
     */
    @GetMapping("add")
    public String addView(Model model) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_date");
        List list = billService.list(queryWrapper);
        model.addAttribute("list", list);
        List<String> labels = billService.labels();
        model.addAttribute("labels", labels);
        model.addAttribute("balance", balance);
        return "add";
    }

    @PostMapping("add")
    public String add(Bill bill) {
        if ("余额".equals(bill.getName())) {
            balance = bill.getAmount().intValue();
        } else {
            billService.add(bill);
        }
        return "redirect:./add";
    }

    @GetMapping("delete")
    public String delele(long id) {
        billService.deleteBill(id);
        return "redirect:./add";
    }

    @Scheduled(cron = " 0 50 23 * * ?")
    public void updateBalance() {
        List<BillMonthDetailVo> billDetails = billDetailService.findBillDetails(balance);
        balance = balance - billDetails.get(0).getAmount();
    }
}
