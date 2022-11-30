package com.yijyun.phantommask.dao;

import com.yijyun.phantommask.pojo.dto.MaskDto;
import com.yijyun.phantommask.pojo.dto.PharmaciesDto;
import com.yijyun.phantommask.pojo.vo.TransactionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ISearchDao {

    @Select("Select pharmacy_id from `phantom_mask`.`pharmacy_opening_hours` " +
            "where open_week = #{week} AND open_hours_start<#{time} AND open_hours_end > #{time} ")
    List<Integer> searchPharmacyOpeningHours(@Param("week") Integer week, @Param("time") String time);

    @Select("Select * from `phantom_mask`.`pharmacy` " +
            "where pharmacy_id = #{id}")
    PharmaciesDto searchPharmacyById(@Param("id") Integer id);

    @Select("Select * from `phantom_mask`.`pharmacy` " +
            "where name = #{name}")
    PharmaciesDto searchPharmacyByName(@Param("name") String name);
    @Select("Select * from `phantom_mask`.`mask` " +
            "where pharmacy_id = #{id} " +
            "ORDER BY price ASC")
    List<MaskDto> searchMaskByPharmacyIdSortPrice(@Param("id") Integer id);

    @Select("Select pharmacy_id from `phantom_mask`.`mask` " +
            "where price > #{low} AND price < #{high} ")
    List<Integer> searchMaskByPrice(@Param("low") Double low,@Param("high") Double high);

    @Select("SELECT user.name,SUM(transaction_history.transaction_amount) AS totalAmount FROM `phantom_mask`.`transaction_history` AS transaction_history\n" +
            "JOIN `phantom_mask`.`user` AS user ON transaction_history.user_id = user.user_id\n" +
            "where transaction_date>#{start} AND transaction_date<#{end}" +
            "GROUP BY transaction_history.user_id\n" +
            "ORDER BY totalAmount DESC " +
            "limit ${limit}")
    List<TransactionVo> searchTransactionByDate(@Param("start") String start,@Param("end") String end,@Param("limit") Integer limit);
}
