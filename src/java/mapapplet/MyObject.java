/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapapplet;

import java.util.ArrayList;

/**
 *
 * @author orcl
 */
public class MyObject {

    ArrayList<PointButton> points = new ArrayList<>();
    int slotId;
    int doorId;

    public MyObject() {
    }

    public MyObject(int slotId, int doorId) {
        this.slotId = slotId;
        this.doorId = doorId;
    }

    
    
    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getDoorId() {
        return doorId;
    }

    public void setDoorId(int doorId) {
        this.doorId = doorId;
    }
    
    
    public void AddToList(PointButton p) {
        points.add(p);
    }

    public void removeFromList(PointButton p) {
        for (int i = 0; i < points.size(); i++) {

            if (points.get(i).getIdentifer().equalsIgnoreCase(p.getIdentifer())) {
                points.remove(i);
            }
        }

    }

    public ArrayList<PointButton> getPoints() {
        return points;
    }
}
