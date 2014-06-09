/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DAOS.GarageSlotDoorsImp;
import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author orcl
 */
@Path("/garageHandler")
public class GarageHandler {

    GarageSlotDoorsImp garageSlotDoorsImp = GarageSlotDoorsImp.getInstance();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String handleThisGarage(@QueryParam(value = "garage")String garage) {
        Gson gson = new Gson();
        GObjects.Garage x = (GObjects.Garage) gson.fromJson(garage, GObjects.Garage.class);
        return garageSlotDoorsImp.handleThisGaragePlease(x)+"";

    }
}
