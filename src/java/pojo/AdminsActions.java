package pojo;
// Generated Mar 28, 2014 6:28:10 PM by Hibernate Tools 3.2.1.GA

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 * AdminsActions generated by hbm2java
 */
@Entity
public class AdminsActions implements java.io.Serializable {

    @Id
    @GeneratedValue
    int id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Employees admin;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Employees employee;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date actionDate = new Date();
    private char actionType;

    public AdminsActions(Employees admin, Employees employee, char actionType) {
        setActionType(actionType);
        setAdmin(admin);
        setEmployee(employee);
    }

    public AdminsActions(int admin, int employee, char actionType) {
        setActionType(actionType);
        setAdmin(new Employees(admin));
        setEmployee(new Employees(employee));

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employees getAdmin() {
        return admin;
    }

    public void setAdmin(Employees admin) {
        this.admin = admin;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public char getActionType() {
        return actionType;
    }

    public void setActionType(char actionType) {
        this.actionType = actionType;
    }

    public AdminsActions() {
    }

}
