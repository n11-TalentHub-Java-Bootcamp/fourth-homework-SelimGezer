package com.SelimGezer.Odev4.Configure;

import com.SelimGezer.Odev4.Dao.CollectionDao;
import com.SelimGezer.Odev4.Dao.DebtDao;
import com.SelimGezer.Odev4.Dao.LateFeeRateDao;
import com.SelimGezer.Odev4.Dao.UserDao;
import com.SelimGezer.Odev4.Entities.Debt;
import com.SelimGezer.Odev4.Entities.LateFeeRate;
import com.SelimGezer.Odev4.Entities.User;
import com.SelimGezer.Odev4.Enum.DeptType;
import com.SelimGezer.Odev4.Util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class InitialValue implements CommandLineRunner {

    @Autowired
    UserDao userDao;
    @Autowired
    LateFeeRateDao lateFeeRateDao;
    @Autowired
    DebtDao debtDao;
    @Autowired
    CollectionDao collectionDao;

    @Override
    public void run(String... args) throws Exception {

        User user=new User( 1L,"Selim","Gezer","27selimgezer@gmail.com","0555_6557898",null);
        User user2=new User(2L,"Neşe","Yetkin","neşe@gmail.com","0598_6557898",null);
        User user3=new User(3L,"Alican","Tamam","alican@gmail.com","0587_6557898",null);
        User user4=new User(4L,"Alexandra","Peggy","alexandra@gmail.com","0568_8557898",null);
        User user5=new User(5L,"Nicolas","Page","nicolas@gmail.com","0520_6557845",null);

        userDao.saveAll(List.of(user,user2,user3,user4,user5));

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        LateFeeRate lateFeeRate=new LateFeeRate(simpleDateFormat.parse("2010-01-01"), Constants.simpleDateFormat.parse("2010-10-19"),1.95f);
        LateFeeRate lateFeeRate2=new LateFeeRate(simpleDateFormat.parse("2010-10-20"),simpleDateFormat.parse("2018-09-05"),1.4f);
        LateFeeRate lateFeeRate3=new LateFeeRate(simpleDateFormat.parse("2018-09-06"),simpleDateFormat.parse("2019-07-01"),1.4f);
        LateFeeRate lateFeeRate4=new LateFeeRate(simpleDateFormat.parse("2019-07-02"),simpleDateFormat.parse("2019-10-02"),2.0f);
        LateFeeRate lateFeeRate5=new LateFeeRate(simpleDateFormat.parse("2019-10-03"),simpleDateFormat.parse("2019-12-30"),2.0f);
        LateFeeRate lateFeeRate6=new LateFeeRate(simpleDateFormat.parse("2019-12-31"),null,1.6f);

        lateFeeRateDao.saveAll(List.of(lateFeeRate,lateFeeRate2,lateFeeRate3,lateFeeRate4,lateFeeRate5,lateFeeRate6));

        Debt debt =new Debt(1L,user,"Telefon borcu",1500,simpleDateFormat.parse(LocalDate.now().toString()),simpleDateFormat.parse("2010-01-01"), DeptType.NORMAL,null);
        Debt debt2=new Debt(2L,user2,"Pc borcu",100,simpleDateFormat.parse("2022-03-12"),simpleDateFormat.parse("2030-01-01"), DeptType.NORMAL,null);
       // Debt debt3=new Debt(3L,user2,"Pc borcu",200,simpleDateFormat.parse("2022-08-12"),simpleDateFormat.parse("2010-01-01"), DeptType.LATE_FEE,debt2);
        Debt debt4=new Debt(4L,user3,"Araba borcu",250000,simpleDateFormat.parse("2022-08-12"),simpleDateFormat.parse("2025-01-01"), DeptType.NORMAL,null);
        Debt debt5=new Debt(5L,user4,"Ev borcu",550000,simpleDateFormat.parse("2022-08-12"),simpleDateFormat.parse("2030-01-01"), DeptType.NORMAL,null);
        debtDao.saveAll(List.of(debt,debt2,debt4,debt5));

       // Collection collection=new Collection(1L,user,debt,150,simpleDateFormat.parse(LocalDate.now().toString()));
       // Collection collection2=new Collection(2L,user,debt3,200,simpleDateFormat.parse(LocalDate.now().toString()));
        //collectionDao.saveAll(List.of(collection,collection2));
    }
}
