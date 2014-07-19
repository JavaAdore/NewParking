/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import java.awt.geom.CubicCurve2D;
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

    public CustomDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public CustomDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);

    }

    public CustomDate() {
        this(new Date());
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
}
