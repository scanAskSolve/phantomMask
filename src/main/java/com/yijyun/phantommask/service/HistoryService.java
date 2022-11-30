package com.yijyun.phantommask.service;

import com.yijyun.phantommask.dao.IHistoryDao;
import com.yijyun.phantommask.pojo.dto.PurchaseHistoriesDto;
import com.yijyun.phantommask.pojo.dto.UserDto;
import com.yijyun.phantommask.pojo.ro.HistoryRo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HistoryService {

    @Autowired
    IHistoryDao iHistoryDao;

    public void SaveHistory(List<HistoryRo> historyRoList) {
        historyRoList.forEach(historyRo -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(historyRo, userDto);
            iHistoryDao.saveUser(userDto);
            historyRo.getPurchaseHistories().forEach(purchaseHistories -> {
                PurchaseHistoriesDto purchaseHistoriesDto = new PurchaseHistoriesDto();
                BeanUtils.copyProperties(purchaseHistories, purchaseHistoriesDto);
                purchaseHistoriesDto.setUserId(userDto.getUserId());
                iHistoryDao.savePurchaseHistories(purchaseHistoriesDto);
            });
        });
    }

}
