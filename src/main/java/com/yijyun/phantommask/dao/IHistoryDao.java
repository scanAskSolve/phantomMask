package com.yijyun.phantommask.dao;

import com.yijyun.phantommask.pojo.dto.PurchaseHistoriesDto;
import com.yijyun.phantommask.pojo.dto.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IHistoryDao {

    @Insert("INSERT INTO `phantom_mask`.`user` (name,cash_balance) Values " +
            "(#{bean.name},#{bean.cashBalance})")
    @Options(useGeneratedKeys=true, keyProperty="userId")
    void saveUser(@Param("bean") UserDto userDto);

    @Insert("INSERT INTO `phantom_mask`.`transaction_history` (user_id,pharmacy_name,transaction_amount,mask_name,transaction_date) Values " +
            "(#{bean.userId},#{bean.pharmacyName},#{bean.transactionAmount},#{bean.maskName},#{bean.transactionDate})")
    void savePurchaseHistories(@Param("bean") PurchaseHistoriesDto purchaseHistoriesDto);
}
