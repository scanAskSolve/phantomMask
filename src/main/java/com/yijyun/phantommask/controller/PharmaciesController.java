package com.yijyun.phantommask.controller;

import com.yijyun.phantommask.pojo.ro.PharmaciesRo;
import com.yijyun.phantommask.service.PharmaciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PharmaciesController {

    @Autowired
    PharmaciesService pharmaciesService;

    @PostMapping("/Pharmacies")
    @ResponseBody
    public Object SavePharmacies(@RequestBody @Validated PharmaciesRo pharmaciesRo) {
        pharmaciesService.savePharmacies(pharmaciesRo);
        return "OK";
    }
}