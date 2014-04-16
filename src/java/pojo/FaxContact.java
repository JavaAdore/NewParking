/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author orcl
 */
@Entity
public class FaxContact implements Serializable {
    @Id
    @GeneratedValue
    int id;
    String faxNumber;
    
    public FaxContact(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public FaxContact() {
    }
     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }
}
