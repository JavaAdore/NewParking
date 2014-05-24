/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapapplet;

/**
 *
 * @author orcl
 */
public class Constants {
    
    private static int DOOR_BUTTON_WIDTH = 100;
    private static int DOOR_BUTTON_HEIGHT = 30;
    
    
    private static int SLOT_BUTTON_WIDTH = 100;
    private static int SLOT_BUTTON_HEIGHT = 30;
    
    
    private static int POINT_BUTTON_WIDTH = 100;
    private static int POINT_BUTTON_HEIGHT = 30;

    public static void initializeDimension(int width, int height)
    {
        DOOR_BUTTON_WIDTH = (int) Math.round(0.0306372549019608*width);
        DOOR_BUTTON_HEIGHT= (int) Math.round(0.0122549019607843*height);
        
        SLOT_BUTTON_WIDTH = (int) Math.round(0.0306372549019608*width);
        SLOT_BUTTON_HEIGHT= (int) Math.round(0.0122549019607843*height);
        
        POINT_BUTTON_WIDTH = (int) Math.round(0.0306372549019608*width);
        POINT_BUTTON_HEIGHT= (int) Math.round(0.0122549019607843*height);
        
    }

    
    public void initializeConstants()
    {
    
    
    }
    public static  int getDOOR_BUTTON_WIDTH() {
        return DOOR_BUTTON_WIDTH;
    }

    private void setDOOR_BUTTON_WIDTH(int DOOR_BUTTON_WIDTH) {
        this.DOOR_BUTTON_WIDTH = DOOR_BUTTON_WIDTH;
    }

    public static  int getDOOR_BUTTON_HEIGHT() {
        return DOOR_BUTTON_HEIGHT;
    }

    private void setDOOT_BUTTON_HEIGHT(int DOOT_BUTTON_HEIGHT) {
        this.DOOR_BUTTON_HEIGHT = DOOT_BUTTON_HEIGHT;
    }

    public static  int getSLOT_BUTTON_WIDTH() {
        return SLOT_BUTTON_WIDTH;
    }

    private void setSLOT_BUTTON_WIDTH(int SLOT_BUTTON_WIDTH) {
        this.SLOT_BUTTON_WIDTH = SLOT_BUTTON_WIDTH;
    }

    public static  int getSLOT_BUTTON_HEIGHT() {
        return SLOT_BUTTON_HEIGHT;
    }

    private void setSLOT_BUTTON_HEIGHT(int SLOT_BUTTON_HEIGHT) {
        this.SLOT_BUTTON_HEIGHT = SLOT_BUTTON_HEIGHT;
    }

    public static  int getPOINT_BUTTON_WIDTH() {
        return POINT_BUTTON_WIDTH;
    }

    private void setPOINT_BUTTON_WIDTH(int POINT_BUTTON_WIDTH) {
        this.POINT_BUTTON_WIDTH = POINT_BUTTON_WIDTH;
    }

    public static  int getPOINT_BUTTON_HEIGHT() {
        return POINT_BUTTON_HEIGHT;
    }

    private void setPOINT_BUTTON_HEIGHT(int POINT_BUTTON_HEIGHT) {
        this.POINT_BUTTON_HEIGHT = POINT_BUTTON_HEIGHT;
    }
    
    
     
}
