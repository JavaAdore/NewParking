/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DAOS.GarageImp;
import DAOS.SlotsDetailsImp;
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
import pojo.GarageSlotsDoors;
import utils.WrappedGarage;

/**
 *
 * @author orcl
 */
@Path("/getgaragedetails")

public class GarageDetails {

    SlotsDetailsImp slotDetailsImp = SlotsDetailsImp.getInstance();

    JSONArray list = new JSONArray();
    JSONObject obj;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String getgaragedetails(@FormParam("doorId") int doorId, @FormParam("slotId") int slotId) {
        System.out.println("webservice has beeen called");
        System.out.println(doorId + "  " + slotId);
        GarageSlotsDoors result = slotDetailsImp.getInstance().getSlotDetail(slotId, doorId);
        obj = new JSONObject();

        return result.getPoints().replace("\\", "");

    }
}
