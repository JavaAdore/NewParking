/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author orcl
 */
public class CustomDate {

    int day;
    int month;
    int year;

    public CustomDate() {
    }

    public static void main(String[] args) {

        Date date = getDate(new CustomDate(1, 10, 2011));
        System.out.println();
    }

    public CustomDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static Date getDate(CustomDate c) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(c.getYear(), c.getMonth() - 1, c.getDay(), 0, 0, 0);
        return new Date(cal.getTimeInMillis());
    }

    public static CustomDate getCustomDate(Date date1) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(date1);
            return new CustomDate(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH)+1, c.get(Calendar.YEAR));
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String toString() {

        return String.format("%s/%s/%s", getDay(), getMonth(), getYear());

    }

}
