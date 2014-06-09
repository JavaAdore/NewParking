/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapapplet;

import com.google.gson.JsonObject;
import java.io.Serializable;
import utils.Step;
import static utils.Utils.ConvertStepToJson;

/**
 *
 * @author orcl
 */
public class SlotDoorPoints implements Serializable {

    int slotId;
    int doorId;
    String path = "";

    public SlotDoorPoints(int slotId, int doorId) {
        this.slotId = slotId;
        this.doorId = doorId;
    }

    public void appendTopath(int order, int x, int y) {
        path += ConvertStepToJson(order, x, y);

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public JsonObject ConvertStepToJson(Step step) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("stepOrder", step.getId());
        jsonObject.addProperty("x", step.getX());
        jsonObject.addProperty("y", step.getY());
        return jsonObject;

    }

    public String ConvertStepToJson(int id, int x, int y) {
        Step step = new Step(id, x, y);
        return ConvertStepToJson(step).toString();
    }
}
