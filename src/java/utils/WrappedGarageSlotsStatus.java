/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author orcl
 */
public class WrappedGarageSlotsStatus {

    int slotId;
    int slotStatus;
    String slotName;
    

    
    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }
    int x;
    int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public WrappedGarageSlotsStatus() {
    }

    public WrappedGarageSlotsStatus(int slotId, String slotName, int slotStatus, int x, int y) {
        this.slotId = slotId;
        this.slotName = slotName;
        this.slotStatus = slotStatus;
        this.x = x;
        this.y = y;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(int slotStatus) {
        this.slotStatus = slotStatus;
    }

}
