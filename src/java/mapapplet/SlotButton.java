/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapapplet;

/**
 *
 * @author orcl
 */
import java.awt.Button;

import javax.swing.JButton;

public class SlotButton extends JButton {

    int slotId;
    private int garageId=8;

    public SlotButton() {
    }

    public SlotButton(int slotId) {
        setSlotId(slotId);

    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getSlotId() {
        return slotId;
    }

    public int getGarageId() {
        return garageId;
    }

    public void setGarageId(int garageId) {
        this.garageId = garageId;
    }
}
