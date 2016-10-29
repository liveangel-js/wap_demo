package com.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by liveangel on 2016-10-29.
 */

@Service
public final class Utility {
    private static final Logger log = LoggerFactory.getLogger(Utility.class);
    public static void hello(){
        log.info("Hello");

    }

    public static double random(double min, double max){
        return min + Math.random()*(max-min);
    }

    public static double decimalTwo(double in){
        return (double)Math.round(in*100)/100;
    }

    public static Date dayAfterToday(int n){
        Calendar   rightNow   =   Calendar.getInstance();
        rightNow.add(Calendar.DAY_OF_MONTH,+n);
        return rightNow.getTime();
    }


}
