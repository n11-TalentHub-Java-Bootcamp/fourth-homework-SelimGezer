package com.SelimGezer.Odev4.Util;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {

    public static float BEFORE_2018=1.5F;
    public static float AFTER_2018=1.5F;

    public static String pattern = "yyyy-MM-dd";
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);


}
