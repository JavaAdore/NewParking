/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import pojo.Garage;
import pojo.Roles;

/**
 *
 * @author ahmed
 */
public class EmployeeWrapper {

    public EmployeeWrapper(Integer employeeId, String firstName, String lastName, String email) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private Integer employeeId;

    private Roles roles;

    private Garage garage;

    private String firstName;

    private String lastName;

    private String email;

    private int active;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    

    public EmployeeWrapper(Integer employeeId, Roles roles, Garage garage, String firstName, String lastName, String email, int active) {

        this.employeeId = employeeId;
        this.roles = roles;
        this.garage = garage;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public Roles getRoles() {
        return roles;
    }

    public Garage getGarage() {
        return garage;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

}
