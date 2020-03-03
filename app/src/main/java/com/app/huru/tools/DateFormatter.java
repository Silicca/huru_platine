package com.app.huru.tools;

import com.applandeo.materialcalendarview.EventDay;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe utilitaire permettant de manipuler et convertir des dates
 * */
public abstract class DateFormatter {

     private static final SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");

     public static String eventDayToString(EventDay day){

         Date dateTime =  Date.from(day.getCalendar().toInstant());

         return formatter.format(dateTime);
     }

     public static String dateToString(Date date){
         return formatter.format(date);
     }

     public static Calendar stringToCalendar(String date){

         Calendar calendar = Calendar.getInstance();

         String[] splited = date.split(" ");

         int year = Integer.parseInt(splited[2]);
         int month = Integer.parseInt(splited[1])-1;
         int day = Integer.parseInt(splited[0]);

         calendar.set(year, month, day);

         return calendar;
     }

}
