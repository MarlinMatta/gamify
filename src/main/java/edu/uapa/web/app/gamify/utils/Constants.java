package edu.uapa.web.app.gamify.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Constants {

    @Value("${spring.profiles.active}")
    private String profile;
    private static final String HOUR_FORMAT = "HH:mm:ss";
    private static final String DATE_FIND = "yyyy-MM-dd";
    private static final String DATE_HOUR_FIND = DATE_FIND + " " + HOUR_FORMAT;

    public static final String SYSTEM_USER = "System";

    public static final SimpleDateFormat hourFormat = new SimpleDateFormat(HOUR_FORMAT);
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FIND);
    public static final SimpleDateFormat simpleDateFormatToFind = new SimpleDateFormat(DATE_HOUR_FIND);
    public static final SimpleDateFormat simpleDateFormatOnlyHour12 = new SimpleDateFormat(HOUR_FORMAT);
    public static final DecimalFormat decimalFormat = new DecimalFormat("#,###.00");

    public static final Gson GSON = new GsonBuilder().create();
}