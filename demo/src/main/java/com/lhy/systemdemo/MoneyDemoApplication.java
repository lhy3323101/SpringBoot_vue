package com.lhy.systemdemo;

import com.lhy.systemdemo.dao.MoneyDao;
import com.lhy.systemdemo.pojo.JodaMoney;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 *
 * @since 2020/5/28 17:54
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lhy.systemdemo.dao")
@Slf4j
public class MoneyDemoApplication implements ApplicationRunner{

    @Autowired
    private MoneyDao moneyDao;

    public static void main(String[] args) {
        SpringApplication.run(MoneyDemoApplication.class, args);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*JodaMoney jodaMoney = JodaMoney.builder().money(Money.of(CurrencyUnit.of("CNY"), 20)).build();
        Long id = moneyDao.save(jodaMoney);
        jodaMoney = moneyDao.findById(id);
        System.out.println(jodaMoney);*/
    }
}
