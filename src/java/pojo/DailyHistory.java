/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import daosint.ReportsInterface;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import utils.Utils;

/**
 *
 * @author orcl
 */
@Entity
public class DailyHistory implements ReportsInterface , Serializable {

    @Id
    @GeneratedValue
    int recordId;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date recordDate = new Date();
    double hours;
    double income;
    @ManyToOne
    GarageStatus slotId;

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public GarageStatus getSlotId() {
        return slotId;
    }

    public void setSlotId(GarageStatus slotId) {
        this.slotId = slotId;
    }
    

    public DailyHistory() {

    }

    public DailyHistory(Date recordDate, double hours,double income) {
        this.recordDate = recordDate;
        this.hours = hours;
        this.income = income;

    }

    public DailyHistory(double hours, GarageStatus slotid) {
        this.hours = hours;
        this.slotId = slotid;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public GarageStatus getSlotid() {
        return slotId;
    }

    public void setSlotid(GarageStatus slotid) {
        this.slotId = slotid;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    @Override
    public String getIdentifier() {
        return "dd";
    }

}
