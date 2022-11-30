package com.yijyun.phantommask.controller;

import com.yijyun.phantommask.pojo.vo.MessageVo;
import com.yijyun.phantommask.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("/Pharmacies/time")
    @ResponseBody
    public MessageVo SearchPharmaciesByTime(@RequestParam String week, @RequestParam String time) {
        MessageVo messageVo = searchService.checkSearchPharmaciesParam(week, time);
        if (messageVo.getErrorCode() == 200)
            messageVo = searchService.searchPharmaciesByTime(week, time);
        return messageVo;
    }
    @GetMapping("/Pharmacies/price")
    @ResponseBody
    public MessageVo SearchPharmaciesByPrice(@RequestParam Double low, @RequestParam Double high) {

        return searchService.searchPharmaciesByPrice(low, high);
    }

    @GetMapping("/Mask")
    @ResponseBody
    public MessageVo SearchMaskByPharmacy(@RequestParam String Pharmacy) {
        return searchService.SearchMaskByPharmacy(Pharmacy);
    }

    @GetMapping("/Transaction")
    @ResponseBody
    public MessageVo SearchTransactionByDate(@RequestParam String startDate,@RequestParam String endDate,@RequestParam Integer limit) {
        MessageVo messageVo = searchService.checkSearchDate(startDate, endDate);
        if (messageVo.getErrorCode() == 200)
            messageVo = searchService.searchTransactionByDate(startDate, endDate,limit);
        return messageVo;
    }
}
