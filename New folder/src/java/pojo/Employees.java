package pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class Employees implements java.io.Serializable {

    @Id
    @GeneratedValue
    private int employeeId;

    @ManyToOne
    private Roles roles;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Garage garage;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String gender;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthDate;
    @ElementCollection(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "admin", orphanRemoval = true)
    private Collection<AdminsActions> admin = new ArrayList<>();
    @ElementCollection(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "employee", orphanRemoval = true)
    private Collection<AdminsActions> employee = new ArrayList<>();

    public Collection<AdminsActions> getAdmin() {
        return admin;
    }

    public void setAdmin(Collection<AdminsActions> admin) {
        this.admin = admin;
    }

    public Collection<AdminsActions> getEmployee() {
        return employee;
    }

    public void setEmployee(Collection<AdminsActions> employee) {
        this.employee = employee;
    }

    public Employees() {
    }

    public Employees(int employeeId) {
        this.employeeId = employeeId;
    }

    public Employees(String firstName, String lastName, String email, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Employees(Roles roles, Garage garage, String firstName, String lastName, String email, String password, String gender, Date birthDate) {
        this.roles = roles;
        this.garage = garage;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;

    }

    public Employees(String firstName, String lastName, String email, String password, String gender, Date birthDate, Roles roles, Garage garage) {
        this.roles = roles;
        this.garage = garage;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;

    }

    public Employees(String firstName, String lastName, String email, String password, String gender, Date birthDate) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Roles getRoles() {
        return this.roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Garage getGarage() {
        return this.garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}
