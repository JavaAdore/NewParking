/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.util.Calendar;
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
public class DeleteGarageSchedule implements Serializable {

    @Id
    @GeneratedValue
    int id;
    int garageId;

    public int getGarageId() {
        return garageId;
    }

    public void setGarageId(int garageId) {
        this.garageId = garageId;
    }
    @Temporal(javax.persistence.TemporalType.DATE)
    Date deleteDate = getDeleteAfter();

    public DeleteGarageSchedule() {
    }

    
    public DeleteGarageSchedule(int garageId) {
        this.garageId = garageId;
    }

    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Date getDeleteAfter() {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, utils.Constants.DELETE_AFTER);

        return c.getTime();

    }
    
   

}
