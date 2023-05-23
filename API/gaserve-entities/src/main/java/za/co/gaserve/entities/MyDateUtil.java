 package za.co.gaserve.entities;
 
 import java.util.Calendar;
 import java.util.Date;
 import java.util.GregorianCalendar;
 import java.util.TimeZone;

 import org.apache.commons.lang3.time.DateUtils;
 
 
 public abstract class MyDateUtil
 {
   public static boolean in(Date date, Iterable<Date> dates)
   {
     for (Date tmpDate : dates) {
       if (DateUtils.isSameDay(date, tmpDate)) {
         return true;
       }
     }
     return false;
   }
   
   public static int getDayOfWeek(Date date) {
     Calendar cal = new GregorianCalendar();
     cal.setTime(date);
     return cal.get(7);
   }

   public static int getMonth(Date date) {
	     Calendar cal = new GregorianCalendar();
	     cal.setTime(date);
	     return cal.get(Calendar.MONTH);
   }

   public static int getHour(Date date) {
     Calendar cal = new GregorianCalendar();
     cal.setTime(date);
     return cal.get(11);
   }
   
   public static int getMinute(Date date) {
     Calendar cal = new GregorianCalendar();
     cal.setTime(date);
     return cal.get(12);
   }
   
   public static boolean isSameDayOfWeek(Date date1, Date date2) {
     return getDayOfWeek(date1) == getDayOfWeek(date2);
   }

   public static boolean isSameMonth(Date date1, Date date2) {
	     return getMonth(date1) == getMonth(date2);
	   }

   public static boolean isDayOfWeek(Date date1, int dayOfWeek) {
     return getDayOfWeek(date1) == dayOfWeek;
   }
   
   public static Date createDate(int year, int month, int day)
   {
     Calendar cal = new GregorianCalendar();
     cal.set(year, month, day, 0, 0, 0);
     return cal.getTime();
   }
   
   public static Date setEndOfDay(Date date) {
     Calendar cal = new GregorianCalendar();
     cal.setTime(date);
     cal.set(11, 23);
     cal.set(13, 59);
     cal.set(12, 59);
     cal.set(cal.MILLISECOND,0);


     return cal.getTime();
   }
   
   public static Date setStartOfMonth(Date date) {
     Calendar cal = new GregorianCalendar();
     cal.setTime(date);
     cal.set(Calendar.DAY_OF_MONTH, 1);
     cal.set(Calendar.HOUR_OF_DAY, 0);
     cal.set(Calendar.MINUTE, 0);
     cal.set(Calendar.SECOND, 0);
     cal.set(Calendar.MILLISECOND, 0);
     return cal.getTime();
   }

   public static Date setStartOfDay(Date date) {

     Calendar cal = new GregorianCalendar();


        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 1);

        return cal.getTime();
   }

   public static Date createDate(int year, int month, int day, int hour, int minutes) {
     Calendar cal = new GregorianCalendar();
     cal.setTimeZone(TimeZone.getTimeZone("UTC"));
     cal.set(year, month, day, hour, minutes, 0);
     return cal.getTime();
   }
 }