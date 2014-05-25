package pojo;
// Generated Mar 28, 2014 6:28:10 PM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Map generated by hbm2java
 */
@Entity
public class Map implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Integer mapId;
    private String mapUrl;
    private double ratio;
    private int width;
    private int height;
    private String unit;
    @OneToOne(mappedBy = "map")
    Garage garage;

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public Map() {
    }

    public Map(String mapUrl, double ratio, int width, int height, String unit) {
        this.mapUrl = mapUrl;
        this.ratio = ratio;
        this.width = width;
        this.height = height;
        this.unit = unit;
    }

    public Map(double ratio, int width, int height, String unit) {

        this.ratio = ratio;
        this.width = width;
        this.height = height;
        this.unit = unit;
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
