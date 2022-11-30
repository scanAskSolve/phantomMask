package com.yijyun.phantommask.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ITableInitDao {

    @Update("CREATE TABLE IF NOT EXISTS `phantom_mask`.`pharmacy` (\n" +
            "\t`pharmacy_id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
            "\t`name` VARCHAR(50) NOT NULL DEFAULT '0' COLLATE 'utf8mb4_general_ci',\n" +
            "\t`cash_balance` DOUBLE NOT NULL DEFAULT '0',\n" +
            "\t`create_time` DATETIME NOT NULL DEFAULT curtime(),\n" +
            "\tPRIMARY KEY (`pharmacy_id`) USING BTREE,\n" +
            "\tINDEX `name` (`name`) USING BTREE\n" +
            ")\n" +
            "COLLATE='utf8mb4_general_ci'\n" +
            "ENGINE=InnoDB\n" +
            ";\n")
    void createPharmacyTable();

    @Update("CREATE TABLE IF NOT EXISTS `phantom_mask`.`pharmacy_opening_hours` (\n" +
            "\t`pharmacy_opening_hours_id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
            "\t`pharmacy_id` INT(11) NOT NULL,\n" +
            "\t`open_hours_start` TIME NOT NULL,\n" +
            "\t`open_hours_end` TIME NOT NULL,\n" +
            "\t`open_week` INT(11) NOT NULL COMMENT '0->星期一 6->星期日',\n" +
            "\tPRIMARY KEY (`pharmacy_opening_hours_id`) USING BTREE,\n" +
            "\tINDEX `FK__pharmacy` (`pharmacy_id`) USING BTREE,\n" +
            "\tCONSTRAINT `FK__pharmacy` FOREIGN KEY (`pharmacy_id`) REFERENCES `pharmacy` (`pharmacy_id`) ON UPDATE NO ACTION ON DELETE NO ACTION\n" +
            ")\n" +
            "COLLATE='utf8mb4_general_ci'\n" +
            "ENGINE=InnoDB\n" +
            ";")
    void createPharmacyOpeningHoursTable();

    @Update("CREATE TABLE IF NOT EXISTS `phantom_mask`.`mask` (\n" +
            "\t`mask_id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
            "\t`pharmacy_id` INT(11) NOT NULL,\n" +
            "\t`name` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'utf8mb4_general_ci',\n" +
            "\t`price` DOUBLE NOT NULL DEFAULT '0',\n" +
            "\t`per` INT(11) NOT NULL DEFAULT '0',\n" +
            "\t`create_time` DATETIME NOT NULL DEFAULT curtime(),\n" +
            "\tPRIMARY KEY (`mask_id`) USING BTREE,\n" +
            "\tINDEX `FK_mask_pharmacy` (`pharmacy_id`) USING BTREE,\n" +
            "\tCONSTRAINT `FK_mask_pharmacy` FOREIGN KEY (`pharmacy_id`) REFERENCES `pharmacy` (`pharmacy_id`) ON UPDATE NO ACTION ON DELETE NO ACTION\n" +
            ")\n" +
            "COLLATE='utf8mb4_general_ci'\n" +
            "ENGINE=InnoDB\n" +
            "AUTO_INCREMENT=96\n" +
            ";\n")
    void createMaskTable();

    @Update("CREATE TABLE IF NOT EXISTS `phantom_mask`.`user` (\n" +
            "\t`user_id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
            "\t`name` VARCHAR(50) NOT NULL DEFAULT '0' COLLATE 'utf8mb4_general_ci',\n" +
            "\t`cash_balance` INT(11) NOT NULL DEFAULT '0',\n" +
            "\tPRIMARY KEY (`user_id`) USING BTREE\n" +
            ")\n" +
            "COLLATE='utf8mb4_general_ci'\n" +
            "ENGINE=InnoDB\n" +
            ";\n")
    void createUser();

    @Update("CREATE TABLE IF NOT EXISTS `phantom_mask`.`transaction_history` (\n" +
            "\t`transaction_history_id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
            "\t`user_id` INT(11) NOT NULL,\n" +
            "\t`pharmacy_name` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'utf8mb4_general_ci',\n" +
            "\t`transaction_amount` DOUBLE NOT NULL DEFAULT '0',\n" +
            "\t`mask_name` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'utf8mb4_general_ci',\n" +
            "\t`transaction_date` DATETIME NULL DEFAULT NULL,\n" +
            "\tPRIMARY KEY (`transaction_history_id`) USING BTREE,\n" +
            "\tINDEX `FK_transaction_history_user` (`user_id`) USING BTREE,\n" +
            "\tINDEX `FK_transaction_history_pharmacy` (`pharmacy_name`) USING BTREE,\n" +
            "\tINDEX `FK_transaction_history_mask` (`mask_name`) USING BTREE\n" +
            ")\n" +
            "COLLATE='utf8mb4_general_ci'\n" +
            "ENGINE=InnoDB\n" +
            ";")
    void createTransactionHistory();
}
