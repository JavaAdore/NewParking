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
public class Feedback implements Serializable {

    @Id
    @GeneratedValue
    int id;
    @ManyToOne
    Users sender;
    @ManyToOne
    Garage garage;
    String feedbackBody;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date feedbackDate = new Date();
    int seen;

}
