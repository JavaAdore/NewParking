package pojo;
// Generated Mar 28, 2014 6:28:10 PM by Hibernate Tools 3.2.1.GA

import DAOS.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import utils.Constants;

@Entity
public class Garage implements java.io.Serializable {

    @Id
    @GeneratedValue
    private int garageId;
    String description;
    String image;
   @Column(unique = true)
    private String title;
    private double lon;
    private double lat;
    int enabled = 1;
    String website;
    int slotWidth;

    int slotHeight;
    @OneToOne(orphanRemoval = true, fetch = FetchType.EAGER)
    private Address address;
    @OneToOne(orphanRemoval = true, fetch = FetchType.EAGER)
    private Map map;
    @OneToMany(mappedBy = "garage", orphanRemoval = true)
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection< Employees> employees = new ArrayList<>();
    @OneToMany(mappedBy = "garage", orphanRemoval = true)
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection< GarageDoors> garageDoors = new ArrayList<>();
    @OneToMany(mappedBy = "garage", orphanRemoval = true)
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection< GarageStatus> garageStatus = new ArrayList<>();

    @OneToMany(mappedBy = "garage", orphanRemoval = true)
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection< Feedback> feedbacks = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection< ContactNumber> contactNumbers = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection< FaxContact> faxNumbers = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<Visit> visits = new ArrayList<>();

    @OneToMany(orphanRemoval = true, mappedBy = "garage")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<InActivePeriod> inActivePeriods = new ArrayList<>();

    public int getGarageId() {
        return garageId;
    }

    public void setGarageId(int garageId) {
        this.garageId = garageId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Collection<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Collection<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Collection<ContactNumber> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(Collection<ContactNumber> contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public Collection<FaxContact> getFaxNumbers() {
        return faxNumbers;
    }

    public void setFaxNumbers(Collection<FaxContact> faxNumbers) {
        this.faxNumbers = faxNumbers;
    }

    public Collection<EmailAddress> getEmails() {
        return emails;
    }

    public void setEmails(Collection<EmailAddress> emails) {
        this.emails = emails;
    }

    @OneToMany(orphanRemoval = true)
    @ElementCollection(fetch = FetchType.LAZY)
    private Collection< EmailAddress> emails = new ArrayList<>();

    private double hourRateInRush;

    public Garage() {
        this.employees = new HashSet<Employees>();
    }

    public Garage(int id) {
        this.employees = new HashSet<Employees>();
        garageId = id;
    }

    public Garage(Map map, String title, int lon, int lat) {
        this.map = map;
        this.title = title;
        this.lon = lon;
        this.lat = lat;
    }

    public Garage(Map map, String title, String country, String city, double lon, double lat) {
        this.employees = new HashSet<Employees>();
        this.map = map;
        this.title = title;
        this.lon = lon;
        this.lat = lat;

    }

    public Garage(String title, String country, String city, double hourRateInRush, double hourRateOutOfRush, int slots, int doors, double lat, double lon) {
        this.employees = new HashSet<Employees>();

        this.title = title;

        this.lon = lon;
        this.lat = lat;
        this.hourRateInRush = hourRateInRush;

    }

    public Collection<GarageStatus> getGarageStatus() {
        return garageStatus;
    }

    public void setGarageStatus(Collection<GarageStatus> garageStatus) {
        this.garageStatus = garageStatus;
    }

    public Collection<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employees> employees) {
        this.employees = employees;
    }

    public Collection<GarageDoors> getGarageDoors() {
        return garageDoors;
    }

    public void setGarageDoors(Collection<GarageDoors> garageDoors) {
        this.garageDoors = garageDoors;
    }

    public void setGarageId(Integer garageId) {
        this.garageId = garageId;
    }

    public Map getMap() {
        return this.map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLon() {
        return this.lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Collection< Employees> getEmployeeses() {
        return this.employees;
    }

    public void setEmployeeses(Collection< Employees> employees) {
        this.employees = employees;
    }

    public List<InActivePeriod> getInActivePeriods() {
        return inActivePeriods;
    }

    public void setInActivePeriods(List<InActivePeriod> inActivePeriods) {
        this.inActivePeriods = inActivePeriods;
    }

    public Collection<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Collection<Visit> visits) {
        this.visits = visits;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public double getHourRateInRush() {
        return hourRateInRush;
    }

    public void setHourRateInRush(double hourRateInRush) {
        this.hourRateInRush = hourRateInRush;
    }

    public int getSlotWidth() {
        return slotWidth;
    }

    public void setSlotWidth(int slotWidth) {
        this.slotWidth = slotWidth;
    }

    public int getSlotHeight() {
        return slotHeight;
    }

    public void setSlotHeight(int slotHeight) {
        this.slotHeight = slotHeight;
    }
}
