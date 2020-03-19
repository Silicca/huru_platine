package com.app.huru.tools;

import com.applandeo.materialcalendarview.EventDay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe utilitaire permettant de manipuler et convertir des dates
 * */
public interface DateFormatter {

     static String eventDayToString(EventDay day){

         Date dateTime =  Date.from(day.getCalendar().toInstant());

         return new SimpleDateFormat("dd MM yyyy").format(dateTime);
     }

     static String dateToString(Date date){
         return new SimpleDateFormat("dd MM yyyy").format(date);
     }

     static Calendar stringToCalendar(String date){

         Calendar calendar = Calendar.getInstance();

         String[] splited = date.split(" ");

         int year = Integer.parseInt(splited[2]);
         int month = Integer.parseInt(splited[1])-1;
         int day = Integer.parseInt(splited[0]);

         calendar.set(year, month, day);

         return calendar;
     }

}
