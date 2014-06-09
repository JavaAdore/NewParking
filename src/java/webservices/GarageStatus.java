/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DAOS.GarageImp;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.WrappedGarage;


/**
 *
 * @author orcl
 */
@Path("/nearestGarages")
public class GarageStatus {

    JSONArray list = new JSONArray();
    JSONObject obj;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getNearstGarages(@QueryParam("lat") double lat, @QueryParam("lon") double lon) 
    {

        ArrayList<WrappedGarage> result = GarageImp.getInstance().getNearGarages(lat, lon);
        for (WrappedGarage garage : result) {
            obj = new JSONObject();
            obj.put("garageName", garage.getGarageName());
            obj.put("numberOfBusySlots", garage.getNumberOfBusySlots());
            obj.put("numberOfAvailableSlots", garage.getNumerOfAvailableSlots());
            obj.put("lat", garage.getLatitude());
            obj.put("lon", garage.getLontitude());
            list.add(obj);
        }
        return list.toString();
    }
}
