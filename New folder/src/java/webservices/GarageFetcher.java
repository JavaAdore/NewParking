/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DAOS.GarageSlotDoorsImp;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author orcl
 */
@Path("/getgarageobject")
public class GarageFetcher {

//     @GET
//    @Produces(MediaType.APPLICATION_JSON)
//   
//    public GObjects.Garage getGarageObject(@QueryParam(value = "garageId") int GarageId) {
//        Gson gosn = new Gson();
//       GObjects.Garage garage = GarageSlotDoorsImp.getInstance().generateGarageObject(GarageId);
//        return garage;
//
//    }

}
