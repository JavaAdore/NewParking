package webservices;

import DAOS.GarageImp;
import DAOS.SlotsDetailsImp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import pojo.Garage;
import pojo.GarageSlotsDoors;

/**
 *
 * @author orcl
 */
@Path("/getgaragedetails")

public class GarageDetails {

    SlotsDetailsImp slotDetailsImp = SlotsDetailsImp.getInstance();

    JSONArray list = new JSONArray();
    JSONObject obj;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getgaragedetails(@QueryParam("doorId") int doorId, @QueryParam("slotId") int slotId) {
        GarageSlotsDoors result = slotDetailsImp.getInstance().getSlotDetail(slotId, doorId);
        obj = new JSONObject();
        if (result != null) {

            try {
                obj.put("points", result.getPoints());
            } catch (JSONException ex) {
                ex.printStackTrace();
            }

        }
        return obj.toString().replace("\\", "");
    }

    @Path("/garages")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllGaragesWs() {

        ArrayList<Garage> garages = GarageImp.getInstance().getAllGarages();

        JSONArray arr = new JSONArray();

        for (Garage g : garages) {
            try {
                JSONObject obj = new JSONObject();

                obj.put("city", (g.getAddress().getCity() != null) ? g.getAddress().getCity() : "Undefined");
                obj.put("country", (g.getAddress().getCountry() != null) ? g.getAddress().getCountry() : "Undefined");
                obj.put("doors", g.getGarageDoors().size());
                obj.put("id", g.getGarageId());
                obj.put("lat", g.getLat());
                obj.put("lon", g.getLon());
                obj.put("title", g.getTitle());
                obj.put("slots", g.getGarageStatus().size());
                obj.put("mapURL", g.getMap().getMapUrl());
                obj.put("height", g.getMap().getHeight());
                obj.put("width", g.getMap().getWidth());
                obj.put("ratio", g.getMap().getRatio());
                obj.put("unit", g.getMap().getUnit());
                obj.put("slotWidth", g.getSlotWidth());
                obj.put("slotHeight", g.getSlotHeight());

                arr = arr.put(obj);
            } catch (JSONException ex) {
                Logger.getLogger(GarageDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return arr.toString();
    }

}
