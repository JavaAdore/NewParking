/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DAOS.GarageImp;
import GObjects.*;
import GObjects.Container;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.Date;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import pojo.Garage;
import pojo.GarageSlotsDoors;

/**
 *
 * @author orcl
 */
@Path("/allgarages")

public class AllGarages {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllGarages() {
        ;
        Gson obj = new Gson();
                

        JsonElement e = obj.toJsonTree(GarageImp.getInstance().getAllGarages());
        
        return "";

    }

    public static void main(String[] args) {
        new AllGarages().getAllGarages();
    }
}
