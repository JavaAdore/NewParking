/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import DAOS.GarageImp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pojo.Garage;
import pojo.GarageStatus;

/**
 *
 * @author orcl
 */
public class GarageUtils {

    public static List<GarageStatus> getActiveSlots(Collection<GarageStatus> allGarageSlots) {
        List<GarageStatus> activeGarageSlots = new ArrayList<GarageStatus>();
        for (GarageStatus slot : allGarageSlots) {

            if (slot.getEnabled() == 1) {
                activeGarageSlots.add(slot);
            }
        }
        return activeGarageSlots;
    }

    public static int getNumberOfBusySlots(Garage garage) {

        int counter = 0;
        if (garage != null) {
            for (GarageStatus slot : garage.getGarageStatus()) {

                if (slot.getStatus() == 1) {
                    counter++;
                }

            }
        }
        return counter;
    }

    public static int getNumberOfUnvailableSlots(Garage garage) {

        int counter = 0;
        if (garage != null) {
            for (GarageStatus slot : garage.getGarageStatus()) {

                if (slot.getEnabled() == 0) {
                    counter++;
                }

            }
        }
        return counter;
    }

    public static String getEnabledGaraegSlots(int id) {

        JSONArray list = new JSONArray();
        JSONObject obj;
        ArrayList<WrappedGarageSlotsStatus> result = GarageImp.getInstance().getGarageSlotsStatus(id);
        for (WrappedGarageSlotsStatus wrappedGarageSlotsStatus : result) {
            obj = new JSONObject();
            obj.put("slotId", wrappedGarageSlotsStatus.getSlotId());
            obj.put("slotName", wrappedGarageSlotsStatus.getSlotName());
            obj.put("status", wrappedGarageSlotsStatus.getSlotStatus());
            obj.put("x", wrappedGarageSlotsStatus.getX());
            obj.put("y", wrappedGarageSlotsStatus.getY());

            list.add(obj);
        }
        return list.toString();

    }

    public static void main(String[] args) {

        System.out.println(getEnabledGaraegSlots(164));
    }
}
