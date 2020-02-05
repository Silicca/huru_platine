package com.app.huru.tools;

import com.applandeo.materialcalendarview.EventDay;

import java.text.SimpleDateFormat;
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
}
