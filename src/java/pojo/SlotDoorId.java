/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author orcl
 */
@Embeddable
public class SlotDoorId implements Serializable {

    @ManyToOne(cascade = CascadeType.REMOVE )
    GarageStatus slotId;
    @ManyToOne(cascade = CascadeType.REMOVE)
    GarageDoors doorId;

    public SlotDoorId() {
    }

    public SlotDoorId(GarageStatus slotId, GarageDoors doorId) {
        this.slotId = slotId;
        this.doorId = doorId;
    }

    public SlotDoorId(int slotId, int doorId) {
        this.slotId = new GarageStatus(slotId);
        this.doorId = new GarageDoors(doorId);
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

}
