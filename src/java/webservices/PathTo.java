/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DAOS.GarageSlotDoorsImp;
import DAOS.SlotsDetailsImp;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pojo.GarageSlotsDoors;

/**
 *
 * @author orcl
 */
@Path("/pathto")
public class PathTo {

    SlotsDetailsImp slotDetailsImp = SlotsDetailsImp.getInstance();

    JSONArray list = new JSONArray();
    JSONObject obj;

    @POST
    @Produces(MediaType.TEXT_HTML)
    public String giveMePathTo(@FormParam("garageid") int garageId, @FormParam("slotid") int slotId, @FormParam("lat") double lat, @QueryParam("lon") double lon) {
        int doorId = GarageSlotDoorsImp.getInstance().getNearestDoor(garageId, lat, lon);
        GarageSlotsDoors result = slotDetailsImp.getInstance().getSlotDetail(slotId, doorId);
        obj = new JSONObject();
       // obj.put("points", );
        return result.getPoints().replace("\\", "");
    }

}
