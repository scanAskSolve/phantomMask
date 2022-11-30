package com.yijyun.phantommask.controller;

import com.yijyun.phantommask.pojo.ro.HistoryRo;
import com.yijyun.phantommask.pojo.ro.PharmaciesRo;
import com.yijyun.phantommask.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryController {

    @Autowired
    HistoryService historyService;

    /**
     * import history to set user with history
     * @param historyRoList
     * @return
     */
    @PostMapping("/history")
    @ResponseBody
    public Object SaveHistory(@RequestBody List<HistoryRo> historyRoList) {
        historyService.SaveHistory(historyRoList);
        return "OK";
    }
}
