/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author orcl
 */
@Entity
public class GarageSlotsDoors implements Serializable {

    //@EmbeddedId
    // SlotDoorId id;
    @Id
    @GeneratedValue
    int Id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    GarageStatus slotId;
    @ManyToOne(cascade = CascadeType.REMOVE)
    GarageDoors doorId;

    @Column(length = 4000)
    String points;

    public GarageSlotsDoors() {
    }

//    public GarageSlotsDoors(SlotDoorId id, String points)
//    {
//        this.id = id;
//        this.points = points;
//    }
    public GarageSlotsDoors(GarageStatus slotId, GarageDoors doorId) {

        //  id = new SlotDoorId(slotId, doorId);
    }

    public GarageSlotsDoors(GarageStatus slotId, GarageDoors doorId, String points) {
       this.slotId = slotId;
       this.doorId = doorId;
        this.points = points;

    }

    public GarageSlotsDoors(int slotId, int doorId, String points) {
        //id = new SlotDoorId(slotId, doorId);
        this.slotId = new GarageStatus(slotId);
        this.doorId = new GarageDoors(doorId);

        this.points = points;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public GarageStatus getSlotId() {
        return slotId;
    }

    public void setSlotId(GarageStatus slotId) {
        this.slotId = slotId;
    }

    public GarageDoors getDoorId() {
        return doorId;
    }

    public void setDoorId(GarageDoors doorId) {
        this.doorId = doorId;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

//    public SlotDoorId getId() {
//        return id;
//    }
//
//    public void setId(SlotDoorId id) {
//        this.id = id;
//    }
}
