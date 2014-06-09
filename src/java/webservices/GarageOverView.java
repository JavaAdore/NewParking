/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DAOS.GarageImp;
import DAOS.GarageSlotsStatusImp;
import java.util.ArrayList;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.WrappedGarageSlotsStatus;

/**
 *
 * @author orcl
 */
@Path("/garageoverview")
public class GarageOverView {

    GarageSlotsStatusImp garageSlotsStatusImp = GarageSlotsStatusImp.getInstance();
    GarageImp garageImp = GarageImp.getInstance();
    JSONArray list = new JSONArray();
    JSONObject obj, obj2;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String getGarageOverView(@FormParam(value = "id") String id) {

        String imagePath = garageImp.getImagePath(Integer.parseInt(id));
        obj2 = new JSONObject();
        ArrayList<WrappedGarageSlotsStatus> result = garageSlotsStatusImp.getGarageSlotsStatus(Integer.parseInt(id));
        obj2.put("imagePath", imagePath);
        for (WrappedGarageSlotsStatus wrappedGarageSlotsStatus : result) 
        {
            obj = new JSONObject();
            obj.put("slotId", wrappedGarageSlotsStatus.getSlotId());
            obj.put("status", wrappedGarageSlotsStatus.getSlotStatus());
            obj.put("x", wrappedGarageSlotsStatus.getX());
            obj.put("y", wrappedGarageSlotsStatus.getY());
            list.add(obj);
        }
        obj2.put("slots", list);
        System.out.println();
        return obj2.toString();
    }
}
