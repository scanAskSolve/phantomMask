package com.yijyun.phantommask.service;

import com.yijyun.phantommask.component.StatusEnum;
import com.yijyun.phantommask.dao.ISearchDao;
import com.yijyun.phantommask.pojo.dto.MaskDto;
import com.yijyun.phantommask.pojo.dto.PharmaciesDto;
import com.yijyun.phantommask.pojo.vo.MessageVo;
import com.yijyun.phantommask.pojo.vo.TransactionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
public class SearchService {

    @Autowired
    PharmaciesService pharmaciesService;

    @Autowired
    ISearchDao iSearchDao;

    public MessageVo checkSearchPharmaciesParam(String week, String time) {
        if (pharmaciesService.getWeekMap().getOrDefault(week, 8) == 8) {
            return new MessageVo(StatusEnum.PARAM_INSERT_ERROR, "please week ex:Sun or Mon");
        }
        try {
            LocalTime checkTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e) {
            return new MessageVo(StatusEnum.PARAM_INSERT_ERROR, "please time HH:mm ex:18:00 ");
        }
        return new MessageVo(StatusEnum.SUCCESS, "");
    }

    public MessageVo checkSearchDate(String startDate, String endDate) {
        try {
            LocalDate checkTime = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
            checkTime = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {
            return new MessageVo(StatusEnum.PARAM_INSERT_ERROR, "please Date yyyymmdd ex:20221130 ");
        }
        return new MessageVo(StatusEnum.SUCCESS, "");
    }

    public MessageVo searchPharmaciesByTime(String week, String time) {
        List<Integer> PharmaciesId = iSearchDao.searchPharmacyOpeningHours(pharmaciesService.getWeekMap().get(week), time);
        List<String> pharmacies = new ArrayList<>();
        PharmaciesId.forEach(id -> {
            PharmaciesDto pharmacy = iSearchDao.searchPharmacyById(id);
            pharmacies.add(pharmacy.getName());
        });
        return new MessageVo(StatusEnum.SUCCESS, pharmacies);
    }

    public MessageVo SearchMaskByPharmacy(String pharmacy) {
        PharmaciesDto pharmaciesDto = iSearchDao.searchPharmacyByName(pharmacy);
        List<MaskDto> maskDtoList = iSearchDao.searchMaskByPharmacyIdSortPrice(pharmaciesDto.getPharmacyId());
        return new MessageVo(StatusEnum.SUCCESS, maskDtoList);
    }

    public MessageVo searchPharmaciesByPrice(Double low, Double high) {
        List<Integer> pharmaciesId = iSearchDao.searchMaskByPrice(low, high);
        HashSet<Integer> pharmaciesIdSet = new HashSet<>(pharmaciesId);
        List<String> pharmaciesList = new ArrayList<>();
        pharmaciesIdSet.forEach(id -> {
            PharmaciesDto pharmaciesDto = iSearchDao.searchPharmacyById(id);
            pharmaciesList.add(pharmaciesDto.getName());
        });

        return new MessageVo(StatusEnum.SUCCESS, pharmaciesList);
    }

    public MessageVo searchTransactionByDate(String startDate, String endDate, Integer limit) {
        List<TransactionVo> transactionVoList = iSearchDao.searchTransactionByDate(startDate, endDate, limit);
        return new MessageVo(StatusEnum.SUCCESS, transactionVoList);
    }
}
