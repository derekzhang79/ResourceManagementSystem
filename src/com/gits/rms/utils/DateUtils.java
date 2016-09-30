
package com.gits.rms.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    public static Boolean compareDate(Date fromDate, Date toDate) {
        if (fromDate.after(toDate)) {
            return false;
        } else {
            return true;
        }
    }
    
    public static Boolean compareGreaterOrEqual(Date fromDate, Date toDate) {
        if (fromDate.equals(toDate)) {
            return false;
        } else {
            return true;
        }
    }

    private static Calendar getCalendar() {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setLenient(false);
        cal.clear();
        cal.set(Calendar.DATE, 0);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.YEAR, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.AM_PM, 0);
        return cal;
    }

    public static java.sql.Date getCurrentDateTime() {
        Date today = new Date();
        return new java.sql.Date(today.getTime());
    }

    public static Timestamp getCurrentTime() {
        Date today = new Date();
        return new Timestamp(today.getTime());
    }

    public static Date getDateOfPreviousMonth() {
        long ltime = System.currentTimeMillis();
        Calendar cal = getCalendar();
        cal.setTimeInMillis(ltime);

        cal.add(Calendar.MONTH, -1);
        int iDate = cal.get(Calendar.DATE);
        int iMonth = cal.get(Calendar.MONTH);
        int iYear = cal.get(Calendar.YEAR);

        Date dPreviousMonth = new Date(iYear, iMonth, iDate);
        return dPreviousMonth;
    }

    public static Date getDateOfPreviousMonth(Date dToDate) {
        System.currentTimeMillis();
        Calendar cal = getCalendar();
        cal.set(dToDate.getYear(), dToDate.getMonth(), dToDate.getDate());

        cal.add(Calendar.MONTH, -1);
        int iDate = cal.get(Calendar.DATE);
        int iMonth = cal.get(Calendar.MONTH);
        int iYear = cal.get(Calendar.YEAR);

        Date dPreviousMonth = new Date(iYear, iMonth, iDate);
        return dPreviousMonth;
    }

    public static Date getYesterdayDate() {
        long ltime = System.currentTimeMillis();
        Calendar cal = getCalendar();
        cal.setTimeInMillis(ltime);

        cal.add(Calendar.DATE, -1);
        int iDate = cal.get(Calendar.DATE);
        int iMonth = cal.get(Calendar.MONTH);
        int iYear = cal.get(Calendar.YEAR);

        Date dYesterday = new Date(iYear, iMonth, iDate);
        return dYesterday;
    }

    public static Boolean isEqual(Date date) {
        Date today = new Date();
        if (date.equals(today)) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isEqualOrGreaterDate(Date date) {
        Date today = new Date();
        if (date.equals(today)) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isGreaterDate(Date date) {
        Date today = new Date();
        if (date.after(today)) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isLesserDate(Date date) {
        Date today = new Date();
        if (date.before(today)) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isMajor(Date date) {
        Date today = new Date();
        int iInc = 1900;
        int currentYear = today.getYear() + iInc;
        int givenYear = date.getYear() + iInc;

        if (currentYear <= givenYear) {
            return false;
        } else {
            if ((currentYear - givenYear) > 16) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static Boolean isNextYear() {
        long ltime = System.currentTimeMillis();
        Calendar cal = getCalendar();
        cal.setTimeInMillis(ltime);

        int iYear = cal.get(Calendar.YEAR);

        cal.add(Calendar.DATE, -1);
        int iYearYesterday = cal.get(Calendar.YEAR);

        if ((iYear - iYearYesterday) == 1) {
            return true;
        } else {
            return false;
        }
    }

}
