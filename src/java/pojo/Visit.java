/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author orcl
 */
@Entity
public class Visit implements Serializable {

    @Id
    @GeneratedValue
    int visitId;
    @ManyToOne
    Garage garage;
    @ManyToOne()
    Users user;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date visitDate = new Date();
    int numberOfVisits = 1;

    public Visit() {
    }

    public Visit(Garage garage, Users user) {
        this.garage = garage;
        this.user = user;
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int visitingTimes) {
        this.numberOfVisits = visitingTimes;
    }

    public void increaseNumberOfVisits() {

        numberOfVisits++;
    }

}
