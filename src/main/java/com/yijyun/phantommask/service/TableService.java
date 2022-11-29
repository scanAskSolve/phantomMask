package com.yijyun.phantommask.service;

import com.yijyun.phantommask.dao.ITableInitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TableService {
    @Autowired
    ITableInitDao itableInitDao;

    @PostConstruct
    public void initTable(){

        itableInitDao.createPharmacyTable();
        itableInitDao.createPharmacyOpeningHoursTable();
        itableInitDao.createMaskTable();
        itableInitDao.createUser();
        itableInitDao.createTransactionHistory();
    }
}
