/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author orcl
 */
@Entity
public class InActivePeriod implements Serializable {

    @Id
    @GeneratedValue
    int id;
    @ManyToOne()
    Garage garage;
    
    java.sql.Timestamp fromDate = new java.sql.Timestamp(new Date().getTime());
    
    java.sql.Timestamp toDate;
    String reason;

    public InActivePeriod(Garage garage) {
        this.garage = garage;

    }

    public Garage getGarage() {
        return garage;
    }

    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public Timestamp getToDate() {
        return toDate;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
    }

   



    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InActivePeriod() {
    }

}
