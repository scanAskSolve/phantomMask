package com.yijyun.phantommask.controller;

import com.yijyun.phantommask.pojo.ro.TransactionRo;
import com.yijyun.phantommask.pojo.vo.MessageVo;
import com.yijyun.phantommask.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/Transaction")
    @ResponseBody
    public MessageVo BuyMask(@RequestBody TransactionRo transactionRo) {
        return transactionService.buyMask(transactionRo);
    }
}
