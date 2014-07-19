/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DAOS.GarageImp;
import DAOS.GarageSlotImp;
import DAOS.UserImp;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import pojo.Garage;
import pojo.Users;

/**
 *
 * @author orcl
 */

@Path("/update")

public class UpdateSlotStatus {

    @Path("/slotStatus")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String updateSlot(@QueryParam("slotid") int slotid, @QueryParam("status") int status) {
        System.out.println(String.format("webservice has been called and slotid is %s status is %s", slotid, status));
        return GarageImp.getInstance().UpdateGarageSlot(slotid, status);
    }

    @Path("/changeSlotStatus")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String updateSlotStatus(@FormParam("slotid") final String slotid, @FormParam("status") int status , @FormParam("userid") final int userId , @FormParam("increase") final String increase) {
        System.out.println(String.format("slot id = %s and status is %s userId = %s increase = %s",slotid , status,userId, increase));
      
            try {
                
                new Thread() {
                    @Override
                    public void run() {
                         {
                            if (increase.equals("1")) {
                                UserImp.getInstance().addVisit(new Users((userId)), slotid);
                            }
                        }
                    }
                }.start();
                return GarageImp.getInstance().UpdateGarageSlot(Integer.parseInt(slotid), status);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        
        return utils.Constants.FAILED+"";

    }

    @Path("/slotAvailability")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String setSlotEnabled(@QueryParam("slotId") int slotId, @QueryParam("enabled") int enabled) {
        return GarageImp.getInstance().setSlotEnabled(slotId, enabled);
    }

    @Path("/garageAvailability")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String setGarageEnabled(@QueryParam("garageid") int gargeId, @QueryParam("enabled") int enabled) {
        return GarageImp.getInstance().setGaragessEnabled(gargeId, enabled);
    }
}
