package com.yijyun.phantommask.service;

import com.yijyun.phantommask.component.StatusEnum;
import com.yijyun.phantommask.dao.ITransactionDao;
import com.yijyun.phantommask.pojo.dto.PurchaseHistoriesDto;
import com.yijyun.phantommask.pojo.dto.UserDto;
import com.yijyun.phantommask.pojo.ro.TransactionRo;
import com.yijyun.phantommask.pojo.vo.MessageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    ITransactionDao iTransactionDao;

    public MessageVo buyMask(TransactionRo transactionRo) {

        Integer pharmacyId = iTransactionDao.findPharmacyByName(transactionRo.getPharmacyName());
        UserDto userDto = iTransactionDao.findUserByName(transactionRo.getName());
        if (pharmacyId != null && userDto != null) {
            String pharmacyCostStr = iTransactionDao.findMaskPriceByPharmacyIdAndName(pharmacyId, transactionRo.getMaskName());
            Double pharmacyCost = 0.0;
            try {
                pharmacyCost = Double.valueOf(pharmacyCostStr);
            } catch (Exception e) {
                return new MessageVo(StatusEnum.PARAM_INSERT_ERROR, "not find Mask");
            }
            if (transactionRo.getTransactionAmount() < userDto.getCashBalance()) {
                addTransactionRecord(transactionRo, userDto);
                iTransactionDao.userDebit(transactionRo.getTransactionAmount(),userDto.getUserId());
                iTransactionDao.pharmacyCollect(transactionRo.getTransactionAmount(),pharmacyId);
            } else {
                return new MessageVo(StatusEnum.SUCCESS, "User not enough money");
            }
        } else {
            return new MessageVo(StatusEnum.PARAM_INSERT_ERROR, "not find PharmacyName or User");
        }
        return new MessageVo(StatusEnum.SUCCESS, "");
    }

    void addTransactionRecord(TransactionRo transactionRo, UserDto userDto) {
        PurchaseHistoriesDto purchaseHistoriesDto = new PurchaseHistoriesDto();
        BeanUtils.copyProperties(transactionRo, purchaseHistoriesDto);
        purchaseHistoriesDto.setUserId(userDto.getUserId());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        purchaseHistoriesDto.setTransactionDate(localDateTime.format(format));
        iTransactionDao.saveTransactionHistory(purchaseHistoriesDto);
    }

}
