package com.yijyun.phantommask.dao;

import com.yijyun.phantommask.pojo.dto.PurchaseHistoriesDto;
import com.yijyun.phantommask.pojo.dto.UserDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ITransactionDao {

    @Select("Select * from `phantom_mask`.`user` " +
            "where name = #{name}")
    UserDto findUserByName(@Param("name")String name);
    @Select("Select pharmacy_id from `phantom_mask`.`pharmacy` " +
            "where name = #{name}")
    Integer findPharmacyByName(@Param("name")String name);
    @Select("Select price from `phantom_mask`.`mask` " +
            "where pharmacy_id = #{pharmacyId} AND name = #{name}")
    String findMaskPriceByPharmacyIdAndName(@Param("pharmacyId") Integer pharmacyId,@Param("name")String name);

    @Insert("Insert INTO `phantom_mask`.`transaction_history` " +
            "(user_id,pharmacy_name,transaction_amount,mask_name,transaction_date) " +
            "Values (#{bean.userId},#{bean.pharmacyName},#{bean.transactionAmount},#{bean.maskName},#{bean.transactionDate})")
    void saveTransactionHistory(@Param("bean")PurchaseHistoriesDto purchaseHistoriesDto);

    @Update("UPDATE `phantom_mask`.`user` SET cash_balance= cash_balance- ${debit} where user_id = #{id}")
    void userDebit(@Param("debit") Double debit,@Param("id") Integer id);

    @Update("UPDATE `phantom_mask`.`pharmacy` SET cash_balance= cash_balance+${collect} where pharmacy_id = #{id}")
    void pharmacyCollect(@Param("collect")Double collect,@Param("id") Integer id);
}
