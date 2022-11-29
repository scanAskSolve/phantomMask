package com.yijyun.phantommask.service;

import com.yijyun.phantommask.dao.IPharmaciesDao;
import com.yijyun.phantommask.pojo.dto.PharmaciesDto;
import com.yijyun.phantommask.pojo.dto.PharmacyOpeningHoursDto;
import com.yijyun.phantommask.pojo.ro.PharmaciesRo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class PharmaciesService {

    @Autowired
    IPharmaciesDao iPharmaciesDao;

    public void savePharmacies(List<PharmaciesRo> pharmaciesRoList) {
        pharmaciesRoList.forEach(pharmaciesRo -> {
            PharmaciesDto pharmaciesDto = new PharmaciesDto();
            BeanUtils.copyProperties(pharmaciesRo, pharmaciesDto);
            iPharmaciesDao.setPharmacy(pharmaciesDto);

            formatOpneHours(pharmaciesRo.getOpeningHours(), pharmaciesDto.getPharmaciesId());

            // create 商店
            //foreach create mask
        });
    }

    void formatOpneHours(String openingHours, Integer pharmaciesId) {

        HashMap<String, Integer> weekMap = getWeekMap();
        List<String> openHoursList = Arrays.asList(openingHours.split("/"));
        openHoursList.forEach(opening -> {
            Pattern pattern = Pattern.compile("[0-9][0-9]:[0-9][0-9] - [0-9][0-9]:[0-9][0-9]");
            Matcher matcher = pattern.matcher(opening);
            if (matcher.find()) {
                String[] time = matcher.group(0).trim().split("-");
                System.out.println("start time:" + time[0] + " end time:" + time[1]);
                Pattern patternWeek;
                if (Pattern.compile(",").matcher(opening).find()) {
                    patternWeek = Pattern.compile("[A-Z][a-z][a-z]+?");
                    matcher = patternWeek.matcher(opening);
                    while (matcher.find()) {
                        System.out.println(matcher.group());
                        PharmacyOpeningHoursDto pharmacyOpeningHoursDto = new PharmacyOpeningHoursDto();
                        pharmacyOpeningHoursDto.setPharmacyId(pharmaciesId);
                        pharmacyOpeningHoursDto.setOpenHoursStart(time[0]);
                        pharmacyOpeningHoursDto.setOpenHoursEnd(time[1]);
                        pharmacyOpeningHoursDto.setOpenWeek(weekMap.get(matcher.group()));
                        iPharmaciesDao.setPharmacyOpeningHours(pharmacyOpeningHoursDto);
                    }
                } else {
                    patternWeek = Pattern.compile(
                            "[A-Z][a-z][a-z]+? - [A-Z][a-z][a-z]+?");
                    matcher = patternWeek.matcher(opening);
                    if (matcher.find()) {
                        String[] week = matcher.group(0).trim().split("-");
                        System.out.println(week[0] + ":::" + week[1]);
                        for (int i = weekMap.get(week[0].trim()); i < weekMap.get(week[1].trim()); i++) {
                            PharmacyOpeningHoursDto pharmacyOpeningHoursDto = new PharmacyOpeningHoursDto();
                            pharmacyOpeningHoursDto.setPharmacyId(pharmaciesId);
                            pharmacyOpeningHoursDto.setOpenHoursStart(time[0]);
                            pharmacyOpeningHoursDto.setOpenHoursEnd(time[1]);
                            pharmacyOpeningHoursDto.setOpenWeek(i);
                        }
                    }
                }
            }

        });
    }

    /**
     * Get week Hash
     */
    HashMap<String, Integer> getWeekMap() {
        String[] week = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < week.length; i++) {
            map.put(week[i], i);
        }
        return map;
    }
}
